import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;

public class KeyGenerator{
    //TODOd create method to save the modulus and exponents to files
    public static void saveToFile(String fileName, BigInteger mod, BigInteger exp) throws IOException {

        System.out.println("Write to " + fileName + ": modulus = " +
                mod.toString() + ", exponent = " + exp.toString() + "\n");

        ObjectOutputStream oout = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(fileName)));

        try {
            oout.writeObject(mod);
            oout.writeObject(exp);
        } catch (Exception e) {
            throw new IOException("Unexpected error", e);
        } finally {
            oout.close();
        }
    }

    public static void saveTextToFile(String filename,String userIn) throws FileNotFoundException {
        System.out.println("Saving symmetric key to file Symmetric.key");

        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(userIn);
        }
    }

    public static void main(String[] args) throws Exception {
        //TODOd Create pair of RSA public and private keys for X
        SecureRandom random = new SecureRandom();
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024, random);
        KeyPair pair = generator.generateKeyPair();
        Key pubKeyX = pair.getPublic();
        Key privKeyX = pair.getPrivate();

        //TODOd Create pair of RSA public and private keys for Y
        SecureRandom randomTwo = new SecureRandom();
        KeyPairGenerator generatorTwo = KeyPairGenerator.getInstance("RSA");
        generatorTwo.initialize(1024, randomTwo);
        KeyPair pairTwo = generatorTwo.generateKeyPair();
        Key pubKeyY = pairTwo.getPublic();
        Key privKeyY = pairTwo.getPrivate();

        //TODOd Get the modulus and exponents for each RSA public and private key
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec publicKeySpecX = factory.getKeySpec(pubKeyX, RSAPublicKeySpec.class);
        RSAPrivateKeySpec privateKeySpecX = factory.getKeySpec(privKeyX, RSAPrivateKeySpec.class);
        RSAPublicKeySpec publicKeySpecY = factory.getKeySpec(pubKeyY, RSAPublicKeySpec.class);
        RSAPrivateKeySpec privateKeySpecY = factory.getKeySpec(privKeyY, RSAPrivateKeySpec.class);

        //TODOd Save the modules and exponents into files (find file names on Project1 pdf)
        saveToFile("XPublic.key", publicKeySpecX.getModulus(), publicKeySpecX.getPublicExponent());
        saveToFile("XPrivate.key", privateKeySpecX.getModulus(), privateKeySpecX.getPrivateExponent());
        saveToFile("YPublic.key", publicKeySpecY.getModulus(), publicKeySpecY.getPublicExponent());
        saveToFile("YPrivate.key", privateKeySpecY.getModulus(), privateKeySpecY.getPrivateExponent());

        //TODOd Take 16 characters from the user
        String symmetricKey;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("Please provide a 16 character symmetric key: ");
            symmetricKey = in.nextLine();
            if (symmetricKey.length() != 16) {
                System.out.println("You only provided " + symmetricKey.length() + " characters");
            }
        }
        while (symmetricKey.length() != 16);


        //TODOd save the users 16 character input to file called symmetric.key
        //This 16 character input will be used as the 128 bit AES symmetric key
        saveTextToFile("symmetric.key",symmetricKey);
    }
}
