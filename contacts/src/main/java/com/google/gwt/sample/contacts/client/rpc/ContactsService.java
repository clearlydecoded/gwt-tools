/**
 * 
 */
package com.google.gwt.sample.contacts.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface ContactsService
  extends RemoteService
{
  public <T extends Response> T execute(Action<T> action);
}
