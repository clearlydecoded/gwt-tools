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
package org.tbiq.gwt.tools.rpcservice.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcService;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * RpcServiceServlet class is the server-side part of the {@link RpcService} interface.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class RpcServiceServlet
  extends RemoteServiceServlet
  implements RpcService
{
  /** Class version ID. */
  private static final long serialVersionUID = 1296115987581738426L;

  /**
   * Attribute name for retrieving a concrete implementation of RpcRequestHanderRegistry
   * from ServletContext.
   */
  public static final String RPC_HANDLER_REGISTRY_ATTRIBUTE_NAME = "org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandlerRegistry";

  /** Registry of instances of concrete implementations of RpcRequestHandlers. */
  private RpcRequestHandlerRegistry rpcRequestHandlerRegistry;

  /*
   * (non-Javadoc)
   * 
   * @see javax.servlet.GenericServlet#init()
   */
  @Override
  public void init()
    throws ServletException
  {
    // Retrieve RPC request handler registry
    ServletContext servletContext = getServletContext();
    rpcRequestHandlerRegistry = (RpcRequestHandlerRegistry) servletContext
      .getAttribute(RPC_HANDLER_REGISTRY_ATTRIBUTE_NAME);

    // Check that the RPC request handler registry was provided
    if (rpcRequestHandlerRegistry == null)
    {
      // Wrap RpcServiceException in the ServletException and throw it
      String message = "No RpcRequestHandlerRegistry has been provided in the servlet";
      message += " context under the predefined key '";
      message += RPC_HANDLER_REGISTRY_ATTRIBUTE_NAME + "'.";
      RpcServiceException exception = new RpcServiceException(message);
      throw new ServletException(exception);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tbiq.gwt.tools.rpcservice.browser.RpcService#execute(org.tbiq.gwt.tools
   * .rpcrequest.browser.RpcRequest)
   */
  @Override
  @SuppressWarnings("unchecked")
  public <RpcRequestT extends RpcRequest<RpcResponseT>, RpcResponseT extends RpcResponse> RpcResponseT execute(RpcRequestT rpcRequest)
    throws RpcServiceException
  {
    // Retrieve RPC request handler for this RPC request
    RpcRequestHandler<RpcRequestT, RpcResponseT> handler = (RpcRequestHandler<RpcRequestT, RpcResponseT>) rpcRequestHandlerRegistry
      .getHandlerFor(rpcRequest.getClass());

    // Throw exception if no handler for this RPC request was found
    if (handler == null)
    {
      String message = "No compatible RpcRequestHandler is found for RPC request type: ";
      message += rpcRequest.getClass() + ".";
      throw new RpcServiceException(message);
    }

    // Extract standard objects available in a servlet to pass along to the execution
    HttpServletRequest request = getThreadLocalRequest();
    HttpServletResponse response = getThreadLocalResponse();
    ServletContext servletContext = getServletContext();
    ServletExecutionContext servletExecutionContext = new ServletExecutionContext(
      request, response, servletContext);

    return handler.execute(rpcRequest, servletExecutionContext);
  }
}
