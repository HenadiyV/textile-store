const updateAddressFromUser = document.getElementById("updateAddressFromUser");
const closeUpdateAddressFromUser = document.getElementById("closeUpdateAddressFromUser");
const updatePhoneFromUser = document.getElementById("updatePhoneFromUser");
const closeUpdatePfoneFromUser = document.getElementById("closeUpdatePfoneFromUser");
const updatePostOfficeFromUser = document.getElementById("updatePostOfficeFromUser");
const closeUpdatePostOfficeFromUser = document.getElementById("closeUpdatePostOfficeFromUser");


const upAddress_region = document.getElementById("upAddress_region");
const upAddress_district = document.getElementById("upAddress_district");
const upAddress_city = document.getElementById("upAddress_city");
const upAddress_address = document.getElementById("upAddress_address");
const upAddress_postCode = document.getElementById("upAddress_postCode");
const upAddress_active = document.getElementById("upAddress_active");
const upAddress_info = document.getElementById("upAddress_info");
const upAddress_id = document.getElementById("upAddress_id");

const upPhone_phone = document.getElementById("upPhone_phone");
const upPhone_active = document.getElementById("upPhone_active");
const upPhone_info = document.getElementById("upPhone_info");
const upPhone_id = document.getElementById("upPhone_id");



const upPostOffice_postOffice = document.getElementById("upPostOffice_postOffice");
const upPostOffice_active = document.getElementById("upPostOffice_active");
const upPostOffice_info = document.getElementById("upPostOffice_info");
const upPostOffice_id = document.getElementById("upPostOffice_id");

const upProfile_btn = document.getElementById("upProfile_btn");
const password = document.querySelector("input[name='password']");
const confirmPassword = document.querySelector("input[name='confirmPassword']");
const spanConfirmPassword = document.querySelector(".resultConfirm");

if(confirmPassword){

    confirmPassword.addEventListener("input",confirmMyPassword);
}else{
    console.log("not");
}


if(password){
    // let pasWActive=true;
    // if(pasWActive){
    // password.addEventListener("click",function(){
    //
    //     prompt("Введите свой возраст:");
    //         pasWActive=false;
    //
    // });
    // }
    password.addEventListener("change",function(){
        confirmMyPassword();
    });
}

function confirmMyPassword(){

    if(password.value.length>0&&confirmPassword.value.length===0||password.value.length===0&&confirmPassword.value.length>0) {

        upProfile_btn.setAttribute("disabled", "disabled");

        return ;
    }
    if(password.value.length === confirmPassword.value.length){

        if(password.value!==confirmPassword.value) {

            upProfile_btn.setAttribute("disabled", "disabled");
            confirmPassword.classList.add("not-corect");
            password.classList.add("not-corect");
            spanConfirmPassword.textContent=" паролі не співпадають";//not-corect
        }
        if(password.value===confirmPassword.value){

            spanConfirmPassword.textContent="";
            confirmPassword.classList.remove("not-corect");
            password.classList.remove("not-corect");
            upProfile_btn.removeAttribute("disabled");
        }
    }else{
        upProfile_btn.setAttribute("disabled", "disabled");
    }
}


window.addEventListener("click",function(event){//onclick=

    if(updateAddressFromUser){

        if(event.target===updateAddressFromUser){

            updateAddressFromUser.style.display="none";
        }
    }

    if(updatePhoneFromUser){

        if(event.target===updatePhoneFromUser){

            updatePhoneFromUser.style.display="none";
        }
    }

    if(updatePostOfficeFromUser){

        if(event.target===updatePostOfficeFromUser){

            updatePostOfficeFromUser.style.display="none";
        }
    }
});


if(closeUpdateAddressFromUser){

    closeUpdateAddressFromUser.onclick = function () {

        updateAddressFromUser.style.display = "none";
    };
}


if(closeUpdatePfoneFromUser){

    closeUpdatePfoneFromUser.onclick = function () {

        updatePhoneFromUser.style.display = "none";
    };
}


if(closeUpdatePostOfficeFromUser){

    closeUpdatePostOfficeFromUser.onclick = function () {

        updatePostOfficeFromUser.style.display = "none";
    };
}


function myFetchFunction(url, data, method, csrf, place) {
    try {
        const response = fetch(url,
            {
                method: method,
                body: data,
                headers: {
                    'X-CSRF-TOKEN': csrf,
                    'Content-Type': 'application/json'
                    //'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
        response.then(data => {

            return data.text()
        })
            .then(data => {

                viewDataReturnFetch(data, place);
            });
    } catch (error) {

        console.error('Ошибка:', error);
    }
}


function viewDataReturnFetch(data1, place1) {

    switch (place1) {

        case "address": dataReturnFetchAddress(data1); break;

        case "postOffice": dataReturnFetchPostOffice(data1); break;

        case "phone":  dataReturnFetchPhone(data1); break;
    }
}


function  updateAddress(addressId){

    let url="/rest/user-address/"+addressId;

    myFetchFunction(url,null,"get","","address");
}


function dataReturnFetchAddress(data1){

    let data= JSON.parse(data1);
console.log(data);
    if(!(upAddress_city && upAddress_address && upAddress_postCode && upAddress_id))return;

    upAddress_id.value = data.id;
    upAddress_region.value = data.region;
    upAddress_district.value = data.district;
    upAddress_city.value = data.city;
    upAddress_address.value = data.address;
    upAddress_postCode.value = data.postCode;
    upAddress_info.value = data.info;

    if(data.active){
        upAddress_active.checked=true;
        upAddress_active.value=1;
    }else{
        upAddress_active.checked=false;
        upAddress_active.value=0;
    }

       updateAddressFromUser.style.display = "block";
}


function updatePhone(phoneId){

    let url="/rest/user-update-phone/"+phoneId;

    myFetchFunction(url,null,"get","","phone");
}


function dataReturnFetchPhone(data1){

    let data = JSON.parse(data1);

    if(!(upPhone_phone && upPhone_active && upPhone_info && upPhone_id)) return;

    upPhone_phone.value =  data.phone;
    upPhone_info.value = data.info;
    upPhone_id.value = data.id;

    if(data.active){
        upPhone_active.checked=true;
        upPhone_active.value=1;
    }else{
        upPhone_active.checked=false;
        upPhone_active.value=0;
    }
    updatePhoneFromUser.style.display = "block";
}


function updatePostOffice(postOfficeId){

    let url="/rest/user-postOffice/"+postOfficeId;

    myFetchFunction(url,null,"get","","postOffice");
}


function dataReturnFetchPostOffice(data1){

    let data = JSON.parse(data1);

    if(!(upPostOffice_postOffice && upPostOffice_info && upPostOffice_active && upPostOffice_id)) return;

    upPostOffice_id.value = data.id;
    upPostOffice_postOffice.value = data.postOffice;
    upPostOffice_info.value = data.info;

    if(data.active){
        upPostOffice_active.checked=true;
        upPostOffice_active.value=1;
    }else{
        upPostOffice_active.checked=false;
        upPostOffice_active.value=0;
    }
    updatePostOfficeFromUser.style.display = "block";
}
//============profile.js ============

/*profile:527 {"id":2,"userId":null,"city":"Львів","address":"Пилипчука 21","postCode":"000000","info":null,"active":true}*/
//{"id":3,"userId":null,"info":"info","phone":"22222","active":true}
// console.log(upPhone_info);//{"id":3,"userId":null,"info":"info","phone":"22222","active":true}
// console.log(updatePostOfficeFromUser);
// //profile:549 {"id":2,"userId":null,"postOffice":"Нова почта","active":true,"info":"info"}


//function(){
// console.log(confirmPassword.value);
// console.log(password.value);
// if(password.value.length === confirmPassword.value.length){
// if(password.value!==confirmPassword.value) {
//     upProfile_btn.setAttribute("disabled", "disabled");
//     confirmPassword.classList.add("not-corect");
//     password.classList.add("not-corect");
//     //confirmPassword.value="not-corect";
//     spanConfirmPassword.textContent="Not corect";//not-corect
// }
// if(password.value===confirmPassword.value){
//   spanConfirmPassword.textContent="";
//     confirmPassword.classList.remove("not-corect");
//     password.classList.remove("not-corect");
//     upProfile_btn.removeAttribute("disabled");
// }
// }else{
//     upProfile_btn.setAttribute("disabled", "disabled");
// }
//});