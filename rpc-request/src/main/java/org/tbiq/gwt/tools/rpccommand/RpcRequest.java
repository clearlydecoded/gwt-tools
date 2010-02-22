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
package org.tbiq.gwt.tools.rpccommand;

import java.io.Serializable;

/**
 * RpcRequest interface represents an GWT RPC request to the server. It should contain the
 * necessary data for the server to complete the response.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface RpcRequest<T extends RpcResponse>
  extends Serializable
{
}
