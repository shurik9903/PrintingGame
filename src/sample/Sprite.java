package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

    public Method.Vector position;
    public Method.Vector velocity;
    public double rotation;
    public Method.Rectangle boundary;
    public Image image;

    public Sprite(){
        this.position = new Method.Vector();
        this.velocity = new Method.Vector();
        this.rotation = 0;
        this.boundary = new Method.Rectangle();
    }

    public Sprite(String fileImageName){
        this();
        setImage(fileImageName);
    }

    public void setImage(String fileImageName){
        this.image = new Image(fileImageName);
    }

    public Method.Rectangle getBoundary(){
        this.boundary.setPosition(this.position.x, this.position.y);
        return this.boundary;
    }

    public boolean overlaps(Sprite other){
        return this.getBoundary().overlaps(other.getBoundary());
    }

    public void update(double deltaTime){
        this.position.add(this.velocity.x * deltaTime, this.velocity.y * deltaTime);
    }

    public void render(GraphicsContext gc){
        gc.save();

        gc.translate(this.position.x, this.position.y);
        gc.rotate(this.rotation);
        gc.translate(-this.image.getWidth()/2,-this.image.getHeight()/2);
        gc.drawImage(this.image,0,0);

        gc.restore();
    }

}
