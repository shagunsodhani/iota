package com.shagunsodhani.iota.utils

import scala.reflect.runtime.universe
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import com.shagunsodhani.iota.parser.DataParser
import com.shagunsodhani.iota.schema.User
import com.typesafe.config.ConfigFactory
import com.shagunsodhani.iota.parser.UserParser
import com.shagunsodhani.iota.parser.PostParser
import org.apache.spark.sql.DataFrame

object DataFrameUtility {
  
  private val userDataPath = ConfigFactory.load.getString("user.data.path")
  private val postDataPath = ConfigFactory.load.getString("post.data.path")
  
  def getUserDataFrame(sc: SparkContext, 
      userDataPath: String = userDataPath): DataFrame = {
    val sqlContext = SQLContext.getOrCreate(sc)
    import sqlContext.implicits._
    sc.textFile(userDataPath, 2)
      .map(_.trim)
      .filter(UserParser.isValidRow)
      .map(UserParser.parseUser)
      .toDF()
  }
  
  def getQuestionDataFrame(sc: SparkContext, 
      postDataPath: String = postDataPath): DataFrame = {
    val sqlContext = SQLContext.getOrCreate(sc)
    import sqlContext.implicits._
    sc.textFile(postDataPath, 2)
      .map(_.trim)
      .filter(PostParser.isValidRow)
      .filter(PostParser.isQuestion)
      .map(PostParser.parseQuestion)
      .toDF()
  }
  
    def getAnswerDataFrame(sc: SparkContext, 
      postDataPath: String = postDataPath): DataFrame = {
    val sqlContext = SQLContext.getOrCreate(sc)
    import sqlContext.implicits._
    sc.textFile(postDataPath, 2)
      .map(_.trim)
      .filter(PostParser.isValidRow)
      .filter(PostParser.isAnswer)
      .map(PostParser.parseAnswer)
      .toDF()
  }
    
}