package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {

    @Test
    public void canMoveToTest(){
        GrassField map = new GrassField(10);
        map.place(new Animal(map,new Vector2d(1,1)));
        map.place(new Animal(map,new Vector2d(1,2)));
        assertTrue(map.canMoveTo(new Vector2d(0,0)));
        assertTrue(map.canMoveTo(new Vector2d(-100,-100)));
        assertTrue(map.canMoveTo(new Vector2d(100,100)));
        assertTrue(map.canMoveTo(new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE)));
        assertTrue(map.canMoveTo(new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE)));
        assertFalse(map.canMoveTo(new Vector2d(1,1)));
        assertFalse(map.canMoveTo(new Vector2d(1,2)));
    }

    @Test
    public void placeTest(){
        GrassField map = new GrassField(10);
        assertTrue(map.place(new Animal(map)));
        assertFalse(map.place(new Animal(map)));
        assertTrue(map.place(new Animal(map,new Vector2d(2,3))));
        assertTrue(map.place(new Animal(map,new Vector2d(5,5))));
        assertTrue(map.place(new Animal(map,new Vector2d(0,0))));
        assertFalse(map.place(new Animal(map,new Vector2d(2,3))));
        assertTrue(map.place(new Animal(map,new Vector2d(-1,3))));
        assertTrue(map.place(new Animal(map,new Vector2d(3,-1))));
        assertTrue(map.place(new Animal(map,new Vector2d(-1,-1))));
        assertTrue(map.place(new Animal(map,new Vector2d(Integer.MIN_VALUE,Integer.MAX_VALUE))));
    }

    @Test
    public void isOccupiedTest(){
        GrassField map = new GrassField(10);
        assertFalse(map.isOccupied(new Animal(map).getPosition()));
        map.place(new Animal(map));
        assertTrue(map.isOccupied(new Animal(map).getPosition()));
        map.place(new Animal(map,new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(0,0)));
        assertFalse(map.isOccupied(new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE)));
        assertFalse(map.isOccupied(new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE)));
    }

    @Test
    public void objectAtTest(){
        GrassField map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(map,new Vector2d(2,1));
        Animal animal3 = new Animal(map,new Vector2d(0,-1));
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        assertEquals(map.objectAt(new Vector2d(2,2)),animal1);
        assertEquals(map.objectAt(new Vector2d(2,1)),animal2);
        assertNull(map.objectAt(new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE)));
        assertNull(map.objectAt(new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE)));
    }
}
