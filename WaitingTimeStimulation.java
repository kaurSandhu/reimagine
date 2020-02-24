
public class WaitingTimeStimulation {
	public static void main(String[]args) {
		WaitLine w1=new WaitLine();
		w1.simulate(20, 0.5, 10);
		w1.displayResults();
	}

}
