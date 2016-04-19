## iota

[![Build Status](https://travis-ci.org/shagunsodhani/iota.svg?branch=master)](https://travis-ci.org/shagunsodhani/iota)

Load StackExchange Data as Spark Dataframes and run analysis over it.

All the StackExchange sites use a common schema for their data. So a common pipeline (to load data) can be shared across different sites in the StackExchange Ecosystem.


##Setup

1. Download [Spark](http://spark.apache.org/) 1.5.1 version.
2. Setup the `$SPARK_HOME` variable, pointing it to your spark installation.
3. Download [StackExchange Data](https://archive.org/details/stackexchange).
4. Clone this repo `git clone https://github.com/shagunsodhani/iota.git`
5. Run `scripts/setup.sh` and update `reference.conf` in `src/main/resources`
4. Run `mvn clean install`

## Use

1. Run `$SPARK_HOME/bin/spark-shell --jars target/uber-iota-0.0.1-SNAPSHOT.jar`
2. Run `import com.shagunsodhani.iota.utils.DataFrameUtility` in the scala REPL.
3. Run `val userDF = DataFrameUtility.getUserDataFrame(sc, PATH_TO_USER_FILE)` to load UserDataFrame. Other avilable functions are `getQuestionDataFrame(sc, PATH_TO_POST_FILE)` and `getAnswerDataFrame(sc, PATH_TO_POST_FILE)`

For now only user and post schema are mapped. Other data (Badges, PostHistory etc.) can be easily mapped.

## Demo:

Checkout the sample Jupyter notebooks:
* [using Ask Ubuntu Data](notebooks/Exploratory Analysis for AskUbuntu User Data.ipynb).
* [using StackOverflow Data](notebooks/Exploratory Analysis for StackOverflow User Data.ipynb).

## Notes:

* Parsing XML files to load the data is inefficient. An application should use XML files to create dataframes only for the first time and should save the dataframes as Parquet files using the [write.parquet](http://spark.apache.org/docs/1.5.0/api/scala/index.html#org.apache.spark.sql.DataFrameWriter) method. The methods that were used to parse XML files can also be used to read the parquet files by passing the path to paquet file instead of the XML file. Once data is converted into Parquet, it can be directly loaded in PySpark/R. Another way to load the Stack Exchange XML data into these languages would be to implement the `getUserDataFrame` method and other methods as datasources just like [Spark-CSV](https://github.com/databricks/spark-csv) is implemented. While this implementation is not tricky, it will still be less efficient than reading Parquet files.