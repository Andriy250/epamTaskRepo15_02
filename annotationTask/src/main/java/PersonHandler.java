import java.lang.reflect.Field;

public class PersonHandler {

    static public <T> void objectReader(T o, Class<T> clazz)throws NoSuchFieldException,IllegalAccessException{

        Field field1 = clazz.getDeclaredField("name");
        Field field2 = clazz.getDeclaredField("age");

        if (field1.isAnnotationPresent(Readable.class)) {
            field1.setAccessible(true);
            System.out.println(field1.get(o));
        }
        if (field2.isAnnotationPresent(Readable.class)){
            field2.setAccessible(true);
            System.out.println(field2.get(o));
        }

    }

}
