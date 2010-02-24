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
package org.tbiq.gwt.tools.rpcrequest.server;

import org.tbiq.gwt.tools.rpcrequest.browser.DummyRpcRequest;
import org.tbiq.gwt.tools.rpcrequest.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcrequest.browser.RpcResponse;

/**
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class Test
{

  /**
   * @param args
   */
  public static <RpcRequestT extends RpcRequest<RpcResponseT>, RpcResponseT extends RpcResponse> void main(String[] args)
  {

    @SuppressWarnings("unchecked")
    RpcRequestHandler<RpcRequestT, RpcResponseT> handler = (RpcRequestHandler<RpcRequestT, RpcResponseT>) (Object) new NewDummyRequestHandler();

    DummyRpcRequest request = new DummyRpcRequest();
    Dummy1RpcRequest request1 = new Dummy1RpcRequest();
    System.out.println(handler.isCompatibleWith(request.getClass()));
    System.out.println(handler.isCompatibleWith(request1.getClass()));

    @SuppressWarnings("unchecked")
    RpcRequestT genericRequest = (RpcRequestT) request;

    System.out.println(handler.execute(genericRequest));


    // System.out.println(handler.isCompatibleWith(new DummyRpcRequest()));
    // System.out.println(handler.isCompatibleWith(new Dummy1RpcRequest()));
    //
    // DummyRpcResponse response = handler.execute(new DummyRpcRequest());
    // System.out.println(response);
  }
}
