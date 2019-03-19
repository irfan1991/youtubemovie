package com.andaratech.youtobeapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    @SerializedName("results")
    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public class Results{
        @SerializedName("id")
        private int id;
        @SerializedName("title")
        private String title;
        @SerializedName("backdrop_path")
        private String backdrop_path;
        @SerializedName("overview")
        private String overview;
        @SerializedName("release_date")
        private String realease_date;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRealease_date() {
            return realease_date;
        }

        public void setRealease_date(String realease_date) {
            this.realease_date = realease_date;
        }
    }

}
