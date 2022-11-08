package model.DTN;

public class Disjunct {
    private int x;
    private int y;
    private int r;

    public Disjunct(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disjunct disjunct = (Disjunct) o;

        if (x != disjunct.x) return false;
        if (y != disjunct.y) return false;
        return r == disjunct.r;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + r;
        return result;
    }

    @Override
    public String toString() {
        return "x" + x + " - " + "x" + y + " <= " + r;
    }
}
