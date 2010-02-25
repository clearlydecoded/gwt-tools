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

import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.browser.RpcService;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * RpcServiceServlet class is the server-side part of the {@link RpcService}
 * interface.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class RpcServiceServlet
  extends RemoteServiceServlet
  implements RpcService
{
  /** Class version ID. */
  private static final long serialVersionUID = 1296115987581738426L;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.tbiq.gwt.tools.rpcservice.browser.RpcService#execute(org.tbiq.gwt.tools
   * .rpcrequest.browser.RpcRequest)
   */
  @Override
  public <RpcRequestT extends RpcRequest<RpcResponseT>, RpcResponseT extends RpcResponse> RpcResponseT execute(RpcRequestT rpcRequest)
    throws RpcServiceException
  {
    // @SuppressWarnings("unchecked")
    // RpcRequestHandler<RpcRequestT, RpcResponseT> handler =
    // (RpcRequestHandler<RpcRequestT, RpcResponseT>) new NewDummyRequestHandler();
    //
    // handler.isCompatibleWith(rpcRequest.getClass());

    // return handler.execute(rpcRequest);

    return null;
  }





  // /*
  // * (non-Javadoc)
  // *
  // * @see
  // * org.tbiq.gwt.tools.rpcservice.browser.RpcService#execute(org.tbiq.gwt.tools
  // * .rpcrequest.browser.RpcRequest)
  // */
  // @Override
  // public <RpcResponseT extends RpcResponse, RpcRequestT extends
  // RpcRequest<RpcResponseT>> RpcResponseT execute(RpcRequestT rpcRequest)
  // throws RpcServiceException
  // {
  // // RpcRequestHandler<? extends RpcRequest<?>, ? extends RpcResponse> handler = new
  // NewDummyRequestHandler();
  // // rpcRequest = ()
  // //
  // // handler.isCompatibleWith(rpcRequest.getClass());
  // // return handler.execute(rpcRequest);
  //
  // return null;
  // }
}
