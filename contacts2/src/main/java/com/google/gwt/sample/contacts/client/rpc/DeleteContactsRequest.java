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

import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;

/**
 * DeleteContactsRequest class represents a delete request of one or more contacts.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DeleteContactsRequest
  implements RpcRequest<DeleteContactsResponse>
{
  /** Class version ID. */
  private static final long serialVersionUID = 7156731475960625950L;

  /** List of IDs of contacts to delete. */
  private List<String> ids;

  /**
   * Default constructor. (for serialization).
   */
  public DeleteContactsRequest()
  {
  }

  /**
   * Constructor.
   * 
   * @param ids List of IDs of contacts to delete.
   */
  public DeleteContactsRequest(List<String> ids)
  {
    this.ids = ids;
  }

  /**
   * @return List of IDs of contacts to delete.
   */
  public List<String> getIds()
  {
    return ids;
  }

  /**
   * @param id List of IDs of contacts to delete.
   */
  public void setIds(List<String> ids)
  {
    this.ids = ids;
  }
}
