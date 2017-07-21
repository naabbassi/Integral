package integral;

public class Main {
	public static final double Increment = 1E-3;
	
	public static void main(String[] args) {
		System.out.println(integral(0,3, x-> {
		 return Math.sin(x);
		}));
	}
	
	public static double integral(double a, double b, Function function){
		double area = 0;
		double modifier = 1;
		if(a > b){
			double tempA = a;
			a = b;
			b = tempA;
			modifier = -1;
		}
		for(double i = a + Increment ; i <= b; i+=Increment){
			double dFromA = i - a;
			area += (Increment / 2) * (function.f(a + dFromA) + function.f(a + dFromA - Increment));
			
		}
		return((Math.round(area * 1000.0)) / 1000.0) * modifier;
	}

}
