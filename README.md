# Pencil Durability Kata
This is designed as a CLI -- you run the program with command line arguments which give commands like "write" and "sharpen". State is currently just saved off to flat files in whatever directory the app is run.

Pencil durabiltiy defaults to 100. Length defaults to 10. If you need, you can edit either of these in `pencil.dat`, which should be created after running a command. 

### To build: 
- Clone this repository
- CD into the project directory and, in a console, enter `./gradlew build` for mac/linux, or `.\gradlew build` for pc
   - This runs tests, too. To view more detailed output, see below.

### To run
- CD into the `build/libs` directory
- The basic command to run is `java -jar pencil-kata.jar`
   - This shows the help text. Read that to see the other available commands
   - Have fun!

### To see test output in the console:
- Enter `./gradlew test -i` for mac/lunix, or `.\gradlew test -i` for pc
