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
package org.tbiq.gwt.tools.placeservice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.tbiq.gwt.tools.placeservice.browser.PlaceServiceUtil;

/**
 * PlaceServiceUtilTest is a test class for {@link PlaceServiceUtil}.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class PlaceServiceUtilTest
{

  /**
   * Test method for
   * {@link org.tbiq.gwt.tools.placeservice.browser.PlaceServiceUtil#getParamValue(java.util.Map, java.lang.String, java.lang.String)}
   * .
   */
  @Test
  public void testGetParamValue()
  {
    Map<String, List<String>> nameValuePairs = null;
    assertEquals("nothing", PlaceServiceUtil.getParamValue(nameValuePairs,
                                                           "something",
                                                           "nothing"));

    nameValuePairs = new HashMap<String, List<String>>();
    List<String> valueList = new ArrayList<String>();
    nameValuePairs.put("param1", valueList);
    assertEquals("nothing", PlaceServiceUtil.getParamValue(nameValuePairs,
                                                           "param1",
                                                           "nothing"));

    valueList.add("value1");
    nameValuePairs.put("param1", valueList);
    assertEquals("value1", PlaceServiceUtil.getParamValue(nameValuePairs,
                                                          "param1",
                                                          "nothing"));

    valueList.add("value2");
    nameValuePairs.put("param1", valueList);
    assertEquals("value1", PlaceServiceUtil.getParamValue(nameValuePairs,
                                                          "param1",
                                                          "nothing"));

  }
}
