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
        javac -d target src/java/edu/school21/printer/logic/ConvertBmp.java \
        && javac -cp target/ -d target src/java/edu/school21/printer/app/Program.java \
        && cp -r src/resources target  \
        && jar cfm target/images-to-char-printer.jar src/manifest.txt -C target .
    ```

    && javac -d target src/java/edu/school21/printer/app/Parser.java\

4. launch program
    ```diff
        ! without argumets
        java -jar target/images-to-char-printer.jar

        ! with argumets (make sure that filePath is correct)
        java -jar target/images-to-char-printer.jar --white=GREEN --black=BLUE
    ```

5. Remove class files
    ```diff
        rm -rf lib target
    ```
