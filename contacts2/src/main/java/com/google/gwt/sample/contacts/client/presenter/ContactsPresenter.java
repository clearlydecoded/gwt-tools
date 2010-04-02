package com.google.gwt.sample.contacts.client.presenter;

import java.util.ArrayList;
import java.util.List;

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
import com.google.gwt.sample.contacts.client.event.ContactDeletedEvent;
import com.google.gwt.sample.contacts.client.place.AddContactPlace;
import com.google.gwt.sample.contacts.client.place.EditContactPlace;
import com.google.gwt.sample.contacts.client.rpc.DeleteContactsRequest;
import com.google.gwt.sample.contacts.client.rpc.DeleteContactsResponse;
import com.google.gwt.sample.contacts.client.rpc.GetContactDetailsRequest;
import com.google.gwt.sample.contacts.client.rpc.GetContactDetailsResponse;
import com.google.gwt.sample.contacts.shared.ContactDetails;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

public class ContactsPresenter
  implements Presenter
{

  private List<ContactDetails> contactDetails;

  public interface Display
    extends View
  {
    HasClickHandlers getAddButton();

    HasClickHandlers getDeleteButton();

    HasClickHandlers getList();

    void setData(List<String> data);

    int getClickedRow(ClickEvent event);

    List<Integer> getSelectedRows();
  }

  private final RpcServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;

  public ContactsPresenter(RpcServiceAsync rpcService,
                           HandlerManager eventBus,
                           Display view)
  {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = view;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tbiq.gwt.tools.presenter.browser.Presenter#bind()
   */
  @Override
  public void bind()
  {
    display.getAddButton().addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent event)
      {
        // Switch to add contact place
        AddContactPlace place = new AddContactPlace(true);
        eventBus.fireEvent(new PlaceChangedEvent(place));
      }
    });

    display.getDeleteButton().addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent event)
      {
        deleteSelectedContacts();
      }
    });

    display.getList().addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent event)
      {
        int selectedRow = display.getClickedRow(event);

        if (selectedRow >= 0)
        {
          // Get contact id based on row clicked
          ContactDetails contactDetails = getContactDetail(selectedRow);

          // Row was clicked to edit, create EditContactPlace
          String id = contactDetails.getId();
          EditContactPlace place = new EditContactPlace(true, id);
          eventBus.fireEvent(new PlaceChangedEvent(place));
        }
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
    bind();
    container.clear();
    container.add(display.asWidget());
    fetchContactDetails();
  }

  public void sortContactDetails()
  {

    // Yes, we could use a more optimized method of sorting, but the
    // point is to create a test case that helps illustrate the higher
    // level concepts used when creating MVP-based applications.
    //
    for (int i = 0; i < contactDetails.size(); ++i)
    {
      for (int j = 0; j < contactDetails.size() - 1; ++j)
      {
        if (contactDetails.get(j).getDisplayName().compareToIgnoreCase(contactDetails
          .get(j + 1).getDisplayName()) >= 0)
        {
          ContactDetails tmp = contactDetails.get(j);
          contactDetails.set(j, contactDetails.get(j + 1));
          contactDetails.set(j + 1, tmp);
        }
      }
    }
  }

  public void setContactDetails(List<ContactDetails> contactDetails)
  {
    this.contactDetails = contactDetails;
  }

  public ContactDetails getContactDetail(int index)
  {
    return contactDetails.get(index);
  }

  private void fetchContactDetails()
  {
    // Define a callback and execute a get contract details request
    DefaultRpcAsyncCallback callback = new DefaultRpcAsyncCallback(
      new DefaultApplicationExceptionHandler())
    {
      @Override
      protected void handleResponse(RpcResponse response)
      {
        // Cast response to GetContactDetailsResponse
        GetContactDetailsResponse getContactDetailsResponse = (GetContactDetailsResponse) response;
        contactDetails = getContactDetailsResponse.getContactList();

        sortContactDetails();
        List<String> data = new ArrayList<String>();

        for (int i = 0; i < contactDetails.size(); ++i)
        {
          data.add(contactDetails.get(i).getDisplayName());
        }

        display.setData(data);
      }
    };
    rpcService.execute(new GetContactDetailsRequest(), callback);
  }

  private void deleteSelectedContacts()
  {
    List<Integer> selectedRows = display.getSelectedRows();

    // Check if at least 1 row was selected
    if (selectedRows.size() == 0)
    {
      Window.alert("You must select at least 1 contact!");
      return;
    }

    ArrayList<String> ids = new ArrayList<String>();

    for (int i = 0; i < selectedRows.size(); ++i)
    {
      ids.add(contactDetails.get(selectedRows.get(i)).getId());
    }

    // Define a callback and execute a delete contracts request
    DefaultRpcAsyncCallback callback = new DefaultRpcAsyncCallback(
      new DefaultApplicationExceptionHandler())
    {
      @Override
      protected void handleResponse(RpcResponse response)
      {
        // Cast response to GetContactDetailsResponse
        DeleteContactsResponse getContactDetailsResponse = (DeleteContactsResponse) response;
        contactDetails = getContactDetailsResponse.getContactList();
        sortContactDetails();
        List<String> data = new ArrayList<String>();

        for (int i = 0; i < contactDetails.size(); ++i)
        {
          data.add(contactDetails.get(i).getDisplayName());
        }

        display.setData(data);
        eventBus.fireEvent(new ContactDeletedEvent());
      }
    };
    rpcService.execute(new DeleteContactsRequest(ids), callback);
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
