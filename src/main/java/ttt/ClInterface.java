package ttt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class ClInterface {

    private BufferedWriter writer;
    private BufferedReader reader;

    public ClInterface() {
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void print(String text) throws IOException {
        writer.write(text);
        writer.flush();
    }

    private void print(char ch) throws IOException {
        print(String.valueOf(ch));
    }

    private void println(String text) throws IOException {
        print(text + "\r\n");
    }

    private void println() throws IOException {
        print("\r\n");
    }

    private String read() throws IOException {
        return reader.readLine();
    }

    private void initPlayers() throws IOException {
        int i = 1;
        String name;
        do {
            print("Podaj nazwę " + (i++) + "-go gracza:\r\n");
            name = read();
        } while (!Engine.getInstance().addPlayer(name));
    }

    private void drawBoard() throws IOException {
        int iterationCount = Engine.getInstance().getBoardSize() * 2 + 1;
        for (int y = 0; y < iterationCount; y++) {
            for (int x = 0; x < iterationCount; x++) {
                if (y == 0) {
                    if (x != 0 && x % 2 == 0) {
                        print(String.valueOf(x / 2));
                    } else {
                        print(" ");
                    }
                } else if (y > 0 && y % 2 == 0 && x == 0) {
                    print(String.valueOf(y / 2));
                } else {
                    if (y % 2 != 0) {
                        if (x % 2 == 0) {
                            print("-");
                        } else {
                            print("+");
                        }
                    } else {
                        if (x % 2 == 0) {
                            print(Engine.getInstance().getBoardValue(x / 2, y / 2).getSign());
                        } else {
                            print("|");
                        }
                    }
                }
            }
            println();
        }
    }

    public void start() {
        try {
            println("Kółko i Krzyżyk v0.88");
            println("Jeśli chcesz wyjść z gry wpisz \"(q)uit\"");
            initPlayers();
            String msg = "Zaczyna gracz: " + Engine.getInstance().getCurrentPlayer().getName() + ".\r\nPodaj współrzędne oddzielone przecinkiem: ";
            do {
                drawBoard();
                println(msg);
                String line = read();
                if (line.toLowerCase().contains("q")) {
                    break;
                }
                String[] coords = line.split(",");
                try {
                    if (coords.length != 2
                            || Stream.of(coords).anyMatch(e -> Integer.valueOf(e) > Engine.getInstance().getBoardSize())
                            || Stream.of(coords).anyMatch(e -> Integer.valueOf(e) <= 0)
                    ) {
                        println("Błędne współrzędne, spróbuj jeszcze raz: ");
                        continue;
                    }
                    msg = Engine.getInstance().turn(Integer.valueOf(coords[0]), Integer.valueOf(coords[1]));
                } catch (NumberFormatException
                        | BoardFieldNotEmptyException
                        | IndexOutOfBoundsException
                        | NullPointerException e) {
                    println("Błędny wybór: " + e.getMessage());
                }
            } while (true);
            println("Koniec gry! Do następnego razu!");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}