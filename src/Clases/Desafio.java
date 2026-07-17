package Clases;

public class Desafio implements Comparable<Desafio>{
    private int scoreAwarded;
    private String name;
    private String type;

    public Desafio(int scoreAwarded, String name, String type) {
        this.scoreAwarded = scoreAwarded;
        this.name = name;
        this.type = type;
    }

    public int getScoreAwarded() {
        return scoreAwarded;
    }

    public void setScoreAwarded(int scoreAwarded) {
        this.scoreAwarded = scoreAwarded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Desafio o) {
        return 0;
    }
}
