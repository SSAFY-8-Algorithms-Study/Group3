import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());
		long W = Integer.parseInt(st.nextToken());
		long S = Integer.parseInt(st.nextToken());
		
		long ans1 = (X+Y) * W; //직선으로 최대한
		
		long ans2 =	Math.min(X, Y) * S + Math.abs(X-Y) * W; // 대각선 + 직선
		
		long ans3;
		if((X+Y)%2 == 0) {
			ans3 = Math.max(X, Y) * S;
		}
		else {
			ans3 = (Math.max(X, Y) - 1) * S + W;
		}
		System.out.println(Math.min(ans3 , Math.min(ans1, ans2)));
	}

}
