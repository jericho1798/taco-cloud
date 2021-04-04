package vsb.tacocloud.data;

import vsb.tacocloud.Order;

public interface OrderRepository {

    Order save(Order order);
}
