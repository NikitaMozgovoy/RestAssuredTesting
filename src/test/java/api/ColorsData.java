package api;

import lombok.Getter;

/** POJO-класс ответа на GET-запрос по адресу https://reqres.in/api/unknown */
@Getter
public class ColorsData {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;

    public ColorsData(Integer id, String name, Integer year, String color, String pantone_value) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantone_value = pantone_value;
    }


}
