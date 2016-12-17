import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESREPORTEXAMPLE {
	public static void main(String[] args){
	try{
		String key = "butterfly";
		FileInputStream in = new FileInputStream("message.txt");
		FileOutputStream out = new FileOutputStream("encrypted.txt");
		encrypt(key,in,out);

		FileInputStream inagain = new FileInputStream("encrypted.txt");
		FileOutputStream outagain = new FileOutputStream("messageagain.txt");
		decrypt(key,inagain,outagain);
	}
	catch (Throwable e){
		e.printStackTrace();
	}
	}

	//encrypting
	public static void encrypt(String key, InputStream somefile, OutputStream someotherfile) throws Throwable{
		actualpart(key, Cipher.ENCRYPT_MODE, somefile, someotherfile);
	}

	//decrypting
	public static void decrypt(String key, InputStream somefile, OutputStream someotherfile) throws Throwable{
		actualpart(key, Cipher.DECRYPT_MODE, somefile, someotherfile);
	}

	public static void actualpart(String key, int mode, InputStream somefile, OutputStream someotherfile) throws Throwable{

		//initializing keys and cipher
		DESKeySpec deskey = new DESKeySpec(key.getBytes());
		SecretKeyFactory secretkey = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = secretkey.generateSecret(deskey);
		Cipher cipher = Cipher.getInstance("DES");

		//if we need to encrypt
		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey); //initalizing the cipher to encrypt mode
			CipherInputStream cis = new CipherInputStream(somefile, cipher); //creating the inputstream(because encrypting)
			perform(cis, someotherfile);//send to the method that writes out
		} 

		//if we need to decrypt
		else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);//just as above the decrypt mode
			CipherOutputStream cos = new CipherOutputStream(someotherfile, cipher);//unlike above this is the outputstream 
			perform(somefile, cos);//sending out to write
		}
	}

	//the actual writing
	public static void perform(InputStream somefile, OutputStream someotherfile) throws IOException {
		byte[] bytes = new byte[64];
		int numberofbytes;
		while ((numberofbytes = somefile.read(bytes)) != -1) {
			someotherfile.write(bytes, 0, numberofbytes);
		}
		os.flush();
		os.close();
		is.close();
	}
}