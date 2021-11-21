package agh.ics.oop;

public class Animal extends AbstractWorldMapElement {
    private MapDirection vector = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private final IWorldMap map;

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.map = new RectangularMap(5, 5);
    }

    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
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
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.add(this.vector.toUnitVector().opposite());
                if (map.canMoveTo(newPosition)) {
                    this.position = newPosition;
                }
            }
        }
    }
}
