import processing.core.*;



public class App extends PApplet {
    public float diameter;
    int x,y;
    boolean running = true;
    public static void main(String[] args)  {
        PApplet.main("App");
    }
    public void settings(){
        size(800,600);
    }
    public void setup() {
        background(0,255,255);
        diameter = 0;
        x=round(random(width));
        y=round(random(height));
        
    }
    public void draw(){
        if(running=true){
        background(0,255,255);
        diameter+=2;
        fill(255,0,0);
        drawCircle(x,y,diameter);
       
        fill(0,255,255);
        drawCircle(x,y,diameter-30);
        if(diameter>1200) {
            diameter = 0;
            x=round(random(width));
            y=round(random(height));
        }
       float distance = dist(x,y,width/2,height/2);
        if(diameter>800 && distance>20){
        running = false;    
        }
    } else {
        background(0);
    }
        
        
    }
    public void drawCircle(int x, int y, float diameter) {
        ellipse(x,y,diameter,diameter);
}
public void keyPressed() {

    if (keyCode == UP) {
        y -= 10;
    } else if (keyCode == DOWN) {
        y += 10;
    } else if (keyCode == RIGHT) {
        x += 10;
    } else if (keyCode == LEFT) {
        x -= 10;
    }
}
}