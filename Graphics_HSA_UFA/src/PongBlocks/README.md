#HOW TO WRITE AN ANIMATION GAME

This is done using the HSA GraphicsConsole so that you don't have to learn Java Swing Graphics first.

----

*Important:*
I'm making "commits" in Git/GitHub for each major improvement. 
Please start at the first one, and go through them so that you understand what is happening.

##5. Draw Blocks ...

Again there are 3 steps: create the block objects, draw them on the screen, then see if they get hit by the ball. (and see if you have won the game).

I've only drawn 5 blocks. You should make a grid of them. You can use modulus (%) and integer division (/) to find the row and columns. e.g. if you had 10 blocks in each row, then for block #43, row = n / 10 (which returns 4) and col = n%10 (which returns 3). The column is used to set the X values, the row is used to set the Y values. 

When a block is hit, there are various things that can be done:
- If all of the blocks are stored in an ArrayList, you can just delete the block.
- You can't do this with Arrays though.
- If you store the blocks in an array, you can do two things: (i) set isVisible to false
(ii) set the y coordinate of the block to -100. This moves the block up off of the top of the screen. 

Method (ii) is better because the block will never collide with the ball since the ball never goes off screen. 
This saves time because you don't have to worry about colliding with invisible blocks.
I am still using isVisible, but I don't really need to. I could replace it with `if (ball.y < 0) ...`

Winning and losing. I've more or less got this working, but if you type "Q" it says that you win.

### DO NOT MAKE THINGS PUBLIC UNLESS YOU HAVE TO. Always restrict the scope of variables and methods as much as possible.

I just caught an error because, thankfully, I didn't make the Block() constructor public. 
It was trying to use an older version of Block() in a completely different package.

So, I've made as many things as possible private. I can't make the variables in the Ball and Block class private unless I write getter and setter methods, which is quite easy, but I'll leave this as an excercise for the student.
