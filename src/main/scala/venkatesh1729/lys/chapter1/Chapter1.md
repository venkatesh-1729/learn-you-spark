# Introduction to Data Analysis with Spark

Apache Spark is **fast** and **general purpose** cluster computing platform.
It extends MapReduce model efficiently to support complex computations, interactive queries and stream processing.
On the speed side Spark allows computation to run on memory.
It is also more efficient than MapReduce for applications running on disk.
On the generality side Spark is designed to handle various workloads such as batch, stream processing, interactive
queries and iterative algorithms. This reduces burden of managing various applications for each task.
Spark integrates nicely with other Big Data tools. It can run on Hadoop cluster and can read any hadoop data source 
including cassandra.

At its core Spark is a **computing engine** that is responsible for scheduling, distributing and monitoring applications
consisting of many computational tasks across many worker machines or a **computing cluster**. Since Spark core is 
general purpose and fast thus it can support multiple higher-level components like SQL or machine learning. 
These are designed to inter-operate like libraries in a software project. The philosophy of tight integration has many 
benefits. All libraries and higher-level components benefit from optimization to lower layers like Spark core. The cost
associated with running the stack are minimized because instead of maintaining and running many applications your
organization needs to run only one. Every time a new component is added to Spark everyone can try that new feature
seamlessly, all that it takes is upgrading your Spark. The biggest advantage of tight integration is ability to
build application that seamlessly combine different processing models.

# Spark Stack

	------------------------------------
	SQL | Streaming | MLib | GraphX
	------------------------------------
				Spark Core
	------------------------------------
	YARN | Mesos | Standalong Scheduler
	------------------------------------

## Spark Core
Spark core contains the basic functionality of Spark including task scheduling, memory management, fault recovery and 
interaction with storage systems. Spark core is also home to **RDD**s(Resilient Distributed Datasets) which are Spark's
main programming abstraction. RDDs represent collection of data distributed across compute nodes.

## Spark SQL
Spark SQL is Spark's package for working with structured data. It allow querying data via SQL/HQL. It supports many data 
sources like Hive tables, Parquet and JSON.

## Spark Streaming
Spark Streaming is a Spark component that enables live processing of streams of data. Streaming has an API similar to 
RDDs in Spark core to work with Data Streams. It is designed to provide same degree of fault tolerance, throughput and 
scalability.  

## MLib
Spark comes with library containing Machine Learning functionality called MLib. MLib has many machine learning 
algorithms and are designed to scale out across a cluster.

## GraphX
GraphX is a library for manipulating graph data and performing graph parallel computations. Like Spark Streaming and 
Spark SQL it extends RDDs API to work with graphs. It provides various operators for graphs and comes with library of 
graph algorithms.


## Cluster Managers
Under the hood spark is designed to scale across thousands of compute nodes. Spark can run over variety of cluster
managers like YARN and Mesos.