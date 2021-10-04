import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

public class Sender {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        String messageFile;
        String otherInput ;

        System.out.print("Input the name of the message file: ");
        messageFile = scan.nextLine();

        // basically says name of the file we need to grab

        File M = new File(messageFile);
            // since this is asking for a path and file name, this WILL vary
            // this also assumes M is a text file, so watch out
            // 1 ascii char = 1 byte, make each part a small multiple of 1024 bytes

        Scanner fileReader = new Scanner(M);
            while (fileReader.hasNextLine()){
                // Something something, read data

                //String data = fileReader.nextLine();
                //System.out.println(data);
            } // Read it piece by piece? idk what that means, but sure


        System.out.println("Do you want to invert the 1st byte in SHA256(M)? (y or n)");
            otherInput = scan.next();
                // could probably just be a Char

            if(otherInput == "y" || otherInput == "Y"){

            } else if (otherInput == "n" || otherInput == "N"){

            }
            // if yes, invert, else, go on.

        //Calculate RSA then AES (this should be a call from keygen class i think?)
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

}
