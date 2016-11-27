# Downloading Spark and Getting Started

Download Spark recent stable version from [here](http://spark.apache.org/downloads.html).
There are many ways of getting Spark up and running on you machine from comping from source code to downloading 
pre-compiled version. If you have Hadoop cluster running already download Spark with your Hadoop version.
I am going with pre-compiled version of Spark 2.0.2 with Hadoop 2.7. 

```
bash> tar -xvzf spark-2.0.2-bin-hadoop2.7.tgz
bash> ls
R  bin  conf  data  examples  jars  licenses  python  sbin  yarn  LICENSE  NOTICE  README.md  RELEASE
```

# Introduction to Spark's Python and Scala shells
Spark comes with interactive shell which lets you do ad hoc data analysis. Unlike most shells which allows you to 
manipulate data on disk of a single machine, Spark shell allows you to work with data distributed across many machines.

### Scala

```
bash> sh bin/spark-shell
```

```
scala> val lines = sc.textFile("README.md")
lines: org.apache.spark.rdd.RDD[String] = README.md MapPartitionsRDD[1] at textFile at <console>:24

scala> lines.count
res1: Long = 99

scala> lines.first
res2: String = # Apache Spark
```

### Python
```
bash> sh bin/pyspark
```
```
python> lines = sc.textFile("README.md")
python> lines.count()
99
python> lines.first()
u'# Apache Spark'
```

# Spark Core Concepts
At the high level Spark program contains Driver program which launches operations on cluster. This contains main
function of you application and it defines and operates on RDDs. In the above example **spark-shell** is the driver
program. Driver program access the spark through **SparkContext** which represents connection to computing cluster.

```
scala> sc
res4: org.apache.spark.SparkContext = org.apache.spark.SparkContext@4dd752e8
```

### Python
```
python> lines = sc.textFile("README.md")

python> pythonLines = lines.filter(lambda line: "Python" in line)
python> pythonLines.first()
u'high-level APIs in Scala, Java, Python, and R, and an optimized engine that'

# Passing functions to Spark
python> def hasPython(line): return "Python" in line
python> pythonLinesV2 = lines.filter(hasPython)
python> pythonLinesV2.first()
u'high-level APIs in Scala, Java, Python, and R, and an optimized engine that'
```

### Scala
```
scala> val lines = sc.textFile("README.md")
lines: org.apache.spark.rdd.RDD[String] = README.md MapPartitionsRDD[5] at textFile at <console>:24

scala> val pythonLines = lines.filter(line => line.contains("Python"))
pythonLines: org.apache.spark.rdd.RDD[String] = MapPartitionsRDD[7] at filter at <console>:26

scala> pythonLines.first
res8: String = high-level APIs in Scala, Java, Python, and R, and an optimized engine that
```

### Java
```
// Passing functions to Spark in Java like we did in Python
JavaRDD<String> pythonLines = lines.filter(
    new Function<String, Boolean>() {
        Boolean call(String line) { return line.contains("Python"); }
    })
// Another Variant
JavaRDD<String> pythonLines = lines.filter(line -> line.contains("Python"));
```

# Standalone Application
Spark can be linked into standalone applications in either Java, Python or Scala. The only difference when compared to 
running Spark from shell is you need to initialize SparkContext on your own. For Java and Scala you can use Maven or
SBT for building projects using adding Spark dependency. In python you simply write Python scripts but you must run them
using bin/spark-submit. This script sets up environment for Spark's Python API.

## Initializing SparkContext

### Python
```
from pyspark import SparkConf, SparkContext
conf = SparkConf().setMaster("local").setAppName("MyApp")
sc = SparkContext(conf = conf)
lines = sc.textFile("README.md")
```

### Scala
```
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

val conf = new SparkConf().setMaster("local").setAppName("My App")
val sc = new SparkContext(conf)
```

### Java
```
import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaSparkContext

SparkConf conf = new SparkConf().setMaster("local").setAppName("My App");
JavaSparkContext sc = new JavaSparkContext(conf);
```

## Building Standalone Applications
See the word count example in Scala and Java in **venkatesh1729.lys.chapter2**.
Since we are using SBT, go to project directory then do :
```
bash> sbt clean package

bash> $SPARK_HOME/bin/spark-submit \
--class venkatesh1729.lys.chapter2.WordCount \
./target/scala-2.11/learn-you-spark_2.11-1.0.jar \
./build.sbt ./wordcounts

bash> $SPARK_HOME/bin/spark-submit \
--class venkatesh1729.lys.chapter2.WordCountJava \
./target/scala-2.11/learn-you-spark_2.11-1.0.jar \
./build.sbt ./wordcounts
```
