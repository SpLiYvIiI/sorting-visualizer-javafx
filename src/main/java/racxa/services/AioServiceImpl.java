package racxa.services;

import lombok.extern.slf4j.Slf4j;
import racxa.dtos.SortHistoryDto;
import racxa.utils.Constants;
import racxa.utils.JdbcConnection;
import racxa.utils.exceptions.InvalidCredentialsException;
import racxa.utils.exceptions.RecordAlreadyExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
public class AioServiceImpl implements AioService{

    @Override
    public void loginUser(String username, String password) throws InvalidCredentialsException {
        try {
            Connection c = JdbcConnection.getConnection();
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1,username);
            ResultSet  resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("password").equals(password)) {
                    Constants.CURRUSER = resultSet.getLong("userid");
                    return;
                }
            }
        }
        catch (SQLException err){
            log.error("Couldn't execute loginUser() " + err.getMessage());
        }
        throw new InvalidCredentialsException("Username or password is incorrect");
    }

    @Override
    public void registerUser(String username, String password) throws RecordAlreadyExistsException, InvalidCredentialsException {

        if(username.indexOf(" ") != -1){
            throw new InvalidCredentialsException("Username shouldn't contain spaces");
        }
        if(username.length() <= 3){
            throw new InvalidCredentialsException("Username should be at least 4 characters long");
        }
        if(password.trim().length() <= 3){
            throw new InvalidCredentialsException("Password should be at least 4 characters long");
        }
        try {
            Connection c = JdbcConnection.getConnection();
            String query = "INSERT INTO users(username, password) VALUES (?,?)";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.executeUpdate();
        }
        catch (SQLException err){
            log.error("Couldn't execute registerUser() " + err.getMessage());
            throw new RecordAlreadyExistsException("User with such username already exists");
        }
    }

    @Override
    public void registerSort(String algoType) {
        try{
            Connection c = JdbcConnection.getConnection();
            String query = "INSERT INTO sort_history(userid, sortalgoid) VALUES (?,?)";
            PreparedStatement pstmt;
            pstmt = c.prepareStatement(query);
            pstmt.setLong(1,Constants.CURRUSER);
            pstmt.setLong(2, Constants.AVAILABLEALGOS.get(algoType));
            pstmt.executeUpdate();
        } catch (SQLException err) {
            log.error("Couldn't execute registerSort() " + err.getMessage());
        }
    }

    @Override
    public void getAlgoTypes() {
        try {
            Constants.AVAILABLEALGOS = new HashMap<>();
            Connection c = JdbcConnection.getConnection();
            String query = "SELECT * FROM sort_algos";
            Statement st = c.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Constants.AVAILABLEALGOS.put(resultSet.getString("sortName"),resultSet.getLong("sortAlgoId"));
            }
        }
        catch (SQLException err){
            log.error("Couldn't execute getAlgoTypes() " + err.getMessage());
        }
    }

    @Override
    public ArrayList<SortHistoryDto> getSortingHistory() {
        ArrayList <SortHistoryDto> res = new ArrayList<>();
        try {
            Connection c = JdbcConnection.getConnection();
            String query = "SELECT u.userId,u.username,a.sortName, i.startDate FROM sort_history" +
                    " as i LEFT JOIN users as u on u.userid = i.userid " +
                    "LEFT JOIN sort_algos as a on a.sortalgoid = i.sortalgoid";
            Statement st = c.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            while(resultSet.next()){
                SortHistoryDto s = new SortHistoryDto();
                s.setUserId(resultSet.getLong("userId"));
                s.setUsername(resultSet.getString("username"));
                s.setAlgoType(resultSet.getString("sortName"));
                Timestamp ts = resultSet.getTimestamp("startDate");
                String date = ts.toString();
                s.setSortDate(date.substring(0,date.length()-7));
                res.add(s);
            }
        }
        catch (SQLException err){
           log.error("Couldn't execute getSortingHistory() " + err.getMessage());
        }
        return res;
    }
}
