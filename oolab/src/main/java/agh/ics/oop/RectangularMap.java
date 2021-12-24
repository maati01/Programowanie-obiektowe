package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{
    //tymczasowo zakladam ze poczatek mapy jest zawsze w 0,0
    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        if(width < 0 || height < 0){
            this.width = 5;
            this.height = 5;
        }
        else {
            this.width = width;
            this.height = height;
        }
        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(this.width,this.height);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement object = objectAt(oldPosition);
        if (object instanceof Animal) {
            this.elementsOnMap.put(newPosition, object);
            this.elementsOnMap.remove(oldPosition, object);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return super.canMoveTo(position) && position.follows(this.lowerLeft) && position.precedes(this.upperRight);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
