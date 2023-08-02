getAllOrderss();

$('#btnSearchOrder').click(function () {

    for (var search of orders) {

        let searchOrder = $('#searchOrder').val();
        let chooseOrderType = $('#chooseOrderType').val();
        if (chooseOrderType === "ID") {
            console.log("ID : " + searchOrder + "===" + search.orId)

            if (searchOrder === search.orId) {
                $('#orderIdDash').val(search.orId);
                $('#OrderDateDash').val(search.orDate);
                $('#customerNameDash').val(search.orCusName);
                $('#discountDash').val(search.orDis);
                $('#subTotDash').val(search.orSubTotal);

            }
        } else if (chooseOrderType === "1") {
            console.log("1 : " + searchOrder + "===" + search.cusName)
            if (searchOrder === search.orCusName) {
                $('#orderIdDash').val(search.orId);
                $('#OrderDateDash').val(search.orDate);
                $('#customerNameDash').val(search.orCusName);
                $('#discountDash').val(search.orDis);
                $('#subTotDash').val(search.orSubTotal);
            }
        } else if (chooseOrderType === "2") {
            console.log("2 : " + searchOrder + "===" + search.ordDate)

            if (searchOrder === search.orDate) {
                $('#orderIdDash').val(search.orId);
                $('#OrderDateDash').val(search.orDate);
                $('#customerNameDash').val(search.orCusName);
                $('#discountDash').val(search.orDis);
                $('#subTotDash').val(search.orSubTotal);
            }
        }

    }
});

$('#btnClearOrd').click(function () {
    $('#orderIdDash').val("");
    $('#OrderDateDash').val("");
    $('#customerNameDash').val("");
    $('#discountDash').val("");
    $('#subTotDash').val("");
    $('#searchOrder').val("");
});

$('#btnDeleteOrd').click(function () {
    let deleteOrderId = $('#orderIdDash').val();

    deleteOrder(deleteOrderId)
});


/*FUNCTIONS*/

function searchOrder(orderId) {
    for (var i of orders) {
        if (i.orId === orderId) {
            return i;
        }
    }
    return null;
}

function deleteOrder(orderId) {
    let ordObj = searchOrder(orderId);


    if (ordObj != null) {
        $.ajax({
            method: "DELETE",
            url: "http://localhost:8080/JavaEE_Servlet_Back_End_Pos_war_exploded/order?orderId="+orderId,
            success: function (resp) {
                let indexNumber = orders.indexOf(ordObj);
                orders.splice(indexNumber, 1);
                loadAllOrder();
                alert("Order Successfully Deleted....");
                setOrderTextfieldValues("", "", "", "");
            },
            error: function (err) {
                alert(err.data);
            }
        });


    }else {
        alert("No such Order to delete. please check the id");
    }
}

function setOrderTextfieldValues(orderId, name, dis, cost) {

    $('#orderIdDash').val(orderId);

    $('#OrderDateDash').val(new Date().toISOString().slice(0, 10));
    $('#customerNameDash').val(name);
    $('#discountDash').val(dis);
    $('#subTotDash').val(cost);
}

function getAllOrderss() {
    $.ajax({
        url: "http://localhost:8080/JavaEE_Servlet_Back_End_Pos_war_exploded/order",
        method: "GET",
        success: function (res) {
            console.log(res);
            orders = res.data;
            console.log(res.data);
            loadAllOrder();
            blindOrderRowClickEvent();
        },
        error: function (ob, status, t) {

        }
    });

}