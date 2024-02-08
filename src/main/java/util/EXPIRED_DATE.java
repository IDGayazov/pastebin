package util;

public enum EXPIRED_DATE {
    TEN_MINUTES("10 minutes"),
    DAY("day"),
    WEEK("week");

    private final String expirationTime;

    EXPIRED_DATE(String time){
        this.expirationTime = time;
    }

    public String getTime(){
        return this.expirationTime;
    }
}
