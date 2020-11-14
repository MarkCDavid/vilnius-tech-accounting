package vilnius.tech.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {


    public static <T> Field extractField(T value, String fieldName) {

        var clazz = value.getClass();
        while(clazz != null) {
            try {
                var field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException ignored) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

    public static List<Field> getFieldsOfType(Class<?> searchIn, Class<?> searchFor) {
        var result = new ArrayList<Field>();
        var clazz = searchIn;
        while(clazz != null) {
            for(var field : clazz.getDeclaredFields()) {
                if(field.getType().equals(searchFor))
                    result.add(field);
            }
            clazz = clazz.getSuperclass();
        }
        return result;
    }

    public static <T> String extractString(T value, Field field) {
        try {
            return field.get(value).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ReflectionUtils() { }
}
