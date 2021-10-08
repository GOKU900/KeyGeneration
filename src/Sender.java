import java.math.BigInteger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.*;
import java.io.*;

public class Sender {
    private static int BUFFER_SIZE = 32*1024;

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        //get the modulus and exponent from the XPrivate.key file
        //NOTE: when uploading to the class server we are going to need the entire file path
        FileInputStream keyFile = new FileInputStream("XPrivate.key");
        ObjectInputStream privateKeyObject = new ObjectInputStream(keyFile);
        BigInteger modulus = (BigInteger) privateKeyObject.readObject();
        BigInteger exponent = (BigInteger) privateKeyObject.readObject();
        privateKeyObject.close();

        //Create the private key spec
        RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(modulus,exponent);

        //create a key factory
        KeyFactory factory = KeyFactory.getInstance("RSA");

        //create the RSA private key
        PrivateKey privateKeyX = factory.generatePrivate(privateSpec);

        //create a symmetric key from symmetric.key file for AES algorithm
        File symmetricKeyFile = new File("symmetric.key");
        Scanner sc = new Scanner(symmetricKeyFile);
        String sKey = sc.nextLine();
        byte[] byteKey = new byte[128];
        SecretKey secretKey = new SecretKeySpec(sKey.getBytes(StandardCharsets.UTF_8),"AES");
        //Cipher cipher = Cipher.getInstance("AES");

        Scanner scan = new Scanner(System.in);
        String messageFileString;
        //String otherInput ;

        System.out.print("Input the name of the message file: ");
        messageFileString = scan.nextLine();
        String sha256Value = SHA256Convert(messageFileString);

        //include the SHA256 value into the message file provided by user
        FileWriter shaWriter = new FileWriter(messageFileString,true);
        BufferedWriter bw = new BufferedWriter(shaWriter);
        bw.write(sha256Value);
        bw.close();




















        // basically says name of the file we need to grab

        File M = new File(messageFileString);
        //probably need to use BufferredInputStream here
        Scanner mReader = new Scanner(M);

            while (mReader.hasNextLine()){

                String data = mReader.nextLine();
                System.out.println(data);

                // Not sure if this should be a char or not yet. LAN: I think this is a byte array not a char array
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

            try{
                FileWriter ddWrite = new FileWriter("message.dd");
                ddWrite.write(sha256Value);
                ddWrite.close();

                System.out.println("File write successful.");

            } catch (Exception e){

                System.err.println("Error. File does not exist.");
                e.printStackTrace();
            }


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



    public static String SHA256Convert(String messageInput) throws NoSuchAlgorithmException, IOException {
        BufferedInputStream file = new BufferedInputStream(new FileInputStream(messageInput));
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        DigestInputStream in = new DigestInputStream(file,md);
        int i;
        byte[] buffer = new byte[BUFFER_SIZE];
        do{
            i = in.read(buffer,0,BUFFER_SIZE);
        } while(i == BUFFER_SIZE);
        md = in.getMessageDigest();
        in.close();

        return new String(md.digest());
    } // This is used to convert message to SHA256

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