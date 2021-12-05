package agh.ics.oop;

import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap{
    int numberGrasses;
    private final MapBoundary mapBoundary = new MapBoundary();

    public GrassField(int numberGrasses){
        this.numberGrasses = numberGrasses;
        generateGrass(this.numberGrasses);
    }

    public void generateGrass(int quantity){
        Random rand = new Random();
        int cnt = 0;
        while (cnt < quantity){
            int x = rand.nextInt((int) Math.sqrt(this.numberGrasses*10));
            int y = rand.nextInt((int) Math.sqrt(this.numberGrasses*10));

            Vector2d new_position = new Vector2d(x,y);
            if (!isOccupied(new_position)) {
                cnt++;
                this.mapBoundary.add(new Grass(new_position));
                this.elementsOnMap.put(new_position, new Grass(new_position));
            }
            }
        }

    @Override
    public boolean place(Animal animal){
        super.place(animal);
        this.mapBoundary.add(animal);

        return true;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        AbstractWorldMapElement object = objectAt(position);
        if (object instanceof Grass) {
            this.elementsOnMap.remove(position);
            this.mapBoundary.remove(object);
            generateGrass(1);
        }
        return super.canMoveTo(position);
    }

    @Override
    public String toString(){
        this.upperRight = mapBoundary.findUpperRight(mapBoundary.objectsOnX, mapBoundary.objectsOnY);
        this.lowerLeft = mapBoundary.findLowerLeft(mapBoundary.objectsOnX, mapBoundary.objectsOnY);
        return super.toString();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement object = objectAt(oldPosition);
        if (object instanceof Animal) {
            this.elementsOnMap.put(newPosition, object);
            this.mapBoundary.remove(objectAt(oldPosition));
//            this.mapBoundary.positionChanged(oldPosition, newPosition);
            this.mapBoundary.add(objectAt(newPosition));
            this.elementsOnMap.remove(oldPosition, object);
        }
    }
}
