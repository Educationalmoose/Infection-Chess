import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ChessApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Infection Chess");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            ChessBoard chessBoard = new ChessBoard(12);
            frame.add(chessBoard);
            
            frame.pack();
            frame.setVisible(true);
        });
    }
}