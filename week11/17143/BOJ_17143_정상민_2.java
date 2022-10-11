import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
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
	static shark[][] map, newmap;
	static ArrayList<shark> sharklist = new ArrayList<>();
	static ArrayList<shark> newsharklist = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new shark[R+1][C+1];
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
			map[r][c] = (new shark(r, c, s, d, z));
			sharklist.add(new shark(r, c, s, d, z));
		}
		for(int king = 1;king<=C;king++) {
			//상어 잡기
			letcatch(king);
			//상어 이동
			newmap = new shark[R+1][C+1];
//			for(int i=1;i<=R;i++) {
//				newmap[i] = map[i].clone();
//			}
			newsharklist = new ArrayList<>();
			for(int i=0;i<sharklist.size();i++) {
				move(sharklist.get(i));
			}
			//두마리 이상인곳 체크
			eatshark();
			sharklist = newsharklist;
			map = new shark[R+1][C+1];
			for(int i=1;i<=R;i++) {
				map[i] = newmap[i].clone();
			}
			
		}
		System.out.println(result);
	} 
	private static void letcatch(int j) {
		for(int i=1;i<=R;i++) {
			if(map[i][j] != null) {
				result += map[i][j].z;
				for(int r=0;r<sharklist.size();r++) {
					if(sharklist.get(r).z == map[i][j].z) {
						sharklist.remove(r); //리스트에서 없애기
						break;
					}
				}
				map[i][j] = null; //맵에서 없애기
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
		newsharklist.add(new shark(nowi,nowj,Shark.s,d,Shark.z));
	}
	private static void eatshark() {
		for(int s=0;s<newsharklist.size();s++) {
			shark nshark = newsharklist.get(s);
			if(newmap[nshark.i][nshark.j] == null) {
				newmap[nshark.i][nshark.j] = nshark;
			}
			else {
				if(newmap[nshark.i][nshark.j].z > nshark.z) {
					newsharklist.remove(s);
				}
				else {
					int removez = newmap[nshark.i][nshark.j].z;
					newmap[nshark.i][nshark.j] = nshark;
					for(int r=0;r<newsharklist.size();r++) {
						if(newsharklist.get(r).z == removez) {
							newsharklist.remove(r);
							break;
						}
					}
				}
				s--;
			}
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
