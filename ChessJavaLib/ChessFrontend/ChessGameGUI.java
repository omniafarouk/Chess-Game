package ChessFrontend;

import ChessCore.*;
import Pieces.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ChessGameGUI extends JFrame {
    
    public static Square fromSquare, toSquare;
    private static JButton[][] board;
    private static ChessGame newGame = new ClassicChessGame();
    public ArrayList<Square> highlightedSquares = new ArrayList<>();
    private static ChessBoard chessBoard = newGame.getBoard();
    private Square whitekingInCheckSquare;
    private Square blackkingInCheckSquare;
    
    public static void main(String[] args)  throws Exception {
        
        SwingUtilities.invokeLater(ChessGameGUI::new);
    }
    
   public ChessGameGUI()  {
         
        this.setTitle("ChessGame");
        setLayout(new GridLayout(8, 8));
        setSize(600, 600);

        board = new JButton[8][8];
        
        JMenuBar menuBar= new JMenuBar();  
        menuBar.add(CreateUndoButton());
        setJMenuBar(menuBar);

        // Create the chessboard GUI
        for (int row = 0; row < 8; row++) 
        {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new JButton();       
                board[row][col].addActionListener(new ButtonClickListener(row, col,this));
            }
        }
        for( int row = 7 ; row >= 0 ; row--)
           for (int col = 0; col < 8; col++) {
                addSquaresToFrame();  
                // Set the background color for alternating spots
                 if ((row + col) % 2 == 0) {
                    board[row][col].setBackground(Color.BLACK);
                } else {
                    board[row][col].setBackground(Color.WHITE);
                }
                
            }
      
        setPiecesToBoard();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
   public void addSquaresToFrame(){
     if(newGame.getWhoseTurn().equals(Player.WHITE)){
          for( int row = 7 ; row >= 0 ; row--)
           for (int col = 0; col < 8; col++) {
                add(board[row][col]); }
         }
         else{
             for( int row = 0 ; row < 8 ; row++)
              for (int col = 7; col >= 0; col--) {
                add(board[row][col]); 
         }
     }
    }
     
    public static void setPiecesToBoard(){
        
        for(int row =0 ; row<8 ; row++)
        {    for(int col = 0 ; col<8 ; col++)
            {   setPieceImage(row, col, null);
                BoardFile squareFile = getBoardFile(col);
                BoardRank squareRank = getBoardRank(row);
                Square currentSquare = new Square(squareFile, squareRank);
                Piece currentPiece = chessBoard.getPieceAtSquare(currentSquare);
                if(currentPiece != null){
                    if(currentPiece.getOwner().equals(Player.WHITE)){
                        if(currentPiece instanceof King){
                          setPieceImage(row, col, "WhiteKing.png");
                        }
                        else if(currentPiece instanceof Rook){
                            setPieceImage(row, col, "WhiteRook.png");
                        }
                        else if(currentPiece instanceof Queen){
                            setPieceImage(row, col, "WhiteQueen.png");
                        }
                        else if(currentPiece instanceof Pawn){
                            setPieceImage(row, col, "WhitePawn.png"); 
                        }
                        else if(currentPiece instanceof Bishop){
                            setPieceImage(row, col, "WhiteBishop.png"); 
                        }
                        else if(currentPiece instanceof Knight){
                            setPieceImage(row, col, "WhiteKnight.png");
                        }
                    }
                    else if (currentPiece.getOwner().equals(Player.BLACK))
                    {
                        if(currentPiece instanceof King){
                          setPieceImage(row, col, "BlackKing.png");
                        }
                        else if(currentPiece instanceof Rook){
                            setPieceImage(row, col, "BlackRook.png");
                        }
                        else if(currentPiece instanceof Queen){
                            setPieceImage(row, col, "BlackQueen.png");
                        }
                        else if(currentPiece instanceof Pawn){
                            setPieceImage(row, col, "BlackPawn.png"); 
                        }
                        else if(currentPiece instanceof Bishop){
                            setPieceImage(row, col, "BlackBishop.png"); 
                        }
                        else if(currentPiece instanceof Knight){
                            setPieceImage(row, col, "BlackKnight.png");
                        }
                    }
                }
            }
        }
    } 
     
    // Method to set the piece image at a specific chessboard spot
    private static void setPieceImage(int row, int col, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        board[row][col].setIcon(new ImageIcon(scaledImage));
    }
    
    public static BoardFile getBoardFile(int x){
        BoardFile[] boardFiles = BoardFile.values();
        return boardFiles[x];
    }
    public static BoardRank getBoardRank(int y){
        BoardRank[] boardRanks = BoardRank.values();
        return boardRanks[y];
    }
    public void setSquare(int row, int col){
       JPanel panel = new JPanel();                
         if(!newGame.isGameEnded())
         {
            //no pieces selected or turn already done
             //First Click
            if(fromSquare == null && toSquare == null || fromSquare != null && toSquare != null){  
                fromSquare = new Square(getBoardFile(col), getBoardRank(row)); 
                toSquare = null;
                
               //clicked on empty square
                if(chessBoard.getPieceAtSquare(fromSquare) == null){
                    System.out.println("No piece at Square");
                    fromSquare = null;
                    return;
                }
                //clicked on the opponent's piece
                if(!newGame.getPieceAtSquare(fromSquare).getOwner().equals(newGame.getWhoseTurn())){
                    System.out.println("Wrong Player piece");
                    fromSquare = null;
                    return;
                }
                
                    java.util.List<Square> validSquares = newGame.getAllValidMovesFromSquare(fromSquare);
                    if(validSquares.isEmpty()){
                         JOptionPane.showMessageDialog(panel, "No Valid Moves For This Piece", "INVALID",
                    JOptionPane.ERROR_MESSAGE);
                         fromSquare = null;
                         return;   
                    }
                    highlightedSquares = (ArrayList<Square>) validSquares;
                    for (Square validSquare : validSquares) {
                        int validSquareCol = getCol(validSquare.getFile());
                        int validSquareRow = getRow(validSquare.getRank());
                        board[validSquareRow][validSquareCol].setBackground(Color.green);    
                    }
               //Second Click     
            }else if(fromSquare != null && toSquare == null){
                    clearHighlightedSquares(); 
                    toSquare = new Square(getBoardFile(col), getBoardRank(row)); 
                    Piece toPiece = chessBoard.getPieceAtSquare(toSquare);
                    Piece fromPiece = chessBoard.getPieceAtSquare(fromSquare);
                    
                    //Second click on another piece of his pieces -> therefore, second click set as first click 
                    if(toPiece != null && fromPiece !=null){
                      if(toPiece.getOwner().equals(Player.WHITE)&& fromPiece.getOwner().equals(Player.WHITE) ||
                            toPiece.getOwner().equals(Player.BLACK)&& fromPiece.getOwner().equals(Player.BLACK)){
                        toSquare = null;
                        fromSquare = null;
                        setSquare(row, col);
                        return;    
                     }
                    }
                    //Second click leads to pawn promotion
                    if(isPawnPromotionMove())
                    {
                        PawnPromotionWindow dialog = new PawnPromotionWindow(this);
                        dialog.setVisible(true);
                        try{
                        Move promoteMove = new Move(fromSquare, toSquare , dialog.pawnPromotedTo);
                         if(newGame.makeMove(promoteMove))
                        {
                           System.out.println("promoted move done");
                           setPiecesToBoard();
                           addSquaresToFrame();
                           kingInCheckChecker();
                        }
                       if(newGame.isGameEnded())
                         checkGameStatus();
                        }catch(Exception e){
                            System.out.println("player didn't choose a promoted piece for the pawn \npromotion move is not done ");
                        }
                    }
                    else if(newGame.makeMove(new Move(fromSquare, toSquare))){
                        System.out.println("Valid Move");
                        setPiecesToBoard();
                        addSquaresToFrame();
                        kingInCheckChecker();
                        
                    //check if game ended after move
                    if(newGame.isGameEnded())
                         checkGameStatus();

                    }
                    else{
                     JOptionPane.showMessageDialog(panel, "Please enter a Valid Move!!", "INVALID MOVE",
                    JOptionPane.ERROR_MESSAGE);
                    }
            }
        }
        else{
            checkGameStatus();
        }
    }
    private boolean isPawnPromotionMove()
      {
       Piece CurrentPiece = newGame.getPieceAtSquare(fromSquare);
       if(CurrentPiece instanceof Pawn)  
       {
           Player playerTurn = newGame.getWhoseTurn();
           if(CurrentPiece.getOwner()==playerTurn  && playerTurn==Player.BLACK && toSquare.getRank()== BoardRank.FIRST)
                return true;
           else if(CurrentPiece.getOwner()==playerTurn  && playerTurn==Player.WHITE && toSquare.getRank()== BoardRank.EIGHTH)
               return true;
       }
       return false;
      }
    
    public static int getCol(BoardFile file){
        return file.getValue();
    }
    public static int getRow(BoardRank rank){
        return rank.getValue();
    }
    public void checkGameStatus(){
        
       JPanel panel = new JPanel();
        switch(newGame.getGameStatus()){
            case WHITE_WON -> JOptionPane.showMessageDialog(panel, "WHITE WON!", "GAME OVER!",
            JOptionPane.INFORMATION_MESSAGE);
            case BLACK_WON -> JOptionPane.showMessageDialog(panel, "BLACK WON!", "GAME OVER!",
            JOptionPane.INFORMATION_MESSAGE);
            case STALEMATE -> JOptionPane.showMessageDialog(panel, "STALEMATE!", "GAME OVER!",
            JOptionPane.INFORMATION_MESSAGE);
            case INSUFFICIENT_MATERIAL -> JOptionPane.showMessageDialog(panel, "INSUFFICIENT MATERIAL!", "GAME OVER!", 
                    JOptionPane.INFORMATION_MESSAGE);    
        }
    }
   public void clearHighlightedSquares(){
        for (Square highlightedSquare : highlightedSquares) {
                        int highlightedRow = highlightedSquare.getRank().getValue();
                        int highlightedCol = highlightedSquare.getFile().getValue();
                        
                        if ((highlightedRow + highlightedCol) % 2 == 0) {
                            board[highlightedRow][highlightedCol].setBackground(Color.BLACK);
                            } else {
                                board[highlightedRow][highlightedCol].setBackground(Color.WHITE);
                            }
                    }
        highlightedSquares.clear();
    }
    public boolean kingInCheck(Player player){
        if(newGame.getGameStatus().equals(GameStatus.WHITE_UNDER_CHECK) && player.equals(Player.WHITE) ||
            newGame.getGameStatus().equals(GameStatus.BLACK_UNDER_CHECK) && player.equals(Player.BLACK))
        {
            Square kingSquare= Utilities.getKingSquare(player,chessBoard);
            board[kingSquare.getRank().getValue()][kingSquare.getFile().getValue()].setBackground(Color.red);
            if(player.equals(Player.WHITE))
               whitekingInCheckSquare = kingSquare;
            else
                blackkingInCheckSquare = kingSquare;
            return true;
        }
            return false; 
    }
    public void resetKingSquare(Player player){
        if(player.equals(Player.WHITE) && whitekingInCheckSquare!=null){
            
            int col = getCol(whitekingInCheckSquare.getFile());
            int row = getRow(whitekingInCheckSquare.getRank());
            if ((row + col) % 2 == 0) {
                    board[row][col].setBackground(Color.BLACK);
            } else {
                    board[row][col].setBackground(Color.WHITE);
            }
            whitekingInCheckSquare = null;
            
        }else if(player.equals(Player.BLACK) && blackkingInCheckSquare!=null){
            
            int col = getCol(blackkingInCheckSquare.getFile());
            int row = getRow(blackkingInCheckSquare.getRank());
            if ((row + col) % 2 == 0) {
                    board[row][col].setBackground(Color.BLACK);
            } else {
                    board[row][col].setBackground(Color.WHITE);
            }
            blackkingInCheckSquare = null;
        }
    }
    private void kingInCheckChecker(){
          if(newGame.getWhoseTurn().equals(Player.WHITE)){
                //highlight the king position if it was in check and if not nothing happens
                  //if game hasn't ended and player moved ,he must have moved a piece to remove the check 
                  // so if king in check and player moved reset the board to its un-highlighted state
                 kingInCheck(Player.WHITE);
                 resetKingSquare(Player.BLACK);
          }
             else{
                 kingInCheck(Player.BLACK);
                 resetKingSquare(Player.WHITE);
                  }
    }

    private JButton CreateUndoButton() {
        JButton undo = new JButton("Undo");
        //   JMenu FileMenu = new JMenu("File");
        //   JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener((ActionEvent e) -> {
          // JOptionPane.showMessageDialog(null, "move undo" ,"Undo Move",JOptionPane.INFORMATION_MESSAGE);
           if(newGame.UndoMove()){
                chessBoard = newGame.getBoard();
                setPiecesToBoard();
                addSquaresToFrame();
                kingInCheckChecker();
                System.out.println("undo true , " + newGame.getWhoseTurn());
                System.out.println(newGame.getGameStatus());
           }
           else
           System.out.println("undo false , " + newGame.getWhoseTurn());
           
       });
       return undo;
    }
}




