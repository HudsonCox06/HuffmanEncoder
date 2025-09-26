import java.lang.*;
import java.util.*;
import java.io.*;

public class Puff{
	private static File selectedFile;
	private static HuffmanNode rootNode;
	private static byte[] encodedBytes;
	private static List<Byte> tempList;
	private static List<Integer> listOfBits;
	private static Integer padding;

	public static void main(String[] args){
		try{
			selectedFile = new File(args[0]);
			ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(selectedFile));
			rootNode = (HuffmanNode) fileIn.readObject();
			padding = (Integer) fileIn.readObject();
			Integer size = (Integer)fileIn.readObject();
			encodedBytes = new byte[size];
			encodedBytes = (byte[]) fileIn.readObject();
			fileIn.close();
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println("You must pass in a file as an argument!");
			e.printStackTrace();
		} catch(FileNotFoundException e){
			System.out.println("File not found!");
			e.printStackTrace();
		} catch(IOException e){
			System.out.println("I/O Exception: "+e.getMessage());
			e.printStackTrace();
		} catch(Exception e){
			System.out.println("Error while retrieving file: "+e.getMessage());
			e.printStackTrace();
		}
		
		listOfBits = bitsAndBytes.bytesToBits(encodedBytes, padding);
		tempList = new ArrayList<Byte>();

		//Traverse tree bit-by-bit find and assign bytes to temporary list
		HuffmanNode currentNode = rootNode;
		for(Integer bit : listOfBits){
			if(currentNode.hasChildren()){
				if(bit==1){
					currentNode = currentNode.getOne();
				} else{
					currentNode = currentNode.getZero();
				}
			}
			if(!currentNode.hasChildren()){
				tempList.add(currentNode.getContent());
				currentNode = rootNode;
			}
		}


		//Write decoded content to new file
		try{
			String outputFileName = args[0].replace(".huff", "");
			FileOutputStream decodedFile = new FileOutputStream("New_"+outputFileName);
			for(Byte b : tempList){
				decodedFile.write(b);
			}
			decodedFile.close();
			System.out.println("File Decoded Successfully!");
		} catch(IOException e){
			System.out.println("Error writing decoded file: "+e.getMessage());
			e.printStackTrace();
		}
		
	}

	
}