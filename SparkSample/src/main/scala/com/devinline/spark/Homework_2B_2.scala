def average[T]( ts: Iterable[T] )( implicit num: Numeric[T] ) = {
         num.toDouble( ts.sum ) / ts.size
} 

val business = sc.textFile("business.csv").map(line=>line.split("::")).map(line => (line(0), (line(1), line(2))))

val review = sc.textFile("review.csv").map(line=>line.split("::")).map(line => (line(2), line(3).toDouble)).groupByKey.mapValues(_.toList).map(x => (x._1, average(x._2)))

val ans = review.join(business).sortBy(line => line._2._1, false).distinct().take(10).map(line=>(line._1, line._2._2))

val rdd = sc.makeRDD(ans).repartition(1).saveAsTextFile("result2")

