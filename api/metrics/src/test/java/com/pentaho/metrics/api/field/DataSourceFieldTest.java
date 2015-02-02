/*!
 * PENTAHO CORPORATION PROPRIETARY AND CONFIDENTIAL
 *
 * Copyright 2002 - 2015 Pentaho Corporation (Pentaho). All rights reserved.
 *
 * NOTICE: All information including source code contained herein is, and
 * remains the sole property of Pentaho and its licensors. The intellectual
 * and technical concepts contained herein are proprietary and confidential
 * to, and are trade secrets of Pentaho and may be covered by U.S. and foreign
 * patents, or patents in process, and are protected by trade secret and
 * copyright laws. The receipt or possession of this source code and/or related
 * information does not convey or imply any rights to reproduce, disclose or
 * distribute its contents, or to manufacture, use, or sell anything that it
 * may describe, in whole or in part. Any reproduction, modification, distribution,
 * or public display of this information without the express written authorization
 * from Pentaho is strictly prohibited and in violation of applicable laws and
 * international treaties. Access to the source code contained herein is strictly
 * prohibited to anyone except those individuals and entities who have executed
 * confidentiality and non-disclosure agreements or other agreements with Pentaho,
 * explicitly covering such access.
 */

package com.pentaho.metrics.api.field;

import com.pentaho.profiling.api.ProfilingField;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DataSourceFieldTest {

  @Test public void testGetName() {
    Map<String, Object> fieldMap = new HashMap<String, Object>();
    fieldMap.put( DataSourceField.NAME, "test-name" );
    assertEquals( fieldMap.get( DataSourceField.NAME ), new DataSourceField( fieldMap ).getName() );
  }

  @Test public void testGetFieldNull() {
    DataSourceField dataSourceField = new DataSourceField( new HashMap<String, Object>() );
    assertNull( dataSourceField.getMetricManagerForType( "test" ) );
    assertEquals( 0, dataSourceField.getMetricManagers().size() );
  }

  @Test public void testGetFieldProperty() {
    Map<String, Object> fieldMap = new HashMap<String, Object>();
    fieldMap.put( "MyProp", "test-myprop" );
    assertEquals( fieldMap.get( "MyProp" ), new DataSourceField( fieldMap ).getFieldProperty( "MyProp" ) );
  }

  @Test public void testTypeToFieldMap() {
    DataSourceField dataSourceField = new DataSourceField();
    dataSourceField.setName( "name" );
    dataSourceField.getMetricManagerForType( "test", true ).setValue( 1L, "count" );
    DataSourceField dataSourceField1 = new DataSourceField();
    dataSourceField1.setName( "name1" );
    dataSourceField1.getMetricManagerForType( "test", true ).setValue( 1L, "count" );

    List<ProfilingField>
        profilingFieldList =
        Arrays.asList( dataSourceField.getProfilingField(), dataSourceField1.getProfilingField() );

    Map<String, List<DataSourceField>> typeToFieldMap = DataSourceField.typeToFieldMap( profilingFieldList );
    assertEquals( 1, typeToFieldMap.size() );
    List<DataSourceField> dataSourceFields = typeToFieldMap.get( "test" );
    assertEquals( 2, dataSourceFields.size() );
  }
}
