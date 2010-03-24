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
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.server.DefaultRpcRequestHandlerRegistry;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandlerRegistry;
import org.tbiq.gwt.tools.rpcservice.server.RpcServiceServlet;

/**
 * SpringRpcRequestRegistryInitializationListener class is a servlet context listener that
 * uses the {@link SpringRpcRequestHandlerInitializer} to automatically discover
 * {@link RpcRequestHandler}s in the Spring application context and register them with a
 * {@link RpcRequestHandlerRegistry}.
 * <p>
 * <b>Please note: This servlet context listener must be configured in the
 * <code>web.xml</code> after the {@link ContextLoaderListener} configuration as this
 * listener relies on the spring context to have been already initialized by the time it
 * executes.</b>
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class SpringRpcRequestRegistryInitializationListener
  implements ServletContextListener
{
  /** Logger for this class. */
  private static final Logger logger = Logger
    .getLogger(SpringRpcRequestRegistryInitializationListener.class);

  @Override
  public void contextInitialized(ServletContextEvent event)
  {
    // Retrieve spring application context
    ServletContext servletContext = event.getServletContext();
    WebApplicationContext springContext = WebApplicationContextUtils
      .getWebApplicationContext(servletContext);

    // Verify that spring context has been initialized
    if (springContext == null)
    {
      String message = "Spring application context not found. Did you forget to";
      message += " configure ContextLoaderListener in web.xml or configured it below";
      message += " this SpringRpcRequestRegistryInitializationListener listener?";
      logger.error(message);
      throw new RpcServiceException(message);
    }

    // Retrieve initialized RPC handlers from the spring application context
    RpcRequestHandlerInitializer initializer = new SpringRpcRequestHandlerInitializer(
      springContext);
    List<? extends RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> rpcHandlers;
    rpcHandlers = initializer.initializeHandlers();

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
