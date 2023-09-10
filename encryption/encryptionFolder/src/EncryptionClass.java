import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Omer
 * Date: Feb.2021
 * Description: This class taked a phrase as input, encrypt, or decrypt it based on which method is called from the UI.
 * Method List:  
 *  public static void main(String[] args) 
 *  private static String readFile() 
 *  private static String encrypt(String input, int shift) 
 *  private static String decrypt(String input, int shift)
 *  private static boolean isNotALetter(char letter)
 *  private static char encode(char letter, int key) 
 *  private static char decode(char letter, int key)
 */public class EncryptionClass {
	 public static void main(String[] args) {

		 int key = 5; // declare key 
		 String input = readFile(); // read file 
		 System.out.println("Input: "+ input); // show the input taken from the input 
		 System.out.println("Encrypt: "+ encrypt(input, key)); // encrypt method to encrypt text file 
		 System.out.println("Decrypt: "+ decrypt(encrypt(input, key), key)); // decrypt method to decrypt text file 
	 }	 

	 public static String readFile() { // read file method 
		 String result = ""; // declare result 
		 try
		 {
			 File file = new File("Phrases.txt"); // read from phrases text 
			 Scanner reader = new Scanner(file);  // scan the file 
			 while (reader.hasNextLine())  // while loop 
			 {
				 result += reader.nextLine() + "\n"; // read line and slash n 
			 }
			 reader.close(); // close 
		 } 
		 // catch  all the potential errors
		 catch (FileNotFoundException e)
		 {
			 System.out.println("An error reading file."); // show the error message 
			 e.printStackTrace();
		 }
		 return result; // return 
	 }

	 public static String encrypt(String input, int key) { // method for encrypt
		 String encrypted = ""; 

		 for(int i = 0; i < input.length(); i++) // for loop 
		 {
			 //Ternary oprator is used to assign value to encrypted variable. 
			 //If condition is true it takes value after ?, else it takes value after :
			 //isNotLetter will return true if character is not a letter, and false if it is a letter
			 //If not a letter, no need to encrypt it, append character directly to encrypted string
			 //If character is a letter, than append the encrypted character to encrypted string
			 encrypted = isNotALetter(input.charAt(i)) ? 
					 encrypted + input.charAt(i) :
						 encrypted + encode(input.charAt(i), key);
		 }

		 return encrypted; // return the encrypted
	 }


	 public static String decrypt(String input, int key) { //method for decrypt
		 String decrypted = "";  // declare decrypted

		 for(int i = 0; i < input.length(); i++) { // for loop 
			 // same thing explained in the encrypt method 
			 decrypted = isNotALetter(input.charAt(i)) ?
					 decrypted + input.charAt(i) :
						 decrypted + decode(input.charAt(i), key);
		 }

		 return decrypted; // return the decrypted 
	 }

	 public static boolean isNotALetter(char letter) { // Not a letter method 
		 // if the letter is less than or equal to 64 or letter is greater than 91 and less than 96 or greater than or equal to 123 then, it is not a letter 
		 return (letter <= 64) || (letter >= 91 && letter <= 96) || (letter >= 123); 
	 }

	 public static char encode(char letter, int key) {  // returns an encrypted character

		 //Normalized key gives you the actual key or wrap around key in case key is bigger than number 
		 // of letters count which is 26
		 //Actual Key example: 5 % 26 gives you 5
		 //Wraparound example: 32 % 26 gives you 6. That means letter needs to move 6 places from start
		 int normalizedKey = key % 26;
		 char encrypted = (char)(letter + normalizedKey);
		 if((encrypted > 'Z' && letter <= 'Z') || (encrypted > 'z' && letter <= 'z')) //  if statement for upper and lower character 
			 encrypted = (char)(encrypted - 26);

		 return encrypted; // return the encypted  
	 }

	 public static char decode(char letter, int key) { // returns a decrypted character

		 int normalizedKey = key % 26; // same thing as explained in the the encode method 
		 char decrypted = (char)(letter - normalizedKey);
		 if((decrypted < 'A' && letter >= 'A') || (decrypted < 'a' && letter >= 'a')) // if statement for upper and lower character 
			 decrypted = (char)(decrypted + 26);

		 return decrypted; // return the decrypted  
	 }
 }
