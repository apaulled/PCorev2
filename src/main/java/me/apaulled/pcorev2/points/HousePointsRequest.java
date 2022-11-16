package me.apaulled.pcorev2.points;

import me.apaulled.pcorev2.pwapi.PWApiHandler;

import java.io.IOException;

public class HousePointsRequest {
    private String rawJsonText;
    private PWApiHandler api;

    public HousePointsRequest() {
        this.api = getApiHandler();
        this.rawJsonText = this.api.getResponseString();
    }

    private PWApiHandler getApiHandler() {
        PWApiHandler apiHandler;
        try {
            apiHandler = new PWApiHandler("https://api.potterworldmc.com/housepoints");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return apiHandler;
    }

    public String getRawJsonText() {
        return this.rawJsonText;
    }
}
