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

package com.pentaho.model.metrics.contributor.metricManager.impl;

import com.pentaho.profiling.api.metrics.MetricMergeException;
import com.pentaho.profiling.api.metrics.NVL;
import com.pentaho.profiling.api.metrics.field.DataSourceFieldValue;
import com.pentaho.profiling.api.metrics.field.DataSourceMetricManager;
import com.pentaho.profiling.api.metrics.MetricManagerContributor;
import com.pentaho.model.metrics.contributor.metricManager.NVLOperations;
import com.pentaho.profiling.api.ProfileFieldProperty;
import com.pentaho.profiling.api.action.ProfileActionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mhall on 27/01/15.
 */
public class DateMetricContributor implements MetricManagerContributor {
  public static final List<String[]> CLEAR_LIST =
    new ArrayList<String[]>( Arrays.asList( NumericMetricContributor.MIN_PATH, NumericMetricContributor.MAX_PATH ) );
  private final NVL nvl;

  public DateMetricContributor() {
    this( new NVL() );
  }

  public DateMetricContributor( NVL nvl ) {
    this.nvl = nvl;
  }

  @Override public Set<String> getTypes() {
    return new HashSet<String>( Arrays.asList( Date.class.getCanonicalName() ) );
  }

  @Override
  public void process( DataSourceMetricManager dataSourceMetricManager, DataSourceFieldValue dataSourceFieldValue )
    throws ProfileActionException {
    Date value = (Date) dataSourceFieldValue.getFieldValue();
    nvl.performAndSet( NVLOperations.DATE_MIN, dataSourceMetricManager, value, NumericMetricContributor.MIN_PATH );
    nvl.performAndSet( NVLOperations.DATE_MAX, dataSourceMetricManager, value, NumericMetricContributor.MAX_PATH );
  }

  @Override public void merge( DataSourceMetricManager into, DataSourceMetricManager from )
    throws MetricMergeException {
    nvl.performAndSet( NVLOperations.DATE_MIN, into, from, NumericMetricContributor.MIN_PATH );
    nvl.performAndSet( NVLOperations.DATE_MAX, into, from, NumericMetricContributor.MAX_PATH );
  }

  @Override public void clear( DataSourceMetricManager dataSourceMetricManager ) {
    dataSourceMetricManager.clear( CLEAR_LIST );
  }

  @Override public List<ProfileFieldProperty> getProfileFieldProperties() {
    return Arrays.asList( NumericMetricContributor.MIN, NumericMetricContributor.MAX );
  }
}
