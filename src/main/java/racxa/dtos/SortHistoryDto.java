package racxa.dtos;

public class SortHistoryDto {
    private long userId;
    private String username;
    private String algoType;
    private String sortDate;

    public SortHistoryDto(){

    }
    public SortHistoryDto(long userId, String username, String algoType, String sortDate) {
        this.userId = userId;
        this.username = username;
        this.algoType = algoType;
        this.sortDate = sortDate;
    }
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlgoType() {
        return algoType;
    }

    public void setAlgoType(String algoType) {
        this.algoType = algoType;
    }

    public String getSortDate() {
        return sortDate;
    }

    public void setSortDate(String sortDate) {
        this.sortDate = sortDate;
    }

    @Override
    public String toString() {
        return "SortHistory{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", algoType='" + algoType + '\'' +
                ", sortDate='" + sortDate + '\'' +
                '}';
    }
}
