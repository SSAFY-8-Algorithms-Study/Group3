import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,TN,result;
	static ArrayList<Integer> trueknow;
	static ArrayList<Integer> party[];
	static ArrayList<Integer> arr[];
	static boolean[] know;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trueknow = new ArrayList<>(); //처음에 아는 놈들
		party = new ArrayList[M]; // 파티별 인원들
		arr = new ArrayList[N+1]; // 연결그래프? 처럼 만들어서 처음에 아는놈들으로 bfs돌려서 
                                  //visit체크하자

		know = new boolean[N+1];// visit 체크
		for(int i=0;i<M;i++) party[i] = new ArrayList<>();
		for(int i=1;i<=N;i++) arr[i] = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		TN = Integer.parseInt(st.nextToken());
		if(TN == 0) {
			System.out.println(M);
			return;
		}
		for(int i=1;i<=TN;i++) {
			trueknow.add(Integer.parseInt(st.nextToken()));
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int PN = Integer.parseInt(st.nextToken());
			for(int j=0;j<PN;j++) {
				party[i].add(Integer.parseInt(st.nextToken()));
			}
		}
        //입력 받기 끝
		for(int i=1;i<=N;i++) {//한명씩
			for(int j=0;j<M;j++) { //파티 돌면서
				boolean flag = false;
				for(int k=0;k<party[j].size();k++) {
					if(party[j].get(k) == i) {
						flag = true;
						break;
					} //파티에 자기가 포함됬으면
				}
				if(flag) {
					for(int k=0;k<party[j].size();k++) {
						if(party[j].get(k) != i) {
							arr[i].add(party[j].get(k));
						} //연결그래프 만들기
					}
				}
			}
		}
		bfs(); //주어진 아는애들을 시작점으로 bfs돌면서 연결된애들 다 아는거로 know배열에 true처리
		for(int i=0;i<M;i++) { // 이제 각 파티 돌면서 아무도 모를때만 ++
			boolean flag = true;
			for(int j=0;j<party[i].size();j++) {
				if(know[party[i].get(j)]) {
					flag = false;
					break;
				}
			}
			if(flag) result++;
		}
		System.out.println(result);
	}
	private static void bfs() {
		Queue<Integer> que = new LinkedList<>();
		for(int i=0;i<trueknow.size();i++) {
			que.add(trueknow.get(i));
			know[trueknow.get(i)] = true;
		}
		while(!que.isEmpty()) {
			int now = que.poll();
			for(int i=0;i<arr[now].size();i++) {
				int next = arr[now].get(i);
				if(!know[next]) {
					que.add(next);
					know[next] = true;
				}
			}
		}
	}
}
