# Times-Backend                                                                               ![BoB_Logo](/BOB.png)
## Description:
>  This is the backend to the Times application that integrates the following sub-systems:
>  * Mongodb
>  * Spring
>  * Thymeleaf
>  * iCalendar Parsing System
>  * Employee-Event Scheduling System
>
> Together these make up the backend codebase and logic for the Times application. They enable the application to add and edit events in the database, as well as parse iCalendar information from the client(s) in order to run a scheduling and optimization system on those inputs. In the current iteration the iCalendar event parsing system generates a file that can be fed-in to the scheduler as long as its called independently.
>
>
## Requirements:
>- Version 3.x - Java 8 or later
>- Maven 3.5.1 or later
## Dependencies:
>- Ical4j Version 3.0.18 or later
>- Fasterxml.Jackson.Core Version 2.9.10.4 or later
>- Lombok Version 1.18.12 or later
>- Auth.0 Version 1.0 or later
>- Springframework.security.oauth Version 2.4.1 or later
>
>  In order to set up the application, a user must first ensure that they have access to a machine that has Java version 11 enabled along with an install of Apache Maven version 3.5.1.
Maven Install
>
>  By nature of design, this application is modular and self-containing. This design choice lends itself to less setup requirements for an end user apart from the two core requirements specified above.
In order to install the dependencies using Maven a user then has to run Maven → Clean and Maven → Install commands to acquire all other dependencies before running the project for the first time. Since this application is a Spring Maven enabled program, all other dependencies will be installed during this process.



## Licensing:
>Protected Under Copyright &copy; 2020 Back Office Boiz - Back Office Apps



