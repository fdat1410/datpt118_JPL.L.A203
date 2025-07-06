package fa.training.entities;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {
    private String name;
    private String phone;
    private String address;
    private List<Order> orderList;

    public Customer() {
    }

    public Customer(String name, String phone, String address, List<Order> orderList) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.orderList = orderList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", orderList=" + orderList +
                '}';
    }
}
