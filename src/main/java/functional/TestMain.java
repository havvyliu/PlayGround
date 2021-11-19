package functional;

public class TestMain {

    public static void main(String[] args) {
        FunctionBaba newFunction = a -> a + 1;
        int result = newFunction.addOne(1);
        System.out.println("Result is " + result);
    }
}
