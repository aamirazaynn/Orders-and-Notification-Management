package com.example.order_management.entities;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Map;

@Setter
@Getter
public class OrderPrinter {
    private String orderId;
    private boolean isShipped;
    private Map<String, ArrayList<ProductItem>> orderDetails;

}
