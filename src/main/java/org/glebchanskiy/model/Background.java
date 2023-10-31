package org.glebchanskiy.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Background implements Entity {
    private Integer id;

    private String name;

    private String description;

    @Override
    public String toString() {
        return name;
    }
}
