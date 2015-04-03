import java.util.stream.IntStream;



public class BiOD2 {

	public static void main(String[] a) throws Exception {
		
		IntStream key = IntStream.of(0,1,0,0, 1,1,1,0, 0,0,1,0, 1,1,1,1, 0,1,0,0, 1,1,0,1, 0,1,1,1, 1,1,0,0,
									 0,0,0,1, 1,1,1,0, 1,0,1,1, 1,0,0,0, 1,0,0,0, 1,0,1,1, 0,0,1,1, 1,0,1,0);
		IntStream frame = IntStream.of(1,1, 1,0,1,0, 1,0,1,1, 0,0,1,1, 1,1,0,0, 1,0,1,1);
		IntStream message = IntStream.of(0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0,
										 0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0);
		
		CipherA51 cipher = new CipherA51();
		cipher.initKey(key);
		cipher.initFrame(frame);
		cipher.shuffle();
		cipher.log();
		IntStream output = cipher.encode(message);
		output.forEach(bit -> System.out.print(bit));
		
	}
}
