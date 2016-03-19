package com.shagunsodhani.iota.test.utils

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.junit.Test
import com.shagunsodhani.iota.utils.SparkContextUtils

@Test
class TestSparkContexUtils {

  @Test
  def testSparkContext(): Unit = {
    val sc: SparkContext = SparkContextUtils.getContext()
    assert(sc.parallelize(List(1, 2)).count() == 2)

  }

}

