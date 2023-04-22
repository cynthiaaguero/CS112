package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.sound.midi.Soundbank;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    private String fileName;
    private ArrayList<CharFreq> sortedCharFreqList;
    private TreeNode huffmanRoot;
    private String[] encodings;

    /**
     * Constructor used by the driver, sets filename
     * DO NOT EDIT
     * @param f The file we want to encode
     */
    public HuffmanCoding(String f) { 
        fileName = f; 
    }

    /**
     * Reads from filename character by character, and sets sortedCharFreqList
     * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
     */
    public void makeSortedList() {

        StdIn.setFile(fileName);
        char value; //creates a current char
        double charCounter = 0; //counts characters; last character is already accounted for
        int ASCII[] = new int[128]; //creates an array that has every ASCII value and counts how many times each appear
        sortedCharFreqList = new ArrayList<CharFreq>(); //creates new sorted array list

        while (StdIn.hasNextChar()){
            value = StdIn.readChar(); //grabs the current char 
            ASCII[value]++; //goes to the char index and adds to its occurance  
            charCounter++;
        }

        for(int i = 0; i < ASCII.length; i++){
            double probOcc = ASCII[i]/charCounter; //calculates the occurance 
            char charry = (char)i; //convert i to a character 
            if (probOcc > 0.0){ //if the character ever appears in file
                CharFreq fhar = new CharFreq(charry, probOcc); //creates a CharFreq which stores a char and its frequency 
                sortedCharFreqList.add(fhar); //adds CharFreq to array list
            }
        }
        if (sortedCharFreqList.size() == 1){ //if there is only one CharFreq in array list 
            char charry = sortedCharFreqList.get(0).getCharacter(); //get the char at index 0 
            if ((int)charry == 127){ //if the char is the 127th value in ASCII
                CharFreq fhar = new CharFreq((char)0, 0); //create fhar
                sortedCharFreqList.add(fhar); //add fhar to list
            }
            else{
                CharFreq fhar = new CharFreq((char)(sortedCharFreqList.get(0).getCharacter()+1), 0);//create fhar
                sortedCharFreqList.add(fhar); //add fhar to list 
 
            }
        }
        Collections.sort(sortedCharFreqList); //sort list 
    }

    /**
     * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
     * in huffmanRoot
     */
    public void makeTree() {

        Queue<CharFreq> source = new Queue<CharFreq>(); //create queue source
        Queue<TreeNode> target = new Queue<TreeNode>(); //create queue target

        for (int i = 0; i < sortedCharFreqList.size(); i++){ //puts all the nodes created in method 1 into the source
            source.enqueue(sortedCharFreqList.get(i));
        }
        while (source.isEmpty() != true || target.size() > 1){ //while there are still nodes in source and target doesnt have only one node, 
                if (target.isEmpty()){ //if target is empty
                    CharFreq firstski = source.dequeue(); //set firstski equal to smaller of 2 smallest values
                    CharFreq secondski = source.dequeue(); //set secondski equal to larger value of 2 smallest values
                    CharFreq tempy = new CharFreq(null,secondski.getProbOcc() + firstski.getProbOcc()); //create temp CharFreq that computes; will be parent & root

                    TreeNode first = new TreeNode(firstski, null, null); //store smaller value into new TreeNode
                    TreeNode second = new TreeNode(secondski, null, null); //store larger value into new TreeNode

                    huffmanRoot = new TreeNode(tempy, first, second); //set tempy as the root

                    target.enqueue(huffmanRoot); //add to target 
                }
                else {
                    CharFreq firstski = new CharFreq(); //make a CharFreq that carries the data of the smallest size ProbOcc
                    CharFreq secondski = new CharFreq();//make a CharFreq that carries the data of the second smallest size ProbOcc
                    CharFreq parento = new CharFreq(); //make a parent that holds the info of first plus second
                    TreeNode left = new TreeNode(); //make a treenode that holds firstski (will be parent's child)
                    TreeNode right = new TreeNode(); //make a treenode that holds secondski (will be parent's child)

                    for (int i = 0; i < 2; i++){ //for loop that goes two times to grab the two smallest variables in source & target 
                        if (i == 0 && source.isEmpty()){ //source is empty for the first and second itt.
                            firstski = target.peek().getData(); //set first as the first out in the target queue 
                            left = (target.dequeue()); //set first out as left and remove from target queue 
                            secondski = target.peek().getData(); //set second as the second out in target queue 
                            right = (target.dequeue()); //remove second out and set it to right child
                            break; //break out of for loop 
                        }
                        if (i == 1 && source.isEmpty()){ //source is empty for only the second itt.
                            secondski = target.peek().getData(); //set second out as secondski
                            right = (target.dequeue());  //remove second out and set as right child
                            break; //break out of for loop 
                        }

                        if(target.isEmpty() && i==1)
                        {
                            secondski = source.dequeue();
                            right = new TreeNode(secondski, null , null);
                            break;
                        }


                        if (i == 0){ //if firstski has nothing in it
                            if (target.peek().getData().getProbOcc() < source.peek().getProbOcc()){ //if target holds the smallest, 
                                firstski = target.peek().getData(); //then peek at the first out and set its data to firstski
                                left = target.dequeue(); //remove the first out and set to left child
                            }
                            else if (target.peek().getData().getProbOcc() >= source.peek().getProbOcc()){ //if source holds the smallest OR if target and source are equal, 
                                firstski = source.dequeue(); //remove first out in source and set to first
                                left = new TreeNode(firstski, null, null); //set left child to firstski 
                            }
                        }
                        if(i == 1) { //if firstski has something in it
                            if (target.peek().getData().getProbOcc() >= source.peek().getProbOcc()){ //if source holds the smallest
                                secondski = source.dequeue(); //remove from source and set to secondski 
                                right = new TreeNode(secondski, null, null); //set right child to secondski 
                            }
                            else if (target.peek().getData().getProbOcc() < source.peek().getProbOcc()){ //if target holds the smallest, 
                                secondski = target.peek().getData(); //then peek at the first out and set its data to firstski
                                right = target.dequeue(); //remove the first out and set to left child
                            }
                        }
                    }
                    parento.setProbOcc(firstski.getProbOcc() + secondski.getProbOcc()); //set parent's proabablity occurance to firstski+secondski
                    huffmanRoot = new TreeNode(parento, left, right); //change the huffmanRoot to parent with left and right child

                    target.enqueue(huffmanRoot);//add new root to target node 
                }
        }
    }


    /**
     * Uses huffmanRoot to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null.
     * Set encodings to this array.
     */
    public void makeEncodings() {
        encodings = new String[128];
        TreeNode root = huffmanRoot;
        String key = "";
        searchy(root, key);
    }

    private void searchy(TreeNode root, String key){
        if (root.getLeft() == null && root.getRight() == null){
            int i = (int)root.getData().getCharacter();
            encodings[i] = key;
        }
        if (root.getLeft() != null){
            String kii = new String(key + '0');
            searchy(root.getLeft(), kii);
        }
        if (root.getRight() != null){
            String kii = new String(key + '1');
            searchy(root.getRight(), kii);

        }
    }
    /**
     * Using encodings and filename, this method makes use of the writeBitString method
     * to write the final encoding of 1's and 0's to the encoded file.
     * 
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public void encode(String encodedFile) {
        StdIn.setFile(fileName);
        String s = "";
        while (StdIn.hasNextChar()){
            s = s + encodings[StdIn.readChar()];
        }
        writeBitString(encodedFile, s);
    }
    
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * DO NOT EDIT
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                return;
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }

    /**
     * Using a given encoded file name, this method makes use of the readBitString method 
     * to convert the file into a bit string, then decodes the bit string using the 
     * tree, and writes it to a decoded file. 
     * 
     * @param encodedFile The file which has already been encoded by encode()
     * @param decodedFile The name of the new file we want to decode into
     */
    public void decode(String encodedFile, String decodedFile) {

        StdOut.setFile(decodedFile);
        String swag = readBitString(encodedFile);
        TreeNode root = huffmanRoot; 
        TreeNode pointer = root;
        for (int i = 0; i < swag.length();i++){
            
            if (swag.charAt(i) == '0'){
                pointer = pointer.getLeft();
            }
            if (swag.charAt(i) == '1') {
                pointer = pointer.getRight();
            }
            if (pointer.getLeft() == null && pointer.getRight() == null){
                StdOut.print(pointer.getData().getCharacter());
                pointer = root; 
            }
        }
    }

    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * DO NOT EDIT
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /*
     * Getters used by the driver. 
     * DO NOT EDIT or REMOVE
     */

    public String getFileName() { 
        return fileName; 
    }

    public ArrayList<CharFreq> getSortedCharFreqList() { 
        return sortedCharFreqList; 
    }

    public TreeNode getHuffmanRoot() { 
        return huffmanRoot; 
    }

    public String[] getEncodings() { 
        return encodings; 
    }
}
