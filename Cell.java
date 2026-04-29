import java.awt.Color;

public class Cell {
    private Color color;
    private Color tempColor = null;
    public boolean isVisible;
    public boolean isHighlighted;
    private Piece piece;
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.CYAN.darker();
        this.isVisible = true;
        this.piece = null;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

    public void setTempColor(Color color) {
        this.tempColor = color;
    }

    public Color getTempColor() {
        return tempColor;
    }

    public void clearTempColor() {
        tempColor = null;
    }

    public Color getColor() {
        return this.color;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int[] getPosition() {
        return new int[]{this.x, this.y};
    }
    
    @Override
    public String toString() {
        if (piece != null)
            return "Position: (" + x + ", " + y + ") Piece: " + this.piece.name;
        else
            return "Position: (" + x + ", " + y + ") Piece: None";
    }
}
