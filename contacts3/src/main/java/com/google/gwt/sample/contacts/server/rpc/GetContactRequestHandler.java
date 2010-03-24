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

import org.springframework.stereotype.Component;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;
import org.tbiq.gwt.tools.rpcservice.server.ServletExecutionContext;
import org.tbiq.gwt.tools.rpcservice.server.discovery.RpcHandler;

import com.google.gwt.sample.contacts.client.rpc.GetContactRequest;
import com.google.gwt.sample.contacts.client.rpc.GetContactResponse;
import com.google.gwt.sample.contacts.server.ContactsStore;
import com.google.gwt.sample.contacts.shared.Contact;

/**
 * GetContactRequestHandler class is the server-side handler of requests of type
 * {@link GetContactRequest}.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
@RpcHandler
@Component
public class GetContactRequestHandler
  implements RpcRequestHandler<GetContactRequest, GetContactResponse>
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
  public GetContactResponse execute(GetContactRequest rpcRequest,
                                    ServletExecutionContext context)
    throws RpcServiceException
  {
    // Retrieve contact with the given ID
    ContactsStore store = ContactsStore.getContactsStore();
    String contactId = rpcRequest.getContactId() + "";
    Contact contact = store.getContact(contactId);

    // Wrap contact into an RPC response object and return response
    GetContactResponse rpcResponse = new GetContactResponse(contact);
    return rpcResponse;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler#getCompatibleRpcRequestType()
   */
  @Override
  public Class<GetContactRequest> getCompatibleRpcRequestType()
  {
    return GetContactRequest.class;
  }
}
