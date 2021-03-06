/*
 * Copyright © 2014 - 2020 Leipzig University (Database Research Group)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradoop.common.model.impl.pojo;

import com.google.common.base.Preconditions;
import org.gradoop.common.model.api.entities.GraphHeadFactory;
import org.gradoop.common.model.impl.id.GradoopId;
import org.gradoop.common.model.impl.properties.Properties;
import org.gradoop.common.util.GradoopConstants;

import java.io.Serializable;

/**
 * Factory for creating EPGM graph head POJOs.
 */
public class EPGMGraphHeadFactory implements GraphHeadFactory<EPGMGraphHead>, Serializable {

  /**
   * serial version uid
   */
  private static final long serialVersionUID = 42L;

  @Override
  public EPGMGraphHead createGraphHead() {
    return initGraphHead(GradoopId.get());
  }

  @Override
  public EPGMGraphHead initGraphHead(final GradoopId id) {
    return initGraphHead(id, GradoopConstants.DEFAULT_GRAPH_LABEL, null);
  }

  @Override
  public EPGMGraphHead createGraphHead(String label) {
    return initGraphHead(GradoopId.get(), label);
  }

  @Override
  public EPGMGraphHead initGraphHead(final GradoopId id, final String label) {
    return initGraphHead(id, label, null);
  }

  @Override
  public EPGMGraphHead createGraphHead(String label, Properties properties) {
    return initGraphHead(GradoopId.get(), label, properties);
  }

  @Override
  public EPGMGraphHead initGraphHead(final GradoopId id, final String label,
    Properties properties) {
    Preconditions.checkNotNull(id, "Identifier was null");
    Preconditions.checkNotNull(label, "Label was null");
    return new EPGMGraphHead(id, label, properties);
  }

  @Override
  public Class<EPGMGraphHead> getType() {
    return EPGMGraphHead.class;
  }
}
