package com.learn

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by spark on 4/21/16.
  */
object SortByKeyExample {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(Seq(
                      ("math",    55),
                      ("math",    56),
                      ("english", 57),
                      ("english", 58),
                      ("science", 59),
                      ("science", 54)))
    val sortedrdd = rdd.sortByKey(true,2)
    sortedrdd.filterByRange("e","s").foreach(println)
  }

}
