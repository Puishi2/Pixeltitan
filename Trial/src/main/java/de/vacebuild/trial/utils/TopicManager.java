package de.vacebuild.trial.utils;

import de.vacebuild.trial.main.Trialsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TopicManager {

    private Trialsystem plugin;
    private ArrayList<String> topics = new ArrayList<>();

    public TopicManager(Trialsystem plugin) {
        this.plugin = plugin;

        loadTopics();
    }

    private void loadTopics() {
        this.topics.addAll(Arrays.asList("Medieval", "Modern", "Fantasy", "Asia", "Sci-Fi", "Terrain", "Apocalypse", "B-Day",
                "D-Day", "Movement", "Sea-Life", "Atlantis", "Greek myth", "Religion", "Hell", "Heaven", "Earth", "Time",
                "Terror", "Rebellion", "Justice", "Survival", "Sailing", "Mayas", "Transport", "Airport", "Zoo", "Animal",
                "Monument", "Arabic", "Videogames", "Kingdom", "Steampunk", "Dragons", "19th century", "Cosmos", "Colosseum",
                "Stonehange", "Stone age", "Car", "Republic", "Star Wars", "Ships", "Sunset", "Nuclear energy", "Food",
                "Lake", "America", "Deams", "Addiction", "Prision", "Alien", "Civilisation", "Antarctica", "Wonders of world",
                "20th century", "India", "Future", "Battle", "Underground", "Cave", "Monsters", "Crypt", "Civil war",
                "Travel", "Town", "Disaster", "Wild west", "Winter", "Summer", "Autumn", "Spring", "Evolution", "Fishing",
                "Igloo", "Steampunk", "Vegetation", "Adventure", "Poison", "Secret", "Freedom", "Forest", "Technology", "View",
                "Sadness", "Happiness", "Polution", "Colours", "Fire", "Water", "Circus", "Gedanken", "Colorfull"));
    }

    public String getRandomTopic() {
        int index = new Random().nextInt(this.topics.size());

        return this.topics.get(index);
    }

}
