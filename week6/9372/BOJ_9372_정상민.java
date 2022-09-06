import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	public static void main(String[] args) throws NumberFormatException, IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;
	StringBuilder sb = new StringBuilder();
	int T = Integer.parseInt(br.readLine());
	for(int tc=1;tc<=T;tc++) {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
		}
		sb.append(N-1+"\n");
	}
	System.out.println(sb);
	}
	

}
