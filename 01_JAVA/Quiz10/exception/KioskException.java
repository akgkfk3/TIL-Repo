package sesac.java.practice.exception;

public class KioskException extends Exception {

    public KioskException() {
    }

    public KioskException(int code) {
        super("error code : " + code);
    }
}
