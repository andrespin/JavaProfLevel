import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestClass {

    @BeforeSuite
    public void init() {
        System.out.println("init");
    }

    @Test(priority = 3)
    public void test1() {
        System.out.println("test1");
    }

    @Test(priority = 7)
    public void test2() {
        System.out.println("test2");
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("test3");
    }

    @Test(priority = 4)
    public void test4() {
        System.out.println("test4");
    }

    public void method() {
        System.out.println("method");
    }

    @AfterSuite
    public void stop() {
        System.out.println("stop");
    }

    public static void start() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = TestClass.class;
        Object testObj = c.getDeclaredConstructor().newInstance();

        ArrayList<Method> al = new ArrayList<>();
        Method beforeMethod = null;
        Method afterMethod = null;

        for (Method o : c.getDeclaredMethods()) {
            if (o.isAnnotationPresent(Test.class)) {
                al.add(o);
            }
            if (o.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null) beforeMethod = o;
                else throw new RuntimeException("Больше одного метода с аннотацией BeforeSuite");
            }
            if (o.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null) afterMethod = o;
                else throw new RuntimeException("Больше одного метода с аннотацией AfterSuite");
            }
        }
        if (beforeMethod != null) beforeMethod.invoke(testObj, null);
        for (Method o : al) o.invoke(testObj, null);
        if (afterMethod != null) afterMethod.invoke(testObj, null);
    }
}
