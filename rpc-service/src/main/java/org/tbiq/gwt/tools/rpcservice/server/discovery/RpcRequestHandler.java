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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandlerRegistry;

/**
 * RpcRequestHandler annotation is a marker annotation designed to mark an implementation
 * of {@link org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler} as a handler that
 * needs to be initialized and registered with some {@link RpcRequestHandlerRegistry}
 * implementation.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RpcRequestHandler
{
}
