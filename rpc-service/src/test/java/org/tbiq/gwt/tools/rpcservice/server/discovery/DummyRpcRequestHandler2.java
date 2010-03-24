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

import org.springframework.stereotype.Component;
import org.tbiq.gwt.tools.rpcservice.browser.DummyRpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.DummyRpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;
import org.tbiq.gwt.tools.rpcservice.server.ServletExecutionContext;

/**
 * Dummy RPC request handler for testing.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
@RpcHandler
@Component
public class DummyRpcRequestHandler2
  implements RpcRequestHandler<DummyRpcRequest, DummyRpcResponse>
{
  @Override
  public DummyRpcResponse execute(DummyRpcRequest rpcRequest,
                                  ServletExecutionContext context)
    throws RpcServiceException
  {
    return null;
  }

  @Override
  public Class<DummyRpcRequest> getCompatibleRpcRequestType()
  {
    return null;
  }
}
