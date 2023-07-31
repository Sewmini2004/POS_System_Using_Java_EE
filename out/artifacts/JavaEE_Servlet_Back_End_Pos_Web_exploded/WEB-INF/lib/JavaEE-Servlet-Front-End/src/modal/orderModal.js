/*

function orderModal(orderId,orderDate,customer_id,discount,subTotal) {
    var order={
        orId:orderId,
        orDate:orderDate,
        orcustomer_id:customer_id,
        orDis:discount,
        orSubTotal:subTotal
    }
    orders.push(order);
}

*/

class OrderModal {
    order(orderId, orderDate, customer_id, discount, subTotal) {
        this.orId = orderId,
            this.orDate = orderDate,
            this.orcustomer_id = customer_id,
            this.orDis = discount,
            this.orSubTotal = subTotal
    }
}