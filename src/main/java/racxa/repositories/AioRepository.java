package racxa.repositories;

import racxa.dtos.SortHistoryDto;
import racxa.utils.exceptions.InvalidCredentialsException;
import racxa.utils.exceptions.RecordAlreadyExistsException;

import java.util.ArrayList;

public interface AioRepository {
    void loginUser(String username,String password) throws InvalidCredentialsException;
    void registerUser(String username,String password) throws RecordAlreadyExistsException, InvalidCredentialsException;
    void registerSort(String algoType);
    void getAlgoTypes();
    ArrayList<SortHistoryDto> getSortingHistory();
}
