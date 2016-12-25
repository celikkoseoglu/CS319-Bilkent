package GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.*;
import java.util.*;

public class Car {

    public double weight = 1800;
    public CarPhysics position = new CarPhysics();
    public CarPhysics direction = new CarPhysics(0, 1, 0);
    public CarPhysics velocity = new CarPhysics();
    public CarPhysics acceleration = new CarPhysics();

    private final double HEIGHT =70;
    private final double WIDTH =50;

    
    public double brakeCons = 500;
    public CarPhysics brakeForce = new CarPhysics();
    
    public double power = 0;
    public CarPhysics tractionForce = new CarPhysics();

    public double dragCons = 1.4257;
    public CarPhysics dragForce = new CarPhysics();

    public CarPhysics rresistanceForce = new CarPhysics();
    public double rresistanceCons = 12.8;
    
    public CarPhysics longForce = new CarPhysics();
    private ArrayList<Cannonball> weapons=new ArrayList<Cannonball>();
    
    public boolean drifts;
    public boolean visible=true;
    private boolean isBackward=false;

    public Car() {
    }
    
    public void update() {
        
        double tyreCons = 0.6;
        
        double dif = tyreCons * (velocity.getMagnitude() / 30.0);
        
        if (InputManager.keydown[37]) {
            direction.gyroZ(Math.toRadians((tyreCons) * velocity.getMagnitude()));
        }
        else if (InputManager.keydown[39]) {
            direction.gyroZ(Math.toRadians((-tyreCons) * velocity.getMagnitude()));
        }

        double dangle = velocity.calculateRelative(direction);
        System.out.println("dif="+dif);
        if (!Double.isNaN(dangle)) {
            double rand = Math.random() * 50;
            velocity.gyroZ(dangle/((50+rand)*(5*dif)));
            drifts = Math.abs(Math.toDegrees(dangle)) > 30;
        }

        if (InputManager.keydown[38]) {
            power = 300;
        }
        else if (InputManager.keydown[40]) {
            power = -300;
        }
        else  {
            power = 0;
        }

        if (drifts) {
            power = power / 5;
        }


        boolean brakes = InputManager.keydown[66];
        isBackward= InputManager.keydown[40];


        changeBrakeForce();
        changeTraction();
        changeDrag();
        changeRR();
        changeLongForce(brakes);
        changeAcceleration();
        changeVelocity(1);
        changePos(1);

        updateWeapons();

    }
    
    private Point2D dp1;
    private Point2D dp2;
    
    public void draw(Graphics2D g, Graphics2D background) {
        background.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double angle = -Math.atan2(direction.x, direction.y);
        g.rotate(angle, position.x, position.y + 0);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.BLACK);
        ImageIcon icon = new ImageIcon(System.getProperty("os.name").contains("Mac") ? "car.jpg" : "car.jpg");
        Image image = icon.getImage();
        g.drawImage(image, (int) (position.x-25), (int) (position.y-35), null);

        
        if (drifts) {
            AffineTransform transf = new AffineTransform();
            transf.rotate(angle, position.x, position.y + 0);
            
            background.setColor(new Color(0, 0, 0, 16));
            BasicStroke basicStroke = new BasicStroke(10);
            background.setStroke(basicStroke);
            
            if (dp1!=null && dp2!=null) {
                Point2D p1 = new Point2D.Double((int) (position.x-17), (int) (position.y-30));
                Point2D p2 = new Point2D.Double((int) (position.x+17), (int) (position.y-30));

                transf.transform(p1, p1);
                transf.transform(p2, p2);
                
                background.drawLine((int) p1.getX(), (int) p1.getY(), (int) dp1.getX(), (int) dp1.getY());
                background.drawLine((int) p2.getX(), (int) p2.getY(), (int) dp2.getX(), (int) dp2.getY());
            }
            
            dp1 = new Point2D.Double((int) (position.x-17), (int) (position.y-30));
            dp2 = new Point2D.Double((int) (position.x+17), (int) (position.y-30));
            transf.transform(dp1, dp1);
            transf.transform(dp2, dp2);
        }
        else {
            dp1 = null;
            dp2 = null;
        }
    }
    
    private void changeTraction() {
        tractionForce.set(direction);
        tractionForce.standardize();
        tractionForce.multiply(power);
    }
   
    private void changeDrag() {
        double speed = velocity.getMagnitude();
        dragForce.set(velocity);
        dragForce.multiply(speed);
        dragForce.multiply(-dragCons);
    }

    
    private void changeRR() {
        rresistanceForce.set(velocity);
        rresistanceForce.multiply(-rresistanceCons);
    }
    
    private void changeLongForce(boolean brakes) {
        if (brakes) {
            longForce.set(brakeForce);
        }
        else if (isBackward){
            brakeForce.multiply(4);
            longForce.set(brakeForce);
        }
        else {
            longForce.set(tractionForce);
        }
        longForce.add(dragForce);
        longForce.add(rresistanceForce);
    }
    
    private void changeAcceleration() {
        acceleration.set(longForce);
        acceleration.multiply(1/weight);
    }
    
    private void changeVelocity(double deltaTime) {
        acceleration.multiply(deltaTime);
        velocity.add(acceleration);
        velocity.print();
    }
 
    private void changePos(double deltaTime) {
        velocity.multiply(deltaTime);
        position.add(velocity);
    }
    
    private void changeBrakeForce() {
        brakeForce.set(velocity);
        brakeForce.standardize();
        brakeForce.multiply(-brakeCons);
    }

    public void fire() {
        double angle =Math.atan2(direction.x, direction.y);

        weapons.add(new Cannonball((int)(position.x+400+35*Math.sin(angle)) , (int) (-position.y + 300- 35*Math.cos(angle)),angle));
    }

    public ArrayList<Cannonball> getWeapons(){
        return weapons;
    }

    private void updateWeapons(){
        ArrayList<Cannonball> cs = getWeapons();

        for (int i = 0; i < cs.size(); i++) {

            Cannonball c = cs.get(i);

            if (c.getVisibility()) {
                c.move();
            } else {
                cs.remove(i);
            }
        }
    }

    public boolean checkCollision(Rectangle2D rand){

        double angle =Math.atan2(direction.x, direction.y);

        Line2D[] lines = new Line2D[4];
        Point2D leftup = new Point2D.Double( position.x+400+WIDTH/2*Math.cos(angle)-HEIGHT/2*Math.sin(angle),-position.y+300+HEIGHT/2*Math.cos(angle)+WIDTH/2*Math.sin(angle));
        Point2D rightup = new Point2D.Double( position.x+400-WIDTH/2*Math.cos(angle)-HEIGHT/2*Math.sin(angle),-position.y+300+HEIGHT/2*Math.cos(angle)-WIDTH/2*Math.sin(angle));
        Point2D leftdown = new Point2D.Double( position.x+400+WIDTH/2*Math.cos(angle)+HEIGHT/2*Math.sin(angle),-position.y+300-HEIGHT/2*Math.cos(angle)+WIDTH/2*Math.sin(angle));
        Point2D rightdown = new Point2D.Double( position.x+400-WIDTH/2*Math.cos(angle)+HEIGHT/2*Math.sin(angle),-position.y+300-HEIGHT/2*Math.cos(angle)-WIDTH/2*Math.sin(angle));


        lines[0]=new Line2D.Double(leftup,rightup);
        lines[1]=new Line2D.Double(leftup,leftdown);
        lines[2]=new Line2D.Double(rightup,rightdown);
        lines[3]=new Line2D.Double(leftdown,rightdown);

        boolean x= false;

        for(int i=0; i < lines.length; i++)
            if(lines[i].intersects(rand)) {
                x = true;
            }
        return x;
    }

    public boolean checkParking(Rectangle2D rand){

        double angle =Math.atan2(direction.x, direction.y);

        Point2D leftup = new Point2D.Double( position.x+400+WIDTH/2*Math.cos(angle)-HEIGHT/2*Math.sin(angle),-position.y+300+HEIGHT/2*Math.cos(angle)+WIDTH/2*Math.sin(angle));
        Point2D rightup = new Point2D.Double( position.x+400-WIDTH/2*Math.cos(angle)-HEIGHT/2*Math.sin(angle),-position.y+300+HEIGHT/2*Math.cos(angle)-WIDTH/2*Math.sin(angle));
        Point2D leftdown = new Point2D.Double( position.x+400+WIDTH/2*Math.cos(angle)+HEIGHT/2*Math.sin(angle),-position.y+300-HEIGHT/2*Math.cos(angle)+WIDTH/2*Math.sin(angle));
        Point2D rightdown = new Point2D.Double( position.x+400-WIDTH/2*Math.cos(angle)+HEIGHT/2*Math.sin(angle),-position.y+300-HEIGHT/2*Math.cos(angle)-WIDTH/2*Math.sin(angle));

        if(rand.contains(leftup) && rand.contains(rightdown) && rand.contains(leftdown) && rand.contains(rightup)) {
            return true;
        }


        return false;
    }

}