package com.peterfranza.ltiutils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.peterfranza.ltiutils.oidc.LTIOIDCResponseFactory;

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

}
