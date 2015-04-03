


public class ShiftRegister {

	int[] register;
	
	ShiftRegister(int size) {
		register = new int[size];
	}
	
	int getBit(int pos) {
		return register[pos];
	}
	
	void setBit(int pos, int bit) {
		register[pos] = bit & 0x0001;	
	}
	
	void shiftRight() {
		for(int i=register.length-1; i>0; i--) {
			register[i] = register[i-1];
		}
		register[0] = 0;
	}
	
	void shiftLeft() {
		for(int i=0; i<register.length - 1; i++) {
			register[i] = register[i+1];
		}
		register[register.length - 1] = 0;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<register.length; i++) {
			sb.append(register[i] + " ");
		}
		return sb.toString();
	}
}

