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
  public void onModuleLoad()
  {
    // ContactsServiceAsync rpcService = GWT.create(ContactsService.class);
    HandlerManager eventBus = new HandlerManager(null);
    RpcServiceAsync rpcService = GWT.create(RpcService.class);

    AppController appViewer = new AppController(eventBus, rpcService);
    appViewer.go(RootPanel.get());
  }
}
