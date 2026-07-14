package Clases;

public class Puerta {
    private int numberDoor;
    private int entranceDoor;
    private int exitDoor;

    public Puerta(int numberDoor, int entranceDoor, int exitDoor) {
        this.numberDoor = numberDoor;
        this.entranceDoor = entranceDoor;
        this.exitDoor = exitDoor;
    }

    public int getNumberDoor() {
        return numberDoor;
    }

    public void setNumberDoor(int numberDoor) {
        this.numberDoor = numberDoor;
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
