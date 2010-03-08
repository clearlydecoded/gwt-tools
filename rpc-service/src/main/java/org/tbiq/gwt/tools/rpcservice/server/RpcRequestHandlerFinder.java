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

/**
 * RpcRequestHandlerFinder interface defines a method which finds the handler classes to
 * be initialized and registered with the {@link RpcRequestHandlerRegistry}.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface RpcRequestHandlerFinder
{
  /**
   * Scan one of more given <code>packages</code> and looks for classes that implement
   * {@link RpcRequestHandler} interface and are annotated with
   * {@link org.tbiq.gwt.tools.rpcservice.server.annotation.RpcRequestHandler} annotation.
   * 
   * @param packages One of more package names which the implementation class should scan
   *          for annotated RPC handler classes.
   * @return List of concrete {@link Class} types which implement
   *         {@link RpcRequestHandler} interface and are annotated with
   *         {@link org.tbiq.gwt.tools.rpcservice.server.annotation.RpcRequestHandler}
   *         annotation located within the provided <code>packages</code>.
   */
  List<Class<?>> findHandlers(String... packages);
}
