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

import com.peterfranza.ltiutils.oidc.OIDCRedirectBuilder;
import com.peterfranza.ltiutils.oidc.OIDCStateBuilder;

public abstract class MockToolDefinition extends AbstractHandler {

	private String name = "MockToolDefinition";
	private String client = "MockToolDefinitionClient";
	private String deployment = "MockToolDefinitionDeployment";


	private List<ExamplePageFlow> mountedPages = new ArrayList<>();

	MockToolDefinition(MockPlatformDefinition platform) {
		mountedPages.add(new ExamplePageFlow() {

			@Override
			public void onRequest(String target, Request baseRequest, final HttpServletRequest request,
					HttpServletResponse response) throws IOException, ServletException {

				Collections.list(request.getParameterNames()).stream().forEach(key -> {
					System.out.println(key + " = " + request.getParameter(key));
				});

				try {

					String newUrl = new OIDCRedirectBuilder() {

						@Override
						public OIDCStateBuilder getStateBuilder() {
							return new OIDCStateBuilder() {

								@Override
								public String getTargetLinkUri() {
									return request.getParameter("target_link_uri");
								}

								@Override
								public String getPlatformISS() {
									return name;
								}

								@Override
								public String getOrigionalISS() {
									return request.getParameter("iss");
								}

								@Override
								public String getLtiMessageHint() {
									return request.getParameter("lti_message_hint");
								}

								@Override
								public String getLoginHint() {
									return request.getParameter("login_hint");
								}

								@Override
								public String getIssuer() {
									return name;
								}

								@Override
								public String getController() {
									return mountPoint();
								}

								@Override
								public String getAudience() {
									return name;
								}
							};
						}

						@Override
						public String getOIDCEndpoint() {
							return platform.getPlatformOIDCAuthURL();
						}

						@Override
						public String getClientID() {
							return client;
						}
					}.buildRedirectURL(getToolKeys());

					System.out.println(newUrl);

					response.sendRedirect(newUrl);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			}

			@Override
			public String mountPoint() {
				return "/oidc/login_initiations";
			}
		});

		mountedPages.add(new ExamplePageFlow() {

			@Override
			public void onRequest(String target, Request baseRequest, final HttpServletRequest request,
					HttpServletResponse response) throws IOException, ServletException {

				new LTI13JWSParser(platform.getPlatformKeys()).onException(exception -> {
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

	protected abstract LTI13KeyLoader getToolKeys();


	private interface ExamplePageFlow {
		String mountPoint();

		void onRequest(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException;
	}

}
