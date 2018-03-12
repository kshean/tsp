package tsp;

public class Main {

	public static void main(String[] args) {
		Tsp ts = new Tsp();
		ts.printPermutation("/csv/first_range.csv");
		ts.printPermutation("/csv/second_range.csv");
		ts.printPermutation("/csv/third_range.csv");
		ts.printPermutation("/csv/fourth_range.csv");
		ts.printPermutation("/csv/fifth_range.csv");
	}
	
}
