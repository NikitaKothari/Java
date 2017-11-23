def sum[T]( ts: Iterable[T] )( implicit num: Numeric[T] ) = {
         num.toDouble( ts.sum )
} 
val incomingEdges = sc.textFile("graph.txt").map(line=>line.split(",")).map(line => (line(1), line(2).toDouble))

val ans = incomingEdges.groupByKey.mapValues(_.toList).sortByKey().map(x => (x._1, sum(x._2))).repartition(1).saveAsTextFile("result3")