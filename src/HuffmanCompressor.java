import java.io.*;
import java.util.*;

public class HuffmanCompressor {
	private static int[] characterFrequency;

	public static void compress(String fileName) {
		characterFrequency = new int[256];
		try {
			Scanner scanner = new Scanner(new File(fileName + ".txt"));
			while (scanner.hasNextLine()) {
				String string = scanner.nextLine();
				for (int i = 0; i < string.toCharArray().length; i++) {
					characterFrequency[(int) string.charAt(i)]++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HuffmanTree tree = new HuffmanTree(characterFrequency);
		tree.write(fileName);
		tree.encode(new BitOutputStream(fileName + ".short"), fileName);
	}
	
	public static void expand(String cFile, String oFile)
	{
		HuffmanTree tree = new HuffmanTree(cFile);
		tree.decode(new BitInputStream(oFile + ".short"), oFile + ".new");
		
	}

	public static void main(String[] args) {
		compress("happy hip hop");
		expand("happy hip hop.code", "happy hip hop");
	}
}