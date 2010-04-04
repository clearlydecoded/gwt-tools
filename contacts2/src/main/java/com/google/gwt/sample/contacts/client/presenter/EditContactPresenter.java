package com.google.gwt.sample.contacts.client.presenter;

import org.tbiq.gwt.tools.placeservice.browser.HistoryTokenParser;
import org.tbiq.gwt.tools.placeservice.browser.Place;
import org.tbiq.gwt.tools.placeservice.browser.PlaceChangedEvent;
import org.tbiq.gwt.tools.presenter.browser.Presenter;
import org.tbiq.gwt.tools.presenter.browser.View;
import org.tbiq.gwt.tools.rpcservice.browser.DefaultApplicationExceptionHandler;
import org.tbiq.gwt.tools.rpcservice.browser.DefaultRpcAsyncCallback;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.contacts.client.place.ListContactsPlace;
import com.google.gwt.sample.contacts.client.rpc.GetContactRequest;
import com.google.gwt.sample.contacts.client.rpc.GetContactResponse;
import com.google.gwt.sample.contacts.client.rpc.UpdateContactRequest;
import com.google.gwt.sample.contacts.shared.Contact;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;

public class EditContactPresenter
  implements Presenter
{
  public interface Display
    extends View
  {
    HasClickHandlers getSaveButton();

    HasClickHandlers getCancelButton();

    HasValue<String> getFirstName();

    HasValue<String> getLastName();

    HasValue<String> getEmailAddress();
  }

  private Contact contact;
  private final RpcServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;

  public EditContactPresenter(RpcServiceAsync rpcService,
                              HandlerManager eventBus,
                              Display display)
  {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.contact = new Contact();
    this.display = display;
    bind();
  }

  public EditContactPresenter(RpcServiceAsync rpcService,
                              HandlerManager eventBus,
                              Display display,
                              HistoryTokenParser historyTokenParser,
                              String id)
  {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = display;
    bind();

    // Define a callback and execute a get contract request
    DefaultRpcAsyncCallback callback = new DefaultRpcAsyncCallback(
      new DefaultApplicationExceptionHandler())
    {
      @Override
      protected void handleResponse(RpcResponse response)
      {
        // Cast response to GetContactResponse (because of GWT compiler generics bug)
        GetContactResponse getContactResponse = (GetContactResponse) response;
        contact = getContactResponse.getContact();

        // Display retrieved contact
        EditContactPresenter.this.display.getFirstName().setValue(contact.getFirstName());
        EditContactPresenter.this.display.getLastName().setValue(contact.getLastName());
        EditContactPresenter.this.display.getEmailAddress().setValue(contact
          .getEmailAddress());
      }
    };
    rpcService.execute(new GetContactRequest(id), callback);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tbiq.gwt.tools.presenter.browser.Presenter#bind()
   */
  @Override
  public void bind()
  {
    this.display.getSaveButton().addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent event)
      {
        doSave();
      }
    });

    this.display.getCancelButton().addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent event)
      {
        eventBus.fireEvent(new PlaceChangedEvent(new ListContactsPlace(eventBus, true)));
      }
    });
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.tbiq.gwt.tools.presenter.browser.Presenter#go(com.google.gwt.user.client.ui.
   * HasWidgets)
   */
  @Override
  public void go(final HasWidgets container)
  {
    container.clear();
    container.add(display.asWidget());
  }

  private void doSave()
  {
    contact.setFirstName(display.getFirstName().getValue());
    contact.setLastName(display.getLastName().getValue());
    contact.setEmailAddress(display.getEmailAddress().getValue());

    // Define a callback and execute an update contact request
    DefaultRpcAsyncCallback callback = new DefaultRpcAsyncCallback(
      new DefaultApplicationExceptionHandler())
    {
      @Override
      protected void handleResponse(RpcResponse response)
      {
        // On successful update (do nothing with response), switch to list contacts place
        Place listContactsPlace = new ListContactsPlace(eventBus, true);
        eventBus.fireEvent(new PlaceChangedEvent(listContactsPlace));
      }
    };
    rpcService.execute(new UpdateContactRequest(contact), callback);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tbiq.gwt.tools.presenter.browser.Presenter#unbind()
   */
  @Override
  public void unbind()
  {
    // Nothing to do for this particular example
  }
}
