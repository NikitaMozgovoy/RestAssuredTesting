package api;

/** POJO-класс POST-запроса на адресу https://reqres.in/api/register */
public class Register {
    private String email;
    private String password;

    public Register(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
