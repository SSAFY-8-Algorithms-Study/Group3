package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2310어드벤처게임 {
	static class room{
		int num,pay;
		ArrayList<Integer> arr;
		char type;
		room(int num,int pay, ArrayList<Integer> arr, char type){
			this.num = num;
			this.pay = pay;
			this.arr = arr;
			this.type = type;
		}
	}
	static room[] rooms;
	static boolean[] visit;
	static boolean flag;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			rooms = new room[N];
			visit = new boolean[N];
			flag = false;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				char type = st.nextToken().charAt(0);
				int pay = Integer.parseInt(st.nextToken());
				ArrayList<Integer> arr = new ArrayList<>();
				while(true) {
					int n = Integer.parseInt(st.nextToken());
					if(n == 0) break;
					arr.add(n-1);
				}
				rooms[i] = new room(i,pay,arr,type);
			}
			if(rooms[0].type == 'E') {
				dfs(0,0);
			}
			else if(rooms[0].type == 'L') {
				dfs(rooms[0].pay,0);
			}
			if(flag) sb.append("Yes\n");
			else sb.append("No\n");
		}
		System.out.println(sb);
	}
	private static void dfs(int money, int room) {
		if(flag) return;
		if(room == rooms.length-1) {
			flag = true;
			return;
		}
		ArrayList<Integer> list = rooms[room].arr;
		for(int i=0;i<list.size();i++) {
			int nroom = list.get(i);
			if(visit[nroom]) continue;
			if(rooms[nroom].type == 'E') {
				visit[nroom] = true;
				dfs(money, nroom);
				visit[nroom] = false;

			}
			else if(rooms[nroom].type == 'L') {
				int nmoney = money >= rooms[nroom].pay ? money : rooms[nroom].pay;
				visit[nroom] = true;
				dfs(nmoney,nroom);
				visit[nroom] = false;

			}
			else if(rooms[nroom].type == 'T'){
				if(money >= rooms[nroom].pay) {
					visit[nroom] = true;
					dfs(money-rooms[nroom].pay,nroom);
					visit[nroom] = false;
				}
				else return;
			}
		}
	}
//	private static boolean bfs() {
//		Queue<Integer> que = new LinkedList<>();
//		que.add(0);
//		if(rooms[0].type == 'T') return false;
//		else que.add(rooms[0].pay);
//		visit[0] = true;
//		while(!que.isEmpty()) {
//			int num = que.poll();
//			int money = que.poll();
//			ArrayList<Integer> list = rooms[num].arr;
//			for(int i=0;i<list.size();i++) {
//				int nnum = list.get(i)-1;
//				if(visit[nnum]) continue;
//				if(rooms[nnum].type == 'E') {
//					que.add(nnum);
//					que.add(money);
//				}
//				else if(rooms[nnum].type == 'L') {
//					
//				}
//				else if(rooms[nnum].type == 'T'){
//					
//				}
//			}
//		}
//		
//		return false;
//	}
}
