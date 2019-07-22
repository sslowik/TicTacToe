package ttt;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

import ttt.javafx.PlayersScene;

public class App extends Application {

    public static void main(String[] args) {
        System.out.println("O + O + O + O + O + O + O + O + O + O + O + O +");
        System.out.println("+                                             O");
        System.out.println("O            Kółko i krzyżyk v0.88            +");
        System.out.println("+                                             O");
        System.out.println("O + O + O + O + O + O + O + O + O + O + O + O +");
        System.out.println();
        System.out.println("Wybierz wersję gry: \r\n 1. Gra tekstowa w konsoli. \r\n 2. Gra okienkowa z JavaFX.");
        String line = chooseGameInterface();
        if (line.contains("1")) {
            new ClInterface().start();
        } else {
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(new PlayersScene(primaryStage));
        primaryStage.show();
    }

    private static String chooseGameInterface() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Twój wybór: ");
        String line = scan.nextLine();
        System.out.println(line);
        while (!line.equals("1") && !line.equals("2")) {
            System.out.println("Nie ma takiej opcji, spróbuj ponownie:");
            line = scan.nextLine();
        }
        return line;
    }
}