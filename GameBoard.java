/*Game board will be the class with most of the effective methods. The game will be played on the board,
 * so all of the methods related to moving and the logic behind legal moves and which discs to be flipped
 * over will be figured out in this class. Also, we should be able to count the number of discs, figure out
 * when the game is over, print the board, figure out/print legal moves, etc.
 */

public class GameBoard {
	
	private GameDisc[][] board;
	private int size;
	private char turnToMove;
	
	//default constructor
	public GameBoard() {
		size = 8;
		board = new GameDisc[8][8]; //board will be a 2D int array of GameDiscs
		turnToMove = 'B'; //Black moves first
		setUpInitialBoard();
	}
	
	public GameBoard(int sizeIn) {
		if (! (sizeIn % 2 == 0) && sizeIn >= 4 && sizeIn <= 16) //we want size to be even, and between 4 and 16
			size = 8;
		else
			size = sizeIn;
		board = new GameDisc[size][size];
		turnToMove = 'B';
		setUpInitialBoard();
	}
	
	public void setUpInitialBoard() {
		
		GameDisc initial1 = new GameDisc('W');
		GameDisc initial2 = new GameDisc('B');
		GameDisc initial3 = new GameDisc('B');
		GameDisc initial4 = new GameDisc('W');
		
		//sets up the four discs in the center of the board
		board[size / 2 - 1][size / 2 - 1] = initial1;
		board[size / 2 - 1][size / 2] = initial2;
		board[size / 2][size / 2 - 1] = initial3;
		board[size / 2][size / 2] = initial4;
	}
	
	public void printBoard() {
		for (int i = - 1; i < size; i++) {
			for (int j = - 1; j < size; j++) {
				//print the coordinates on the side of the board
				if (i < 0 && j < 0) {
					System.out.print("  ");
				}
				if (i < 0 && !(j < 0)) {
					System.out.print(j + " ");
				}
				if (!(i < 0) && j < 0) {
					System.out.print(i + " ");
				}
				
				//and print the game discs, or the null spaces
				if (i >= 0 && j >= 0 && board[i][j] != null)
					System.out.print((board[i][j]).getColor() + " ");
				else if (i >= 0 && j >= 0 && board[i][j] == null)
					System.out.print("_ ");
			}
			System.out.println();
		}
	}
	
	//switches player's turn after they place down a disc
	public void switchTurn() {
		if (turnToMove == 'B')
			turnToMove = 'W';
		else if (turnToMove == 'W')
			turnToMove = 'B';
	}
	
	//places a disc on the gameBoard for a player's turn
	public void placeDisc(int row, int col) {
		if (isLegal(row, col, turnToMove)) {
			if (checkHorizontalLeftLegal(row, col, turnToMove))
				turnOverHorizontalLeft(row, col);
			if (checkHorizontalRightLegal(row, col, turnToMove))
				turnOverHorizontalRight(row, col);
			if (checkVerticalUpLegal(row, col, turnToMove))
				turnOverVerticalUp(row, col);
			if (checkVerticalDownLegal(row, col, turnToMove))
				turnOverVerticalDown(row, col);
			if (checkDiagonalNWLegal(row, col, turnToMove))
				turnOverDiagonalNW(row, col);
			if (checkDiagonalNELegal(row, col, turnToMove))
				turnOverDiagonalNE(row, col);
			if (checkDiagonalSWLegal(row, col, turnToMove))
				turnOverDiagonalSW(row, col);
			if (checkDiagonalSELegal(row, col, turnToMove))
				turnOverDiagonalSE(row, col);
			
			GameDisc placedDisc = new GameDisc(turnToMove);
			board[row][col] = placedDisc;
			
			switchTurn();
		}
		else
			System.out.println("Sorry, that's not a legal move. Please try again.");
	}
	
	//a submethod of placeDisc. Checks if any discs horizontal with the placed disc should be turned over, and does so.
	public void turnOverHorizontalLeft(int row, int col) {
		//turn over tiles to the left
		if (col > 0) {
			if ((board[row][col - 1]) != null && (board[row][col - 1]).getColor() != turnToMove) {
				int j = col - 1;
				while (j >= 0 && board[row][j] != null && (board[row][j]).getColor() != turnToMove) {
					board[row][j].setColor(turnToMove);
					j--;
				}
			}
		}
	}
	public void turnOverHorizontalRight(int row, int col) {		
		//turn over tiles to the right
		if (col < size - 1) {
			if ((board[row][col + 1]) != null && (board[row][col + 1]).getColor() != turnToMove) {
				int j = col + 1;
				while (j < size && board[row][j] != null && (board[row][j]).getColor() != turnToMove) {
					board[row][j].setColor(turnToMove);
					j++;
				}
			}
		}		
	}
	
	//a submethod of placeDisc. Checks if any discs vertical with the placed disc should be turned over, and does so.
	public void turnOverVerticalUp(int row, int col) {
		//turn over tiles above
		if (row > 0) {
			if ((board[row - 1][col]) != null && (board[row - 1][col]).getColor() != turnToMove) {
				int i = row - 1;
				while (i >= 0 && board[i][col] != null && (board[i][col]).getColor() != turnToMove) {
					board[i][col].setColor(turnToMove);
					i--;
				}
			}
		}
	}
	public void turnOverVerticalDown(int row, int col) {
		//turn over tiles below
		if (row < size - 1) {
			if ((board[row + 1][col]) != null && (board[row + 1][col]).getColor() != turnToMove) {
				int i = row + 1;
				while (i < size && board[i][col] != null && (board[i][col]).getColor() != turnToMove) {
					board[i][col].setColor(turnToMove);
					i++;
				}
			}
		}
		
	}
	
	//a submethod of placeDisc. Checks if any discs diagonal with the placed disc should be turned over, and does so.
	public void turnOverDiagonalNW(int row, int col) {
		//turn over tiles above and to the left
		if (row > 0 && col > 0) {
			if ((board[row - 1][col - 1]) != null && (board[row - 1][col - 1]).getColor() != turnToMove) {
				int i = row - 1;
				int j = col - 1;
				while (i >= 0 && j >= 0 && board[i][j] != null && (board[i][j]).getColor() != turnToMove) {
					board[i][j].setColor(turnToMove);
					i--;
					j--;
				}
			}
		}
	}
	public void turnOverDiagonalNE(int row, int col) {
		//turn over tiles above and to the right
		if (row > 0 && col < size - 1) {
			if ((board[row - 1][col + 1]) != null && (board[row - 1][col + 1]).getColor() != turnToMove) {
				int i = row - 1;
				int j = col + 1;
				while (i >= 0 && j < size && board[i][j] != null && (board[i][j]).getColor() != turnToMove) {
					board[i][j].setColor(turnToMove);
					i--;
					j++;
				}
			}
		}
	}
	public void turnOverDiagonalSW(int row, int col) {
		//turn over tiles below and to the left
		if (row < size - 1 && col > 0) {
			if ((board[row + 1][col - 1]) != null && (board[row + 1][col - 1]).getColor() != turnToMove) {
				int i = row + 1;
				int j = col - 1;
				while (i < size && j >= 0 && board[i][j] != null && (board[i][j]).getColor() != turnToMove) {
					board[i][j].setColor(turnToMove);
					i++;
					j--;
				}
			}
		}
	}
	public void turnOverDiagonalSE(int row, int col) {
		//turn over tiles below and to the right
		if (row < size - 1 && col < size - 1) {
			if ((board[row + 1][col + 1]) != null && (board[row + 1][col + 1]).getColor() != turnToMove) {
				int i = row + 1;
				int j = col + 1;
				while (i < size && j < size && board[i][j] != null && (board[i][j]).getColor() != turnToMove) {
					board[i][j].setColor(turnToMove);
					i++;
					j++;
				}
			}
		}
	}
	
	public boolean isLegal(int row, int col, char color) {
		boolean horizontalLeftLegal = checkHorizontalLeftLegal(row, col, color);
		boolean horizontalRightLegal = checkHorizontalRightLegal(row, col, color);
		boolean horizontalLegal = (horizontalLeftLegal || horizontalRightLegal);
		
		boolean verticalUpLegal = checkVerticalUpLegal(row, col, color);
		boolean verticalDownLegal = checkVerticalDownLegal(row, col, color);
		boolean verticalLegal = (verticalUpLegal || verticalDownLegal);
		
		boolean diagonalNWLegal = checkDiagonalNWLegal(row, col, color);
		boolean diagonalNELegal = checkDiagonalNELegal(row, col, color);
		boolean diagonalSWLegal = checkDiagonalSWLegal(row, col, color);
		boolean diagonalSELegal = checkDiagonalSELegal(row, col, color);
		boolean diagonalLegal = (diagonalNWLegal || diagonalNELegal || diagonalSWLegal || diagonalSELegal);
		
		return (horizontalLegal || verticalLegal || diagonalLegal);
	}
	
	public boolean checkHorizontalLeftLegal(int row, int col, char color) { //color is the color of disc being placed
		//start with the left of the coordinate; if there is enough space and the disc to the left exists and is opposite color...
		if (board[row][col] != null)
			return false;
			
		if (col - 1 > 0 && (board[row][col - 1]) != null && (board[row][col - 1]).getColor() != color) {
			for (int j = col - 2; j >= 0; j--) {
				if ((board[row][j]) == null)
					return false;
				if ((board[row][j]).getColor() == color)
					return true;
			}
		}
		return false;
	}
	public boolean checkHorizontalRightLegal(int row, int col, char color) {
		//now go to the right of the coordinate
		if (board[row][col] != null)
			return false;
		
		if (col + 1 < size - 1 && (board[row][col + 1]) != null && (board[row][col + 1]).getColor() != color) {
			for (int j = col + 2; j < size; j++) {
				if ((board[row][j]) == null)
					return false;
				if ((board[row][j]).getColor() == color)
					return true;
			}
		}
		//if it hasn't returned true, then by horizontal standards, this move is not legal
		return false;
	}
	
	public boolean checkVerticalUpLegal(int row, int col, char color) {
		if (board[row][col] != null)
			return false;
		
		//start with looking above the coordinate
		if (row - 1 > 0 && (board[row - 1][col]) != null && (board[row - 1][col]).getColor() != color) {
			for (int i = row - 2; i >= 0; i--) {
				if ((board[i][col]) == null)
					return false;
				if ((board[i][col]).getColor() == color)
					return true;
			}
		}
		return false;
	}
	public boolean checkVerticalDownLegal(int row, int col, char color) {
		//now go to the right of the coordinate
		if (board[row][col] != null)
			return false;
		
		if (row + 1 < size - 1 && (board[row + 1][col]) != null && (board[row + 1][col]).getColor() != color) {
			for (int i = row + 2; i < size; i++) {
				if ((board[i][col]) == null)
					return false;
				if ((board[i][col]).getColor() == color)
					return true;
			}
		}
		//if it hasn't returned true, then by horizontal standards, this move is not legal
		return false;
	}
	
	public boolean checkDiagonalNWLegal(int row, int col, char color) {
		if (board[row][col] != null)
			return false;
		
		//start with looking at up and to the left
		if (row - 1 > 0 && col - 1 > 0 && (board[row - 1][col - 1]) != null && (board[row - 1][col - 1]).getColor() != color) {
			int i = 2;
			while (row - i >= 0 && col - i >= 0 && (board[row - i][col - i]) != null) {
				if ((board[row - i][col - i]) == null)
					return false;
				if ((board[row - i][col - i]).getColor() == color)
					return true;
				i++;
			}
		}
		return false;
	}
	public boolean checkDiagonalNELegal(int row, int col, char color) {
		//now, start looking at up and to the right
		if (board[row][col] != null)
			return false;
		
		if (row - 1 > 0 && col + 1 < size - 1 && (board[row - 1][col + 1]) != null && (board[row - 1][col + 1]).getColor() != color) {
			int i = 2;
			while (row - i >= 0 && col + i < size && (board[row - i][col + i]) != null) {
				if ((board[row - i][col + i]) == null)
					return false;
				if ((board[row - i][col + i]).getColor() == color)
					return true;
				i++;
			}
		}
		return false;
	}
	public boolean checkDiagonalSWLegal(int row, int col, char color) {
		//now, start looking at down and to the left
		if (board[row][col] != null)
			return false;
		
		if (row + 1 < size - 1 && col - 1 > 0 && (board[row + 1][col - 1]) != null && (board[row + 1][col - 1]).getColor() != color) {
			int i = 2;
			while (row + i < size && col - i >= 0 && (board[row + i][col - i]) != null) {
				if ((board[row + i][col - i]) == null)
					return false;
				if ((board[row + i][col - i]).getColor() == color)
					return true;
				i++;
			}
		}
		return false;
	}
	public boolean checkDiagonalSELegal(int row, int col, char color) {
		//finally, look at down and to the right
		if (board[row][col] != null)
			return false;
		
		if (row + 1 < size - 1 && col + 1 < size - 1 && (board[row + 1][col + 1]) != null && (board[row + 1][col + 1]).getColor() != color) {
			int i = 2;
			while (row + i < size && col + i < size && (board[row + i][col + i]) != null) {
				if ((board[row + i][col + i]) == null)
					return false;
				if ((board[row + i][col + i]).getColor() == color)
					return true;
				i++;
			}
		}
		//reach here if none of the diagonal conditions were met
		return false;
	}
	
	public int[][] getLegalMoves(){
		int[][] legalMoves = new int[20][2]; //should only really be 20 max legal moves
		int count = 0;
		legalMoves[0][0] = -1; //if [0][0] is still -1 when called, then there are no legal moves.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isLegal(i, j, turnToMove)) {
					legalMoves[count][0] = i;
					legalMoves[count][1] = j;
					count++;
				}						
			}
		}
		return legalMoves;		//legalMoves is a 2D array of legal moves via their coordinates
	}
	
	public void printLegalMoves() {
		int[][] legalMoves = new int[20][2]; //should only really be 20 max legal moves
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isLegal(i, j, turnToMove)) {
					legalMoves[count][0] = i;
					legalMoves[count][1] = j;
					count++;
				}						
			}
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(legalMoves[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean gameOver() {
		int[][] test = this.getLegalMoves();
		return (test[0][0] == -1);
	}
	
	public String determineWinner() {
		int blackDiscs = countBlack();
		int whiteDiscs = countWhite();
		
		if (blackDiscs > whiteDiscs)
			return "Black wins! Score: Black: " + blackDiscs + " White: " + whiteDiscs;
		else if (whiteDiscs > blackDiscs)
			return "White wins! Score: Black: " + blackDiscs + " White: " + whiteDiscs;
		else
			return "It's a tie! Score: Black: " + blackDiscs + " White: " + whiteDiscs;
	}
	
	public int countBlack() {
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if ((board[i][j]) != null && (board[i][j]).getColor() == 'B')
					count++;
			}			
		}
		return count;
	}
	
	public int countWhite() {
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if ((board[i][j])!= null && (board[i][j]).getColor() == 'W')
					count++;
			}			
		}
		return count;
	}
	
	public char getTurnToMove(){
		return turnToMove;
	}
}
