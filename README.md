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
3. Run `val userDF = DataFrameUtility.getUserDataFrame(sc, PATH_TO_USER_FILE)` to load UserDataFrame. Other avilable functions are `getQuestionDataFrame(sc, PATH_TO_POST_FILE)` and `getUserDataFrame(sc, PATH_TO_POST_FILE)`

For now only user and post schema are mapped. Other data (Badges, PostHistory etc.) can be easily mapped.
