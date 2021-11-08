package es.Punikii.MLGWars.countdown;

import es.Punikii.MLGWars.Listeners.ItemBuilder;
import es.Punikii.MLGWars.Listeners.Shop;
import es.Punikii.MLGWars.Lives.Lives;
import es.Punikii.MLGWars.Locations.LocationManager;
import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ShopCountdown {

    public int secounds = 60;
    public int task;
    public boolean isRunning;

    public void startcountdown(Player player) {

        if(MLGWars.getInstance().getGameStates() == GameStates.SHOP) {

            isRunning = true;

            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(MLGWars.getInstance(), new Runnable() {
                @Override
                public void run() {

                    secounds--;

                    for(Player all : Bukkit.getOnlinePlayers()) {
                        all.setLevel(secounds);
                        all.setExp(secounds / 60.0F);
                    }

                    switch (secounds) {
                        case 60: case 50: case 40: case 30: case 20: case 10: case 5: case 4: case 3: case 2:

                            Bukkit.broadcastMessage(MLGWars.prefix + "Du hast noch §c" + secounds + " §7Sekunden!");
                            player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                            break;

                        case 1:
                            Bukkit.broadcastMessage(MLGWars.prefix + "Du hast noch §ceine §7Sekunde!");
                            player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                            break;

                        case 0:
                            Bukkit.broadcastMessage(MLGWars.prefix + "Der Kampf beginnt §cjetzt§7!");

                            MLGWars.getInstance().setGameStates(GameStates.PVP);
                            System.out.println(MLGWars.getInstance().getGameStates());

                            Bukkit.getScheduler().cancelTask(task);

                            int i = 1;

                            for(Player all : Bukkit.getOnlinePlayers()) {
                                all.setFoodLevel(20);
                                all.setHealth(20);
                                all.teleport(LocationManager.getLocation("Pvp" + i));
                                i++;
                                Lives.lives.put(all, 3);
                            }

                            isRunning = false;

                            break;

                        default:
                            break;
                    }

                }
            }, 0, 20);

        }

    }

    public void stopcountdown(Player player) {
        if(isRunning) {
            isRunning = false;
            Bukkit.getScheduler().cancelTask(task);
            Bukkit.broadcastMessage("");
            secounds = 30;
        }
    }


    public int getSecounds() {
        return secounds;
    }

    public void setSecounds(int secounds) {
        this.secounds = secounds;
    }
}