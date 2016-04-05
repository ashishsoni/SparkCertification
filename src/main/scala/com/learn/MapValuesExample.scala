package com.learn

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by spark on 4/1/16.
  */
object MapValuesExample {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val data =   sc.parallelize(Seq((1 ,"Ashish"),(2,"Neelu"),(3,"Ansh")))
    data.mapValues(x => println(x)).collect()


  }


}
