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

import javax.servlet.http.HttpServletRequest;

import org.tbiq.gwt.tools.rpcrequest.browser.DummyRpcRequest;
import org.tbiq.gwt.tools.rpcrequest.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcrequest.browser.RpcRequestException;
import org.tbiq.gwt.tools.rpcrequest.browser.RpcRequestService;
import org.tbiq.gwt.tools.rpcrequest.browser.RpcResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * RpcRequestServiceServlet class is the server-side part of the {@link RpcRequestService}
 * interface.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class RpcRequestServiceServlet
  extends RemoteServiceServlet
  implements RpcRequestService
{
  /** Class version ID. */
  private static final long serialVersionUID = 1296115987581738426L;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.tbiq.gwt.tools.rpcrequest.browser.RpcRequestService#execute(org.tbiq.gwt.tools
   * .rpcrequest.browser.RpcRequest)
   */
  @Override
  public <RpcRequestT extends RpcRequest<RpcResponseT>, RpcResponseT extends RpcResponse> RpcResponseT execute(RpcRequestT rpcRequest)
    throws RpcRequestException
  {
    @SuppressWarnings("unchecked")
    RpcRequestHandler<RpcRequestT, RpcResponseT> handler = (RpcRequestHandler<RpcRequestT, RpcResponseT>) new NewDummyRequestHandler();

    handler.isCompatibleWith(rpcRequest.getClass());

    return handler.execute(rpcRequest);
  }





  // /*
  // * (non-Javadoc)
  // *
  // * @see
  // * org.tbiq.gwt.tools.rpcrequest.browser.RpcRequestService#execute(org.tbiq.gwt.tools
  // * .rpcrequest.browser.RpcRequest)
  // */
  // @Override
  // public <RpcResponseT extends RpcResponse, RpcRequestT extends
  // RpcRequest<RpcResponseT>> RpcResponseT execute(RpcRequestT rpcRequest)
  // throws RpcRequestException
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
