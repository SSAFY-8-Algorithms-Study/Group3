package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 어드벤처 게임
public class BOJ_2310_김지희 {
	static class roomInfo{
		char type;
		int fee;
		roomInfo(char type, int fee){
			this.type = type;
			this.fee = fee;
		}
	}
	
	static int N;
	static ArrayList<Integer>[] list;
	static int[] visit;
	static char[] info;
	static roomInfo[] rooms;
	static boolean answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			
			list = new ArrayList[N+1];
			visit = new int[N+1];
			info = new char[N+1];
			rooms = new roomInfo[N+1];
			
			for(int i=0; i<=N; i++) {
				list[i] = new ArrayList<>();
			}
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				char type = st.nextToken().charAt(0);
				int fee = Integer.parseInt(st.nextToken());
				rooms[i] = new roomInfo(type, fee);
				
				while(true) {
					int next = Integer.parseInt(st.nextToken());
					if(next==0) break;
					list[i].add(next);
				}
			} //input end
			answer = false;
			//1번 방부터 시작하고 소지금 0부터 시작
			visit[1] = 1;
			dfs(0, 1);
			
			if(answer) System.out.println("Yes");
			else System.out.println("No");
			
		}
	}
	
	private static void dfs(int fee, int node) {
		if(node == N) { //N번 방까지 갔나?
			answer = true;
			return;
		}
		for(int next : list[node]) {
			if(visit[next] == 1) continue; //이미 방문한 방이면 넘어감
			
			if(rooms[next].type == 'L') { //다음 방 type이 레프리콘
				if(fee >= rooms[next].fee) {
					visit[next] = 1;
					dfs(fee, next);
				}else {
					visit[next] = 1;
					dfs(rooms[next].fee, next);
				}
			}
			else if(rooms[next].type == 'T') {//다음 방 type 트롤
				if(fee<rooms[next].fee) { //소지금이 통행료보다 적으면 못 감.
					return; //그냥 리턴
				}else {
					visit[next] = 1;
					dfs(fee-rooms[next].fee, next); //다음 방으로 이동.
				}
			}else {//빈 방이면?
				visit[next] = 1; //그냥 방문
				dfs(fee, next);
			}
			//탐색하고 나오면 방문처리 초기화
			visit[next] = 0;
		}
	}
	
}
