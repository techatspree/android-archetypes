android-archetypes
==================

This projects provides several Maven archetypes for Android. Those archetypes allows to quickly bootstrap a Maven project
to develop an android application.

These artifacts are based on the android-maven-plugin (http://code.google.com/p/maven-android-plugin/). It currently uses the 3.1.1 version.

The android-quickstart archetype
--------------------------------
The quickstart archetype creates a simple android application ready to be deployed on an android device. It's a pretty simple
way to initiate an android project:

    mvn archetype:generate \
      -DarchetypeArtifactId=android-quickstart \
      -DarchetypeGroupId=de.akquinet.android.archetypes \
      -DarchetypeVersion=1.0.8 \
      -DgroupId=your.company \
      -DartifactId=my-android-application

You can also set three optional properties :

* The created 'package' with '-Dpackage=your.company.android'. By default it uses the given groupId.
* The Android emulator's name to use with '-Demulator=my-avd'. If none specified the property <emulator> will be ignored in the pom file.
* The targeted Android platform with '-Dplatform=7'. The Android SDK version will be automatically fetched to fit the corresponding API level. Available API Level are 3, 4, 7, 8, 9, 10 and 14. By default, it uses 10 (android 2.3.3).

Once generated, the application is ready to be built and deployed. Start an android emulator, or plug an Android dev phone,
and launch:

    cd my-android-application
    mvn clean install android:deploy

The application will be built and deployed on the device.

The android-with-test archetype
-------------------------------

This archetype creates a multi-module project containing an android application and a project testing this application
(integration tests using instrumentation).

    mvn archetype:generate \
      -DarchetypeArtifactId=android-with-test \
      -DarchetypeGroupId=de.akquinet.android.archetypes \
      -DarchetypeVersion=1.0.8 \
      -DgroupId=com.foo.bar \
      -DartifactId=my-android-project \
      -Dpackage=com.foo.bar.android

The 'package' value is optional (by default use the groupId). You can also set the targeted Android platform with
'-Dplatform=x'. By default, it uses 10 (android 2.3.3).

Once generated, the application is ready to be built and tested. Start an android emulator, or plug an Android dev phone,
and launch:

    cd my-android-project
    mvn clean install

The application will be built, then the integration-tests will be built and executed on the Android device.

The android-release archetype
--------------------------

This archetype extends `android-with-test` with release management.

    mvn archetype:generate \
      -DarchetypeArtifactId=android-release \
      -DarchetypeGroupId=de.akquinet.android.archetypes \
      -DarchetypeVersion=1.0.8 \
      -DgroupId=com.foo.bar \
      -DartifactId=my-android-project \
      -Dpackage=com.foo.bar.android

The 'package' value is optional (by default use the groupId). You can also set the targeted Android platform with
'-Dplatform=x'. By default, it uses 10 (android 2.3.3).

Once generated, the application is ready to be built and tested. Start an android emulator, or plug an Android dev phone,
and launch:

    cd my-android-project
    mvn clean install

The application will be built, then the integration-tests will be built and executed on the Android device.

When you release your application, it will generate a signed, zipaligned and [ProGuard](http://proguard.sourceforge.net)-processed apk.
You will have to add a profile to your `settings.xml` containing the signing informations:

    <profile>
      <id>android-release</id>
      <properties>
        <sign.keystore>/path/to/keystore</sign.keystore>
        <sign.alias>key alias</sign.alias>
        <sign.storepass>keystore password</sign.storepass>
        <sign.keypass>key password</sign.keypass>
      </properties>
    </profile>

or directly pass those properties through the command line:

    mvn release:prepare
    mvn release:perform -Dsign.keystore=/path/to/keystore \
                        -Dsign.alias=key-alias \
                        -Dsign.storepass=keystore-password \
                        -Dsign.keypass=key-password
    mvn release:clean

The archetype contains a test key store which *MUST NOT BE USED IN PRODUCTION*. However you can use it for testing:

    mvn clean install -Prelease \
    -Dsign.keystore=PATH_OF_THE_PROJECT/my-android-project/test-key.keystore \
    -Dsign.alias=mykey \
    -Dsign.storepass=testtest \
    -Dsign.keypass=testtest

Be aware that Android cannot re-deploy artifacts using a different key, so be sure to undeploy all artifacts before running the
release.

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
