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
package com.google.gwt.sample.contacts.client.rpc;

import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;

import com.google.gwt.sample.contacts.shared.Contact;

/**
 * GetContactResponse class represents a response to the {@link GetContactRequest}.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class GetContactResponse
  implements RpcResponse
{
  /** Class version ID. */
  private static final long serialVersionUID = -4334636472137820671L;

  /** Contact details retrieved from the server. */
  private Contact contact;

  /**
   * Default constructor. (for serialization).
   */
  public GetContactResponse()
  {
  }

  /**
   * Constuctor.
   * 
   * @param contact Contact details retrieved from the server.
   */
  public GetContactResponse(Contact contact)
  {
    this.contact = contact;
  }

  /**
   * @return Contact details retrieved from the server.
   */
  public Contact getContact()
  {
    return contact;
  }

  /**
   * @param contact the contact to set
   */
  public void setContact(Contact contact)
  {
    this.contact = contact;
  }
}
