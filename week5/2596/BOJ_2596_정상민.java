import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String[] result = {"A","B","C","D","E","F","G","H"};
	static String[] ABC = {"000000","001111","010011","011100","100110","101001","110101","111010"};
	static int[] diff;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		for(int n=0;n<N;n++) {
			diff = new int[8];
			int flag = 0;
			for(int i=n*6;i<(n+1)*6;i++) {
				for(int j=0;j<8;j++) {
					if(str.charAt(i) != ABC[j].charAt(i-n*6)) diff[j]++;
				}
			}
			for(int i=0;i<8;i++) {
				if(diff[i] <= 1) {
					sb.append(result[i]);
					flag = 1;
				}
				
			}
			if(flag == 0) {
				System.out.println(n+1); return;
			}
		}
		System.out.println(sb);
		
		
	}

}
