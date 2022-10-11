package boj;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_17143_김동욱 {
	// 아이디어 : 상어 Point만듬
	// 사용 할 변수 : R,C, int board[][], Point, fisherman,
	// 만들 함수 : catchFish(), moveShark();
	static int R;
	static int C;
	static int M;
	static int board[][];
	static int answer;
	static PriorityQueue<Point> sharks;
	public static class Point implements Comparable<Point>{
		int x;
		int y;
		int speed;
		int dir;
		int size;
		boolean isEaten=false;
		public Point() {}
		public Point(int x, int y,int speed,int dir, int size) {
			super();
			this.x = x;
			this.y = y;
			this.speed=speed;
			this.dir=dir;
			this.size=size;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", speed=" + speed + ", dir=" + dir + ", size=" + size + "]";
		}
		
		@Override
		public int compareTo(Point o) {
			
			return o.size-this.size;
		}
		
	}
	public static int convertDirection(int dir) {
		switch(dir) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return -1;
	}
	public static void catchFish(int fisherman) { //board를 0으로 만들고 moveShark에서 상어가 움직일때 board 가 0이면 움직이지 않고 삭제
		for(int i=0;i<R;i++) {
			if(board[i][fisherman]!=0) {
				answer+= board[i][fisherman];
				board[i][fisherman]=0;
				return;
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		R= sc.nextInt();
		C = sc.nextInt();
		M =sc.nextInt();
		sharks = new PriorityQueue<>();
		board =new int[R][C];
		answer=0;
		for(int i=0;i<M;i++) {
			int r=sc.nextInt()-1;
			int c = sc.nextInt()-1;
			int s = sc.nextInt();
			int d= sc.nextInt();
			int z = sc.nextInt();
			board[r][c]= z;
			sharks.add(new Point(r,c,s,d,z));
		} // end input
		
		int fisherman=-1;

		
		print();
		while(true) {
			fisherman++;
			if(fisherman>C-1) {
				break;
			}
			catchFish(fisherman);
			sharks = moveShark();
			sharks= spreadShark(sharks);
			System.out.println(fisherman);
			print();
		}
		System.out.println(answer);
	}
	public static void print() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("__________");
	}
	public static PriorityQueue<Point> spreadShark(PriorityQueue<Point> tq) {
		int[][] tempBoard= new int[R][C];
		PriorityQueue<Point> returnList = new PriorityQueue<Point>();
		for(Point p : tq) {
			if(tempBoard[p.x][p.y]!=0) {
				if(tempBoard[p.x][p.y] >p.size) { //기존에 있던 상어가 크다면
					continue;
				}
			}
			tempBoard[p.x][p.y]= p.size;
			returnList.add(p);
		}
		board=tempBoard;
		return returnList;
	}
	private static PriorityQueue<Point> moveShark() { //상어를 움직이는데 다 움직이게 한 다음에 board에 뿌려주자
		PriorityQueue<Point> tq = new PriorityQueue<>();
	for(Point s: sharks) {
		
		int sharkX = s.x;
		int sharkY =s.y;
		if(board[sharkX][sharkY]==0)continue;
		//System.out.println("First pos "+ sharkX+" "+ sharkY+" "+s.dir); // 상어 넘겼음
		if(s.dir==1) { //위 아래 오른쪽 왼쪽
			sharkX-=s.speed;
		}
		if(s.dir==2) {
			sharkX+=s.speed;
		}
		if(s.dir==3) {
			sharkY+=s.speed;
		}
		if(s.dir==4) {
			sharkY-=s.speed;
		}
		//System.out.println("Before "+ sharkX+" "+ sharkY); // 상어 넘겼음
		while(true) {
			if(sharkX>=0 && sharkX<R && sharkY>=0 && sharkY<C) {
				break;
			}
			if(s.dir==1) {
				sharkX = (0-sharkX);
				s.dir = convertDirection(s.dir);
				continue;
			}
			if(s.dir==2) {
				sharkX = (R-1) -(sharkX-(R-1));
				s.dir = convertDirection(s.dir);
				continue;
			}
			if(s.dir==3) {
				sharkY = (C-1) -(sharkY-(C-1));
				s.dir = convertDirection(s.dir);
				continue;
			}
			if(s.dir==4) {
				sharkY = (0-sharkY);
				s.dir = convertDirection(s.dir);
				continue;
			}
		}
		tq.add(new Point(sharkX,sharkY,s.speed,s.dir,s.size));
		
	}
	
		return tq;
	}

}
