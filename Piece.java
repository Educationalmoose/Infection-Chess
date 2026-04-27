public class Piece {
    int[] mRows;
    int[] mCols;

    private Cell cell;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int[][] getMoveCells() {
        int[][] moveCells = new int[mRows.length][2];

        int row = cell.getPosition()[0];
        int col = cell.getPosition()[1];
        
        for (int i = 0; i < mRows.length; i++) {
            moveCells[i][0] = row + mRows[i];
            moveCells[i][1] = col + mCols[i];
        }
        
        return moveCells;
    }
}
