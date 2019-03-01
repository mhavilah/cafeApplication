# Cafe Application
### Mock testing with Mockito 2.x

----

## Intro
 
This repository demonstrates the basics of Mocking a Service in Java.




## How to build with Maven

### Prerequisites

Download Maven 3.6.0 and Java 1.8.x JDK to compile the project

On OSX
```bash
$ brew install maven

$ brew cask install java
```
See also:
[https://discourse.brew.sh/t/how-to-install-openjdk-with-brew/712/5]

Clone the Git repository

```bash
$ git clone https://github.com/mhavilah/cafeApplication.git
```


Then, invoking Maven from the project folder (containing the pom.xml):

```bash
 $ cd cafeApplication
 $ mvn clean test package [INFO] Scanning for projects... 
[INFO]
[INFO] -----------------< com.thesolutionlab:CafeApplication\>----------------- 
[INFO] Building cafedemo 1.0-SNAPSHOT 
[INFO]
--------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @
CafeApplication --- 
[INFO] Deleting
/Users/mhavilah/projects/sydneytrains/experiments/CafeApplication/target
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources
(default-resources) @ CafeApplication --- 
[INFO] Using 'UTF-8' encoding
to copy filtered resources. 
[INFO] Copying 0 resource 
[INFO] 
[INFO] ---
maven-compiler-plugin:3.8.0:compile (default-compile) @ CafeApplication
--- 
[INFO] Changes detected - recompiling the module! 
[INFO] Compiling 8
source files to
/Users/mhavilah/projects/sydneytrains/experiments/CafeApplication/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources
(default-testCompile) @ CafeApplication --- 
[INFO] Changes detected - recompiling the module! 
[INFO] Compiling 3 source files to
/Users/mhavilah/projects/sydneytrains/experiments/CafeApplication/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @
CafeApplication --- 
[INFO] 
[INFO]
------------------------------------------------------- 
[INFO] T E S T S
[INFO] ------------------------------------------------------- 
[INFO]
Running com.thesolutionlab.WaiterMockTest 
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.332 s - in
com.thesolutionlab.WaiterMockTest 
[INFO] Running com.thesolutionlab.WaiterTest 
[INFO] Tests run: 2, Failures: 0, Errors:
0, Skipped: 0, Time elapsed: 0 s - in com.thesolutionlab.WaiterTest
[INFO] Running com.thesolutionlab.CafeAppTest 
CLI Output: Unknown beverage or invalid arguments:[] [] 
Usage: CafeApp [numberOfCups] [Beverage type] 
CafeApp 1 Latte 
CafeApp 3 Capp 
Possible beverages:Latte:Mocha:Cappuccino:

CLI Output:
Unknown beverage or invalid arguments:[1] [Coke]
Usage:
CafeApp [numberOfCups] [Beverage type]
CafeApp 1 Latte
CafeApp 3 Capp
Possible beverages:  Latte:Mocha:Cappuccino:

CLI Output:
Unknown beverage or invalid arguments:[1] []
Usage:
CafeApp [numberOfCups] [Beverage type]
CafeApp 1 Latte
CafeApp 3 Capp
Possible beverages:  Latte:Mocha:Cappuccino:

CLI Output:
Unknown beverage or invalid arguments:[A] [Latte]
Usage:
CafeApp [numberOfCups] [Beverage type]
CafeApp 1 Latte
CafeApp 3 Capp
Possible beverages:  Latte:Mocha:Cappuccino:

CLI Output:
1 cups of Latte costs: 4.000000

[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 s - in com.thesolutionlab.CafeAppTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ CafeApplication ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ CafeApplication ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ CafeApplication ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/mhavilah/projects/sydneytrains/experiments/CafeApplication/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ CafeApplication ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ CafeApplication ---
[INFO] Skipping execution of surefire because it has already been run for this configuration
[INFO] 
[INFO] --- maven-jar-plugin:3.1.1:jar (default-jar) @ CafeApplication ---
[INFO] Building jar: /Users/mhavilah/projects/sydneytrains/experiments/CafeApplication/target/CafeApplication-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-shade-plugin:1.4:shade (default) @ CafeApplication ---
[INFO] Replacing original artifact with shaded artifact.
[INFO] Replacing /Users/mhavilah/projects/sydneytrains/experiments/CafeApplication/target/CafeApplication-1.0-SNAPSHOT.jar with /Users/mhavilah/projects/sydneytrains/experiments/CafeApplication/target/CafeApplication-1.0-SNAPSHOT-shaded.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.359 s
[INFO] Finished at: 2019-03-01T16:55:07+11:00
[INFO] ------------------------------------------------------------------------
```

The build will create an application JAR under:
target/CafeApplication-1.0-SNAPSHOT.jar

### How to run the CLI

Just run the JAR to see usage:

```bash
 $ java -jar target/CafeApplication-1.0-SNAPSHOT.jar 
Unknown beverage or invalid arguments:[] [] 
Usage: CafeApp [numberOfCups] [Beverage type]
CafeApp 1 Latte 
CafeApp 3 Cappuccino 
Possible beverages: Latte:Mocha:Cappuccino:
```

Then pass in the number of cups/beverage type:

```bash
$ java -jar target/CafeApplication-1.0-SNAPSHOT.jar  
1 Latte 1 cups of Latte costs: 4.000000
```

```bash
$ java -jar target/CafeApplication-1.0-SNAPSHOT.jar 3 Cappuccino 
3 cups of Cappuccino costs: 12.000000
```

