package animals.testings;

public class TestUtils {

    static <T> void check(String method, T expected, T result, String tested){
        if(!expected.equals(result)) {
            throw new AssertionError(method + " failed test: " + tested);
        } else {
            System.out.println(method + " passed test: " + tested);
        }
    }
}
