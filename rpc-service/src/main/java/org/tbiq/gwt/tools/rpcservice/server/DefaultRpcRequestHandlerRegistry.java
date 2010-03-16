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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;

/**
 * DefaultRpcRequestHandlerRegistry class is the default implementation of the
 * {@link RpcRequestHandlerRegistry} interface.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultRpcRequestHandlerRegistry
  implements RpcRequestHandlerRegistry
{
  /** Logger for this class. */
  private static Logger logger = Logger.getLogger(DefaultRpcRequestHandlerRegistry.class);

  /** Map of RpcRequestHandler instances keyed by their compatible type. */
  private Map<Class<? extends RpcRequest<? extends RpcResponse>>, RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> handlerMap;

  /**
   * Default constructor.
   */
  public DefaultRpcRequestHandlerRegistry()
  {
    // Initialize handler map
    handlerMap = new HashMap<Class<? extends RpcRequest<? extends RpcResponse>>, RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>>();
  }

  @Override
  public void addHandler(RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse> handler)
  {
    if (logger.isDebugEnabled())
    {
      logger.debug("Registering handler instance [" + handler + "].");
    }

    handlerMap.put(handler.getCompatibleRpcRequestType(), handler);
  }

  @Override
  public void addHandlers(List<? extends RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> handlers)
  {
    // Check to make sure handlers is not null
    if (handlers == null)
    {
      return;
    }

    // Loop through handlers and add them to the map
    for (RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse> handler : handlers)
    {
      addHandler(handler);
    }
  }

  @Override
  public RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse> getHandlerFor(Class<?> rpcRequestClassType)
  {
    if (logger.isDebugEnabled())
    {
      logger.debug("Retrieving handler for RPC request type [" + rpcRequestClassType
                   + "].");
    }

    return handlerMap.get(rpcRequestClassType);
  }

  @Override
  public void removeHandler(Class<?> rpcRequestClassType)
  {
    if (logger.isDebugEnabled())
    {
      logger
        .debug("Removing handler for RPC request type [" + rpcRequestClassType + "].");
    }

    handlerMap.remove(rpcRequestClassType);
  }
}
