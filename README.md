#### To run the game there is only one main (`ChessCoreGui`) in `chessFrontend` package which is the starting of the game logic.

The project is divided into four main packages, excluding the images:

1) `pieces`  
> Contains a separate class for each piece.

2) `designpatterns`  
> To identify which design patterns are used in the project.  
> All added files/classes made especially for a design pattern are in this package.  
> Contains classes specifically created to implement and demonstrate software design patterns used in the project.  
> For example, the **Memento Pattern** is applied to support the "Undo" functionality.

3) `chessCore`  
> Contains the backend logic for the game, including move validation, game state management, and win/draw detection.

4) `chessFrontend`  
> Handles the game's GUI and UI.  
> The main class `ChessCoreGui`, located in this package, serves as the game’s entry point.

---

### Game Features

1. **`Two-Player Gameplay`**  
   It is a game between 2 players. When a player plays their turn, the board is turned automatically so the other player can see their pieces and play from a normal view.

2. **`Undo Functionality`**  
   This "undo" feature allows undoing the last move. Implemented by **Memento Design Pattern**.

3. **`Visual Move Assistance`**  
   The game shows the player all available moves of a piece once the piece is clicked on.

4. **`Move Validation and Warnings`**  
   The game shows a warning message if a move was made incorrectly based on the game logic or if a player tried to move the opponent's pieces.

5. **`No Time Limit`**  
   The game has no time limit. It only ends when a player wins or a draw is detected.
