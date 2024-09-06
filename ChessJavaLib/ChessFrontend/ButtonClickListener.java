
package ChessFrontend;


import java.awt.event.*;

public class ButtonClickListener implements ActionListener {
        private int row;
        private int col;
        private ChessGameGUI gui;

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
       
    public ButtonClickListener(int row, int col, ChessGameGUI gui) {
            this.row = row;
            this.col = col;
            this.gui=gui;
        }

    @Override
    public void actionPerformed(ActionEvent event) {
        gui.setSquare(row, col);
    }  
}
