# fileupload

In order to run this SpringBoot application, one will need at the very least a APPL_ROOT environment pointing to the directory, 
where one's copy of setup.properties is located.

In addition, this repository already contains a copy of said file, which one can modify so as to suit one's needs.

Finally, a HTML file has also been provided, so that one can then open it in a browser and then process a file together with
types of metadata currently supported by this application.

Note:  currently all supported types of metadata are predetermined.

*Execution notes*

1)  At the command line, move into the directory where you have extracted the pom.xml
2)  Enter the following command:  "mvn package".  This command will create a .jar for you in the 'target' subdirectory
3)  Enter the following command, which will launch this Spring Boot application:  "java -jar target/fileupload-1.0-SNAPSHOT.jar".
4)  Once the application is started, open the enclosed upload.html and submit the HTML form (after having filled out the various fields, inclusive of the filename of the file to be uploaded).

    To see that the application has indeed started, you ought to see a line similar to the following, in the console:
    
    2018-01-03 18:46:25.626  INFO 6903 --- [           main] upload.UploadApplication                 : Started UploadApplication in 73.538 seconds (JVM running for 75.6)

