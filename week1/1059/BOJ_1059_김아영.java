package home0727;
import java.util.Arrays;
import java.util.Scanner;
public class test1059 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();
		int []arr=new int[L + 1];
		for(int i=0;i<L;i++) {
			arr[i] = sc.nextInt();
		}
		int n = sc.nextInt();
		arr[L] = 0;
		Arrays.sort(arr);
		int MIN = 0;
		int MAX = 0;
		for(int i = 1; i<L + 1;i++) {
			if (arr[i-1] < n && arr[i] > n) {
				MIN = arr[i-1] + 1;
				MAX = arr[i] - 1;
			}
		}
	
		if (MIN == 0 && MAX == 0) {
			System.out.println(0);
		}
		else {
			int sum = 0;
			int cal = MAX - n + 1;
			sum = sum + cal * (n - MIN);
			sum = sum + MAX - n;
			System.out.println(sum);
		}
		
	}
}
