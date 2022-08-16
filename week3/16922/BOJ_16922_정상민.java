package baek16922_로마숫자만들기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main { // 중복 순열이네
	static int[] card = {1,5,10,50};
	static int result;
	static int N;
	static Set<Integer> set = new HashSet<Integer>();//set이라는 자료구조사용, 중복허용X
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i1=0;i1<=N;i1++) { // 1개수 0~N
			for(int i5=0;i5<=N-i1;i5++) { // 5개수 0~ N-1개수
				for(int i10=0;i10<=N-i1-i5;i10++) { // 10개수 0~ N-1개수-5개수
					for(int i50=0;i50<=N-i1-i5-i10;i50++) { // 50개수 0~ N-1개수-5개수-10개수
						if(i1+i5+i10+i50 == N) { //개수 합이 N개 일때 합 set에 추가
							set.add((i1*1)+(i5*5)+(i10*10)+(i50*50)); //set에 추가하면서 중복값 X
						}
					}
				}
			}
		}
		System.out.println(set.size());
	}
}
