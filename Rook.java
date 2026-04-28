import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Rook extends Piece {
    
    public Rook(String color) {
        name = "Rook";
        makeMoves();
        try {
            if (color.toLowerCase().equals("black")) {
                this.image = ImageIO.read(new File("Pieces/black-rook.png"));
                name = "Black Rook";
            }
            if (color.toLowerCase().equals("white")) {
                this.image = ImageIO.read(new File("Pieces/white-rook.png"));
                name = "White Rook";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void makeMoves() {
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();

        for (int i = -boardSize-1; i < boardSize-1; i ++) {
            rows.add(0);
            rows.add(i);
            cols.add(i);
            cols.add(0);
        }
        mRows = rows.stream().mapToInt(i -> i).toArray();
        mCols = cols.stream().mapToInt(i -> i).toArray();
    }
}
