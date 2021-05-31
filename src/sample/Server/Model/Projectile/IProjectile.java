package sample.Server.Model.Projectile;

import sample.Server.Model.Sprite.ISprite;

public interface IProjectile {
    //Расчет следующей позиции
    void Position();

    //Движение к метеориту, проверка на попадание и выход за границу игры
    void MoveAndFireToTarget();

    //Получение угла поворота снаряда
    double getAngleToTarget(ISprite other);

    //Обновление объекта
    void update(double deltaTime);

    boolean isMiss();

    boolean isDestroy();

    ISprite getBasic();
}
