import java.util.stream.IntStream;


public class CipherA51 {

	private ShiftRegister regA = new ShiftRegister(19);
	private ShiftRegister regB = new ShiftRegister(22);
	private ShiftRegister regC = new ShiftRegister(23);

	// Initialize with arrays
	/*CipherA51(int[] key, int[] frame) throws Exception{
		initKey(key);
		initSession(frame);
		shuffle();
	}
	
	private void initKey(int[] key) {
		for(int bit : key) {
			pushBit(bit);
		}
	}
	
	private void initSession(int[] session) {
		for(int bit : session) {
			pushBit(bit);
		}
	}*/
	
	// Initialize with streams
	
	CipherA51() {}
	
	public void initKey(IntStream key) {
		key.forEach(bit -> pushBit(bit));
	}
	
	public void initFrame(IntStream frame) {
		frame.forEach(bit -> pushBit(bit));
	}
	
	public void shuffle() {
		for(int i=0; i<100; i++) {
			clock();
		}
	}
	
	IntStream encode (IntStream message) {
		return message.map( bit ->  bit ^ clock() );
	}
	
	private int clock() {
		int output = xor(regA.getBit(18), xor(regB.getBit(21), regC.getBit(22)));
		int majority = majority();
		clockA(majority);
		clockB(majority);
		clockC(majority);
		return output;
	}
	
	private void pushBit(int bit) {
		int bitA = xor(bit, getBitA());
		regA.shiftRight();
		regA.setBit(0, bitA);
		
		int bitB = xor(bit, getBitB());
		regB.shiftRight();
		regB.setBit(0, bitB);
		
		int bitC = xor(bit, getBitC());
		regC.shiftRight();
		regC.setBit(0, bitC);
		
	}
	
	private int getBitA() {
		return  xor(regA.getBit(13), xor(regA.getBit(16), xor(regA.getBit(17), regA.getBit(18))));
	}
	
	private int getBitB() {
		return xor(regB.getBit(20), regB.getBit(21));
	}
	
	private int getBitC() {
		return xor(regC.getBit(7), xor(regC.getBit(20), xor(regC.getBit(21), regC.getBit(22))));
	}
	
	private void clockA(int majority) {
		if (regA.getBit(8) == majority) {
			int bit = getBitA();
			regA.shiftRight();
			regA.setBit(0, bit);
		}
	}
	
	private void clockB(int majority) {
		if (regB.getBit(10) == majority) {
			int bit = getBitB();
			regB.shiftRight();
			regB.setBit(0, bit);
		}
	}
	
	private void clockC(int majority) {
		if (regC.getBit(10) == majority) {
			int bit = getBitC();
			regC.shiftRight();regC.setBit(0, bit);
		}
	}
	
	private int majority() {
		int sum = regA.getBit(8) + regB.getBit(10) + regC.getBit(10);
		if (sum < 2) {
			return 0;
		} else {
			return 1;
		}
	}
	
	private int xor(int a, int b) {
		return a ^ b;
	}
	
	public void log() {
		System.out.println(regA);
		System.out.println(regB);
		System.out.println(regC);
		System.out.println();
	}
}
