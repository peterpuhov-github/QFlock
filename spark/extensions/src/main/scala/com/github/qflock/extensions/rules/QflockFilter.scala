/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.qflock.extensions.rules

import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, ExpressionSet, PredicateHelper, SubqueryExpression}
import org.apache.spark.sql.catalyst.plans.logical.{Filter => LogicalFilter, LogicalPlan, OrderPreservingUnaryNode, Statistics}
import org.apache.spark.sql.catalyst.plans.logical.statsEstimation.BasicStatsPlanVisitor
import org.apache.spark.sql.catalyst.trees.TreePattern.{FILTER, TreePattern}

class QflockFilter(override val condition: Expression, override val child: LogicalPlan)
  extends LogicalFilter(condition, child) {
  def getArgs: Option[(Expression, LogicalPlan)] =
    Some((condition, child))
  override def stats: Statistics = statsCache.getOrElse {
    statsCache = Option(BasicStatsPlanVisitor.visit(this))
    statsCache.get
  }
}

object QflockFilter {
  def apply(condition: Expression, child: LogicalPlan): QflockFilter = {
    new QflockFilter(condition, child)
  }
  def unapply(f: QflockFilter):
  Option[(Expression, LogicalPlan)] = {
    f.getArgs
  }
}