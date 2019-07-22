package ttt;


@SuppressWarnings("serial")
public class BoardFieldNotEmptyException extends Exception {

    public BoardFieldNotEmptyException() {
        super("Pole jest już zajęte!");
    }
}
