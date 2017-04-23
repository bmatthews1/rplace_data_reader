# rplace_data_reader
A small Java program to interpret and display r/place data

# How to use
You will need to download this dataset and place it in a folder called "data" in the same directory as the project root:
https://storage.googleapis.com/place_events/tile_placements.csv.gz

There's not much to this program. Simply clone the repository and compile and run Main.java. There's no GUI right now other than the display, so if you want to change parameters you have to go into the code to do it.

# Data storage
The data gets stored into a 3D int array that is 1000x1000x17. The first two indicies are the x and y coordinate respectively. the last array is the 16 colors and a 17 value that represents the last color value placed on the image. For more explanation on the colros and data, see this thread: https://www.reddit.com/r/redditdata/comments/6640ru/place_datasets_april_fools_2017/
