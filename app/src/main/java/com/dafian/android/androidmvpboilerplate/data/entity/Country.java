package com.dafian.android.androidmvpboilerplate.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Dafian on 13/03/18
 */
public class Country extends RealmObject implements Serializable {

    @PrimaryKey
    @SerializedName("cioc")
    @Expose
    private String cioc;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("alpha2Code")
    @Expose
    private String alpha2Code;

    @SerializedName("alpha3Code")
    @Expose
    private String alpha3Code;

    @SerializedName("capital")
    @Expose
    private String capital;

    @SerializedName("region")
    @Expose
    private String region;

    @SerializedName("subregion")
    @Expose
    private String subRegion;

    @SerializedName("population")
    @Expose
    private Integer population;

    @SerializedName("area")
    @Expose
    private Double area;

    @SerializedName("nativeName")
    @Expose
    private String nativeName;

    @SerializedName("numericCode")
    @Expose
    private String numericCode;

    @SerializedName("flag")
    @Expose
    private String flag;

    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
