import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	int[][] outputArray;
	int numOfBlock;
	int[] rowOfBlockInOutputArray;
	int[] topIdexOfRowInOutputArray;

	private void initializeArry() {
		for (int i = 0; i < numOfBlock; i++) {
			outputArray[i][0] = i;
			for (int k = 1; k < numOfBlock; k++)
				outputArray[i][k] = -1;
			rowOfBlockInOutputArray[i] = i;
			topIdexOfRowInOutputArray[i] = 0;
		}
	}

	void processCommand(String command) {

		StringTokenizer st = new StringTokenizer(command, " ");
		String firstPart = st.nextToken();
		int a = Integer.valueOf(st.nextToken());
		String thirdPart = st.nextToken();
		int b = Integer.valueOf(st.nextToken());

		if (a == b)
			return;
		if (rowOfBlockInOutputArray[a] == rowOfBlockInOutputArray[b])
			return;

		if (firstPart.equals("move")) {

			int bIndex = rowOfBlockInOutputArray[b];
			int aIndex = rowOfBlockInOutputArray[a];

			int i = numOfBlock - 1;
			while (outputArray[aIndex][i] != a) {
				if (outputArray[aIndex][i] == -1) {
				} else {
					int arrayValue = outputArray[aIndex][i]; // value ta nilam
					outputArray[arrayValue][topIdexOfRowInOutputArray[arrayValue] + 1] = arrayValue;
					topIdexOfRowInOutputArray[aIndex]--;
					topIdexOfRowInOutputArray[arrayValue]++;
					rowOfBlockInOutputArray[arrayValue] = arrayValue;
					outputArray[aIndex][i] = -1;

				}
				i--;
			}

			if (thirdPart.equals("onto")) {

				// now move blocks that are over b to their initial position

				i = numOfBlock - 1;
				while (outputArray[bIndex][i] != b) {
					if (outputArray[bIndex][i] == -1) {
					} else {
						int arrayValue = outputArray[bIndex][i]; // value ta
																	// nilam
						outputArray[arrayValue][topIdexOfRowInOutputArray[arrayValue] + 1] = arrayValue;
						topIdexOfRowInOutputArray[bIndex]--;
						topIdexOfRowInOutputArray[arrayValue]++;
						rowOfBlockInOutputArray[arrayValue] = arrayValue;
						outputArray[bIndex][i] = -1;

					}
					i--;
				}

				// now move a over b

				outputArray[bIndex][topIdexOfRowInOutputArray[bIndex] + 1] = a;
				topIdexOfRowInOutputArray[bIndex]++;
				outputArray[aIndex][topIdexOfRowInOutputArray[aIndex]] = -1;
				topIdexOfRowInOutputArray[aIndex]--;
				rowOfBlockInOutputArray[a] = bIndex;

			}

			else if (thirdPart.equals("over")) {
				outputArray[bIndex][topIdexOfRowInOutputArray[bIndex] + 1] = a;
				topIdexOfRowInOutputArray[bIndex]++;
				outputArray[aIndex][topIdexOfRowInOutputArray[aIndex]] = -1;
				topIdexOfRowInOutputArray[aIndex]--;
				rowOfBlockInOutputArray[a] = bIndex;
			}

		} else if (firstPart.equals("pile")) {

			int bIndex = rowOfBlockInOutputArray[b];
			int aIndex = rowOfBlockInOutputArray[a];

			if (thirdPart.equals("onto")) {

				// move the block that are over b before moving the pile.
				int i = numOfBlock - 1;
				while (outputArray[bIndex][i] != b) {
					if (outputArray[bIndex][i] == -1) {
					} else {
						int arrayValue = outputArray[bIndex][i]; // value ta
																	// nilam
						outputArray[arrayValue][topIdexOfRowInOutputArray[arrayValue] + 1] = arrayValue;
						topIdexOfRowInOutputArray[bIndex]--;
						topIdexOfRowInOutputArray[arrayValue]++;
						rowOfBlockInOutputArray[arrayValue] = arrayValue;
						outputArray[bIndex][i] = -1;
					}
					i--;
				}

			}

			//rest operation is same for both over and onto

			int temp[] = new int[numOfBlock];

			for (int j = 0; j < numOfBlock; j++) {
				temp[j] = -1;
			}

			int len = 0;
			int i = numOfBlock - 1;
			while (outputArray[aIndex][i] != a) {
				if (outputArray[aIndex][i] != -1) 	
				{					
					int arrayValue = outputArray[aIndex][i]; // now take the value 
					temp[len++] = arrayValue;			
					topIdexOfRowInOutputArray[aIndex]--;
					outputArray[aIndex][i] = -1;

				}
				i--;
			}

			temp[len] = a;
			topIdexOfRowInOutputArray[aIndex]--;
			outputArray[aIndex][i] = -1;

			if (len >= 0) {

				for (int l = len; l >= 0; l--) {

					outputArray[bIndex][topIdexOfRowInOutputArray[bIndex] + 1] = temp[l];
					topIdexOfRowInOutputArray[bIndex]++;
					rowOfBlockInOutputArray[temp[l]] = bIndex;

				}

			}

		}
	}

	public static void main(String[] args) throws IOException {

		Main myObject = new Main();
		Scanner reader = new Scanner(System.in);
		myObject.numOfBlock = reader.nextInt();

		myObject.outputArray = new int[myObject.numOfBlock][myObject.numOfBlock];
		myObject.rowOfBlockInOutputArray = new int[myObject.numOfBlock];
		myObject.topIdexOfRowInOutputArray = new int[myObject.numOfBlock];
		myObject.initializeArry();

		while (true) {
			String command = reader.next();
			if (command.equals("quit"))
				break;
			if (command.equals("move") || command.equals("pile")) {

				int a = reader.nextInt();
				String subCommand = reader.next();
				int b = reader.nextInt();

				command = command + " " + a + " " + subCommand + " " + b;
				myObject.processCommand(command);
			
			}
		}
		reader.close();
		myObject.printOutputArray();
	}

	void printOutputArray() {
		
		for (int i = 0; i < numOfBlock; i++) {
			System.out.print(i + ":");
			for (int j = 0; j < numOfBlock; j++) {
				if (outputArray[i][j] != -1)
					System.out.print(" "+outputArray[i][j]);
			}
			System.out.println();
		}
	}
}
