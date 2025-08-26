package default_package;

public class DEMO {
	public int a=20;
	public static void main(String[] args) {
		//int a=20;
	//	System.out.println(a);

	}
 
}
class A extends DEMO
{
	int a=30;
	public static void main(String[] args) {
		
		A r= new A();
		r.t();
	
	}
	void t(){
		System.out.println(a);

	}
}
