package com.google.gwt.sample.contacts.client;

import org.tbiq.gwt.tools.placeservice.DefaultHistoryTokenParser;
import org.tbiq.gwt.tools.placeservice.DefaultPlaceService;
import org.tbiq.gwt.tools.placeservice.HistoryTokenParser;
import org.tbiq.gwt.tools.placeservice.Place;
import org.tbiq.gwt.tools.placeservice.PlaceChangedEvent;
import org.tbiq.gwt.tools.placeservice.PlaceChangedEventHandler;
import org.tbiq.gwt.tools.placeservice.PlaceService;

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
  private HasWidgets container;
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
    placeService.registerPlace(new ListContactsPlace(historyTokenParser, true), true);
    placeService.registerPlace(new EditContactPlace(historyTokenParser, true, -1), false);
    placeService.registerPlace(new AddContactPlace(historyTokenParser, true), false);

    eventBus.addHandler(PlaceChangedEvent.TYPE, new PlaceChangedEventHandler()
    {
      @Override
      public void onPlaceChange(PlaceChangedEvent event)
      {
        showPlace(event.getPlace());
      }
    });

    eventBus.addHandler(ContactDeletedEvent.TYPE, new ContactDeletedEventHandler()
    {
      @Override
      public void onContactDeleted(ContactDeletedEvent event)
      {
        Window.alert("Contact Deleted!");
      }
    });
  }

  /**
   * Shows place in the container for this app controller.
   * 
   * @param place {@link Place} to show.
   */
  protected void showPlace(Place place)
  {
    place.show(container, eventBus);
  }

  public void go(final HasWidgets container)
  {
    this.container = container;

    // Force initial place evaluation
    placeService.forcePlaceEvaluation();
  }
}
