package ttt;

public class BoardCoordsExceededException extends RuntimeException {
    public BoardCoordsExceededException() {
        super("Wspołrzędne poza zakresem planszy!");
    }
}