import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int cnt300=0,cnt60=0,cnt10=0;
		if(T % 10 != 0) {
			System.out.println(-1);
			return;
		}

		while(true) {
			if(T/300 >= 1) {
				T -= 300;
				cnt300++;
			}
			else if(T/60 >= 1) {
				T -= 60;
				cnt60++;
			}
			else if(T/10 >= 1) {
				T -= 10;
				cnt10++;
			}
			if(T < 10) break;
		}
		if(T == 0) {
			System.out.println(cnt300+" "+cnt60+" "+cnt10);
		}
		else {
			System.out.println(-1);
		}
		
	}

}
