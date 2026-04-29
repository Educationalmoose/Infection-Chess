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
    private final int cellSize = 60;
    private final Cell[][] grid;
    private ArrayList<Cell> inactiveCells;
    private Piece selectedPiece;
    private int playerTurn = 1;

    public ChessBoard(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        inactiveCells = new ArrayList<>();

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = new Cell(r, c);
                // make grid pattern
                if ((r % 2 == 1 && c % 2 == 1) || (r % 2 == 0 && c % 2 == 0))
                    grid[r][c].setColor(Color.WHITE);

                // makes the edges inactive at the start
                if (r == 0 || r == 1 || r == (size - 2) || r == (size - 1) || c == 0 || c == 1 || c == (size - 2) || c == (size - 1)) { 
                    inactiveCells.add(grid[r][c]);
                    grid[r][c].isVisible = false;
                }

                // setup pawns
                if (r == 3 && grid[r][c].isVisible){
                    Pawn p = new Pawn(0);
                    p.setCell(grid[r][c]);
                    grid[r][c].setPiece(p);
                }
                    
                if (r == 8 && grid[r][c].isVisible) {
                    Pawn p = new Pawn(1);
                    p.setCell(grid[r][c]);
                    grid[r][c].setPiece(p);
                }

                
            }
        }

        // setup pieces
        {
            // setup rooks
            Rook r1 = new Rook(0);
            r1.setCell(grid[2][2]); 
            grid[2][2].setPiece(r1);

            Rook r2 = new Rook(0);
            r2.setCell(grid[2][9]); 
            grid[2][9].setPiece(r2);

            Rook r3 = new Rook(1);
            r3.setCell(grid[9][9]); 
            grid[9][9].setPiece(r3);

            Rook r4 = new Rook(1);
            r4.setCell(grid[9][2]); 
            grid[9][2].setPiece(r4);

            // setup knights
            Knight k1 = new Knight(0);
            k1.setCell(grid[2][3]); 
            grid[2][3].setPiece(k1);

            Knight k2 = new Knight(0);
            k2.setCell(grid[2][8]); 
            grid[2][8].setPiece(k2);

            Knight k3 = new Knight(1);
            k3.setCell(grid[9][8]); 
            grid[9][8].setPiece(k3);

            Knight k4 = new Knight(1);
            k4.setCell(grid[9][3]); 
            grid[9][3].setPiece(k4);

            // setup bishops
            Bishop b1 = new Bishop(0);
            b1.setCell(grid[2][4]); 
            grid[2][4].setPiece(b1);

            Bishop b2 = new Bishop(0);
            b2.setCell(grid[2][7]); 
            grid[2][7].setPiece(b2);

            Bishop b3 = new Bishop(1);
            b3.setCell(grid[9][4]); 
            grid[9][4].setPiece(b3);

            Bishop b4 = new Bishop(1);
            b4.setCell(grid[9][7]); 
            grid[9][7].setPiece(b4);

            // setup queens
            Queen q1 = new Queen(0);
            q1.setCell(grid[2][5]); 
            grid[2][5].setPiece(q1);

            Queen q2 = new Queen(1);
            q2.setCell(grid[9][5]); 
            grid[9][5].setPiece(q2);

            // setup kings
            King ki1 = new King(0);
            ki1.setCell(grid[2][6]); 
            grid[2][6].setPiece(ki1);

            King ki2 = new King(1);
            ki2.setCell(grid[9][6]); 
            grid[9][6].setPiece(ki2);
        }

        setPreferredSize(new Dimension(size * cellSize, size * cellSize));
        setBackground(Color.DARK_GRAY);

        addMouseListener(new MouseAdapter() {

            // click a cell
            // if there is a piece in the cell, i don't have another piece selected already, and that piece is not in my available moves, select that piece
            // if there is no piece in the cell, and i don't have a piece selected, clear the highlights
            // if there is a piece in the cell, i have another piece selected, and this piece is in my available moves, move there

            @Override
            public void mousePressed(MouseEvent e) {
                Cell clickedCell = getClickedCell(e.getX(), e.getY());
                if (clickedCell.getPiece() != null && playerTurn % 2 == clickedCell.getPiece().getTeam()) { 
                    if (selectedPiece != null) {
                        if (clickedCell.getPiece() != selectedPiece) {
                            if (selectedPiece.getValidMoves(grid).contains(clickedCell)) {
                                clickedCell.setPiece(selectedPiece);
                                selectedPiece.getCell().setPiece(null);
                                selectedPiece.setCell(clickedCell);
                                selectedPiece = null;
                                clearHighlights();
                                growMap();
                                playerTurn += 1;
                            } else {
                                selectedPiece = clickedCell.getPiece();
                                showMoves(selectedPiece);
                            }
                        } else {
                            selectedPiece = null;
                            clearHighlights();
                        }
                    } else {
                        selectedPiece = clickedCell.getPiece();
                        showMoves(selectedPiece);
                    }
                } else {
                    if (selectedPiece != null && playerTurn % 2 == selectedPiece.getTeam()) {
                        if (selectedPiece.getValidMoves(grid).contains(clickedCell)) {
                            clickedCell.setPiece(selectedPiece);
                            selectedPiece.getCell().setPiece(null);
                            selectedPiece.setCell(clickedCell);
                            growMap();
                            playerTurn += 1;
                        }
                    }
                    selectedPiece = null;
                    clearHighlights();
                }
                repaint();
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
        clearHighlights();

        ArrayList<Cell> validMoves = piece.getValidMoves(grid);
        
        for (Cell target : validMoves) {
            if (target.isVisible) {
                target.setTempColor(blend(target.getColor(), Color.RED, .75));
            }
        }
    }

    public void clearHighlights() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c].clearTempColor();
            }
        }
    }

    public void growMap() {
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

    public Color blend(Color c1, Color c2, double ratio) {
        if (ratio > 1f) ratio = 1f;
        else if (ratio < 0f) ratio = 0f;
        float iRatio = (float) (1.0 - ratio);

        int r = (int) ((c1.getRed() * iRatio) + (c2.getRed() * ratio));
        int g = (int) ((c1.getGreen() * iRatio) + (c2.getGreen() * ratio));
        int b = (int) ((c1.getBlue() * iRatio) + (c2.getBlue() * ratio));

        return new Color(r, g, b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Cell cell = grid[r][c];
                if (cell.isVisible) {
                    int x = c * cellSize;
                    int y = r * cellSize;

                    if (cell.getTempColor() == null) 
                        g.setColor(cell.getColor());
                    else 
                        g.setColor(cell.getTempColor());
                    
                    g.fillRect(x, y, cellSize, cellSize);
                    

                    if (cell.getPiece() != null) {
                        g.drawImage(cell.getPiece().image, x, y, cellSize, cellSize, this);
                        if (cell.getTempColor() != null)
                            g.setColor(cell.getTempColor());
                    }
                }
            }
        }
    }
}
