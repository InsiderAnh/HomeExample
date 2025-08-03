package io.github.insideranh.examplehomes.data;

public class HomeData {

    private String name;
    private String location;

    public HomeData(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

}