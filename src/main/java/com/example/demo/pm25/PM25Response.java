package com.example.demo.pm25;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PM25Response {

    private Data data;

    public PM25Response() {
    }

    public PM25Response(int aqi) {
        data = new Data();
        data.setAqi(aqi);
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getAqi() {
        return String.valueOf(this.data.getAqi());
    }

    public static class Data {
        private int aqi;

        public int getAqi() {
            return aqi;
        }

        public void setAqi(int aqi) {
            this.aqi = aqi;
        }
    }
}
