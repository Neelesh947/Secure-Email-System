package in.neelesh.Email.Utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class EncryptionUtility {

	@Value("${encryption.key}")
	private String aesKeyBase64;

	private SecretKeySpec secretKeySpec;
	private final int GCM_IV_LENGTH = 12;
	private final int GCM_TAG_LENGTH = 128;

	@PostConstruct
	public void init() {
		byte[] keyBytes = Base64.getDecoder().decode(aesKeyBase64);
		secretKeySpec = new SecretKeySpec(keyBytes, "AES");
	}

	public String encrypt(String plaintext) throws Exception {
		byte[] iv = new byte[GCM_IV_LENGTH]; // TODO: use random IV in production

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, spec);

		byte[] encrypted = cipher.doFinal(plaintext.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public String decrypt(String ciphertext) throws Exception {
		byte[] iv = new byte[GCM_IV_LENGTH]; // TODO: use stored IV in production

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, spec);

		byte[] decoded = Base64.getDecoder().decode(ciphertext);
		byte[] decrypted = cipher.doFinal(decoded);

		return new String(decrypted);
	}
}
