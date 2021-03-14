package animals.userInterface;

import animals.model.Animal;
import animals.model.Distinction;
import animals.model.Fact;

import static animals.userInterface.WordService.Affirmation;


import java.util.Scanner;

public class UI {
    final Scanner scanner = new Scanner(System.in);

    public void start(){
        getFactRoutine();
    }

    public void getFactRoutine() {
        println(Sentences.getGreeting());
        println("");

        println(Sentences.queryFirstAnimal);
        final String firstAnimalStr = getInput();
        final Animal firstAnimal = WordService.extractAnimal(firstAnimalStr);

        println(Sentences.querySecondAnimal);
        final String secondAnimalStr = getInput();
        final Animal secondAnimal = WordService.extractAnimal(secondAnimalStr);


        final Fact fact = getValidFact(firstAnimal, secondAnimal);

        println(Sentences.getIsItCorrectForQueryTemplate(secondAnimal));
        final Affirmation isPositiveForSecondAnimal = getValidConfirmation();
        final Distinction distinction = getDistinction(isPositiveForSecondAnimal, firstAnimal, secondAnimal, fact);

        println(Sentences.getConclusions(distinction, isPositiveForSecondAnimal));
        println("");

        println(Sentences.getGoodbye());

    }

    private Fact getValidFact(Animal firstAnimal, Animal secondAnimal) {
        println(Sentences.getSpecifyFactQueryTemplate(firstAnimal, secondAnimal));
        final String factStr = getInput();
        final boolean isFact = WordService.checkHasValidVerb(factStr);

        if(isFact) {
            return WordService.extractFact(factStr);
        } else {
            println(Sentences.reQueryFactExamples);
            return getValidFact(firstAnimal, secondAnimal);
        }
    }

    private Affirmation getValidConfirmation(){

        final String inputAnswer = getInput();
        final Affirmation affirmation = WordService.checkAffirmation(inputAnswer);

        if(affirmation != Affirmation.NEITHER) {
            return affirmation;
        } else {
            final String confirmationReQuery = Sentences.getConfirmationReQuery();
            println(confirmationReQuery);
            return getValidConfirmation();
        }

    }

    private Distinction getDistinction(Affirmation affirmation, Animal firstAnimal, Animal secondAnimal, Fact fact){
        final boolean isSecondAnimalPositiveForFact = affirmation == Affirmation.AFFIRMATIVE;

        final Animal positiveForFact = isSecondAnimalPositiveForFact ? secondAnimal : firstAnimal;
        final Animal negativeForFact = isSecondAnimalPositiveForFact ? firstAnimal : secondAnimal;

        return new Distinction(fact, positiveForFact, negativeForFact);
    }


    /**
     * @deprecated migrated to getFactRoutine() on task2
     */
    @Deprecated
    private void getAnimalRoutine() {
        println(Sentences.getGreeting());

        println(Sentences.queryAnimal);
        final String inputAnimal = getInput();
        final Animal animal = WordService.extractAnimal(inputAnimal);

        final String confirmationMessage = Sentences.getConfirmAnimalQuery(animal.getNameWithPreposition());

        println(confirmationMessage);
        final WordService.Affirmation affirmation = getValidConfirmation();

        final String answer = Sentences.getAnswer(affirmation);
        println(answer);

        final String goodbyeMessage = Sentences.getGoodbye();
        println(goodbyeMessage);
        System.exit(0);
    }



    //////////////////////////////....UI private utils......///////////////////////////////////////////////

    private void println(Object any) {
        System.out.println(any.toString());
    }

    private String getInput() {
        return scanner.nextLine();
    }
}
