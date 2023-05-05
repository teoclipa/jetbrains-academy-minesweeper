package minesweeper;

import java.util.Random;

public class Board {
    private final int rows;
    private final int columns;
    private final Cell[][] cells;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new Cell[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                this.cells[row][col] = new Cell(false);
            }
        }
    }

    public void placeMines(int numMines) {
        Random random = new Random();

        int count = 0;
        while (count < numMines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(columns);

            if (!cells[row][col].isMine()) {
                cells[row][col].setMine(true);
                count++;
            }
        }
    }

    public void print() {
        System.out.println(" │123456789│");
        System.out.println("—│—————————│");
        for (int row = 0; row < rows; row++) {
            System.out.print(row + 1 + "|");
            for (int col = 0; col < columns; col++) {
                Cell cell = cells[row][col];
                cell.setNumAdjacentMines(cell.getNumAdjacentMines(this, row, col));
                if (!cell.isMine()) {
                    System.out.print(cell.getSymbol());
                } else if (cell.isFlagged()) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("│");
        }
        System.out.println("—│—————————│");
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void revealAdjacentCells(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            if (i < 0 || i >= rows) {
                continue;
            }
            for (int j = col - 1; j <= col + 1; j++) {
                if (j < 0 || j >= columns) {
                    continue;
                }
                Cell cell = cells[i][j];
                if (!cell.isMine() && cell.isFlagged()) {
                    cell.setFlagged(false);

                }
                if (!cell.isRevealed()) {
                    cell.reveal();
                    if (cell.getNumAdjacentMines(this, i, j) == 0) {
                        revealAdjacentCells(i, j);
                    }
                }
            }
        }
    }
}
