NAME="Snake"
DESCRIPTION="A snake game"
VENDOR="Maxime DEROISSART"
VERSION="1.0"
OUTPUT=out
# Build maven libraries path, then replace '\' by '/' (Windows to Unix path convention)
MAVEN_LOCAL_REPOSITORY_LOCATION=$(shell mvn help:evaluate -Dexpression=settings.localRepository -q -DforceStdout | tr '\\\\' '/')
JAVA_FX_LIBRARY_PATH=$(MAVEN_LOCAL_REPOSITORY_LOCATION)/org/openjfx

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
	--module-path $(JAVA_FX_LIBRARY_PATH) \
	--main-jar Snake.jar \
	--runtime-image target/image \
	--win-shortcut
