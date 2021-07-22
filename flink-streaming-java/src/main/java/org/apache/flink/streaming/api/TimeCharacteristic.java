/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.streaming.api;

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;

/**
 * 时间特性定义了系统如何为依赖时间的订单和依赖时间的操作确定时间
 * 在flink版本1.12中已弃用
 */
@PublicEvolving
@Deprecated
public enum TimeCharacteristic {

    /**
     *  处理时间指消息被计算引擎处理的时间
     *  例如，在物理节点1处理时，处理时间（即当前系统时间）为2019-02-05 12∶00∶00，然后交给下游的计算节点进程处理，
     *  此时的处理时间（即当前系统时间）为2019-02-05 12∶00∶01。可以看到处理时间是在不停变化的。
     *  使用处理时间依赖于操作系统的时钟，重复执行基于窗口的统计作业，结果可能是不同的。
     *  处理时间的计算逻辑非常简单，性能好于事件时间，延迟低于事件时间，只需要获取当前系统的时间戳即可。
     *
     *  */
     ProcessingTime,
    /**
     * 摄取时间指事件进入流处理系统的时间，对于与一个事件来说，使用其被读取的那一刻的时间戳作作为摄取时间。
     * 摄取时间一般使用得较少，从处理机制上来说，其类似于事件时间，在作业异常重启执行的时候，也无法避免使用处理时间的结果不准确的问题。
     * 一般来说，若在数据记录中没有记录时间，又想使用事件时间机制来处理记录，会选择使用摄取时间。
     */
    IngestionTime,

    /**
     * 事件时间指事件发生时的时间，一旦确定之后再也不会改变。
     * 例如，事件被记录在日志文件中，日志中记录的时间戳就是事件时间。
     * 通过事件时间能够还原出来事件发生的顺序。使用事件时间的好处是不依赖操作系统的时钟，无论执行多少次，
     * 可以保证计算结果是一样的，但计算逻辑稍微复杂，需要从每一条记录中提取时间戳。
     */
    EventTime
}
