
package DesignPatterns;


import ChessCore.*;
import Pieces.*;

public class PieceFactory {
    public static Piece createPiece(PawnPromotion promote, Player playerPromoting){
         switch (promote) {
                    case Queen -> {
                        return new Queen(playerPromoting);
            }
                    case Rook -> {
                        return new Rook(playerPromoting);
            }
                    case Knight -> {
                        return new Knight(playerPromoting);
            }
                    case Bishop -> {
                        return new Bishop(playerPromoting);
            }
                    case None -> {
                        throw new RuntimeException("Pawn moving to last rank without promotion being set. This should NEVER happen!");
            }
                    default -> {
                        throw new RuntimeException("Pawn moving to last rank without promotion being set. This should NEVER happen!");
            }
        }                
    } 
    public static Piece createPiece(PieceType piece, Player player){
         switch (piece) {
                    case QUEEN -> {
                        return new Queen(player);
            }
                    case ROOK -> {
                        return new Rook(player);
            }
                    case KNIGHT -> {
                        return new Knight(player);
            }
                    case BISHOP -> {
                        return new Bishop(player);
            }
                    case KING ->{
                        return new King(player);
            }
                    case PAWN ->{
                        return new Pawn(player);
            }
                    default->{
                        throw new RuntimeException("No piece created in this case");
            }       
         
        }
    }
}