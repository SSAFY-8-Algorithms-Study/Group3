package baek1059;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());//L입력
		int[] arr = new int[L];//L 만큼 집합배열 (집합) 생성
		int cnt = 0; // 좋은 구간 개수 카운트변수
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<L;i++) {
			arr[i] = Integer.parseInt(st.nextToken()); //정수 집합 입력
		}
		Arrays.sort(arr);//집합 정렬
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //n값 입력3
		for(int i=0;i<L;i++) {
			if(i == 0 && arr[i]>n) { //첫번째 값이 n보다 클때
				for(int j=1;j<n;j++) {
					for(int k=n;k<arr[i];k++) {
						cnt++;
					}
				}
				cnt += arr[i]-n-1;
				System.out.println(cnt);
				return;
			}
			else if(arr[i]<n && arr[i+1]>n) { // n이 집합 값 사이에 있을 때
				for(int j=arr[i]+1;j<n;j++) {
					for(int k=n;k<arr[i+1];k++) {
						cnt++;
					}
				}
				cnt += arr[i+1]-n-1;
				System.out.println(cnt);
				return;
			}
			else if(arr[i] == n) { //집합에 n값있으면 0출력 후 종료
				System.out.println(0);
				return;
			} 
		} // 이문제에서 놓쳤던 것들 : n값이 집합 최초값보다 작을때 
	}
}
