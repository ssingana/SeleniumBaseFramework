
Step 1: Getting the required jars into local maven reporsitory.
---------------------------------------------------------------------------------
Download the jar file WebUiAutomation-1.0.jar and save to your local drive.
open command prompt and change directory to the location jar saved.
Execute the following commands to get necessary details.
mvn install:install-file -Dfile=WebUiAutomation-1.0.jar -DgroupId=com.bosch.web.ui.automation -DartifactId=WebUiAutomation -Dversion=1.0 -Dpackaging=jar

Go to libs folder and run the following command to install the ojdbc7 jar
mvn install:install-file -Dfile=ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc -Dversion=7 -Dpackaging=jar

mvn clean install
All required jars are downloaded to your local maven reporsitory.


Step 2: Adding dependency to your project
---------------------------------------------------------------------------------
Add below dependency to your project pom.xml file.
		<dependency>
			<groupId>com.bosch.web.ui.automation</groupId>
			<artifactId>WebUiAutomation</artifactId>
			<version>1.0</version>
		</dependency>
		
		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc</artifactId>
			<version>7</version>
		</dependency>

Step 3: Adding custom testng listeners to the project.
---------------------------------------------------------------------------------
Add the maven surefire plugin to execute test cases and to configure the custom listeners as below.			
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.20</version>
				<configuration>
					<reportsDirectory>${basedir}/test-output/ExecutionReport_${timestamp}</reportsDirectory>
					<testFailureIgnore>true</testFailureIgnore>
					<properties>
						<property>
							<name>usedefaultlisteners</name>
							<value>false</value>
						</property>
						<property>
							<name>listener</name>
							<value>com.bosch.web.ui.automation.listeners.ExecutionListenerImpl,com.bosch.web.ui.automation.listeners.InvokedMethodListenerImpl,com.bosch.web.ui.automation.listeners.ReporterImpl</value>
						</property>
					</properties>
					<suiteXmlFiles>
						<suiteXmlFiles>testng.xml</suiteXmlFiles>
					</suiteXmlFiles>
				</configuration>
			</plugin>


Step 4: Copy Configurable property files.
---------------------------------------------------------------------------------
Copy the the config folder and paste it in your maven project src/main/resources path.
Edit the properties as per your need.


Step 5: Adding below one line as very first line for all your test methods.
---------------------------------------------------------------------------------
 BrowserHandle browser = new BrowserHandle(DriverManager.getWebDriver());
 write your test statements.
 

Step 6: Running the Testcases.
---------------------------------------------------------------------------------
Add the testng.xml file at the root folder of the project.
Add your test classes to the testng file.
Go to command prompt and navigate the test project root folder.
execute mvn test command.


Step 7: Test results folder.
---------------------------------------------------------------------------------
Go to test-output folder and you will find one folder with ExecutionReport_yyyy_MM_dd_hh_ss format.
The folder contains the index.html file contains report of all test cases.
summary.html is the emailable report with only failed test cases.
