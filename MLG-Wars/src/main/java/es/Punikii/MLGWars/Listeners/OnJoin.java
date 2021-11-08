package es.Punikii.MLGWars.Listeners;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import es.Punikii.MLGWars.Locations.LocationManager;
import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.countdown.LobbyCountdown;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

public class OnJoin implements Listener {

    public static ArrayList<Player> build = new ArrayList<Player>();

    public MLGWars plugin;

    public OnJoin(MLGWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        LocationManager locationManager = new LocationManager();
        Player player = event.getPlayer();

        event.setJoinMessage(MLGWars.prefix + "Der Spieler §c" + event.getPlayer().getName() + " §7hat das Spiel betreten!");

        MLGWars.playlist.add(player.getName());

        try {
            player.teleport(locationManager.getLocation("Spawn"));
        } catch (Exception ex) {
            player.sendMessage(MLGWars.prefix + "Bitte Setzt den Spawn und die Positionen§8!");
        }

        if (MLGWars.getInstance().getGameStates() == GameStates.LOBBY) {
            if (MLGWars.playlist.size() == 2) {
                new LobbyCountdown().startcountdown(player);
            }
            if (MLGWars.playlist.size() == 4) {
                player.kickPlayer("Runde ist schon voll!");
            }
        }

        player.getInventory().setItem(8, getSkull("http://textures.minecraft.net/texture/19bf3292e126a105b54eba713aa1b152d541a1d8938829c56364d178ed22bf",
                "§8» §cVerlassen"));

        player.getInventory().clear();
        player.setHealth(20D);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().setArmorContents(null);
    }

    private ItemStack getSkull(String skinURL, String displayname) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        if(skinURL.isEmpty())return head;

        ItemMeta headMeta = head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", skinURL).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(headMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        headMeta.setDisplayName(displayname);

        head.setItemMeta(headMeta);
        return head;
    }

}
