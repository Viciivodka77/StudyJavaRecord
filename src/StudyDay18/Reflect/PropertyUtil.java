package StudyDay18.Reflect;

import java.lang.reflect.Field;

public class PropertyUtil {

    public void setProperty(Object o,String propertyName,Object value) throws NoSuchFieldException, IllegalAccessException {
        Class<?> oClass = o.getClass();
        Field field = oClass.getDeclaredField(propertyName);
        field.setAccessible(true);
        field.set(o,value);
    }

}
