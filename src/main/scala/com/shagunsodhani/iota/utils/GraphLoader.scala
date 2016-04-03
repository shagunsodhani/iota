package com.shagunsodhani.iota.utils

import org.apache.spark.SparkContext
import org.apache.spark.graphx.Edge
import org.apache.spark.graphx.Graph
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.typesafe.config.ConfigFactory

object GraphLoader {
  private val logger: Logger = LoggerFactory.getLogger(getClass)
  private val _userDataPath = ConfigFactory.load.getString("user.data.path")
  private val _postDataPath = ConfigFactory.load.getString("post.data.path")
  private val _questionDataPath = {
    if (ConfigFactory.load.getString("question.data.path") == "") {
      _postDataPath
    } else {
      ConfigFactory.load.getString("question.data.path")
    }
  }

  private val _answerDataPath = {
    if (ConfigFactory.load.getString("answer.data.path") == "") {
      _postDataPath
    } else {
      ConfigFactory.load.getString("answer.data.path")
    }
  }

  def getUserGraph(sc: SparkContext,
                   userDataPath: String = _userDataPath,
                   questionDataPath: String = _questionDataPath,
                   answerDataPath: String = _answerDataPath): Graph[String, Long] = {
    logger.debug("Creating User Graph")
    val sqlContext = SQLContext.getOrCreate(sc)
    import sqlContext.implicits._
    val questionDF = DataFrameUtility.getQuestionDataFrame(sc, questionDataPath)
      .selectExpr("Id as questionId", "OwnerUserId as questionOwnerUserId", "AcceptedAnswerId as acceptedAnswerId")
    val answerDF = DataFrameUtility.getAnswerDataFrame(sc, answerDataPath)
      .selectExpr("Id as answerId", "OwnerUserId as answerOwnerUserId", "ParentId as parentQuestionId")
    val joinedDF = answerDF.join(questionDF, answerDF("parentQuestionId") === questionDF("questionId"), "inner")
    val userRDD = DataFrameUtility.getUserDataFrame(sc, userDataPath)
      .select("Id", "DisplayName")
      .rdd
      .map { x => (x.getAs[Long](0), x.getAs[String](1)) }
    val edgeRDD: RDD[Edge[Long]] = joinedDF.select("questionOwnerUserId", "answerOwnerUserId")
      .rdd
      .map { x => Edge(x.getAs[Long](0), x.getAs[Long](1)) }
    Graph(userRDD, edgeRDD)
  }
}