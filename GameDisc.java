//Game Disc will be placed on the board. Board will be a 2D array of GameDiscs. 

public class GameDisc {
	private char color;
	
	//default constructor
	public GameDisc() {
		color = 'B';
	}
	
	public GameDisc(char colorIn) {
		if (colorIn != 'B' && colorIn!= 'W') {
			color = 'B';
			System.out.println("ERROR: Unidentified color entered for disc");
		}
		else
			color = colorIn;
	}
	
	//to be used for flipping over already existing discs when a new one is placed
	public void setColor(char colorIn) {
		if (colorIn != 'B' && colorIn!= 'W') {
			System.out.println("ERROR: Unidentified color entered for disc");
		}
		else
			color = colorIn;
	}
	
	public char getColor() {
		return color;
	}

}
