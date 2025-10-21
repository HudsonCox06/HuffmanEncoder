**Huffman Encoder (Java)**

A simple yet efficient Huffman Coding implementation in Java — a classic compression algorithm that reduces file size by assigning shorter binary codes to more frequent characters.

This project demonstrates understanding of data compression, tree-based algorithms, and file I/O operations in Java.

**Features**
- Reads input text files and encodes them using Huffman’s algorithm
- Generates optimal binary codes for each character
- Outputs compressed data to a new file
- Decodes compressed data back into its original form
- Displays frequency tables, Huffman tree structure, and compression ratio
- Includes error handling for invalid or missing files

**How It Works**
1. Frequency Analysis – The program counts how often each character appears.
2. Tree Building – A priority queue builds the Huffman tree by joining least-frequent nodes.
3. Code Generation – Each character receives a unique binary string based on its path in the tree.
4. Encoding – Input text is replaced with its binary codes.
5. Decoding – The encoded data is translated back using the Huffman tree.

**What I Learned**
- Implementing binary trees and priority queues for efficient data structures
- File reading/writing and bit-level encoding in Java
- Understanding lossless compression principles
- Designing modular code with encapsulation and reusability in mind
- Debugging and verifying compression correctness through test cases

**How to Run**
1. Clone this repository:

_git clone https://github.com/HudsonCox06/HuffmanEncoder.git_

_cd HuffmanEncoder_
2. Compile:

_javac *.java_
3. Run the enconder:

_java HuffmanEncoder input.txt encoded.huff_
4. Run the decoder:

_java HuffmanDecoder encoded.huff output.txt_

(Make sure you replace the file names with your actual input and output files.)

**Future Enhancements**
- Add graphical visualization of the Huffman tree
- Support for binary and image file compression
- Improve efficiency for large files (streamed I/O)
- Include benchmark comparison vs. standard ZIP compression

**Author**
**Hudson Cox**

📧 hudsoncox56@gmail.com

💼 LinkedIn
 https://www.linkedin.com/in/hudsoncox/
