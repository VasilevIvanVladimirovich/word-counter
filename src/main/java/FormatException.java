
public class FormatException {
    final static public int IS_TXT = 1;

    private int code;

    public FormatException(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
