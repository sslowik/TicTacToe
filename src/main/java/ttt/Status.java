package ttt;

public enum Status {

    WIN(" wygrał/a, gratulacje!\r\nWpisz (q)uit aby zakończyć grę."),
    PAT("Remis!"),
    TURN(", teraz Twój ruch!\r\nPodaj współrzędne oddzielone przecinkiem:\r\n ");

    private String message;
    private Player player;

    Status(String message) {
        this.message = message;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getMessage() {
        return player.getName() + message;
    }
}
