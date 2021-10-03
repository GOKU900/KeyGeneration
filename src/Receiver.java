import java.io.*;
import java.util.*;
public class Receiver {
    public static void main(String [] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        String fileName;
        System.out.println("Please enter name of the message file");
        fileName = scan.nextLine();
        // This should be to find the file, but maybe don't make it like this

        File keyGen = new File (fileName);

        Scanner fileReader = new Scanner (keyGen);
            while (fileReader.hasNextLine()){
                // This is reading the data again

                //String message = fileReader.nextLine();
                //System.out.println(message)
            } // This should read the cyphertext, C


        // Calculate AES decryption of C with Kxy

        try (FileWriter writeFile = new FileWriter("message.ds-msg")) {
            BufferedWriter output = new BufferedWriter(writeFile);
                output.write("This is where the file contents go");
                output.close();
        } catch (IOException e){
            System.err.println("Error");
        } // This whole thing should be able to output the decryption to a file
    }

    // Read first 128 bytes from message.ds-msg for digital signature and copy message M

    //Calculate RSA decryption using Kx+ and save into file named message.dd and output into console in hexadecimal

    // Read message M piece by piece; calculate SHA256 of all of M; output to console in hexadecimal bytes
    // Compare with SHA256 digital digest; return if true or not
}
