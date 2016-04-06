package com.learn

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by spark on 4/5/16.
  */
object FoldByKeyExample {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(List("Cat","Mouse","Rat","Dog","Spark","rdd"),2)
    val pairedRdd = data.map(x => (x.length,x))
    val foldrdd = pairedRdd.foldByKey("")(_ + _).collect()
    foldrdd.foreach(println)

  }

}
