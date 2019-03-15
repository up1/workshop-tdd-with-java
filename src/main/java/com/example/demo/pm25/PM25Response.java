package com.example.demo.pm25;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PM25Response that = (PM25Response) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "PM25Response{" +
                "data=" + data +
                '}';
    }

    public static class Data {
        private int aqi;

        public int getAqi() {
            return aqi;
        }

        public void setAqi(int aqi) {
            this.aqi = aqi;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return aqi == data.aqi;
        }

        @Override
        public int hashCode() {
            return Objects.hash(aqi);
        }

        @Override
        public String toString() {
            return "Data{" +
                    "aqi=" + aqi +
                    '}';
        }
    }
}
