package com.example.gk.dictionary;

import java.io.Serializable;

public class Medicine implements Serializable {
    String genericName;
    String brandName;
    String purpose;
    String deaSch;
    String special;
    String category;
    String studyTopic;
    // constructors
    public Medicine() {
    }
    public Medicine(String genericName, String brandName, String purpose, String deaSch, String special,
                    String category, String studyTopic) {
        this.genericName = genericName;
        this.brandName = brandName;
        this.purpose = purpose;
        this.deaSch = deaSch;
        this.special = special;
        this.category = category;
        this.studyTopic = studyTopic;
    }
    // getters
    public String getGenericName() {
        return genericName;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getDeaSch() {
        return deaSch;
    }

    public String getSpecial() {
        return special;
    }

    public String getCategory() {
        return category;
    }

    public String getStudyTopic() {
        return studyTopic;
    }

    // setters
    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setDeaSch(String deaSch) {
        this.deaSch = deaSch;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStudyTopic(String studyTopic) {
        this.studyTopic = studyTopic;
    }
}
