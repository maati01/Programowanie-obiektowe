package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsParserTest {

    public void testOptionsParser(MoveDirection[] expected, String[] test){
        ArrayList<MoveDirection> result = OptionsParser.parse(test);

        assertEquals(expected.length,result.size());

        for(int i = 0; i < expected.length; i++){
            assertEquals(expected[i], result.get(i));
        }
    }
    @Test
    public void testOptionsParser(){
        String[] test1 = {};
        String[] test2 = {"f"};
        String[] test3 = {"l","left","leeeft","f","forward"};
        String[] test4 = {"x","y","z"};
        String[] test5 = {"R","r","baCkward","backward"};

        MoveDirection[] expected1 = {};
        MoveDirection[] expected2 = {MoveDirection.FORWARD};
        MoveDirection[] expected3 = {MoveDirection.LEFT,MoveDirection.LEFT,MoveDirection.FORWARD,MoveDirection.FORWARD};
        MoveDirection[] expected4 = {};
        MoveDirection[] expected5 = {MoveDirection.RIGHT,MoveDirection.BACKWARD};

        testOptionsParser(expected1,test1);
        testOptionsParser(expected2,test2);
        testOptionsParser(expected3,test3);
        testOptionsParser(expected4,test4);
        testOptionsParser(expected5,test5);
    }
}
