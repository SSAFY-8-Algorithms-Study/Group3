package baek11286_절대값힙;

import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<num> pque = new PriorityQueue<num>(new Comparator<num>() {
			public int compare(num n1,num n2) {
				if(Math.abs(n1.n) > Math.abs(n2.n)) return 1;
				else if(Math.abs(n1.n) < Math.abs(n2.n)) return -1;
				else return n1.n - n2.n;
			}// 우선순위큐 생성, 우선순위 조건이 절대값 가장 작고, 절댓값 같으면 더 음수 출력
		});
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			int o = Integer.parseInt(br.readLine());
			if(o != 0) { // 0이 아닌 값이면 큐에 값 추가
				pque.add(new num(o));
			}
			else if(o == 0) {//0이 들어오면 우선순위 큐 값 출력
				if(pque.isEmpty()) {
					System.out.println(0);
					continue;
				}
				System.out.println(pque.poll().n);
			}
		}
		
	}

}
class num{
	int n;
	num(int n){
		this.n = n;
	}
}