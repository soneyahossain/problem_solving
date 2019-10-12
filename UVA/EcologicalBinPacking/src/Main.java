import java.util.ArrayList;
import java.util.Scanner;
public class Main {

	ArrayList<String> permutation = new ArrayList<String>();
	private void initializePermutationList() {

		permutation.add("BCG");
		permutation.add("BGC");
		permutation.add("CBG");
		permutation.add("CGB");
		permutation.add("GBC");
		permutation.add("GCB");
	}

	private String ProcessEcologicalBin(long[][] binArray) {
		String returnString = "";
		long move = Long.MAX_VALUE;

		for (int i = 0; i < permutation.size(); i++) {

			long tempMove = 0;
			String returnString1 = permutation.get(i);
			for (int j = 0; j < 3; j++) {
				if (returnString1.charAt(j) == 'B') {
					tempMove += binArray[j][1] + binArray[j][2];

					if (tempMove >= move)
						break;
				} else if (returnString1.charAt(j) == 'C') {
					tempMove += binArray[j][0] + binArray[j][1];
					if (tempMove >= move)
						break;
				} else if (returnString1.charAt(j) == 'G') {
					tempMove += binArray[j][0] + binArray[j][2];
					if (tempMove >= move)
						break;
				}
			}
			if (tempMove < move) {
				move = tempMove;
				returnString = returnString1 + " " + move;
			}
		}
		return returnString;
	}

	public static void main(String[] args) {
		Main myObject = new Main();
		Scanner reader = new Scanner(System.in);
		myObject.initializePermutationList();

		while (reader.hasNextLong()) {

			long[][] temp = new long[3][3];

			temp[0][0] = reader.nextLong();
			temp[0][1] = reader.nextLong();
			temp[0][2] = reader.nextLong();
			temp[1][0] = reader.nextLong();
			temp[1][1] = reader.nextLong();
			temp[1][2] = reader.nextLong();
			temp[2][0] = reader.nextLong();
			temp[2][1] = reader.nextLong();
			temp[2][2] = reader.nextLong();
			System.out.println(myObject.ProcessEcologicalBin(temp));

		}
		reader.close();

	}
}
