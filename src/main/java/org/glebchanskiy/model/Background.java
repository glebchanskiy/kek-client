package org.glebchanskiy.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Background {
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @Override
    public String toString() {
        return name;
    }
}
