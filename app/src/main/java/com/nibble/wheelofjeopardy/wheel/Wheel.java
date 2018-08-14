package com.nibble.wheelofjeopardy.wheel;

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
	
	
	public Sector get_State() {
		Sector sector;
		
		switch (position) {
		case 0:
			sector = Sector.CATEGORY_ONE;
			break;
		case 1:
			sector = Sector.CATEGORY_TWO;
			break;
		case 2:
			sector = Sector.CATEGORY_THREE;
			break;
		case 3:
			sector = Sector.CATEGORY_FOUR;
			break;
		case 4:
			sector = Sector.CATEGORY_FIVE;
			break;
		case 5:
			sector = Sector.CATEGORY_SIX;
			break;
		case 6:
			sector = Sector.BANKRUPT;
			break;
		case 7:
			sector = Sector.LOOSE_TURN;
			break;
		case 8:
			sector = Sector.FREE_SPIN;
			break;
		case 9:
			sector = Sector.PLAYERS_CHOICE;
			break;
		case 10:
			sector = Sector.OPPONENTS_CHOICE;
			break;
		case 11:
		default:
			sector = Sector.DOUBLE_SCORE;
			break;
		}
		return sector;
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
	
	public int getSpinCount() {
		return COUNTER;
	}

	public void reset() {
	    COUNTER = 0;
	    position = BANKRUPT;
    }
}