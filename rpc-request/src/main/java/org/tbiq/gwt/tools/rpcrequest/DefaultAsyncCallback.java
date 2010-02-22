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
 * DefaultAsyncCallback abstract class shields the concrete implementations of this class
 * from having to always override onFailure. Instead, it provides a mechanism to have
 * central exception handling as well as a way to override/augment central exception
 * handling when needed.
 * <p>
 * Typically, the concrete implementation of this class would only implement
 * {@link DefaultAsyncCallback#onSuccess(RpcResponse)}.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public abstract class DefaultAsyncCallback<T extends RpcResponse>
  implements AsyncCallback<T>
{
  /** Application-wide exception handler. */
  protected ApplicationWideExceptionHandler appWideExceptionHandler;

  /**
   * This method uses {@link DefaultAsyncCallback#appWideExceptionHandler} to handle the
   * exception if the app-wide exception handler has been provided.
   * 
   * @param exception Exception to handle.
   * @return <code>true</code> if the <code>exception</code> has been handled,
   *         <code>false</code> otherwise.
   */
  protected boolean handleException(Throwable exception)
  {
    // Check if application wide exception handler is available
    if (appWideExceptionHandler == null)
    {
      // Can't handle exception as app wide exception handler is missing
      return false;
    }

    // Use central exception handler to handle this exception
    appWideExceptionHandler.handleException(exception);

    // Notify that the exception has been handled
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
    // Attempt to handle the exception in an app-wide way
    if (handleException(exception))
    {
      return;
    }

    exception.printStackTrace();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
   */
  @Override
  public abstract void onSuccess(T response);
}
