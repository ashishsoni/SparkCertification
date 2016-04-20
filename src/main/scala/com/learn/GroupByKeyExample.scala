package com.learn

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by spark on 4/20/16.
  */
object GroupByKeyExample {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val data = sc.parallelize(Seq("a" -> 1,"b" ->2,"c" -> 3,"a" ->4),2)


  }

}
