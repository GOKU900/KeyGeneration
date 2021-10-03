import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class KeyGenerator{
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
        generator.initialize(1024, randomTwo);
        KeyPair pairTwo = generatorTwo.generateKeyPair();
        Key pubKeyY = pair.getPublic();
        Key privKeyY = pair.getPrivate();
        //TODOd Get the modulus and exponents for each RSA public and private key
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec publicKeySpecX = factory.getKeySpec(pubKeyX,RSAPublicKeySpec.class);
        RSAPrivateKeySpec privateKeySpecX = factory.getKeySpec(privKeyX,RSAPrivateKeySpec.class);
        RSAPublicKeySpec publicKeySpecY = factory.getKeySpec(pubKeyY,RSAPublicKeySpec.class);
        RSAPrivateKeySpec privateKeySpecY = factory.getKeySpec(privKeyY,RSAPrivateKeySpec.class);
        //TODO Save the modules and exponents into files (find file names on Project1 pdf)

        //TODO Take 16 characters from the user

        //TODO save the users 16 character input to file called symmetric.key
        //This 16 character input will be used as the 128 bit AES symmetric key
    }
}
