package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2304_김동욱 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] tempPillars = new int[N][2];
		int maxPillar = 0; // 최대 기둥
		int maxPillarPos= 1; // 최대 기둥 Pos
		int end=0;
		int sum=0;
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			tempPillars[i][0]= Integer.parseInt(st.nextToken());
			tempPillars[i][1]= Integer.parseInt(st.nextToken());
			
			if(end<tempPillars[i][0]) // 제일 큰 기둥 탐색 
			end = tempPillars[i][0];
			if(maxPillar <tempPillars[i][1]) {
				maxPillar = tempPillars[i][1];
				maxPillarPos = tempPillars[i][0];
			}
	}
		int[] pillars=  new int[end+1];
		for(int i=0;i<N;i++) {
			pillars[tempPillars[i][0]] = tempPillars[i][1]; 
		}
		int tempLength=0; // 왼쪽 부터 제일 큰 기둥까지 
		for(int i=0;i<maxPillarPos+1;i++) {
			if(tempLength<pillars[i]) {
				tempLength=pillars[i];
			}
			sum+=tempLength;
		}
		tempLength=0; // 오른쪽 부터 제일큰 기둥까지
		for(int j=end;j>maxPillarPos;j--) {
			if(tempLength<pillars[j]) {
				tempLength=pillars[j];
			}
			sum+=tempLength;
		}
		System.out.println(sum);
}
}