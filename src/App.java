import processing.core.*;

public class App extends PApplet {
    public float diameter;
    public float diameter2;
    int x1, y1;
    int x2, y2;

    int birdY = 0;

    boolean running = true;
    boolean firstRing = true;
    public float ringCount = 0;
    boolean goingUp = true;

    int cloudX = 0;
    int cloudY = 0;
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
        x1 = round(random(width));
        y1 = round(random(height));
        x2 = round(random(width));
        y2 = round(random(width));

    }

    public void draw() {
        
     float distance = dist(x1, y1, width / 2, height / 2);
        float distance2 = dist(x2, y2, width / 2, height / 2);
        
        if (running == true) {
            
            if (firstRing == true) {
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
                if (ringCount > 0) {
                    diameter2 += 2+ringCount;
                    stroke(255, 0, 0);
                    strokeWeight(20);
                    fill(255, 0, 0, 0);
                    drawCircle(x2, y2, diameter2);
                }

                diameter += 2+ringCount;

                stroke(255, 0, 0);
                strokeWeight(20);
                fill(255, 0, 0, 0);
                drawCircle(x1, y1, diameter);

                if (diameter > 800) {
                    drawCircle(x2, y2, diameter2);
                    ringCount++;
                    x2 = round(random(width));
                    y2 = round(random(height));
                    diameter2 = 0;
                    firstRing = false;
                }
                bird();

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

                stroke(255, 0, 0);
                strokeWeight(20);
                fill(255, 0, 0, 0);
                drawCircle(x1, y1, diameter);
                diameter += 2+ringCount;

                diameter2 += 2+ringCount;
                stroke(255, 0, 0);
                strokeWeight(20);
                fill(255, 0, 0, 0);
                drawCircle(x2, y2, diameter2);
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

        } else {
            background(0);
            fill(255,255,0);
            textSize(48);
            textAlign(CENTER,CENTER);
            text("Press R to restart",width/2,300);
            
            textSize(48);
            textAlign(CENTER,CENTER);
            text("Rings:"+ringCount, width/2, 500);

            fill(255,0,0);
            textSize(75);
            textAlign(CENTER,CENTER);
            text("YOU LOSE",width/2,100);
            

        }

        if (diameter > 800 && distance > 50) {
            running = false;
        }

        if (diameter2 > 800 && distance2 > 50) {
            running = false;
        }
    

    }

    public void drawCircle(int x, int y, float diameter) {
        ellipse(x, y, diameter, diameter);
    }

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
        
            if (keyCode == UP) {
                upKey = true;
            } if (keyCode == DOWN) {
                downKey = true;
            } if (keyCode == RIGHT) {
                rightKey = true;
            } if (keyCode == LEFT) {
                leftKey = true;
            }
           
            if ((keyCode=='r'||keyCode=='R')){
            
                setup();
                cloudX=0;
                cloudY=0;
                running=true;
                firstRing=true;
                ringCount=0;
            
            
        }
    }
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