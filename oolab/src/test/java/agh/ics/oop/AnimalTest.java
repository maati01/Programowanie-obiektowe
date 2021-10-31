package agh.ics.oop;
import org.junit.jupiter.api.Test;

import static agh.ics.oop.World.run;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AnimalTest {

    @Test
    public void testToString(){
        Animal animal = new Animal();
        assertEquals("Direction: Północ, Position: (2,2)",animal.toString());
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals("Direction: Południe, Position: (2,0)",animal.toString());
    }

    @Test
    public void moveTest(){
        Animal animal = new Animal();
        run(OptionsParser.parse(new String[]{"f", "forward"}),animal);
        assertEquals(new Vector2d(2,4), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());

        run(OptionsParser.parse(new String[]{"f"}),animal);
        assertEquals(new Vector2d(2,4), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());

        run(OptionsParser.parse(new String[]{"r", "f", "f", "f"}),animal);
        assertNotEquals(new Vector2d(5,4), animal.getPosition());
        assertEquals(new Vector2d(4,4), animal.getPosition());
        assertEquals(MapDirection.EAST, animal.getOrientation());

        run(OptionsParser.parse(new String[]{"r", "right", "x", "y", "z", "wrong_arg", "r", "f"}),animal);
        assertEquals(new Vector2d(4,4), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());

        run(OptionsParser.parse(new String[]{"b", "back", "backward", "l"}),animal);
        assertEquals(new Vector2d(4,2), animal.getPosition());
        assertEquals(MapDirection.WEST, animal.getOrientation());

        run(OptionsParser.parse(new String[]{"left", "f", "f", "f", "f", "f", "f"}),animal);
        assertEquals(new Vector2d(4,0), animal.getPosition());
        assertEquals(MapDirection.SOUTH, animal.getOrientation());
    }
}
