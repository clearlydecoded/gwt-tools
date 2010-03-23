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

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;

/**
 * DefaultRpcRequestHandlerInitializerTest class is a test class for
 * {@link DefaultRpcRequestHandlerInitializer} class.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultRpcRequestHandlerInitializerTest
{
  @Test
  public void testInitializeHandlers()
  {
    RpcRequestHandlerInitializer initializer = new DefaultRpcRequestHandlerInitializer(
      "org.tbiq.gwt.tools.rpcservice.server.discovery");
    List<? extends RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> rpcHandlers;

    rpcHandlers = initializer.initializeHandlers();
    assertEquals("4 handlers should have been discovered.", 4, rpcHandlers.size());

    initializer = new DefaultRpcRequestHandlerInitializer(
      "org.tbiq.gwt.tools.rpcservice.server.discovery.dummy");

    rpcHandlers = initializer.initializeHandlers();
    assertEquals("2 handlers should have been discovered.", 2, rpcHandlers.size());
  }
}
