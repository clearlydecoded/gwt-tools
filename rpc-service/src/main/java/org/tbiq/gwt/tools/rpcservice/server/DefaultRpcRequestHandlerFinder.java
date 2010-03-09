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

import java.util.List;

import org.apache.log4j.Logger;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;


/**
 * DefaultRpcRequestHandlerFinder class is the default implementation of the
 * {@link RpcRequestHandlerFinder} interface. It uses the the rpc-service defined
 * annotation {@link org.tbiq.gwt.tools.rpcservice.server.annotation.RpcRequestHandler} to
 * identify implementations of {@link RpcRequestHandler} in the specified packages that
 * need to be "found".
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultRpcRequestHandlerFinder
  implements RpcRequestHandlerFinder
{
  /** Logger for this class. */
  private static Logger logger = Logger.getLogger(DefaultRpcRequestHandlerFinder.class);

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandlerFinder#findHandlers(java.lang
   * .String[])
   */
  @Override
  public List<Class<?>> findHandlers(String... packageStrings)
    throws RpcServiceException
  {
    // Check if packages array is null
    if (packageStrings == null)
    {
      String message = "No packages specified for finding RpcRequestHandler";
      message += " implementations in.";
      logger.error(message);
      throw new RpcServiceException(message);
    }

    // Loop through package names
    for (String packageString : packageStrings)
    {
      // Check if the packageString is an empty string
      if (packageString == null || packageString.trim().isEmpty())
      {
        String message = "Can't load handlers from package name '" + packageString + "'.";
        logger.error(message);
        throw new RpcServiceException(message);
      }

      // TODO: Finish this code.
    }

    return null;
  }
}
