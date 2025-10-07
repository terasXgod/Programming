javac -d bin -cp "Pokemon.jar;src" src/*.java src/pokemons/*.java src/abilities/*.java

jar cfm lab2.jar manifest.mf -C bin .