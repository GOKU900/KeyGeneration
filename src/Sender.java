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

        File M = new File(messageFile + ".txt");
            // since this is asking for a path and file name, this WILL vary
            // this also assumes M is a text file, so watch out

        Scanner fileReader = new Scanner(M);
            while (fileReader.hasNextLine()){
                // Something something, read data

                //String data = fileReader.nextLine();
                //System.out.println(data);
            } // Read it piece by piece? idk what that means, but sure


        System.out.println("Do you want to invert the 1st byte in SHA256(M)? (y or n)");
            otherInput = scan.nextLine();
                // could probably just be a Char

            if(otherInput == "y"){

            } else {

            }
            // if yes, invert, else, go on.

        //Calculate RSA then AES (this should be a call from keygen class i think?)
    }

}
