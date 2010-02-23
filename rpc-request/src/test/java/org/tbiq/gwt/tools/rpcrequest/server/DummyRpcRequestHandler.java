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
import org.tbiq.gwt.tools.rpcrequest.browser.DummyRpcResponse;
import org.tbiq.gwt.tools.rpcrequest.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcrequest.browser.RpcRequestException;
import org.tbiq.gwt.tools.rpcrequest.browser.RpcResponse;

/**
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DummyRpcRequestHandler
  implements RpcRequestHandler<>
{


  // /*
  // * (non-Javadoc)
  // *
  // * @see
  // * org.tbiq.gwt.tools.rpcrequest.server.RpcRequestHandler#isCompatibleWith(org.tbiq.
  // * gwt.tools.rpcrequest.browser.RpcRequest)
  // */
  // @Override
  // public <T extends RpcResponse> boolean isCompatibleWith(RpcRequest<T> rpcRequest)
  // {
  // if (rpcRequest instanceof DummyRpcRequest)
  // {
  // return true;
  // }
  //
  // return false;
  // }
  //
  // /*
  // * (non-Javadoc)
  // *
  // * @see
  // * org.tbiq.gwt.tools.rpcrequest.browser.RpcRequestService#execute(org.tbiq.gwt.tools
  // * .rpcrequest.browser.RpcRequest)
  // */
  // @Override
  // public <T extends RpcResponse> T execute(RpcRequest<T> rpcRequest)
  // throws RpcRequestException
  // {
  // T rpcResponse = null;
  // if (!isCompatibleWith(rpcRequest))
  // {
  // throw new RpcRequestException("This handler only handles DummyRpcRequest objects.");
  // }
  //
  // rpcResponse = (T) new DummyRpcResponse();
  // return rpcResponse;
  //
  // }
}
