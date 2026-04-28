import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bishop extends Piece {

    public Bishop (String color) {
        name = "Bishop";
        makeMoves();
        try {
            if (color.toLowerCase().equals("black")) {
                this.image = ImageIO.read(new File("Pieces/black-bishop.png"));
                name = "Black Bishop";
            }
            if (color.toLowerCase().equals("white")) {
                this.image = ImageIO.read(new File("Pieces/white-bishop.png"));
                name = "White Bishop";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeMoves() {
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();

        for (int i = -boardSize-1; i < boardSize-1; i ++) {
            rows.add(i);
            rows.add(-i);
            cols.add(i);
            cols.add(i);
        }
        mRows = rows.stream().mapToInt(i -> i).toArray();
        mCols = cols.stream().mapToInt(i -> i).toArray();
    }
}
