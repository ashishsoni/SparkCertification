package com.learn

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by spark on 4/1/16.
  */
object CombineByKeyAvgRating {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val rating_rdd = sc.textFile("././././ml-1m/ratings.dat")
    val split_rdd = rating_rdd.map(x => x.split("::"))
    val pair_rdd = split_rdd.map(x => (x(0).toInt,x(2).toInt))
    val reduced = pair_rdd.combineByKey(
      (value) => (value,1),
      (acc:(Int,Int),val1) => (acc._1 +val1 ,acc._2+1),
      (acc1:(Int,Int),acc2:(Int,Int)) => (acc1._1+acc2._1,acc2._2+acc2._2)
    )


  }



}
