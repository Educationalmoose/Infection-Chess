import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Pawn extends Piece {
    int[] mRows = {-1, -2, -1, -1};
    int[] mCols = {0, 0, -1, 1};

    public Pawn(String color) {
        name = "Pawn";
        try {
            if (color.toLowerCase().equals("black")) {
                this.image = ImageIO.read(new File("Pieces/black-pawn.png"));
                mRows = Arrays.stream(mRows).map(n -> -n).toArray();
                name = "Black Pawn";
            }
            if (color.toLowerCase().equals("white")) {
                this.image = ImageIO.read(new File("Pieces/white-pawn.png"));
                name = "White Pawn";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
