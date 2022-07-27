package week1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_1051_김동욱 {
	static int[] dx = {1,1,0};
	static int[] dy = {0,1,1};
	static ArrayList<Integer> maxSquare(int N, int M,char[][] arr) {
		ArrayList<Integer> maxVal = new ArrayList<Integer>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				for(int l=1;l<M;l++)
				{

				int count =0;
				for(int k=0;k<dx.length;k++) {
					if(i+l*dy[k] <0 || i+l*dy[k] >=N || j+l*dx[k] <0 || j+l*dx[k] >=M)
						break;
					//System.out.printf("(%d,%d) -> (%d,%d)\n",i,j,i+l*dy[k],j+l*dx[k]);
					if(arr[i][j] == arr[i+l*dy[k]][j+l*dx[k]]) //arr[0+1*0][0+1*1]
					{

						count++;
						
					}
					else if(arr[i][j] != arr[i+l*dy[k]][j+l*dx[k]])
						break;
					if(count==3)
						maxVal.add(l+1);
				}
				
				}
				//System.out.println("--------------");
			}
		}
		if(maxVal.size()==0) {
			maxVal.add(1);
		}
		return maxVal;
	}
	public static void main(String[] args) throws Exception{
	Scanner sc = new Scanner(System.in);
	int N = sc.nextInt();
	int M = sc.nextInt();
	char[][] arr = new char[N][M];
	for(int i=0;i<N;i++) {
		String temp = sc.next(); //nextLine() method Issue. Use next();
		for(int j=0;j<M;j++) {
			arr[i][j] = temp.charAt(j);

		}
	}
	if(N ==1 || M ==1)
		System.out.println(1);
	else {
		ArrayList<Integer> answer = maxSquare(N,M,arr);
		Collections.sort(answer);
		System.out.println((int)Math.pow(answer.get(answer.size()-1), 2));
	}
	}

}
