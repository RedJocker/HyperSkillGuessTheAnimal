package animals;


import animals.testings.MainTest;
import animals.testings.WordServiceTests;
import animals.userInterface.UI;


import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
//        tests();
        String type = parseArgs(args);
        new UI(type).start();



    }


    public static String parseArgs(String... args){
        return Arrays.stream(args)
                .dropWhile(str -> !str.equals("-type"))
                .skip(1)
                .map(String::toLowerCase)
                .findFirst()
                .orElse("json");
    }

    public static void tests(){
        MainTest.testParseArgs();
        WordServiceTests.testCheckFact();
        WordServiceTests.testExtractFact();
    }
}
