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
package org.tbiq.gwt.tools.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Presenter interface contains methods which are typically used in MVP (Model View
 * Presenter) pattern.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface Presenter
{
  /**
   * This method typically binds the view's artifacts to some behavior. This binding is
   * typically accomplished by attaching event handlers to the event bus (
   * {@link HandlerManager}).
   * <p>
   * It is usually called from the constructor of the implementing concrete class.
   */
  public void bind();

  /**
   * This method makes the presenter "go", i.e., it shows the view with its view
   * components already bound from before.
   * 
   * @param container Container to which to add the view which this presenter controls.
   */
  public void go(final HasWidgets container);

  /**
   * This method typically unbinds whatever bindings were made during the
   * {@link Presenter#bind()} call.
   * <p>
   * It is usually called when the presenter is tearing itself down, i.e., a different
   * present/view is being loaded instead and this presenter no longer needs to listen for
   * events that affect its view.
   */
  public void unbind();
}
