import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConnectFourGUI {
	private JFrame frame;
	private JLabel status;
	private JLabel[][] slots;
	private int currentPlayer;
	public ConnectFourGUI() {
		frame = new JFrame("Connect Four");
		JPanel panel= new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(6,7));
		slots = new JLabel[7][6];
		for (int row=5; row>=0; row--) {
			for (int column=0; column<7; column++) {
				slots[column][row] = new JLabel();
				// slots[column][row].setFont(new Font("SansSerif", Font.BOLD, 18));
				slots[column][row].setHorizontalAlignment(SwingConstants.CENTER);
				slots[column][row].setBorder(new LineBorder(Color.green));
				panel.add(slots[column][row]);
			}
		}
		frame.add(panel,BorderLayout.CENTER);
		frame.setSize(700,600);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent we){
			  System.exit(0);
			  }
		});
		status= new JLabel("Please click on any column to begin playing.(Player: Red, Bot: Yellow).");
		frame.add(status,BorderLayout.PAGE_END);
		currentPlayer = 1;
	}
	public void addListener(ConnectFourListener listener) { 
		for (int row=0; row<6; row++) { 
			for (int column=0; column<7; column++) { 
				slots[column][row].addMouseListener(listener); 
			} 
		} 
	} 
	public int getColumn(JLabel label) { 
		int returnColumn = -1; 
		for (int row=0; row<6; row++) { 
			for (int column=0; column<7; column++) { 
				if (slots[column][row] == label) { 
					returnColumn = column; 
				} 
			} 
		} 
		return returnColumn; 
	} 
	public void set(int column, int row) {
		// slots[column][row].setText("*" + currentPlayer + "*");
		if (currentPlayer == 1) {
			slots[column][row].setIcon(new ImageIcon("redcircle.png"));
			status.setText("Bot's turn");
		}
		else {
			slots[column][row].setIcon(new ImageIcon("yellowcircle.png"));
			status.setText("Player's turn.");
		}
		currentPlayer = (currentPlayer%2)+1;
	}
	public void setStatus(String s){
		status.setText(s);
	}
	public static void main(String[] args) {
		ConnectFour game = new ConnectFour();
		ConnectFourGUI gui = new ConnectFourGUI();
		ConnectFourListener listener = new ConnectFourListener(game, gui);
	}


}
