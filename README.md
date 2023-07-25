This is a Java implementation of the classic game of Minesweeper.

## Key Features

1. **Board and Cell Structures**: The program uses a 2D array of Cell objects to represent the Minesweeper game board.

2. **Dynamic Board Initialization**: Upon startup, the game asks for the number of mines and dynamically places them on the board.

3. **Gameplay Actions**: Users can perform two actions: flagging a cell as a mine, or claiming a cell as free (not containing a mine). To do this, the user inputs the coordinates of the cell and the action they wish to take.

4. **Game Flow Control**: The game continues until all non-mine cells are revealed, or all mines are correctly flagged, at which point the player wins. Conversely, if a player attempts to claim a cell as free when it is a mine, the game ends in a loss.

5. **Adjacent Mine Count**: Each cell, when revealed, shows the number of adjacent mines to assist the player in their decisions.

## How to Use

To play the game, run the `Main` class. The game will prompt you for the number of mines you want on the field. The game board is printed and you are prompted to either set/unset mines or claim a cell as free by providing the coordinates and the desired action. 
