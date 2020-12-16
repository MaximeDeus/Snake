NAME="Snake"
DESCRIPTION="A snake game"
VENDOR="Maxime DEROISSART"
VERSION="1.0"
OUTPUT=out
# Build maven libraries path, then replace '\' by '/' (Windows to Unix path convention)
MAVEN_LOCAL_REPOSITORY_LOCATION=$(shell mvn help:evaluate -Dexpression=settings.localRepository -q -DforceStdout | tr '\\' '/')
JAVA_FX_LIBRARY_PATH=$(MAVEN_LOCAL_REPOSITORY_LOCATION)/org/openjfx
JAVA_FX_SDK_VERSION=11.0.2
#'$/' are used here for splitting in multiple lines
MODULE_PATH="$(JAVA_FX_LIBRARY_PATH)/javafx-base/$(JAVA_FX_SDK_VERSION);$\
$(JAVA_FX_LIBRARY_PATH)/javafx-graphics/$(JAVA_FX_SDK_VERSION);$\
$(JAVA_FX_LIBRARY_PATH)/javafx-fxml/$(JAVA_FX_SDK_VERSION);$\
$(JAVA_FX_LIBRARY_PATH)/javafx-controls/$(JAVA_FX_SDK_VERSION)"

all: executable

clean:
	mvn clean ; rm -rf $(OUTPUT)

jar:
	mvn compile assembly:single

run_with_jar: jar
	java --module-path $(MODULE_PATH) --add-modules javafx.controls,javafx.fxml -jar target/Snake.jar

runtime-image:
	mvn javafx:jlink

run_with_runtime_image: runtime-image
	./target/image/bin/java -m com.app/com.app.App #[main class opt] [module][main class ref])

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
