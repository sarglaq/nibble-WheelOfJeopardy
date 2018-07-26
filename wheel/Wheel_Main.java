package wheel;

public class Wheel_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Wheel ins = new Wheel();
		System.out.println("---Wheel_Main.java---");
		

		//unit test case to test for max 10 spins
		for (int i=0;i<=13;i++)
		{
			if (i < 10 )
			{
		//spin function returns a array with the first value having the position of the wheel & second the spin count
			int[] result = ins.spin();
		
		System.out.println("---Spin-Count---");
		System.out.println(result[1]);
		
		System.out.println("---Wheel-Position---");
		System.out.println(result[0]);
		System.out.println("Wheel Outcome");

		//get_state function return the value of the sector 
		System.out.println(ins.get_State());
		System.out.println("\n");
			}
			else {System.out.println("Max allowed Wheel Spin count reached");}
		}
				
		
	}

}

