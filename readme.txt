------------------------------------------------------------
HOW TO RUN THE APPLICATION
------------------------------------------------------

This application can be tested by running the unit tests
under "java/test/src". The "DataAggregatorTest" will
produce a file called "Output.csv" in the root of the
project.

Alternatively you can run the application by opening
a command window and navigating to the root of the
project. Execute the following command in the root
of the project:

java -cp aggregate.jar com.jumoassess.aggregator.Aggregate NetworkLoans.csv

This will produce a file called "Output.csv" in the
root of the project.

------------------------------------------------------------
SETTING UP THE ENVIRONMENT
------------------------------------------------------

This project was created using "Eclipse Neon 3".
In order to correctly setup the project in your IDE should 
fix the java build path libraries by:

* Including JDK 8
* Including JUnit 4
* Including the library under the "dependencies" directory

------------------------------------------------------------
RUNNING THE UNIT TESTS
------------------------------------------------------

All the unit tests can be located under "java/test/src".
Right click the "java/test/src" directory and choose
"Run As" > "JUnit Test".

------------------------------------------------------------
ABOUT THE PROJECT
------------------------------------------------------

This project enables the aggregation of loans based on
the tuple (Network, Product, Month). The resulting file
is called "Output.csv" and contains the aggregated data
with the summed amounts and counts.

-------------------------------------------------------------
ASSUMPTIONS MADE
-------------------------------------------------------

The project was created under the assumption that a given csv 
file will have the columns "Network, Product, Date, Amount".
It was assumed that only the month should be taken into
account and that the year will always be the same per given
csv file. Another assumption made was that there would not
be a limit on memory availability on the system where the
application would be running.

-------------------------------------------------------------
DESIGN DECISIONS
-------------------------------------------------------

This project was created using the Java language. Java was
the language of choice since it is a very popular language
and allows for "write once run anywhere" applications.

A 3rd party library (super-csv.2.4.0.jar) was used to
simplify the process of reading and creating csv files.
Reading and writing a csv file is a very common process
and it would be unnecessary to "reinvent the wheel"
by implementing this functionality. SuperCSV was the
preferred choice since it provides the ability to easily
map the csv file to a POJO, as well as other features.
(See : http://super-csv.github.io/super-csv/index.html)

Attention was given to the way aggregation is implemented
to prioritize performance above memory usage. It is
assumed that memory availability is not limited on the
system that runs the application. The application
represents the MVP, but could easily be changed to 
introduce multithreading (for example) for scaling purposes,
allowing more than 1 file to be processed simultaneously.
