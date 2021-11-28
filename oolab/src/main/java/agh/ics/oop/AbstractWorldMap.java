package agh.ics.oop;

import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected HashMap<Vector2d, AbstractWorldMapElement> elementsOnMap = new HashMap<>();
    protected Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!(objectAt(position) instanceof Animal));
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            this.elementsOnMap.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        if (this.elementsOnMap.containsKey(position)) {
            return this.elementsOnMap.get(position);
        }
        return null;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        AbstractWorldMapElement animal = this.elementsOnMap.get(oldPosition);
        this.elementsOnMap.remove(oldPosition);
        this.elementsOnMap.put(newPosition,animal);

    }
    @Override
    public String toString(){
        return mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }
}
