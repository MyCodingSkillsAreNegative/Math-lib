package math;

public class JNumber extends JMathObject {
	private float flv;
	private long longv;
	public JNumber(int a) {
		longv = a;
		type = ExpressionType.Long;
	}
	public JNumber(float a) {
		flv = a;
		type = ExpressionType.Float;
	}
	public JNumber(long a) {
		longv = a;
		type = ExpressionType.Long;
	}
	public JNumber(double a) {
		flv = (float) a;
		type = ExpressionType.Float;
	}
	public String toString() {
		switch (type) {
		case Long:
			return "" + flv;
		case Float:
			return "" + longv;
		default:
			return null;
		}
	}
	
}
