import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Pawn extends Piece {
    public Pawn(int team) {
        this.team = team;
        if (team == 0) {
            name = "Black Pawn";
        } else {
            name = "White pawn";
        }
        
        try {
            String image_path = team == 0 ? "Pieces/black-pawn.png" : "Pieces/white-pawn.png";
            image = ImageIO.read(new File(image_path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cell> getValidMoves(Cell[][] grid) {
        ArrayList<Cell> validMoves = new ArrayList<>();
        int currentRow = getCell().getPosition()[0];
        int currentCol = getCell().getPosition()[1];
        int boardSize = grid.length;

        int dir = this.team == 0 ? 1 : -1;
        
        int startRow = this.team == 0 ? 3 : boardSize - 4;

        int forwardRow = currentRow + dir;

        if (forwardRow >= 0 && forwardRow < boardSize) {
            
            Cell forwardCell = grid[forwardRow][currentCol];
            if (forwardCell.getPiece() == null) {
                validMoves.add(forwardCell);

                if (currentRow == startRow) {
                    int doubleRow = currentRow + (dir * 2);
                    Cell doubleCell = grid[doubleRow][currentCol];
                    if (doubleCell.getPiece() == null) {
                        validMoves.add(doubleCell);
                    }
                }
            }

            int[] captureCols = {currentCol - 1, currentCol + 1};
            
            for (int col : captureCols) {
                if (col >= 0 && col < boardSize) {
                    Cell captureCell = grid[forwardRow][col];
                    
                    if (captureCell.getPiece() != null && captureCell.getPiece().getTeam() != this.team) {
                        validMoves.add(captureCell);
                    }
                }
            }
        }
        
        return validMoves;
    }

}
