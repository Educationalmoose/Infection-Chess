import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ChessBoard extends JPanel{
    private final int size;
    private final int cellSize = 40;
    private final Cell[][] grid;
    private ArrayList<Cell> inactiveCells;

    public ChessBoard(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        inactiveCells = new ArrayList<>();

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = new Cell(r, c);
                if ((r % 2 == 1 && c % 2 == 1) || (r % 2 == 0 && c % 2 == 0))
                    grid[r][c].setColor(Color.WHITE);
                if (r == 0 || r == 1 || r == (size - 2) || r == (size - 1) || c == 0 || c == 1 || c == (size - 2) || c == (size - 1)) { 
                    inactiveCells.add(grid[r][c]);
                    grid[r][c].isVisible = false;
                }
                if (r == 3 && grid[r][c].isVisible){
                    Pawn p = new Pawn("black");
                    p.setCell(grid[r][c]);
                    grid[r][c].setPiece(p);
                }
                    
                if (r == 8 && grid[r][c].isVisible) {
                    Pawn p = new Pawn("white");
                    p.setCell(grid[r][c]);
                    grid[r][c].setPiece(p);
                }
            }
        }

        setPreferredSize(new Dimension(size * cellSize, size * cellSize));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Cell clickedCell = getClickedCell(e.getX(), e.getY());
                //clickedCell.setColor(Color.RED);
                //repaint();

                if (!inactiveCells.isEmpty()) {
                    while (true) {
                        Cell cell = getRandomCell();
                        if (hasVisibleNeighbors(cell)) {
                            unlockCell(cell);
                            break;
                        }
                    }
                    repaint();
                }
            }
        });
    }

    public Cell getRandomCell() {
        int randomIndex = ThreadLocalRandom.current().nextInt(inactiveCells.size());
        return inactiveCells.get(randomIndex);
    }

    public Cell getClickedCell(int x, int y) {
        int col = (int) Math.floor(x / cellSize);
        int row = (int) Math.floor(y / cellSize);
        return grid[row][col];
    }

    public boolean hasVisibleNeighbors(Cell cell) {
        int[] dRow = {-1,  0,  1,  0};
        int[] dCol = { 0,  1,  0, -1};

        int[] pos = cell.getPosition();

        for (int i = 0; i < dRow.length; i++) {
            int neighborRow = pos[0] + dRow[i];
            int neighborCol = pos[1] + dCol[i];

            if (isValid(neighborRow, neighborCol, grid.length)) {
                
                Cell neighbor = grid[neighborRow][neighborCol];
                
                if (neighbor.isVisible) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, int size) {
        return (row >= 0 && row < size && col >= 0 && col < size);
    }

    public void unlockCell(Cell cell) {
        int[] pos = cell.getPosition();

        int ur = Math.abs(pos[0] - size + 1);
        int uc = Math.abs(pos[1] - size + 1);
        inactiveCells.remove(cell);
        cell.isVisible = true;
        for (Cell c : inactiveCells) {
            int[] cPos = c.getPosition();
            if (cPos[0] == ur && cPos[1] == uc) {
                c.isVisible = true;
                inactiveCells.remove(c);
                break;
            }
        }
    }

    public void movePiece(Cell startingCell, Cell targetCell) {
        Piece movingPiece = startingCell.getPiece();
        targetCell.setPiece(movingPiece);
        movingPiece.setCell(targetCell);
        startingCell.setPiece(null);
    }

    public void showMoves(Piece piece) {
        int[][] moves = piece.getMoveCells();
        for (int[] pos : moves) {
            int x = pos[0];
            int y = pos[1];

            if (isValid(x, y, size)) {
                grid[x][y].setColor(Color.RED);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.DARK_GRAY);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Cell cell = grid[r][c];
                if (cell.isVisible) {
                    int x = c * cellSize;
                    int y = r * cellSize;

                    g.setColor(cell.getColor());
                    g.fillRect(x, y, cellSize, cellSize);

                    if (cell.getPiece() != null) {
                        g.drawImage(cell.getPiece().image, x, y, cellSize, cellSize, this);
                    }
                }
            }
        }
    }
}
