package com.shagunsodhani.iota.test.utils

import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.shagunsodhani.iota.utils.DataFrameUtility
import com.shagunsodhani.iota.utils.SparkContextUtils

@Test
class TestDataFrameUtility {
  private val logger: Logger = LoggerFactory.getLogger(getClass)


  @Test
  def testDataFrame(): Unit = {
      logger.debug("Testing if valid dataframes are created")
    val sc = SparkContextUtils.getContext()
    val userDF = DataFrameUtility.getUserDataFrame(sc)
    assert(userDF.collect().count(x=>x.getAs[Long]("Id")!=0) == 10)
    
    val questionDF = DataFrameUtility.getQuestionDataFrame(sc)
    assert(questionDF.collect().count(x=>x.getAs[Long]("Id")!=0) == 10)
    
    val answerDF = DataFrameUtility.getAnswerDataFrame(sc)
    assert(answerDF.collect().count(x=>x.getAs[Long]("Id")!=0) == 7)
  }

}

