package org.glebchanskiy.model;


import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Class {
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
