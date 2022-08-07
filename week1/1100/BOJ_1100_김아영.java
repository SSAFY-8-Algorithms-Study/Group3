package home0727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test1100 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = 0;
		int ans = 0;
		for(int i=0;i<8;i++) {
			// StringTokenizer st = new StringTokenizer(br.readLine());
			String st = br.readLine();
			for(int j=0;j<8;j++) {
				char a = st.charAt(j);
				if (a=='F') {
					if (i%2==0 && j % 2 == 0) {
						ans++;
					}
					else if (i%2==1 && j % 2 == 1) {
						ans++;
					}
				}
			}
		}
		System.out.println(ans);
	}
}
