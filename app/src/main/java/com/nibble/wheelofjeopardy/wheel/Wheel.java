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
	
	
	public Sector get_State() {
		Sector money;
		
		switch (position) {
		case 0:
			money = Sector.CATEGORY_ONE;
			break;
		case 1:
			money = Sector.CATEGORY_TWO;
			break;
		case 2:
			money = Sector.CATEGORY_THREE;
			break;
		case 3:
			money = Sector.CATEGORY_FOUR;
			break;
		case 4:
			money = Sector.CATEGORY_FIVE;
			break;
		case 5:
			money = Sector.CATEGORY_SIX;
			break;
		case 6:
			money = Sector.BANKRUPT;
			break;
		case 7:
			money = Sector.LOOSE_TURN;
			break;
		case 8:
			money = Sector.FREE_SPIN;
			break;
		case 9:
			money = Sector.PLAYERS_CHOICE;
			break;
		case 10:
			money = Sector.OPONENTS_CHOICE;
			break;
		case 11:
		default:
			money = Sector.DOUBLE_SCORE;
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