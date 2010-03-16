/**
 * Copyright 2010 Yaakov Chaikin (yaakov.chaikin@gmail.com) Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed
 * to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the
 * License.
 */
package org.tbiq.gwt.tools.rpcservice.server.discovery;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.server.DefaultRpcRequestHandlerRegistry;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandlerRegistry;
import org.tbiq.gwt.tools.rpcservice.server.RpcServiceServlet;

/**
 * DefaultRpcRequestRegistryInitializationListener class is a servlet context listener
 * that uses the {@link DefaultRpcRequestHandlerDiscoverer} to automatically discover
 * {@link RpcRequestHandler}s on the classpath and register them with a
 * {@link RpcRequestHandlerRegistry}.
 * <p>
 * Besides the declaration of this listener in <code>web.xml</code> of the client web
 * application, <code>web.xml</code> must also declare a context parameter with the name
 * <code>packagesToScan</code> which must contain comma-separated list of package names to
 * scan to look for {@link RpcRequestHandler} implementations.
 * 
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultRpcRequestRegistryInitializationListener
  implements ServletContextListener
{
  /** Logger for this class. */
  private static final Logger logger = Logger
    .getLogger(DefaultRpcRequestRegistryInitializationListener.class);

  /**
   * Context param name to look for in web.xml to retrieve packages to scan for RPC
   * request handler classes.
   */
  public static final String PACKAGES_TO_SCAN_CONTEXT_PARAM_NAME = "packagesToScan";

  @Override
  public void contextInitialized(ServletContextEvent event)
  {
    // Retrieve packages to scan from the context param
    ServletContext servletContext = event.getServletContext();
    String packagesToScanString = servletContext
      .getInitParameter(PACKAGES_TO_SCAN_CONTEXT_PARAM_NAME);

    // Check that packages to scan string exists and not an empty string
    if (packagesToScanString == null || packagesToScanString.trim().equals(""))
    {
      String message = "Context parameter " + PACKAGES_TO_SCAN_CONTEXT_PARAM_NAME;
      message += " is either missing or an empty string.";
      logger.error(message);
      throw new RpcServiceException(message);
    }

    // Split the packages to scan string into packages and trim them
    String[] packagesToScan = packagesToScanString.split(",");
    for (String packageToScan : packagesToScan)
    {
      packageToScan = packageToScan.trim();
    }

    // Auto-discover the RPC request handlers
    RpcRequestHandlerDiscoverer discoverer = new DefaultRpcRequestHandlerDiscoverer(
      packagesToScan);
    List<? extends RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> rpcHandlers;
    rpcHandlers = discoverer.discoverHandlers();

    // Warn in the logs if no handlers were discovered
    if (rpcHandlers.size() == 0)
    {
      String message = "No RPC request handlers were discovered in the following";
      message += " packages: " + packagesToScanString;
      logger.warn(message);
    }

    // Add auto-discovered handlers to handler registry
    RpcRequestHandlerRegistry registry = new DefaultRpcRequestHandlerRegistry();
    registry.addHandlers(rpcHandlers);

    // Share handler registry with the RPC servlet
    servletContext.setAttribute(RpcServiceServlet.RPC_HANDLER_REGISTRY_ATTRIBUTE_NAME,
                                registry);
  }

  @Override
  public void contextDestroyed(ServletContextEvent event)
  {
  }
}
