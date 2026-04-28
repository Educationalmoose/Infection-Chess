import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Pawn extends Piece {
    public Pawn(String color) {

        mRows = new int[]{-1, -2, -1, -1};
        mCols = new int[]{0, 0, -1, 1};
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
