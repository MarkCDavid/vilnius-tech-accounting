package vilnius.tech.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public static <T> Method extractGetter(T value, String getterName) {

        var clazz = value.getClass();
        while(clazz != null) {
            try {
                var method = clazz.getDeclaredMethod(getterName);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException ignored) {
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

    public static <T> String extractString(T value, Method method) {
        try {
            var result = method.invoke(value);
            if(result == null)
                return "ERROR";
            return result.toString();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static <T> String extractString(T value, Field field) {
        try {
            var result = field.get(value);
            if(result == null)
                return "ERROR";
            return result.toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ReflectionUtils() { }
}
