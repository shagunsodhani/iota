package com.shagunsodhani.iota.test.utils

import java.io.File

import org.apache.spark.graphx.Graph.graphToGraphOps
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.shagunsodhani.iota.utils.GraphLoader
import com.shagunsodhani.iota.utils.SparkContextUtils
import com.typesafe.config.ConfigFactory

@Test
class TestGraphLoader {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  @Test
  def testGraphLoader(): Unit = {

    logger.debug("Testing if valid graph is created.")
    val sc = SparkContextUtils.getContext()

    val config = ConfigFactory.parseFile(new File("src/main/resources/reference.conf.sample"))
    val userDataPath = config.getString("user.data.path")
    val postDataPath = config.getString("post.data.path")

    val userGraph = GraphLoader.getUserGraph(sc, userDataPath, postDataPath, postDataPath)
    assert(userGraph.numEdges == 7)
    assert(userGraph.numVertices == 15)
  }

}