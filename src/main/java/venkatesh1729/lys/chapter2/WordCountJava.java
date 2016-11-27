package venkatesh1729.lys.chapter2;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;

public class WordCountJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Word Coun Java");
        JavaSparkContext sc = new JavaSparkContext(conf);
        if (args.length != 2) {
            System.out.print("Input and output files need to be given as argument");
            System.exit(0);
        }

        String inputFile = args[0];
        String outputFile = args[1];

        JavaRDD<String> lines = sc.textFile(inputFile);
        JavaRDD<String> words = lines.flatMap((FlatMapFunction<String, String>) s ->
                Arrays.asList(s.split(" ")).iterator());
        JavaPairRDD<String, Integer> counts = words.mapToPair((PairFunction<String, String, Integer>) s ->
                new Tuple2<>(s, 1)).reduceByKey((Function2<Integer, Integer, Integer>) (v1, v2) -> v1 + v2);

        counts.saveAsTextFile(outputFile);
    }
}
