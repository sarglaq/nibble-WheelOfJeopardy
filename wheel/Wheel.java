package wheel;

import java.util.Random;

/**
 * This class models a wheel for a guessing game such as Wheel of Fortune. Each
 * spin of the wheel has 12 possible outcomes, as mentioned in the switch case
 *  0 - 5: gives the sector randomization from 1 to 6
 * 
 * 
 * 
 * 
 */

public class Wheel {

	/**
	 * Return value for a Bankrupt spin
	 */
	public static final int BANKRUPT = -1;
	

	/**
	 * Wheel spin counter.
	 */
	
	static int COUNTER;
		
	/**
	 * Random number for a wheel spin
	 */
	private Random generator;

	/**
	 * Current position of the wheel. 0 - 12
	 */
	private int position;

	/**
	 * Constructs a wheel using a default instance of Random.
	 */
	
	int spincount = 0;
	
	
	public Wheel() {
		generator = new Random();
		position = BANKRUPT;
	}

	/**
	 * Constructs a Wheel whose random number generator is initialized with the
	 * given value as a seed.
	 * 
	 * @param seed
	 *            seed for the random number generator.
	 */
	public Wheel(int seed) {
		generator = new Random(seed);
		position = BANKRUPT;
	}

	/**
	 * Returns the dollar amount for the current wheel position, using the
	 * values 0 for "Lose a Turn" and -1 for "Bankrupt".
	 * 
	 * @return dollar amount for the current wheel position
	 */
	
	
	public String get_State() {
		String money = "";
		
		switch (position) {
		case 0:
			money = "Sector 1";
			break;
		case 1:
			money = "Sector 2";
			break;
		case 2:
			money = "Sector 3";
			break;
		case 3:
			money = "Sector 4";
			break;
		case 4:
			money = "Sector 5";
			break;
		case 5:
			money = "Sector 6";
			break;
		case 6:
			money = "BANKRUPT";
			break;
		case 7:
			money = "LOSE_A_TURN";
			break;
		case 8:
			money = "FREE_TURN";
			break;
		case 9:
			money = "PLAYERS_CHOICE";
			break;
		case 10:
			money = "OPPONENTS_CHOICE";
			break;
		case 11:
			money = "DOUBLE_YOUR_SCORE";
			break;
		}
		return money;
	}
	
	

	/**
	 * Spins the wheel , updates the wheel position & returns the spin count.
	 */
	public int[] spin() {
		position = generator.nextInt(12);
		//System.out.println("Position:");
		//System.out.println(position);
		//System.err.println("Passing checkpoint " + COUNTER++);
		COUNTER++;
		int position_counter[] = new int[2];
		position_counter[0] = position;
		position_counter[1] = COUNTER;
		return position_counter;
		
	}
	
			
}