package DesignPatterns;

import ChessCore.ChessBoard;
import ChessCore.GameStatus;
import ChessCore.Move;
import ChessCore.Player;


public class ChessGameMemento {
    private final ChessBoard boardState;
    private final GameStatus gameStatus; 
    private final Player PlayerTurn;
    private final Move lastMove;
    
    public ChessGameMemento(ChessBoard boardState , GameStatus gameStatus , Player PlayerTurn , Move lastMove) {
        this.boardState = boardState;
        this.gameStatus = gameStatus;
        this.PlayerTurn = PlayerTurn;
        this.lastMove = lastMove;
    }

    public ChessBoard getBoardState() {
        return boardState;
    }
    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public Player getPlayerTurn() {
        return PlayerTurn;
    }
    public Move getLastMove() {
        return lastMove;
    }

}
