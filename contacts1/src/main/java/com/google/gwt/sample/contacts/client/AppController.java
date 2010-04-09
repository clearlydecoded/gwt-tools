package com.google.gwt.sample.contacts.client;

import org.tbiq.gwt.tools.placeservice.browser.DefaultHistoryTokenParser;
import org.tbiq.gwt.tools.placeservice.browser.DefaultPlaceChangedEventHandler;
import org.tbiq.gwt.tools.placeservice.browser.DefaultPlaceService;
import org.tbiq.gwt.tools.placeservice.browser.PlaceChangedEvent;
import org.tbiq.gwt.tools.placeservice.browser.PlaceChangedEventHandler;
import org.tbiq.gwt.tools.placeservice.browser.PlaceService;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.contacts.client.event.ContactDeletedEvent;
import com.google.gwt.sample.contacts.client.event.ContactDeletedEventHandler;
import com.google.gwt.sample.contacts.client.place.AddContactPlace;
import com.google.gwt.sample.contacts.client.place.EditContactPlace;
import com.google.gwt.sample.contacts.client.place.ListContactsPlace;
import com.google.gwt.sample.contacts.client.presenter.Presenter;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController
  implements Presenter
{
  private final HandlerManager eventBus;
  private final PlaceService placeService;

  public AppController(HandlerManager eventBus)
  {
    this.eventBus = eventBus;
    placeService = new DefaultPlaceService(new DefaultHistoryTokenParser(), eventBus);
    bind();
  }

  private void bind()
  {
    // Register places with the place service
    placeService.registerPlace(new ListContactsPlace(eventBus), true);
    placeService.registerPlace(new EditContactPlace(eventBus));
    placeService.registerPlace(new AddContactPlace(eventBus));

    eventBus.addHandler(ContactDeletedEvent.TYPE, new ContactDeletedEventHandler()
    {
      @Override
      public void onContactDeleted(ContactDeletedEvent event)
      {
        Window.alert("Selected contacts were successfully deleted!");
      }
    });
  }

  public void go(final HasWidgets container)
  {
    // Register place changed event handler
    PlaceChangedEventHandler placeChangedHandler = new DefaultPlaceChangedEventHandler(
      container, placeService);
    eventBus.addHandler(PlaceChangedEvent.TYPE, placeChangedHandler);

    // Force initial place evaluation
    placeService.forcePlaceEvaluation();
  }
}
