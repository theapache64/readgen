echo "Downloading main JAR..." &&
wget -q "https://github.com/theapache64/readgen/releases/latest/download/readgen.main.jar" -O "readgen.main.jar" --show-progress &&

echo "Moving files to ~/.readgen" &&

mkdir -p ~/.readgen &&
mv readgen.main.jar ~/.readgen/readgen.main.jar

echo "Installing..." &&
echo "alias readgen='java -jar ~/.readgen/readgen.main.jar'" >> ~/.bashrc &&

echo "Done"