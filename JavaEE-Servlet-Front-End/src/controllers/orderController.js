

function loadAllCustomerId() {
    $('#customerIdOrd').empty();
    for (let customerArElement of customerAr) {
        $('#customerIdOrd').append(`<option>${customerArElement.cusId}</option>`);
    }
}

function loadAllItemId() {
    $('#itemIdOrd').empty();
    for (let itemArElement of itemAr) {
        $('#itemIdOrd').append(`<option>${itemArElement.itemCode}</option>`);
    }
}


/*Listener fir the Customer Combo*/
$('#customerIdOrd').on('change',function (){
    /*get Customer*/
    let customer = searchCustomer($('#customerIdOrd').val());

    $('#customerNameOrd').val(customer.cusName);
    $('#salaryOrd').val(customer.cusSalary);
    $('#addressOrd').val(customer.cusAddress);


});

/*Listener fir the Item Combo*/
$('#itemIdOrd').on('change',function (){
    console.log($('#itemIdOrd').val());

    let item = searchItem($('#itemIdOrd').val());

    $('#item').val(item.itemName);
    $('#priceOrd').val(item.itemPrice);
    $('#qtyOnHandOrd').val(item.qtyOnHand);

});


$('#btnAddToCart').click(function (){

    let itemCode=$('#itemIdOrd').val();
    let order_id = $('#orderId').val();
    let itmPrice = $('#priceOrd').val();
    let itemName = $('#item').val();
    let itemOrderQty = $('#orderQty').val();

    let total =itmPrice*itemOrderQty;


    let rowExists = searchRowExists(itemCode);
    if(rowExists!=null){
        let newQty=((parseInt(rowExists.orItemQTY))+(parseInt(itemOrderQty)));

        // rowExists.orItemQTY.val(newQty);
        rowExists.orItemQTY=newQty;
        rowExists.orItemTotal=parseFloat(itmPrice)*newQty;
        addCartData();

    }else{
        let tempCartModal=new TempCartModal(itemCode,itemName,order_id,itmPrice,itemOrderQty,total);
        tempOrderCartAr.push(tempCartModal);
        addCartData();
    }

    minQty(itemCode,itemOrderQty);

})

/*Add Table*/
function addCartData() {
    $("#tblCart> tr").detach();

    for (var tc of tempOrderCartAr){
        var row="<tr><td>"+tc.orItemCOde+"</td><td>"+tc.orItemName+"</td><td>"+tc.orItemPrice+"</td><td>"+tc.orItemQTY+"</td><td>"+tc.orItemTotal+"</td></tr>";
        $('#tblCart').append(row);
    }
    trCusSelector();
    getTotal();
}

function getTotal() {
    let tempTot=0;
    for (let tempOrderCartArElement of tempOrderCartAr) {
        tempTot=tempTot+tempOrderCartArElement.orItemTotal;
    }
    $('#total').val(tempTot);

}

/*discount*/
let disTOGave=0;
$('#discount').on('keyup',function (){
    let dis=$('#discount').val();
    let tot=$('#total').val();
    var totMin=0;
    let subTot=0;

    console.log(dis+"=="+tot);
    totMin=parseFloat(tot)*(dis/100);
    console.log("dis Dis: "+totMin)

    subTot=tot-totMin;
    disTOGave=totMin;

    $('#subTotal').val(subTot);
})

/*Cash*/
$('#cash').on('keyup',function (){
    let cash=$('#cash').val();
    let subT=$('#subTotal').val();

    $('#balance').val((parseFloat(cash))-parseFloat(subT));
})

/*Remove Duplicate Row*/
function searchRowExists(itemCode) {
    for (let tempOr of tempOrderCartAr) {
        console.log(tempOr.orItemCOde+"-----"+itemCode);
        if(tempOr.orItemCOde===itemCode){
            return tempOr
        }
    }
    return null;
}

/*Min QTY*/
function minQty(itemCode,orderQty) {
    for (let itemArElement of itemAr) {
        if(itemArElement.itemCode===itemCode){
            itemArElement.qtyOnHand=parseInt(itemArElement.qtyOnHand)-parseInt(orderQty);
        }
    }
    addTable();
    clearData();
}

function clearData() {
    $('#qtyOnHandOrd').val("");
    $('#item').val("");
    $('#priceOrd').val("");
    $('#orderQty').val("");
}

/*Purchase Order*/
$('#purchaseOrder').click(function (){
    let orderId = $('#orderId').val();
    let orderDate = $('#OrderDate').val();
    let customer_id = $('#customerIdOrd').val();
    let discount = disTOGave;
    let subTotal = $('#subTotal').val();
    let orderModal = new OrderModal(orderId,orderDate,customer_id,discount,subTotal,tempOrderCartAr);

    $.ajax({
        method:"POST",
        url:"http://localhost:8080/JavaEE_Servlet_Back_End_Pos_war_exploded/order",
        data:JSON.stringify(orderModal),
        contentType:"application/json",
        success:function (resp) {

            loadAllOrder();
            blindOrderRowClickEvent();
            clearOrderTexts();

            for (var tempOrder of tempOrderCartAr){
                tempOrderCartAr.pop();
            }
            tempOrderCartAr.pop();
            addCartData();

            // console.log(orderArray);
        },
        error:function (err){

        }
    });


});

/*FUNCTIONS*/
function blindOrderRowClickEvent(){

    $('#tblOrder>tr').click(function (){
        let ordId = $(this).children(':eq(0)').text();
        $('#orderIdDash').val(ordId);
        let ordDate = $(this).children(':eq(1)').text();
        $('#OrderDateDash').val(ordDate);
        let ordCusId = $(this).children(':eq(2)').text();
        $('#customerIdDash').val(ordCusId);
        let ordDis = $(this).children(':eq(3)').text();
        $('#discountDash').val(ordDis);
        let ordCost = $(this).children(':eq(4)').text();
        $('#subTotDash').val(ordCost);
    });
}

function clearOrderTexts(){
    $('#orderId').val("");
    $('#OrderDate').val("");
    $('#customerNameOrd').val("");
    $('#salaryOrd').val("");
    $('#addressOrd').val("");

    $('#item').val("");
    $('#priceOrd').val("");
    $('#qtyOnHandOrd').val(0);
    $('#orderQty').val("");

    $('#cash').val("");
    $('#discount').val(0);
    $('#balance').val("");
    $('#subTotal').val(0);
}

function loadAllOrder(){
    $("#tblOrder> tr").detach();
    for (var i of orders){
        $('#tblOrder').append('<tr><td>'+i.orId+'</td>'+'<td>'+i.orDate+'</td>'+'<td>'+i.orcustomer_id+'</td>'+'<td>'+i.orDis+'</td>'+'<td>'+i.orSubTotal+'</td></tr>');
    }
}