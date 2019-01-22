package com.peterfranza.ltiutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.peterfranza.ltiutils.oidc.LTI13Request;
import com.peterfranza.ltiutils.oidc.LTIOIDCResponseFactory;
import com.peterfranza.ltiutils.utils.LTI13JWSParser;
import com.peterfranza.ltiutils.utils.LTI13KeyLoader;

/**
 * Simple Test Server for debugging auth flows against the IMSGlobal LTI13
 * Reference Implementation
 * 
 * @author peter
 *
 */
public class StartTestServer extends AbstractHandler {

	private List<ExamplePageFlow> mountedPages = new ArrayList<>();

	{
		mountedPages.add(new ExamplePageFlow() {

			@Override
			public void onRequest(String target, Request baseRequest, HttpServletRequest request,
					HttpServletResponse response) throws IOException, ServletException {
				LTIOIDCResponseFactory factory = new LTIOIDCResponseFactory(request);
				if (factory.isValidRequest()) {
					response.sendRedirect(factory.generateReponseURL());
				} else {
					response.sendError(500, "Invalid Request");
				}
			}

			@Override
			public String mountPoint() {
				return "/login";
			}
		});

		mountedPages.add(new ExamplePageFlow() {

			@Override
			public void onRequest(String target, Request baseRequest, final HttpServletRequest request,
					HttpServletResponse response) throws IOException, ServletException {

				new LTI13JWSParser(createTestKeyResolver()).onException(exception -> {
					try {
						response.getWriter().print("ERROR: " + exception.getMessage());
						response.getWriter().flush();
						response.setStatus(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).onError(error -> {
					try {
						response.getWriter().print("ERROR: " + error);
						response.getWriter().flush();
						response.setStatus(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).onSuccess(jws -> {
					try {
						PrintWriter w = response.getWriter();

						LTI13Request req = new LTI13Request(request, jws);

						w.println("Success launch for \"" + req.getName() + "\"");
						w.println("");
						w.println("");
						jws.getBody().keySet().stream().forEach(key -> {
							w.println(" -- " + key + " = " + jws.getBody().get(key));
						});
						response.getWriter().flush();
						response.setStatus(200);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}).parse(request.getParameter("id_token"));
			}

			@Override
			public String mountPoint() {
				return "/launch";
			}
		});
	}

	@Override
	public void handle(final String target, Request baseRequest, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Optional<ExamplePageFlow> handler = mountedPages.stream().filter((r) -> {
			return r.mountPoint().equalsIgnoreCase(target);
		}).findFirst();

		if (handler.isPresent()) {
			handler.get().onRequest(target, baseRequest, request, response);
		} else {
			response.sendError(404);
		}
	}

	public static void main(String[] args) throws Exception {

		Server server = new Server(9534);
		server.setHandler(new StartTestServer());
		server.start();
		server.dumpStdErr();
		server.join();
	}

	private interface ExamplePageFlow {
		String mountPoint();

		void onRequest(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException;
	}

	private static LTI13KeyLoader createTestKeyResolver() {
		return new LTI13KeyLoader() {

			@Override
			public Key loadPublicKey() throws Exception {
				return parsePublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxUXNExuL9U/Xm+qxodaI"
						+ "suVHHaqmh1pePzxSnHeUZwuCJqII/9UVfKfeseIPLHsSr6cA9b9FDwyo16Men/iS"
						+ "nFkRtZp3aeKfC1COLzi24tjmJ6wkGGFCv8Ik3a6gZAV3uV1Cja7Kyc//BdjZaIsy"
						+ "hn2ZyJcNm2M+pYi4yL9rffz3NIBtIMD+2Dizz4XZCvRrstf0iwnsTqG5MksnV6uq"
						+ "vDhEWuY/ynaxlAs2aUUw252ofdzT+/pAUF7CSVp8bHdUYusmzlBT4Q78c0gWXBNW"
						+ "ya5r5ipF2HlmYyUPTo8S7NaF7jIogsYYCg0MkuE0/jv5O0Kf2BVymcEcpLzWRL/C" + "QQIDAQAB");
			}

			@Override
			public Key loadPrivateKey() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

		};
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
