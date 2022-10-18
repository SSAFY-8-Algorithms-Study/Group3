import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N,K,time=1;
	static int[] di = {0,0,0,-1,1};
	static int[] dj = {0,1,-1,0,0};
	static int[][] map;
	static ArrayList<horse> horsemap[][]; //맵을 2차원 리스트로 만듦
	static ArrayList<horse> horselist = new ArrayList<>(); //말 리스트
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		horsemap = new ArrayList[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				horsemap[i][j] = new ArrayList<>();
			}
		}
		for(int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken())-1;
			int j = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			horselist.add(new horse(k, i, j, dir));
			horsemap[i][j].add(new horse(k, i, j, dir));
		}
		///////////////////////////////////////////////입력 끝
		while(time<=1000) {
			boolean flag= false;
			for(int k=0;k<K;k++) {
				horse now = horselist.get(k);//0번말부터 k-1말까지 순서대로 이동
				int nowi = now.i;
				int nowj = now.j;
				int nowdir = now.dir;
				int nexti = nowi + di[nowdir];
				int nextj = nowj + dj[nowdir];

				if(nexti<0 || nextj<0 || nexti>=N || nextj>=N || map[nexti][nextj]==2) {
					//맵 벗어나거나 다음이 파란색일때 동일
					if(blue(now)) {
						flag = true;
						break;
					}
				}
				else if(map[nexti][nextj]==0) { //흰색 만났을 때
					if(white(now)) {
						flag = true;
						break;
					}
				}
				else if(map[nexti][nextj]==1) { //빨간색 만났을 때
					if(red(now)) {
						flag = true;
						break;
					}
				}
			}
			if(flag) break;
			time++;
		}
		System.out.println(time>1000?-1:time);
	}
	private static boolean red(horse now) {
		int nowi = now.i;
		int nowj = now.j;
		int nowdir = now.dir;
		int nexti = nowi + di[nowdir];
		int nextj = nowj + dj[nowdir];
		int index = -1; //해당 말위치에 몇번째로 놓인 말인지 index에 저장
		for(int i=0;i<horsemap[nowi][nowj].size();i++) {//말이 몇번째에 있는지 확인
			//자기위에 애들만 데리고 가야하니까, 이반복문 어짜피 4번이상 안돔 
			if(horsemap[nowi][nowj].get(i).num == now.num) index = i;
		}
		for(int i=horsemap[nowi][nowj].size()-1;i>=index;i--) {//자기위에 쌓인애들 데리고가기
			horsemap[nexti][nextj].add(horsemap[nowi][nowj].get(i));//빨간색은 다음위치에순서 반대로 쌓기
			int num = horsemap[nowi][nowj].get(i).num;
			horselist.get(num).i = nexti;
			horselist.get(num).j = nextj;//말 리스트에 말위치 값 갱신
		}
		for(int i=horsemap[nowi][nowj].size()-1;i>=index;i--) {
			horsemap[nowi][nowj].remove(i); //이동후 원래 위치 말 삭제
		}
		int size = horsemap[nexti][nextj].size();
		if(size >= 4) return true;
		else return false;
	}
	private static boolean white(horse now) {
		int nowi = now.i;
		int nowj = now.j;
		int nowdir = now.dir;
		int nexti = nowi + di[nowdir];
		int nextj = nowj + dj[nowdir];
		int index = -1;
		for(int i=0;i<horsemap[nowi][nowj].size();i++) {//말이 몇번째에 있는지 확인
			//자기위에 애들만 데리고 가야하니까, 이반복문 어짜피 4번이상 안돔 
			if(horsemap[nowi][nowj].get(i).num == now.num) index = i;
		}
		for(int i=index;i<horsemap[nowi][nowj].size();i++) {//자기위에 쌓인애들 데리고가기
			horsemap[nexti][nextj].add(horsemap[nowi][nowj].get(i));//하얀색은 다음위치에 순서 그대로 쌓기
			int num = horsemap[nowi][nowj].get(i).num;
			horselist.get(num).i = nexti;
			horselist.get(num).j = nextj;
		}
		for(int i=horsemap[nowi][nowj].size()-1;i>=index;i--) {
			horsemap[nowi][nowj].remove(i);
		}
		int size = horsemap[nexti][nextj].size();
		if(size >= 4) {
			return true;
		}
		else return false;
	}
	private static boolean blue(horse now) {//여기들어오면 방향 바꾸고 다음칸 파란색아니면 이동
		// 파란색이면 방향만 바꾸고 리턴
		int nowi = now.i;
		int nowj = now.j;
		int nowdir = now.dir;
		now.dir = changedir(nowdir);
		nowdir = now.dir;
		int nexti = nowi + di[nowdir];
		int nextj = nowj + dj[nowdir];//파란색으로 들어왔을때 다음위치가 범위밖인거도 계속 확인해줘야함
		if(nexti<0 || nextj<0 || nexti>=N || nextj>=N || map[nexti][nextj]==2) return false;
		else if(map[nexti][nextj]==0){ 
			if(white(now)) return true;
		}
		else if(map[nexti][nextj]==1) {
			if(red(now)) return true;
		}
		return false;
	}
	private static int changedir(int d) { //방향 바꾸기 메소드
		if(d == 1) d = 2;
		else if(d == 2) d = 1;
		else if(d == 3) d = 4;
		else d = 3;
		return d;
	}
	static class horse{
		int num,i,j,dir;
		public horse(int num,int i, int j, int dir) {
			this.num = num;
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "horse [num=" + num + ", i=" + i + ", j=" + j + ", dir=" + dir + "]";
		}
	}
}
