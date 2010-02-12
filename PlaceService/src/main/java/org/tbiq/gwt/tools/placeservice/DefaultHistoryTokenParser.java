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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DefaultHistoryTokenParser class is the default implementation of the
 * {@link HistoryTokenParser} interface.
 * <p>
 * This implementation expects the history token (i.e., part of the URL after the first
 * occurrence of '#' sign) to be in the same format as a standard HTTP query string, e.g.,
 * view=list, view=list&id=20, etc. In keeping with HTTP query string format standard, the
 * parser supports having multiple values for any given parameter name.
 * <p>
 * The result of the parsing is a map of each request attribute to a list of 1 or more of
 * values.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultHistoryTokenParser
  implements HistoryTokenParser<Map<String, List<String>>>
{
  /** Regular expression to validate format of a history token. */
  private static final String HISTORY_TOKEN_REGEX = "\\p{Alpha}+[\\p{Alnum}]*=[\\p{Alnum}.\\-*_+%()]*(&\\p{Alpha}+\\p{Alnum}*=[\\p{Alnum}.\\-*_+%()]*)*";

  /*
   * (non-Javadoc)
   * 
   * @see org.tbiq.gwt.place.HistoryTokenParser#parse(java.lang.String)
   */
  @Override
  public Map<String, List<String>> parse(String historyToken)
  {
    // Ensure the history token is in proper format
    if (!isValidHistoryToken(historyToken))
    {
      return null;
    }

    // Break up the name/value pair strings
    String[] nameValuePairStrings = historyToken.split("&");

    // Loop through the name/value pair strings
    Map<String, List<String>> keyedValueMap = new HashMap<String, List<String>>();
    for (String nameValuePairString : nameValuePairStrings)
    {
      // Break up name/value string into name/value pair (always force 2 groups)
      String[] nameValuePair = nameValuePairString.split("=", 2);
      String name = nameValuePair[0];
      String value = nameValuePair[1];

      // Add name/value to map if value is not an empty string
      if (!value.trim().isEmpty())
      {
        // Check if this name doesn't have any values yet
        List<String> values = keyedValueMap.get(name);
        if (values == null)
        {
          // Create new list to hold values
          values = new ArrayList<String>();

          // Add new list to map
          keyedValueMap.put(name, values);
        }

        // Add new value to the values list for this name
        values.add(value);
      }
    }

    return keyedValueMap;
  }

  /**
   * @param historyToken Entire history token, i.e., part of the URL after the first
   *          occurance of the '#' sign. The valid history token format is the same format
   *          as a standard HTTP query string, e.g., view=list, view=list&id=20, etc. In
   *          keeping with HTTP query string format standard, it is valid for the history
   *          token to contain multiple values for any given parameter name.
   * @return <code>true<code> if the <code>historyToken</code> comforms to the format of
   *         'name=value' or 'name1=value1&name2=value2', i.e., standard HTTP query string
   *         format, <code>false</code> otherwise. If the value is <code>null</code>,
   *         <code>false</code> is returned.
   */
  public boolean isValidHistoryToken(String historyToken)
  {
    // Check historyToken for null
    if (historyToken == null)
    {
      return false;
    }

    return historyToken.matches(HISTORY_TOKEN_REGEX);
  }
}
