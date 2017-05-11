package com.airmap.airmapsdk.models.airspace;

import android.support.annotation.ColorRes;
import android.text.TextUtils;

import com.airmap.airmapsdk.R;
import com.airmap.airmapsdk.models.AirMapBaseModel;
import com.airmap.airmapsdk.models.Coordinate;
import com.airmap.airmapsdk.models.permits.AirMapAvailablePermit;
import com.airmap.airmapsdk.models.permits.AirMapPermitIssuer;
import com.airmap.airmapsdk.models.status.AirMapAdvisory;
import com.airmap.airmapsdk.models.status.AirMapStatusAdvisory;
import com.airmap.airmapsdk.models.status.AirMapStatusWeather;
import com.airmap.airmapsdk.networking.services.MappingService;
import com.airmap.airmapsdk.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vansh Gandhi on 6/15/16.
 * Copyright © 2016 AirMap, Inc. All rights reserved.
 */
@SuppressWarnings("unused")
public class AirMapAirspaceAdvisoryStatus implements Serializable, AirMapBaseModel {
    public enum StatusColor {
        Red("red"), Yellow("yellow"), Green("green"), Orange("orange");

        private final String text;

        StatusColor(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public static StatusColor fromString(String text) {
            switch (text) {
                case "red":
                    return Red;
                case "yellow":
                    return Yellow;
                case "green":
                    return Green;
                case "orange":
                    return Orange;
                default:
                    return null;
            }
        }

        public @ColorRes
        int getColorRes() {
            switch (this) {
                case Red:
                    return R.color.status_red;
                case Orange:
                    return R.color.status_orange;
                case Yellow:
                    return R.color.status_yellow;
                default:
                case Green:
                    return R.color.status_green;
            }
        }
    }

    private StatusColor advisoryColor;
    private List<AirMapAdvisory> advisories;

    /**
     * Initialize an AirMapStatus from JSON
     *
     * @param statusJson A JSON representation of an AirMapStatus
     */
    public AirMapAirspaceAdvisoryStatus(JSONObject statusJson) {
        constructFromJson(statusJson);
    }

    /**
     * Initialize an AirMapStatus with default values
     */
    public AirMapAirspaceAdvisoryStatus() {

    }

    @Override
    public AirMapAirspaceAdvisoryStatus constructFromJson(JSONObject json) {
        if (json != null) {
            List<AirMapAdvisory> advisories = new ArrayList<>();
            JSONArray advisoriesJson = json.optJSONArray("advisories");
            for (int i = 0; advisoriesJson != null && i < advisoriesJson.length(); i++) {
                advisories.add(new AirMapAdvisory(advisoriesJson.optJSONObject(i)));
            }
            setAdvisories(advisories);
            setAdvisoryColor(StatusColor.fromString(json.optString("color")));
        }
        return this;
    }

    public StatusColor getAdvisoryColor() {
        return advisoryColor;
    }

    public AirMapAirspaceAdvisoryStatus setAdvisoryColor(StatusColor advisoryColor) {
        this.advisoryColor = advisoryColor;
        return this;
    }

    public List<AirMapAdvisory> getAdvisories() {
        return advisories;
    }

    public AirMapAirspaceAdvisoryStatus setAdvisories(List<AirMapAdvisory> advisories) {
        this.advisories = advisories;
        return this;
    }
}
