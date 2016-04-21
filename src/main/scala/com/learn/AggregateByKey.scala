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
//    sets.collect.foreach(println)

    //Aggregate by Key with Log message
    val dataRDD = sc.parallelize(List(("a",1),("a",1),("b",1),("b",1)),3)
    dataRDD.aggregateByKey(0)((acc,value) =>{
      println("Accum: "+ acc)
      println("Value: "+ value)
      acc+value }, (part1result,part2result) => {
      println("part1result: " + part1result)
      println("part2result: " + part2result)
      part1result+part2result
    }).foreach(println)



  }

}
