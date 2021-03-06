package com.airmap.airmapsdk.models.status.properties;

import com.airmap.airmapsdk.models.AirMapBaseModel;
import com.airmap.airmapsdk.util.Utils;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vansh Gandhi on 7/27/16.
 * Copyright © 2016 AirMap, Inc. All rights reserved.
 */
@SuppressWarnings("unused")
public class AirMapEmergencyProperties implements AirMapBaseModel, Serializable {

    private String agencyId;
    private Date effectiveDate;

    public AirMapEmergencyProperties(JSONObject emergencyJson) {
        constructFromJson(emergencyJson);
    }

    public AirMapEmergencyProperties() {

    }

    @Override
    public AirMapEmergencyProperties constructFromJson(JSONObject json) {
        if (json != null) {
            setAgencyId(json.optString("agency_id"));
            setEffectiveDate(Utils.getDateFromIso8601String(json.optString("date_effective")));
        }
        return this;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public AirMapEmergencyProperties setAgencyId(String agencyId) {
        this.agencyId = agencyId;
        return this;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public AirMapEmergencyProperties setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }
}
