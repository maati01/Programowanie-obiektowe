package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    public void testOptionsParser(){
        ArrayList<MoveDirection> test1 = OptionsParser.parse(new String[]{"l","left","f","forward","r","b"});
        ArrayList<MoveDirection> test2 = OptionsParser.parse(new String[]{});

        assertEquals(MoveDirection.LEFT, test1.get(0));
        assertEquals(MoveDirection.LEFT, test1.get(1));
        assertEquals(MoveDirection.FORWARD, test1.get(2));
        assertEquals(MoveDirection.FORWARD, test1.get(3));
        assertEquals(MoveDirection.RIGHT, test1.get(4));
        assertEquals(MoveDirection.BACKWARD, test1.get(5));
        assertTrue(test2.isEmpty());

        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(new String[]{"l","leeft"}));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(new String[]{"r", "R"}));
    }
}
