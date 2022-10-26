package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_14267_김동욱 {
	static int N;
	static int M;
	static ArrayList<Integer>[] employees;
	static int[] praise;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		employees = new ArrayList[N + 1];
		praise = new int[N + 1];
		sb =new StringBuilder();
		for (int i = 0; i < N + 1; i++) {
			employees[i] = new ArrayList<Integer>();
		} // 초기화

		for (int i = 1; i < N + 1; i++) {
			int temp = sc.nextInt();
			if(temp<0 ) continue;
			employees[temp].add(i);
		} //adjList
		
		for(int i=0;i<M;i++) {
			praise[sc.nextInt()] += sc.nextInt();
		} // 칭찬 저장
		
		내리칭찬(1);
		for(int i=1;i<praise.length;i++) {
			sb.append(praise[i]+" ");
		}
		System.out.println(sb);
	}

	public static void 내리칭찬(int num) {
		for(int a : employees[num]) { // num을 직속상사로 데리고 있는 직원들을 순회
			praise[a]+= praise[num]; // 그 직원에 칭찬 저장
			내리칭찬(a);
		}
		
	}

}
