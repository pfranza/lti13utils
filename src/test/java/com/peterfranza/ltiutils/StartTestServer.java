package com.peterfranza.ltiutils;

import java.io.PrintStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

import org.eclipse.jetty.server.Server;

/**
 * Simple Test Server for debugging auth flows against the IMSGlobal LTI13
 * Reference Implementation
 * 
 * @author peter
 *
 */
public class StartTestServer {

	public static void main(String[] args) throws Exception {

		Server server = new Server(9534);

		MockPlatformDefinition plaform = new MockPlatformDefinition();

		server.setHandler(new MockToolDefinition(plaform) {

			@Override
			protected LTI13KeyLoader getToolKeys() {
				return new LTI13KeyLoader() {

					private KeyPair keys;

					{
						try {
							KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
							keyGen.initialize(2048);
							keys = keyGen.generateKeyPair();

							write(System.out);

						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}

					@Override
					public Key loadPublicKey() throws Exception {
						return keys.getPublic();
					}

					@Override
					public Key loadPrivateKey() throws Exception {
						return keys.getPrivate();
					}

					@Override
					public String getKeyPairIdentifier() {
						return "RndmKeys-" + UUID.randomUUID().toString();
					}

					public void write(PrintStream out) {
						Base64.Encoder encoder = Base64.getEncoder();
						out.println();
						out.print("-----BEGIN RSA PRIVATE KEY-----\n");
						out.print(encoder.encodeToString(keys.getPrivate().getEncoded()));
						out.print("\n-----END RSA PRIVATE KEY-----\n");
						out.println();
						out.print("-----BEGIN RSA PUBLIC KEY-----\n");
						out.print(encoder.encodeToString(keys.getPublic().getEncoded()));
						out.print("\n-----END RSA PUBLIC KEY-----\n");
						out.flush();
					}
				};
			}



		});
		server.start();
		server.dumpStdErr();
		server.join();

	}

	public static RSAPublicKey parsePublicKey(String key) throws GeneralSecurityException {

		String publicKeyContent = key.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "")
				.replace("-----END PUBLIC KEY-----", "").replace(" ", "");
		KeyFactory kf = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
		RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
		return pubKey;
	}

}
