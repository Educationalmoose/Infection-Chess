import java.io.File;
import javax.imageio.ImageIO;

public class King extends Piece {
    int[] mRows = {-1, -1, -1, 0, 1, 1, 1, 0};
    int[] mCols = {-1, 0, 1, 1, 1, 0, -1, -1};

    public King(String color) {
        this.name = "King";
        try {
            if (color.toLowerCase().equals("black")) {
                this.image = ImageIO.read(new File("Pieces/black-king.png"));
                name = "Black King";
            }
            if (color.toLowerCase().equals("white")) {
                this.image = ImageIO.read(new File("Pieces/white-king.png"));
                name = "White King";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
