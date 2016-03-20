package com.shagunsodhani.iota.utils

import scala.reflect.runtime.universe
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import com.shagunsodhani.iota.parser.DataParser
import com.shagunsodhani.iota.schema.User
import com.typesafe.config.ConfigFactory

object DataFrameUtility {
  
  val userDataPath = ConfigFactory.load.getString("user.data.path")
  
  def getUserDataFrame(sc: SparkContext, 
      path: String = userDataPath) = {
    val sqlContext = SQLContext.getOrCreate(sc)
    import sqlContext.implicits._
    sc.textFile(path, 2)
      .map(_.trim)
      .filter(DataParser.isValidRow)
      .map(DataParser.parseUser)
      //     Some users have blank accountid. This condition takes care of that.
      .filter(!_.last.isEmpty())
      .map {
        user =>
          try {
            User(user(0).toLong, user(1).toLong, user(2), user(3).toLong, 
                user(4).toLong, user(5).toLong, user(6).toLong)
          } catch {
            case ex: Exception ⇒
              println(s"failed to parse line: $user")
              User(0, 0, "", 0, 0, 0, 0)
//              All erroneous records are replaced by this placeholder record. 
//              Can be filtered later as required by the application logic.
          }
      }.toDF()

  }
}