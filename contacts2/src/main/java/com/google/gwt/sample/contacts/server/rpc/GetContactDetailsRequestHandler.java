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
package com.google.gwt.sample.contacts.server.rpc;

import java.util.List;

import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;
import org.tbiq.gwt.tools.rpcservice.server.ServletExecutionContext;

import com.google.gwt.sample.contacts.client.rpc.GetContactDetailsRequest;
import com.google.gwt.sample.contacts.client.rpc.GetContactDetailsResponse;
import com.google.gwt.sample.contacts.server.ContactsStore;
import com.google.gwt.sample.contacts.shared.ContactDetails;

/**
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class GetContactDetailsRequestHandler
  implements RpcRequestHandler<GetContactDetailsRequest, GetContactDetailsResponse>

{
  /*
   * (non-Javadoc)
   * 
   * @see
   * org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler#execute(org.tbiq.gwt.tools
   * .rpcservice.browser.RpcRequest,
   * org.tbiq.gwt.tools.rpcservice.server.ServletExecutionContext)
   */
  @Override
  public GetContactDetailsResponse execute(GetContactDetailsRequest rpcRequest,
                                           ServletExecutionContext context)
    throws RpcServiceException
  {
    // Retrieve details of all contacts
    ContactsStore store = ContactsStore.getContactsStore();
    List<ContactDetails> contactList = store.getContactDetails();

    // Wrap contact list in a response object and return the response
    GetContactDetailsResponse rpcResponse = new GetContactDetailsResponse(contactList);
    return rpcResponse;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler#getCompatibleType()
   */
  @Override
  public Class<GetContactDetailsRequest> getCompatibleType()
  {
    return GetContactDetailsRequest.class;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler#isCompatibleWith(java.lang
   * .Class)
   */
  @Override
  public boolean isCompatibleWith(Class<? extends RpcRequest<? extends RpcResponse>> rpcRequestClass)
  {
    if (rpcRequestClass == GetContactDetailsRequest.class)
    {
      return true;
    }

    return false;
  }
}
