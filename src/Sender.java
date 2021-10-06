import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

public class Sender {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        String messageFileString;
        String otherInput ;



        System.out.print("Input the name of the message file: ");
        messageFileString = scan.nextLine();

        // basically says name of the file we need to grab

            File M = new File(messageFileString);
            Scanner mReader = new Scanner(M);

            while (mReader.hasNextLine()){

                String data = mReader.nextLine();
                System.out.println(data);

                // Not sure if this should be a char or not yet.
                // Will have to read piece by piece.
            }

            try {
                File messageDD = new File("message.dd");
                if (messageDD.createNewFile()){
                    System.out.println("File " + messageDD.getName() +" successfully created.");
                } else {
                    System.out.println("This file already exists!");
                }
            } catch (Exception e){

                System.err.println("Error");
                e.printStackTrace();
            } // this DOES in fact create the file, but adds nothing to it


            // since this is asking for a path and file name, this WILL vary
            // this also assumes M is a text file, so watch out
            // 1 ascii char = 1 byte, make each part a small multiple of 1024 bytes
            // I think this should be a Char array also


        System.out.println("Do you want to invert the 1st byte in SHA256(M)? (y or n)");
        otherInput = scan.nextLine();


        if(otherInput == "y" || otherInput == "Y"){


            // this should invert first byte

        } else if (otherInput == "n" || otherInput == "N"){

        } else {
            System.out.println("Please input either 'y' or 'n'! ");
            otherInput = scan.nextLine();
        }
        // if yes, invert, else, go on.
        //Calculate RSA of SHA256 with Kx-

        // then AES of RSA-En(SHA256(M)||M) using Kxy
        // (this should be a call from keygen class i think?) save into file message.aescipher
    }



    public static byte[] SHAconvert(String input) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    } // This is used to convert text to SHA

    public static String toHex(byte[] hash){

        BigInteger integer = new BigInteger(1,hash);

        StringBuilder hexString = new StringBuilder(integer.toString(16));

        while (hexString.length() < 32){

            hexString.insert(0, '0');
        }

        // add invert first byte portion

        return hexString.toString();
    }

    static int gcd (){

        return 0;
            // Change that
    }

}