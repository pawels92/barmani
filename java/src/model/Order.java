package model;

public class Order {
    private String date;
    private String time;
    private Integer guests;
    private String bar;
    private String additional;
    private String address;
    private String client;

    public Order() {
    }

    public Order(String date, String time, Integer guests, String bar, String additional, String address, String client) {
        this.date = date;
        this.time = time;
        this.guests = guests;
        this.bar = bar;
        this.additional = additional;
        this.address = address;
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
