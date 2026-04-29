import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Rook extends Piece {
    private final int[] dirRow = {-1,  1,  0,  0};
    private final int[] dirCol = { 0,  0, -1,  1};
    
    public Rook(int team) {
        this.team = team;
        name = team == 0 ? "Black Rook" : "White Rook";
        try {
            String image_path = team == 0 ? "Pieces/black-rook.png" : "Pieces/white-rook.png";
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
        boardSize = grid.length;

        for (int i = 0; i < dirRow.length; i++) {
            int r = currentRow + dirRow[i];
            int c = currentCol + dirCol[i];

            while (r >= 0 && r < boardSize && c >= 0 && c < boardSize) {
                Cell targetCell = grid[r][c];

                if (targetCell.getPiece() == null) {
                    validMoves.add(targetCell);
                } else {
                    if (targetCell.getPiece().getTeam() != this.team) {
                        validMoves.add(targetCell);
                    }
                    break;
                }
                r += dirRow[i];
                c += dirCol[i];
            }
        }
        return validMoves;
    }
}
