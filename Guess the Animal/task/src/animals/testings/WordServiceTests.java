package animals.testings;

import animals.model.Fact;
import animals.userInterface.WordService;

import static animals.userInterface.WordService.Verb;

public class WordServiceTests {

    public static void testCheckFact(){
        final String method = new Object(){}.getClass().getEnclosingMethod().getName();

        final String given = "It can climb trees.";
        final boolean expected = true;
        final boolean result = WordService.checkHasValidVerb(given);
        check(method, expected, result, given);

        final String given2 = "It has a horn";
        final boolean expected2 = true;
        final boolean result2 = WordService.checkHasValidVerb(given2);
        check(method, expected2, result2, given2);

        final String given3 = "It is a shy animal";
        final boolean expected3 = true;
        final boolean result3 = WordService.checkHasValidVerb(given3);
        check(method, expected3, result3, given3);

        final String given4 = "Is it a mammal?";
        final boolean expected4 = false;
        final boolean result4 = WordService.checkHasValidVerb(given4);
        check(method, expected4, result4, given4);

        final String given5 = "It is a mammal?";
        final boolean expected5 = true;
        final boolean result5 = WordService.checkHasValidVerb(given5);
        check(method, expected5, result5, given5);

    }

    public static void testExtractFact(){
        final String method = new Object(){}.getClass().getEnclosingMethod().getName();

        final String given = "It can climb trees.";
        final Fact expected = new Fact(Verb.CAN, "climb trees");
        final Fact result = WordService.extractFact(given);
        check(method, expected, result, given);


        final String given2 = "It has a horn";
        final Fact expected2 = new Fact(Verb.HAS, "a horn");
        final Fact result2 = WordService.extractFact(given2);
        check(method, expected2, result2, given2);

        final String given3 = "It is a shy animal";
        final Fact expected3 = new Fact(Verb.IS, "a shy animal");
        final Fact result3 = WordService.extractFact(given3);
        check(method, expected3, result3, given3);

        final String given4 = "It is a mammal?";
        final Fact expected4 = new Fact(Verb.IS, "a mammal");
        final Fact result4 = WordService.extractFact(given4);
        check(method, expected4, result4, given4);

    }

    private static <T> void check(String method, T expected, T result, String tested){
        if(!expected.equals(result)) {
            throw new AssertionError(method + " failed test: " + tested);
        } else {
            System.out.println(method + " passed test: " + tested);
        }
    }
}
