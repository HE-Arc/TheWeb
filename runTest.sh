    
#!/bin/bash

Xvfb :99 &
export local_addr="127.0.0.1:8080"

/Katalon_Studio_Linux_64-5.7.1/katalon -noSplash  -runMode=console -projectPath="$(pwd)/TheWebTest/TheWebTest.prj" -retry=0 -testSuitePath="Test Suites/TS_Card" -executionProfile="default" -browserType="Chrome (headless)"