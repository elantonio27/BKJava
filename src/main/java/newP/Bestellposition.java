package newP;

import com.fasterxml.jackson.annotation.JsonProperty;

//(rid BLOB PRIMARY KEY,bid BLOB,tableno INT,drink TEXT,price REAL,validation TEXT)
public class Bestellposition {
    @JsonProperty(value="rid")
    private String rid;
    @JsonProperty(value="bid")
    private String bid;
    @JsonProperty(value="tableno")
    private int tableno;
    @JsonProperty(value="drink")
    private String drink;
    @JsonProperty(value="price")
    private double price;
    @JsonProperty(value="validation")
    private String validation;

    public String getRid() {
        return rid;
    }
    public void setRid(String rid) {
        this.rid = rid;
    }
    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }
    public int getTableno() {
        return tableno;
    }
    public void setTableno(int tableno) {
        this.tableno = tableno;
    }
    public String getDrink() {
        return drink;
    }
    public void setName(String drink) {
        this.drink = drink;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getValidation() {
        return validation;
    }
    public void setValidation(String validation) {
        this.validation = validation;
    }

    public Bestellposition()
    {

    }
    //rid BLOB PRIMARY KEY,bid BLOB,tableno INT,drink TEXT,price REAL,validation TEXT
    public  Bestellposition(String rid, String bid, int tableno, String drink, double price, String validation) {
        super();
        this.rid = rid;
        this.bid = bid;
        this.tableno = tableno;
        this.drink = drink;
        this.price = price;
        this.validation = validation;
    }

    @Override
    public String toString() {
        return   tableno + " " + drink + " " + price + " " + validation;
    }


}
