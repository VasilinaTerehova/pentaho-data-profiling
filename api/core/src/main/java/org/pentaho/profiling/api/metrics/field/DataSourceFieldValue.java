/*******************************************************************************
 *
 * Pentaho Data Profiling
 *
 * Copyright (C) 2002-2015 by Pentaho : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.profiling.api.metrics.field;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for encapsulating a field value read from a data source along with arbitrary metadata about it (e.g. its path,
 * whether it's a leaf etc.)
 * <p/>
 * Created by mhall on 23/01/15.
 */
public class DataSourceFieldValue {
  private static final String PHYSICAL_NAME = "physicalName";
  private static final String LOGICAL_NAME = "logicalName";
  /**
   * The actual value
   */
  private Object fieldValue;

  /**
   * String capable of uniquely identifying this field
   */
  private String physicalName;

  /**
   * Human-friendly name
   */
  private String logicalName;

  /**
   * Canonical name of field type
   */
  private String fieldTypeName;
  /**
   * arbitrary data source specific metadata about this value - e.g. path, whether its a leaf etc.
   */
  private Map<String, Object> fieldMetadata = new HashMap<String, Object>();

  /**
   * Constructor for a null-valued DataSourceField
   */
  public DataSourceFieldValue() {
    this( null );
  }

  /**
   * Constructor
   *
   * @param fieldValue the value to encapsulate
   */
  public DataSourceFieldValue( Object fieldValue ) {
    this.fieldValue = fieldValue;
  }

  /**
   * Gets the physical (uniquely identifying) name
   *
   * @return the physical (uniquely identifying) name
   */
  public String getPhysicalName() {
    return physicalName;
  }

  /**
   * Sets the physical (uniquely identifying) name
   *
   * @param physicalName the physical (uniquely identifying) name
   */
  public void setPhysicalName( String physicalName ) {
    this.physicalName = physicalName;
  }

  /**
   * Gets the logical (human friendly) name
   *
   * @return the logical name
   */
  public String getLogicalName() {
    return logicalName;
  }

  /**
   * Sets the logical (human friendly) name
   *
   * @param logicalName the logical name
   */
  public void setLogicalName( String logicalName ) {
    this.logicalName = logicalName;
  }

  /**
   * Get the field value
   *
   * @return the field value
   */
  public Object getFieldValue() {
    return this.fieldValue;
  }

  /**
   * Set the field value
   *
   * @param fieldValue the field value
   */
  public void setFieldValue( Object fieldValue ) {
    this.fieldTypeName = null;
    this.fieldValue = fieldValue;
  }

  /**
   * Set the value of a piece of metadata
   *
   * @param keyName       the name of the metadata
   * @param metadataValue the associated value of the metadata
   * @param <T>           the type of the metadata value
   */
  public <T> void setFieldMetatdata( String keyName, T metadataValue ) {
    fieldMetadata.put( keyName, metadataValue );
  }

  /**
   * Get the value of a piece of metadata
   *
   * @param keyName the name of the metadata to get
   * @param <T>     the type of the metadata
   * @return the value of the metadata
   */
  public <T> T getFieldMetadata( String keyName ) {
    return (T) fieldMetadata.get( keyName );
  }

  /**
   * Clear the metadata map
   */
  public void clearFieldMetadata() {
    fieldMetadata.clear();
  }

  /**
   * Get the number of metadata elements that are currently set
   *
   * @return the number of metadata elements associated with this field value
   */
  public int numMetadataElements() {
    return fieldMetadata.size();
  }

  public String getFieldTypeName() {
    if ( fieldTypeName == null ) {
      fieldTypeName = fieldValue == null ? "null" : fieldValue.getClass().getCanonicalName();
    }
    return fieldTypeName;
  }

  public void setFieldTypeName( String fieldTypeName ) {
    this.fieldTypeName = fieldTypeName;
  }

  //OperatorWrap isn't helpful for autogenerated methods
  //CHECKSTYLE:OperatorWrap:OFF
  @Override public boolean equals( Object o ) {
    if ( this == o ) {
      return true;
    }
    if ( o == null || getClass() != o.getClass() ) {
      return false;
    }

    DataSourceFieldValue that = (DataSourceFieldValue) o;

    if ( fieldValue != null ? !fieldValue.equals( that.fieldValue ) : that.fieldValue != null ) {
      return false;
    }
    if ( physicalName != null ? !physicalName.equals( that.physicalName ) : that.physicalName != null ) {
      return false;
    }
    if ( logicalName != null ? !logicalName.equals( that.logicalName ) : that.logicalName != null ) {
      return false;
    }
    if ( fieldTypeName != null ? !fieldTypeName.equals( that.fieldTypeName ) : that.fieldTypeName != null ) {
      return false;
    }
    return !( fieldMetadata != null ? !fieldMetadata.equals( that.fieldMetadata ) : that.fieldMetadata != null );

  }

  @Override public int hashCode() {
    int result = fieldValue != null ? fieldValue.hashCode() : 0;
    result = 31 * result + ( physicalName != null ? physicalName.hashCode() : 0 );
    result = 31 * result + ( logicalName != null ? logicalName.hashCode() : 0 );
    result = 31 * result + ( fieldTypeName != null ? fieldTypeName.hashCode() : 0 );
    result = 31 * result + ( fieldMetadata != null ? fieldMetadata.hashCode() : 0 );
    return result;
  }

  @Override public String toString() {
    return "DataSourceFieldValue{" +
      "fieldValue=" + fieldValue +
      ", physicalName='" + physicalName + '\'' +
      ", logicalName='" + logicalName + '\'' +
      ", fieldTypeName='" + fieldTypeName + '\'' +
      ", fieldMetadata=" + fieldMetadata +
      '}';
  }
  //CHECKSTYLE:OperatorWrap:ON
}