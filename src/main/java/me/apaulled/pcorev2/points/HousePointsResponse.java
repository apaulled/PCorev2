package me.apaulled.pcorev2.points;

import me.apaulled.pcorev2.houses.House;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HousePointsResponse {
    private Map<House, String> housePointsMap;

    public HousePointsResponse(String rawJsonText) {
        JSONObject housePoints = new JSONObject(rawJsonText).getJSONObject("housepoints");
        this.housePointsMap = new HashMap<>();

        for (House house : House.values()) {
            this.housePointsMap.put(house, (String.valueOf(housePoints.getInt(String.valueOf(house).toLowerCase()))));
        }
    }

    public String getHousePoints(House house) {
        return this.housePointsMap.get(house);
    }

    public Map<House, String> getHousePointsMap() {
        return this.housePointsMap;
    }
}
