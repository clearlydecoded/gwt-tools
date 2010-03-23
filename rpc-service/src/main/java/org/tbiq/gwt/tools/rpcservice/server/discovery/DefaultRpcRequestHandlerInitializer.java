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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;

/**
 * DefaultRpcRequestHandlerInitializer class is the default implementation of the
 * {@link RpcRequestHandlerInitializer} interface. It looks for classes in the specified
 * packages which are annotated with {@link RpcHandler} annotation as well as implements
 * {@link RpcRequestHandler} interface and loads them using their default (no-args)
 * constructor.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultRpcRequestHandlerInitializer
  implements RpcRequestHandlerInitializer
{
  /** Logger for this class. */
  private static final Logger logger = Logger
    .getLogger(DefaultRpcRequestHandlerInitializer.class);

  /** Array of package name strings to look through to find RPC handlers in. */
  private String[] packageNames;

  /**
   * Constructor.
   * 
   * @param packageNames Array of 1 or more package name specifications for the discoverer
   *          that this initializer uses to look through to find classes annotated with
   *          {@link RpcHandler} annotation.
   */
  public DefaultRpcRequestHandlerInitializer(String... packageNames)
  {
    this.packageNames = packageNames;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<? extends RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> initializeHandlers()
  {
    // Discover RPC request handler classes within the given package(s)
    RpcRequestHandlerDiscoverer discoverer = new DefaultRpcRequestHandlerDiscoverer(
      packageNames);
    List<Class<? extends RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>>> rpcHandlerClasses;
    rpcHandlerClasses = discoverer.discoverHandlers();

    // Instantiate each RPC handler class
    List<RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> rpcHandlers;
    rpcHandlers = new ArrayList<RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>>();
    for (Class rpcHandlerClass : rpcHandlerClasses)
    {
      RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse> rpcHandler;
      try
      {
        // Instantiate RPC handler and add it to the collection
        rpcHandler = (RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>) rpcHandlerClass
          .newInstance();
        rpcHandlers.add(rpcHandler);
      }
      catch (Exception exception)
      {
        // Log and re-throw
        logger.error(exception.getMessage(), exception);
        throw new RpcServiceException(exception);
      }
    }

    return rpcHandlers;
  }

}
