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
package org.tbiq.gwt.tools.presenter.browser;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * View interface defines a single method asWidget() which allows the presenter
 * responsible for this view to add it to the container where this view should be
 * displayed.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface View
{
  /**
   * @return The raw representation of this view, i.e., the representation which can be
   *         added directly to a GWT container which implements {@link HasWidgets}
   *         interface.
   */
  public Widget asWidget();
}
