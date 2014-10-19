
public class Reference {

	public static void main(String[] args) {
		int [] a1 = {1,2,3,4,5,6};
		int [] a2;
		Integer [] a3 = {new Integer(1),new Integer(2),3,4,5,6};
		Integer [] a4;
		a2 = a1;
		a4 = a3;
		System.out.println("Before:");
		for(int i = 0; i < 6; i++){
			System.out.println("a1["+i+"] = "+a1[i]);
		}
		
		for(int i = 0; i < 6; i++){
			a2[i] = a2[i] + 1;
		}
		
		System.out.println("After:");
		for(int i = 0; i < 6; i++){			
			System.out.println("a1["+i+"] = "+a1[i]);
		}
		System.out.println("Before:");
		for(int i = 0; i < 6; i++){			
			System.out.println("a3["+i+"] = "+a3[i]);
		}
		System.out.println("After:");
		for(int i = 0; i < 6; i++){
			a4[i] = a4[i] + 1;
			System.out.println("a3[" + i +"] = "+a3[i]);
			
		}
	}

}
