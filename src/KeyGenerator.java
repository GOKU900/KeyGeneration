import java.security.*;

public class KeyGenerator{
    public static void main(String[] args) throws Exception {
        //TODO Create pair of RSA public and private keys for X
        SecureRandom random = new SecureRandom();
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024, random);
        KeyPair pair = generator.generateKeyPair();
        Key pubKeyX = pair.getPublic();
        Key privKeyX = pair.getPrivate();

        //TODO Create pair of RSA public and private keys for Y

        //TODO Get the modulus and exponents for each RSA public and private keys

        //TODO Save the modules and exponents into files (find file names on Project1 pdf)

        //TODO Take 16 characters from the user

        //TODO save the users 16 character input to file called symmetric.key
        //This 16 character input will be used as the 128 bit AES symmetric key
    }
}
