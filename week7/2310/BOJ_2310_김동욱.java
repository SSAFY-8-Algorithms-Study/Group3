package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2310_김동욱 {
	static int N;
	static ArrayList[] adjList;
	static StringBuilder sb;
	static boolean[] visited;
	static boolean flag;
	static ArrayList<Integer> cost;

	public static void print() {
		for (ArrayList t : adjList) {
			for (int i = 0; i < t.size(); i++) {
				System.out.printf("%d ", t.get(i));
			}
			System.out.println();
		}
	}

	public static void dfs(int idx, int money, int count, boolean visited[]) {
		
		visited[idx] = true;
		int roomMoney = cost.get(idx);
		
		if (roomMoney >= 0 && money < roomMoney) {
			money = roomMoney;
		}
		if (roomMoney < 0) {
			money = money + roomMoney;
		}
		if(money<0)
			return;
		count+=1;
		//System.out.printf("현재 방 :%d 현재 돈 : %d, 방문한 방의 갯수 :%d\n", idx, money, count);
		//System.out.println(Arrays.toString(visited));
		for (Object t : adjList[idx]) {
			if (!visited[(int) t]) {
				dfs((int) t, money, count, visited);
				visited[(int) t] =false;
				if(idx==1) { // 첫 번 째로 bfs를 돌 경우  true를 해버리고 ,방을 재방문 할 경우가 있기 때문에 열어둠
					visited[1]=false;
				}
			}
		}
		if (count >= N) { // dfs 가지 중에서 N개의 방을 순회하면
			flag = true;
			return;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		while (true) {
			flag = false;
			cost = new ArrayList<Integer>();
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			adjList = new ArrayList[N + 1];
			for (int i = 0; i < N + 1; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			int money = 0;
			cost.add(0); // 비용을 저장하는 ArrayList
			for (int i = 1; i < N + 1; i++) {
				st = new StringTokenizer(br.readLine());
				String ch = st.nextToken();
				money = Integer.parseInt(st.nextToken());
				if (ch.equals("T")) {
					money *= -1; // T 일경우 비용 음수로 저장
				}
				cost.add(money);
				int roomNum = 0;
				while (true) {
					roomNum = Integer.parseInt(st.nextToken());
					if (roomNum == 0)
						break;
					adjList[i].add(roomNum);

				}

			}
			//print();
			flag = false;
			visited = new boolean[N + 1];
			dfs(1, 0, 0, visited);
			if (flag) {
				sb.append("Yes\n");
			} else {
				sb.append("No\n");
			}
		}
		System.out.println(sb);
	}

}