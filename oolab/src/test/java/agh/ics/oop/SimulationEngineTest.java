package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationEngineTest {

    @Test
    public void runTest(){
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f","b","r","l"});
        IWorldMap map = new RectangularMap(10, 5);
        List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();


        assertEquals(new Vector2d(2,3), engine.getAnimals().get(0).getPosition());
        assertNotEquals(new Vector2d(2,2), engine.getAnimals().get(0).getPosition());
        assertEquals(new Vector2d(3,3), engine.getAnimals().get(1).getPosition());
        assertNotEquals(new Vector2d(3,5), engine.getAnimals().get(1).getPosition());
    }

    @Test
    public void runTest1() {
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r",
                "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);
        List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertEquals(new Vector2d(2,0), engine.getAnimals().get(0).getPosition());
        assertNotEquals(new Vector2d(2,2), engine.getAnimals().get(0).getPosition());
        assertNotEquals(new Vector2d(2,-1), engine.getAnimals().get(0).getPosition());
        assertEquals(new Vector2d(3,5), engine.getAnimals().get(1).getPosition());
        assertNotEquals(new Vector2d(3,7), engine.getAnimals().get(1).getPosition());
        assertNotEquals(new Vector2d(3,4), engine.getAnimals().get(1).getPosition());
    }
}
