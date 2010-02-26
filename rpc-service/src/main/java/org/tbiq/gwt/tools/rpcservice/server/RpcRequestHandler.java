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
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;

/**
 * RpcRequestHandler interface defines methods which allows its implementations to do the
 * actual work of processing a concrete type of {@link RpcRequest} and produce concrete
 * type of {@link RpcResponse}.
 * <p>
 * This is the interface that the clients of this package would have to implement for each
 * distinct type of {@link RpcRequest}/{@link RpcResponse} pair.
 * <p>
 * <b>Warning! Implementations of this interface must account for the fact that the
 * execution of its code might happen in a multi-threaded environment. Do NOT assume that
 * a new instance of this handler will be created for each thread. In other words,
 * multiple threads might share the same instance of the handler.</b>
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface RpcRequestHandler<RpcRequestT extends RpcRequest<RpcResponseT>, RpcResponseT extends RpcResponse>
{
  /**
   * Executes the <code>rpcRequest</code>, producing an RPC response of type which is
   * embedded in the type of <code>rpcRequest</code>.
   * 
   * @param rpcRequest RPC request possibly containing data which is needed for execution
   *          of that request.
   * @param context Servlet execution context, containing standard objects available
   *          within a servlet.
   * @return RPC response (i.e., some concrete subtype of {@link RpcResponse} which is
   *         embedded in the type of <code>rpcRequest</code>), possibly containing the
   *         data which represents a response to the executed <code>rpcRequest</code>.
   * @throws RpcServiceException If anything goes wrong during the execution of the
   *           request.
   */
  public RpcResponseT execute(RpcRequestT rpcRequest, ServletExecutionContext context)
    throws RpcServiceException;

  /**
   * @return Class type of {@link RpcRequest} that this handler is able to execute.
   */
  public Class<RpcRequestT> getCompatibleType();

  /**
   * Examines the <code>rpcRequest</code> objects and decides if this handler is
   * compatible with (i.e., able to execute) that particular {@link RpcRequest}. Returns
   * <code>true</code> if it is compatible, <code>false</code> otherwise.
   * 
   * @param rpcRequestClass RPC request class to handle/execute.
   * @return <code>true</code> if this handler is compatible with (i.e., able to execute)
   *         <code>rpcRequest</code>, <code>false</code> otherwise.
   */
  public boolean isCompatibleWith(Class<? extends RpcRequest<? extends RpcResponse>> rpcRequestClass);
}
