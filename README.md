
## HICX Assesment Simple File Parser

The solution is based on a Spring Boot App console App.  Using Java platform 14  and IntelliJ community as IDE
___
### Brief description of interfaces, classes and subclasses


###### HicxSimpleFileParserApplication 
Spring-Boot entrypoint and Spring config class

###### SimpleFileParserApplicationConfig
Spring context confing class

###### DirectoryWatcher
Interface, two methods (or messages) one for watching new files copied or created in a given dir and the other to stop the watching a dir.

###### DirectoryWatcherImpl
Implementation of DirectoryWatcher. Uses a WatchService of NIO to poll a directory the FS in order to get changes, in this case only file creation event.

###### FileParser
Interface what defines two methods one for parse a file and other for asking if the parser supports the parsing of a file based in its extension. The best opmtimun solution would have ben to check the file header or using any MIME utility checker etc but for simplicity this I've chosen this alternative.

###### FileParserImpl
Abstract class, implementation of FileParser with the only porpouse to having as members a TokenCounter (a counter) and a generic List of TokenParsers (words, a word, periods, commas). I could go to straight the concrete class but I wanted subclasses have those members. 

###### TextFileParser
Implements the ParseFile, using Scanner of NIO API. The open scanner object is sent to an instance of TokenParser which receives also a TokenCounter instance.

###### PdFileParser
Dummy implementation to show extensibility

###### XlsFileParser
Dummy implementation to show extensibility

###### TokenCounter
Interface defining methods for counting tokens, and getting the count of all, a given one or the most token frequently ocurring token. 

###### TokenParser
One method interface, the method get the tokens from a Scanner a set of delimiters. Also receives a TokenCounter count and TokenStatsResult to fill the stats.

###### Class DotParser
Implements TokenParser only parsing and counting dots in a text.

###### Class WordParser
Implements TokenParser parsing words, counting and getting the most frequently ocurring word in a text.

###### TokeCounterImpl
Implementation of TokenCounter, uses a HashMap to track de couting of each token.

###### TokenStatsResult
Structure to return results to the console app
___


## Instructions

Open a console extract the zip file, get into extracted the folder 
and run:

`mvn clean install package -U`
`mvn spring-boot:run -D spring-boot.run.arguments="[PATH]"`

where [PATH] can be strings like c:\documents, d:\assessment /home/user1 etc

Inside the extracted folder of the project there are 3 files for testing purpouses.
