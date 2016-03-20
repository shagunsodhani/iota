package com.shagunsodhani.iota.test.utils

import scala.annotation.elidable
import scala.annotation.elidable.ASSERTION

import org.junit.Test

import com.shagunsodhani.iota.utils.DataFrameUtility
import com.shagunsodhani.iota.utils.SparkContextUtils

@Test
class TestDataFrameUtility {

  @Test
  def testUserDataFrame(): Unit = {
    val sc = SparkContextUtils.getContext()
    val userDF = DataFrameUtility.getUserDataFrame(sc)
    assert(userDF.collect().count(x=>x.getAs[Long]("Id")!=0) == 10)
    
    val questionDF = DataFrameUtility.getQuestionDataFrame(sc)
    assert(questionDF.collect().count(x=>x.getAs[Long]("Id")!=0) == 10)
    
    val answerDF = DataFrameUtility.getAnswerDataFrame(sc)
    assert(answerDF.collect().count(x=>x.getAs[Long]("Id")!=0) == 7)
  }

}

