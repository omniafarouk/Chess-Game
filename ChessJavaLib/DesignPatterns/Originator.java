
package DesignPatterns;

import ChessCore.ChessBoard;
import ChessCore.ChessGame;

public abstract class Originator {
    
    public static ChessGameMemento createState(ChessGame game)
    {
        ChessBoard clonedBoard = game.getBoard().clone();
        ChessGameMemento currentState = new ChessGameMemento(clonedBoard , game.getGameStatus() ,
                                                        game.getWhoseTurn() , game.getLastMove());
          return currentState;
    }
}
