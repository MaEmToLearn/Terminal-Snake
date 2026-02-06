# Terminal Snake

### How to setup and play (for IntelliJ users)
1. Download repository or clone it with git or IntelliJ
2. Open project in IntelliJ
3. Make sure you see the Maven symbol in the sidebar (if not View -> Tool Windows -> Maven)
4. Click the symbol to open the Maven menu
5. Click Sync (leftmost button) to resolve dependencies (or Reload from the dropdown, if it complains about Maven plugins)
6. Click Execute Maven Goal (button towards the middle with a terminal icon)
7. In the floating window that opens, enter `mvn clean package assembly:single` and run it
8. Find `Snake-1.0-SNAPSHOT-jar-with-dependencies.jar` in the newly created `target` folder
9. Open your system terminal either directly in that folder or `cd` into it
10. Run `java --enable-native-access=ALL-UNNAMED -jar Snake-1.0-SNAPSHOT-jar-with-dependencies.jar` (if you don't have `java` in your PATH, you have to prefix the path to the executable)

### How to play only
Download the [latest release](https://github.com/MaEmToLearn/Terminal-Snake/releases/tag/1.0) and do the above mentioned step 10 whereever you saved the file.

***

Tested on Windows 10 and Fedora 43
