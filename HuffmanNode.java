import java.util.*;
import java.io.*;

public class HuffmanNode implements Comparable<HuffmanNode>, Serializable{
	HuffmanNode parent;
	HuffmanNode zero;
	HuffmanNode one;
	int freq;
	Byte byteContent;

	public HuffmanNode(){
		parent = null;
		zero = null;
		one = null;

	}

	public HuffmanNode(int frequency, byte byteContent){
		parent = null;
		zero = null;
		one = null;
		freq = frequency;
		this.byteContent = byteContent;
	}

	public HuffmanNode(HuffmanNode zero, HuffmanNode one, int frequency){
		parent = null;
		this.zero = zero;
		this.one = one;
		freq = frequency;
		byteContent = null;
	}

	public HuffmanNode(HuffmanNode zero, HuffmanNode one, int frequency, byte byteContent){
		parent = null;
		this.zero = zero;
		this.one = one;
		freq = frequency;
		this.byteContent = byteContent;
	}

	public HuffmanNode(HuffmanNode parent, HuffmanNode zero, HuffmanNode one, int frequency, byte byteContent){
		this.parent = parent;
		this.zero = zero;
		this.one = one;
		freq = frequency;
		this.byteContent = byteContent;
	}

	public void setParent(HuffmanNode newParent){
		parent = newParent;
	}

	public void setZero(HuffmanNode newZero){
		zero = newZero;
	}

	public void setOne(HuffmanNode newOne){
		one = newOne;
	}

	public boolean hasChildren(){
		if(zero!=null || one!=null){
			return true;
		} else{
			return false;
		}
	}

	public Byte getContent(){
		return byteContent;
	}

	public int getFreq(){
		return freq;
	}

	public HuffmanNode getZero(){
		return zero;
	}

	public HuffmanNode getOne(){
		return one;
	}

	@Override
	public int compareTo(HuffmanNode other){
		return Integer.compare(this.freq, other.freq);
	}
}