
package ChessFrontend;

import ChessCore.PawnPromotion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PawnPromotionWindow extends JDialog {
    
     PawnPromotion pawnPromotedTo;
    
      public PawnPromotionWindow(JFrame parentFrame)
      {
            super(parentFrame, "Pawn Promotion", true);

            // Create buttons for promotion options
            JButton queenButton = new JButton("Promote to Queen");
            JButton rookButton = new JButton("Promote to Rook");
            JButton bishopButton = new JButton("Promote to Bishop");
            JButton knightButton = new JButton("Promote to Knight");

            //actions performed of each button
            queenButton.addActionListener(new ButtonListener());
            rookButton.addActionListener(new ButtonListener());
            bishopButton.addActionListener(new ButtonListener());
            knightButton.addActionListener(new ButtonListener());

            // Create a JPanel to hold the buttons
            JPanel panel = new JPanel();
            panel.add(queenButton);
            panel.add(rookButton);
            panel.add(bishopButton);
            panel.add(knightButton);
            
            getContentPane().add(panel);
            
            setSize(400, 120);
            setLocationRelativeTo(parentFrame); 
    }
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();
            String buttonValue = sourceButton.getText();
            switch(buttonValue){
                case "Promote to Queen" -> pawnPromotedTo = PawnPromotion.Queen;
                case "Promote to Rook" -> pawnPromotedTo = PawnPromotion.Rook;
                case "Promote to Knight" -> pawnPromotedTo = PawnPromotion.Knight;
                case "Promote to Bishop" -> pawnPromotedTo = PawnPromotion.Bishop;
                default -> pawnPromotedTo = PawnPromotion.None;
            }
                    JOptionPane.showMessageDialog(null, "New piece created: " + buttonValue);
                    dispose();
                        
        }
    }
}
