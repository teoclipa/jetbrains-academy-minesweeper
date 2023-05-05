package minesweeper;

import java.util.Scanner;

public class Game {
    private static Scanner scanner;
    private final Board board;
    private static int numMines;

    public Game(int numRows, int numCols) {
        this.board = new Board(numRows, numCols);
        scanner = new Scanner(System.in);
    }

    public void play() {
        initializeBoard();

        while (!isGameOver()) {
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            int col = scanner.nextInt() - 1;
            int row = scanner.nextInt() - 1;
            String action = scanner.next();
            Cell cell = board.getCell(row, col);

            switch (action) {
                case "mine":
                    cell.setFlagged(!cell.isFlagged());
                    break;
                case "free":
                    if (cell.isMine()) {
                        board.print();
                        System.out.println("You stepped on a mine and failed!");
                        return;
                    } else if (cell.getNumAdjacentMines(board, row, col) == 0) {
                        board.revealAdjacentCells(row, col);
                    }else if (cell.getNumAdjacentMines(board, row, col) > 0) {
                        cell.reveal();
                    }
                    break;
                default:
                    System.out.println("Invalid action!");
                    break;
            }
            board.print();
        }
        scanner.close();
    }

    public void initializeBoard() {
        System.out.print("How many mines do you want on the field? ");
        numMines = scanner.nextInt();
        board.placeMines(numMines);
        board.print();
    }

    public boolean isGameOver() {
        return hasPlayerSteppedOnMine() || hasPlayerWon();
    }

    public boolean hasPlayerSteppedOnMine() {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                Cell cell = board.getCell(row, col);
                if (cell.isRevealed() && cell.isMine()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPlayerMarkedAllMines() {
        int countFlags = 0;

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                Cell cell = board.getCell(row, col);
                if (cell.isFlagged() && cell.isMine()) {
                    countFlags++;
                } else if (cell.isFlagged() && !cell.isMine()) {
                    return false;
                }
            }
        }
        if (countFlags == numMines) {
            System.out.println("Congratulations! You found all mines!");
        }
        return countFlags == numMines;
    }

    public boolean hasPlayerRevealedAllOtherCells() {
        int count = 0;
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                Cell cell = board.getCell(row, col);
                if (cell.isRevealed() && !cell.isMine()) {
                    count++;
                }
            }
        }
        if (count == board.getRows() * board.getColumns() - numMines) {
            System.out.println("Congratulations! You found all mines!");
        }
        return count == board.getRows() * board.getColumns() - numMines;
    }

    public boolean hasPlayerWon() {
        return hasPlayerRevealedAllOtherCells() || hasPlayerMarkedAllMines();
    }
}