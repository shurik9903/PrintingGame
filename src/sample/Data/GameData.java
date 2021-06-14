package sample.Data;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.Data.DataInterface.*;
import sample.Server.Model.ImageDate.IImageDate;
import sample.Server.Model.MyImage.IMyImage;
import sample.Server.Model.ServerFactory.ServerFactory;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;


public class GameData implements Serializable, IGameData {
    @Serial
    private static final long serialVersionUID = 6529685098267757691L;
    private final int UserScore;
    private boolean GameProcess;
    private final ArrayList<IMeteorData> MeteorList;
    private final ArrayList<ISpriteData> PlayersGun;
    private final ArrayList<IProjectileData> ProjectileList;
    private final ArrayList<IAimData> PlayersAim;

    private final IEnterToNumberData enterToNumberData;


    public GameData(int UserScore, ArrayList<ISpriteData> PlayersGun, boolean GameProcess, ArrayList<IMeteorData> MeteorList, ArrayList<IProjectileData> ProjectileList, ArrayList<IAimData> PlayersAim, IEnterToNumberData enterToNumberData){
        this.UserScore = UserScore;
        this.PlayersGun = PlayersGun;
        this.GameProcess = GameProcess;
        this.MeteorList = MeteorList;
        this.ProjectileList = ProjectileList;
        this.PlayersAim = PlayersAim;
        this.enterToNumberData = enterToNumberData;
    }

    public GameData(){
        this.UserScore = 0;
        this.PlayersGun = new ArrayList<>();
        this.GameProcess = false;
        this.MeteorList = new ArrayList<>();
        this.ProjectileList = new ArrayList<>();
        this.PlayersAim = new ArrayList<>();
        this.enterToNumberData = null;
    }


    public static class MeteorData implements Serializable, IMeteorData, ISpriteData {

        private ITextData Text;
        private ISpriteData spriteData;

        public MeteorData(double PosX, double PosY, double rotation,double ImageDataWidth, double ImageDataHeight, String file, ITextData Text) {
            spriteData = ServerFactory.SpriteDataCreateInstance( PosX,  PosY,  rotation, ImageDataWidth,  ImageDataHeight,  file);
            this.Text = Text;
        }

        //отрисовка объекта
        @Override
        public void render(GraphicsContext gc) {
            spriteData.render(gc);
            this.Text.render(gc);
        }

    }

    public static class SpriteData implements Serializable, ISpriteData {

        public double PosX, PosY;
        public double rotation;
        public double ImageDataWidth, ImageDataHeight;
        public String file;


        //конуструктор
        public SpriteData(double PosX, double PosY, double rotation,double ImageDataWidth, double ImageDataHeight, String file) {
            this.PosX = PosX;
            this.PosY = PosY;
            this.rotation = rotation;
            this.ImageDataWidth = ImageDataWidth;
            this.ImageDataHeight = ImageDataHeight;
            this.file = file;
        }


        //Отрисовка обекта на экране
        @Override
        public void render(GraphicsContext gc) {

            gc.save();

            gc.translate(PosX, PosY);
            gc.rotate(this.rotation);
            gc.translate(-ImageDataWidth / 2, -ImageDataHeight / 2);
            gc.drawImage(new Image(file, ImageDataWidth, ImageDataHeight, false, false), 0, 0);

            gc.restore();
        }
    }

    public static class TextData implements Serializable, ITextData{

        public double x, y;
        public double Width, Height;
        public int TextLength;
        public ArrayList<IImageDate> FrameImage, FrameNumber;
        public ArrayList<ArrayList<IMyImage>> FrameText;

        //конструктор
        public TextData(double x, double y, double Width, double Height,int TextLength,
                        ArrayList<IImageDate> FrameImage, ArrayList<IImageDate> FrameNumber,
                        ArrayList<ArrayList<IMyImage>> FrameText) {
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
        @Override
        public void render(GraphicsContext gc) {
            gc.save();

            int TextCount = 0;
            int NumberCount = 0;

            gc.translate(this.x, this.y);

            for (IImageDate i : this.FrameImage) {
                gc.translate(+this.Width, 0);
                gc.drawImage(new Image(i.getFileImageName(),i.getImageWidth(),i.getImageHeight(),false,false), 0, 0);

                if (NumberCount < FrameNumber.size()) {
                    gc.drawImage(new Image(FrameNumber.get(NumberCount).getFileImageName(),FrameNumber.get(NumberCount).getImageWidth(), FrameNumber.get(NumberCount).getImageHeight(),false,false ), 0, 0);
                    NumberCount++;
                } else {
                    if (TextCount < TextLength) {
                        gc.drawImage(new Image(this.FrameText.get(TextCount).get(0).getFileImageName(), this.FrameText.get(TextCount).get(0).getImageWidth(),this.FrameText.get(TextCount).get(0).getImageHeight(), false, false), 0, 0);TextCount++;
                    }
                }
            }

            gc.restore();
        }

    }

    public static class ProjectileData implements Serializable, IProjectileData, ISpriteData{

        private final ISpriteData spriteData;
        //Конструктор класса
        public ProjectileData(double PosX, double PosY, double rotation,double ImageDataWidth, double ImageDataHeight, String file) {
            spriteData = ServerFactory.SpriteDataCreateInstance( PosX,  PosY,  rotation, ImageDataWidth,  ImageDataHeight, file);
        }

        @Override
        public void render(GraphicsContext gc) {
            spriteData.render(gc);
        }
    }

    public static class AimData implements Serializable, IAimData, ISpriteData{

        private final ISpriteData spriteData;
        //Конструктор класса
        public AimData(double PosX, double PosY, double rotation,double ImageDataWidth, double ImageDataHeight, String file) {
            spriteData = ServerFactory.SpriteDataCreateInstance( PosX,  PosY,  rotation, ImageDataWidth,  ImageDataHeight, file);
        }

        @Override
        public void render(GraphicsContext gc) {
            spriteData.render(gc);
        }
    }

    public static class EnterToNumberData implements Serializable, IEnterToNumberData {
        private final double x, y;
        private final double Size;
        private final ArrayList<IImageDate> FrameNumber;

        //Конструктор
        public EnterToNumberData(double PosX, double PosY, double Size, ArrayList<IImageDate> FrameNumber) {
            this.x = PosX;
            this.y = PosY;
            this.Size = Size;
            this.FrameNumber = FrameNumber;
        }

        //Отрисовка номера
        @Override
        public void render(GraphicsContext gc) {
            gc.save();

            gc.translate(this.x, this.y);
            if (FrameNumber != null)
                for (IImageDate i : this.FrameNumber) {
                    gc.translate(+Size, 0);
                    gc.drawImage(new Image(i.getFileImageName(),i.getImageWidth(),i.getImageHeight(),false,false), 0, 0);
                }

            gc.restore();
        }

    }

    @Override
    public IEnterToNumberData getEnterToNumberData() {
        return enterToNumberData;
    }

    @Override
    public int getUserScore() {
        return UserScore;
    }

    @Override
    public ArrayList<ISpriteData> getPlayersGun(){
        return PlayersGun;
    }

    @Override
    public boolean isGameProcess() {
        return GameProcess;
    }

    @Override
    public void setGameProcess(boolean gameProcess) {
        GameProcess = gameProcess;
    }

    @Override
    public ArrayList<IMeteorData> getMeteorList() {
        return MeteorList;
    }

    @Override
    public ArrayList<IProjectileData> getProjectileList() {
        return ProjectileList;
    }

    @Override
    public ArrayList<IAimData> getAim() {
        return PlayersAim;
    }
}
