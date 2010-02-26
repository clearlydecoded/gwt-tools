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

import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;

import com.google.gwt.sample.contacts.shared.Contact;

/**
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class UpdateContactRequest
  implements RpcRequest<UpdateContactResponse>
{
  /** Class version ID. */
  private static final long serialVersionUID = 732509426992612814L;

  /** Updated contact info to update contact in the store with. */
  private final Contact updatedContact;

  /**
   * Constructor.
   * 
   * @param updatedContact Updated contact info to update contact in the store with.
   */
  public UpdateContactRequest(final Contact updatedContact)
  {
    this.updatedContact = updatedContact;
  }

  /**
   * @return Updated contact info to update contact in the store with.
   */
  public Contact getUpdatedContact()
  {
    return updatedContact;
  }
}
