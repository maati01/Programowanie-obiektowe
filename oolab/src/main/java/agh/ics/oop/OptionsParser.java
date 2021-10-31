package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {

    public static ArrayList<MoveDirection> parse(String[] args){
        ArrayList<MoveDirection> directionsList = new ArrayList<>();
        for(String string: args){
            switch (string){
                case "f", "forward" -> directionsList.add(MoveDirection.FORWARD);
                case "b", "backward" -> directionsList.add(MoveDirection.BACKWARD);
                case "r", "right" -> directionsList.add(MoveDirection.RIGHT);
                case "l", "left" -> directionsList.add(MoveDirection.LEFT);
                default -> {}
            }
        }
        return directionsList;
    }
}
