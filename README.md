# mtsearch
springboot REST demo app

##Requirements
Write a server application which supports a search API end-point, the search should be case-insensitive. (Imagine implementing GOOGLE search)

**Input to the API end-point:** 
* would be a string text (e.g. 'superman')
* should be provided as a query string. 

**Output from the API end-point:**

 response should include the following:
  
* Total count of search results

* Time (in milliseconds) it took to search.

* List of all the matching lines.


NOTE: If a line has the search text more than once, the line should be included only once, however the Total count should include all the occurrences.

**Output** 
* in a standard format (XML, JSON) representation is preferable.              
                
* Implementation in your choice of programming language and web servers (tomcat, jetty, etc).
 
* Data source is attached, it's a text file with 30 lines (movie plot from 2006 superman film, source is wikipedia).
                . If you prefer to use a database, that's ok too. Please keep it simple to a single table, treat every line from the text file as a row in the table.
 
**Deliverable** 

* Code (or project) packaged as a .zip file. (other file compressions are fine too). Or reference to a public code repository.

* Detailed instructions for running the application in a server.


## Usage/Build
### build
<root project directory>$ mvn clean install

### RUN
<root project directory>$ mvn spring-boot:run

### Swagger docs
http://localhost:8080/swagger-ui.html

