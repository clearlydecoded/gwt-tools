package com.google.gwt.sample.contacts.client;

import org.tbiq.gwt.tools.placeservice.browser.DefaultHistoryTokenParser;
import org.tbiq.gwt.tools.placeservice.browser.DefaultPlaceChangedEventHandler;
import org.tbiq.gwt.tools.placeservice.browser.DefaultPlaceService;
import org.tbiq.gwt.tools.placeservice.browser.PlaceChangedEvent;
import org.tbiq.gwt.tools.placeservice.browser.PlaceChangedEventHandler;
import org.tbiq.gwt.tools.placeservice.browser.PlaceService;
import org.tbiq.gwt.tools.presenter.browser.Presenter;
import org.tbiq.gwt.tools.rpcservice.browser.RpcService;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.contacts.client.event.ContactDeletedEvent;
import com.google.gwt.sample.contacts.client.event.ContactDeletedEventHandler;
import com.google.gwt.sample.contacts.client.place.AddContactPlace;
import com.google.gwt.sample.contacts.client.place.EditContactPlace;
import com.google.gwt.sample.contacts.client.place.ListContactsPlace;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController
  implements Presenter
{
  private final HandlerManager eventBus;
  private final PlaceService placeService;
  private final RpcServiceAsync rpcService = GWT.create(RpcService.class);

  public AppController(HandlerManager eventBus)
  {
    this.eventBus = eventBus;
    placeService = new DefaultPlaceService(new DefaultHistoryTokenParser(), eventBus);
    bind();
  }

  @Override
  public void bind()
  {
    // Register places with the place service
    placeService.registerPlace(new ListContactsPlace(eventBus, rpcService, true), true);
    placeService.registerPlace(new EditContactPlace(eventBus, rpcService, true, null),
                               false);
    placeService.registerPlace(new AddContactPlace(eventBus, rpcService, true), false);

    eventBus.addHandler(ContactDeletedEvent.TYPE, new ContactDeletedEventHandler()
    {
      @Override
      public void onContactDeleted(ContactDeletedEvent event)
      {
        Window.alert("Selected contacts were successfully deleted!");
      }
    });
  }

  @Override
  public void go(final HasWidgets container)
  {
    // Register place changed event handler
    PlaceChangedEventHandler placeChangedHandler = new DefaultPlaceChangedEventHandler(
      container, placeService);
    eventBus.addHandler(PlaceChangedEvent.TYPE, placeChangedHandler);

    // Force initial place evaluation
    placeService.forcePlaceEvaluation();
  }

  @Override
  public void unbind()
  {
    // Nothing to do for this particular example
  }
}
