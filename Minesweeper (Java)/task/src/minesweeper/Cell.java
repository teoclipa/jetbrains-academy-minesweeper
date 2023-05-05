package minesweeper;

import minesweeper.Board;

public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int numAdjacentMines;

    public Cell(boolean isMine) {
        this.isMine = isMine;
        this.isRevealed = false;
        this.isFlagged = false;
        this.numAdjacentMines = 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void reveal() {
        isRevealed = true;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public void setNumAdjacentMines(int numAdjacentMines) {
        this.numAdjacentMines = numAdjacentMines;
    }

    public boolean isEmpty() {
        return !isMine && numAdjacentMines == 0;
    }

    public String getSymbol() {
        if (isFlagged) {
            return "*";
        }
        if (!isRevealed) {
            return ".";
        }
        if (isMine) {
            return "X";
        }
        if (numAdjacentMines == 0) {
            return "/";
        }
        if (numAdjacentMines > 0) {
            return String.valueOf(numAdjacentMines);
        }
        return ".";
    }

    public int getNumAdjacentMines(Board board, int row, int col) {
        int numMines = 0;
        for (int r = Math.max(row - 1, 0); r <= Math.min(row + 1, board.getRows() - 1); r++) {
            for (int c = Math.max(col - 1, 0); c <= Math.min(col + 1, board.getColumns() - 1); c++) {
                if (r == row && c == col) {
                    continue;
                }
                if (board.getCell(r, c).isMine()) {
                    numMines++;
                }
            }
        }
        return numMines;
    }
}
