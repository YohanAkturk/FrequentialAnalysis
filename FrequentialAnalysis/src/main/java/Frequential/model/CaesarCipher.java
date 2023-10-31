package Frequential.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 56133 Leong Paeg-Hing
 *         56514 Akturk Yohan
 */
public class CaesarCipher {

    /**
     * Cipher a text using the caesar cipher with a key/shift.
     * 
     * @param inputFile the input file that contains the plain text.
     * @param outputFile the output file that will contain the ciphered text.
     * @param key the key for the caesar cipher.
     * @throws IOException
     */
    public void caesarCipher(String inputFile, String outputFile, int key) throws IOException {
        FileReader ifr = new FileReader(inputFile);
        BufferedReader ibr = new BufferedReader(ifr);
        String text = ibr.readLine();
        ibr.close();
        FileWriter ofw = new FileWriter(outputFile);
        BufferedWriter obw = new BufferedWriter(ofw);
        int character;
        for (int i = 0; i < text.length(); i++) {
            character = text.charAt(i);
            int originalAlphabetPosition = character - 'a';
            int newAlphabetPosition = (originalAlphabetPosition + key) % 26;
            char newCharacter = (char) ('a' + newAlphabetPosition);
            obw.write(newCharacter);
        }
        obw.flush();
        obw.close();
    }
    
    /**
     * Decipher a text that has been ciphered with the caesar cipher.
     * 
     * @param inputFile the file that contains the ciphered text.
     * @param outputFile the file that will contains the ciphered text.
     * @return the key used to cipher the text.
     * @throws IOException 
     */
    public int caesarCipherDecodeWithoutKey(String inputFile, String outputFile) throws IOException {
        FileReader ifr = new FileReader(inputFile);
        BufferedReader ibr = new BufferedReader(ifr);
        long[] observation = new long[26];
        double[] standardTableLetterFrequency = createEnglishLetterFrequencyTable();
        int character;
        double textSize = 0;
        while ((character = ibr.read()) != -1) {
            textSize++;
            observation[character - 'a']++;
        }

        ibr.close();

        double[] expected = new double[26];

        for (int i = 0; i < standardTableLetterFrequency.length; i++) {
            expected[i] = textSize * standardTableLetterFrequency[i];
        }

        double result = chiSquareStat(expected, observation);
        int shift = 0;
        shiftValueInArray(observation);
        for (int i = 1; i < observation.length; i++) {
            double tmpValue = chiSquareStat(expected, observation);
            if (tmpValue < result) {
                result = tmpValue;
                shift = i;
            }
            shiftValueInArray(observation);
        }
        caesarCipher(inputFile, outputFile, shift);
        return 26 - shift;
    }
    
    /**
     * Create the table of english letter frequency.
     * 
     * The frequencies data come from this website :
     * https://en.wikipedia.org/wiki/Letter_frequency
     * 
     * @return the table of english letter frequency.
     */
    public double[] createEnglishLetterFrequencyTable() {
        double[] standardLetterFrequency = new double[]{0.082, 0.015, 0.027, 0.047, 0.13, 0.022, 0.02, 0.062, 0.069,
            0.0016, 0.0081, 0.04, 0.027, 0.067, 0.078, 0.019, 0.0011, 0.059,
            0.062, 0.096, 0.027, 0.0097, 0.024, 0.0015, 0.02, 0.0078};
        return standardLetterFrequency;
    }
    
    /**
     * Shift the array to the right.
     * 
     * @param array the array that will be shift.
     */
    public void shiftValueInArray(long[] array) {
        long tmpValue = array[array.length - 1];
        for (int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = tmpValue;
    }
    
    /**
     * Implementation of chi square test.
     * 
     * The formula come from this website:
     * https://www.cuemath.com/chi-square-formula/
     * 
     * @param expected the expected value.
     * @param observation the observed value.
     * @return the difference between the two array of values.
     */
    public double chiSquareStat(double[] expected, long[] observation) {
        double chiSquare = 0;
        for (int i = 0; i < expected.length; i++) {
            double tmpValue = observation[i] - expected[i];
            chiSquare += Math.pow(tmpValue, 2) / 2;
        }
        return chiSquare;
    }
    
    /**
     * Caesar cipher used to find the letter when we found the key lenght with vigenere cipher.
     * 
     * @param inputFile the input file.
     * @param begin the first letter of the text.
     * @param textOffset the offset that is the key lenght.
     * @return one of the letter of the key.
     * @throws IOException 
     */
    public char caesarCipherDecodeFindLetter(String inputFile, int begin,
            int textOffset) throws IOException {
        FileReader ifr = new FileReader(inputFile);
        BufferedReader ibr = new BufferedReader(ifr);
        long[] observation = new long[26];
        double[] standardTableLetterFrequency = createEnglishLetterFrequencyTable();
        int character;
        int index = 0;
        double textSize = 0;
        while ((character = ibr.read()) != -1) {
            if ((index % textOffset) == begin) {
                observation[character - 'a']++;
                textSize++;
            }
            index++;
        }

        ibr.close();

        double[] expected = new double[26];

        for (int i = 0; i < standardTableLetterFrequency.length; i++) {
            expected[i] = textSize * standardTableLetterFrequency[i];
        }

        double result = chiSquareStat(expected, observation);
        int shift = 0;
        shiftValueInArray(observation);
        for (int i = 1; i < observation.length; i++) {
            double tmpValue = chiSquareStat(expected, observation);
            if (tmpValue < result) {
                result = tmpValue;
                shift = i;
            }
            shiftValueInArray(observation);
        }
        int letterPosition = (26 - shift) % 26;
        char guessCharacter = (char) ('a' + letterPosition);
        return guessCharacter;
    }
}
