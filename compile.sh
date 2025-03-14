#!/bin/bash

### Compilation du cours de POO-IHM

SRC_DIR="./src" # À mettre à jour si nécessaire

cd "${SRC_DIR}" || exit 1  # Quitte si le dossier source n'existe pas

pwd

# Trouver tous les fichiers .java et les compiler en une seule commande
find . -name '*.java' > tempo

# Compilation de tous les fichiers en une seule fois
set -x
~/Documents/zulu21.38.21-ca-fx-jdk21.0.5-linux_x64/bin/javac @tempo
{ set +x; } &> /dev/null

rm tempo

# Mise à jour de la configuration
echo "localRootDirectory=file://${SRC_DIR}/" > ./compile.txt

