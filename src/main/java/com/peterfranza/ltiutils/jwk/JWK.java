package com.peterfranza.ltiutils.jwk;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.annotations.SerializedName;

public class JWK {

	private static final String PUBLIC_KEY_ALGORITHM = "RSA";
	public static final String KEY_ID = "kid";

	@SerializedName(KEY_ID)
	private String id;

	@SerializedName("kty")
	private String type = PUBLIC_KEY_ALGORITHM;

	@SerializedName("alg")
	private String algorithm;

	@SerializedName("use")
	private String usage;

	@SerializedName("key_ops")
	private List<String> operations;

	@SerializedName("x5u")
	private String certificateUrl;

	@SerializedName("x5c")
	private List<String> certificateChain;

	@SerializedName("x5t")
	private String certificateThumbprint;

	@SerializedName("n")
	private String modulus;

	@SerializedName("e")
	private String exponent;

	public final String getId() {
		return id;
	}

	public final String getType() {
		return type;
	}

	public final String getAlgorithm() {
		return algorithm;
	}

	public final String getUsage() {
		return usage;
	}

	public final List<String> getOperations() {
		return operations;
	}

	public final String getCertificateUrl() {
		return certificateUrl;
	}

	public final List<String> getCertificateChain() {
		return certificateChain;
	}

	public final String getCertificateThumbprint() {
		return certificateThumbprint;
	}

	public final String getModulus() {
		return modulus;
	}

	public final String getExponent() {
		return exponent;
	}

	protected void setId(String id) {
		this.id = id;
	}

	protected void setType(String type) {
		this.type = type;
	}

	protected void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	protected void setUsage(String usage) {
		this.usage = usage;
	}

	protected void setOperations(List<String> operations) {
		this.operations = operations;
	}

	protected void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}

	protected void setCertificateChain(List<String> certificateChain) {
		this.certificateChain = certificateChain;
	}

	protected void setCertificateThumbprint(String certificateThumbprint) {
		this.certificateThumbprint = certificateThumbprint;
	}

	protected void setModulus(String modulus) {
		this.modulus = modulus;
	}

	protected void setExponent(String exponent) {
		this.exponent = exponent;
	}

	public PublicKey getPublicKey() {
		if (!PUBLIC_KEY_ALGORITHM.equalsIgnoreCase(type)) {
			throw new RuntimeException("The key is not of type RSA", null);
		}
		try {
			KeyFactory kf = KeyFactory.getInstance(PUBLIC_KEY_ALGORITHM);
			BigInteger modulus = new BigInteger(1, Base64.decodeBase64(this.modulus));
			BigInteger exponent = new BigInteger(1, Base64.decodeBase64(this.exponent));
			return kf.generatePublic(new RSAPublicKeySpec(modulus, exponent));
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("Invalid public key", e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Invalid algorithm to generate key", e);
		}
	}

}
