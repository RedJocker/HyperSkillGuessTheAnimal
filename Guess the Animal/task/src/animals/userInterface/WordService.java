package animals.userInterface;

import animals.model.Animal;
import animals.model.Fact;

import java.util.List;

public class WordService {

    private static final List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');
    private final static List<String> prepositions = List.of("a", "an");


    public static Animal extractAnimal(String animalMaybeWithPreposition) {
        final String normalizedAnimalMaybeWithPreposition = normalize(animalMaybeWithPreposition);

        final String[] animalArray =
                normalizedAnimalMaybeWithPreposition.split("\\s+", 2);

        if(animalArray.length < 2) {
            final String preposition = getPreposition(animalArray[0]);
            final String animal = normalizedAnimalMaybeWithPreposition;
            return new Animal(preposition, animal);
        } else if(isPreposition(animalArray[0])) {
            return new Animal(animalArray[0], animalArray[1]);
        } else {
            final String preposition = getPreposition(animalArray[0]);
            final String animal = normalizedAnimalMaybeWithPreposition;
            return new Animal(preposition, animal);
        }
    }

    public static Affirmation checkAffirmation(String someAffirmation) {
        return Affirmation.checkAffirmation(someAffirmation);
    }

    public static Fact extractFact(String someFact) {

        final Verb verb = Verb.extractVerb(someFact);

        final String factDescription = someFact.trim().split(" ", 3)[2]
                                        .replaceAll("[.?!]+$", "");

        return new Fact(verb, factDescription);
    }

    public static boolean checkHasValidVerb(String someFact) {

        return Verb.checkHasValidVerb(someFact);
    }

    private static String getPreposition(String str){
        return isVowel(str.charAt(0)) ? prepositions.get(1) : prepositions.get(0);
    }

    private static boolean isVowel(char ch) {
        return vowels.contains(ch);
    }

    private static boolean isPreposition(String maybePreposition) {
        return prepositions.contains(maybePreposition);
    }

    private static String normalize(String str) {
        return str.trim().toLowerCase();
    }

    enum Affirmation {
        AFFIRMATIVE, NEGATIVE, NEITHER;

        private final static List<String> affirmatives =
                List.of("y", "yes", "yeah", "yep", "sure", "right", "affirmative",
                        "correct", "indeed", "you bet", "exactly", "you said it");
        private final static List<String> negatives =
                List.of("n", "no", "no way", "nah", "nope",
                        "negative", "i don't think so", "yeah no");

        private static boolean isAffirmative(String maybeAffirmative) {
            return affirmatives.contains(normalize(maybeAffirmative));
        }

        private static boolean isNegative(String maybeNegative) {
            return negatives.contains(normalize(maybeNegative));
        }

        private static Affirmation checkAffirmation(String someAffirmation) {
            final String normalizedAffirmation  = normalize(someAffirmation).replaceFirst("[.!?]$", "");

            if(Affirmation.isAffirmative(normalizedAffirmation)) {
                return Affirmation.AFFIRMATIVE;
            } else if(Affirmation.isNegative(normalizedAffirmation)) {
                return Affirmation.NEGATIVE;
            } else {
                return Affirmation.NEITHER;
            }
        }
    }

    public enum Verb {
        CAN("can", "can't", "Can it"),
        HAS("has", "doesn't have", "Does it have"),
        IS("is", "isn't", "Is it");

        public final String positive;
        public final String negative;
        public final String question;

        Verb(String positive, String negative, String question) {
            this.positive = positive;
            this.negative = negative;
            this.question = question;
        }

        private static boolean isVerbToBe(String someFact) {
            return someFact.matches("(?i)^it is .*$");
        }

        private static boolean isVerbToHave(String someFact) {
            return someFact.matches("(?i)^it has .*$");
        }

        private static boolean isVerbToCan(String someFact) {
            return someFact.matches("(?i)^it can .*$");
        }

        private static boolean checkHasValidVerb(String someFact) {
            return someFact.matches("(?i)^it (can|has|is) .+$");
        }

        private static Verb extractVerb(String validFact) {

            if(isVerbToHave(validFact)) {
                return HAS;
            } else if(isVerbToCan(validFact)) {
                return CAN;
            } else if(isVerbToBe(validFact)){
                return IS;
            } else {
                throw new IllegalArgumentException("extractVerb received invalid fact");
            }
        }
    }
}
