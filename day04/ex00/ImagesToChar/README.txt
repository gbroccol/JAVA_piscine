HOW TO COMPILE AND LAUNCH IMAGESTOCHAR PROJECT:

1. go to a terminal
2. get the project root (the project root directory is ImagesToChar)

3. Download a file "it.bmp" from project page and save it in project root directory or any other place

4.compile
    ```diff
        ! -d means directory - Set the destination directory for class files. If -d is not specified, javac puts each class files in the same directory as the source file from which it was generated.
        ! -cp - Specify where to find user class files and annotation processors

        javac -d target src/java/edu/school21/printer/logic/ConvertBmp.java \
        && javac -d target src/java/edu/school21/printer/app/Parser.java\
        && javac -cp target/ -d target src/java/edu/school21/printer/app/Program.java
    ```

5. launch program
    ```diff
        ! without arguments
        java -cp target edu.school21.printer.app.Program

        ! wrong arguments
        java -cp target edu.school21.printer.app.Program --white= --black=0 --filePath=./it.bmp
        java -cp target edu.school21.printer.app.Program --white= --black= --filePath=./it.bmp
        java -cp target edu.school21.printer.app.Program --white=. --black= --filePath=./it.bmp
        java -cp target edu.school21.printer.app.Program --white=. --black= --filePath=
        java -cp target edu.school21.printer.app.Program --white=. --black=0 --filePath=
        java -cp target edu.school21.printer.app.Program --white= --black= --filePath=

        ! with directory
        java -cp target edu.school21.printer.app.Program --white=. --black=0 --filePath=./src/java/edu/school21/printer/app/

        ! wrong path
        java -cp target edu.school21.printer.app.Program --white=. --black=0 --filePath=./src/java/edu/school21/wrong

        ! with arguments (make sure that filePath is correct)
        java -cp target edu.school21.printer.app.Program --white=. --black=0 --filePath=./it.bmp
    ```

6. Remove class files
    ```diff
        rm -rf target
    ```