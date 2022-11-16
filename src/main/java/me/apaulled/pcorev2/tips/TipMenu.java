package me.apaulled.pcorev2.tips;

import me.apaulled.pcorev2.utils.YamlReader;
import java.util.ArrayList;
import java.util.Random;

public class TipMenu {
    private ArrayList<String> tipList;
    private int tipInterval;

    public TipMenu(String fileName) {
        this.tipList = this.getTipsFromFile(fileName);
        this.tipInterval = this.getIntervalFromFile(fileName);
    }

    private ArrayList<String> getTipsFromFile(String fileName) {
        YamlReader yamlReader = new YamlReader(fileName);
        return (ArrayList<String>) yamlReader.getValue("tips");
    }

    private int getIntervalFromFile(String fileName) {
        YamlReader yamlReader = new YamlReader(fileName);
        return ((Integer) yamlReader.getValue("tip_interval")) * 20;
    }

    public ArrayList<String> getTipList() {
        return this.tipList;
    }

    public int getTipInterval() {
        return this.tipInterval;
    }

    public String getRandomTip() {
        Random rand = new Random();
        String randomTip = this.tipList.get(rand.nextInt(this.tipList.size()));
        return randomTip;
    }

}
