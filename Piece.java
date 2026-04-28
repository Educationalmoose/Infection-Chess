import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece {
    BufferedImage image;
    String name;
    int team;
    static int boardSize;

    private Cell cell;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getTeam() {
        return team;
    }

    public BufferedImage getImage() {
        return image;
    }

    public abstract ArrayList<Cell> getValidMoves(Cell[][] grid);
}
