/*
function customerModal(customerID, customerName, customerAddress, customerSalary) {
    var customer = {
        cusId: customerID,
        cusName: customerName,
        cusAddress: customerAddress,
        cusSalary: customerSalary
    }
    customerAr.push(customer);
}
*/


class CustomerModal {

    constructor(customerID, customerName, customerAddress, customerSalary) {
        this.cusId = customerID;
        this.cusName = customerName;
        this.cusAddress = customerAddress;
        this.cusSalary = customerSalary;
    }

}