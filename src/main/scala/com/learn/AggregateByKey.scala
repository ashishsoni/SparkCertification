package com.learn

import org.apache.spark.{SparkContext, SparkConf}

import scala.collection.immutable.HashSet

/**
  * Created by spark on 3/31/16.
  */
object AggregateByKey {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(Array(("a", 3), ("a", 1), ("b", 7), ("a", 5)))
    val sets = data.aggregateByKey(new HashSet[Int])(_+_,_++_)
    sets.collect.foreach(println)


  }

}
