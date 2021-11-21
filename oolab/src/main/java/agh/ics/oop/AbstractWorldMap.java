package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap{
    protected ArrayList<AbstractWorldMapElement> elementsOnMap = new ArrayList<>();
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
            this.elementsOnMap.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(AbstractWorldMapElement element: this.elementsOnMap){
            if(element.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        for (AbstractWorldMapElement element : this.elementsOnMap) {
            if (element.getPosition().equals(position)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }
}
