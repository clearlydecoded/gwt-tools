/**
 * 
 */
package com.google.gwt.sample.contacts.client.rpc;

import java.util.ArrayList;

import com.google.gwt.sample.contacts.shared.ContactDetails;

/**
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class GetDetailsResponse
  implements Response
{
  private final ArrayList<ContactDetails> details;

  public GetDetailsResponse(ArrayList<ContactDetails> details)
  {
    this.details = details;
  }

  /**
   * @return the details
   */
  public ArrayList<ContactDetails> getDetails()
  {
    return details;
  }
}
