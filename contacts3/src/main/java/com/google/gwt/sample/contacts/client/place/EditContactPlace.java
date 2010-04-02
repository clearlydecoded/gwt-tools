/**
 * Copyright 2010 Yaakov Chaikin (yaakov.chaikin@gmail.com) Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed
 * to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the
 * License.
 */
package com.google.gwt.sample.contacts.client.place;

import java.util.List;
import java.util.Map;

import org.tbiq.gwt.tools.placeservice.browser.HistoryTokenParser;
import org.tbiq.gwt.tools.placeservice.browser.Place;
import org.tbiq.gwt.tools.placeservice.browser.PlaceServiceUtil;
import org.tbiq.gwt.tools.presenter.browser.Presenter;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceAsync;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.contacts.client.Contacts;
import com.google.gwt.sample.contacts.client.presenter.EditContactPresenter;
import com.google.gwt.sample.contacts.client.view.EditContactView;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * EditContactPlace class represents the view that allows editing of the info a particular
 * contact.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class EditContactPlace
  implements Place
{
  /** Class version ID. */
  private static final long serialVersionUID = -808135099530157227L;

  /** View ID of this place. */
  public static String VIEW_ID = "edit";

  /** Param name of the ID of the person to edit. */
  private static String CONTACT_ID_PARAM_NAME = "id";

  /** Flag if the history token of this place should be added to the browser URL. */
  private boolean toBeAddedToBrowserHistory;

  /**
   * History token parser which knows the format to use when building history token based
   * on this place's data.
   */
  private HistoryTokenParser historyTokenParser;

  /** ID of the contact to edit. */
  private String contactId;


  /**
   * Constructor.
   * 
   * @param toBeAddedToBrowserHistory Flag if the history token of this place should be
   *          added to the browser URL.
   * @param contactId ID of the contact to edit.
   */
  public EditContactPlace(boolean toBeAddedToBrowserHistory, String contactId)
  {
    this.toBeAddedToBrowserHistory = toBeAddedToBrowserHistory;
    this.contactId = contactId;
  }

  /**
   * @return ID of the contact to edit.
   */
  protected String getContactId()
  {
    return contactId;
  }

  @Override
  public Place createPlace(Map<String, List<String>> nameValuePairs,
                           boolean toBeAddedToBrowserHistory)
  {
    // Retrieve contact ID to edit
    String idString = PlaceServiceUtil.getParamValue(nameValuePairs,
                                                     CONTACT_ID_PARAM_NAME,
                                                     null);

    // Return null if ID value doesn't exit
    if (idString == null)
    {
      // Creation failed; makes sense to just go to 'add contact place'
      return new AddContactPlace(toBeAddedToBrowserHistory);
    }

    // Return null if ID value is not translatable to an int (being extra cautious)
    try
    {
      @SuppressWarnings("unused")
      int id = Integer.parseInt(idString);
    }
    catch (NumberFormatException e)
    {
      // ID is not translatable to an int; makes sense to just go to 'add contact place'
      return new AddContactPlace(toBeAddedToBrowserHistory);
    }

    return new EditContactPlace(toBeAddedToBrowserHistory, idString);
  }

  @Override
  public String getHistoryToken()
  {
    String historyToken = historyTokenParser.buildHistoryToken("", historyTokenParser
      .getViewIdParam(), VIEW_ID);
    historyToken = historyTokenParser.buildHistoryToken(historyToken,
                                                        CONTACT_ID_PARAM_NAME,
                                                        contactId);

    return historyToken;
  }

  @Override
  public String getViewId()
  {
    return VIEW_ID;
  }

  @Override
  public boolean isToBeAddedToBrowserHistory()
  {
    return toBeAddedToBrowserHistory;
  }

  @Override
  public void setToBeAddedToBrowserHistory(boolean toBeAddedToBrowserHistory)
  {
    this.toBeAddedToBrowserHistory = toBeAddedToBrowserHistory;
  }

  @Override
  public void show(HasWidgets container, HandlerManager eventBus)
  {
    // Add history token to URL if so indicated
    if (toBeAddedToBrowserHistory)
    {
      PlaceServiceUtil.addToBrowserHistory(this);
    }

    // Initialize async service needed for the presenter
    RpcServiceAsync rpcService = Contacts.RPC_SERVICE;

    // Create presenter and execute
    Presenter editContactPresenter = new EditContactPresenter(rpcService, eventBus,
      new EditContactView(), historyTokenParser, contactId);
    editContactPresenter.go(container);
  }

  @Override
  public void setHistoryTokenParser(HistoryTokenParser historyTokenParser)
  {
    this.historyTokenParser = historyTokenParser;
  }
}
