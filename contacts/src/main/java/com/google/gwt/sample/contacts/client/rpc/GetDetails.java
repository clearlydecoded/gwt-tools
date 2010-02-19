/**
 * 
 */
package com.google.gwt.sample.contacts.client.rpc;

import java.util.ArrayList;

/**
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class GetDetails
  implements Action<GetDetailsResponse>
{
  final ArrayList<String> ids;

  public GetDetails(ArrayList<String> ids)
  {
    this.ids = ids;
  }

  public ArrayList<String> getIds()
  {
    return ids;
  }
}
