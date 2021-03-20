package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Method {

    public static class Vector{

        public double x,y;

        public Vector(){
            this.set(0,0);
        };

        public Vector(double x, double y){
            this.set(x,y);
        }

        public void set(double x, double y){
            this.x = x;
            this.y = y;
        }

        public void add(double x, double y){
            this.x += x;
            this.y += y;
        }

        public void multiply(double m){
            this.x *= m;
            this.y *= m;
        }

        public double getLength(){
            return Math.sqrt(this.x * this.x + this.y * this.y);
        }

        public void setLength(double l){
            double currentLength = this.getLength();
            if (currentLength == 0)
                return;

            this.multiply( 1/currentLength);

            this.multiply(l);
        }

        public double getAngle(){
            return Math.toDegrees(Math.atan2(this.y, this.x));
        }

        public void setAngle(double angleDegrees){
            double L = this.getAngle();
            double angleRadian = Math.toRadians(angleDegrees);
            this.x = L * Math.cos(angleRadian);
            this.y = L * Math.sin(angleRadian);
        }

    }

    public static class Rectangle{
        double x, y;
        double width, height;


        public Rectangle(){
            setPosition(0,0);
            setSize(0, 0);
        }

        public Rectangle(double x, double y, double width, double height){
            setPosition(x, y);
            setSize(width, height);
        }

        public void setPosition(double x, double y){
            this.x = x;
            this.y = y;
        }

        public void setSize(double width, double height){
            this.width = width;
            this.height = height;
        }

        public boolean overlaps(Rectangle other){
            boolean noOverlaps = this.x + this.width < other.x ||
                    other.x + other.width < this.x ||
                    this.y + this.height < other.y ||
                    other.y + other.height < this.y;

            return !noOverlaps;
        }

    }


}
