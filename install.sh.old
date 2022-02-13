echo "Downloading main JAR..." &&
wget -q "https://github.com/theapache64/readgen/releases/latest/download/readgen.main.jar" -O "readgen.main.jar" --show-progress &&

echo "Moving files to ~/.readgen" &&

mkdir -p ~/.readgen &&
mv readgen.main.jar ~/.readgen/readgen.main.jar

echo "Installing..." &&
echo "\nalias readgen='java -jar ~/.readgen/readgen.main.jar'" >> ~/.bash_aliases &&

echo "Done"