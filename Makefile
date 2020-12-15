NAME="Snake"
DESCRIPTION="A snake game"
VENDOR="Maxime DEROISSART"
VERSION="1.0"
OUTPUT=out

all: executable

clean:
	mvn clean ; rm -rf $(OUTPUT)

jar:
	mvn compile assembly:single

runtime-image:
	mvn javafx:jlink

executable: jar runtime-image
	jpackage --type exe \
	--name $(NAME) \
	--description $(DESCRIPTION) \
	--vendor $(VENDOR) \
	--app-version $(VERSION) \
	--input target \
	--dest $(OUTPUT) \
	--module-path  "lib" \
	--main-jar Snake.jar \
	--runtime-image target/image \
	--win-shortcut
