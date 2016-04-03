package com.shagunsodhani.iota.test.utils

import scala.annotation.elidable
import scala.annotation.elidable.ASSERTION

import org.apache.spark.graphx.Graph.graphToGraphOps
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.shagunsodhani.iota.utils.GraphLoader
import com.shagunsodhani.iota.utils.SparkContextUtils

@Test
class TestGraphLoader {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  @Test
  def testGraphLoader(): Unit = {

    logger.debug("Testing if valid graph is created.")
    val sc = SparkContextUtils.getContext()

    val userGraph = GraphLoader.getUserGraph(sc)
    assert(userGraph.numEdges == 7)
    assert(userGraph.numVertices == 15)
  }

}