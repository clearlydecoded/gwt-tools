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
 * GetContactDetailsRequest class represents an RPC request to get a detail list of
 * contacts.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class GetContactDetailsRequest
  implements RpcRequest<GetContactDetailsResponse>
{
  /** Class version ID. */
  private static final long serialVersionUID = -7321323290472983430L;

  /**
   * Default constructor. (for serialization).
   */
  public GetContactDetailsRequest()
  {
  }
}
