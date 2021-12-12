package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.List;

public class World {
    public static void main(String[] args) {
        Application.launch(App.class, args);



//        Animal animal = new Animal();
//        //System.out.println("Start");
//        ArrayList<MoveDirection> directions = OptionsParser.parse(args);
//        run(directions, animal);
//        //System.out.println("Stop");
//
//        System.out.println(animal);

    }

    public static void run(List<MoveDirection> vectors, Animal animal) {
        for (MoveDirection vector : vectors){
            animal.move(vector);
            switch (vector) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
            }

    }

//    public static List<Direction> convert(String[] strings) {
//        ArrayList<Direction> arr = new ArrayList<>();
//        for (String info : strings) {
//            switch (info) {
//                case "f" -> arr.add(Direction.FORWARD);
//                case "b" -> arr.add(Direction.BACK);
//                case "l" -> arr.add(Direction.LEFT);
//                case "r" -> arr.add(Direction.RIGHT);
//            }
//        }
//        return arr;

  }
}
