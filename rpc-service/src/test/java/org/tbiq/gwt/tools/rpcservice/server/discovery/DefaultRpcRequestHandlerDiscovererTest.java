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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;
import org.tbiq.gwt.tools.rpcservice.server.discovery.dummy.DummyRpcRequestHandler3;
import org.tbiq.gwt.tools.rpcservice.server.discovery.dummy.DummyRpcRequestHandler4;

/**
 * DefaultRpcRequestHandlerDiscovererTest class is a test class for
 * {@link DefaultRpcRequestHandlerDiscoverer}.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultRpcRequestHandlerDiscovererTest
{
  @Test
  public void testFindRpcHandlerClassesString()
    throws IOException, ClassNotFoundException
  {
    DefaultRpcRequestHandlerDiscoverer discoverer = new DefaultRpcRequestHandlerDiscoverer(
      "org.tbiq.gwt.tools.rpcservice.server.discovery");

    @SuppressWarnings("unchecked")
    List<? extends Class> rpcClasses = discoverer
      .findRpcHandlerClasses("org.tbiq.gwt.tools.rpcservice.server.discovery");
    assertEquals("Should find 4 classes", 4, rpcClasses.size());
    assertTrue("DummyRpcRequestHandler1 should have been found.", rpcClasses
      .contains(DummyRpcRequestHandler1.class));
    assertTrue("DummyRpcRequestHandler2 should have been found.", rpcClasses
      .contains(DummyRpcRequestHandler2.class));
    assertTrue("DummyRpcRequestHandler3 should have been found.", rpcClasses
      .contains(DummyRpcRequestHandler3.class));
    assertTrue("DummyRpcRequestHandler4 should have been found.", rpcClasses
      .contains(DummyRpcRequestHandler4.class));
  }

  @Test
  @SuppressWarnings("unused")
  public void testConstructor()
  {
    try
    {
      RpcRequestHandlerDiscoverer discoverer = new DefaultRpcRequestHandlerDiscoverer("");
      fail("Empty package should not create a discoverer.");
    }
    catch (RpcServiceException e)
    {
    }

    try
    {
      RpcRequestHandlerDiscoverer discoverer = new DefaultRpcRequestHandlerDiscoverer(
        "org.tbiq", "");
      fail("Empty package should not create a discoverer even if another package exists.");
    }
    catch (RpcServiceException e)
    {
    }

    try
    {
      RpcRequestHandlerDiscoverer discoverer = new DefaultRpcRequestHandlerDiscoverer(
        "org.tbiq", null);
      fail("No package name can be null.");
    }
    catch (RpcServiceException e)
    {
    }
  }

  @Test
  public void testDiscoverHandlers()
  {
    RpcRequestHandlerDiscoverer discoverer = new DefaultRpcRequestHandlerDiscoverer(
      "org.tbiq.gwt.tools.rpcservice.server.discovery");
    List<? extends RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> rpcHandlers;

    rpcHandlers = discoverer.discoverHandlers();
    assertEquals("4 handlers should have been discovered.", 4, rpcHandlers.size());

    discoverer = new DefaultRpcRequestHandlerDiscoverer(
      "org.tbiq.gwt.tools.rpcservice.server.discovery.dummy");

    rpcHandlers = discoverer.discoverHandlers();
    assertEquals("2 handlers should have been discovered.", 2, rpcHandlers.size());
  }
}
