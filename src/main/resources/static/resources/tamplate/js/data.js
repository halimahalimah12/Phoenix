"use script"
const url ='http://localhost:8081/api/v1';
(function (){
//    $('.alert-gagal').hide();
    $('.success-alert').hide();
    $('.close-totalIncome').click(function(event){
       location.reload();
    });
    submitAddNewInventory();
    submitAddAdmin();
    submitEditAdmin();
    submitAddChangePassword();
    getId();
    submitEditInventory();
    deleteInventory();
//    submitAddRoomInventory();
    deleteInventoryRoom();
    submitRoomService();
    totalIncome();
    updateRoomService();
    updateAdmin();
    showAlertSuccess();
}())

function updateAdmin(){
    $('.updateAdmin').click(function(event){
        event.preventDefault();
        let id = $(this).attr('data-id');

        $.ajax({
            method:"GET",
             url: `${url}/admin/${id}`,
             headers:{"Authorization" : `Bearer ${localStorage.getItem('token')}` },
             success: function(response){
                showDataAdmin(response);
             }
        })
    })
}
function showDataAdmin(response){
    $('#usernameAdminEdit').val(response.username);
    $('#jobTitleEdit').val(response.jobTitle);
}
function totalIncome(){
    $('.submitTotalCollection').click(function(event){

        event.preventDefault();
        let dto = collectionTotalIncome();
        console.log(dto);

        $.ajax({
            method:"POST",
            url:`${url}/reservation/totalIncome`,
            headers:{
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            data:JSON.stringify(dto),
            contentType:'application/JSON',
            success: function(response){
                console.log(response);
                let total = document.querySelector(".totalIn");
                total.innerHTML = `<h1>Rp. ${response.totalIncome}</h1>`
            }, error: function(response){
                writeValidationMessage({errors: response.responseJSON});
            }
        })
    })
}
function collectionTotalIncome(){
    let dto = {
        month:$('#month').val()
    }

    return dto;
}
function submitAddNewInventory(){
    $('.formInventory .submitInventory').click(function(event){
    console.log("abc")
     event.preventDefault();
        let dto = collectionFormAddNewInventory();
        $.ajax({
            method:"POST",
            url:`${url}/inventory`,
            headers:{
             'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            data:JSON.stringify(dto),
            contentType:'application/JSON',
            statusCode:{
               200: function (response) {
                    localStorage.setItem('successMessage','Inventory berhasil di input');
                    location.reload();
               },
               400: function(response){
                    writeValidationMessage({errors: response.responseJSON});
               }
            }
        })
    })

}
function collectionFormAddNewInventory(){
    let dto = {
        name: $('.formInventory #name').val(),
        stock: $('.formInventory #stock').val(),
        description: $('.formInventory #description').val()
    }
    return dto;
}
function getId(){
    $(document).on('click','.editInventory',function(event){
     event.preventDefault();
     let inventoryId = $(this).attr('data-id');

     $.ajax({
        method:"GET",
        url: `${url}/inventory/${inventoryId}`,
        headers:{"Authorization" : `Bearer ${localStorage.getItem('token')}` },
        success: function(response){
            showDataInventory(response);
        }
     })
    })


}
function showDataInventory(response){
    $('.formInventory #editName').val(response.name);
    $('.formInventory #editStock').val(response.stock);
    $('.formInventory #editDescription').val(response.description);
}
function collectionFormEditInventory(){
    let dto = {
        name: $('.formInventory #editName').val(),
        stock: $('.formInventory #editStock').val(),
        description: $('.formInventory #editDescription').val()
    }
    return dto;
}
function submitEditInventory(){
    $('.formInventory .submitEditInventory').click(function(event){
        event.preventDefault();
        let dto = collectionFormEditInventory();
        $.ajax({
            method:"PUT",
            url:`${url}/inventory/${dto.name}`,
            headers:{
             'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            data:JSON.stringify(dto),
            contentType:'application/JSON',
            success : function(response){
                localStorage.setItem('successMessage',response);
                location.reload();
            }, error: function(response){
                alert(response);
            }

        })
    })

}
function deleteInventory(){
    $('.deleteInventory').click(function(event){
         event.preventDefault();
              let inventoryId = $(this).attr('data-id');

              $.ajax({
                    method:"Delete",
                    url:`${url}/inventory/${inventoryId}`,
                    headers:{
                     'Authorization': `Bearer ${localStorage.getItem('token')}`
                    },
                    success : function(response){
                        localStorage.setItem('successMessage',response.pesan);
                        location.reload();
                    }, error: function(response){
                        alert(response);
                    }
              })
    })

}
function deleteInventoryRoom(){
    $('.deleteInventoryRoom').click(function(event){
         event.preventDefault();
              let inventoryId = $(this).attr('data-id');

              $.ajax({
                    method:"Delete",
                    url:`${url}/roomInventory/${inventoryId}`,
                    headers:{
                     'Authorization': `Bearer ${localStorage.getItem('token')}`
                    },
                    success : function(response){
                        localStorage.setItem('successMessage','Data Berhasil di delete');
                        location.reload();
                    }, error: function(response){
                     alert(response);
                    }
              })
    })

}

function writeValidationMessage(errorMessages) {
     $('.formInventory [data-for]').text('');
   let errors = errorMessages.errors
   Object.keys(errors).forEach((key, index) => {
        console.log(key+''+errors[key]);
        $(`.modal-content [data-for=${key}]`).text(errors[key]);
   });

}

function collectionFormAddNewAdmin(){
    let dto = {
        username : $('#usernameAdmin').val(),
        jobTitle : $('#jobTitle').val(),
        password : $('#password').val()
    }
    return dto;
}
function collectionFormEditAdmin(){
    let dto = {
        username : $('#usernameAdminEdit').val(),
        jobTitle : $('#jobTitleEdit').val()
    }
    return dto;
}
function submitEditAdmin(){
    $('.submitEditAdmin').click(function(event){
        event.preventDefault();
        let dto = collectionFormEditAdmin();
        console.log(dto);
        $.ajax({
            method:"PUT",
            url: `${url}/admin/edit/{dto.username}`,
            headers:{"Authorization" : `Bearer ${localStorage.getItem('token')}`},
            data: JSON.stringify(dto),
            contentType:'application/json',
            success: function(response){
                localStorage.setItem('successMessage',response);
                location.reload();
            }
        })

    })
}
function submitAddAdmin(){
    $('.submitAddAdmin').click(function(event){
        event.preventDefault();
        let dto = collectionFormAddNewAdmin();
        console.log(localStorage.getItem('token'));

        $.ajax({
            method: "POST",
            url: `${url}/admin`,
            headers:{"Authorization" : `Bearer ${localStorage.getItem('token')}`},
            data: JSON.stringify(dto),
            contentType:'application/json',
            success: function(response){
                localStorage.setItem('successMessage',response.message);
                location.reload();
            }
        })

    })
}
function collectionFormChangePassword(){
    let dto = {
        username: $('#username').val(),
        oldPassword:$('#oldPassword').val(),
        newPassword:$('#newPassword').val(),
        confirmPassword:$('#confirmPassword').val()
    }
    return dto;
}
function submitAddChangePassword(){
    $('.submitChangePassword').click(function(event){
        event.preventDefault();
        let dto = collectionFormChangePassword();
        console.log(dto);

        $.ajax({
            method:"PUT",
            url: `${url}/admin/${dto.username}`,
            headers:{"Authorization" : `Bearer ${localStorage.getItem('token')}`},
            data: JSON.stringify(dto),
            contentType:'application/json',
            success: function(response){
                localStorage.setItem('successMessage',response);
                location.reload();
            },error: function(response){
                alert(`ada error`);
            }
        })

    })
}
function showAlertSuccess(){
    let sukses = localStorage.getItem('successMessage');
    if(sukses){
        $('.success-alert').show().text(sukses);
    }
    localStorage.removeItem('successMessage');
}
//function submitAddRoomInventory(){
//    $('.submitInventory').click(function(event){
//
//       event.preventDefault();
//        let dto = collectionFormInventoryRoom();
//    console.log(dto);
//
//        $.ajax({
//            method:"POST",
//            headers:{
//                "Authorization" : `Bearer ${localStorage.getItem('token')}`
//            },
//            data: JSON.stringify(dto),
//            url: `${url}/roomInventory`,
//            contentType:'application/json',
//            success: function(response){
//                localStorage.setItem("successMessage",response);
//                location.reload();
//             }, error:function(response){
//              alert(response);
//            }
//        })
//
//    })
//}

//function collectionFormInventoryRoom(){
//    let dto = {
//        id : $('#id').val(),
//        roomNumber : $('#roomNumber').val(),
//        inventory: $('#inventory').val(),
//        quantity: $('#quantity').val()
//    }
//    return dto;
//}

function submitRoomService(){
    $('.submitRoomService').click(function(event){
        event.preventDefault();
        let dto = collectionFormRoomService();
        console.log(dto);
        $.ajax({
            method:"POST",
            url:`${url}/roomService`,
            data:JSON.stringify(dto),
            contentType:'application/json',
            headers:{'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            success:function(response){
                localStorage.setItem("successMessage",response);
                location.reload();
            },error:function(response){
                writeValidationMessage({errors: response.responseJSON})
            }
        })
    })
}

function collectionFormRoomService(){
    let dto = {
        employeeNumber: $('#employeeNumber').val(),
        firstName:$('#firstName').val(),
        middleName:$('#middleName').val(),
        lastName:$('#lastName').val(),
        company: $('#outsourcing').val()
    }
    return dto;
}

function updateRoomService(){
    $(document).on('click','.updateRoomService' ,function(event){
        event.preventDefault();
        let id = $(this).attr('data-id');
        $.ajax({
            method:"GET",
            url:`${url}/roomService/${id}`,
            headers:{
                "Authorization":`Bearer ${localStorage.getItem('token')}`
            },
            success:function(response){
                showDataRoomService(response);
            }
        })
    })
}

function showDataRoomService(response){
    $('#employeeNumber').prop('readonly', true);
    $('#employeeNumber').val(response.employeeNumber);
    $('#firstName').val(response.firstName);
    $('#middleName').val(response.middleName);
    $('#lastName').val(response.lastName);
    $('#outsourcing').val(response.company);
}



