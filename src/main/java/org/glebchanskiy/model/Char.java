package org.glebchanskiy.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Char {
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Class charClass;

    @NonNull
    private Race race;

    @NonNull
    private Background background;

    @NonNull
    private String description;

    private List<Equip> equips;
}
