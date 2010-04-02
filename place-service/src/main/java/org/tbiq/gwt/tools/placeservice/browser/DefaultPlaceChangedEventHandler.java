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
package org.tbiq.gwt.tools.placeservice.browser;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * DefaultPlaceChangedEventHandler class is the default implementation of the
 * {@link PlaceChangedEventHandler} interface.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultPlaceChangedEventHandler
  implements PlaceChangedEventHandler
{
  /** Container to add the corresponding to this place view into. */
  private final HasWidgets container;

  /** Application-wide event bus. */
  private final HandlerManager eventBus;

  /** Place service used in the application. */
  private final PlaceService placeService;

  /**
   * Constructor.
   * 
   * @param container Container to add the corresponding to this place view into.
   * @param eventBus Application-wide event bus.
   * @param placeService Place service used in the application.
   */
  public DefaultPlaceChangedEventHandler(final HasWidgets container,
                                         final HandlerManager eventBus,
                                         final PlaceService placeService)
  {
    // Check that container is not null (done here since if it is, it wouldn't come out
    // until later in the place's show method and wouldn't be as obvious why it's null)
    if (container == null)
    {
      String message = "container must not be null.";
      throw new NullPointerException(message);
    }

    this.container = container;
    this.eventBus = eventBus;
    this.placeService = placeService;
  }

  @Override
  public void onPlaceChange(PlaceChangedEvent event)
  {
    // Retrieve wrapped place
    Place requestedPlace = event.getPlace();

    // Inject the history token parser used by the place service into this place
    requestedPlace.setHistoryTokenParser(placeService.getHistoryTokenParser());

    // Show requested place
    requestedPlace.show(container, eventBus);
  }
}
