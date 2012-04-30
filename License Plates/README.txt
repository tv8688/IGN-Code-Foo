Execute: To run the program, you need to install Python and use the following command in the command prompt in Windows(or terminal in Linux):
python license_plate.py population
where population is the input parameter for the population. If the population parameter is not specified, then a default population of 26 will be used.
Here is an example command:
python license_plate.py 10

Alogrithm: This is just a simple recurive algorithm. It will compute the coverage of every position in a pattern. If a position cannot cover all the population, then it will go to one more position to the left and choose the least excess between digits and alphabets.
The base case is when the population is less than 10 (then we will use digits) or is less than 26( then we will use alphabets).

Note: This is what I could come up with based on the examples given. It fits with the two examples and sounds correct although it still create many excess plates. I thought of some other more complicated patterns but I am just not sure if they are actually a valid license patterns.