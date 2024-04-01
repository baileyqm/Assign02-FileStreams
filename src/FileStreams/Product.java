package FileStreams;

import java.io.Serializable;

public class Product implements Serializable{
    private String ID;
    private String name;
    private String description;
    private double cost;

    public Product(String ID, String name, String description, double cost) {
        this.ID = ID.trim();
        this.name = name.trim();
        this.description = description.trim();
        this.cost = cost;
    }

    //Getters and Setters and ToString Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override
    public String toString() {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", Name='" + name + '\'' +
                ", Description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }

    //Unique Methods

    public String toCSVDataRecord(){
        return this.ID + ", " + this.name + ", " + this.description + ", " + cost;
    }

    public String toJSONRecord()
    {
        String retString = "";
        char DQ = '\u0022';
        retString =  "{" + DQ + "IDNum" + DQ + ":" + DQ + this.ID + DQ + ",";
        retString += DQ + "name" + DQ + ":" + DQ + this.name + DQ + ",";
        retString += " " + DQ + "description"  + DQ + ":" + DQ + this.description + DQ + ",";
        retString += " " + DQ + "cost"  + DQ + ":" + this.cost + "}";

        return retString;
    }

    public String toXMLRecord()
    {
        String retString = "";

        retString = "<Product>" + "<ID>" + this.ID + "</ID>";
        retString += "<name>" + this.name + "</name>";
        retString += "<description>" + this.description + "</description>";
        retString += "<cost>" + this.cost + "</cost></Product>";

        return retString;
    }



}
