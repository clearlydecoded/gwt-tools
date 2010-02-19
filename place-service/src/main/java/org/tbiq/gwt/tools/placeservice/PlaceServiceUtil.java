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

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.History;

/**
 * PlaceServiceUtil class contains utility methods for the place service.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class PlaceServiceUtil
{
  /**
   * Adds history token which represents provided <code>place</code> to browser history.
   * <b>Calling this method does <i>not</i> trigger history token evaluation.</b>
   * 
   * @param place {@link Place} data to add to the browser history.
   */
  public static void addToBrowserHistory(Place place)
  {
    History.newItem(place.getHistoryToken(), false);
  }

  /**
   * @param nameValuePairs Map of parameter names to a list of 1 or more parameter values.
   * @param paramName Name of the parameter to extract the value for.
   * @param defaultValue Default value to return if no value for the
   *          <code>paramName</code> exists in the map.
   * @return A single value for <code>paramName</code> from the map of
   *         <code>nameValuePairs</code>. If the the map contains multiple values for the
   *         <code>paramName</code>, returns the first value in the list. If no value
   *         exists for the <code>paramName</code>, returns <code>defaultValue</code>.
   */
  public static String getParamValue(Map<String, List<String>> nameValuePairs,
                                     String paramName,
                                     String defaultValue)
  {
    // If map is null, return default value
    if (nameValuePairs == null)
    {
      return defaultValue;
    }

    // If list of value for param name doesn't exist, return default value
    List<String> paramValues = nameValuePairs.get(paramName);
    if (paramValues == null)
    {
      return defaultValue;
    }

    // If paramValues list doesn't have any elements, return default value
    if (paramValues.size() == 0)
    {
      return defaultValue;
    }

    String paramValue = paramValues.get(0);
    return paramValue;
  }
}
