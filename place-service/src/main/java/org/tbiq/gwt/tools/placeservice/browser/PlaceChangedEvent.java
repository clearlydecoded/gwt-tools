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

import com.google.gwt.event.shared.GwtEvent;

/**
 * PlaceChangedEvent class represents an event within the application which signifies that
 * a different place (view) should be shown to the end user. The place to show to the end
 * user and all the place specifics are enclosed within the <code>place</code> property of
 * this event.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class PlaceChangedEvent
  extends GwtEvent<PlaceChangedEventHandler>
{
  /** Type of this event - used to register event handler implementation. */
  public static Type<PlaceChangedEventHandler> TYPE = new Type<PlaceChangedEventHandler>();

  /** The place the view should be switched to. */
  private final Place place;

  /**
   * Flag which indicates that the place changed event should show the wrapped in it place
   * instance but should not affect the current URL, i.e., it should call
   * Place.showWithoutUrlUpdate method which guarantees not to update the URL with the
   * wrapped place's history token.
   */
  private final boolean updateUrl;

  /**
   * Constructor.
   * <p>
   * Wraps the <code>place</code> instance. Updates the URL with that <code>place</code>'s
   * history token.
   * 
   * @param place The place the view should be switched to.
   */
  public PlaceChangedEvent(Place place)
  {
    this(place, true);
  }

  /**
   * Constructor.
   * 
   * @param place The place the view should be switched to.
   * @param updateUrl Flag which indicates that the place changed event should show the
   *          wrapped in it place instance but should not affect the current URL, i.e., it
   *          should call Place.showWithoutUrlUpdate method which guarantees not to update
   *          the URL with the wrapped place's history token.
   */
  public PlaceChangedEvent(Place place, boolean updateUrl)
  {
    this.place = place;
    this.updateUrl = updateUrl;
  }

  @Override
  protected void dispatch(PlaceChangedEventHandler handler)
  {
    handler.onPlaceChange(this);
  }

  @Override
  public Type<PlaceChangedEventHandler> getAssociatedType()
  {
    return TYPE;
  }

  /**
   * @return The place the view should be switched to.
   */
  public Place getPlace()
  {
    return place;
  }

  /**
   * @return <code>true</code> if the URL should be updated with the history token
   *         representing the place instance wrapped into this place changed event,
   *         <code>false</code> otherwise.
   */
  public boolean isUpdateUrl()
  {
    return updateUrl;
  }
}
