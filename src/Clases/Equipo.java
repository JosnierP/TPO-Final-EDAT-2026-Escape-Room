package Clases;

public class Equipo {
    private String nameTeam;
    private int requieredPoints;
    private int currentRoom;
    private int currentPoints;

    public Equipo(String nameTeam, int requieredPoints, int currentRoom, int currentPoints) {
        this.nameTeam = nameTeam;
        this.requieredPoints = requieredPoints;
        this.currentRoom = currentRoom;
        this.currentPoints = currentPoints;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public int getRequieredPoints() {
        return requieredPoints;
    }

    public void setRequieredPoints(int requieredPoints) {
        this.requieredPoints = requieredPoints;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }
}
