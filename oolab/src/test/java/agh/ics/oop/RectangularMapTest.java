package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {


    @Test
    public void canMoveToTest(){
        RectangularMap map = new RectangularMap(5,5);
        assertTrue(map.canMoveTo(new Vector2d(0,0)));
        assertTrue(map.canMoveTo(new Vector2d(0,5)));
        assertTrue(map.canMoveTo(new Vector2d(5,0)));
        assertTrue(map.canMoveTo(new Vector2d(5,5)));
        assertTrue(map.canMoveTo(new Vector2d(5,5)));
        assertFalse(map.canMoveTo(new Vector2d(0,-1)));
        assertFalse(map.canMoveTo(new Vector2d(-1,0)));
        assertFalse(map.canMoveTo(new Vector2d(5,6)));
        assertFalse(map.canMoveTo(new Vector2d(6,5)));
        assertFalse(map.canMoveTo(new Vector2d(6,6)));
    }

    @Test
    public void placeTest(){
        RectangularMap map = new RectangularMap(5,5);
        assertTrue(map.place(new Animal(map)));
        assertThrows(IllegalArgumentException.class,() -> map.place(new Animal(map)));
        assertTrue(map.place(new Animal(map,new Vector2d(2,3))));
        assertTrue(map.place(new Animal(map,new Vector2d(5,5))));
        assertTrue(map.place(new Animal(map,new Vector2d(0,0))));
        assertThrows(IllegalArgumentException.class,() -> map.place(new Animal(map,new Vector2d(2,3))));
        assertThrows(IllegalArgumentException.class,() -> map.place(new Animal(map,new Vector2d(-1,3))));
        assertThrows(IllegalArgumentException.class,() -> map.place(new Animal(map,new Vector2d(3,-1))));
        assertThrows(IllegalArgumentException.class,() -> map.place(new Animal(map,new Vector2d(-1,-1))));
    }

    @Test
    public void isOccupiedTest(){
        RectangularMap map = new RectangularMap(5,5);
        assertFalse(map.isOccupied(new Animal(map).getPosition()));
        map.place(new Animal(map));
        assertTrue(map.isOccupied(new Animal(map).getPosition()));
        assertFalse(map.isOccupied(new Vector2d(2,3)));
        assertFalse(map.isOccupied(new Vector2d(0,0)));
        assertFalse(map.isOccupied(new Vector2d(-1,-1)));
        map.place(new Animal(map,new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(0,0)));
    }

    @Test
    public void objectAtTest(){
        RectangularMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(map,new Vector2d(2,1));
        Animal animal3 = new Animal(map,new Vector2d(0,-1));
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        assertNull(map.objectAt(new Vector2d(2,3)));
        assertEquals(map.objectAt(new Vector2d(2,2)),animal1);
        assertEquals(map.objectAt(new Vector2d(2,1)),animal2);
        assertNull(map.objectAt(new Vector2d(0,-1)));
    }

}
