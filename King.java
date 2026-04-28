import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class King extends Piece {
    private final int[] mRows = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    private final int[] mCols = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};

    public King(int team) {
        this.team = team;
        name = team == 0 ? "Black King" : "White King";
        try {
            String image_path = team == 0 ? "Pieces/black-king.png" : "Pieces/white-king.png";
            image = ImageIO.read(new File(image_path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Cell> getValidMoves(Cell[][] grid) {
        ArrayList<Cell> validMoves = new ArrayList<>();
        int currentRow = getCell().getPosition()[0];
        int currentCol = getCell().getPosition()[1];
        int boardSize = grid.length;

        for (int i = 0; i < mRows.length; i++) {
            int r = currentRow + mRows[i];
            int c = currentCol + mCols[i];

            if (r >= 0 && r < boardSize && c >= 0 && c < boardSize) {
                Cell targetCell = grid[r][c];

                if (targetCell.getPiece() == null || targetCell.getPiece().getTeam() != this.team) {
                    validMoves.add(targetCell);
                }
            }
        }
        return validMoves;
    }
}
