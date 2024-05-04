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
So I designed the structure of our project choosing to make individual folders for each person so we can easily identify who made what. I chose to make a new compile script for linux which let me run and compile at the same time making it easier to see changes on my machine and due to having issues with vscode a temporary `Compile.bat` file was created which does the same as the linux script but for windows. After we had a way to quickly run the code, I designed a rain fall component and this is when our team thought about making a platformer-style game with a cube in the middle jumping over obstacles to avoid falling into a toxic pool, this allowed me to create a wave at the bottom of the screen which bounces up and down by changing the amplitude in relation to the music being played by using Minim. Once I create these components I moved onto making the square, I firstly created a basic grey square and decided to add a circle in the middle of it to make it look nicer. After the basic square without physics was made I created the platform component, firstly it started off with just a giant rectangle going across the screen but then I made it create a random sized gap before the platform was rendered again as well as moving physics making it move left. The random sized gap was achieved by making the next platform be rendered a `-x` distance from the one that was just on screen and spawning when the other platform is 50% off the screen. After the platform was fully working, I created physics for the cube to jump up and down. This was created by adding a gravity which pulls the cube down slowly by changing the `y coordinate` and `velocity` which changes how fast it moves up and down. Once I ghot it working moving up and down, I added a check to see if its jumping to allow the user to hold spacebar so it jumps forever as well as adding a rotating effect so the cube slowly spins while jumping for a cool animation. To finish the platforms off I added a small triangle under it to make it look nicer. So now we have to create some backgrounds, I firstly created multiple trees in a component that open and close and use recursion for the branches. After making them I add boids to make it look like birds are flying from tree to tree like they would in the real world. I used a tutorial to get the boids fully working and they change color and speed depending on the song. These boids flock like real birds which means they group up and fly from one side of the screen to the other. Next thing I worked on is finishing and making something cool with the rainfall so I added a sphere which gets bigger and smaller using camera angle and is dependent on the song used. It also uses colors and lerp for smooth transitions. The final thing I added to this background was a snowflake like shape which gets drawn out slowly and changes color smoothly using lerp, the longer this gets drawn out the brighter it becomes as the lines start out as being feint. Next thing I implemented was an array of dots with lines that change color to make it look like a skipping rope for the cube to jump over. Lastly I implmented multiple cubes rotating, changing color and chaning size to make them look like they are bouncing depending on the music and implemented 2 ball curves used for another background that change color and speed depending on the music.
# What I am most proud of in the assignment


