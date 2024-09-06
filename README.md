---> To run the game there is only one main (ChessCoreGui) in chessFrontend package which is the starting of the game logic.

This is the files of chess game which includes rather than the images in the begining,
4 packges : 
  1) pieces :
  for each piece , a seperate class

  2) design patterns:
     
  To identify which design patterns are used in the project.
  All added files/classes made especially for a design pattern is in this package.
  
  3)chessCore
  for all backend classes responsible for the game logic

  4)chessFrontend
  for all classes of the project responsible for the GUI and UI
  
The game features are :
  It is a game between 2 players , when a player paly their turn the board is turned automatically so the other player can see their pieces and play as a normal view.
  There is "undo" feature to undo the last move , implemented by memento design pattern.
  The game shows the player all avalible moves of a piece once the player clicked on the piece.
  The game shows a warning message if a move was made incorrectly based on the game logic or if a player tried to move the opponent pieces.
  The game has no timer and only end when a player wins or in case of draws. 
  
