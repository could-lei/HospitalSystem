package com.example.demo.enums;

import java.util.List;

/**
 * Created by next on 2018/12/23.
 * @author next
 */
public class Detail {
    private Double total;
    private List<Item> items;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
