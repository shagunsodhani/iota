package com.shagunsodhani.iota.utils

import scala.reflect.runtime.universe
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import com.shagunsodhani.iota.parser.DataParser
import com.shagunsodhani.iota.schema.User
import com.typesafe.config.ConfigFactory
import com.shagunsodhani.iota.parser.UserParser

object DataFrameUtility {
  
  private val userDataPath = ConfigFactory.load.getString("user.data.path")
  
  def getUserDataFrame(sc: SparkContext, 
      path: String = userDataPath) = {
    val sqlContext = SQLContext.getOrCreate(sc)
    import sqlContext.implicits._
    sc.textFile(path, 2)
      .map(_.trim)
      .filter(UserParser.isValidRow)
      .map(UserParser.parseUser)
      .toDF()
  }
}