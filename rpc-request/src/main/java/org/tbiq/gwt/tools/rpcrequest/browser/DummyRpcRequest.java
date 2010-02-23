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
package org.tbiq.gwt.tools.rpcrequest.browser;

/**
 * DummyRpcRequest class is only here to allow the templated (generics-enabled)
 * {@link RpcRequestService} to compile in dependent modules. Without it, the dependent
 * modules would not GWT compile because the GWT compiler would fail to resolve
 * {@link RpcRequestService} via deferred binding.
 * <p>
 * Note that this is only a problem in the initial stages of the project when the new
 * project has no implementations of {@link RpcRequest} and {@link RpcResponse} of its
 * own.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
@SuppressWarnings("serial")
public class DummyRpcRequest
  implements RpcRequest<DummyRpcResponse>
{
}
