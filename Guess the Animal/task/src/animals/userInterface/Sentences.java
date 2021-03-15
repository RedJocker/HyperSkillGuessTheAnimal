package animals.userInterface;

import animals.model.Animal;
import animals.model.Distinction;
import animals.utils.Pair;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static animals.userInterface.WordService.Affirmation;


public class Sentences {

    public static final String queryAnimal = "Enter an animal:";
    public static final String queryFirstAnimal = "Enter the first animal:";
    public static final String querySecondAnimal = "Enter the second animal:";
    public static final String reQueryFactExamples = "The examples of a statement:\n" +
                                                        " - It can fly\n" +
                                                        " - It has horn\n" +
                                                        " - It is a mammal";
    public static final String youThinkOfAnAnimal = "You think of an animal, and I guess it.";
    public static final String pressEnterWhenReady = "Press enter when you're ready.";
    public static final String iWantToLearnAnimals = "I want to learn about animals.";
    public static final String whichAnimalFavorite = "Which animal do you like most?";
    public static final String learnedSoMuch = "Wonderful! I've learned so much about animals!";
    public static final String letsPlay = "Let's play a game!";
    public static final String iWon = "I won!";
    public static final String iGiveUp = "I give up. What animal do you have in mind?";
    public static final String wannaPlayAgain = "Would you like to play again?";
    public static final String playAgainEnthusiasm = "Ow yes!";

    private static final String confirmAnimalTemplate = "Is it %s?";
    private static final String answerTemplate = "You answered: %s";
    private static final String specifyFactQueryTemplate = "Specify a fact that distinguishes %s from %s.%n" +
            "The sentence should be of the format: 'It can/has/is ...'.";
    private static final String isItCorrectForQueryTemplate = "Is the statement correct for %s?";

    private static final String iHaveLearnedAffirmationTemplate = "I have learned the following facts about animals:%n" +
            "- %s%n" +
            "- %s";
    private static final String factLearnedTemplate = "The %s %s.";

    private static final String iCanDistinguishAffirmationTemplate = "I can distinguish these animals by asking the question:%n" +
            "- %s";

    private static final String yes = "Yes";
    private static final String no = "No";
    private static final List<String> reQuerySentences =
            List.of(
                    "I'm not sure I caught you: was it yes or no?",
                    "Funny, I still don't understand, is it yes or no?",
                    "Oh, it's too complicated for me: just tell me yes or no.",
                    "Could you please simply say yes or no?",
                    "Oh, no, don't try to confuse me: say yes or no."
            );
    private static final List<String> goodbyeMessages =
            List.of(
                    "Bye!",
                    "See you soon!",
                    "Have a nice day!",
                    "See you the next time",
                    "Talk to you later",
                    "Au revoir",
                    "Arrivederci",
                    "Falou ae, é nois",
                    "Adios",
                    "Te visurum",
                    "Vi ses"
            );
    private static final Random random = new Random();



    public static String getGreeting() {
        return Greetings.getGreeting();
    }

    public static String getConfirmAnimalQuery(String animal) {

        return String.format(confirmAnimalTemplate, animal);
    }

    public static String getConfirmationReQuery(){
        final int sentenceIndex = random.nextInt(reQuerySentences.size());
        return reQuerySentences.get(sentenceIndex);
    }

    public static String getGoodbye() {
        final int sentenceIndex = random.nextInt(goodbyeMessages.size());
        return goodbyeMessages.get(sentenceIndex);
    }

    public static String getAnswer(WordService.Affirmation affirmation) {
        if(affirmation == WordService.Affirmation.AFFIRMATIVE) {
            return String.format(answerTemplate, yes);
        } else if(affirmation == WordService.Affirmation.NEGATIVE) {
            return String.format(answerTemplate, no);
        } else {
            throw new IllegalArgumentException(
                    "affirmation on getAnswer could be only " +
                            "affirmative or negative, but was neither");
        }
    }

    public static String getSpecifyFactQueryTemplate(Animal firstAnimal, Animal secondAnimal) {
        return String.format(
                specifyFactQueryTemplate,
                firstAnimal.getNameWithPreposition(),
                secondAnimal.getNameWithPreposition()
        );
    }

    public static String getIsItCorrectForQueryTemplate(Animal secondAnimal) {
        return String.format(
                isItCorrectForQueryTemplate,
                secondAnimal.getNameWithPreposition()
        );
    }

    public static String getConclusions(Distinction distinction, Affirmation isPositiveForSecondAnimal) {
        final String affirmation = distinction.fact.getPositiveAffirmation();
        final String negation = distinction.fact.getNegativeAffirmation();
        final Animal positiveAnimal = (Animal) distinction.positiveForFact;
        final Animal negativeAnimal = (Animal) distinction.negativeForFact;

        final String positiveAnimalStr = String.format(
                factLearnedTemplate, positiveAnimal.name, affirmation
        );
        final String negativeAnimalStr = String.format(
                factLearnedTemplate, negativeAnimal.name, negation
        );

        final String firstFactLearned =
                isPositiveForSecondAnimal == Affirmation.AFFIRMATIVE ? negativeAnimalStr :positiveAnimalStr;
        final String secondFactLearned =
                isPositiveForSecondAnimal == Affirmation.AFFIRMATIVE ? positiveAnimalStr :negativeAnimalStr;

        final String factsLearned =
                String.format(iHaveLearnedAffirmationTemplate, firstFactLearned, secondFactLearned);
        final String canDistinguishBy =
                String.format(iCanDistinguishAffirmationTemplate, distinction.fact.getQuestion());

        return String.format("%s%n%s", factsLearned, canDistinguishBy);
    }

    private static class Greetings {
        static final String morning = "Good morning!";
        static final String afternoon = "Good afternoon!";
        static final String evening = "Good evening!";
        static final String lateHours = "Hi, night owl!";


        static final List<Pair<Predicate<Integer>, String>> testsList =
                List.of(
                        new Pair<>(Greetings::isMorning, morning),
                        new Pair<>(Greetings::isAfternoon, afternoon),
                        new Pair<>(Greetings::isEvening, evening),
                        new Pair<>(Greetings::isLateHours, lateHours)
                );

        static String getGreeting(){
            final LocalTime time = LocalTime.now();
            final int currentHour = time.getHour();
            return testsList.stream()
                    .filter(pair -> pair.head.test(currentHour))
                    .findFirst()
                    .orElse(new Pair<>(x -> true, "You are out of the time dimension! NICE!!!"))
                    .tail;
        }

        static private boolean isMorning(int currentHour) {
            return currentHour >= 5 && currentHour <= 12;
        }

        static private boolean isAfternoon(int currentHour) {
            return currentHour > 12 && currentHour <= 18;
        }

        static private boolean isEvening(int currentHour) {
            return currentHour > 18 ;
        }

        static private boolean isLateHours(int currentHour) {
            return currentHour < 5;
        }
    }

}
