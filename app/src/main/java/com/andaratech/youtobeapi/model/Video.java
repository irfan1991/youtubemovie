package com.andaratech.youtobeapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Video {
    @SerializedName("results")
    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public class Results{
        @SerializedName("name")
        private String name;
        @SerializedName("key")
        private String key;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

}
