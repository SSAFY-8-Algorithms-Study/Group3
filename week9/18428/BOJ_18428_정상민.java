import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class node{
		int i,j;
		node(int i,int j){
			this.i = i;
			this.j = j;
		}
	}
	static int N,flag;
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	static boolean use[];
	static char map[][],copy[][];
	static ArrayList<node> teacher = new ArrayList<>();
	static ArrayList<node> empty = new ArrayList<>(); 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == 'T') teacher.add(new node(i,j));
				else if(map[i][j] == 'X') empty.add(new node(i,j));
			}
		}
		use = new boolean[empty.size()];
		comb(0,0);
		
		if(flag == 1) System.out.println("YES");
		else System.out.println("NO");
	}
	private static void comb(int idx,int cnt) {
		if(flag == 1) return;
		if(cnt == 3) {
			copy = new char[N][N];
			for(int i=0;i<N;i++) {
				copy[i] = map[i].clone();
			}
			for(int i=0;i<use.length;i++) {
				if(use[i]) {
					copy[empty.get(i).i][empty.get(i).j] = 'O';
				}
			}
			check();
			return;
		}
		if(idx == empty.size()) return;
		
		use[idx] = true;
		comb(idx+1,cnt+1);
		use[idx] = false;
		comb(idx+1,cnt);
		
	}
	private static void check() {
		for(int t=0;t<teacher.size();t++) {
			int i = teacher.get(t).i;
			int j = teacher.get(t).j;
			for(int d=0;d<4;d++) {
				int ni = i + di[d];
				int nj = j + dj[d];
				while(ni>=0 && nj>=0 && ni<N && nj<N) {
					if(copy[ni][nj] == 'S') {
						return;
					}
					if(copy[ni][nj] == 'O') {
						break;
					}
					ni = ni + di[d];
					nj = nj + dj[d];
				}
			}
		}
		flag = 1;
	}
}
