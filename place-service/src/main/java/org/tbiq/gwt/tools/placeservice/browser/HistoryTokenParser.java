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

import java.util.List;
import java.util.Map;

/**
 * HistoryTokenParser interface defines methods to extract information out of history
 * tokens as a map of parameter name to a list of 1 or more parameter values. In other
 * words, the implementation parser must support having multiple instances of the same
 * parameter name as part of the history token.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface HistoryTokenParser
{
  /**
   * Returns <code>currentHistoryToken</code> appended with <code>paramName</code> and
   * <code>paramValue</code>. The values are appended in the same format that the
   * {@link HistoryTokenParser#parse(String)} method expects the history token to be in
   * order to be parsable.
   * <p>
   * If <code>currentHistoryToken</code> is <code>null</code>, an empty string for the
   * current history token is assumed.
   * <p>
   * <b>Neither <code>paramName</code> nor <code>paramValue</code> is allowed to be
   * <code>null</code>. In addition, <code>paramName</code> is not allowed to be an empty
   * string (after trimming).</b>
   * 
   * 
   * @param currentHistoryToken Current history token to append parameter name/value pair.
   * @param paramName Parameter name to append.
   * @param paramValue Parameter value to append.
   * @return <code>currentHistoryToken</code> appended with <code>paramName</code> and
   *         <code>paramValue</code>.
   * @throws NullPointerException If either <code>paramName</code> or
   *           <code>paramValue</code> is <code>null</code> or if <code>paramName</code>
   *           is an empty string (after trimming).
   */
  public String buildHistoryToken(String currentHistoryToken,
                                  String paramName,
                                  String paramValue)
    throws NullPointerException;

  /**
   * @return Name of the parameter which identifies the view ID value.
   */
  public String getViewIdParam();

  /**
   * Parses the <code>historyToken</code> and returns the information contained in the
   * history token as a map of parameter name to a list of 1 or more parameter values.
   * <p>
   * If any parsing problems occur, this method return <code>null</code>.
   * 
   * @param historyToken History token from the URL, i.e., everything after the initial
   *          '#' sign. (E.g., in the URL
   *          http://someHost/somePage.html#view=someView&id=20, 'view=someView&id=20' is
   *          the history token).
   * @return The information contained in the <code>historyToken</code> as a map of
   *         parameter name to a list of 1 or more parameter values or <code>null</code>
   *         if any parsing problems occur.
   */
  public Map<String, List<String>> parse(String historyToken);
}
