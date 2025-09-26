import java.util.*;


//I faced different issues when implementing the padding, so I redid the functionality with help from online resources
public class bitsAndBytes{
	
	public static byte[] bitsToBytes(List<Integer> bits, int padding){
		int totalBits = bits.size() + padding;
		int byteCount = (totalBits+7)/8;
		byte[] returnList = new byte[byteCount];

		for(int i = 0; i<bits.size(); i++){
			int byteIndex = i/8;
			int bitPosition = 7-(i%8);
			returnList[byteIndex] |= (bits.get(i) << bitPosition);
		}

		return returnList;
	}

	public static List<Integer> bytesToBits(byte[] bytes, int padding){
		//System.out.println(padding);
		ArrayList<Integer> returnList = new ArrayList<>();
		
		for(byte b: bytes){
			for(int j = 7; j>=0; j--){
				returnList.add((b>>j) & 1);
			}
		}
		
		for(int i = 0; i<padding; i++){
			returnList.remove(returnList.size()-1);
		}
		return returnList;
	}
}