javac -d bin src/com/laba/*.java
echo -e "your files have been compiled to bin/com/laba\n"

jar cfm app.jar manifest.mf -C bin .
echo "jar have been created in a project directory"
