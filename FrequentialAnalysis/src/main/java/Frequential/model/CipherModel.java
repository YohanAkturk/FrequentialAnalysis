package Frequential.model;

import java.io.File;
import java.io.IOException;

/**
 * @author 56133 Leong Paeg-Hing
 *         56514 Akturk Yohan
 */
public class CipherModel
{

    private CaesarCipher caesar;
    private VigenereCipher vigenere;

    public CipherModel() {
        this.caesar = new CaesarCipher();
        this.vigenere = new VigenereCipher(this.caesar);
    }

    public void whichCipher(String[] arguments) {

        if (arguments.length < 2) {
            throw new IllegalArgumentException("There is not enough arguments or the arguments are wrong.");
        }

        if (arguments[1].equalsIgnoreCase("cc") && arguments.length == 3) {
            caesarCipher(arguments);
        } else if (arguments[1].equalsIgnoreCase("cd")) {
            caesarDecipher(arguments);
        } else if (arguments[1].equalsIgnoreCase("vc") && arguments.length == 3) {
            vigenereCipher(arguments);
        } else if (arguments[1].equalsIgnoreCase("vd")) {
            vigenereDecipher(arguments);
        } else {
            throw new IllegalArgumentException("The command is incorrect.");
        }
    }

    private void caesarCipher(String[] arguments) {
        try {
            // Create the temporary file for the preprocess text.
            fileCreator("tmpProcessFile.txt");
            Preprocess.preprocessFile(arguments[0], "tmpProcessFile.txt", true);
            this.caesar.caesarCipher("tmpProcessFile.txt", "CaesarCipher.txt", Integer.parseInt(arguments[2]) % 26);
            System.out.println("");
            System.out.println("The text was successfully ciphered.");
            System.out.println("You can find the result in the file CaesarCipher.txt.");
            System.out.println("");
        } catch (NumberFormatException | IOException ex) {
            System.out.println("The key must be a number or the input file doesn't exist");
        }
    }

    private void caesarDecipher(String[] arguments) {
        try {
            if (arguments.length == 2) {
                int key = this.caesar.caesarCipherDecodeWithoutKey(arguments[0], "CaesarDecipherWithoutKey.txt");
                System.out.println("");
                System.out.println("The text was successfully deciphered.");
                System.out.println("You can find the result in the file CaesarDecipherWithoutKey.txt.");
                System.out.println("The key used was " + key);
                System.out.println("");
            } else {
                this.caesar.caesarCipher(arguments[0], "CaesarDecipherWithKey.txt", 26 - Integer.parseInt(arguments[2]) % 26);
                System.out.println("");
                System.out.println("The text was successfully deciphered.");
                System.out.println("You can find the result in the file CaesarDecipherWithKey.txt.");
                System.out.println("");
            }
        } catch (NumberFormatException | IOException ex) {
            System.out.println("The key must be a number or the input file doesn't exist");
        }
    }

    private void vigenereCipher(String[] arguments) {
        try {
            if (!isVigenereKeyCorrect(arguments[2])) {
                throw new IllegalArgumentException("The key must only contains letter.");
            }
            // Create the temporary file for the preprocess text.
            fileCreator("tmpProcessFile.txt");
            Preprocess.preprocessFile(arguments[0], "tmpProcessFile.txt", true);
            this.vigenere.vigenereCipherEncode("tmpProcessFile.txt", "VigenereCipher.txt", arguments[2]);
            System.out.println("");
            System.out.println("The text has been successfully ciphered.");
            System.out.println("You can find the result in the file VigenereCipher.txt.");
            System.out.println("");
        } catch (IOException ex) {
            System.out.println("The input file doesn't exist.");
        }
    }

    private void vigenereDecipher(String[] arguments) {
        try {
            if (arguments.length == 2) {
                String key = this.vigenere.decodeViegenereWithoutKey(arguments[0], "VigenereDecipherWithoutKey.txt");
                System.out.println("");
                System.out.println("The text was successfully deciphered.");
                System.out.println("You can find the result in the file VigenereDecipherWithoutKey.txt.");
                System.out.println("The key used was " + key);
                System.out.println("");
            } else {
                if (!isVigenereKeyCorrect(arguments[2])) {
                    throw new IllegalArgumentException("The key must only contains letter.");
                }
                this.vigenere.vigenereCipherDecodeWithKey(arguments[0], "VigenereDecipherWithKey.txt", arguments[2]);
                System.out.println("");
                System.out.println("The text was successfully deciphered.");
                System.out.println("You can find the result in the file VigenereDecipherWithKey.txt.");
                System.out.println("");
            }
        } catch (IOException ex) {
            System.out.println("The input file can't be found.");
        }
    }

    private boolean isVigenereKeyCorrect(String key) {
        for (int i = 0; i < key.length(); i++) {
            if (!Character.isLetter(key.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private void fileCreator(String name) throws IOException {
        File file = new File(".\\" + name);
        file.createNewFile();
    }
}