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
import org.tbiq.gwt.tools.rpcservice.browser.DummyRpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;

/**
 * DummyRpcRequestHandler class is dummy (test) implementation of the
 * {@link RpcRequestHandler} interface.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DummyRpcRequestHandler
  implements RpcRequestHandler<DummyRpcRequest, DummyRpcResponse>
{
  /*
   * (non-Javadoc)
   * 
   * @see
   * org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler#getCompatibleRpcRequestType()
   */
  @Override
  public Class<DummyRpcRequest> getCompatibleType()
  {
    return DummyRpcRequest.class;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler#isCompatibleWith(java.lang
   * .Class)
   */
  @Override
  public boolean isCompatibleWith(Class<? extends RpcRequest<? extends RpcResponse>> rpcRequestClass)
  {
    if (DummyRpcRequest.class == rpcRequestClass)
    {
      return true;
    }

    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler#execute(org.tbiq.gwt.tools
   * .rpcservice.browser.RpcRequest,
   * org.tbiq.gwt.tools.rpcservice.server.ServletExecutionContext)
   */
  @Override
  public DummyRpcResponse execute(DummyRpcRequest rpcRequest,
                                  ServletExecutionContext context)
    throws RpcServiceException
  {
    return new DummyRpcResponse();
  }
}
