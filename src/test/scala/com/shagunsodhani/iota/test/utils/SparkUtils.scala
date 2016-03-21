package com.shagunsodhani.iota.test.utils

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.junit.Test
import com.shagunsodhani.iota.utils.SparkContextUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Test
class TestSparkContexUtils {
  
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  @Test
  def testSparkContext(): Unit = {
    logger.debug("Testing if valid sparkcontext is created")
    val sc: SparkContext = SparkContextUtils.getContext()
    assert(sc.parallelize(List(1, 2)).count() == 2)

  }

}

