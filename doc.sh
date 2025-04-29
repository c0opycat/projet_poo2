#!/bin/bash

# filepath: /home/arthur/Documents/S6/projet_poo2/generate_javadoc.sh

# Chemin vers les sources et le dossier de sortie
SOURCE_PATH="./src"
OUTPUT_DIR="doc"
LIB_DIR="./lib"

# Construire le classpath en incluant tous les fichiers JAR du dossier lib
CLASSPATH=$(find "$LIB_DIR" -name "*.jar" | tr '\n' ':')

# Générer la Javadoc
~/Documents/zulu21.38.21-ca-fx-jdk21.0.5-linux_x64/bin/javadoc -d "$OUTPUT_DIR" -sourcepath "$SOURCE_PATH" -subpackages view:model:controller -classpath "$CLASSPATH"
#a modifié celon votre path

# Message de confirmation
echo "La documentation Javadoc a été générée dans le dossier '$OUTPUT_DIR'."
