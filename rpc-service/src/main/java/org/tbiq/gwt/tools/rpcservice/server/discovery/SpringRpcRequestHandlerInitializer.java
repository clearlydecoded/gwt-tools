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
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;

/**
 * SpringRpcRequestHandlerInitializer class is the Spring framework implementation of the
 * {@link RpcRequestHandlerInitializer} interface. It relies on the initialized Spring
 * framework context with Spring-enabled beans which also annotated with the
 * {@link RpcHandler}
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class SpringRpcRequestHandlerInitializer
  implements RpcRequestHandlerInitializer
{
  /** Spring framework application context loaded with all the spring-initialized beans. */
  private ApplicationContext springContext;

  /**
   * Constructor.
   * 
   * @param springContext Spring framework application context loaded with all the
   *          spring-initialized beans.
   */
  public SpringRpcRequestHandlerInitializer(ApplicationContext springContext)
  {
    this.springContext = springContext;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<? extends RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> initializeHandlers()
  {
    // Retrieve all spring initialized beans that implement RpcRequestHandler
    Map beanMap = springContext.getBeansOfType(RpcRequestHandler.class);

    // Build list of RPC handler instances from bean map which have RpcHandler annotation
    Set<String> beanNames = beanMap.keySet();
    List<RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> rpcHandlers;
    rpcHandlers = new ArrayList<RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>>();
    for (String beanName : beanNames)
    {
      RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse> handler;
      handler = (RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>) beanMap
        .get(beanName);

      // Check if RpcHandler annotation is present
      if (handler.getClass().isAnnotationPresent(RpcHandler.class))
      {
        rpcHandlers.add(handler);
      }
    }

    return rpcHandlers;
  }
}
