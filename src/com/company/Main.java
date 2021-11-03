package com.company;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
	// write your code here
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Battleship");
        Players players = new Players();
        Scene scene = new Scene(players.firstPlayerBorderPane(), 1200, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
        /*
        PaneOrganizer playerField = new PaneOrganizer();
        PaneOrganizer enemyField = new PaneOrganizer();
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().addAll(new Node[]{playerField.PaneOrganizer(), enemyField.PaneOrganizer()});
        borderPane.setLeft(playerField.PaneOrganizer());
        borderPane.setRight(enemyField.PaneOrganizer());
        Scene scene = new Scene(playerField.PaneOrganizer());
        primaryStage.setScene(scene);
        primaryStage.show();

         */
    }
}
