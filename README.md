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
$ brew update

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

<details>
<summary>Maven build session</summary>

```bash
 $ cd cafeApplication
 $ mvn clean test package 
[INFO] Scanning for projects... 
[INFO]
[INFO] -----------------< com.thesolutionlab:CafeApplication\>----------------- 
[INFO] Building cafedemo 1.0-SNAPSHOT 
[INFO]
--------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ CafeApplication --- 
[INFO] Deleting
/Users/mhavilah/projects/sydneytrains/experiments/CafeApplication/target
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ CafeApplication --- 
[INFO] Using 'UTF-8' encoding to copy filtered resources. 
[INFO] Copying 0 resource 
[INFO] 
[INFO] ---
maven-compiler-plugin:3.8.0:compile (default-compile) @ CafeApplication
--- 
[INFO] Changes detected - recompiling the module! 
[INFO] Compiling 8 source files to
/Users/mhavilah/projects/sydneytrains/experiments/CafeApplication/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testCompile) @ CafeApplication --- 
[INFO] Changes detected - recompiling the module! 
[INFO] Compiling 3 source files to
/Users/mhavilah/projects/experiments/CafeApplication/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ CafeApplication --- 
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
[INFO] skip non existing resourceDirectory /Users/mhavilah/projects/experiments/CafeApplication/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ CafeApplication ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ CafeApplication ---
[INFO] Skipping execution of surefire because it has already been run for this configuration
[INFO] 
[INFO] --- maven-jar-plugin:3.1.1:jar (default-jar) @ CafeApplication ---
[INFO] Building jar: /Users/mhavilah/projects/experiments/CafeApplication/target/CafeApplication-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-shade-plugin:1.4:shade (default) @ CafeApplication ---
[INFO] Replacing original artifact with shaded artifact.
[INFO] Replacing /Users/mhavilah/projects/experiments/CafeApplication/target/CafeApplication-1.0-SNAPSHOT.jar with /Users/mhavilah/projects/experiments/CafeApplication/target/CafeApplication-1.0-SNAPSHOT-shaded.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.359 s
[INFO] Finished at: 2019-03-01T16:55:07+11:00
[INFO] ------------------------------------------------------------------------
```
</details>
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
$ java -jar target/CafeApplication-1.0-SNAPSHOT.jar  1 Latte 
1 cups of Latte costs: 4.000000
```

```bash
$ java -jar target/CafeApplication-1.0-SNAPSHOT.jar 3 Cappuccino 
3 cups of Cappuccino costs: 12.000000
```

## Mock Testing Notes

In this example the key service being mocked is in CafeService:

```java
public interface CafeService {
   BigDecimal makeBeverage(int numberOfCups, Enum<BeverageType> beverageType);
}
```

This is implemented in CafeServiceImpl and used by the Waiter client's
takeOrder method.

```java
 public BigDecimal takeOrder( Order order ) { 
     ...
     for( OrderItem orderItem : order.getItems()) {
            BigDecimal itemPrice = cafeService.makeBeverage(orderItem.numberOfCups, orderItem.beverageType);

     }
     ...
 }
```

By using mocks we can test the interaction between the Waiter and the
CafeService for several different scenarios. 

### Test Scenarios

For example
- valid "sunny day" scenarios
  - positive amounts of known beverage types
  
- invalid parameter scenarios
  - negative cup amounts
  - unknown beverage types (passed to the CLI)
  - non-numeric cup amounts (passed to the CLI)
  
- business logic exception scenarios
  - out of inventory scenario
  
In the last instance, the Mock should throw an
UnavailableProductException, similar to the CafeServiceImpl production 
version.

Similarly, the negative cup scenario triggers an
**IllegalArgumentException** from the Waiter order validation logic. For
this scenario a mock is not needed as the validation is performed in the
client itself.

### Mocking with Answer APIs

The Mockito Answer API supports a "smart stub" implementation whereby
the mock can dynamically alter the response/answer value - for example
based on the argument values passed in the request method.

This is useful when the response value must return an ID from the
request objects or it should simulate "simple" business rules or
validation.

In the example above, the CafeService can throw an
UnavailableProductException if its current inventory state is depleted
and the order cannot be fulfilled.

Rather than maintaining state in the Mock, we simply place a cap on the
maximum order size to be 10 cups. 

The logic is subtlely different but
allow us to exercise the error handling logic in the Waiter client:


```java
public class WaiterMockTest {
...
 @Test
    public void canMockViaAnAnswer()
    {
        ...
    
        Mockito.when( cafeService.makeBeverage(Mockito.anyInt(), 
                            Mockito.any(BeverageType.class)))
            .then( 
                (invocation) -> {
                int cups = invocation.getArgument(0);
                BeverageType type = invocation.getArgument(1);

                return new BigDecimal( ANOTHER_UNIT_PRICE * cups );
            } );
```

The Mockito logic above is saying:

- when the makeBeverage service is invoked
  - with *any* number of cups and *any* beverage type
-  then return a mock price based on those arguments' values

The Answer API here provides an Invocation object to hold the argments
passed to the mock service.

We use a simple Java 8 lambda function to take the invocation value,
unpack its embedded arguments and return a response value.

Mockito's Answer API takes care of the typecasting etc to return the
appropriate response type. (Actually builds an Answer &lt;BigDecimal
&gt;)

### Mixing Request Argment Matchers

When working with **Objects** (rather than primitives like int) the
Mockito Matchers othen require you to use the **Any** wildcard.

You generally, cannot mix the matcher for one argument that is of a
specific value with a matcher for another argument that is *Any* 
**Object** value.

In the example above we could not have setup a *when* expectation with a
cup amount specifically equal to, say, 5 cups and a wildcard Beverage
matcher:

```java
// Mixing matchers is generally not allowed
 Mockito.when( cafeService.makeBeverage(
                    Mockito.eq(5), 
                    Mockito.any(BeverageType.class)))
            .then()
            ....
````

#### Note
```
Enums are an exception to this rule and the above block of code is actually valid.
If BeveageType was a POJO Model class, then the above would be strictly true
```



### Smart stubs - but how smart

The above discussion raises the question - how "smart" do you make the
Mock ?

Mocks are supposed to be quick to write, and should really be checking
the interaction *behaviour* between a client and its collaborator.

By contrast, true smart stub Test Doubles (eg, simulators) have more
complicated logic and routinely inspect the values of the arguments for
use in their response generation/calculations.


Care should be taken to verify that the Mock behaviour resembles that
described/expected in the interface/API. Excessive use of the Answer API
with dynamic response generation may result in undue maintenance costs
for tests.

