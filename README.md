# Frequential Analysis #

## What is this project ? ##

This project is a simple program that can cipher and decipher a text with or without a key using Caesar's cipher or Vigenere's cipher.

## Language used to write the program ##

This program was mainly written in java.

## Project tree structure ##
```bash
│   .gitignore
│   README.md
│
└───FrequentialAnalysis
    │   Makefile
    │   MakefileMaven.mak
    │   pom.xml
    │
    └───src
        └───main
            └───java
                └───Frequential
                    ├───app
                    │       Main.java
                    │
                    ├───controller
                    │       Controller.java
                    │
                    ├───model
                    │       CaesarCipher.java
                    │       CipherModel.java
                    │       Preprocess.java
                    │       VigenereCipher.java
                    │
                    └───view
                            View.java
```

## How to build and execute the program ? ##

### Without make installed ###

If you don't have make install on your computer but have java installed, the first step is to go into the FrequentialAnalysis directory. Then you can compile and create a jar manually with the following commands.  
To compile the source files :
```bash
javac -d ./target/classes src/main/java/Frequential/view/*.java src/main/java/Frequential/model/*.java src/main/java/Frequential/controller/*.java src/main/java/Frequential/app/*.java 
```
Then to create a jar file :
```bash
jar --create --file frequentialAnalysis-1.0.jar --main-class Frequential.app.Main -C target/classes/ .
```
Finally you can execute the jar file with the following command:
```bash
java -jar ./frequentialAnalysis-1.0.jar
```

### With make installed ###

If you have make installed on your computer, then the first step, go to the FrequentialAnalysis directory. 
Inside we provide you with 2 makefiles that will compile and create a jar file.
The first one can be used if you just have a JVM. To execute the first makefile, just type in the terminal :
```bash
make
```

While the second makefile (MakefileMaven) is to be used if and only if you have maven installed.
If you have maven installed, just type in the terminal :
```bash
make -f ./MakefileMaven
```

After running one of the two makefiles, a jar file will be create in the current directory and to execute the jar file just type in the terminal :
```bash
java -jar ./frequentialAnalysis-1.0.jar
```

## How the program works ? ##

<p>
The program will ask you to enter a command to cipher or decipher a text with or without a key using the Caesar cipher or the Vigenere cipher. You can also type 'exit' to stop the program.
</p>
<p>
The file path you provide to the command must either be an absolute path or relative path. If you use a relative path, this path must be relative to the current directory of the executable (for this you can type 'dir' in the terminal to display the absolute path to the current directory of the executable).
</p>
<p>
In the following points, we will explain how to properly enter the command to cipher or decipher a text. (These explanations can also be found in the program when you type 'help all', 'help caesar' or 'help vigenere' in the terminal if you don't remember the command).
</p>  
<b>
Important note : the file you provide to the program must be encoded in UTF-8 and the text must be an English text. To decipher without key, the text must be large enough and for the path to the file, the path must also not contain spaces. If you want to decipher a ciphered text, this ciphered text must contain only lower case letters and no white space.
</b>

### Caesar cipher ###

For the Caesar cipher, you can cipher a text with a key, decipher a text with a key or decipher a text without the key.  
**The key used must be a number.**

#### Cipher ####

If you want to cipher a text, you must follow the following format : 
```bash
<Path of the file containing the plain text> CC <Key>
```
Concrete example (considering that the file is in the directory of the executable):
```bash 
MyPlainText.txt CC 3
```
This will cipher the text using a key of 3.
A file named CaesarCipher.txt in the directory of the executable will be created which contains the ciphered text.

#### Decipher with key ####

If you want to decipher a ciphered text with a key, you must follow the following format : 
```bash
<Path of the file containing the ciphered text> CD <Key>
```
Concrete example (considering that the file is in the directory of the executable):
```bash 
MyCipheredText.txt CD 3
```
This will decipher the ciphered text that has been cipher with a key of 3.
A file named CaesarDecipherWithKey.txt in the directory of the executable will be created which contains the deciphered text.

#### Decipher without key ####

If you want to decipher a ciphered text without key, you must follow the following format : 
```bash
<Path of the file containing the ciphered text> CD
```
Concrete example (considering that the file is in the directory of the executable):
```bash 
MyCipherText.txt CD
```
This will decipher the ciphered text and give the key that was used to cipher the text.
A file named CaesarDecipherWithoutKey.txt in the directory of the executable will be created which contains the deciphered text.

### Vigenere cipher ###

For the Vigenere cipher, you can cipher a text with a key, decipher a text with a key or decipher a text without the key.  
**The key used must contain only letters.**

#### Cipher ####

If you want to cipher a text, you must follow the following format : 
```bash
<Path of the file containing the plain text> VC <Key>
```
Concrete example (considering that the file is in the directory of the executable):
```bash 
MyPlainText.txt VC mykey
```
This will cipher plain text with the help of a key (mykey in this case).
A file named VigenereCipher.txt in the directory of the executable will be created which contains the ciphered text.

#### Decipher with key ####

If you want to decipher a ciphered text with a key, you must follow the following format : 
```bash
<Path of the file containing the ciphered text> VD <Key>
```
Concrete example (considering that the file is in the directory of the executable):
```bash 
MyCipherText.txt VD mykey
```
This will decipher the ciphered text with the help of a key (mykey in this case).
A file named VigenereDecipherWithKey.txt in the directory of the executable will be created which contains the deciphered text.

#### Decipher without key ####

If you want to decipher a ciphered text without key, you must follow the following format : 
```bash
<Path of the file containing the ciphered text> VD
```
Concrete example (considering that the file is in the directory of the executable):
```bash 
MyCipherText.txt VD
```
This will decipher the ciphered text and give the key that was used to cipher the text.
A file named VigenereDecipherWithoutKey.txt in the directory of the executable will be created which contains the deciphered text.

## How the decipher without key works ? ##

### Caesar cipher ###

To break the Caesar cipher, the Chi square formula is used (https://www.cuemath.com/chi-square-formula/). The Chi square formula compares 2 distributions and the smaller the calculated value, the more likely it is that the 2 distributions are similar.

### Vigenere cipher ###
<p>
To break the Vigenere cipher, we calculate the index of coincidence (https://en.wikipedia.org/wiki/Index_of_coincidence#:~:text=The%20chance%20of%20drawing%20a,letter%20twice%20in%20a%20row.) for each hypothetical key size and compare it to the average index of coincidence of English. If the calculated value is greater than or equal to the average index of coincidence of English, then there is a chance that the hypothetical key size is the key size or a multiple of that key size.
</p>
<p>
Once we have the size, we will use the Caesar cipher to find each letter of the key.
</p>

## contributors ##
<p>
Leong Paeg-Hing 56133
</p>
<p>
Akturk Yohan 56514
</p>
