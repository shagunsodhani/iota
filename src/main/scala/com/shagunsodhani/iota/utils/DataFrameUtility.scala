package com.shagunsodhani.iota.utils

import scala.reflect.runtime.universe

import org.apache.spark.SparkContext
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SQLContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.shagunsodhani.iota.parser.PostParser
import com.shagunsodhani.iota.parser.UserParser
import com.typesafe.config.ConfigFactory

object DataFrameUtility {

  private val logger: Logger = LoggerFactory.getLogger(getClass)
  private val XML = "xml"
  private val _userDataPath = ConfigFactory.load.getString("user.data.path")
  private val _postDataPath = ConfigFactory.load.getString("post.data.path")

  def getUserDataFrame(sc: SparkContext,
                       userDataPath: String = _userDataPath): DataFrame = {
    logger.debug("Creating User DataFrame")
    val sqlContext = SQLContext.getOrCreate(sc)
    import sqlContext.implicits._
    if (userDataPath.toLowerCase().endsWith(XML)) {
      sc.textFile(userDataPath, 2)
        .map(_.trim)
        .filter(UserParser.isValidRow)
        .map(UserParser.parseUser)
        .toDF()
    } else {
      sqlContext.read.parquet(userDataPath)
    }
  }

  def getQuestionDataFrame(sc: SparkContext,
                           postDataPath: String = _postDataPath): DataFrame = {
    logger.debug("Creating Question DataFrame")
    val sqlContext = SQLContext.getOrCreate(sc)
    import sqlContext.implicits._
    if (postDataPath.toLowerCase().endsWith(XML)) {
      sc.textFile(postDataPath, 2)
        .map(_.trim)
        .filter(PostParser.isValidRow)
        .filter(PostParser.isQuestion)
        .map(PostParser.parseQuestion)
        .toDF()
    } else {
      sqlContext.read.parquet(postDataPath)
    }
  }

  def getAnswerDataFrame(sc: SparkContext,
                         postDataPath: String = _postDataPath): DataFrame = {
    logger.debug("Creating Answer DataFrame")
    val sqlContext = SQLContext.getOrCreate(sc)
    import sqlContext.implicits._
    if (postDataPath.toLowerCase().endsWith(XML)) {
      sc.textFile(postDataPath, 2)
        .map(_.trim)
        .filter(PostParser.isValidRow)
        .filter(PostParser.isAnswer)
        .map(PostParser.parseAnswer)
        .toDF()
    } else {
      sqlContext.read.parquet(postDataPath)
    }
  }

}