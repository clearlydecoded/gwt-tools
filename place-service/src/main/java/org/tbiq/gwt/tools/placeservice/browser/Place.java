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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Place interface represents a place within an application from the end user prospective.
 * For example, a ListContactsPlace could represent a view which shows a list of contacts,
 * including appropriately visually selected menu items, header/footer of the app, etc.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface Place
  extends Serializable
{
  /**
   * Returns the view ID of this place. For example, ListContactsPlace view ID can be
   * 'listContacts'. The view ID should generally be an exact match for some part of the
   * history token which denotes the requested view ID.
   * <p>
   * <b>The view ID must be unique among all the the implementing classes of this
   * interface within a single application.</b>
   * 
   * @return Returns the view ID of this place.
   */
  public String getViewId();

  /**
   * @return History token which represents this place, i.e., manually placing this
   *         history token on the URL should display the same view to the end user as this
   *         place represents, populated with the given data from the history token (if
   *         any).
   */
  public String getHistoryToken();

  /**
   * Sets the history token parser to use in this place.
   * 
   * @param historyTokenParser Instance of {@link HistoryTokenParser} to be used in this
   *          place.
   */
  public void setHistoryTokenParser(HistoryTokenParser historyTokenParser);

  /**
   * Returns an instance of concrete implementation of {@link Place}. The implementation
   * of this method <i>may</i> assume that the view ID contained in the
   * <code>nameValuePairs</code> matches the value of its {@link Place#getViewId()} method
   * and attempt to retrieve the rest of the items (if any) out of
   * <code>nameValuePairs</code> based on that assumption.
   * <p>
   * The constructed instance is <i>usually</i> of the same type as the type of instance
   * this method belongs to. However, it's possible to return a different concrete
   * implementation of {@link Place} instead. Usually, returning an instance of a
   * different type would make sense if the data provided in the
   * <code>nameValuePairs</code> is missing (or incorrect) in order to create of an
   * instance of the same type. In such a case, it would make sense to either return
   * <code>null</code> or return some other instance of type {@link Place}. For example,
   * if 'editPerson' place is requested, but no 'id' of the person to edit is provided in
   * the <code>nameValuePairs</code> map, it would make sense to return an instance of
   * {@link Place} which identifies 'createNewPerson' place instead.
   * <p>
   * If creation is not successful, returns <code>null</code>.
   * <p>
   * <b>Invoking this method on some instance of place should NOT in any way alter that
   * place's state.</b>
   * 
   * @param nameValuePairs {@link Map} of known parameters (as keys) to one or more values
   *          for that parameter. Each place should know its predefined parameters it must
   *          look for in the map.
   * @return An instance of concrete implementation of {@link Place}. If creation is not
   *         successful, returns <code>null</code>.
   */
  public Place createPlace(Map<String, List<String>> nameValuePairs);

  /**
   * This method is similar to the classic Command Pattern (i.e., execute()), which should
   * know how to invoke functionality to display a view (or construct proper presenters
   * which would show the view if MVP pattern is used) to the end user which matches this
   * place.
   * 
   * @param container Container to add the corresponding to this place view into.
   */
  public void show(final HasWidgets container);

  /**
   * This method is exactly the same as {@link Place#show(HasWidgets)} method, but the
   * implementations of this method <b>must</b> guarantee that the URL will NOT be updated
   * as a result of showing this place, i.e., the current URL will stay unmodified.
   * 
   * @param container Container to add the corresponding to this place view into.
   */
  public void showWithoutUrlUpdate(final HasWidgets container);
}
