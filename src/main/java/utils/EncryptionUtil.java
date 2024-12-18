package utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
	//Contruct instance
	private EncryptionUtil() {
		
	}
	private static EncryptionUtil instance;
	public static EncryptionUtil getInstance() {
		if (instance == null) {
			instance = new EncryptionUtil();
		}
		return instance;
	}

    // Mã hóa dữ liệu với AES
    public String encrypt(String data, String key) {
        // Tạo SecretKey từ chuỗi khóa (key) 
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        byte[] encryptedData = null;
        try {
        	
        // Khởi tạo đối tượng Cipher với thuật toán AES
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Mã hóa dữ liệu
        encryptedData = cipher.doFinal(data.getBytes());
        }catch (Exception e) {
        	
        }

        // Chuyển dữ liệu mã hóa thành dạng Base64 để dễ truyền tải
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Giải mã dữ liệu với AES
    public String decrypt(String encryptedData, String key) {
        // Tạo SecretKey từ chuỗi khóa (key)
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        byte[] originalData = null;
        try { 
        	
	        // Khởi tạo đối tượng Cipher với thuật toán AES
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	
	        // Giải mã dữ liệu
	        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
	        originalData = cipher.doFinal(decodedData);
	        
		} catch (Exception e) {
	
		}

        // Chuyển dữ liệu đã giải mã thành chuỗi
        return new String(originalData);
    }

}