package sample;

import javafx.scene.canvas.GraphicsContext;
import sample.Server.Model.ImageDate;
import sample.Server.Model.MyImage;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;


public class GameData implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757691L;
    private final int UserScore;
    private final double PlayerRotation;
    private boolean GameProcess;
    private final ArrayList<MeteorData> MeteorList;
    private final ArrayList<ProjectileData> ProjectileList;
    private final AimData aim;
    private final EnterToNumberData enterToNumberData;

    public GameData(int UserScore, double PlayerRotation, boolean GameProcess, ArrayList<MeteorData> MeteorList, ArrayList<ProjectileData> ProjectileList, AimData aim, EnterToNumberData enterToNumberData){
        this.UserScore = UserScore;
        this.PlayerRotation = PlayerRotation;
        this.GameProcess = GameProcess;
        this.MeteorList = MeteorList;
        this.ProjectileList = ProjectileList;
        this.aim = aim;
        this.enterToNumberData = enterToNumberData;
    }

    public GameData(){
        this.UserScore = 0;
        this.PlayerRotation = 0;
        this.GameProcess = false;
        this.MeteorList = new ArrayList<>();
        this.ProjectileList = new ArrayList<>();
        this.aim = null;
        this.enterToNumberData = null;
    }

    public static class MeteorData extends SpriteData implements Serializable{

        public TextData Text;

        public MeteorData(double PosX, double PosY, double rotation, ImageDate image, TextData Text) {
            super(PosX, PosY, rotation, image);
            this.Text = Text;
        }

        //отрисовка объекта
        @Override
        public void render(GraphicsContext gc) {
            super.render(gc);
            this.Text.render(gc);
        }

    }

    public static class SpriteData implements Serializable{

        public double PosX, PosY;
        public double rotation;
        public ImageDate image;

        //конуструктор
        public SpriteData(double PosX, double PosY, double rotation, ImageDate image) {
            this.PosX = PosX;
            this.PosY = PosY;
            this.rotation = rotation;
            this.image = image;
        }


        //Отрисовка обекта на экране
        public void render(GraphicsContext gc) {

            gc.save();

            gc.translate(PosX, PosY);
            gc.rotate(this.rotation);
            gc.translate(-this.image.getWidth() / 2, -this.image.getHeight() / 2);
            gc.drawImage(image.getImage(), 0, 0);

            gc.restore();
        }
    }

    public static class TextData implements Serializable{

        public double x, y;
        public double Width, Height;
        public int TextLength;
        public ArrayList<ImageDate> FrameImage, FrameNumber;
        public ArrayList<ArrayList<MyImage>> FrameText;

        //конструктор
        public TextData(double x, double y, double Width, double Height,int TextLength,
                        ArrayList<ImageDate> FrameImage, ArrayList<ImageDate> FrameNumber,
                        ArrayList<ArrayList<MyImage>> FrameText) {
            this.x = x;
            this.y = y;
            this.TextLength = TextLength;
            this.Width = Width;
            this.Height = Height;
            this.FrameImage = FrameImage;
            this.FrameNumber = FrameNumber;
            this.FrameText = FrameText;
        }

        //Отрисовка объекта
        public void render(GraphicsContext gc) {
            gc.save();

            int TextCount = 0;
            int NumberCount = 0;

            gc.translate(this.x, this.y);

            for (ImageDate i : this.FrameImage) {
                gc.translate(+this.Width, 0);
                gc.drawImage(i.getImage(), 0, 0);

                if (NumberCount < FrameNumber.size()) {
                    gc.drawImage(this.FrameNumber.get(NumberCount).getImage(), 0, 0);
                    NumberCount++;
                } else {
                    if (TextCount < TextLength) {
                        gc.drawImage(this.FrameText.get(TextCount).get(0).getImage(), 0, 0);
                        TextCount++;
                    }
                }
            }

            gc.restore();
        }

    }

    public static class ProjectileData extends SpriteData implements Serializable{
        //Конструктор класса
        public ProjectileData(double PosX, double PosY, double rotation, ImageDate image) {
            super(PosX, PosY, rotation, image);
        }
    }

    public static class AimData extends SpriteData implements Serializable{
        //Конструктор класса
        public AimData(double PosX, double PosY, double rotation,ImageDate image) {
            super(PosX, PosY, rotation, image);
        }
    }



    public static class EnterToNumberData implements Serializable {
        private final double x, y;
        private final double Size;
        private final ArrayList<ImageDate> FrameNumber;

        //Конструктор
        public EnterToNumberData(double PosX, double PosY, double Size, ArrayList<ImageDate> FrameNumber) {
            this.x = PosX;
            this.y = PosY;
            this.Size = Size;
            this.FrameNumber = FrameNumber;
        }

        //Отрисовка номера
        public void render(GraphicsContext gc) {
            gc.save();

            gc.translate(this.x, this.y);
            if (FrameNumber != null)
                for (ImageDate i : this.FrameNumber) {
                    gc.translate(+Size, 0);
                    gc.drawImage(i.getImage(), 0, 0);
                }

            gc.restore();
        }

    }

    public EnterToNumberData getEnterToNumberData() {
        return enterToNumberData;
    }

    public int getUserScore() {
        return UserScore;
    }

    public double getPlayerRotation() {
        return PlayerRotation;
    }

    public boolean isGameProcess() {
        return GameProcess;
    }

    public void setGameProcess(boolean gameProcess) {
        GameProcess = gameProcess;
    }

    public ArrayList<MeteorData> getMeteorList() {
        return MeteorList;
    }

    public ArrayList<ProjectileData> getProjectileList() {
        return ProjectileList;
    }

    public AimData getAim() {
        return aim;
    }
}
