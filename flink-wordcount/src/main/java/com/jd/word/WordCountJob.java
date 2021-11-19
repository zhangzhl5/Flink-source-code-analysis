package com.jd.word;

import org.apache.flink.api.java.DataSet;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Iterator;
import java.util.List;

public class WordCountJob {

    public static void main(String[] args) throws Exception{
        // 创建执行环境
        StreamExecutionEnvironment enviroment = StreamExecutionEnvironment.getExecutionEnvironment();
        // 从文件中读取数据
/*        String inputPath ="D:\\JDWork\\Flink-source-code-analysis\\flink-wordcount\\src\\main\\resources\\hello.txt";
        DataStream<String> inputData = enviroment.readTextFile(inputPath);*/

        DataStream<String> inputData = enviroment.socketTextStream("localhost",7777);
        // 基于
        DataStream<Tuple2<String,Integer>> res = inputData.flatMap(new WordFlatMapper())
                .keyBy(0)
                .sum(1)
                .setParallelism(1);// 将第二个位置上的数据求和
        res.print();
        enviroment.setParallelism(1);
        enviroment.execute();
    }
}
