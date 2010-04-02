package com.google.gwt.sample.contacts.client;

import org.tbiq.gwt.tools.placeservice.browser.DefaultHistoryTokenParser;
import org.tbiq.gwt.tools.placeservice.browser.DefaultPlaceChangedEventHandler;
import org.tbiq.gwt.tools.placeservice.browser.DefaultPlaceService;
import org.tbiq.gwt.tools.placeservice.browser.HistoryTokenParser;
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
  private final HistoryTokenParser historyTokenParser;

  public AppController(HandlerManager eventBus)
  {
    this.eventBus = eventBus;
    historyTokenParser = new DefaultHistoryTokenParser();
    placeService = new DefaultPlaceService(historyTokenParser, eventBus);
    bind();
  }

  private void bind()
  {
    // Register places with the place service
    placeService.registerPlace(new ListContactsPlace(true), true);
    placeService.registerPlace(new EditContactPlace(true, null), false);
    placeService.registerPlace(new AddContactPlace(true), false);

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
      container, eventBus, placeService);
    eventBus.addHandler(PlaceChangedEvent.TYPE, placeChangedHandler);

    // Force initial place evaluation
    placeService.forcePlaceEvaluation();
  }
}
