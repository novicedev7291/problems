package tips;

public classBitwiseApp {

	public static void main(String[] args){
		BitwiseApp app = new BitwiseApp();
		System.out.println(app.count(15));
		
	}
	
	public int count(int n){
		int count = 0;
		while(n > 0){
			n = n & (n-1);
			System.out.println(n);
			count++;
		}
		return count;
	}
}
