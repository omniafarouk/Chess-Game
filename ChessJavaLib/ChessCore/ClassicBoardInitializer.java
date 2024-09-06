package ChessCore;

import DesignPatterns.PieceFactory;
import Pieces.Piece;
import Pieces.PieceType;


public final class ClassicBoardInitializer implements BoardInitializer {    
    private static BoardInitializer instance = null;

    private ClassicBoardInitializer() {
    }
    public static BoardInitializer getInstance() {          //this implements Singleton design pattern
        if(instance == null){
            instance = new ClassicBoardInitializer();
        }
        return instance;
    }

  @Override
    public Piece[][] initialize() {
        Piece[][] initialState = {
            {PieceFactory.createPiece(PieceType.ROOK, Player.WHITE), PieceFactory.createPiece(PieceType.KNIGHT, Player.WHITE), PieceFactory.createPiece(PieceType.BISHOP, Player.WHITE),PieceFactory.createPiece(PieceType.QUEEN, Player.WHITE), PieceFactory.createPiece(PieceType.KING, Player.WHITE),PieceFactory.createPiece(PieceType.BISHOP, Player.WHITE),PieceFactory.createPiece(PieceType.KNIGHT, Player.WHITE), PieceFactory.createPiece(PieceType.ROOK, Player.WHITE)},
            {PieceFactory.createPiece(PieceType.PAWN, Player.WHITE) , PieceFactory.createPiece(PieceType.PAWN, Player.WHITE), PieceFactory.createPiece(PieceType.PAWN, Player.WHITE),PieceFactory.createPiece(PieceType.PAWN, Player.WHITE), PieceFactory.createPiece(PieceType.PAWN, Player.WHITE), PieceFactory.createPiece(PieceType.PAWN, Player.WHITE), PieceFactory.createPiece(PieceType.PAWN, Player.WHITE), PieceFactory.createPiece(PieceType.PAWN, Player.WHITE)},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {PieceFactory.createPiece(PieceType.PAWN, Player.BLACK), PieceFactory.createPiece(PieceType.PAWN, Player.BLACK), PieceFactory.createPiece(PieceType.PAWN, Player.BLACK), PieceFactory.createPiece(PieceType.PAWN, Player.BLACK), PieceFactory.createPiece(PieceType.PAWN, Player.BLACK), PieceFactory.createPiece(PieceType.PAWN, Player.BLACK), PieceFactory.createPiece(PieceType.PAWN, Player.BLACK), PieceFactory.createPiece(PieceType.PAWN, Player.BLACK)},
            {PieceFactory.createPiece(PieceType.ROOK, Player.BLACK), PieceFactory.createPiece(PieceType.KNIGHT, Player.BLACK), PieceFactory.createPiece(PieceType.BISHOP, Player.BLACK), PieceFactory.createPiece(PieceType.QUEEN, Player.BLACK), PieceFactory.createPiece(PieceType.KING, Player.BLACK), PieceFactory.createPiece(PieceType.BISHOP, Player.BLACK), PieceFactory.createPiece(PieceType.KNIGHT, Player.BLACK), PieceFactory.createPiece(PieceType.ROOK, Player.BLACK)}
        };
        return initialState;
    }
}
