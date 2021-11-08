package es.Punikii.MLGWars.countdown;

import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EndCountdown {

    public int secounds = 10;
    public int task;
    public boolean isRunning;

    public void startcountdown(Player player) {

        if(MLGWars.getInstance().getGameStates() == GameStates.ENDING) {

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
                        case 10: case 5: case 4: case 3: case 2:

                            Bukkit.broadcastMessage(MLGWars.prefix + "Der Server startet sich in §c" + secounds + " §7Sekunden neu!");
                            player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                            break;

                        case 1:
                            Bukkit.broadcastMessage(MLGWars.prefix + "Der Server startet in §ceiner §7Sekunde neu!");
                            player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                            break;

                        case 0:
                            Bukkit.broadcastMessage(MLGWars.prefix + "Der Server wurde neugestartet!");
                            Bukkit.shutdown();

                            MLGWars.getInstance().setGameStates(GameStates.INGAME);
                            System.out.println(MLGWars.getInstance().getGameStates());


                            Bukkit.getScheduler().cancelTask(task);

                            ItemStack Water = new ItemStack(Material.WATER_BUCKET);
                            ItemMeta WatMeta = Water.getItemMeta();
                            WatMeta.setDisplayName("§8» §bWaterMLG");
                            Water.setItemMeta(WatMeta);

                            for(Player all : Bukkit.getOnlinePlayers()) {
                                all.kickPlayer("");
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
            Bukkit.broadcastMessage("§cDer Countdown wurde gestoppt!");
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