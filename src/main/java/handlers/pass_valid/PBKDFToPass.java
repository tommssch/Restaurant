package handlers.pass_valid;
import lombok.SneakyThrows;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;

public class PBKDFToPass {
@SneakyThrows
public static String generatePassHash(String pass){
    int iterations=1000;
    byte[] salt= getSalt();
    PBEKeySpec spec= new PBEKeySpec(pass.toCharArray(),salt,iterations,64*8);
    SecretKeyFactory skf= SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] hash=skf.generateSecret(spec).getEncoded();
    return iterations+":"+toHex(salt)+":"+toHex(hash);
}
@SneakyThrows
private static byte[] getSalt(){
    SecureRandom sr=SecureRandom.getInstance("SHA1PRNG");
    byte[] salt=new byte[16];
    sr.nextBytes(salt);
    return salt;
}
private static String toHex(byte[] arr){
    BigInteger bi= new BigInteger(1,arr);
    String hex=bi.toString(16);
    int paddingLength = (arr.length*2)- hex.length();

    if(paddingLength>0)
        return String.format("%0"+paddingLength +"d",0)+hex;
    else
        return hex;
}
}
