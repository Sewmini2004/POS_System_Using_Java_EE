/*
function tempCartModal(id,itemCode,order_id,itmPrice,itemOrderQty,total) {
    var tempOrder={
        orid:id,
        orItemCOde:itemCode,
        ororder_id:order_id,
        orItemPrice:itmPrice,
        orItemQTY:itemOrderQty,
        orItemTotal:total
    }
    tempOrderCartAr.push(tempOrder);
}
*/


class TempCartModal {
    constructor(itemCode,orItemName,order_id,itmPrice,itemOrderQty,total) {
       /*     this.orid = id,*/
            this.orItemCOde = itemCode;
            this.orItemName=orItemName;
            this.ororder_id = order_id;
            this.orItemPrice = itmPrice;
            this.orItemQTY = itemOrderQty;
            this.orItemTotal = total;
    }
}