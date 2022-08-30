package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_16235나무재테크 {
	static class tree implements Comparable<tree>{
		int i,j,age;
		tree(int i,int j,int age){
			this.i=i; this.j=j; this.age=age;
		}
		@Override
		public int compareTo(tree o) {
			return this.age - o.age;
		}
	}
	static ArrayList<tree> Tree;
	static ArrayList<tree> Ltree;
	static ArrayList<tree> Dtree;
	static int N,M,K;
	static int[][] map, feed;
	static int[] di = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dj = { -1, 0, 1, -1, 1, -1, 0, 1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		feed = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;
				feed[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Tree = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			Tree.add(new tree(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,Integer.parseInt(st.nextToken())));
		}
		while(K>0) { //K년 실행
			Ltree = new ArrayList<>();
			Dtree = new ArrayList<>();
			Collections.sort(Tree); //나이순 정렬
			//봄
			for (int i = 0; i < Tree.size(); i++) {
				tree t = Tree.get(i);
				if (t.age > map[t.i][t.j]) {
					Dtree.add(t); //먹을 양분없으면 죽을 트리에 추가
				} else {//먹을 양분 있을 경우
					map[t.i][t.j] -= t.age; //나이만큼 먹고
					t.age += 1; //나이추가
					Ltree.add(t); //살을 나무에 추가
				}
			}
			// 나무 리스트 새로 생성 살아있는 나무들 입력
			Tree = new ArrayList<>();
			Tree.addAll(Ltree);
			//여름
			for (int i = 0; i < Dtree.size(); i++) {
				tree t = Dtree.get(i); //죽은나무들 양분으로 추가
				map[t.i][t.j] += t.age / 2;
			}
			//가을
			int size = Tree.size();
			for (int i = 0; i < size; i++) { 
				tree t = Tree.get(i);
				if (t.age % 5 == 0) {//나이가 딱 5의 배수면 주변에 나무 생성
					for (int d = 0; d < 8; d++) {
						int ni = t.i + di[d];
						int nj = t.j + dj[d];
						if (ni>=0 && ni<N && nj>=0 && nj<N) {
							Tree.add(new tree(ni, nj, 1));
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] += feed[i][j];
				}
			}
			K--;
		}
		System.out.println(Tree.size());
	}

}
