import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;


@Annotation(count = 11)
public class Main {

    private int someObject;

    public int getSomeObject() {
        return someObject;
    }

    static public void main(String ... args) throws NoSuchFieldException,IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Person person1 = new Person("Andriy", 20);
        Person person2 = new Person("Yaroslan", 24);


        //task2
        PersonHandler.objectReader(person1, Person.class);

        PersonHandler.objectReader(person2,Person.class);

        //task3
        System.out.println("annotation's field " + Main.class.getAnnotation(Annotation.class).count());

        //task4
        Method method1 = Main.class.getDeclaredMethod("method1", int.class );
        Method method2 = Main.class.getDeclaredMethod("method2",int.class, String.class);
        Method method3 = Main.class.getDeclaredMethod("method3",int.class, String.class, Double.class);

        method1.setAccessible(true);
        method2.setAccessible(true);
        method3.setAccessible(true);

        Main mainObject = new Main();


        method1.invoke(mainObject,3);
        method2.invoke(mainObject, 4," Andriy");
        String str = (String)method3.invoke(mainObject, 5, " Nazar ", 4.8); // I guess, it always return Object class
        System.out.println(str);

        //task5

        System.out.println(Main.class.getDeclaredField("someObject").getType());
        Field someField = Main.class.getDeclaredField("someObject");
        someField.set(mainObject, 3);
        System.out.println(mainObject.getSomeObject());

        //task6

        Method myMethod1 = Main.class.getDeclaredMethod("myMethod1", String[].class);
        myMethod1.setAccessible(true);
        String[] array ={"str1","str2"};
        myMethod1.invoke(mainObject, (Object)array);

        Method myMethod2 = Main.class.getDeclaredMethod("myMethod2", String.class, int[].class);
        myMethod2.setAccessible(true);
        int[] intArr = new int[]{1,2};
        myMethod2.invoke(mainObject, "string", intArr);

        //task7
        System.out.println("\n\ntask 7");
        showInfo(new Person("Nazar",23));

}
    private void method1(int a){
        System.out.printf("method1 ");
        System.out.println(a);
    }

    private void method2(int a, String b){
        System.out.printf("method2 ");
        System.out.println(a + b);
    }

    private String method3(int a, String b, Double c){
        System.out.printf("method3 ");
        System.out.println(a + b + c);
        return a + b + c;
    }

    private void myMethod1(String ... args){
        System.out.println("myMethod1");
        for (String str : args)
            System.out.println(str);
    }

    private void myMethod2(String a, int ... args){
        System.out.println("myMethod2");
        System.out.println(a);
        for (int i : args) System.out.print(i + " ");
        System.out.println();
    }


    static private void showInfo(Object o){
        Class<?> clazz = o.getClass();
        System.out.println("simple name: " + clazz.getSimpleName());
        System.out.println("full name: " + clazz.getName());

        Constructor[] constructors = clazz.getConstructors();
        Method[] methods = clazz.getMethods();
        Field[] fields = clazz.getFields();

        for (Field f  : fields) System.out.println(fields.toString());

        for (Constructor c : constructors) System.out.println(c.toString());

        for (Method m : methods)
            System.out.println(m.toString());

    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Annotation{
    int count() default 0;
}