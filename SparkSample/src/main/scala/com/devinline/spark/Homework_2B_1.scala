val business = sc.textFile("business.csv").filter(line => line.contains("Stanford")).map(line=>line.split("::")).map(line => (line(0), (line(1), line(2))))

val review = sc.textFile("review.csv").map(line=>line.split("::")).map(line => (line(2), (line(1), line(3))))

val ans = review.join(business).sortBy(line => line._2._1._2, false).map(line=>(line._2._1)).repartition(1).saveAsTextFile("result1")


