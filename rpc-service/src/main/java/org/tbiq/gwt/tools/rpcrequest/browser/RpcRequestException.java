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
 * RpcRequestException class is an exception that the RpcService can throw if something
 * goes wrong during an RPC request.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class RpcRequestException
  extends RuntimeException
{
  /** Class version ID. */
  private static final long serialVersionUID = 4211068316498827998L;

  /**
   * @see RuntimeException#RuntimeException()
   */
  public RpcRequestException()
  {
    super();
  }

  /**
   * @see RuntimeException#RuntimeException(String, Throwable)
   */
  public RpcRequestException(String message, Throwable cause)
  {
    super(message, cause);
  }

  /**
   * @see RuntimeException#RuntimeException(String)
   */
  public RpcRequestException(String message)
  {
    super(message);
  }

  /**
   * @see RuntimeException#RuntimeException(Throwable)
   */
  public RpcRequestException(Throwable cause)
  {
    super(cause);
  }
}
