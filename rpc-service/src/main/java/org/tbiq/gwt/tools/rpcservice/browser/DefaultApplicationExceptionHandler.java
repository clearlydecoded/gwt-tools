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
package org.tbiq.gwt.tools.rpcservice.browser;

import com.google.gwt.user.client.Window;

/**
 * DefaultApplicationExceptionHandler class is the default implementation of the
 * {@link ApplicationExceptionHandler} interface. It uses a pop-up window to display the
 * message in the exception. It is provided as part of the rpc-service package as a
 * starting point and convenience. Most application should create their own
 * implementations of the {@link ApplicationExceptionHandler} interface to provide styled,
 * non-native pop-ups, etc.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultApplicationExceptionHandler
  implements ApplicationExceptionHandler
{
  @Override
  public void handleException(Throwable exception)
  {
    exception.printStackTrace();
    Window.alert(exception.getMessage());
  }
}
