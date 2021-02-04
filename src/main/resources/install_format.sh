echo "Downloading main JAR..." &&
wget -q "https://github.com/{{githubUsername}}/{{projectName}}/releases/latest/download/{{projectName}}.main.jar" -O "{{projectName}}.main.jar" --show-progress &&

echo "Moving files to ~/.{{projectName}}" &&

mkdir -p ~/.{{projectName}} &&
mv {{projectName}}.main.jar ~/.{{projectName}}/{{projectName}}.main.jar

echo "Installing..." &&
echo "alias {{projectName}}='java -jar ~/.{{projectName}}/{{projectName}}.main.jar'" >> ~/.bashrc &&

echo "Done"