package com.company;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
public class Players  {
    //health for both players
    public int healthFirst = 100;
    public int healthSecond = 100;
    //To avoid tie , Need this later. Still under test
    public boolean keepGettingNumbers = true;
    // Take PlayersName from TextField (Input) and save them here
    public String firstName ;
    public String secondName;
    //Input to take The names
    public TextField enterName  = new TextField();
    //Label to Show dice result to each player
    public Label diceResultFirstPlayer = new Label();
    public Label diceResultSecondPlayer = new Label();
    // Label to show who got the bigger number and who will start
    public Label diceResult = new Label();
    //button To get randoms 2 numbers between 1 and 12
    public Button getNumbers = new Button("Ok Let's see");
    //Button to start fight after dice result. move to fight board
    public Button gotToFightBoard = new Button("Start Fight");
    // save all three labels in vertical box. diceResultFirstPlayer, diceResultSecondPlayer, diceResult.
    public VBox vBoxForDiceResult = new VBox();
    // To take firstName and secondName and putting them inside those labels to show them in fight board beside health
    public Label firstPlayer = new Label();
    public Label secondPlayer = new Label();
    // just a note header
    public Label writeNote = new Label();
    // button to enter and continue
    public Button enterAndContinue = new Button();
    // the main board that will take other BorderPane
    public BorderPane enterFirstPlayerName = new BorderPane();
    // to take Input and button and save them inside vertical box
    public VBox toTextFieldAndButton = new VBox(1);

    HBox forFirstAndSecond = new HBox(5);
    //For image in the bottom of BorderPane
    HBox hBoxForImage = new HBox(5);

    BorderPane mainPane = new BorderPane();
    BorderPane firstPlayerPane = new BorderPane();
    BorderPane secondPlayerPane = new BorderPane();



    public Players(){

    }

    //get randoms number between 1 and 12
    public int getRandomsNumber(){
        Random random = new Random();
        int number = random.nextInt(12) + 1;
        return number;
    }

    //First scene
    public BorderPane firstPlayerBorderPane(){
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("https://icon-library.com/images/battleship-icon/battleship-icon-12.jpg"));
        enterFirstPlayerName.setStyle("-fx-background-color: #53BFC9");
        enterFirstPlayerName.setPadding(new Insets(50, 0, 0, 0));
        writeNote = new Label("Enter First Player Name");
        writeNote.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        forFirstAndSecond.getChildren().add(writeNote);
        forFirstAndSecond.setAlignment(Pos.TOP_CENTER);
        enterName.setMaxWidth(250);
        enterName.setPadding(new Insets(10, 5, 10, 5));
        VBox.setMargin(enterName, new Insets(50, 0, 20, 0));
        enterName.setPromptText("Enter The Name Here");
        enterAndContinue = new Button("Continue >");
        enterAndContinue.setPadding(new Insets(10, 30, 10, 30));
        hBoxForImage.getChildren().add(imageView);
        hBoxForImage.setAlignment(Pos.CENTER);
        toTextFieldAndButton.getChildren().addAll(enterName, enterAndContinue);
        toTextFieldAndButton.setAlignment(Pos.TOP_CENTER);
        enterFirstPlayerName.setTop(forFirstAndSecond);
        enterFirstPlayerName.setCenter(toTextFieldAndButton);
        enterFirstPlayerName.setBottom(hBoxForImage);
        //Scene enterFirstPlayerScene = new Scene(enterFirstPlayerName, 1200, 750, Color.BLACK);
        enterAndContinue.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                //Moving to another function to edit on our BorderPane
                secondPlayer();
            }
        });

        return enterFirstPlayerName;
    }

    public void secondPlayer(){
        //Save the name into firstName
        firstName = enterName.getText();
        //testing if we received it
        System.out.println(firstName + " ...........");
        //Change some info
        enterName.clear();
        writeNote.setText("Enter Second Player Name");
        writeNote.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        //button action Click
        enterAndContinue.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                //Move to explain BorderPane
                explainWhoStart();
            }
        });
    }

    // Explain
    public void explainWhoStart(){
        //Save the second player name into our string variable
        secondName = enterName.getText();
        //testing if we received it
        System.out.println(secondName + " ...........");
        //Now we remove input field and our continue button
        enterFirstPlayerName.getChildren().remove(toTextFieldAndButton);
        //Replace the Header with new text and attribute
        writeNote.setText("The player with the highest number of two dice. will start .. ");
        writeNote.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        getNumbers.setPadding(new Insets(10, 30, 10, 30));
        enterFirstPlayerName.setCenter(getNumbers);
        getNumbers.setOnAction(new EventHandler() {
            public void handle(Event event) {
                //Movie to result BorderPane
                showDiceResult();
            }
        });
    }

    public void showDiceResult() {
        // To avoid tie result , Looping again if got same numbers
        //  Some whileLopp
            // getting numbers for players from our function
            int number1 = getRandomsNumber();
            int number2 = getRandomsNumber();
            // remove some Nodes
            enterFirstPlayerName.getChildren().remove(forFirstAndSecond);
            enterFirstPlayerName.getChildren().remove(writeNote);
            // setting som new attributes
            diceResultFirstPlayer.setText("The First Player " + firstName.toUpperCase() + " : " + " Got " + number1);
            diceResultFirstPlayer.setFont(Font.font("Arial", FontWeight.BOLD, 32));
            diceResultSecondPlayer.setText("The Second Player " + secondName.toUpperCase() + " : " + " Got " + number2);
            diceResultSecondPlayer.setFont(Font.font("Arial", FontWeight.BOLD, 32));
            gotToFightBoard.setPadding(new Insets(10, 30, 10, 30));
            diceResult.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            vBoxForDiceResult.setMargin(diceResultFirstPlayer, new Insets(10, 0, 15, 0));
            vBoxForDiceResult.setMargin(diceResultSecondPlayer, new Insets(30, 0, 15, 0));
            vBoxForDiceResult.setMargin(diceResult, new Insets(30, 0, 20, 0));
            vBoxForDiceResult.getChildren().addAll(diceResultFirstPlayer, diceResultSecondPlayer, diceResult, gotToFightBoard);
            vBoxForDiceResult.setAlignment(Pos.BASELINE_CENTER);
            enterFirstPlayerName.setCenter(vBoxForDiceResult);
            // select the player from our function
            chooseWhoStartsFirst(number1,number2);
            gotToFightBoard.setOnAction(new EventHandler() {
            public void handle(Event event) {
                //Go to fightBoard
                int number = chooseWhoStartsFirst(number1,number2);
                shootEachOther(number);
            }
        });
    }

    public int chooseWhoStartsFirst(int number1, int number2){
        if (number1 > number2) {
            diceResult.setText(firstName.toUpperCase() + " Will Start The Fight!");
            return 1;

        } else if (number1 < number2) {
            diceResult.setText(secondName.toUpperCase() + " Will Start The Fight!");
            return 2;

        } else if (number1 == number2){
            //Go again and get a new numbers
            showDiceResult();
        }
        return 0;
    }

    public void shootEachOther(int number)  {
        switch (number) {
            case 1: shootFromFirstToSecond();
            break;
            case 2: shootFromSecondToFirst();
            break;
            default:
                System.out.println("Tie");
                showDiceResult();
        }
    }

    public void shootFromFirstToSecond() {
        firstPlayer.setStyle("-fx-text-fill: blue;");
        firstPlayerPane.setDisable(true);
        firstPlayerPane.setCursor(Cursor.CLOSED_HAND);
        secondPlayer.setStyle("-fx-text-fill: black;");
        secondPlayerPane.setDisable(false);
        secondPlayerPane.setCursor(Cursor.HAND);
        fightBoard();

    }

    public void shootFromSecondToFirst()  {
        secondPlayer.setStyle("-fx-text-fill: blue;");
        secondPlayerPane.setDisable(true);
        secondPlayerPane.setCursor(Cursor.CLOSED_HAND);
        firstPlayer.setStyle("-fx-text-fill: black;");
        firstPlayerPane.setDisable(false);
        firstPlayerPane.setCursor(Cursor.HAND);
        fightBoard();
    }

    public void fightBoard() {
            HBox hBoxFirstPlayer = new HBox();
            HBox hBoxSecondPlayer = new HBox();
            HBox firstPlayerPositionsNumbers = new HBox();
            HBox secondPlayerPositionsNumbers = new HBox();
            HBox hBoxToTakeNamesAndHealths = new HBox();
            VBox firstPlayerPositionsAlphabet = new VBox();
            VBox secondPlayerPositionsAlphabet = new VBox();
            enterFirstPlayerName.getChildren().removeAll(vBoxForDiceResult, hBoxForImage);
            firstPlayer.setText(firstName + "s" + " Health is " + ":     " + healthFirst + " %");
            firstPlayer.setFont(Font.font("Arial", FontWeight.BOLD, 25));
            secondPlayer.setText(secondName + "s " + "Health is " + ":     " + healthSecond + "%");
            secondPlayer.setFont(Font.font("Arial", FontWeight.BOLD, 25));
            for (int i = 1; i < 11; i++) {
                Label firstPlayerNumbers = new Label();
                firstPlayerNumbers.setText(String.valueOf(i));
                firstPlayerPositionsNumbers.setMargin(firstPlayerNumbers, new Insets(5, 0, 5, 40));
                firstPlayerPositionsNumbers.getChildren().add(firstPlayerNumbers);
                firstPlayerNumbers.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            }
            for (int i = 1; i < 11; i++) {
                Label secondPlayerNumbers = new Label();
                secondPlayerNumbers.setText(String.valueOf(i));
                secondPlayerPositionsNumbers.setMargin(secondPlayerNumbers, new Insets(5, 0, 5, 40));
                secondPlayerPositionsNumbers.getChildren().add((secondPlayerNumbers));
                secondPlayerNumbers.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            }

            for (char i = 'A'; i < 'K'; i++) {
                Label firstPlayerAlphabet = new Label();
                firstPlayerAlphabet.setText(String.valueOf(i));
                firstPlayerPositionsAlphabet.getChildren().add((firstPlayerAlphabet));
                firstPlayerPositionsAlphabet.setMargin(firstPlayerAlphabet, new Insets(10, 10, 20, 0));
                firstPlayerAlphabet.setFont(Font.font("Arial", FontWeight.BOLD, 15));

            }
            for (char i = 'A'; i < 'K'; i++) {
                Label secondPlayerAlphabet = new Label();
                secondPlayerAlphabet.setText(String.valueOf(i));
                secondPlayerPositionsAlphabet.setMargin(secondPlayerAlphabet, new Insets(10, 10, 20, 0));
                secondPlayerPositionsAlphabet.getChildren().add((secondPlayerAlphabet));
                secondPlayerAlphabet.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            }
            hBoxFirstPlayer.getChildren().add(firstPlayer);
            hBoxSecondPlayer.getChildren().addAll(secondPlayer);
            hBoxToTakeNamesAndHealths.getChildren().addAll(hBoxFirstPlayer, hBoxSecondPlayer);
            hBoxFirstPlayer.setAlignment(Pos.TOP_LEFT);
            hBoxSecondPlayer.setAlignment(Pos.TOP_RIGHT);
            hBoxToTakeNamesAndHealths.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
            hBoxToTakeNamesAndHealths.setMargin(hBoxFirstPlayer, new Insets(50, 350, 50, 100));
            hBoxToTakeNamesAndHealths.setMargin(hBoxSecondPlayer, new Insets(50, 60, 50, 0));
            mainPane.setStyle("-fx-background-color: #53BFC9");
            firstPlayerPane.setTop(firstPlayerPositionsNumbers);
            firstPlayerPane.setLeft(firstPlayerPositionsAlphabet);
            firstPlayerPane.setMargin(firstPlayerPositionsNumbers, new Insets(50, 0, 0, 20));
            firstPlayerPane.setMargin(firstPlayerPositionsAlphabet, new Insets(10, 0, 0, 20));
            secondPlayerPane.setTop(secondPlayerPositionsNumbers);
            secondPlayerPane.setLeft(secondPlayerPositionsAlphabet);
            secondPlayerPane.setMargin(secondPlayerPositionsNumbers, new Insets(50, 0, 0, 40));
            secondPlayerPane.setMargin(secondPlayerPositionsAlphabet, new Insets(10, 0, 0, 40));
            secondPlayerPane.setPadding(new Insets(0, 40, 0, 0));
            mainPane.setTop(hBoxToTakeNamesAndHealths);
            mainPane.setLeft(firstPlayerPane);
            mainPane.setRight(secondPlayerPane);
            enterFirstPlayerName.setPadding(new Insets(0, 0, 0, 0));
            enterFirstPlayerName.setTop(mainPane);

            /*
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask(){

            @Override
            public void run() {
                Platform.runLater(() -> shootFromSecondToFirst());
            }
        }, 5000);

        Timer myTimer2 = new Timer();
        myTimer2.schedule(new TimerTask(){

            @Override
            public void run() {
                Platform.runLater(() -> shootFromFirstToSecond());
            }
        }, 5000);

             */





    }
}



