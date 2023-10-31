package Frequential.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 56133 Leong Paeg-Hing
 *         56514 Akturk Yohan
 */
public class VigenereCipher {

    private CaesarCipher caesar;

    public VigenereCipher(CaesarCipher caesar) {
        this.caesar = caesar;
    }
    
    /**
     * Cipher a text with the vigenere cipher.
     * 
     * @param inputFile the file that conatin the plain text.
     * @param outputFile the file that will contains the ciphered text.
     * @param key the key that will be used to cipher the text.
     * @throws IOException 
     */
    public void vigenereCipherEncode(String inputFile, String outputFile, String key) throws IOException {
        FileReader ifr = new FileReader(inputFile);
        BufferedReader ibr = new BufferedReader(ifr);
        key = key.toLowerCase();
        String text = ibr.readLine();
        ibr.close();
        FileWriter ofw = new FileWriter(outputFile);
        BufferedWriter obw = new BufferedWriter(ofw);
        int character;
        int keyCharacter;
        int keyIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            character = text.charAt(i);
            keyCharacter = key.charAt(keyIndex);
            int originalCharacterPos = character - 'a';
            int offset = keyCharacter - 'a';
            int newCharacterPos = (originalCharacterPos + offset) % 26;
            char newCharacter = (char) ('a' + newCharacterPos);
            obw.write(newCharacter);
            keyIndex++;
            keyIndex = keyIndex % key.length();
        }
        obw.flush();
        obw.close();
    }
    
    /**
     * Decipher a text tha has been ciphered with vigenere cipher with the help of the key.
     * 
     * @param inputFile the file that contain the ciphered text.
     * @param outputFile the file that will contains the deciphered text.
     * @param key the key to decipher the text.
     * @throws IOException 
     */
    public void vigenereCipherDecodeWithKey(String inputFile, String outputFile, String key) throws IOException {
        FileReader ifr = new FileReader(inputFile);
        BufferedReader ibr = new BufferedReader(ifr);
        key = key.toLowerCase();
        String text = ibr.readLine();
        ibr.close();
        FileWriter ofw = new FileWriter(outputFile);
        BufferedWriter obw = new BufferedWriter(ofw);
        int character;
        int keyCharacter;
        int keyIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            character = text.charAt(i);
            keyCharacter = key.charAt(keyIndex);
            int charAlphabetPos = character - 'a';
            int offset = 'z' - keyCharacter + 1;
            int newCharacterPos = (charAlphabetPos + offset) % 26;
            char newCharacter = (char) ('a' + newCharacterPos);
            obw.write(newCharacter);
            keyIndex++;
            keyIndex = keyIndex % key.length();
        }
        obw.flush();
        obw.close();
    }
    
    /**
     * Decipher an ciphered text without the key.
     * 
     * @param inputFile the file that contain the ciphered text.
     * @param outputFile the file that will contain the deciphered text.
     * @return the key that has been used to cipher the text.
     * @throws IOException 
     */
    public String decodeViegenereWithoutKey(String inputFile, String outputFile) throws IOException {
        int keyLenght = findKeylenght(inputFile);
        String key = "";
        for (int i = 0; i < keyLenght; i++) {
            key += this.caesar.caesarCipherDecodeFindLetter(inputFile, i, keyLenght);
        }
        vigenereCipherDecodeWithKey(inputFile, outputFile, key);
        return key;
    }
    
    /**
     * Find the key lenght when we try to decipher a text that has been ciphered with the vigenere cipher.
     * 
     * @param inputFile the file that contains the ciphered text.
     * @return the key lenght.
     * @throws IOException 
     */
    public int findKeylenght(String inputFile) throws IOException {
        double englishIC = 0.0667;
        ArrayList<Integer> possibleKeyLenght = new ArrayList<>();
        BufferedReader ibr = new BufferedReader(new FileReader(inputFile));
        int textLenght = ibr.readLine().length();
        ibr.close();
        ArrayList<long[]> subGroups;

        for (int i = 1; i <= textLenght / 2.0 + 1
                && possibleKeyLenght.size() <= 15; i++) {
            ibr = new BufferedReader(new FileReader(inputFile));
            subGroups = createSubGroup(i);
            int counter = 0;
            int character;
            while ((character = ibr.read()) != -1) {
                subGroups.get(counter % (i))[character - 'a']++;
                counter++;
            }
            ibr.close();
            double ICSum = 0;
            for (long[] a : subGroups) {
                double b = indexOfCoincidence(a);
                ICSum += b;
            }

            double ICAvg = ICSum / i;

            if (ICAvg >= englishIC) {
                possibleKeyLenght.add(i);
            }
        }
        int keyLenght = findGCD(possibleKeyLenght);
        return keyLenght;
    }
    
    /**
     * Create the sub-groups to decipher a text that has been ciphered with the vigenere cipher.
     * 
     * @param numberOfGroups the number of groups that must be created.
     * @return a list of groups.
     */
    public ArrayList<long[]> createSubGroup(int numberOfGroups) {
        ArrayList<long[]> subGroups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; i++) {
            long[] groups = new long[26];
            subGroups.add(groups);
        }
        return subGroups;
    }
    
    /**
     * Implementation of the index of coincidence formula.
     * 
     * the formula comes from this website : 
     * https://en.wikipedia.org/wiki/Index_of_coincidence#:~:text=The%20chance%20of%20drawing%20a,letter%20twice%20in%20a%20row.
     * 
     * @param letterFrequency the array that contains the frequency of the letters.
     * @return the index of coincidence.
     */
    public double indexOfCoincidence(long[] letterFrequency) {
        double value = 0;
        double textSize = 0;
        for (int i = 0; i < letterFrequency.length; i++) {
            double tmp = letterFrequency[i] * (letterFrequency[i] - 1);
            value = value + tmp;
            textSize += letterFrequency[i];
        }
        double txt = textSize * (textSize - 1);
        return value / txt;
    }

    static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
    
    /**
     * Find the true key lenght.
     * 
     * @param possibleKey all the possible size of key.
     * @return the true size of the key.
     */
    static int findGCD(List<Integer> possibleKey) {
        int result = possibleKey.get(0);
        for (int element : possibleKey) {
            result = gcd(result, element);

            if (result == 1) {
                return 1;
            }
        }

        return result;
    }
}