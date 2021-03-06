package com.shagunsodhani.iota.test.utils

import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.shagunsodhani.iota.utils.DataFrameUtility
import com.shagunsodhani.iota.utils.SparkContextUtils
import com.typesafe.config.ConfigFactory
import java.io.File

@Test
class TestDataFrameUtility {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  @Test
  def testDataFrame(): Unit = {

    logger.debug("Testing if valid dataframes are created")
    val sc = SparkContextUtils.getContext()
    
    val config = ConfigFactory.parseFile(new File("src/main/resources/reference.conf.sample"))
    val userDataPath = config.getString("user.data.path")
    val postDataPath = config.getString("post.data.path")

    val userDF = DataFrameUtility.getUserDataFrame(sc, userDataPath)
    assert(userDF.collect().count(x => x.getAs[Long]("Id") != 0) == 10)

    val questionDF = DataFrameUtility.getQuestionDataFrame(sc, postDataPath)
    assert(questionDF.collect().count(x => x.getAs[Long]("Id") != 0) == 10)

    val answerDF = DataFrameUtility.getAnswerDataFrame(sc, postDataPath)
    assert(answerDF.collect().count(x => x.getAs[Long]("Id") != 0) == 7)

    val userDataParquetPath = userDataPath.split('.')(0)
    userDF.write.parquet(userDataParquetPath)

    val userParquetDF = DataFrameUtility.getUserDataFrame(sc, userDataParquetPath)
    assert(userParquetDF.collect().count(x => x.getAs[Long]("Id") != 0) == 10)

    val questionDataParquetPath = postDataPath.split('.')(0) + "Question"
    questionDF.write.parquet(questionDataParquetPath)

    val questionParquetDF = DataFrameUtility.getQuestionDataFrame(sc, questionDataParquetPath)
    assert(questionParquetDF.collect().count(x => x.getAs[Long]("Id") != 0) == 10)

    val answerDataParquetPath = postDataPath.split('.')(0) + "Answer"
    answerDF.write.parquet(answerDataParquetPath)

    val answerParquetDF = DataFrameUtility.getAnswerDataFrame(sc, answerDataParquetPath)
    assert(answerParquetDF.collect().count(x => x.getAs[Long]("Id") != 0) == 7)
  }

}

