package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1043_김아영 {

	static int N, M, tN;
	static int tP[];
	static int arr[];
	static List<Integer>[] list;

	static int findboss(int x) {
		if (arr[x] == 0)
			return x;
		return arr[x] = findboss(arr[x]);
	}

	static void setunion(int a, int b) {
		int finda = findboss(a);
		int findb = findboss(b);
		if (finda == findb)
			return;
		arr[findb] = finda;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		tN = Integer.parseInt(st.nextToken());
		tP = new int[tN + 1];
		for (int i = 0; i < tN; i++) {
			tP[i] = Integer.parseInt(st.nextToken());
		}
		list = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			list[i] = new ArrayList();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // size
			int b = Integer.parseInt(st.nextToken()); // 첫번째 수
			list[i].add(b);
			for (int j = 1; j < a; j++) {
				int c = Integer.parseInt(st.nextToken());
				list[i].add(c);
				if (findboss(b) != findboss(c)) {
					setunion(b, c);
				}
				b = c;
			}
		} // 입력과 유니온 세팅 완료

		// System.out.println(Arrays.toString(bat));
		int result = M;
		for (int i = 0; i < M; i++) {
			int flag = 0;
			int size = list[i].size();
			for (int j = 0; j < size; j++) {
				for (int t = 0; t < tN; t++) {
					if (findboss(tP[t]) == findboss(list[i].get(j))) { // 만약에 아는 사람을 만나면 그때 파티는 --, 체크
						result--;
						flag++;
						break;
					}
				}
				if (flag == 1)
					break;
			}

		}
		System.out.println(result);
	}

}
