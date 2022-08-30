import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static class point{
		int i,j;
		point(int i,int j){
			this.i = i;
			this.j = j;
		}
	}
	static int N,M,flag,mintime = Integer.MAX_VALUE,cnt0,ccc;
	static int[][] map,copy;
	static boolean[][] visit;
	static int[] di = {-1,+1,0,0};
	static int[] dj = {0,0,-1,+1};
	static boolean[] used;
	static ArrayList<point> list = new ArrayList<>();
	static ArrayList<point> V;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 맵 크기 입력
		M = Integer.parseInt(st.nextToken()); // 활성 바이러스 개수 입력
		map = new int[N][N]; //맵 배열 초기화
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 맵 입력
				if(map[i][j] == 2) list.add(new point(i,j)); //모든 바이러스들 리스트에 추가
				else if(map[i][j] == 0) ccc++;
			}
		}
		if(ccc == 0) {
			System.out.println(0);
			return;
		}
		used = new boolean[list.size()]; // 조합 생성배열 초기화
		comb(0,0); // 조합 생성 호출
		
		if(flag == 1) System.out.println(mintime);
		else System.out.println(-1);
//		if(mintime == Integer.MAX_VALUE) System.out.println(-1); //flag 1이면 한번이라도 모든칸에 퍼진 것 시간 출력
//		else System.out.println(mintime); // flag 그대로 0이면 한번도 모든칸에 퍼진 경우 없으므로 -1 출력
	}
	private static void comb(int idx,  int cnt){
		if(cnt == M) { // 바이러스 M개 used배열에 true로 선택 완료
			V = new ArrayList<>(); //사용할 바이러스 리스트
			for(int i=0;i<list.size();i++) {
				if(used[i]) V.add(list.get(i)); 
			} // 조합에서 생성된 경우의 수 바이러스만 V에 추가
			copy = new int[N][N]; //맵 복사할 배열, 딥카피
			visit = new boolean[N][N];
			for(int i=0;i<N;i++) {
				copy[i] = map[i].clone();
			}
			int time = bfs(); //해당 경우의 수로 bfs호출, time에 마지막에 전염된칸의 시간 저장
//			System.out.println(time);
//			for(int i=0;i<N;i++) {
//				System.out.println(Arrays.toString(copy[i]));
//			}
//			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡ");
			int che = check(); //copy배열에 빈칸이 없는지 확인
			if(che != 0) { // 없으면 시간 값 갱신
				mintime = Math.min(time, mintime);
			} // 0이 하나라도 있으면 무시
//			mintime = Math.min(time, mintime);
			return;
		}
		if(idx == list.size()) return;
		used[idx] = true; // 순열 공식 
		comb(idx+1,cnt+1);
		used[idx] = false;
		comb(idx+1,cnt); // 순열 공식
	}
	private static int bfs() {
		Queue<point> que = new LinkedList<>();
		int cnt = 1;
		cnt0 = ccc;
		for(int m=0;m<M;m++) { //M개 바이러스 만큼 que에 add, 방문처리
			que.add(V.get(m));
			copy[V.get(m).i][V.get(m).j] = 1;
			visit[V.get(m).i][V.get(m).j] = true;
		}
		while(!que.isEmpty()) {
			int size = que.size();
			for(int s=0;s<size;s++) {
				point p = que.poll();
				for(int d=0;d<4;d++) {
					int ni = p.i + di[d];
					int nj = p.j + dj[d]; //범위 밖이거나 벽이면 무시
					if(ni<0 || nj<0 || ni>=N || nj>=N || copy[ni][nj] == 1) continue;
					if(!visit[ni][nj]) { //빈칸이면 바이러스 퍼트리기
						que.add(new point(ni,nj)); //전염된 바이러스칸 add 
						visit[ni][nj] = true;
						if(copy[ni][nj] == 0) {
							cnt0--;
//							System.out.println(cnt0);
							copy[ni][nj] = cnt; //해당 칸까지 최단거리로 방문처리
							if(cnt0 == 0) {
								return cnt;
							}
						}
					}
				}
			}
			cnt++;//시간 ++
		}
		return cnt-2; //마지막 칸 전염되고 while문 2번 더돌고 탈출하기 때문에 -2값 리턴
	}
	private static int check() { // 0이 있는지 없는지 체크 
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(copy[i][j] ==  0) return 0;
			}
		}
		flag  = 1; // 한번이라도 모든칸이 바이러스 퍼졌으면 flag 1로 갱신
		return 1;
	}
}
