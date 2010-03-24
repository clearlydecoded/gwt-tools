package com.google.gwt.sample.contacts.client;

import org.tbiq.gwt.tools.rpcservice.browser.RpcService;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class Contacts
  implements EntryPoint
{
  /**
   * RPC service for this application. The need for this global declaration will go away
   * once we introduce dependency injection.
   */
  public static final RpcServiceAsync RPC_SERVICE = GWT.create(RpcService.class);;

  public void onModuleLoad()
  {
    // ContactsServiceAsync rpcService = GWT.create(ContactsService.class);
    HandlerManager eventBus = new HandlerManager(null);

    AppController appViewer = new AppController(eventBus);
    appViewer.go(RootPanel.get());
  }
}
