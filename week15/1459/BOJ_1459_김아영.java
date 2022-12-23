package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1459_김아영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long X, Y, W, S, result;
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		if (W + W < S) {
			result = (X + Y) * W;
		} else if (W + W > S + S) {
			if ((X + Y) % 2 == 0) {
				result = Math.max(X, Y) * S;
			} else {
				result = (Math.max(X, Y) - 1) * S;
				result = result + W;
			}
		} else {
			if (X < Y) {
				result = X * S;
				result = result + ((Y - X) * W);
			} else {
				result = Y * S;
				result = result + ((X - Y) * W);
			}
		}
		System.out.println(result);
	}

}
