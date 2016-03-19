package main.scala.utils

import java.net.InetAddress

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import com.typesafe.config.ConfigFactory

object SparkContextUtils {

  val Config = ConfigFactory.load

  val FILE_SEP = System.getProperty("file.separator")
  val LOCAL_IP_ADDRESS = InetAddress.getLocalHost().getHostAddress()
  val SPARK_DRIVER_HOST = "spark.driver.host"
  val SPARK_EXECUTOR_MEMORY = "spark.executor.memory"
  val SPARK_CORES_MAX = "spark.cores.max"
  val CLUSTER_SPARK_MASTER = "cluster.spark.master"
  val APP_NAME = "app.name"

  def getContext() = {

    val conf = new SparkConf()
    conf.setAppName(Config.getString(APP_NAME))
      .setMaster(Config.getString(CLUSTER_SPARK_MASTER))
      .set(SPARK_DRIVER_HOST, LOCAL_IP_ADDRESS)
      .set(SPARK_EXECUTOR_MEMORY, Config.getString(SPARK_EXECUTOR_MEMORY))
      .set(SPARK_CORES_MAX, Config.getString(SPARK_CORES_MAX))

    new SparkContext(conf)
  }

}
