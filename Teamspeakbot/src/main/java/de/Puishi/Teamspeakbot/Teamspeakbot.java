package de.Puishi.Teamspeakbot;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import de.Puishi.Teamspeakbot.events.Events;

import java.util.logging.Level;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 04.07.2020 / 15:06                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Teamspeakbot {

    public static Teamspeakbot instance;
    public static final TS3Config config = new TS3Config();
    public static final TS3Query query = new TS3Query(config);
    public static final TS3Api api = query.getApi();

    public void main(String[] args) {
        instance = this;
        config.setHost("pixeltitan.net");
        config.setDebugLevel(Level.ALL);
        query.connect();
        api.setNickname("Testbot by Puishi");
        Events.loadEvents();
    }

    public static Teamspeakbot getInstance() {
        return instance;
    }
}
