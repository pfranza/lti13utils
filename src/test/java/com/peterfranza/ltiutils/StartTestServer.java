package com.peterfranza.ltiutils;

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
@SuppressWarnings("restriction")
public class StartTestServer {



	public static void main(String[] args) throws Exception {

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		KeyPair keys = keyGen.generateKeyPair();

		Base64.Encoder encoder = Base64.getEncoder();
		System.out.println("Tool Keys:");
		System.out.println("Public: ");
		System.out.println(encoder.encodeToString(keys.getPublic().getEncoded()));

		System.out.println();
		System.out.println("Private: ");
		System.out.println(encoder.encodeToString(keys.getPrivate().getEncoded()));
		System.out.println();

		Server server = new Server(9534);

		MockPlatformDefinition plaform = new MockPlatformDefinition() {

			@Override
			protected LTI13KeyLoader getPlatformKeys() {
				return new LTI13KeyLoader() {

					@Override
					public Key loadPublicKey() throws Exception {
						return parsePublicKey("-----BEGIN PUBLIC KEY-----\n"
								+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxUXNExuL9U/Xm+qxodaI\n"
								+ "suVHHaqmh1pePzxSnHeUZwuCJqII/9UVfKfeseIPLHsSr6cA9b9FDwyo16Men/iS\n"
								+ "nFkRtZp3aeKfC1COLzi24tjmJ6wkGGFCv8Ik3a6gZAV3uV1Cja7Kyc//BdjZaIsy\n"
								+ "hn2ZyJcNm2M+pYi4yL9rffz3NIBtIMD+2Dizz4XZCvRrstf0iwnsTqG5MksnV6uq\n"
								+ "vDhEWuY/ynaxlAs2aUUw252ofdzT+/pAUF7CSVp8bHdUYusmzlBT4Q78c0gWXBNW\n"
								+ "ya5r5ipF2HlmYyUPTo8S7NaF7jIogsYYCg0MkuE0/jv5O0Kf2BVymcEcpLzWRL/C\n" + "QQIDAQAB\n"
								+ "-----END PUBLIC KEY-----");
					}

					@Override
					public Key loadPrivateKey() throws Exception {
						return null;
					}

					@Override
					public String getKeyPairIdentifier() {
						return "Platform Key";
					}
				};
			}
		};

		server.setHandler(new MockToolDefinition(plaform) {

			@Override
			protected LTI13KeyLoader getToolKeys() {
				return new LTI13KeyLoader() {

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
		System.out.println(publicKeyContent);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
		RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
		return pubKey;
	}

}
