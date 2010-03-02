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
package com.google.gwt.sample.contacts.server.rpc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.tbiq.gwt.tools.rpcservice.server.DefaultRpcRequestHandlerRegistry;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandlerRegistry;
import org.tbiq.gwt.tools.rpcservice.server.RpcServiceServlet;

/**
 * RpcRequestHandlerRegistryInitializationListener class is a listener that initializes an
 * implementation of {@link RpcRequestHandlerRegistry} and registers implementations of
 * {@link RpcRequestHandler} for this application.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class RpcRequestHandlerRegistryInitializationListener
  implements ServletContextListener
{
  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent
   * )
   */
  @Override
  public void contextDestroyed(ServletContextEvent event)
  {
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent
   * )
   */
  @Override
  public void contextInitialized(ServletContextEvent event)
  {
    // Initialize registry
    RpcRequestHandlerRegistry registry = new DefaultRpcRequestHandlerRegistry();

    // Add registry as an application-wide attribute, using key predefined in rpc-service
    ServletContext servletContext = event.getServletContext();
    servletContext.setAttribute(RpcServiceServlet.RPC_HANDLER_REGISTRY_ATTRIBUTE_NAME,
                                registry);

    // Add handlers to the registry
    registry.addHandler(new GetContactRequestHandler());
    registry.addHandler(new UpdateContactRequestHandler());
    registry.addHandler(new DeleteContactsRequestHandler());
    registry.addHandler(new GetContactDetailsRequestHandler());
  }
}
