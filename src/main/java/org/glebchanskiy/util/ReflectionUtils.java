package org.glebchanskiy.util;

import org.glebchanskiy.model.Entity;

import java.lang.reflect.Field;

public class ReflectionUtils {
    public static  <T extends Entity> T dynamicInstance(Class<T> clazz, String nameValue, String descriptionValue) {
        try {
            Object obj = clazz.getDeclaredConstructor().newInstance();
            Field name = clazz.getField("name");
            Field description = clazz.getField("description");
            name.setAccessible(true);
            name.set(obj, nameValue);
            description.setAccessible(true);
            description.set(obj, descriptionValue);
            return (T) obj;
        } catch (Exception e) {
            return null;
        }
    }
}
