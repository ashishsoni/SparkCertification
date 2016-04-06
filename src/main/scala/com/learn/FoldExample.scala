package com.learn

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by spark on 4/4/16.
  */
object FoldExample {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
//    val rdd1 = sc.parallelize(List(1 ,2,3,4,5),5)
//    println(rdd1.fold(10)(_ + _))
    sc.parallelize(1 to 10,5).fold(0){ (acc,element) => println(s"$acc : $element"); acc + 1}

  }


}
