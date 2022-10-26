package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14267회사문화1{
	static int N,M;
	static long[] result;
	static int[] boss;
	static boolean[] check;
	static PriorityQueue<goodword> que = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = new long[N+1]; //결과값 저장 배열
		boss = new int[N+1]; //각각의 직속상사 저장할 배열
		check = new boolean[N+1];//했는지 안했는지 체크 배열
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			boss[i] = Integer.parseInt(st.nextToken());
		}
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			que.add(new goodword(target, weight));
		}
		int prev = 100000;
		for(int m=0;m<M;m++) {
			//우선순위 큐여서 상사가 칭찬 받는거부터 시작함 
			goodword now = que.poll();
			int target = now.target;
			for(int i=prev+1;i<target;i++) {
				//일단 내 이전애들중에 칭찬  받은애가 없으면 그놈들 체크 해주고 시작
				//얘는 직접 받은 칭찬이 없으므로 자기 직속상사 의 결과 값이랑 같음
				if(!check[i] && boss[i] != -1) result[i] = result[boss[i]];
			}
			if(!check[target]) { //처음  방문할때는 직속상사의 결과값에다가 현재값 더하기
				result[target] = result[boss[target]] + now.weight;
			}
			else { //이전에 직속상사 결과값 이미 한적있으므로 현재값만 더하기
				//똑같은애가 칭찬 여러번 받는 경우 있을 수 있으므로
				result[target] += now.weight;
			}
			check[target] = true;
			prev = target;
		}
		for(int i=prev+1;i<=N;i++) {//혹시 마지막애가 직접 받은 칭찬이 없을경우 때문에 한번더 확인
			if(!check[i] && boss[i] != -1) result[i] = result[boss[i]];
		}
//		System.out.println(Arrays.toString(result));
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			sb.append(result[i]+" ");
		}
		System.out.println(sb); //결과값 출력
	}
	static class goodword implements Comparable<goodword>{
		//dp로 풀기위해 번호가 작은놈부터 나오게 설정
		int target; 
		long weight;

		public goodword(int target, long weight) {
			this.target = target;
			this.weight = weight;
		}
		@Override
		public int compareTo(goodword o) {
			return (int) (this.target - o.target);
		}
		
	}
}
