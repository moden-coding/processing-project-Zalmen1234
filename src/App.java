import processing.core.*;

public class App extends PApplet {
    public float diameter;
    public float diameter2;
    int x1, y1;
    int x2, y2;

    boolean running = true;
    boolean firstRing = true;
    public float ringCount = 0;

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
                drawCloud(70, 70, 100);
                drawCloud(400, 500, 200);
                drawCloud(700, 200, 100);
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

            } else {
                background(0, 255, 255);
                drawCloud(70, 70, 100);
                drawCloud(400, 500, 200);
                drawCloud(700, 200, 100);


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
            }

        } else {
            background(0);
            fill(255,255,0);
            textSize(48);
            textAlign(CENTER,CENTER);
            text("Press R to restart",width/2,500);
            fill(255,0,0);
            textSize(75);
            textAlign(CENTER,CENTER);
            text("YOU LOSE",width/2,300);

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

    public void keyPressed() {
        if (firstRing == true) {
            if (keyCode == UP) {
                y1 -= 10+ringCount;
            } else if (keyCode == DOWN) {
                y1 += 10+ringCount;
            } else if (keyCode == RIGHT) {
                x1 += 10+ringCount;
            } else if (keyCode == LEFT) {
                x1 -= 10+ringCount;
            }
            

        }

        if (firstRing == false) {
            if (keyCode == UP) {
                y2 -= 10+ringCount;
            } else if (keyCode == DOWN) {
                y2 += 10+ringCount;
            } else if (keyCode == RIGHT) {
                x2 += 10+ringCount;
            } else if (keyCode == LEFT) {
                x2 -= 10+ringCount;
            }
        }
        if ((keyCode=='r'||keyCode=='R')){
            ringCount=0;
            setup();
            running=true;
            
            
            
        }
    }
}