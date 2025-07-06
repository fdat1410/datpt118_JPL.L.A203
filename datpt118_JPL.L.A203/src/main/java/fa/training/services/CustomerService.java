package fa.training.services;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.utils.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    public List<Customer> createCustomer() {
        return new ArrayList<>();
    }

    // Ghi đè toàn bộ danh sách vào file (object file)
    public String save(List<Customer> customers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.DATA_FILE))) {
            oos.writeObject(customers);
            return "Saved successfully!";
        } catch (IOException e) {
            return "Save failed: " + e.getMessage();
        }
    }

    // Đọc toàn bộ danh sách từ file (object file)
    public List<Customer> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.DATA_FILE))) {
            return (List<Customer>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // Hiển thị danh sách (theo định dạng đề bài)
    public void display(List<Customer> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No customer data!");
            return;
        }
        System.out.println("------ LIST OF CUSTOMER ------");
        for (Customer c : list) {
            System.out.printf("Name: %-10s | Phone: %-12s | Address: %-10s | Orders: %s\n",
                    c.getName(), c.getPhone(), c.getAddress(), c.getOrderList());
        }
    }

    // Tìm khách hàng theo phone (exact), trả về danh sách (dù thông thường là 1)
    public List<Customer> search(String phone) {
        List<Customer> result = new ArrayList<>();
        List<Customer> all = findAll();
        for (Customer c : all) {
            if (c.getPhone().equals(phone)) result.add(c);
        }
        return result;
    }

    // Xóa khách hàng theo phone (trả về true nếu xóa được)
    public boolean remove(String phone) {
        List<Customer> all = findAll();
        boolean removed = all.removeIf(c -> c.getPhone().equals(phone));
        if (removed) save(all);
        return removed;
    }
}
