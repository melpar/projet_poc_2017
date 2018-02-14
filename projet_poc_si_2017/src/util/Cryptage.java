package util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class Cryptage {

	private String chaineIn;
	private String algo;
	private byte[] cle;

	/**
	 * chriffrage d'une chaine de caractere
	 * 
	 * @param chaine
	 *            Chaine de caractere a chiffrer
	 * @param cle
	 *            Cle de chiffrement
	 */

	private Cryptage(String chaine, String cle) {
		// TODO Auto-generated constructor stub
		this.chaineIn = chaine;
		this.cle = cle.getBytes();
		this.algo = "AES";
	}

	public String chiffrer() throws Exception {
		Key cle = generationCle();
		Cipher c = Cipher.getInstance(algo);
		c.init(Cipher.ENCRYPT_MODE, cle);
		byte[] encVal = c.doFinal(this.chaineIn.getBytes());
		return new BASE64Encoder().encode(encVal);
	}

	private Key generationCle() throws Exception {
		return new SecretKeySpec(cle, algo);
	}

	public static void main(String[] args) throws Exception {
		Cryptage c1 = new Cryptage("motDePasse", "abc123deaoezdf77");
		System.out.println(c1.chiffrer());

	}

}
