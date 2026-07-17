package Clases;

import java.io.Serializable;

public class Habitacion implements Serializable, Comparable<Habitacion> {
    private int code;
    private String name;
    private int floor;
    private double squareMeters;
    private boolean exit;

    public Habitacion(int code, String name, int floor, double squareMeters, boolean exit) {
        this.code = code;
        this.name = name;
        this.floor = floor;
        this.squareMeters = squareMeters;
        this.exit = exit;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getSquareMeters() {
        return squareMeters;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    @Override
    public int compareTo(Habitacion o) {
        return 0;
    }
}
