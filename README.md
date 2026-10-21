# metcs622_Assignment4_Yuexian_Feng

## Description

This project finds duplicate images in the `pics` folder using Java and a Bash shell script.

The Java program uses a Lambda expression to search for JPG, JPEG, and PNG files. It calculates SHA-1 hashes and uses a HashMap to compare them and identify duplicate images.

The Java program also uses ProcessBuilder to execute `FindDuplicates.sh`.

## How to Run

1. Open the project in IntelliJ IDEA.
2. Make sure the images are in the `pics` folder.
3. Run `RunScriptWithProcess.java`.

To run the shell script directly, open the terminal in the project directory and enter:

/bin/bash FindDuplicates.sh

## Testing

I tested the program with five JPG images:

- Dog.jpg
- Cat.jpg
- Cat-copy.jpg
- Penguin.jpg
- Flower.jpg

`Cat.jpg` and `Cat-copy.jpg` are identical copies.

Both the Java program and the shell script successfully detected the duplicate images.

## Results

Java output:

Java duplicate found: Cat-copy.jpg and Cat.jpg

Shell script output:

Duplicate found: ./pics/Cat-copy.jpg and ./pics/Cat.jpg

The shell script also saved the results in `duplicates_2026-10-19.log`.

## Technologies Used

- Java 21
- Bash Shell Script
- SHA-1 Hashing
- Lambda Expression
- HashMap
- ProcessBuilder