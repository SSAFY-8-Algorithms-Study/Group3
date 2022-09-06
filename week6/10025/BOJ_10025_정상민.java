package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10025게으른백곰 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int sum = 0, result = 0;
		int lidx=1000001, hidx=-1;
		int[] arr = new int[1000001];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			int ice = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			arr[idx] += ice;
			if(idx > hidx) hidx = idx;
			if(idx < lidx) lidx = idx;
		}
//		for(int i=lidx-K;i<=lidx+K;i++) {
//			if(i<0 || i>1000000) continue;
//			sum += arr[i];
//		}
//		System.out.println(sum);
//		result = sum;
//		for(int i=lidx+1;i<=hidx-K;i++) {
//			if(i-K-1>=0 && i+K < 1000001){
//				sum -= arr[i-K-1];
//				sum += arr[i+K];
//			}
//			if(result < sum) result = sum;
//		}
        for (int i = 0; i < 1+2*K && i <= 1000000; i++) {
            sum += arr[i];
        }
        result = sum;
        for (int i = 1+2*K, j = 0; i <= 1000000; i++, j++) {
            sum -= arr[j];
            sum += arr[i];
            if (sum > result)
                result = sum;
        }
		System.out.println(result);
	}

}
