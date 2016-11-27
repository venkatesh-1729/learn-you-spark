package venkatesh1729.lys.chapter2

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object WordCount extends App {

  val conf: SparkConf = new SparkConf().setAppName("Word Count")
  val sc: SparkContext = new SparkContext(conf)

  if (args.length != 2) {
    println("Input and output files need to be given as argument.")
    System.exit(0)
  }

  val inputFile: String = args(0)
  val outputFile: String = args(1)

  val lines: RDD[String] = sc.textFile(inputFile)
  val words: RDD[String] = lines.flatMap(line => line.split(" "))
  val counts: RDD[(String, Int)] = words.map(word => (word, 1)).reduceByKey({ case (x, y) => x + y })

  counts.saveAsTextFile(outputFile)

}
