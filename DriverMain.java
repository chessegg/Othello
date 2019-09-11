/* Craig Hilby CS 2336.004
 * Extra credit included: diagonal move, list of legal moves
 * 
 * Run the program on a java application. The scanner will ask what board size you want, and what color you want.
 * When it is your move, enter the coordinates (separated by a space) of where you want to place your disc.
 */


import java.util.Scanner;

public class DriverMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//has to be an even number so that there are 4 middle squares on the board.
		//4x4 should be minimum size, and anything beyond 16x16 is a bit too extreme.
		System.out.print("Please enter an even number between 4 and 16 for the board size: ");
		int boardSize = scan.nextInt();
		GameBoard playingBoard = new GameBoard(boardSize);
		
		System.out.print("Please choose whether to be (B)lack or (W)hite: ");
		String playerColorIn = scan.next();
		char playerColor = playerColorIn.charAt(0); //playerColor should now just be either 'B' or 'W'
		
		playingBoard.printBoard(); //prints the original starting board
		
		while (!playingBoard.gameOver()) {
			System.out.print(playingBoard.getTurnToMove());
			System.out.println(" to move. Place a disc. Legal moves: ");
			playingBoard.printLegalMoves();
			if (playingBoard.getTurnToMove() == playerColor) {
				System.out.print("Enter two ints, separated by a space, for the coordinates of your move: ");
				int rowCoordinate = scan.nextInt();
				int colCoordinate = scan.nextInt();
				playingBoard.placeDisc(rowCoordinate, colCoordinate);
				playingBoard.printBoard();
				System.out.println("Score: Black: " + playingBoard.countBlack() +", White: " + playingBoard.countWhite());
			}
			else {  //AI's move
				int[][] legalMovesArray = playingBoard.getLegalMoves();
				int rowCoordinate = legalMovesArray[0][0];
				int colCoordinate = legalMovesArray[0][1];
				playingBoard.placeDisc(rowCoordinate, colCoordinate); //just plays the first move on the top of the legal moves list
				playingBoard.printBoard();
				System.out.println("AI move: " + rowCoordinate + " " + colCoordinate);
				System.out.println("Score: Black: " + playingBoard.countBlack() +", White: " + playingBoard.countWhite());
			}
			
		}
		int finalBlackScore = playingBoard.countBlack();
		int finalWhiteScore = playingBoard.countWhite();
		
		System.out.println("Game over! Final score: Black: " + finalBlackScore + ", White: " + finalWhiteScore);
		if (playerColor == 'B') {
			if (finalBlackScore > finalWhiteScore)
				System.out.print("You win!");
			else if (finalBlackScore < finalWhiteScore)
				System.out.print("AI wins!");
			else
				System.out.print("It's a tie!");
		}
		else if (playerColor == 'W') {
			if (finalBlackScore < finalWhiteScore)
				System.out.print("You win!");
			else if (finalBlackScore > finalWhiteScore)
				System.out.print("AI wins!");
			else
				System.out.print("It's a tie!");
		}
	}

}
