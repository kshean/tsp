package tsp;

public class Main {

	public static void main(String[] args) {
		Tsp ts = new Tsp();
		System.out.println("Driver 1, Route 1:");
		Double driverOneDistance = ts.printPermutation("/csv/first_range.csv");
		System.out.println("Driver 2, Route 1:");
		Double driverTwoDistance = ts.printPermutation("/csv/second_range.csv");
		System.out.println("Driver 1, Route 2:");
		driverOneDistance += ts.printPermutation("/csv/third_range.csv");
		System.out.println("Driver 2, Route 2:");
		driverTwoDistance += ts.printPermutation("/csv/fourth_range.csv");
		System.out.println("Driver 1 or 2, Route 3:");
		Double remainderDistance = ts.printPermutation("/csv/fifth_range.csv");
		System.out.print("Driver 1- total distance traveled: ");
		System.out.println(driverOneDistance);
		System.out.print("Driver 2- total distance traveled: ");
		System.out.println(driverTwoDistance);
		System.out.print("Remainder driver 1 or driver 2 needs to travel: ");
		System.out.println(remainderDistance);
		System.out.print("Combined total distance traveled: ");
		System.out.println(driverOneDistance + driverTwoDistance + remainderDistance);
	}
	
}
