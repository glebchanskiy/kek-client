package org.glebchanskiy.model;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Char {
    private Integer id;

    private String name;

    private Class charClass;

    private Race race;

    private Background background;

    private String description;

    private List<Equip> equips;
}
