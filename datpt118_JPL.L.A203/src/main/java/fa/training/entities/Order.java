package fa.training.entities;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private String number;
    private Date date;

    public Order() {
    }

    public Order(String number, Date date) {
        this.number = number;
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Order[number=%s, date=%tF]", number, date);
    }
}
