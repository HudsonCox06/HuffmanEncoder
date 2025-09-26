import java.lang.*;
import java.io.*;
import java.util.*;

public class Huff{
	private static File selectedFile;
	private static FileInputStream fileIn;
	private static byte[] byteArray;
	private static HashMap<Byte, Integer> bytes;
	private static PriorityQueue<HuffmanNode> pQueue;
	private static HashMap<Byte, String> bitSequences;
	private static List<Integer> fileAsBits;

	public static void main(String[] args){
		try{
			selectedFile = new File(args[0]);
			fileIn = new FileInputStream(selectedFile);
			byteArray = new byte[fileIn.available()];
			fileIn.read(byteArray);
			fileIn.close();
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println("You must pass in a file as an argument!");
			e.printStackTrace();
		} catch(FileNotFoundException e){
			System.out.println("File not found: "+e.getMessage());
			e.printStackTrace();
		} catch(Exception e){
			System.out.println("Exception when retrieving file: "+e.getMessage());
			e.printStackTrace();
		}

		//Assign frequency for each byte
		bytes = new HashMap<Byte, Integer>();
		for(int i = 0; i<byteArray.length; i++){
			byte currentByte = byteArray[i];
			if(bytes.containsKey(currentByte)){
				bytes.put(currentByte, bytes.get(currentByte)+1);
			} else{
				bytes.put(currentByte, 1);
			}
			
		}
		System.out.println("Frequency Assigned!");

		//Create Nodes and place into Priority Queue
		pQueue = new PriorityQueue<HuffmanNode>();
		Set<Byte> keySet = bytes.keySet();
		int size = keySet.size();
		for(Byte b : keySet){
			pQueue.add(new HuffmanNode(bytes.get(b), b));
		}
		System.out.println("Nodes and Priority Queue created!");

		//Build Huffman Tree according to byte frequency
		HuffmanNode zero;
		HuffmanNode one;
		while(pQueue.size()>1){
			zero = (HuffmanNode)pQueue.poll();
			one = (HuffmanNode)pQueue.poll();
			int value = zero.getFreq()+one.getFreq();
			HuffmanNode newParent = new HuffmanNode(zero, one, value);
			zero.setParent(newParent);
			one.setParent(newParent);
			pQueue.add(newParent);
		}
		System.out.println("Huffman Tree built!");

		//Next, run through each byte in the tree and determine its Huffman bit sequence
		//REMOVE THIS
		if(pQueue.size()!=1){
			System.out.println("Error, Huffman Tree not properly constructed");
		}
		bitSequences = new HashMap<>();
		HuffmanNode root = pQueue.peek();
		assignBitSequence(root, "");
		System.out.println("Bit Sequences created!");

		//Traverse through file content again and add adjacent bit sequences to new List
		fileAsBits = new ArrayList<Integer>();
		for(int i = 0; i<byteArray.length; i++){
			String bitSequence = bitSequences.get(byteArray[i]);
			for(char bit : bitSequence.toCharArray()){
				fileAsBits.add(bit - '0');
			}
		}
		System.out.println("List of bit Sequences created!");

		//Get byte list
		Integer padding = (8 - (fileAsBits.size() % 8)) % 8;
		byte[] fullyEncodedFile = bitsAndBytes.bitsToBytes(fileAsBits, padding);

		//Write encodeed data to new .huff File
		try{
			ObjectOutputStream encodedFile = new ObjectOutputStream(new FileOutputStream(args[0]+".huff"));
			encodedFile.writeObject(root);
			encodedFile.writeObject(padding);
			encodedFile.writeObject(Integer.valueOf(fullyEncodedFile.length));
			encodedFile.writeObject(fullyEncodedFile);
			encodedFile.close();
			System.out.println("File Encoded Successfuly!");
		} catch(IOException e){
			e.printStackTrace();
		}




	}


	public static void assignBitSequence(HuffmanNode currentNode, String bitSequence){
		if(currentNode == null){
			return;
		}
		if(!currentNode.hasChildren()){
			bitSequences.put(currentNode.getContent(), bitSequence);
			return;
		} else{
			assignBitSequence(currentNode.getZero(), bitSequence+"0");
			assignBitSequence(currentNode.getOne(), bitSequence+"1");
		}
	}
}