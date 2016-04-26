package com.learn

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}



/**
  * Created by spark on 4/25/16.
  */
object DataFrameExample {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("AggregateBy Key").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext= new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    val rawrdd = sc.textFile("./././././data/2014-Q1-cabi-trip-history-data.csv")
    val filterRdd = rawrdd.map(x => x.split(",")).filter(x => !x.contains("Duration")).
      map( r => Trip(r(5),convertDur(r(0)),r(2),r(4),r(6)))
    filterRdd.cache()
    val bike_df = filterRdd.toDF()
    bike_df.registerTempTable("bikeshare")
    sqlContext.sql("select count(*) as num , s0,s1 from bikeshare group by s0,s1 order by num desc limit 10").show()
//    bike_df.selectExpr("select count(*) as num , s0,s1 from bikeshare group by s0,s1 order by num desc limit 10").show()
    bike_df.groupBy("reg")





  }
  def convertDur(dur:String): Long ={
    val dur_regex= """(\d+)h\s(\d+)m\s(\d+)s""".r
    val dur_regex(hour , minute , second) = dur
    (hour.toLong * 3600L) + ( minute.toLong * 60L) + second.toLong
  }

  case class Trip ( id:String,dur:Long,s0:String,s1:String,reg:String)


}
