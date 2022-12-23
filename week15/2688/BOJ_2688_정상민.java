import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long[][] result;
	static int[] input;
	static long[] answer;
	static int maxinput=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		input = new int[T+1];
		for(int tc=1;tc<=T;tc++) {
			input[tc] = Integer.parseInt(br.readLine());
			maxinput = Math.max(maxinput, input[tc]);
		}
		result = new long[maxinput+1][10];
		answer = new long[maxinput+1];
		answer[1] = 10;
		for(int i=0;i<10;i++) {
			result[1][i] = 1;
		}
		for(int i=2;i<=maxinput;i++) {
			long sum = 1;
			for(int j=0;j<10;j++) {
				if(j==0) {
					result[i][j] = 1;
					continue;
				}
				result[i][j] = result[i-1][j] + result[i][j-1];
				sum += result[i][j];
			}
			answer[i] = sum;
		}
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			int num = input[tc];
			sb.append(answer[num]+"\n");
		}
		System.out.println(sb);
	}

}
