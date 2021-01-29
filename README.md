Coverage: 53.7%
## IMS 
A barebones CI IMS. A learning exercise in Java to aid and demonstrate key concepts of Java within a more complex system. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Requires MySQL to be installed as the IMS connects to a SQL server to host its database. MySQL tools can be downloaded [here!](https://dev.mysql.com/downloads/file/?id=501137) You'll need to download Maven and Java
perform unit tests, which can be gotten [here](https://www.oracle.com/java/technologies/javase-downloads.html) and [here.](https://maven.apache.org/download.cgi)

![Image of the MySQL download page. Red arrow points to the "No thanks, just start my download"](https://cdn.discordapp.com/attachments/761214563660070922/804544672084394074/unknown.png)

When downloading servers make the password "root".

![Download apache_maven-3.6.3-bin.zip](https://cdn.discordapp.com/attachments/761214563660070922/804720073138896916/unknown.png)

Check out [this guide](https://www.javatpoint.com/how-to-install-maven) to get Maven running. https://www.javatpoint.com/how-to-install-maven

### Installing
This README won't cover installation for MySQL, however the default installation should be suitable for the purposes of using this IMS. The Github folder should contain everything you need to run
the IMS as it is thus no installation should be required. 

This program must be executed from the command line. I would run the command line as an admin. This can be done in one of two ways. Skip to **"Running the Program"** to skip running command line instructions.

## 1. One off instances
This won't change default settings. Go to your search field in your OS.

![Windows10 "Type here to search" program](https://cdn.discordapp.com/attachments/761214563660070922/804547527080018010/unknown.png)

Type in "Command" into the search field.

!(https://cdn.discordapp.com/attachments/761214563660070922/804547805896376340/unknown.png)

Right click on the command prompt (or whatever your version of Windows calls the command line) and select "Run as administrator".

!(https://cdn.discordapp.com/attachments/761214563660070922/804548276724301845/unknown.png)

End with an example of getting some data out of the system or using it for a little demo

## 2. Run command line as admin for all instances.
Everytime you run command line it'll be ran as an admin with the benefits that entails. Follow the previous method up to right the right click. Instead of clicking "Run as administrator",
click on "open file location"

!(https://cdn.discordapp.com/attachments/761214563660070922/804549922841296916/unknown.png)

This will take you to the shortcut location. Right click on "Command Prompt" (or your Window's version) shortcut in the Windows Explorer and select "Properties"

![Right click on Command Prompt. Properties is usually at the bottom. Arrow points to this](https://cdn.discordapp.com/attachments/761214563660070922/804550363570896916/unknown.png)

Go to the "Shortcut" tab and then to "Advanced button".

!(https://cdn.discordapp.com/attachments/761214563660070922/804550692215062558/unknown.png)

You should see the option to "Run as administrator". Check this box if it's not already. Click "OK" then "Apply" to make the changes go into effect.

https://cdn.discordapp.com/attachments/761214563660070922/804551313370906664/unknown.png

A quick way to open command line is "Ctrl + r" and then type in "cmd".

### Running the Program
Open your command line. Change the directory to where ever you have the program saved. To change directory use "cd " followed by the directory of the program.

!(https://cdn.discordapp.com/attachments/761214563660070922/804552261233672192/unknown.png)

From here you will need to locate the jar file. By default this is in "\target"

!(https://cdn.discordapp.com/attachments/761214563660070922/804552957923819580/unknown.png)

You'll need to execute the following command:  
```
java -jar ims-0.0.1-jar-with-dependencies.jar
```

!(https://cdn.discordapp.com/attachments/761214563660070922/804724401283006464/unknown.png)

If everything has gone as intended you should be welcomed by the start menu. The IMS will also create the needed databases in your MySQL.

![Demonstration of getting customer information from the system](https://cdn.discordapp.com/attachments/761214563660070922/804724557013188628/unknown.png)

### Unit Tests 

Unit tests confirm whether the a method in a class works as intended. These tests use JUnit and thus require mvn to use despite the program providing a fatJar. The domain tests confirm that each unique is
to itself and only itself. The DAO tests check confirm whether the connections to the database work as intended and the database returns the expecteded data. Unfortunately no controller tests were performed, which
of checked when the correct input is entered the proper changes in the system were made.

```
    @Test
    public void testReadAll() {
        List<Item> expected = new ArrayList<>();
        expected.add(new Item(1L, "jordan", 50));
        assertEquals("Expected: Outputs an item called "Jordan" with a price of £50", expected, DAO.readAll());
    }
```
The test above creates an new arrayList that only holds elements that belong to the class "Item". It then adds a new element to the table. The assertEquals statement checks to see whether the left and the
right sides of the second comma match. If they do the test is successful, otherwise the test fails.

## Performing the prewritten tests
To run the tests yourself you'll need to have Maven installed. From there you'll need to open command line and change directory to where the folder is stored (see Installing). From there run
```
mvn clean package
```

## Deployment

By default the IMS will create its own tables with database. These are generated with in the src/main/resources directory with "sql-schema.sql" and "sql-data.sql".
sql-schema.sql can be opened in MySQL workbench to edit these. Currently data must be entered one at a time and there are no plans to allow mass entry or import.
To change the database location in terms of server go to "db.properties" and change "localhost:3306/ims" as needed. Here you can also change the username and password.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management and executable builder
* [JUnit]() - Unit testing the application

## Written With
* [Eclipse](https://www.eclipse.org/ide/) - IDE the IMS was built with

## Project Board
* [Jira](https://ekiddqa.atlassian.net/secure/BrowseProjects.jspa) - Used for Agile Scrum sprint management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Chris Perrins
* Nick Johnson
* J. Harry
* Ian Kidd
* Pawel Stypulkowski
* Everyone at Team Serpent
* Melike 
* Inspiration
* etc
