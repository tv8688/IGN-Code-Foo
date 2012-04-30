/**
* The ConnectFour class.
*
* This class represents a Connect Four (TM)
* game, which allows two players to drop
* checkers into a grid until one achieves
* four checkers in a straight line.
*/
public class ConnectFour implements Cloneable{
	// the grid used for storing the game layout.
	private int[][] grid;
	// the player whose turn it is.
	private int currentPlayer;
	// Most recent move row and column
	private int row;
	private int col;
	/**
	* The ConnectFour constructor.
	*
	* Creates and initializes the grid for the
	* Connect Four game.
	*/
	public ConnectFour() {
		// create the grid
		grid = new int[7][6];
		// initialize the grid
		for (int row=0; row<6; row++) {
			for (int column=0; column<7; column++) {
			// set the position to a default value
				grid[column][row] = 0;
			}
		}
		// set the first move to Player 1
		currentPlayer = 1;
		row=-1;
		col=-1;
	}
	public ConnectFour(int [][]source_grid, int source_currentPlayer) {
		// create the grid
		grid = new int[7][6];
		// initialize the grid
		for (int row=0; row<6; row++) {
			for (int column=0; column<7; column++) {
			// set the position to a default value
				grid[column][row] = source_grid[column][row];
			}
		}
		// set the first move to Player 1
		currentPlayer = source_currentPlayer;
		row=-1;
		col=-1;
	} 

	/**
	* The drop method.
	*
	* Drop a checker into the specified column,
	* and return the row that the checker lands on.
	*/
	int drop(int column) {
		if (hasWon()) {
			return -1;
		}
		if (hasDraw()){
			return -2;
		}
		int row = 0;
		for ( ; row<6 && grid[column][row]!=0; row++) { };
			if (row==6) {
			// if the row is 6, it went through all 6 rows
			// of the grid, and couldn't find an empty one.
			// Therefore, return false to indicate that this
			// drop operation failed.
				return -3;
		}
		// fill the row of that column with a checker.
		grid[column][row] = currentPlayer;
		this.row=row;
		this.col=column;
		// alternate the players
		currentPlayer = (currentPlayer%2)+1;
		return row;
	}
	/**
	* The possible_drop method
	*
	* Returns true if it is still possible to drop in the specified column
	*/
	public boolean possible_drop(int column){
		if (grid[column][5]!=0) return false;
		return true;
	}
	/**
	* The toString method
	*
	* Returns a String representation of this
	* Connect Four (TM) game.
	*/
	public String toString() {
		String returnString = "";
		for (int row=5; row>=0; row--) {
			for (int column=0; column<7; column++) {
				returnString = returnString + grid[column][row];
			}
			returnString = returnString + "\n";
		}
		return returnString;
	}
	/**
	* The hasDraw method.
	*
	* This method returns true if the game is drawn. 
	* The game is drawn when there are no more places to play.
	*/	
	public boolean hasDraw(){
		for(int i=0;i<7;i++)
			if(grid[i][5]==0)return false;
		return true;
	}
	/**
	* The hasWon method.
	*
	* This method returns true if one of the
	* players has won the game.
	*/
	public boolean hasWon() {
		boolean status = false;
		// check for a horizontal win
		for (int row=0; row<6; row++) {
			for (int column=0; column<4; column++) {
				if (grid[column][row] != 0 &&
				grid[column][row] == grid[column+1][row] &&
				grid[column][row] == grid[column+2][row] &&
				grid[column][row] == grid[column+3][row]) {
					status = true;
				}
			}
		}
		// check for a vertical win
		for (int row=0; row<3; row++) {
			for (int column=0; column<7; column++) {
				if (grid[column][row] != 0 &&
				grid[column][row] == grid[column][row+1] &&
				grid[column][row] == grid[column][row+2] &&
				grid[column][row] == grid[column][row+3]) {
					status = true;
				}
			}
		}
		// check for a diagonal win (positive slope)
		for (int row=0; row<3; row++) {
			for (int column=0; column<4; column++) {
				if (grid[column][row] != 0 &&
				grid[column][row] == grid[column+1][row+1] &&
				grid[column][row] == grid[column+2][row+2] &&
				grid[column][row] == grid[column+3][row+3]) {
					status = true;
				}
			}
		}
		// check for a diagonal win (negative slope)
		for (int row=3; row<6; row++) {
			for (int column=0; column<4; column++) {
				if (grid[column][row] != 0 &&
				grid[column][row] == grid[column+1][row-1] &&
				grid[column][row] == grid[column+2][row-2] &&
				grid[column][row] == grid[column+3][row-3]) {
					status = true;
				}
			}
		}
		return status;
	}
	/**
	* The minimax method.
	*
	* This modified MINIMAX method returns the minimal risks move 
	* for a specified column move.
	* Since MINIMAX algorithm has to reach the terminal state to return,
	* a modified version is used that will return a heuristic value instead
	* when the given depth is reached.
	*/
	public int minimax(int col, int depth){
		ConnectFour this_move=new ConnectFour(this.grid,this.currentPlayer);
		//Make the move
		this_move.drop(col);
		//If the game terminates, return maximal value
		if (this_move.hasWon()){
			return Integer.MAX_VALUE;
		}
		//If the depth is reached, calculate the heuristic value 
		if (depth==0){
			return this_move.heuristic();
		}
		int value=Integer.MAX_VALUE;
		//Consider all possible opponent moves and take the minimum result
		for (int i=0;i<7;i++){
			if(this_move.possible_drop(i)){
				int result=-1*this_move.minimax(i,depth-1);
				if(value>result)value=result;
			}
		}
		return value;
	}
	/**
	* The hasWon method.
	*
	* This method returns the heuristic value of a move
	* which has not reach the terminal state.
	* The heuristic is calculated by 
	* counting the number of checkers 
	* belong to the a player in various strips of 4 
	*/
	public int heuristic(){
		int heuristic=0;
		int count=0;
		//Calculate row heuristic
		for(int i=0;i<4;i++){
			count=0;
			for(int j=0;j<4;j++){
				if(grid[i+j][this.row]==this.currentPlayer){
					count=0;
					break;
				}
				else if (grid[i+j][this.row]==(currentPlayer%2)+1){
					count++;
				}
			}
			heuristic+=count_value(count);
		}
		//Calculate column heuristic
		count=0;
		int r=0;
		for(;r<4;r++){
			if(this.row-r<0)break;
			else if(grid[this.col][this.row-r]==this.currentPlayer)break;
			else if (grid[this.col][this.row-r]==(currentPlayer%2)+1){
				count++;
			}			
		}
		if((5-this.row)+count>=4)heuristic+=count_value(count);
		//Calculate diagonal heuristic
		int rc=this.row+this.col;
		int diag_len=(rc<6)? (rc+1):(11-rc+1);
		for(int i=0;i<=diag_len-4;i++){
			count=0;
			if(rc<6)
				for(int j=0;j<4;j++){
					if(grid[rc-j-i][j+i]==this.currentPlayer){
						count=0;
						break;
					}
					else if (grid[rc-j-i][j+i]==(currentPlayer%2)+1){
						count++;
					}
				}
			else
				for(int j=0;j<4;j++){
					if(grid[i+j+1][5-j-i]==this.currentPlayer){
						count=0;
						break;
					}
					else if (grid[i+j+1][5-j-i]==(currentPlayer%2)+1){
						count++;
					}
				}
			heuristic+=count_value(count);
		}
		return heuristic;
	}
	/**
	* The count_value method.
	*
	* This method calculate the value of a strip of 4 
	* based on the number of checkers of the corresponding player in it.
	* For example, a row of 4 with one checker gets score 1.
	*/
	public static int count_value(int count){
		switch(count){
			case 0: return 0; 
			case 1: return 1;
			case 2: return 4;
			case 3: return 32;
			default:
				System.out.println("Error count value: -1");
				return -1;
		}
	}
}

