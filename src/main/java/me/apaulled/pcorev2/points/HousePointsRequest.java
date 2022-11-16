package me.apaulled.pcorev2.points;

import me.apaulled.pcorev2.apiaccess.APIGet;

import java.io.IOException;

public class HousePointsRequest {
    private String rawJsonText;
    private APIGet api;

    public HousePointsRequest() {
        this.api = getApiHandler();
        this.rawJsonText = this.api.getResponseString();
    }

    private APIGet getApiHandler() {
        APIGet apiHandler;
        try {
            apiHandler = new APIGet("https://api.potterworldmc.com/housepoints");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return apiHandler;
    }

    public String getRawJsonText() {
        return this.rawJsonText;
    }
}
