package com.marshmallow.android.model;

import com.marshmallow.android.model.asset.AssetInterface;
import com.marshmallow.android.model.asset.SpeedBump;
import com.marshmallow.android.model.career.Career;
import com.marshmallow.android.model.education.Education;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by George on 11/22/2018.
 */
public class MarshmallowUser {
    private String name;
    private Vector<AssetInterface> assets;
    private HashMap<String, Education> education;
    private Career career;
    private Integer savings;

    public MarshmallowUser() {
        assets = new Vector<>();
        education = new HashMap<>();
        savings = 0;
        career = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<AssetInterface> getAssets() {
        return assets;
    }

    public void addAsset(AssetInterface assetInterface) {
        assetInterface.setOwned(true);
        assets.add(assetInterface);
        savings -= assetInterface.getInitialCost();
    }

    public void setAssets(Vector<AssetInterface> assets) {
        this.assets = assets;
    }

    public HashMap<String, Education> getEducation() {
        return education;
    }

    public void addEducation(Education education) {
        this.education.put(education.getDegreeTitle(), education);
        savings -= education.getCost();
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public int getSavings() {
        return savings;
    }

    public void setSavings(int savings) {
        this.savings = savings;
    }

    // Returns boolean indicating a speed bump occurred
    public boolean applySpeedBumps() {
        boolean speedBumpApplied = false;
        for (AssetInterface asset : assets) {
            SpeedBump speedBump = asset.checkForSpeedBump();
            if (speedBump != null) {
                speedBumpApplied = true;
                savings -= speedBump.getCost();
            }
        }

        return speedBumpApplied;
    }

    public synchronized void applyMonthlyUpdates() {
        for (AssetInterface asset : assets) {
            savings -= asset.applyMonthlyCosts();
        }

        if (career != null) {
            savings += career.getPayCheck();
        }
    }

    public synchronized void clearUserData() {
        name = null;
        assets = new Vector<AssetInterface>();
        education = null;
        career = null;
        savings = 0;
    }
}
