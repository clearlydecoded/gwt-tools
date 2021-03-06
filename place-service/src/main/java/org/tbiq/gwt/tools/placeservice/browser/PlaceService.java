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

import com.google.gwt.event.logical.shared.ValueChangeHandler;

/**
 * PlaceService interface is a service that is registered as the history manager for GWT
 * applications. It converts URL-based history tokens into {@link Place}s and fires
 * {@link PlaceChangedEvent}s.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface PlaceService
  extends ValueChangeHandler<String>
{
  /**
   * @return Instance of the {@link HistoryTokenParser} this place service is using.
   */
  public HistoryTokenParser getHistoryTokenParser();

  /**
   * Registers <code>place</code> as an available place to choose from when interpreting
   * requested URLs. If <code>isDefaultPlace</code> is <code>true</code>, this place is
   * also registered as the default place of the {@link PlaceService}. There can only be
   * one default place per instance of {@link PlaceService}. The default place is used if
   * the history token can not be properly interpreted or is empty.
   * <p>
   * Calling this method with the <code>isDefaultPlace</code> as <code>true</code> when
   * previous such calls have been already made will override the default place to be
   * whatever the last call's place was.
   * <p>
   * <b>Note: The passed in <code>place</code> must not be <code>null</code> and its
   * {@link Place#getViewId()} should return a valid (non-null) unique view ID. The rest
   * of the data contained in the <code>place</code> is irrelevant.</b>
   * 
   * @param place Place to register.
   * @param isDefaultPlace If <code>true</code>, this place is registered as the default
   *          place for this place service instance.
   */
  public void registerPlace(Place place, boolean isDefaultPlace);

  /**
   * Registers <code>place</code> as an available place to choose from when interpreting
   * requested URLs.
   * <p>
   * <b>Note: The passed in <code>place</code> must not be <code>null</code> and its
   * {@link Place#getViewId()} should return a valid (non-null) unique view ID. The rest
   * of the data contained in the <code>place</code> is irrelevant.</b>
   * 
   * @param place Place to register.
   */
  public void registerPlace(Place place);

  /**
   * Forces history token to be evaluated and responded to accordingly. This method is
   * usually called when the application is loading for the first time to trigger history
   * token evaluation (i.e., place evaluation).
   */
  public void forcePlaceEvaluation();
}
