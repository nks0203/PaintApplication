# PaintApplication

This application is a console based Java program to demonstrate Paint features. This application depicts knowledge about design pattern ,solid principles, Spring boot , config . 
Design is made keeping in mind the maintainability, readability , refactoring. It would seem a bit too much but is easy to include new features later on.    

# Assumptions

This program takes the following assumptions:
- Create console command overwrites the previous canvas
- Canvas max height and width are 1000
- Dirty checking is not supported at the moment.

# Edge cases covered
- Canvas max height and width
- create canvas before other commands
- passed arguments outside of canvas
- point should be greater than 0
- only one fill character allowed for bucket fill

# How to use?
Supported Commands :
- C <width> <height> ; 0<width<=1000 , 0<height<=1000. **You would need to create a canvas first before running any other command.**
- L x1 y1 x2 y2 : Create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character.
- R x1 y1 x2 y2 : Create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character.
- B x y c : Fills the entire area connected to (x,y) with \"colour\" c. The behaviour of this is the same as that of the \"bucket fill\" tool in paint programs.
- Q :Quits the program. 

# How to run the program?
- Method 1 \
  You can open your favorite IDE which supports java programs > Open PaintApplication.java > click run/debug
  
- Method 2 \
  Run the following command : java -jar Paint-1.0.0-SNAPSHOT.jar
  
# Want to add new features?
Create two classes : **NEW-FEATURE**Command extends BaseCommand & **NEW-FEATURE-COMMAND**Processor extends AbstractCommandProcessor. Provide implemention for validating your command and processing for your entity.
Final rendering is taken care by AbstractCommand processor.
