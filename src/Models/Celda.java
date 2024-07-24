package Models;

public class Celda {

    private boolean passable;

    private boolean passed;

    private boolean notPath;

    private boolean isFinish;

    private int column;
    private int row;

    public Celda(int column, int row, boolean passable) {
        this.column = column;
        this.row = row;
        this.passable = passable;
        passed = false;
        isFinish = false;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public boolean isNotPath() {
        return notPath;
    }

    public void setNotPath(boolean notPath) {
        this.notPath = notPath;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "Celda [" + column + ", " + row + "]";
    }

    public Boolean equals(Celda celda) {
        return this.column == celda.getColumn() && this.row == celda.getRow();
    }

    public String getCelda() {
        return "[" + this.column + "," + this.row + "]";
    }

}
