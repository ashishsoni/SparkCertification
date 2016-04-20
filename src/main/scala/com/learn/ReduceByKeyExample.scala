package com.learn

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by spark on 4/20/16.
  */
object ReduceByKeyExample {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
     val sales=sc.parallelize(List(
      ("West",  "Apple",  2.0, 10),
      ("West",  "Apple",  3.0, 15),
      ("West",  "Orange", 5.0, 15),
      ("South", "Orange", 3.0, 9),
      ("South", "Orange", 6.0, 18),
      ("East",  "Milk",   5.0, 5)))
    val reducerdd = sales.map{case(store,prod,amt,unit) => ((store,prod),(amt))}.reduceByKey( ( x,y) => x+y)
    reducerdd.collect.foreach(println)

  }
}
