# Slick2D Eclipse Seed Project
This an eclipse project containing all the files necessary to begin developing games with [Slick 2D](http://slick.ninjacave.com/). You can fork this repository, clone it on your disk and start to work in Eclipse.

## Project configuration

After having cloned the repository to your disk you have to follow these few steps:

1. Open Eclipse.
2. Create a new java project selecting the local cloned folder.
2. Right-click on the project and select *Properties*.
3. Click on *Java Build Path*.
4. Expand *lwjgl.jar*.
5. Select *Natives Library Location* and click the *Edit* button.
6. Click the *Workspace* button.
7. Select the *native* folder in your project.
8. Click *OK* until you get back to the default eclipse window.
9. Select *SimpleSlickGame.java* from the *Package Explorer* and press play to check it it works.

## Building an executable JAR file

To generate an executable version of the game you will need to use *JarSplice* and follow these steps:

1. Right-click on the project and select *Export*.
2. Click on *Java* and select *Runnable JAR File*.
3. Select your main class in the *Lunch configuration* drop-down menu.
4. Select *Extract required libraries into generated JAR* and press the *Finish* button.
5. Download *JarSplice* at http://ninjacave.com/jarsplice and execute it (double-click on it).
6. Select *1) ADD JARS*, press the *Add Jar(s)* button in the bottom and select the previously exported JAR file.
7. Select *2) ADD NATIVES*, press the *Add Native(s)* button in the bottom, go to you projects *native* library and select all the files inside it.
8. Select *3) MAIN CLASS*, and in the *Enter Main Class* area type in the name of your main class including the name of the package (e.g. examples.SimpleSlickGame).
9. Select *4) CREATE FAT JAR* and press the *Create Fat Jar* button.
10. The Jar you generated can be distributed and played on Windows, Linux and Mac.


