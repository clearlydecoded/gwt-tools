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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ServletExecutionContext class serves as a container of objects regularly available
 * within a servlet. It is used as a container of those objects to be able to pass it to
 * resources outside of the servlet.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class ServletExecutionContext
{
  /** Current request object. */
  private final HttpServletRequest request;

  /** Current response object. */
  private final HttpServletResponse response;

  /** Servlet context. */
  private final ServletContext servletContext;

  /**
   * Constructor.
   * 
   * @param request Current request object.
   * @param response Current response object.
   * @param servletContext Servlet context.
   */
  public ServletExecutionContext(HttpServletRequest request,
                                 HttpServletResponse response,
                                 ServletContext servletContext)
  {
    this.request = request;
    this.response = response;
    this.servletContext = servletContext;
  }

  /**
   * @return Current request object.
   */
  public HttpServletRequest getRequest()
  {
    return request;
  }

  /**
   * @return Current response object.
   */
  public HttpServletResponse getResponse()
  {
    return response;
  }

  /**
   * @return Servlet context.
   */
  public ServletContext getServletContext()
  {
    return servletContext;
  }
}
