package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    //tymczasowo zakladam ze poczatek mapy jest zawsze w 0,0
    private final int width;
    private final int height;
    private final List<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height){
        if(width < 0 || height < 0){
            this.width = 5;
            this.height = 5;
        }
        else {
            this.width = width;
            this.height = height;
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(this.width,this.height))
                && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animal: animals){
            if(animal.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal: animals){
            if(animal.getPosition().equals(position)){
                return animal;
            }
        }
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String toString(){
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(this.width,this.height));

    }
}
