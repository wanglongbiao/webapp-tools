

#-*- coding: UTF-8 -*-

import os
import time
import shutil
 
artifactId = raw_input("artifactId: ")

print artifactId

command = 'mvn archetype:generate -DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-webapp  -DgroupId=com.highlander -DartifactId=' + artifactId

os.system(command)

#time.sleep(1)

try:
	os.makedirs('./' + artifactId + '/src/main/java')
	os.makedirs('./' + artifactId + '/src/test/java')
	shutil.copy('./resources/web.xml','./' + artifactId + '/src/main/webapp/WEB-INF')
	shutil.copy('./resources/pom.xml','./' + artifactId)
	
	

except Exception, e:
	print str(Exception)
	print e.message

	

#test = raw_input('wait...')