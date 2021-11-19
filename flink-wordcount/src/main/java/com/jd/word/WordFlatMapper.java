package com.jd.word;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple0;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class WordFlatMapper implements FlatMapFunction<String ,Tuple2<String,Integer>>{

    public void flatMap(String value , Collector<Tuple2<String,Integer>> out) throws Exception {
        String[] words = value.split(" ");
        for(String word : words){
            out.collect(new Tuple2<String,Integer>(word,1));
        }
    }
}
