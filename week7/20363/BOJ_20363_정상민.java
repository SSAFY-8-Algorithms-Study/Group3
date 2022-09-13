import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int X = Integer.parseInt(st.nextToken()); //햇빛   
		int Y = Integer.parseInt(st.nextToken()); // 물    
		int result=0,rx=0,ry=0;
		int sun=0,water=0;
		int flag=0;
		if(X>Y) {
			result = X;
			rx=X;
			sun = X % 10;
			while(rx != X || ry != Y) {
				if(flag==0) { //  물주는곳     
					result += Y - ry;
					rx -= (water + Y - ry) / 10;
					water = (water + Y - ry) % 10;
					ry = Y;
					flag = 1;
				} 
				else { //   햇빛 주는 곳    
					result += X - rx;
					ry -= (sun + X - rx) / 10;
					sun = (sun + X - rx) % 10;
					rx = X;
					flag = 0;
				}
			}
		}
		else {
			result = Y;
			ry=Y;
			water = Y % 10;
			while(rx != X || ry != Y) { 
				if(flag==1) {  //  물   
					result += Y - ry;
					rx -= (water + Y - ry) / 10;
					water = (water + Y - ry) % 10;
					ry = Y;
					flag = 0;
				}
				else { // 햇 빛    
					result += X - rx;
					ry -= (sun + X - rx) / 10;
					sun = (sun + X - rx) % 10;
					rx = X;
					flag = 1;
				}
			}
		}
		System.out.println(result);
	}

}
