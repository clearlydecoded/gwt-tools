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

import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;
import org.tbiq.gwt.tools.rpcservice.server.ServletExecutionContext;
import org.tbiq.gwt.tools.rpcservice.server.discovery.RpcHandler;

import com.google.gwt.sample.contacts.client.rpc.UpdateContactRequest;
import com.google.gwt.sample.contacts.client.rpc.UpdateContactResponse;
import com.google.gwt.sample.contacts.server.ContactsStore;
import com.google.gwt.sample.contacts.shared.Contact;

/**
 * UpdateContactRequestHandler class is the server-side handler of requests of type
 * {@link UpdateContactRequest}.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
@RpcHandler
public class UpdateContactRequestHandler
  implements RpcRequestHandler<UpdateContactRequest, UpdateContactResponse>
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
  public UpdateContactResponse execute(UpdateContactRequest rpcRequest,
                                       ServletExecutionContext context)
    throws RpcServiceException
  {
    // Retrieve updated contact and update it in the store
    Contact updatedContact = rpcRequest.getUpdatedContact();
    ContactsStore store = ContactsStore.getContactsStore();
    store.updateContact(updatedContact);

    return new UpdateContactResponse();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler#getCompatibleRpcRequestType()
   */
  @Override
  public Class<UpdateContactRequest> getCompatibleRpcRequestType()
  {
    return UpdateContactRequest.class;
  }
}
