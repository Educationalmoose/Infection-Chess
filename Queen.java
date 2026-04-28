import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Queen extends Piece {

    public Queen(String color) {
        name = "Queen";
        makeMoves();
        try {
            if (color.toLowerCase().equals("black")) {
                this.image = ImageIO.read(new File("Pieces/black-queen.png"));
                name = "Black Queen";
            }
            if (color.toLowerCase().equals("white")) {
                this.image = ImageIO.read(new File("Pieces/white-queen.png"));
                name = "White Queen";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void makeMoves() {
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();

        for (int i = -boardSize-1; i < boardSize-1; i ++) {
            // Bishop moves
            rows.add(i);
            rows.add(-i);
            cols.add(i);
            cols.add(i);

            // Rook moves
            rows.add(0);
            rows.add(i);
            cols.add(i);
            cols.add(0);
        }
        mRows = rows.stream().mapToInt(i -> i).toArray();
        mCols = cols.stream().mapToInt(i -> i).toArray();
    }
}
