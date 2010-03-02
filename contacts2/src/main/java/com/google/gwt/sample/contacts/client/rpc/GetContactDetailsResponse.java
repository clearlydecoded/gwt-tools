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

import java.util.List;

import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;

import com.google.gwt.sample.contacts.shared.ContactDetails;

/**
 * GetContactDetailsResponse class represents a response to the
 * {@link GetContactDetailsRequest}.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class GetContactDetailsResponse
  implements RpcResponse
{
  /** Class version ID. */
  private static final long serialVersionUID = -2656746574072779384L;

  /** List of contact details. */
  private List<ContactDetails> contactList;

  /**
   * Default constructor. (for serialization).
   */
  public GetContactDetailsResponse()
  {
  }

  /**
   * Constructor.
   * 
   * @param contactList List of contact details.
   */
  public GetContactDetailsResponse(List<ContactDetails> contactList)
  {
    this.contactList = contactList;
  }

  /**
   * @return the contactList
   */
  public List<ContactDetails> getContactList()
  {
    return contactList;
  }

  /**
   * @param contactList the contactList to set
   */
  public void setContactList(List<ContactDetails> contactList)
  {
    this.contactList = contactList;
  }
}
