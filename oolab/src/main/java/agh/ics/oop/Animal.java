package agh.ics.oop;

public class Animal {
    private MapDirection vector = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public MapDirection getOrientation() {
        return vector;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString(){
        return "Direction: " + this.vector.toString() + ", " + "Position: " + this.position.toString();
    }

    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT -> this.vector = this.vector.next();
            case LEFT -> this.vector = this.vector.previous();
            case FORWARD -> {
                Vector2d tempPosition = this.position.add(this.vector.toUnitVector());
                if (tempPosition.follows(new Vector2d(0, 0)) && tempPosition.precedes(new Vector2d(4, 4))) {
                    this.position = tempPosition;
                }
            }
            case BACKWARD -> {
                Vector2d tempPosition = this.position.add(this.vector.toUnitVector().opposite());
                if (tempPosition.follows(new Vector2d(0, 0)) && tempPosition.precedes(new Vector2d(4, 4))) {
                    this.position = tempPosition;
                }
            }
        }
    }
}
