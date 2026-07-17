package Clases;

public class Puerta {
    private int point;
    private int entranceDoor;
    private int exitDoor;

    public Puerta(int point, int entranceDoor, int exitDoor) {
        this.point = point;
        this.entranceDoor = entranceDoor;
        this.exitDoor = exitDoor;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getEntranceDoor() {
        return entranceDoor;
    }

    public void setEntranceDoor(int entranceDoor) {
        this.entranceDoor = entranceDoor;
    }

    public int getExitDoor() {
        return exitDoor;
    }

    public void setExitDoor(int exitDoor) {
        this.exitDoor = exitDoor;
    }
}
