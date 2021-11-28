package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement {
    private MapDirection vector = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private final AbstractWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.map = new RectangularMap(5, 5);
        this.addObserver(this.map);
    }

    public Animal(AbstractWorldMap map) {
        this.map = map;
        this.addObserver(this.map);
    }

    public Animal(AbstractWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.addObserver(this.map);
    }

    public MapDirection getOrientation() {
        return vector;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString(){
        return switch (this.vector){
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }

    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT -> this.vector = this.vector.next();
            case LEFT -> this.vector = this.vector.previous();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.vector.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    positionChanged(this.position, newPosition);
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.add(this.vector.toUnitVector().opposite());
                if (map.canMoveTo(newPosition)) {
                    positionChanged(this.position, newPosition);
                    this.position = newPosition;
                }
            }
        }
    }

    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : this.observers)
            observer.positionChanged(oldPosition, newPosition);
    }
}
