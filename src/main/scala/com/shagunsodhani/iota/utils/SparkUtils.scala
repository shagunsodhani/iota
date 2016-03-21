package com.shagunsodhani.iota.utils

import java.net.InetAddress

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.typesafe.config.ConfigFactory

object SparkContextUtils {

  private val Config = ConfigFactory.load
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  private val FILE_SEP = System.getProperty("file.separator")
  private val LOCAL_IP_ADDRESS = InetAddress.getLocalHost().getHostAddress()
  private val SPARK_DRIVER_HOST = "spark.driver.host"
  private val SPARK_EXECUTOR_MEMORY = "spark.executor.memory"
  private val SPARK_CORES_MAX = "spark.cores.max"
  private val CLUSTER_SPARK_MASTER = "cluster.spark.master"
  private val APP_NAME = "app.name"

  def getContext() = {

    logger.debug("Creating SparkContext")

    val conf = new SparkConf()
    conf.setAppName(Config.getString(APP_NAME))
      .setMaster(Config.getString(CLUSTER_SPARK_MASTER))
      .set(SPARK_DRIVER_HOST, LOCAL_IP_ADDRESS)
      .set(SPARK_EXECUTOR_MEMORY, Config.getString(SPARK_EXECUTOR_MEMORY))
      .set(SPARK_CORES_MAX, Config.getString(SPARK_CORES_MAX))

    SparkContext.getOrCreate(conf)
  }

}
