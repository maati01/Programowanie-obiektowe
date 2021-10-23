package agh.ics.oop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d test_v = new Vector2d(1,1);

    @Test
    public void testEquals(){
        assertEquals(test_v, new Vector2d(1, 1));
        assertNotEquals(test_v, new Vector2d(2, 1));
    }

    @Test
    public void testToString(){
        assertEquals("(1,1)",test_v.toString());
        assertNotEquals("(2,1)",test_v.toString());
    }

    @Test
    public void testPrecedes(){
        assertTrue(test_v.precedes(new Vector2d(2,2)));
        assertTrue(test_v.precedes(new Vector2d(1,2)));
        assertTrue(test_v.precedes(new Vector2d(2,1)));
        assertTrue(test_v.precedes(new Vector2d(1,1)));
        assertFalse(test_v.precedes(new Vector2d(0,0)));
        assertFalse(test_v.precedes(new Vector2d(1,0)));
        assertFalse(test_v.precedes(new Vector2d(0,1)));
    }

    @Test
    public void testFollows(){
        assertTrue(test_v.follows(new Vector2d(0,0)));
        assertTrue(test_v.follows(new Vector2d(0,1)));
        assertTrue(test_v.follows(new Vector2d(1,0)));
        assertTrue(test_v.follows(new Vector2d(1,1)));
        assertFalse(test_v.follows(new Vector2d(2,2)));
        assertFalse(test_v.follows(new Vector2d(2,1)));
        assertFalse(test_v.follows(new Vector2d(1,2)));
    }

    @Test
    public void testUpperRight(){
        assertEquals(new Vector2d(2,2),test_v.upperRight(new Vector2d(2,2)));
        assertEquals(new Vector2d(1,1),test_v.upperRight(new Vector2d(0,0)));
        assertEquals(new Vector2d(1,1),test_v.upperRight(new Vector2d(1,1)));
        assertEquals(new Vector2d(2,1),test_v.upperRight(new Vector2d(2,1)));
        assertEquals(new Vector2d(1,1),test_v.upperRight(new Vector2d(-1,1)));
    }

    @Test
    public void testLowerLeft(){
        assertEquals(new Vector2d(1,1),test_v.lowerLeft(new Vector2d(2,2)));
        assertEquals(new Vector2d(0,0),test_v.lowerLeft(new Vector2d(0,0)));
        assertEquals(new Vector2d(1,1),test_v.lowerLeft(new Vector2d(1,1)));
        assertEquals(new Vector2d(1,1),test_v.lowerLeft(new Vector2d(2,1)));
        assertEquals(new Vector2d(-1,1),test_v.lowerLeft(new Vector2d(-1,1)));
    }

    @Test
    public void testAdd(){
        assertEquals(new Vector2d(3,3),test_v.add(new Vector2d(2,2)));
        assertEquals(new Vector2d(1,3),test_v.add(new Vector2d(0,2)));
        assertEquals(new Vector2d(1,1),test_v.add(new Vector2d(0,0)));
        assertEquals(new Vector2d(-3,1),test_v.add(new Vector2d(-4,0)));
    }

    @Test
    public void testSubtract(){
        assertEquals(new Vector2d(-1,-1),test_v.subtract(new Vector2d(2,2)));
        assertEquals(new Vector2d(1,-1),test_v.subtract(new Vector2d(0,2)));
        assertEquals(new Vector2d(1,1),test_v.subtract(new Vector2d(0,0)));
        assertEquals(new Vector2d(5,1),test_v.subtract(new Vector2d(-4,0)));
    }

    @Test
    public void testOpposite(){
        assertEquals(new Vector2d(-1,-1),test_v.opposite());
    }

}
