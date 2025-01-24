package math;

public class JConstant {
	public enum type {
		PLACEHOLDER,
		DEFINED
	}
	public float constantValue;
	public type ConstantType;
	public char letter;
	public String index;
	public JConstant(float constructValue) {
		constantValue = constructValue;
		ConstantType = type.DEFINED;
	}
	public JConstant(char placeholder, String ind) {
		ConstantType = type.PLACEHOLDER;
		letter = placeholder;
		ind = index;
	}
}
