package Frequential.view;

import java.util.Scanner;

/**
 * @author 56133 Leong Paeg-Hing
 *         56514 Akturk Yohan
 */
public class View {
    
    private final Scanner in;

    public View() {
        this.in = new Scanner(System.in);
    }
    
    public String askUser() {
        System.out.println("If you don't remember the command, type 'help all' or 'help caesar' or 'help vigenere' to get the command.");
        System.out.println("If you want to know the current directory of the executable, type 'dir'.");
        System.out.println("If you want to stop the program, type 'exit'.");
        System.out.println("Type a command : ");
        return in.nextLine();
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome user.");
        System.out.println("This program can cipher and decipher (with or without key)" + 
        " a text using Caesar's cipher or Vigenere's cipher.");
        System.out.println("");
    }
    
    public void displayGoodbyeMessage() {
        System.out.println("Goodbye user.");
    }
    
    public void displayCurrentDirectory() {
        System.out.println("");
        System.out.println(System.getProperty("user.dir"));
        System.out.println("");
    }
    
    public void displayRule() {
        System.out.println("");
        System.out.println("The different command to cipher or decipher a text correctly.");
        System.out.println("All arguments provided must be separated by a white space.");
    }
    
    public void displayAllOption(String manuel) {
        displayRule();
        if(manuel.equalsIgnoreCase("caesar")){
            System.out.println("");
            caesarCipherOption();
        }else if(manuel.equalsIgnoreCase("vigenere")) {
            System.out.println("");
            vigenereCipherOption();
        }else if(manuel.equalsIgnoreCase("all")){
            System.out.println("");
            caesarCipherOption();
            System.out.println("");
            vigenereCipherOption();
        }else {
            displayError("The help command is not correct.");
        }
    }

    public void caesarCipherOption() {
        System.out.println("Caesar cipher : ");
        System.out.println("");
        caeserCipherEncryption();
        caeserCipherDecryptionWithKey();
        caeserCipherDecryptionWithoutKey();
    }
    
    public void caeserCipherEncryption() {
        System.out.println("    Cipher : ");
        System.out.println("    <the path to your plain text> CC <key>");
        System.out.println("    Concrete example (considering that the file is in the directory of the executable): ");
        System.out.println("    MyPlainText.txt CC 3");
        System.out.println("    Your ciphered text will be in a file named CaesarCipher.txt in the directory of the executable.");
        System.out.println("");
    }
    
    public void caeserCipherDecryptionWithKey() {
        System.out.println("    Decipher with key : ");
        System.out.println("    <the path to your cypher text> CD <key>");
        System.out.println("    Concrete example (considering that the file is in the directory of the executable): ");
        System.out.println("    MyCypheredText.txt CD 3");
        System.out.println("    Your deciphered text will be in a file named CaesarDecipherWithKey.txt in the directory of the executable.");
        System.out.println("");
    }
    
    public void caeserCipherDecryptionWithoutKey() {
        System.out.println("    Decipher without key : ");
        System.out.println("    <the path to your cypher text> CD");
        System.out.println("    Concrete example (considering that the file is in the directory of the executable): ");
        System.out.println("    MyCypheredText.txt CD");
        System.out.println("    Your deciphered text will be in a file named CaesarDecipherWithoutKey.txt in the directory of the executable.");
        System.out.println("");
    }
    
    public void vigenereCipherOption() {
        System.out.println("Vigenere cipher : ");
        System.out.println("");
        vigenereCipherEncryption();
        vigenereCipherDecryptionWithKey();
        vigenereCipherDecryptionWithoutKey();
    }
    
    public void vigenereCipherEncryption() {
        System.out.println("    Cipher : ");
        System.out.println("    <the path to your plain text> VC <key>");
        System.out.println("    Concrete example (considering that the file is in the directory of the executable): ");
        System.out.println("    MyPlainText.txt VC mykey");
        System.out.println("    Your ciphered text will be in a file named VigenereCipher.txt in the directory of the executable.");
        System.out.println("");
    }
    
    public void vigenereCipherDecryptionWithKey() {
        System.out.println("    Decipher with key : ");
        System.out.println("    <the path to your cypher text> VD <key>");
        System.out.println("    Concrete example (considering that the file is in the directory of the executable): ");
        System.out.println("    MyCypheredText.txt VD mykey");
        System.out.println("    Your deciphered text will be in a file named VigenereDecipherWithKey.txt in the directory of the executable.");
        System.out.println("");
    }
    
    public void vigenereCipherDecryptionWithoutKey() {
        System.out.println("    Decipher without key : ");
        System.out.println("    <the path to your cypher text> VD");
        System.out.println("    Concrete example (considering that the file is in the directory of the executable): ");
        System.out.println("    MyCypheredText.txt VD");
        System.out.println("    Your deciphered text will be in a file named VigenereDecipherWithoutKey.txt in the directory of the executable.");
        System.out.println("");
    }
    
    public void displayError(String message) {
        System.out.println(message);
        System.out.println("");
    }
}
