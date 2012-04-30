import javax.swing.*;
import java.awt.event.*;
public class ConnectFourListener implements MouseListener {
	ConnectFourGUI gui;
	ConnectFour game;
	public ConnectFourListener(ConnectFour game, ConnectFourGUI gui) {
		this.game = game;
		this.gui = gui;
		gui.addListener(this);
	}
	public void mouseClicked(MouseEvent event) {
		JLabel label = (JLabel) event.getComponent();
		int column = gui.getColumn(label);
		//Player's move
		int row = game.drop(column);
		if (row != -1 && row !=-2 && row!=-3) {
			gui.set(column, row);
			if(game.hasWon())
				gui.setStatus("Player won. Congratulations !!!");
			else if(game.hasDraw())
				gui.setStatus("Draw.");
			else{
				//Calculate bot's move
				int min_value=Integer.MAX_VALUE;
				int min_col=-1;
				for(int i=0;i<7;i++){
					if(game.possible_drop(i)){
						int result=-1*game.minimax(i, 3);
						if(min_value>=result){
							min_value=result;
							min_col=i;
						}
					}
				}
				
				if (min_col!=-1)row=game.drop(min_col);
				else System.out.println("Error");
				if (row != -1 && row != -2 && row!=-3) {
					gui.set(min_col, row);
					if(game.hasWon())
						gui.setStatus("Bot won.");
					if(game.hasDraw())
						gui.setStatus("Draw.");
				}
			}
		}

	}
	public void mousePressed(MouseEvent event) {
	}
	public void mouseReleased(MouseEvent event) {
	}
	public void mouseEntered(MouseEvent event) {
	}
	public void mouseExited(MouseEvent event) {
	}
}

