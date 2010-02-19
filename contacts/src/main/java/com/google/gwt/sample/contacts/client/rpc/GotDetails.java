/**
 * 
 */
package com.google.gwt.sample.contacts.client.rpc;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public abstract class GotDetails
  implements AsyncCallback<GetDetailsResponse>
{
  @Override
  public void onFailure(Throwable exception)
  {
    if (handleException(exception))
    {
      return;
    }

    // central exception handling
    Window.alert(exception.getMessage());
  }

  /**
   * @param exception
   * @return
   */
  private boolean handleException(Throwable exception)
  {
    return false;
  }

  @Override
  public void onSuccess(GetDetailsResponse arg0)
  {
    handleResponse();

  }


  public abstract void handleResponse();

}
