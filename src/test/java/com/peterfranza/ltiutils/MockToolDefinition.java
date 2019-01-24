package com.peterfranza.ltiutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.gson.GsonBuilder;
import com.peterfranza.ltiutils.jwk.SignatureKeyProvider;
import com.peterfranza.ltiutils.jwk.SignatureKeyToJWKSerializer;
import com.peterfranza.ltiutils.oidc.OIDCRedirectBuilder;

public abstract class MockToolDefinition extends AbstractHandler {

	private String name = "MockToolDefinition";
	private String client = "MockToolDefinitionClient";
	private String deployment = "MockToolDefinitionDeployment";

	private List<ExamplePageFlow> mountedPages = new ArrayList<>();

	MockToolDefinition(MockPlatformDefinition platform) {

		/**
		 * This Mounts the tool public key at the 'well-known' jwks url
		 */
		mountedPages.add(new ExamplePageFlow() {

			@Override
			public void onRequest(String target, Request baseRequest, HttpServletRequest request,
					HttpServletResponse response) throws IOException, ServletException {

				try {
					PrintWriter w = response.getWriter();
					w.print(new SignatureKeyToJWKSerializer().serialize(getToolKeys().getFirst().get()));
					response.getWriter().flush();
					response.setStatus(200);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public String mountPoint() {
				return "/.well-known/jwks.json";
			}
		});

		/**
		 * Redirect for OIDC third-party login requests
		 */
		mountedPages.add(new ExamplePageFlow() {

			@Override
			public void onRequest(String target, Request baseRequest, final HttpServletRequest request,
					HttpServletResponse response) throws IOException, ServletException {

				Collections.list(request.getParameterNames()).stream().forEach(key -> {
					System.out.println(key + " = " + request.getParameter(key));
				});

				try {

					response.sendRedirect(OIDCRedirectBuilder.createFromRequest(request, name, name, mountPoint(), name,
							client, platform.getPlatformOIDCAuthURL())
							.buildRedirectURL(getToolKeys().getFirst().get()));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			}

			@Override
			public String mountPoint() {
				return "/oidc/login_initiations";
			}
		});

		/**
		 * Handle the resource launch and decode the plaform JWT into usable claims
		 */
		mountedPages.add(new ExamplePageFlow() {

			@Override
			public void onRequest(String target, Request baseRequest, final HttpServletRequest request,
					HttpServletResponse response) throws IOException, ServletException {

				Collections.list(request.getParameterNames()).stream().forEach(key -> {
					System.out.println(key + " = " + request.getParameter(key));
				});

				LTI13Request ltiRequest = LTI13JWSParser.convertOrThrow(platform.getPlatformKeys(), request);

				try {

					System.out.println(ltiRequest.getJws().getBody().toString());

					try {
						PrintWriter w = response.getWriter();

						w.println("Success launch for \"" + ltiRequest.getLaunch().getName() + "\"");
						w.println("");
						w.println("");
						w.println("Full JWS Map:");
						ltiRequest.getJws().getBody().keySet().stream().forEach(key -> {
							w.println(" -- " + key + " = " + ltiRequest.getJws().getBody().get(key));
						});

						w.println("");
						w.println("");
						w.println("JSON Recode:");

						w.println(new GsonBuilder().setPrettyPrinting().create().toJson(ltiRequest.getLaunch()));

						response.getWriter().flush();
						response.setStatus(200);

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					try {
						response.getWriter().print("ERROR: " + e.getMessage());
						response.getWriter().flush();
						response.setStatus(500);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}

			@Override
			public String mountPoint() {
				return "/launch";
			}
		});
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Optional<ExamplePageFlow> handler = mountedPages.stream().filter((r) -> {
			return r.mountPoint().equalsIgnoreCase(target);
		}).findFirst();

		if (handler.isPresent()) {
			handler.get().onRequest(target, baseRequest, request, response);
		} else {
			response.sendError(404);
		}
	}

	protected abstract SignatureKeyProvider getToolKeys();

	private interface ExamplePageFlow {
		String mountPoint();

		void onRequest(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException;
	}

}
