HOW TO COMPILE AND LAUNCH IMAGESTOCHAR PROJECT:

1. go to a terminal
2. get the project root (the project root directory is ImagesToChar)

3.compile
    ```diff
        ! -d means directory - Set the destination directory for class files. If -d is not specified, javac puts each class files in the same directory as the source file from which it was generated.
        ! -cp - Specify where to find user class files and annotation processors

        javac -d target src/java/edu/school21/printer/logic/ConvertBmp.java \
        && javac -d target src/java/edu/school21/printer/app/Parser.java\
        && javac -cp target/ -d target src/java/edu/school21/printer/app/Program.java \
        && cp -r src/resources target  \
        && jar cfm target/images-to-char-printer.jar src/manifest.txt -C target .
    ```

4. launch program
    ```diff
        ! without argumets
        java -jar target/images-to-char-printer.jar

        ! with argumets (make sure that filePath is correct)
        java -jar target/images-to-char-printer.jar --white=. --black=0


        java -jar images-to-chars-printer . jar -- white=RED --black=GREEN
    ```

5. Remove class files
    ```diff
        rm -rf target
    ```