import java.util.ArrayList;
import java.util.Scanner;

class Main {

	void findLongestStack(int arr[][], int numOfBox, int numOfDimensions) {

		ArrayList<Integer> resultingboxes = new ArrayList<Integer>();
		int list[] = new int[numOfBox];
		int i, j, max = 0;

		for (i = 0; i < numOfBox; i++) {
			list[i] = 1;

		}

		for (i = 1; i < numOfBox; i++) {
			for (j = 0; j < i; j++) {
				if (boxOneFixInBoxTwo(arr[j], arr[i], numOfDimensions) && list[i] < list[j] + 1)
					list[i] = list[j] + 1;
			}
		}

		for (i = 0; i < numOfBox; i++) {
			if (max < list[i])
				max = list[i];
		}
		int mx = max;

		int last = -1;
		for (i = list.length - 1; i >= 0; i--) {
			if (list[i] == mx) {

				if (resultingboxes.size() > 0) {
					if (boxOneFixInBoxTwo(arr[i], arr[last], numOfDimensions)) {

						resultingboxes.add(arr[i][numOfDimensions]);
						last = i;
						mx = mx - 1;
					}
				} else {
					resultingboxes.add(arr[i][numOfDimensions]);
					last = i;
					mx = mx - 1;
				}
			}
		}
		
		System.out.println(max);
		
		for (int l = resultingboxes.size() - 1; l >= 0; l--) {
			if (l == 0) {
				System.out.print(resultingboxes.get(l));
			} else
				System.out.print(resultingboxes.get(l) + " ");
		}
		System.out.println();
	}

	void ProcessStackingBoxes(int[][] temp, int numOfBox, int numOfDimensions) {

		
		for (int jj = 0; jj < numOfBox; jj++) {
			temp[jj] = sort(temp[jj]);                                           // start sorting
		}
		
		for (int i = 1; i < numOfBox; i++) {

			int j = i;
			while (j > 0) {

				if (temp[j][0] < temp[j - 1][0]) {                               // start sorting according to priority

					int[] tempArray = temp[j];
					temp[j] = temp[j - 1];
					temp[j - 1] = tempArray;
				} else if (temp[j][0] == temp[j - 1][0]) {

					int index = 1;
					while (temp[j][index] == temp[j - 1][index]
							&& index < numOfDimensions) {
						index++;
					}
					if (index <= numOfDimensions - 1
							&& temp[j][index] < temp[j - 1][index]) {
						int[] tempArray = temp[j];
						temp[j] = temp[j - 1];
						temp[j - 1] = tempArray;
					}
				}
				j--;
			}
		}

		findLongestStack(temp, numOfBox, numOfDimensions);
		//PrintArray(temp, numOfBox, numOfDimensions);
	}

	public static void main(String args[]) {
		Main myObject = new Main();
		Scanner reader = new Scanner(System.in);
		while (reader.hasNextInt()) {
			int numOfBox = reader.nextInt();
			int numOfDimensions = reader.nextInt();
			int[][] temp = new int[numOfBox][numOfDimensions + 1];
			for (int j = 0; j < numOfBox; j++) {
				for (int jj = 0; jj < numOfDimensions; jj++) {
					temp[j][jj] = reader.nextInt();
				}
				temp[j][numOfDimensions] = j + 1;
			}
			myObject.ProcessStackingBoxes(temp, numOfBox, numOfDimensions);
		}
	}

	private int[] sort(int[] array) {

		for (int i = 1; i < array.length - 1; i++) {
			int j = i;
			while (j > 0) {
				if (array[j] < array[j - 1]) {
					int tempValue = array[j];
					array[j] = array[j - 1];
					array[j - 1] = tempValue;
				}
				j--;
			}
		}
		return array;
	}

	private boolean boxOneFixInBoxTwo(int[] One, int[] Two, int numOfDimensions) {

		for (int i = 0; i < numOfDimensions; i++) {
			if (One[i] >= Two[i])
				return false;
		}
		return true;
	}

	
	private void PrintArray(int[][] temp, int numOfBox, int numOfDimensions) {

		for (int k = 0; k < numOfBox; k++) {

			System.out.println();
			for (int j = 0; j <= numOfDimensions; j++) {
				System.out.print(temp[k][j] + " ");
			}
		}
		System.out.println();

	}
}