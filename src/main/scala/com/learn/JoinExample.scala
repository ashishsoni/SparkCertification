package com.learn

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by spark on 4/21/16.
  */
object JoinExample {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(Seq(
      ("math",    55),

      ("english", 57),

      ("science", 59)
      ))
    val rdd2= sc.parallelize(Seq(



      ("english", 58),

      ("science", 54)))
    rdd1.join(rdd2).collect().foreach(println)
    rdd1.leftOuterJoin(rdd2).collect().foreach(println)
    rdd1.rightOuterJoin(rdd2).collect().foreach(println)
  }


}
