# Introduction #

You should have the following in your settings.xml:
```
<settings>
  <mirrors>
    <mirror>
      <id>archiva.default.mirror</id>
      <name>Archiva Repository</name>
      <!-- This URL is the archiva repo URL. -->
      <url>http://localhost:8080/archiva/repository/internal</url>
      <mirrorOf>*</mirrorOf>
    </mirror>		
  </mirrors>
  <servers>
	<server>
		<id>archiva.internal</id>
		<username>someuseruser</username>
		<password>somepassword</password>
	</server>
  </servers>
  
  <profiles>
	<profile>
		<id>release</id>
		<properties>
			<maven.test.skip>true</maven.test.skip>
		</properties>
	</profile>
  </profiles>
</settings>
```

Then, issue the following command from the root of the project:
```
mvn clean deploy -DrepositoryId=archiva.internal
```