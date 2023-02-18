package constraints;

public class BinaryConstraint {
    private int x;
    private int y;
    private int r;

    public BinaryConstraint(int x, int y, int r) {
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

        BinaryConstraint binaryConstraint = (BinaryConstraint) o;

        if (x != binaryConstraint.x) return false;
        if (y != binaryConstraint.y) return false;
        return r == binaryConstraint.r;
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
        return "x" + y + " - " + "x" + x + " <= " + r;
    }
}
