package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
//    private final AbstractWorldMap map;
    public final SortedSet<AbstractWorldMapElement> objectsOnX = new TreeSet<>((o1, o2) -> {
        if(o1.getPosition().x < o2.getPosition().x) return -1;
        else if(o1.getPosition().x > o2.getPosition().x) return 1;
        else{
            if(o1.getPosition().y < o2.getPosition().y) return -1;
            else if(o1.getPosition().y > o2.getPosition().y) return 1;
            return 0;
        }
    });


    public final SortedSet<AbstractWorldMapElement> objectsOnY = new TreeSet<>((o1, o2) -> {
        if(o1.getPosition().y < o2.getPosition().y) return -1;
        else if(o1.getPosition().y > o2.getPosition().y) return 1;
        else{
            if(o1.getPosition().x < o2.getPosition().x) return -1;
            else if(o1.getPosition().x > o2.getPosition().x) return 1;
            return 0;
        }
    });

//    public MapBoundary(AbstractWorldMap map) {
//        this.map = map;
//    }


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        System.out.println(objectsOnX);
//        System.out.println(objectsOnY);
//        add(this.map.objectAt(newPosition));
//        remove(this.map.objectAt(oldPosition));
//        System.out.println(objectsOnX);
//        System.out.println(objectsOnY);

    }

    public void add(AbstractWorldMapElement element){
        this.objectsOnX.add(element);
        this.objectsOnY.add(element);
    }

    public void remove(AbstractWorldMapElement element){
        this.objectsOnX.remove(element);
        this.objectsOnY.remove(element);
    }

    public Vector2d findUpperRight(SortedSet<AbstractWorldMapElement> objectsOnX,
                                   SortedSet<AbstractWorldMapElement> objectsOnY){
    return new Vector2d(objectsOnX.last().getPosition().x,objectsOnY.last().getPosition().y);
    }

    public Vector2d findLowerLeft(SortedSet<AbstractWorldMapElement> objectsOnX,
                                  SortedSet<AbstractWorldMapElement> objectsOnY){
        return new Vector2d(objectsOnX.first().getPosition().x,objectsOnY.first().getPosition().y);
    }

}
