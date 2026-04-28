import java.io.File;
import javax.imageio.ImageIO;

public class King extends Piece {

    public King(String color) {
        mRows = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
        mCols = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
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
