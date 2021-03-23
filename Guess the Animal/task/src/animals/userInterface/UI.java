package animals.userInterface;

import animals.model.Animal;
import animals.model.Distinction;
import animals.model.Fact;
import animals.model.Node;
import animals.service.MapperHelper;

import static animals.userInterface.WordService.Affirmation;
import static animals.userInterface.WordService.extractAnimal;

import java.util.Scanner;

public class UI {
    final Scanner scanner = new Scanner(System.in);
    final MapperHelper mapper;

    public UI(String type) {
        this.mapper = new MapperHelper(type);
    }

    public void start(){
        /* mutating */ Node root = initRoutine();


        while(true) {

            println(Sentences.youThinkOfAnAnimal);
            println(Sentences.pressEnterWhenReady);
            getInput();

            root = game(root);

            wannaPlayAgain(root);
        }



    }

    private Node initRoutine(){

        final Node root = mapper.loadKnowledge();

        if(root != null) {
            return root;
        } else {
            return getFavoriteAnimal();
        }


    }

    private Animal getFavoriteAnimal(){
        println(Sentences.getGreeting());
        println("");

        println(Sentences.iWantToLearnAnimals);
        println(Sentences.whichAnimalFavorite);

        final String inputAnimal = getInput();
        println(Sentences.learnedSoMuch);
        println(Sentences.letsPlay);
        return WordService.extractAnimal(inputAnimal);
    }

    private Node game(Node node){
        if(node.isLeaf()) {
            final Animal guessAnimal = (Animal) node;
            println(Sentences.getConfirmAnimalQuery(guessAnimal.getNameWithPreposition()));
            final Affirmation affirmation = getValidConfirmation();

            if(affirmation == Affirmation.AFFIRMATIVE) {
                println(Sentences.iWon);
                return node;
            } else {
                println(Sentences.iGiveUp);
                final String newAnimalStr = getInput();
                final Animal newAnimal = extractAnimal(newAnimalStr);
                final Fact fact = getValidFact(guessAnimal, newAnimal);

                println(Sentences.getIsItCorrectForQueryTemplate(newAnimal));
                final Affirmation isPositiveForSecondAnimal = getValidConfirmation();
                final Distinction distinction = getDistinction(isPositiveForSecondAnimal, guessAnimal, newAnimal, fact);
                println(Sentences.getConclusions(distinction, isPositiveForSecondAnimal));
                println(Sentences.learnedSoMuch);

                return distinction;
            }


        } else {
            final Distinction guessDistinction = (Distinction) node;
            println(guessDistinction.fact.getQuestion());
            final Affirmation affirmation = getValidConfirmation();

            if(affirmation == Affirmation.AFFIRMATIVE) {
                return new Distinction(guessDistinction.fact,
                                        game(guessDistinction.positiveForFact),
                                        guessDistinction.negativeForFact);
            } else {
                return new Distinction(guessDistinction.fact,
                        guessDistinction.positiveForFact,
                        game(guessDistinction.negativeForFact));
            }

        }

    }

    private void wannaPlayAgain(Node root){
        println("");
        println(Sentences.wannaPlayAgain);
        final Affirmation affirmation = getValidConfirmation();

        if(affirmation == Affirmation.NEGATIVE) {
            mapper.saveKnowledge(root);
            println("");
            println(Sentences.getGoodbye());
            System.exit(0);
        } else {
            println(Sentences.playAgainEnthusiasm);
            println("");
        }
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
     * @deprecated migrated to game() on task3
     */
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
