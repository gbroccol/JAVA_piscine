HOW TO COMPILE AND LAUNCH IMAGESTOCHAR PROJECT:

1. go to a terminal
2. get the project root (the project root directory is ImagesToChar)

3. install JCommander if not exists
    ```diff
        mkdir lib && curl -o lib/JCommander.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.81/jcommander-1.81.jar
    ```

4. install JColor if not exists
    ```diff
        curl -o lib/JColor.jar https://repo1.maven.org/maven2/com/diogonunes/JColor/5.0.1/JColor-5.0.1.jar
    ```

5. setting up external libraries
    ```diff
        mkdir target && cd target \
        && jar xf ../lib/JCommander.jar com \
        && jar xf ../lib/JColor.jar com \
        && cd ..
    ```

6.compile src
    ```diff
        javac -cp target/ -d target -sourcepath src/java src/java/edu/school21/printer/app/Program.java \
        && cp -r src/resources target  \
        && jar cfm target/images-to-char-printer.jar src/manifest.txt -C target .
    ```

4. launch program
    ```diff
        ! without arguments
        java -jar target/images-to-char-printer.jar

        ! wrong arguments
        java -jar target/images-to-char-printer.jar --white=RED --black=
        java -jar target/images-to-char-printer.jar --white= --black=RED
        java -jar target/images-to-char-printer.jar --white= --black=
        java -jar target/images-to-char-printer.jar --white=RED
        java -jar target/images-to-char-printer.jar --black=RED
        java -jar target/images-to-char-printer.jar --white=RED --black=RED  --filePath=./it.bmp

        ! with arguments (make sure that filePath is correct)
        java -jar target/images-to-char-printer.jar --white=RED --black=BLUE
    ```

5. Remove class files
    ```diff
        rm -rf lib target
    ```
