package api;

/** POJO-класс ответа при неудавшейся регистрации */
public class UnsuccessfulReg {
    private String error;

    public UnsuccessfulReg(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
