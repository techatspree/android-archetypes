android-archetypes
==================

This projects provides several Maven archetypes for Android. Those archetypes allows to quickly bootstrap a Maven project
to develop an android application.

These artifacts are based on the maven-android-plugin (http://code.google.com/p/maven-android-plugin/). It currently uses the 2.8.3 version.

The android-quickstart archetype
--------------------------------
The quickstart archetype creates a simple android application ready to be deployed on an android device. It's a pretty simple
way to initiate an android project:

    mvn archetype:generate \
      -DarchetypeArtifactId=android-quickstart \
      -DarchetypeGroupId=de.akquinet.android.archetypes \
      -DarchetypeVersion=1.0.3 \
      -DgroupId=your.company \
      -DartifactId=my-android-application

You can also set the created 'package' with '-Dpackage=your.company.android'. By default it uses the given groupId.

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
      -DarchetypeVersion=1.0.3 \
      -DgroupId=com.foo.bar \
      -DartifactId=my-android-project \
      -Dpackage=com.foo.bar.android

The 'package' value is optional (by default use the groupId). You can also set the targeted Android platform with
'-Dplatform=x'. By default, it use 7 (android 2.1)

Once generated, the application is ready to be built and tested. Start an android emulator, or plug an Android dev phone,
and launch:

    cd my-android-project
    mvn clean install

The application will be built, then the integration-tests will be built and executed on the Android device.

The android-full archetype
--------------------------

This archetype extends `android-with-test` with release management.

    mvn archetype:generate \
      -DarchetypeArtifactId=android-full \
      -DarchetypeGroupId=de.akquinet.android.archetypes \
      -DarchetypeVersion=1.0.3 \
      -DgroupId=com.foo.bar \
      -DartifactId=my-android-project \
      -Dpackage=com.foo.bar.android

The 'package' value is optional (by default use the groupId). You can also set the targeted Android platform with
'-Dplatform=x'. By default, it use 7 (android 2.1)

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
        <android.sign.keystore>/path/to/keystore</android.sign.keystore>
        <android.sign.alias>key alias</android.sign.alias>
        <android.sign.storepass>keystore password</android.sign.storepass>
        <android.sign.keypass>key password</android.sign.keypass>
      </properties>
    </profile>

or directly pass those properties through the command line:

    mvn release:prepare
    mvn release:perform -Dandroid.sign.keystore=/path/to/keystore \
                        -Dandroid.sign.alias=key-alias \
                        -Dandroid.sign.storepass=keystore-password \
                        -Dandroid.sign.keypass=key-password
    mvn release:clean

Credits
-------
The android-archetype-project is an open source project licensed under the Apache License 2.0.
It is founded by akquinet (http://akquinet.de/en)

Technical Notes
---------------
* As the archetypes used the new archetype format, they don't work with the deprecated goal 'archetype:create'
