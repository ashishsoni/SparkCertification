package com.learn

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by spark on 3/31/16.
  */
object AggregateByKey {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val rating_rdd = sc.textFile("././././ml-1m/ratings.dat")
    println(rating_rdd.count())


  }

}
