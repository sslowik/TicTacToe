package ttt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ttt.BoardFieldNotEmptyException;
import ttt.Engine;

@DisplayName("Test silnika gry")
public class EngineTest {

    private static Engine engine;

    @BeforeAll
    public static void init() {
        System.out.println("before all");
        engine = Engine.getInstance();
    }

    @AfterAll
    public static void clear() {
        System.out.println("after all");
    }

    @BeforeEach
    public void initEach() {
        System.out.println("before each");
        int i = 1;
        while(!engine.addPlayer("ala" + i++));
    }

    @AfterEach
    public void clearEach() {
        System.out.println("after each");
        EngineTest.engine = Engine.reset();
    }

    @Test
    @DisplayName("Test zaznaczenia pola")
    public void fieldCheckTest() {
        try {
            char sign = engine.getCurrentPlayer().getSign();
            engine.turn(1, 1);
            assertEquals(sign, engine.getBoardValue(1, 1).getSign());
        } catch(BoardFieldNotEmptyException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test zmiany gracza")
    public void playerChange() {
        try {
            char sign = engine.getCurrentPlayer().getSign();
            engine.turn(1, 1);
            assertNotEquals(sign, engine.getCurrentPlayer().getSign());
        } catch(BoardFieldNotEmptyException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test wyłączony")
    @Disabled
    public void disabledTest() {
        System.out.println("wyłączony test");
    }

}
