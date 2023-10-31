package org.glebchanskiy.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Race implements Entity {
    private Integer id;

    private String name;

    private String description;

    @Override
    public String toString() {
        return name;
    }
}
