import java.math.BigInteger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.*;
import java.io.*;

public class Sender {
    private static int BUFFER_SIZE = 32*1024;

    public static void main(String[] args) throws Exception{
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
        String otherInput ;

        System.out.print("Input the name of the message file: ");
        messageFileString = scan.nextLine();
        String sha256Value = SHA256Convert(messageFileString);

        //System.out.println("THIS IS THE PRINT"+SHA256Convert(messageFileString));

        //include the SHA256 value into the message file provided by user
        FileWriter shaWriter = new FileWriter("message.dd",true);
        BufferedWriter bw = new BufferedWriter(shaWriter);
        bw.write(sha256Value);
        bw.close();
            // This block of code writes to message.dd; also doesn't append to the original text file
            // Don't tinker too much here unless you put a try/catch block


        File message = new File(messageFileString);
        //probably need to use BufferedInputStream here
        Scanner messageScan = new Scanner(message);

        while (messageScan.hasNextLine()){

            String data = messageScan.nextLine();
            System.out.println(data);

            //TURN SHA INTO BYTE HERE

            // Not sure if this should be a char or not yet. LAN: I think this is a byte array not a char array

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
        // 1 ascii char = 1 byte, make each part a small multiple of 1024 bytes



        System.out.println("Do you want to invert the 1st byte in SHA256(M)? (y or n)");
        otherInput = scan.next();


        if(otherInput.equals("y") || otherInput.equals("Y")){

            // DO SILLY STUFF HEREEEEEHGEHJKGBFJH

            // this should invert first byte

        } else if (otherInput.equals("n") || otherInput.equals("N")){
            System.out.println("Continuing.");

            // toHex(sha256Value);

        } else {
            System.out.println("Please input either 'y' or 'n'! ");
            otherInput = scan.next();

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

        byte[] hash = md.digest();

        System.out.println("digital digest hash value: ");

        for(int k = 0, j=0; k < hash.length; k++, j++){
            System.out.format("%2X", hash[k]);

            if(j >= 15){
                System.out.println("");
                j = 0;
            }
        }

        // We might have to put the try/catch block HERE!!!!!!!!

        return new String (hash);
    } // This is used to convert message to SHA256

    public static byte[] AESencrypt(byte[] symmetricKey, byte[] messageInput) throws Exception{

        SecretKey key = new SecretKeySpec(symmetricKey, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE,key);

        return cipher.doFinal(messageInput);
    }

    public static byte[] RSAencrypt(String messageInput, BigInteger exponent, BigInteger n) throws Exception{

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        RSAPrivateKeySpec rsaPriv = new RSAPrivateKeySpec(n, exponent);

        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(rsaPriv);

        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");

        cipher.init(Cipher.ENCRYPT_MODE, key);


        return cipher.doFinal(messageInput.getBytes());
    }

    public static byte[] hexToByte(String hexString){

        byte [] hexValue = new byte[hexString.length() /2];

        for (int i =0; i < hexValue.length / 2; i++){
            int index = i*2;

            int j = Integer.parseInt(hexString.substring(index, index + 2), 16);
            hexValue[i] = (byte) j;
        }

        return hexValue;
    }




}