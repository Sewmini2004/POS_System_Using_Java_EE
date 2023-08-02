getAllCustomersFromBackEnd();

$('#btnSaveCustomer').click(function (event) {
    cusSave($('#customerId').val(), $('#customerName').val(), $('#customerAddress').val(), $('#customerSalary').val());
});

function addCustomerTable() {
    $("#tblCustomer> tr").detach();
    for (var customer of customerAr) {
        console.log(customer);
        var row = `<tr><td>${customer.cusId}</td><td>${customer.cusName}</td><td>${customer.cusAddress}</td><td>${customer.cusSalary}</td></tr>`;
        $('#tblCustomer').append(row);
    }
    trCusSelector();
}

/*====Add Focus Event when user Click Enter====*/
$('#customerId').on('keydown', function (event) {

    if (event.key === "Enter" && check(cusIDRegEx, $("#customerId"))) {
        $("#customerName").focus();
    } else if (event.key === "ArrowUp") {
        $("#customerSalary").focus();
    }

});
$('#customerName').on('keydown', function (event) {

    if (event.key === "Enter" && check(cusNameRegEx, $("#customerName"))) {
        $("#customerAddress").focus();
    } else if (event.key === "ArrowUp") {
        $("#customerId").focus();
    }

});
$('#customerAddress').on('keydown', function (event) {

    if (event.key === "Enter" && check(cusAddressRegEx, $("#customerAddress"))) {
        $("#customerSalary").focus();
    } else if (event.key === "ArrowUp") {
        $("#customerName").focus();
    }

});
$('#customerSalary').on('keydown', function (event) {

    if (event.key === "Enter" && check(cusSalaryRegEx, $("#customerSalary"))) {
        let res = confirm("Do you want to add this customer.?");
        if (res) {
            cusSave($('#customerId').val(), $('#customerName').val(), $('#customerAddress').val(), $('#customerSalary').val());
        }

    } else if (event.key === "ArrowUp") {
        $("#customerAddress").focus();
    }

});

//GetAllCustomers from backend
function getAllCustomersFromBackEnd() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/JavaEE_Servlet_Back_End_Pos_war_exploded/customer",
        success: function (resp) {
            customerAr = resp.data;
            console.log("Get all request success");
            addCustomerTable();
            loadAllCustomerId();

            //set today
            $("#OrderDate").val(new Date().toISOString().slice(0, 10));
        },
        error: function (err) {
            console.log("Get all request failed");
        }
    });
}


/*save Customer*/
function cusSave(customerID, customerName, customerAddress, customerSalary) {
    let customerModal = new CustomerModal(customerID, customerName, customerAddress, customerSalary);
    $.ajax({
        method: "POST",
        url: "http://localhost:8080/JavaEE_Servlet_Back_End_Pos_war_exploded/customer",
        data: JSON.stringify(customerModal),
        contentType: "application/json",
        success: function (resp) {
            console.log("Save request success");

            /*Double click to remove*/
            getAllCustomersFromBackEnd();
            dblClickCusDelete();
            loadAllCustomerId();
            clearAllCusData();
        },
        error: function (err) {
            console.log("Save request failed");
        }
    })

}

/*Search Customer*/
$('#btnSearchButton').click(function () {

    for (let customerKey of customerAr) {

        //check the ComboBox Id Equal
        console.log($('#cusCombo').val());

        if ($('#cusCombo').val() === "ID") {
            //check Id
            // alert(customerKey.id+"=="+$('#inputCusSearch').val());

            if (customerKey.cusId === $('#inputCusSearch').val()) {
                $('#cId').val(customerKey.cusId);
                $('#cName').val(customerKey.cusName);
                $('#cSalary').val(customerKey.cusSalary);
                $('#cAddress').val(customerKey.cusAddress);
            }
        } else if ($('#cusCombo').val() === "1") {
            //check Name
            if (customerKey.cusName === $('#inputCusSearch').val()) {
                $('#cId').val(customerKey.cusId);
                $('#cName').val(customerKey.cusName);
                $('#cSalary').val(customerKey.cusSalary);
                $('#cAddress').val(customerKey.cusAddress);
            }
        }
    }

});


/*Double Click delete*/
function dblClickCusDelete() {
    $("#tblCustomer>tr").dblclick(function () {
        deleteCustomer($(this).children(':eq(0)').text());
        $(this).remove();
        addCustomerTable();
    });
}


/*When the table click set data to the field*/
function trCusSelector() {

    $("#tblCustomer>tr").click(function () {
        let id = $(this).children(':eq(0)').text();
        let name = $(this).children(':eq(1)').text();
        let address = $(this).children(':eq(2)').text();
        let salary = $(this).children(':eq(3)').text();

        console.log(id + "  " + name + "  " + address + " " + salary);

        $('#cId').val(id);
        $('#cName').val(name);
        $('#cAddress').val(address);
        $('#cSalary').val(salary);

    });

}

/*for Delete Customer*/
$("#btnCusDelete").click(function () {
    let delID = $("#cId").val();

    let option = confirm("Do you really want to delete customer id :" + delID);
    if (option) {
        if (deleteCustomer(delID)) {
            $.ajax({
                url: "http://localhost:8080/JavaEE_Servlet_Back_End_Pos_war_exploded/customer?customerId=".concat(delID),
                method: "DELETE",
                success: function (res) {
                    console.log(res);
                    if (res.status == 200) {
                        alert(res.message);
                        clearAllCusData();
                    } else if (res.status == 400) {
                        alert(res.data);
                    }

                },
                error: function (ob, status, t) {
                    console.log(ob);
                    console.log(status);
                    console.log(t);


                }
            });


        }
    }
});

function searchCustomer(cusID) {
    for (let customer of customerAr) {
        if (customer.cusId == cusID) {
            return customer;
        }
    }
    return null;
}


function deleteCustomer(customerID) {
    let customer = searchCustomer(customerID);

    if (customer != null) {
        let indexNumber = customerAr.indexOf(customer);
        customerAr.splice(indexNumber, 1);
        addCustomerTable();
        return true;
    } else {
        return false;
    }
}

/*Update Customer*/
$("#btnCusUpdate").click(function () {
    let customerID = $("#cId").val();
    let customerName = $("#cName").val();
    let customerAddress = $("#cAddress").val();
    let customerSalary = $("#cSalary").val();

    let customerModal1 = new CustomerModal(customerID, customerName, customerAddress, customerSalary);
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, Update it!'
    }).then((result) => {
        if (result.isConfirmed) {
            updateCustomer(customerModal1);
        }
    })


});
function updateCustomer(customerModal) {
    let customer = searchCustomer(customerModal.cusId);
    if (customer != null) {
        $.ajax({
            method: "PUT",
            url: "http://localhost:8080/JavaEE_Servlet_Back_End_Pos_war_exploded/customer",
            data: JSON.stringify(customerModal),
            contentType: "application/json",
            success: function (resp) {
                if (resp.status == 200) { //process is ok

                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: 'Update success',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    getAllCustomersFromBackEnd();

                    $("#cId").val("");
                    $("#cName").val("");
                    $("#cAddress").val("");
                    $("#cSalary").val("");


                } else if (resp.status == 400) { //there is a problem with the client side
                    Swal.fire({
                        position: 'top-end',
                        icon: 'warning',
                        title: 'Update failed !',
                        showConfirmButton: false,
                        timer: 1500
                    })
                } else {   // else may be there is a exception
                    Swal.fire({
                        position: 'top-end',
                        icon: 'warning',
                        title: 'Update failed !',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            },
            error: function (err) {
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: 'Update failed !',
                    showConfirmButton: true,
                    timer: 1500
                })
            }
        })
        return true;
    } else {
        return false;
    }
}

/*Disable Tab*/
$("#customerId,#customerName,#customerAddress,#customerSalary").on('keydown', function (event) {
    if (event.key == "Tab") {
        event.preventDefault();
    }
});


/*For Validation*/


$("#customerId").focus();

// customer reguler expressions
const cusIDRegEx = /^(C)[0-9]{1,3}$/;
const cusNameRegEx = /^[A-z ]{1,20}$/;
const cusAddressRegEx = /^[0-9/A-z. ,]{4,}$/;
const cusSalaryRegEx = /^[0-9]{1,}[.]?[0-9]{1,2}$/;

let customerValidations = [];
customerValidations.push({reg: cusIDRegEx, field: $('#customerId'), error: 'Customer ID Pattern is Wrong : C00-001'});
customerValidations.push({
    reg: cusNameRegEx,
    field: $('#customerName'),
    error: 'Customer Name Pattern is Wrong : A-z 5-20'
});
customerValidations.push({
    reg: cusAddressRegEx,
    field: $('#customerAddress'),
    error: 'Customer Address Pattern is Wrong : A-z 0-9 ,/'
});
customerValidations.push({
    reg: cusSalaryRegEx,
    field: $('#customerSalary'),
    error: 'Customer Salary Pattern is Wrong : 100 or 100.00'
});


$("#customerId,#customerName,#customerAddress,#customerSalary").on('keyup', function (event) {
    checkCusValidity();
});

$("#customerId,#customerName,#customerAddress,#customerSalary").on('blur', function (event) {
    checkCusValidity();
});

function checkCusValidity() {
    let errorCount = 0;
    for (let validation of customerValidations) {
        if (checkCus(validation.reg, validation.field)) {
            textCusSuccess(validation.field, "");
        } else {
            errorCount = errorCount + 1;
            setCusTextError(validation.field, validation.error);
        }
    }
    setCusButtonState(errorCount);
}

function checkCus(regex, txtField) {
    let inputValue = txtField.val();
    return regex.test(inputValue) ? true : false;
}

function textCusSuccess(txtField, error) {
    if (txtField.val().length <= 0) {
        defaultCusText(txtField, "");
    } else {
        txtField.css('border', '2px solid green');
        txtField.parent().children('span').text(error);
    }
}

function setCusTextError(txtField, error) {
    if (txtField.val().length <= 0) {
        defaultCusText(txtField, "");
    } else {
        txtField.css('border', '2px solid red');
        txtField.parent().children('span').text(error);
    }
}

function defaultCusText(txtField, error) {
    txtField.css("border", "1px solid #ced4da");
    txtField.parent().children('span').text(error);
}

function setCusButtonState(value) {
    if (value > 0) {
        $("#btnSaveCustomer").attr('disabled', true);
    } else {
        $("#btnSaveCustomer").attr('disabled', false);
    }
}

$("#clearCus").click(function () {
    clearAllCusData();
});

function clearAllCusData() {
    $('#customerId').val("");
    $('#customerName').val("");
    $('#customerAddress').val("");
    $('#customerSalary').val("");

    $('#cId').val("");
    $('#cName').val("");
    $('#cSalary').val("");
    $('#cAddress').val("");
}