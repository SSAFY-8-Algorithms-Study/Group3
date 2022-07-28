package baek1100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] chess = new String[8];
		int flag = 0;
		int cnt = 0;
		for(int i=0;i<8;i++) {
			chess[i] = br.readLine();
		}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(flag%2 == 0) {
					if(chess[i].charAt(j++) == 'F') cnt++;
				}
				else {
					if(chess[i].charAt(++j) == 'F') cnt++; 
				}
			}
			flag++;
		}
		System.out.println(cnt);
	}
}
