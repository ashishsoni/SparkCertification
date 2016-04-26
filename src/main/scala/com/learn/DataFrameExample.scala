package com.learn

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by spark on 4/25/16.
  */
object DataFrameExample {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val rawrdd = sc.textFile("./././././data/2014-Q1-cabi-trip-history-data.csv")
    val filterRdd = rawrdd.filter(x => !x.contains("Duration")).map(x => x.split(","))
  }

}
