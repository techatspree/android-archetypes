android-archetypes
==================

This projects provides several Maven archetypes for Android. Those archetypes allows to quickly bootstrap a Maven project
to develop an android application.

These artifacts are based on the android-maven-plugin (http://code.google.com/p/maven-android-plugin/). It currently uses the 3.5.3 version.

The android-quickstart archetype
--------------------------------
The quickstart archetype creates a simple android application ready to be deployed on an android device. It's a pretty simple
way to initiate an android project:

    mvn archetype:generate \
      -DarchetypeArtifactId=android-quickstart \
      -DarchetypeGroupId=de.akquinet.android.archetypes \
      -DarchetypeVersion=1.0.9 \
      -DgroupId=your.company \
      -DartifactId=my-android-application

You can also set three optional properties :

* The created 'package' with `-Dpackage=your.company.android`. By default it uses the given groupId.
* The Android emulator's name to use with `-Demulator=my-avd`. If none specified the property <emulator> will be ignored in the pom file.
* The targeted Android platform with `-Dplatform=X`. The Android SDK version will be automatically fetched to fit the corresponding API level. Available API Level are 3, 4, 7, 8, 9, 10, 14 and 16. By default, it uses 16 (android 4.1.1.4).

Once generated, the application is ready to be built and deployed (you may need to configure your `ANDROID_HOME`environment variable to point to your Android SDK). Start an android emulator, or plug an Android dev phone,
and launch:

    cd my-android-application
    mvn clean install android:deploy android:run

The application will be built, deployed and launched on the device.

The android-with-test archetype
-------------------------------

This archetype creates a multi-module project containing an android application and a project testing this application
(integration tests using instrumentation).

    mvn archetype:generate \
      -DarchetypeArtifactId=android-with-test \
      -DarchetypeGroupId=de.akquinet.android.archetypes \
      -DarchetypeVersion=1.0.9 \
      -DgroupId=com.foo.bar \
      -DartifactId=my-android-project \
      -Dpackage=com.foo.bar.android

You can also set three optional properties :

* The created 'package' with `-Dpackage=your.company.android`. By default it uses the given groupId.
* The Android emulator's name to use with `-Demulator=my-avd`. If none specified the property <emulator> will be ignored in the pom file.
* The targeted Android platform with `-Dplatform=X`. The Android SDK version will be automatically fetched to fit the corresponding API level. Available API Level are 3, 4, 7, 8, 9, 10, 14 and 16. By default, it uses 16 (android 4.1.1.4).

Once generated, the application is ready to be built and tested. Start an android emulator, or plug an Android dev phone,
and launch:

    cd my-android-project
    mvn clean install

The application will be built, then the integration-tests will be built and executed on the Android device.
If you whish to launch just the application:

	cd my-android-project
	mvn clean install android:deploy android:run

The android-library-quickstart archetype
----------------------------------------
The library quickstart archetype creates a simple Android library ready to be used with another Android application. It's a pretty simple
way to initiate an android project:

    mvn archetype:generate \
      -DarchetypeArtifactId=android-library-quickstart \
      -DarchetypeGroupId=de.akquinet.android.archetypes \
      -DarchetypeVersion=1.0.9 \
      -DgroupId=your.company \
      -DartifactId=my-android-application

You can also set three optional properties :

* The created 'package' with `-Dpackage=your.company.android`. By default it uses the given groupId.
* The targeted Android platform with `-Dplatform=7`. The Android SDK version will be automatically fetched to fit the corresponding API level. Available API Level are 3, 4, 7, 8, 9, 10 and 14. By default, it uses 10 (android 2.3.3).

Once generated, the library is ready to be built:

    cd my-android-application
    mvn clean install
The android-release archetype
----------------------------

This archetype extends `android-with-test` with release management.

    mvn archetype:generate \
      -DarchetypeArtifactId=android-release \
      -DarchetypeGroupId=de.akquinet.android.archetypes \
      -DarchetypeVersion=1.0.9 \
      -DgroupId=com.foo.bar \
      -DartifactId=my-android-project \
      -Dpackage=com.foo.bar.android

You can also set three optional properties :

* The created 'package' with `-Dpackage=your.company.android`. By default it uses the given groupId.
* The Android emulator's name to use with `-Demulator=my-avd`. If none specified the property <emulator> will be ignored in the pom file.
* The targeted Android platform with `-Dplatform=X`. The Android SDK version will be automatically fetched to fit the corresponding API level. Available API Level are 3, 4, 7, 8, 9, 10, 14 and 16. By default, it uses 16 (android 4.1.1.4).

Once generated, the application is ready to be built and tested. Start an Android emulator, or plug an Android dev phone,
and launch:

    cd my-android-project
    mvn clean install

The application will be built, then the integration-tests will be built and executed on the Android device.

If you whish to launch just the application:

	cd my-android-project
	mvn clean install android:deploy android:run

By default the app is built in "debug mode". This means  `BuildCongif.DEBUG` is `true` and `android:debuggable="true"` and the apk is signed with the debug key (`~/.android/debug.keystore`).

When you release your application, it will generate a signed, zipaligned and [ProGuard](http://proguard.sourceforge.net)-processed apk.
You will have to add a profile to your `~/.m2/settings.xml` file containing the signing informations:

    <profile>
      <id>android-release</id>
      <properties>
        <sign.keystore>/path/to/keystore</sign.keystore>
        <sign.alias>key alias</sign.alias>
        <sign.storepass>keystore password</sign.storepass>
        <sign.keypass>key password</sign.keypass>
      </properties>
    </profile>

At this point you can generate a signed apk using the Maven release plugin:

    mvn release:prepare
    mvn release:perform -DreleaseProfiles=android release,release
    mvn release:clean

Or, if you wish to generate a signed apk without performing the whole release process:

	mvn clean deploy -Pandroid-release,release

Be aware that Android cannot re-deploy artifacts using a different key, so be sure to undeploy all artifacts before running the
release.

The android-gcm-quickstart archetype
------------------------------------
The android-gcm-quickstart creates a simple Google Cloud Messaging application.

    mvn archetype:generate \
      -DarchetypeArtifactId=android-gcm-quickstart \
      -DarchetypeGroupId=de.akquinet.android.archetypes \
      -DarchetypeVersion=1.0.9 \
      -DgroupId=your.company \
      -DartifactId=my-android-application
      -DsenderId=my-sender-id

The 'senderId' value is found in the [Google APIs console](https://code.google.com/apis/console).
You can also set one optional property :
* The URL of your GCM server, with `-DgcmReceiverUrl=http://my-gcm-server`. By default it uses the local test server provided by the gcmutils-maven-plugin.

Generated files includes the assets/gcmutils.properties configuration file, containing GCM specific values.
Once generated, the library is ready to be built with:

    cd my-android-application
    mvn clean install

Before starting the test server add the API Key configuration. This can be added in the user settings file, `~/.m2/settings.xml`, by system property `-DapiKey=` or in the pom.xml (not recommended).
When the key is added, you can start the GCM test server:

    mvn gcmutils:run-server

optionally:

    mvn gcmutils:run-server -DapiKey=my-api-key

By default the server is available at http://localhost:9595

The gcmutils-maven-plugin provides three additional configuration options:
* apiKey - Must be included, but is not recommended to be added in pom.xml
* port - The port number of the test server. Default: 9595
* contextRoot - The context root of the test server. Default: "/"

Detailed information on the gcmutils-maven-plugin is available [here](https://code.google.com/p/gcmutils/wiki/MavenPlugin).
For more information on GCM and how to obtain the senderId and API Key, see [GCM: Getting Started](http://developer.android.com/google/gcm/gs.html).


Setting the maven-android-plugin version
----------------------------------------
You can change the default maven-android-plugin version by specifying the 'android-plugin-version' parameter.

Credits
-------
The android-archetype-project is an open source project licensed under the Apache License 2.0.
It is founded by [akquinet](http://akquinet.de/en)

Technical Notes
---------------
* As the archetypes used the new archetype format, they don't work with the deprecated goal 'archetype:create'
