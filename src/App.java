import processing.core.*;

public class App extends PApplet {
//sets up variables for the diameters of the two rings
    public float diameter;
    public float diameter2;
//sets up variables so the coordinates of the rings can be changed
    int x1, y1;
    int x2, y2;
    
//sets up variable to change the position of the birds' wings so they can flap
    int birdY = 0;

//booleans to detect if the game is running, which ring is being controlled, 
//and which direction the bird's wings are moving
    boolean running = true;
    boolean firstRing = true;
    boolean goingUp = true;
    public float ringCount = 0;
//sets up coordinates of the clouds
    int cloudX = 0;
    int cloudY = 0;
//booleans so that multiple keys can be pressed at once
    boolean upKey = false;
    boolean downKey = false;
    boolean rightKey = false;
    boolean leftKey = false;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        background(0, 255, 255);
        diameter = 0;
        diameter2 = 0;
//coordinates of the rings randomized
        x1 = round(random(width));
        y1 = round(random(height));
        x2 = round(random(width));
        y2 = round(random(width));

    }

    public void draw() {
//sets up floats for the distance of the rings from the center.
     float distance = dist(x1, y1, width / 2, height / 2);
     float distance2 = dist(x2, y2, width / 2, height / 2);
        
//puts all the code under the running==true condition so that if you lose, the code stops
        if (running == true) {
//code is written twice - if the first ring is in play, the first ring is controlled,
//if the second ring is in play, the second ring is controlled
            if (firstRing == true) {

//sets up blue background and clouds
                background(0, 255, 255);
                drawCloud(70+cloudX, 70+cloudY, 100);
                drawCloud(400+cloudX, 500+cloudY, 200);
                drawCloud(700+cloudX, 200+cloudY, 100);
                drawCloud(800+cloudX,700+cloudY, 100);
                drawCloud(cloudX, 800+cloudY, 70);
                drawCloud(-200+cloudX, 300+cloudY, 140);

//sets up a visible counter that displays how many rings have been passed through
                fill(255,255,0);
                textSize(48);
                textAlign(CENTER,CENTER);
                text("Rings: " + ringCount,700,500);
//condition so that when the game starts, only the first ring is in play
                if (ringCount > 0) {
                    diameter2 += 2+ringCount;
                    stroke(255, 0, 0);
                    strokeWeight(20);
                    fill(255, 0, 0, 0);
                    drawCircle(x2, y2, diameter2);
                }

//creates a ring that constantly increases in diameter as the code runs
                diameter += 2+ringCount;
                stroke(255, 0, 0);
                strokeWeight(20);
                fill(255, 0, 0, 0);
                drawCircle(x1, y1, diameter);

//when the first ring reaches a certain size, the second ring appears in a random position
//the second ring is now being controlled as firstRing=false, and its diameter begins at 0

                if (diameter > 800) {
                    drawCircle(x2, y2, diameter2);
                    ringCount++;
                    x2 = round(random(width));
                    y2 = round(random(height));
                    diameter2 = 0;
                    firstRing = false;
                }
//places the bird
                bird();

//when the arrow keys are pressed, the bird moves in that direction
                if (upKey==true){
                    y1 += 5+ringCount;
                    cloudY += 5+ringCount;
                }
                if (downKey==true){
                    y1 -= 5+ringCount;
                    cloudY -= 5 + ringCount;
                }
                if (rightKey==true) {
                    x1 -= 5+ringCount;
                    cloudX -= 5+ringCount;
                }
                if (leftKey==true) {
                    x1 += 5+ringCount;
                    cloudX += 5+ringCount;
                }
//code for when firstRing==false, meaning the second ring is in being controlled
//nearly identical to code for first ring, just applying to the second ring
            } else {
                background(0, 255, 255);
                drawCloud(70+cloudX, 70+cloudY, 100);
                drawCloud(400+cloudX, 500+cloudY, 200);
                drawCloud(700+cloudX, 200+cloudY, 100);
                drawCloud(800+cloudX,700+cloudY, 100);
                drawCloud(cloudX, 800+cloudY, 70);
                drawCloud(-200+cloudX, 300+cloudY, 140);
                


                fill(255,255,0);
                textSize(48);
                textAlign(CENTER,CENTER);
                text("Rings: " + ringCount,700,500);

//code so that the first ring still increases as before
                stroke(255, 0, 0);
                strokeWeight(20);
                fill(255, 0, 0, 0);
                drawCircle(x1, y1, diameter);
                diameter += 2+ringCount;
//code for the second ring being in play and increasing in size
                diameter2 += 2+ringCount;
                stroke(255, 0, 0);
                strokeWeight(20);
                fill(255, 0, 0, 0);
                drawCircle(x2, y2, diameter2);
//completes the cycle - if the second ring reaches 800, the first ring's diameter is reset
// and firstRing becomes true again
                if (diameter2 > 800) {
                    diameter = 0;
                    x1 = round(random(width));
                    y1 = round(random(width));
                    firstRing = true;
                    ringCount++;
                }
                bird();

                if (upKey==true){
                    y2 += 5+ringCount;
                    cloudY += 5+ringCount;
                }
                if (downKey==true){
                    y2 -= 5+ringCount;
                    cloudY -= 5 + ringCount;
                }
                if (rightKey==true) {
                    x2 -= 5+ringCount;
                    cloudX -= 5+ringCount;
                }
                if (leftKey==true) {
                    x2 += 5+ringCount;
                    cloudX += 5+ringCount;
                }
            }
//code for if the code stops running, sets up the game over screen
        } else {
            background(0);
            fill(255,255,0);
            textSize(48);
            textAlign(CENTER,CENTER);
            text("Press R to restart",width/2,300);
            
            textSize(48);
            textAlign(CENTER,CENTER);
            text("Rings:"+(ringCount-1), width/2, 500);

            fill(255,0,0);
            textSize(75);
            textAlign(CENTER,CENTER);
            text("YOU LOSE",width/2,100);
            

        }
//if the diameter of either circle is too great and the circle is too far away from the center,
//the game stops and it goes to the game over screen
        if (diameter > 800 && distance > 50) {
            running = false;
        }

        if (diameter2 > 800 && distance2 > 50) {
            running = false;
        }
    

    }

//method for drawing circles
    public void drawCircle(int x, int y, float diameter) {
        ellipse(x, y, diameter, diameter);
    }

//method for drawing clouds by drawing many white circles
    public void drawCloud(int x, int y, float diameter) {
        fill(255, 255, 255);
        stroke(255, 255, 255);
        ellipse(x, y, diameter, diameter);
        ellipse(x + 50, y, diameter, diameter);
        ellipse(x + 100, y, diameter, diameter);
        ellipse(x + 80, y + 20, diameter, diameter);
        ellipse(x + 30, y + 20, diameter, diameter);
        ellipse(x + 20, y - 30, diameter, diameter);
        ellipse(x + 80, y - 30, diameter, diameter);
    }

//method for drawing the bird with the moving wings in the center of the screen
    public void bird() {
        fill(0,0,255);
        stroke(0);
        strokeWeight(0);
        circle(width/2,height/2,30);

        stroke(0);
        strokeWeight(0);
        quad(415,315,415,285,465,285+birdY,465,315+birdY);
        quad(385,315,385,285,335,285+birdY,335,315+birdY);
        
        if(goingUp==true){
        birdY+=1;
        } else {
            birdY-=3;
        }
        if(birdY>10){
            goingUp=false;
        }
        if(birdY<-10){
            goingUp=true;
        }        

        
    }

    public void keyPressed() {
//if an arrow key is pressed, its boolean is activated so the bird moves in that direction until the key is released
            if (keyCode == UP) {
                upKey = true;
            } if (keyCode == DOWN) {
                downKey = true;
            } if (keyCode == RIGHT) {
                rightKey = true;
            } if (keyCode == LEFT) {
                leftKey = true;
            }
//resets the game if r is pressed
            if ((keyCode=='r'||keyCode=='R')){
            
                setup();
                cloudX=0;
                cloudY=0;
                running=true;
                firstRing=true;
                ringCount=0;
            
            
        }
    }
//code to stop the bird from moving in a direction once the arrow key is released
    public void keyReleased(){
        if (keyCode == UP){
            upKey = false;
        }
        if (keyCode == DOWN){
            downKey = false;
        }
        if (keyCode == RIGHT){
            rightKey = false;
        }
        if (keyCode == LEFT) {
            leftKey = false;
        }
    }
}