package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private final List<MoveDirection> moves;
    private final IWorldMap map;
    private final List<Animal> animals;

    public List<Animal> getAnimals() {
        return animals;
    }


    public SimulationEngine(List<MoveDirection> moves,IWorldMap map, List<Vector2d> positions){
        this.moves = moves;
        this.map = map;
        this.animals = new ArrayList<>();

        for(Vector2d vector2d: positions){
            Animal animal = new Animal(this.map,vector2d);
            if(map.place(animal)){
                this.animals.add(animal);
            }
        }
    }
    @Override
    public void run() {
        System.out.println(this.map);
        for(int i = 0; i < this.moves.size(); i++)    {
            this.animals.get(i%this.animals.size()).move(this.moves.get(i));
            System.out.println(this.map);
        }

    }
}
