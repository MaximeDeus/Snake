NAME="Snake"
DESCRIPTION="A snake game"
VENDOR="Maxime DEROISSART"
VERSION="1.0"
OUTPUT="out"

all: executable

clean:
	mvn clean ; rm -rf $(OUTPUT)

runtime:
	mvn javafx:jlink

executable: runtime
	jpackage --type exe \
	--name $(NAME) \
	--description $(DESCRIPTION) \
	--vendor $(VENDOR) \
	--app-version $(VERSION) \
	--input target \
	--dest $(OUTPUT) \
	--module-path  "lib" \
	--main-jar Snake_jar/Snake.jar \
	--runtime-image target/image \
	--win-shortcut
