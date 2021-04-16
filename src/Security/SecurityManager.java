package Security;

import org.apache.commons.codec.binary.Hex;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SecurityManager{

    //The number of iterations the hashing algorithm goes through
    private final int iterations = 10000;

    //The length of the key 
    private final int keyLength = 512;

    
    //This method uses the user's entered password and id(which will function as the salt) to create a hashed password using
    //the PBKDF2 hashing algorithm
    public String hashPassword(String password, int userid) {

        //Converts the user id to a string
        String salt=Integer.toString(userid);

        //Converts the password and userid to byte and character arrays to be used in the algorithm
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();
        
        try{
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(passwordChars,saltBytes,iterations,keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            String hashedString = convertByteHashToString(res);
            return hashedString;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new RuntimeException( e );
        }
    }

    //Used to format the Byte array returned by hashPassword() as a String

    private String convertByteHashToString(byte[] hashedPassword){

        String hashedString = Hex.encodeHexString(hashedPassword);
        return hashedString;
    }

}