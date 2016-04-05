package com.learn

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by spark on 4/4/16.
  */
object ReduceExample {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val data =   sc.parallelize(List(1,2,3,4,5,6,7,8,9))
    val reducerdd = data.reduce((a,b) => a+b)
    println(reducerdd)

  }

}
