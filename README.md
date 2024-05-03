# Music Visualiser Project

| Student Names | Student Numbers |
|-----------|-----------|
|Kacper Palka| C22376553|
|Miron Nekrasovs| C22486382 |
|Pavels Strelnikovs | C22491452 |

# Description of the assignment
In our assignment, we decided to create a platformer style game where a cube jumps over a gap in the floor to avoid landing in the toxic music wave below it. During the game the user can change the background 1-7 to simulate the cube going on an adventure where there is multiple different components such as boids, rotating cubes, trees, rainfall, spheres and many more reacting to the music. We managed to get the components reacting to music using the Minim audio library to extract audio from an MP3 file to allow for cool animations. In the project we have folders with our student number showing what components were created by who but take that with a grain of salt as everyone in the group was helping each other mostly on 1 laptop while creating the project due to us casting it to a TV. 

Here is our YouTube video of the project:


And Lastly the song chosen is Snake Eyes by Faint:


[![SnakeEyes](http://img.youtube.com/vi/cvq7Jy-TFAU/0.jpg)](https://www.youtube.com/watch?v=cvq7Jy-TFAU)

# How to Run
For Linux/Mac Systems:

```./CompileAndRun.sh```


For Windows Systems:
Run/Debug Java in Vscode

or

Double Click `Compile.bat`

or

Using Powershell ```.\Compile.bat```

# Instructions
- Compile and run using instructions above for your system.
- Press or hold spacebar to jump.
- Press 1-7 to change between backgrounds.

# How it works
Kacper Palka C22376553:
So I designed the structure of our project choosing to make individual folders for each person so we can easily identify who made what. I chose to make a new compile script for linux which let me run and compile at the same time making it easier to see changes on my machine and due to having issues with vscode a temporary `Compile.bat` file was created which does the same as the linux script but for windows. After we had a way to quickly run the code, I designed a rain fall component and this is when our team thought about making a platformer-style game with a cube in the middle jumping over obstacles to avoid falling into a toxic pool, this allowed me to create a wave at the bottom of the screen which bounces up and down by changing the amplitude in relation to the music being played by using Minim. Once I create these components I moved onto making the square, I firstly created a basic grey square and decided to add a circle in the middle of it to make it look nicer. After the basic square without physics was made I created the platform component, firstly it started off with just a giant rectangle going across the screen but then I made it create a random sized gap before the platform was rendered again as well as moving physics making it move left. The random sized gap was achieved by making the next platform be rendered a `-x` distance from the one that was just on screen and spawning when the other platform is 50% off the screen. After the platform was fully working, I created physics for the cube to jump up and down. This was created by adding a gravity which pulls the cube down slowly by changing the `y coordinate` and `velocity` which changes how fast it moves up and down. Once I ghot it working moving up and down, I added a check to see if its jumping to allow the user to hold spacebar so it jumps forever as well as adding a rotating effect so the cube slowly spins while jumping for a cool animation. To finish the platforms off I added a small triangle under it to make it look nicer.
# What I am most proud of in the assignment


