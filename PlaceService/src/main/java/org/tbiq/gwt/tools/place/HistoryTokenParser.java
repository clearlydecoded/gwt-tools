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

/**
 * HistoryTokenParser interface defines methods to extract information out of history
 * tokens as some T type.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface HistoryTokenParser<T>
{
  /**
   * Parses the <code>historyToken</code> and returns some structured form of the
   * information contained in the history token.
   * <p>
   * If any parsing problems occur, this method return <code>null</code>.
   * 
   * @param historyToken History token from the URL, i.e., everything after the initial
   *          '#' sign. (E.g., in the URL
   *          http://someHost/somePage.html#view=someView&id=20, 'view=someView&id=20' is
   *          the history token).
   * @return Structured information about what was contained in the
   *         <code>historyToken</code> or <code>null</code> if any parsing problems occur.
   */
  public T parse(String historyToken);
}
