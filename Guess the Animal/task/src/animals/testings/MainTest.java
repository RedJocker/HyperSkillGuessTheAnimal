package animals.testings;

import animals.Main;

import java.util.Arrays;

import static animals.testings.TestUtils.*;

public class MainTest {


    public static void testParseArgs() {
        final String method = new Object(){}.getClass().getEnclosingMethod().getName();

        final String[] givenBlank = {};
        final String defaultExpected = "json";
        final String blankResult = Main.parseArgs(givenBlank);
        check(method, defaultExpected, blankResult, Arrays.toString(givenBlank));

        final String[] correctYaml = {"-type", "yaml"};
        final String yamlExpected = "yaml";
        final String yamlResult = Main.parseArgs(correctYaml);
        check(method, yamlExpected, yamlResult, Arrays.toString(correctYaml));

        final String[] incorrectYaml = {"yaml"};
        final String incorrectYamlResult = Main.parseArgs(incorrectYaml);
        check(method, defaultExpected, incorrectYamlResult, Arrays.toString(incorrectYaml));

        final String[] correctXml = {"a", "b", "-type", "xml"};
        final String xmlExpected = "xml";
        final String xmlResult = Main.parseArgs(correctXml);
        check(method, xmlExpected, xmlResult, Arrays.toString(correctXml));





    }


}
