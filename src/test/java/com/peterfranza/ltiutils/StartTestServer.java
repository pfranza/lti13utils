package com.peterfranza.ltiutils;

import java.io.PrintStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.jetty.server.Server;

import com.peterfranza.ltiutils.jwk.CollectionSignatureKeyProvider;
import com.peterfranza.ltiutils.jwk.GuavaCachedSignatureKeyProvider;
import com.peterfranza.ltiutils.jwk.SignatureKey;
import com.peterfranza.ltiutils.jwk.SignatureKeyProvider;

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

			SignatureKeyProvider keyProvider;

			{
				InMemoryGeneratedKey localKey = new InMemoryGeneratedKey();
				localKey.write(System.out);
				keyProvider = new GuavaCachedSignatureKeyProvider(
						new CollectionSignatureKeyProvider(Arrays.asList(localKey)));
			}

			@Override
			protected SignatureKeyProvider getToolKeys() {
				return keyProvider;
			}

		});
		server.start();
		server.dumpStdErr();
		server.join();

	}

	/**
	 * Locally generated RSA key for signing outbound OIDC redirects
	 * 
	 * @author peter
	 *
	 */
	private static class InMemoryGeneratedKey implements SignatureKey {
		private KeyPair keys;
		private String name = "GeneratedKey_" + UUID.randomUUID().toString().replace("-", "");

		public InMemoryGeneratedKey() {
			try {
				KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
				keyGen.initialize(2048, new SecureRandom());
				keys = keyGen.generateKeyPair();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public Key getPublicKey() {
			return keys.getPublic();
		}

		@Override
		public Optional<Key> getPrivateKey() {
			return Optional.of(keys.getPrivate());
		}

		@Override
		public String getKeyPairIdentifier() {
			return name;
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
	}

}
