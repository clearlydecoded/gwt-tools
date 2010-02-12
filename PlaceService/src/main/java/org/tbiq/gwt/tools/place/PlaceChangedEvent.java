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
package org.tbiq.gwt.tools.place;

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

  /** The place the view should be switch to. */
  private final Place place;

  /**
   * Constructor.
   * 
   * @param place The place the view should be switch to.
   */
  public PlaceChangedEvent(Place place)
  {
    this.place = place;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler
   * )
   */
  @Override
  protected void dispatch(PlaceChangedEventHandler handler)
  {
    handler.onPlaceChange(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
   */
  @Override
  public Type<PlaceChangedEventHandler> getAssociatedType()
  {
    return TYPE;
  }

  /**
   * @return The place this event is associate with (the place the view should be switch
   *         to).
   */
  public Place getPlace()
  {
    return place;
  }
}
