package sample.Server.Model.Projectile;

import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Player.IPlayer;

public class ProjectileFactory {

    public static IProjectile CreateInstance(IPlayer Player, char Letter, double BoxHeight, double BoxWidth, IMeteor meteor){
        return new Projectile(Player,Letter,BoxHeight,BoxWidth,meteor);
    }

}
