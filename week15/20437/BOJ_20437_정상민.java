import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			String str = br.readLine();
			int K = Integer.parseInt(br.readLine());
			int[] count = new int[26];
			int[] farindex = new int[26];
			int ans1 = Integer.MAX_VALUE;
			int ans2 = 0;
			if(K == 1) {
				ans1 = 1;
				ans2 = 1;
				sb.append(ans1+" "+ans2+"\n");
				continue;
			}
			for(int i=0;i<str.length();i++) {
				char now = str.charAt(i);
				int alphaindex = now - 97;
				if(count[alphaindex] == 0) {
					farindex[alphaindex] = i;
				}
				count[alphaindex]++;
				if(count[alphaindex] == K) {
					int length = i - farindex[alphaindex] + 1;
					ans1 = Math.min(length, ans1);
					ans2 = Math.max(length, ans2);
					count[alphaindex]--;
					if(K==2) {
						farindex[alphaindex] = i;
					}
					else if(K>2) {
						for(int j=farindex[alphaindex]+1;j<i;j++) {
							if(str.charAt(j) == now) {
								farindex[alphaindex] = j;
								break;
							}
						}
					}
				}
			}
			if(ans1 != Integer.MAX_VALUE) {
				sb.append(ans1+" "+ans2+"\n");
			}
			else {
				sb.append(-1+"\n");
			}
			
		}
		System.out.println(sb);
	}

}
