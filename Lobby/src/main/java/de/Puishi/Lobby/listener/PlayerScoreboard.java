package de.Puishi.Lobby.listener;

import de.Puishi.Lobby.Main;
import de.javaarray.pixeltitan.coinsapi.api.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 20.05.2020 / 10:21                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerScoreboard implements Listener {

    private static BukkitTask task;

    private static final HashMap<Scoreboard, Player> board = new HashMap<>();

    public void setScoreboard(Player p) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
        task(objective);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.getScore("§1").setScore(12);
        objective.getScore("  §8■ §7Name").setScore(11);
        objective.getScore("     §8● §a" + p.getName()).setScore(10);
        objective.getScore("§2").setScore(9);
        objective.getScore("  §8■ §7Rang").setScore(8);

        if (p.hasPermission("pixel.admin")) {
            objective.getScore("     §8● §4Administrator").setScore(7);
        } else if (p.hasPermission("pixel.srdev")) {
            objective.getScore("     §8● §bSrDeveloper").setScore(7);
        } else if (p.hasPermission("pixel.dev")) {
            objective.getScore("     §8● §bDeveloper").setScore(7);
        } else if (p.hasPermission("pixel.content")) {
            objective.getScore("     §8● §bContent").setScore(7);
        } else if (p.hasPermission("pixel.srmod")) {
            objective.getScore("     §8● §cSrModerator").setScore(7);
        } else if (p.hasPermission("pixel.mod")) {
            objective.getScore("     §8● §cModerator").setScore(7);
        } else if (p.hasPermission("pixel.jrmod")) {
            objective.getScore("     §8● §cJrModerator").setScore(7);
        } else if (p.hasPermission("pixel.srbuilder")) {
            objective.getScore("     §8● §3SrBuilder").setScore(7);
        } else if (p.hasPermission("pixel.builder")) {
            objective.getScore("     §8● §3Builder").setScore(7);
        } else if (p.hasPermission("pixel.youtube")) {
            objective.getScore("     §8● §5YouTuber").setScore(7);
        } else if (p.hasPermission("pixel.pixel")) {
            objective.getScore("     §8● §aPixel").setScore(7);
        } else if(p.hasPermission("pixel.premium")) {
            objective.getScore("     §8● §6Premium").setScore(7);
        } else {
            objective.getScore("     §8● §7Spieler").setScore(7);
        }


        objective.getScore("§9").setScore(6);
        objective.getScore("  §8■ §7Teamspeak").setScore(5);
        objective.getScore("     §8● §bPixelTitan.net").setScore(4);
        objective.getScore("§4").setScore(3);
        objective.getScore("  §8■ §7Coins").setScore(2);

        Team team = scoreboard.registerNewTeam("x8");
        team.setPrefix("§8");
        team.setSuffix("     §8● §e" + CoinsAPI.getCoins(p.getUniqueId().toString()));
        team.addEntry("§3");
        objective.getScore("§3").setScore(1);

        objective.getScore("§5").setScore(0);
        p.setScoreboard(scoreboard);
        board.put(scoreboard, p);
    }

    public void update() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Scoreboard scoreboard : board.keySet()) {
                    Player player = board.get(scoreboard);
                    scoreboard.getTeam("x8").setSuffix("     §8● §e" + CoinsAPI.getCoins(player.getUniqueId().toString()));
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
    }

    public static void task(Objective obj) {

        task = Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                obj.setDisplayName("§8»");
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        obj.setDisplayName("§8» §6P");
                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                obj.setDisplayName("§8» §6Pi");
                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        obj.setDisplayName("§8» §6Pix");
                                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                            @Override
                                            public void run() {
                                                obj.setDisplayName("§8» §6Pixe");
                                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        obj.setDisplayName("§8» §6Pixel");
                                                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                obj.setDisplayName("§8» §6PixelT");
                                                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        obj.setDisplayName("§8» §6PixelTi");
                                                                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                obj.setDisplayName("§8» §6PixelTit");
                                                                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        obj.setDisplayName("§8» §6PixelTita");
                                                                                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                            @Override
                                                                                            public void run() {
                                                                                                obj.setDisplayName("§8» §6PixelTitan");
                                                                                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                                    @Override
                                                                                                    public void run() {
                                                                                                        obj.setDisplayName("§8» §6PixelTitan ");
                                                                                                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                                            @Override
                                                                                                            public void run() {
                                                                                                                obj.setDisplayName("§8» §6PixelTitan §8■");
                                                                                                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                                                    @Override
                                                                                                                    public void run() {
                                                                                                                        obj.setDisplayName("§8» §6PixelTitan §8■ §7");
                                                                                                                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                                                            @Override
                                                                                                                            public void run() {
                                                                                                                                obj.setDisplayName("§8» §6PixelTitan §8■ §7L");
                                                                                                                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                                                                    @Override
                                                                                                                                    public void run() {
                                                                                                                                        obj.setDisplayName("§8» §6PixelTitan §8■ §7Lo");
                                                                                                                                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                                                                            @Override
                                                                                                                                            public void run() {
                                                                                                                                                obj.setDisplayName("§8» §6PixelTitan §8■ §7Lob");
                                                                                                                                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                                                                                    @Override
                                                                                                                                                    public void run() {
                                                                                                                                                        obj.setDisplayName("§8» §6PixelTitan §8■ §7Lobb");
                                                                                                                                                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                                                                                            @Override
                                                                                                                                                            public void run() {
                                                                                                                                                                obj.setDisplayName("§8» §6PixelTitan §8■ §7Lobby");
                                                                                                                                                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                                                                                                    @Override
                                                                                                                                                                    public void run() {
                                                                                                                                                                        obj.setDisplayName("§8» §6PixelTitan §8■ §7Lobby");
                                                                                                                                                                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                                                                                                                                                            @Override
                                                                                                                                                                            public void run() {
                                                                                                                                                                                task(obj);
                                                                                                                                                                            }
                                                                                                                                                                        }, 10);
                                                                                                                                                                    }
                                                                                                                                                                }, 10);
                                                                                                                                                            }
                                                                                                                                                        }, 10);
                                                                                                                                                    }
                                                                                                                                                }, 10);
                                                                                                                                            }
                                                                                                                                        }, 10);
                                                                                                                                    }
                                                                                                                                }, 10);
                                                                                                                            }
                                                                                                                        }, 10);
                                                                                                                    }
                                                                                                                }, 10);
                                                                                                            }
                                                                                                        }, 10);
                                                                                                    }
                                                                                                }, 10);
                                                                                            }
                                                                                        }, 10);
                                                                                    }
                                                                                }, 10);
                                                                            }
                                                                        }, 10);
                                                                    }
                                                                }, 10);
                                                            }
                                                        }, 10);
                                                    }
                                                }, 10);
                                            }
                                        }, 10);
                                    }
                                }, 10);
                            }
                        }, 10);
                    }
                }, 10);
            }
        }, 1);
    }
}
