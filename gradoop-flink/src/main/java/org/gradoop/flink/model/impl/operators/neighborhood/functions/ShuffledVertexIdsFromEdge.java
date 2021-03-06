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
package org.gradoop.flink.model.impl.operators.neighborhood.functions;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;
import org.gradoop.common.model.api.entities.Edge;
import org.gradoop.common.model.impl.id.GradoopId;

/**
 * Returns a tuple which contains the source id and target id of an edge and a tuple which contains
 * the target id and the source id of the same edge.
 *
 * @param <E> edge type
 */
public class ShuffledVertexIdsFromEdge<E extends Edge>
  implements FlatMapFunction<E, Tuple2<GradoopId, GradoopId>> {

  /**
   * Reuse tuple to avoid instantiations.
   */
  private Tuple2<GradoopId, GradoopId> reuseTuple;

  /**
   * Constructor which instantiates the reuse tuple.
   */
  public ShuffledVertexIdsFromEdge() {
    reuseTuple = new Tuple2<>();
  }

  @Override
  public void flatMap(E edge, Collector<Tuple2<GradoopId, GradoopId>> collector)
    throws Exception {
    reuseTuple.setFields(edge.getSourceId(), edge.getTargetId());
    collector.collect(reuseTuple);
    reuseTuple.setFields(edge.getTargetId(), edge.getSourceId());
    collector.collect(reuseTuple);
  }
}
