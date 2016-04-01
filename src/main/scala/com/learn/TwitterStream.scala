package com.learn

import com.google.gson.Gson
import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by spark on 2/18/16.
  */
object TwitterStream {
  private var gson = new Gson()

  def main(args: Array[String]) {
//    val checkpointDir = Helper.getCheckpointDirectory()
    if (args.length < 1) {
      System.err.println("Usage: No Master specified")
      System.exit(1)
    }

    // Configure Twitter credentials
    val apiKey = "ZVNmNPqWJGpfqlPir3aXEEvAH"
    val apiSecret = "vA34z1GonsVfexnkHwQ8GBuCQq9CTQ6lSzTW5ZuQiIKRISQX77"
    val accessToken = "14545862-BRr8knDsumOMDQzpm3yq7rWJeAvgCXP0mcOnkrbe3"
    val accessTokenSecret = "H0OPQfj7ZQolNxONT45xgnZjBUM84oysUoL9TXI2xzb0L"
    Helper.configureTwitterCredentials(apiKey, apiSecret, accessToken, accessTokenSecret)
    val ssc = new StreamingContext(new SparkConf().setMaster(args(0)).setAppName("tweetApp"), Seconds(1))
    val tweets = TwitterUtils.createStream(ssc, None)
//    val statuses = tweets.map(gson.toJson(_))
//    statuses.print()
    val statuses = tweets.map(status => status.getText())
    val words = statuses.flatMap(status => status.split(" "))
    val hashtags = words.filter(word => word.startsWith("#"))
    val counts = hashtags.map(tag => (tag, 1))
      .reduceByKeyAndWindow(_ + _, _ - _, Seconds(60 * 5), Seconds(1))
    hashtags.print()
    ssc.start();
    ssc.awaitTermination();
  }

}
