import java.io.File;
import javax.imageio.ImageIO;

public class Knight extends Piece {
    int[] mRows = {2, 2, 1, -1, -2, -2, -1, 1};
    int[] mCols = {-1, 1, 2, 2, 1, -1, -2, -2};
    public Knight(String color) {
        this.name = "Knight";
        try {
            if (color.toLowerCase().equals("black")) {
                this.image = ImageIO.read(new File("Pieces/black-knight.png"));
                name = "Black Knight";
            }
            if (color.toLowerCase().equals("white")) {
                this.image = ImageIO.read(new File("Pieces/white-knight.png"));
                name = "White Knight";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}