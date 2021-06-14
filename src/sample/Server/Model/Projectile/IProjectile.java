package sample.Server.Model.Projectile;

import sample.Server.Model.Sprite.ISprite;

public interface IProjectile extends ISprite{
    //Расчет следующей позиции
    void Position();

    //Движение к метеориту, проверка на попадание и выход за границу игры
    void MoveAndFireToTarget();

    //Обновление объекта
    void update(double deltaTime);

    boolean isMiss();

    boolean isDestroy();
}
