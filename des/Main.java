package des;

import java.io.*;

public class Main{
	public static void main(String[] args) throws Exception{
		int choice = 0;
		DES des = new DES();
		String key;
		do {
			printMenu();
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			choice = Integer.parseInt(input.readLine());

			//add a switch statement
			switch (choice) {
				case 1:
					System.out.println("Enter 64-bit plain text i.e 16 characters [A-Z 0-9]:");
					String plainText = input.readLine();
					System.out.println("Enter 64-bit key i.e 16 characters [A-Z 0-9]:");
					key = input.readLine();
					System.out.println("Getting the key....\nEncrypting plain text....\nDone!");
					System.out.println("Your Encrypted string is - " + des.encryptPlainText(plainText, key));
					break;
				case 2:
					System.out.println("Enter encryted data:");
					String cipherText = input.readLine();
					System.out.println("Enter 64-bit key i.e 16 characters [A-Z 0-9] to decrypt:");
					key = input.readLine();
					System.out.println("Getting the key....\nDecrypting cipher text....\nDone!");
					System.out.println("Your Decrypted string is - " + des.decryptCipherText(cipherText, key));
					break;

				case 3:
					choice = 3;
					break;
				default:
					System.out.println("Please enter appropriate index.");
			}
		}while (choice != 3);
		System.out.println("Application closed.");
    }

    public static void printMenu() {
		System.out.println("1.Encrypt plain text using DES(Data Encryption Standard)\n" +
				"2.Decrypt plain text using DES(Data Encryption Standard)\n"+
				"3.Exit");
		System.out.print("Enter your choice:");
	}
}