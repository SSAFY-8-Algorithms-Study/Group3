package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2003_김아영 {
	
	private static int N, M;
	private static int[] A;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        int answer = 0;
        int sum = 0;
        int s = 0;
        int e = 0;
        
        for (s = 0; e < N ; e++) {
            sum += A[e];
            while (sum > M) {
                sum -= A[s];
                s++;
            }
            if (sum == M) answer++;
        }
        System.out.println(answer);

	}

}
