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

/**
 * GetContactRequest class represents a request for the details of a contact with a
 * specific ID.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class GetContactRequest
  implements RpcRequest<GetContactResponse>
{
  /** Class version ID. */
  private static final long serialVersionUID = 4848650876180814057L;

  /** ID of the contact to retrieve from the server. */
  private final int contactId;

  /**
   * Constructor.
   * 
   * @param contactId ID of the contact to retrieve from the server.
   */
  public GetContactRequest(final int contactId)
  {
    this.contactId = contactId;
  }

  /**
   * @return ID of the contact to retrieve from the server.
   */
  public int getContactId()
  {
    return contactId;
  }
}
