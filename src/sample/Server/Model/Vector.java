package sample.Server.Model;

//Класс вектор: Расположение, направление, скорость движения
public class Vector {

    public double x, y;

    //конструктор
    public Vector() {
        this.set(0, 0);
    }

    public Vector(double x, double y) {
        this.set(x, y);
    }

    //установка координат
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //смещение координат путем сложения
    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    //смещение координат путем произведения
    public void multiply(double m) {
        this.x *= m;
        this.y *= m;
    }

    //Получение длины
    public double getLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double getLength(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    //Скорость объекта
    public void setLength(double L) {
        double currentLength = this.getLength();
        if (currentLength == 0) {
            this.set(L, 0);
        } else {
            this.multiply(1 / currentLength);

            this.multiply(L);
        }
    }

    //получение угла объекта
    public double getAngle() {
        if (this.getLength() == 0)
            return 0;
        else
            return Math.toDegrees(Math.atan2(this.y, this.x));
    }

    //Расчет угла движения объекта
    public void setAngle(double angleDegrees) {
        double L = this.getLength();
        double angleRadian = Math.toRadians(angleDegrees);
        this.x = L * Math.cos(angleRadian);
        this.y = L * Math.sin(angleRadian);
    }

    //Расчет угла между двумя векторами
    public double getAngle2Vectors(double x1, double y1, double x2, double y2) {
        x1 -= this.x;
        y1 -= this.y;
        x2 -= this.x;
        y2 -= this.y;
        double ma = getLength(x1, y1);
        double mb = getLength(x2, y2);
        double sc = (x1 * x2) + (y1 * y2);
        return Math.toDegrees(Math.acos(sc / (ma * mb)));
    }


}
