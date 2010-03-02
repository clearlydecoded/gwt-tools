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
package com.google.gwt.sample.contacts.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.sample.contacts.shared.Contact;
import com.google.gwt.sample.contacts.shared.ContactDetails;

/**
 * ContactsStore class stores all the information about contacts.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class ContactsStore
{
  private static final String[] contactsFirstNameData = new String[] {"Hollie",
    "Emerson", "Healy", "Brigitte", "Elba", "Claudio", "Dena", "Christina", "Gail",
    "Orville", "Rae", "Mildred", "Candice", "Louise", "Emilio", "Geneva", "Heriberto",
    "Bulrush", "Abigail", "Chad", "Terry", "Bell" };

  private final String[] contactsLastNameData = new String[] {"Voss", "Milton",
    "Colette", "Cobb", "Lockhart", "Engle", "Pacheco", "Blake", "Horton", "Daniel",
    "Childers", "Starnes", "Carson", "Kelchner", "Hutchinson", "Underwood", "Rush",
    "Bouchard", "Louis", "Andrews", "English", "Snedden" };

  private final String[] contactsEmailData = new String[] {"mark@example.com",
    "hollie@example.com", "boticario@example.com", "emerson@example.com",
    "healy@example.com", "brigitte@example.com", "elba@example.com",
    "claudio@example.com", "dena@example.com", "brasilsp@example.com",
    "parker@example.com", "derbvktqsr@example.com", "qetlyxxogg@example.com",
    "antenas_sul@example.com", "cblake@example.com", "gailh@example.com",
    "orville@example.com", "post_master@example.com", "rchilders@example.com",
    "buster@example.com", "user31065@example.com", "ftsgeolbx@example.com" };

  private final HashMap<String, Contact> contacts = new HashMap<String, Contact>();

  /** Private instance of this class. */
  private static ContactsStore contactsStore;

  /**
   * @return The single instance of ContactsStore.
   */
  public static ContactsStore getContactsStore()
  {
    // Check if contactsStore needs to be created first
    if (contactsStore == null)
    {
      contactsStore = new ContactsStore();
    }

    return contactsStore;
  }

  private ContactsStore()
  {
    initContacts();
  }

  private void initContacts()
  {
    // TODO: Create a real UID for each contact
    //
    for (int i = 0; i < contactsFirstNameData.length && i < contactsLastNameData.length
                    && i < contactsEmailData.length; ++i)
    {
      Contact contact = new Contact(String.valueOf(i), contactsFirstNameData[i],
        contactsLastNameData[i], contactsEmailData[i]);
      contacts.put(contact.getId(), contact);
    }
  }

  public synchronized Contact addContact(Contact contact)
  {
    contact.setId(String.valueOf(contacts.size()));
    contacts.put(contact.getId(), contact);
    return contact;
  }

  public synchronized Contact updateContact(Contact contact)
  {
    contacts.remove(contact.getId());
    contacts.put(contact.getId(), contact);
    return contact;
  }

  public synchronized Boolean deleteContact(String id)
  {
    contacts.remove(id);
    return true;
  }

  public synchronized ArrayList<ContactDetails> deleteContacts(ArrayList<String> ids)
  {

    for (int i = 0; i < ids.size(); ++i)
    {
      deleteContact(ids.get(i));
    }

    return getContactDetails();
  }

  public synchronized ArrayList<ContactDetails> getContactDetails()
  {
    ArrayList<ContactDetails> contactDetails = new ArrayList<ContactDetails>();

    Iterator<String> it = contacts.keySet().iterator();
    while (it.hasNext())
    {
      Contact contact = contacts.get(it.next());
      contactDetails.add(contact.getLightWeightContact());
    }

    return contactDetails;
  }

  public synchronized Contact getContact(String id)
  {
    return contacts.get(id);
  }
}
