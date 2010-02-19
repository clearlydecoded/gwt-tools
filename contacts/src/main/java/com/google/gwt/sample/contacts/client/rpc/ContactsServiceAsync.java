/**
 * 
 */
package com.google.gwt.sample.contacts.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface ContactsServiceAsync
{

  public <T extends Response> void execute(Action<T> action, AsyncCallback<T> callback);

}
