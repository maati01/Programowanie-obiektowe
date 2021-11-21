package agh.ics.oop;

import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap{
    int numberGrasses;

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
                this.elementsOnMap.add(new Grass(new_position));
            }
            }
        }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object object = objectAt(position);
        if (object instanceof Grass) {
            elementsOnMap.remove(object);
            generateGrass(1);
        }
        return super.canMoveTo(position);
    }

    @Override
    public String toString(){
        for(AbstractWorldMapElement element: this.elementsOnMap){
            this.lowerLeft = this.lowerLeft.lowerLeft(element.getPosition());
            this.upperRight = this.upperRight.upperRight(element.getPosition());
        }
        return super.toString();
    }
}
