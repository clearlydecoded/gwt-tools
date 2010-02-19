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
package org.tbiq.gwt.tools.placeservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;

/**
 * DefaultPlaceService class is the default implementation of the {@link PlaceService}
 * interface.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultPlaceService
  implements PlaceService
{
  /** History token parser to use for this place service. */
  private HistoryTokenParser historyTokenParser;

  /** Application-wide event bus. */
  private HandlerManager eventBus;

  /** Map of registered places. Each place is keyed by its {@link Place#getViewId()}. */
  private Map<String, Place> registeredPlaces;

  /**
   * Default place to use if no other place is able to be used, e.g., if the presented
   * history token is unknown or blank.
   */
  private Place defaultPlace;

  /**
   * Constructor. Registers itself with the {@link History} management, i.e., to listen
   * for history token changes.
   * 
   * @param historyTokenParser History token parser to use for this place service.
   * @param eventBus Application-wide event bus.
   */
  public DefaultPlaceService(HistoryTokenParser historyTokenParser,
                             HandlerManager eventBus)
  {
    this.historyTokenParser = historyTokenParser;
    this.eventBus = eventBus;

    // Register itself with the History management
    History.addValueChangeHandler(this);

    // Initialize registered places map
    registeredPlaces = new HashMap<String, Place>();
  }

  /**
   * Returns a new instance which is data-wise a duplicate of <code>place</code>, except
   * the isBeAddedToBrowserHistory property, which this method allows to to be modified
   * when creating the new instance. This method relies on the
   * {@link DefaultPlaceService#historyTokenParser} to parse the history token of the
   * provided <code>place</code> to extract the data.
   * 
   * @param place Place to create a duplicate of.
   * @param isBeAddedToBrowserHistory Indicator if the history token of the newly created
   *          place should be added to the browser URL (and therefore browser history)
   *          when processed.
   * @return A new instance which is data-wise a duplicate of <code>place</code>, except
   *         the isBeAddedToBrowserHistory property, which this method allows to to be
   *         modified when creating the new instance.
   */
  public Place duplicatePlace(Place place, boolean isBeAddedToBrowserHistory)
  {
    // Build name/value pairs map based on place's history token
    String historyToken = place.getHistoryToken();
    Map<String, List<String>> nameValuePairs = historyTokenParser.parse(historyToken);

    // Return new instance based on the name/value pairs map
    return place.createPlace(nameValuePairs, isBeAddedToBrowserHistory);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.event.logical.shared.ValueChangeHandler#onValueChange(com.google.gwt
   * .event.logical.shared.ValueChangeEvent)
   */
  @Override
  public void onValueChange(ValueChangeEvent<String> event)
  {
    // Extract name/value map from history token
    String historyToken = event.getValue();
    Map<String, List<String>> nameValuePairs = historyTokenParser.parse(historyToken);

    // If parsing was unsuccessful, fire event with default place
    if (nameValuePairs == null)
    {
      // Fire default place with no history update
      fireDefaultPlaceChangedEvent();
      return;
    }

    // Extract requested view ID; if doesn't exist, assign empty string to avoid NPE
    String requestedViewId = PlaceServiceUtil.getParamValue(nameValuePairs,
                                                            historyTokenParser
                                                              .getViewIdParam(),
                                                            "");


    // If place with requested view ID is not registered, fire event with default place
    Place requestedPlace = registeredPlaces.get(requestedViewId);
    if (requestedPlace == null)
    {
      fireDefaultPlaceChangedEvent();
      return;
    }

    // Construct new instance of Place based on the map of history token data
    requestedPlace = requestedPlace.createPlace(nameValuePairs, false);

    // If creation of place was not successful, fire event with default place
    if (requestedPlace == null)
    {
      fireDefaultPlaceChangedEvent();
      return;
    }

    // Fire event with requested place
    eventBus.fireEvent(new PlaceChangedEvent(requestedPlace));
  }

  /**
   * Fires {@link PlaceChangedEvent} wrapping an instance of
   * {@link DefaultPlaceService#defaultPlace} in the event. If
   * {@link DefaultPlaceService#defaultPlace} is <code>null</code> throws an exception
   * stating that.
   * <p>
   * This method does not add the default place's history token to the URL even if the
   * registered default place instance indicates that it should. Instead, it creates a
   * duplicate of the default place instance and changes its isToBeAddedToBrowserHistory
   * property to false.
   */
  private void fireDefaultPlaceChangedEvent()
  {
    // If defaultPlace is not set, throw an exception
    if (defaultPlace == null)
    {
      throw new RuntimeException("No default place is registered with the PlaceService.");
    }

    // Duplicate default place to change its isToBeAddedToBrowserHistory flag
    Place duplicateDefaultPlace = duplicatePlace(defaultPlace, false);

    // Fire PlaceChangedEvent wrapping defaultPlace in it
    eventBus.fireEvent(new PlaceChangedEvent(duplicateDefaultPlace));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tbiq.gwt.place.PlaceService#registerPlace(org.tbiq.gwt.place.Place, boolean)
   */
  @Override
  public void registerPlace(Place place, boolean isDefaultPlace)
  {
    // Add place to registered places, keyed by view ID
    registeredPlaces.put(place.getViewId(), place);

    // If to be marked as default, remember it as default place
    if (isDefaultPlace)
    {
      defaultPlace = place;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tbiq.gwt.place.PlaceService#forcePlaceEvaluation()
   */
  @Override
  public void forcePlaceEvaluation()
  {
    History.fireCurrentHistoryState();
  }
}
