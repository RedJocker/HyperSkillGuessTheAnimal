package animals;

import animals.testings.WordServiceTests;
import animals.userInterface.UI;

public class Main {

    public static void main(String[] args) {
        //tests();
        new  UI().start();
    }

    public static void tests(){
        WordServiceTests.testCheckFact();
        WordServiceTests.testExtractFact();
    }
}
