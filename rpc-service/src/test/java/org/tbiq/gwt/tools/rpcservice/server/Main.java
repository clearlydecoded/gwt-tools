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

import org.tbiq.gwt.tools.rpcservice.browser.DummyRpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.server.DefaultRpcRequestHandlerRegistry;
import org.tbiq.gwt.tools.rpcservice.server.DummyRpcRequestHandler;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;

/**
 * This class is just for testing and to try out the crazy generics constructs that this
 * package turned out to be using.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class Main
{
  public static <RpcRequestT extends RpcRequest<RpcResponseT>, RpcResponseT extends RpcResponse> void main(String[] args)
  {
    @SuppressWarnings("unchecked")
    RpcRequestHandler<RpcRequestT, RpcResponseT> handler = (RpcRequestHandler<RpcRequestT, RpcResponseT>) (Object) new DummyRpcRequestHandler();

    DefaultRpcRequestHandlerRegistry registry = new DefaultRpcRequestHandlerRegistry();
    System.out.println("Handler is compatible? - " + handler
                         .isCompatibleWith(DummyRpcRequest.class));

    registry.addHandler(handler);
    System.out.println(registry.getHandlerFor(DummyRpcRequest.class));

    DummyRpcRequest request = new DummyRpcRequest();
    @SuppressWarnings("unchecked")
    RpcRequestT requestToExecute = (RpcRequestT) request;
    @SuppressWarnings("unchecked")
    RpcRequestHandler<RpcRequestT, RpcResponseT> handlerToExecute = (RpcRequestHandler<RpcRequestT, RpcResponseT>) registry
      .getHandlerFor(requestToExecute.getClass());
    System.out.println(handlerToExecute.execute(requestToExecute, null));
  }
}
