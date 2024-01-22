package api;

import lombok.Getter;

/** POJO-класс успешного ответа при регистрации */
@Getter
public class SuccessReg {
    private Integer id;
    private String token;

    public SuccessReg(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

}
