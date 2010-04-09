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
import com.google.gwt.sample.contacts.client.presenter.EditContactPresenter;
import com.google.gwt.sample.contacts.client.view.EditContactView;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * AddContactPlace class represents the view that allows adding a new contact.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class AddContactPlace
  implements Place
{
  /** Class version ID. */
  private static final long serialVersionUID = -5499503727843493372L;

  /** View ID of this place. */
  public static String VIEW_ID = "add";

  /** Flag if the history token of this place should be added to the browser URL. */
  private boolean toBeAddedToBrowserHistory;

  /** Application-wide event bus. */
  private final HandlerManager eventBus;

  /** RPC service to use. */
  private final RpcServiceAsync rpcService;

  /**
   * History token parser which knows the format to use when building history token based
   * on this place's data.
   */
  private HistoryTokenParser historyTokenParser;

  /**
   * Constructor.
   * 
   * @param eventBus Application-wide event bus.
   * @param rpcService RPC service to use.
   * @param toBeAddedToBrowserHistory Flag if the history token of this place should be
   *          added to the browser URL.
   */
  public AddContactPlace(HandlerManager eventBus,
                         RpcServiceAsync rpcService,
                         boolean toBeAddedToBrowserHistory)
  {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    this.toBeAddedToBrowserHistory = toBeAddedToBrowserHistory;
  }

  @Override
  public Place createPlace(Map<String, List<String>> nameValuePairs,
                           boolean toBeAddedToBrowserHistory)
  {

    return new AddContactPlace(eventBus, rpcService, toBeAddedToBrowserHistory);
  }

  @Override
  public String getHistoryToken()
  {
    return historyTokenParser.buildHistoryToken("",
                                                historyTokenParser.getViewIdParam(),
                                                VIEW_ID);
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
  public void show(HasWidgets container)
  {
    // Add history token to URL if so indicated
    PlaceServiceUtil.addToBrowserHistory(this);

    // Create presenter and execute
    Presenter editcontactPresenter = new EditContactPresenter(rpcService, eventBus,
      new EditContactView());
    editcontactPresenter.go(container);
  }

  @Override
  public void setHistoryTokenParser(HistoryTokenParser historyTokenParser)
  {
    this.historyTokenParser = historyTokenParser;
  }
}
