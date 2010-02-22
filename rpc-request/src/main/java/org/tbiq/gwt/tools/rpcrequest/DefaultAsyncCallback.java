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
package org.tbiq.gwt.tools.rpcrequest;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO: JavaDoc
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public abstract class DefaultAsyncCallback<T extends RpcResponse>
  implements AsyncCallback<T>
{
  /** Application-wide exception handler. */
  protected ApplicationWideExceptionHandler exceptionHandler;

  /**
   * TODO: write javadoc here.
   * 
   * @param exception
   * @return
   */
  protected boolean handleException(Throwable exception)
  {
    // Check if application wide exception handler is available
    if (exceptionHandler == null)
    {
      // Can't handle exception as app wide exception handler is missing
      return false;
    }

    // Use central exception handler to handle this exception
    exceptionHandler.handleException(exception);

    // Nofify that the exception has been handled
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
   */
  @Override
  public void onFailure(Throwable exception)
  {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
   */
  @Override
  public void onSuccess(T response)
  {
    processResponse(response);
  }

  /**
   * @param response
   */
  protected abstract void processResponse(T response);
}
