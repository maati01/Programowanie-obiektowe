package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        List<Direction> directions = convert(args);
        run(directions);
        System.out.println("Stop");
//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
        MapDirection vector = MapDirection.EAST;
        System.out.println(vector.next());
        System.out.println(vector.previous());

    }

    public static void run(List<Direction> vectors) {
        for (Direction vector : vectors)
            switch (vector) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACK -> System.out.println("Zwierzak idzie do tyłu");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
            }

    }

    public static List<Direction> convert(String[] strings) {
        ArrayList<Direction> arr = new ArrayList<>();
        for (String info : strings) {
            switch (info) {
                case "f" -> arr.add(Direction.FORWARD);
                case "b" -> arr.add(Direction.BACK);
                case "l" -> arr.add(Direction.LEFT);
                case "r" -> arr.add(Direction.RIGHT);
            }
        }
        return arr;

    }
}
