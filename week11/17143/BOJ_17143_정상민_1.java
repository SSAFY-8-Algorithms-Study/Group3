import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Main{
	static class shark{
		int i,j,s,d,z;
		public shark(int i, int j, int s, int d, int z) {
			this.i = i;
			this.j = j;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		@Override
		public String toString() {
			return "shark [i=" + i + ", j=" + j + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}
	static int R,C,M,result;
	static int[] di = {0,-1,+1,0,0};
	static int[] dj = {0,0,0,+1,-1};
	static ArrayList<shark>[][] map;
	static ArrayList<shark> sharklist = new ArrayList<>();
	static ArrayList<shark> newsharklist = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new ArrayList[R+1][C+1];
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		if(M == 0 ) {
			System.out.println(0);
			return;
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c].add(new shark(r, c, s, d, z));
			sharklist.add(new shark(r, c, s, d, z));
		}
		for(int king = 1;king<=C;king++) {
			//상어 잡기
			letcatch(king);
			//상어 이동
			newsharklist = new ArrayList<>();
			for(int i=0;i<sharklist.size();i++) {
				move(sharklist.get(i));
			}
			//두마리 이상인곳 체크
			for(int i=1;i<=R;i++) {
				for(int j=1;j<=C;j++) {
					if(map[i][j].size() > 1) {
						eatshark(i,j);
					}
				}
			}
			sharklist = newsharklist;
			
		}
		System.out.println(result);
	} 
	private static void letcatch(int j) {
		for(int i=1;i<=R;i++) {
			if(map[i][j].size() != 0) {
				result += map[i][j].get(0).z;
				for(int r=0;r<sharklist.size();r++) {
					if(sharklist.get(r).z == map[i][j].get(0).z) {
						sharklist.remove(r); //리스트에서 없애기
						break;
					}
				}
				map[i][j].remove(0); //맵에서 없애기
				return;
			}
		}
	}
	private static void move(shark Shark) {
		if(Shark.s == 0) {
			newsharklist.add(Shark);
			return;//속력 0이면 이동할 필요 없음
		}
		int nowi = Shark.i;
		int nowj = Shark.j;
		int nexti = -1;
		int nextj = -1;
		int d = Shark.d;
        int S = 0;
		if(d == 1 || d == 2) {
			S = (R-1) * 2;
		}
		else {
			S = (C-1) * 2;
		}
		S = Shark.s % S;
		for(int s=0;s<S;s++) {
			nexti = nowi + di[d];
			nextj = nowj + dj[d];
			if(nexti<1 || nextj<1 || nexti>R || nextj>C) {
				d = changedir(d);
				nexti = nowi + di[d];
				nextj = nowj + dj[d];
			}
			nowi = nexti;
			nowj = nextj;
		}
		for(int i=0;i<map[Shark.i][Shark.j].size();i++) {
			if(map[Shark.i][Shark.j].get(i).z == Shark.z) {
				map[Shark.i][Shark.j].remove(i); //기존 위치에 크기 z인 상어 삭제
				break;
			} //이동하다 보면 혹시 두마리씩 있을수도 있어서 z값으로 구분
		}
		map[nowi][nowj].add(new shark(nowi,nowj,Shark.s,d,Shark.z)); //이동한 위치에 상어 추가
		newsharklist.add(new shark(nowi,nowj,Shark.s,d,Shark.z));
		//혹시 이동위치가 원래위치랑 같아도 먼저 지우고 추가하니까 괜찮
	}
	private static void eatshark(int i,int j) {
		int max= 0 ;
		int size = map[i][j].size();
		shark bigshark = null;
		for(int s=0;s<size;s++) {
			if(max < map[i][j].get(s).z) {
				max = map[i][j].get(s).z;
				bigshark = new shark(map[i][j].get(s).i, map[i][j].get(s).j, 
						map[i][j].get(s).s, map[i][j].get(s).d, map[i][j].get(s).z);
			}
		}
		map[i][j] = new ArrayList<>();
		map[i][j].add(bigshark);
		ArrayList<Integer> removelist = new ArrayList<>();
		for(int s=newsharklist.size()-1;s>=0;s--) {
			if(newsharklist.get(s).i == i && newsharklist.get(s).j == j) {
				if(newsharklist.get(s).z != max) {
					removelist.add(s);
				}
			}
		}
		for(int z=0;z<removelist.size();z++) {
			int remove = removelist.get(z);
			newsharklist.remove(remove);
//			newsharklist.remove(removelist.get(z));
		}
	}
	private static int changedir(int d) {
		if(d == 1) d = 2;
		else if(d == 2) d = 1;
		else if(d == 3) d = 4;
		else d = 3;
		return d;
	}
}
