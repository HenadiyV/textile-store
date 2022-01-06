const frm_csrf = document.getElementById('frm_csrf');
const modalPhone = document.getElementById('myModalPhone');
const spanCloseMyModalPhone = document.getElementById("closeMyModalPhone");

if(spanCloseMyModalPhone){

    spanCloseMyModalPhone.onclick = function() {

        modalPhone.style.display = "none";
    };
}


const modalAddress= document.getElementById('myModalAddress');
const spanCloseMyModalAddress = document.getElementById("closeMyModalAddress");

if(spanCloseMyModalAddress){

    spanCloseMyModalAddress.onclick = function() {

        modalAddress.style.display = "none";
    };
}

const modalPostOffice= document.getElementById('modalPostOffice');
const spanCloseMyModalPostOffice = document.getElementById("closeMyModalPostOffice");

if(spanCloseMyModalPostOffice){

    spanCloseMyModalPostOffice.onclick = function() {

        modalPostOffice.style.display = "none";
    };
}


window.onclick = function(event) {

    if (event.target === modalPhone) {

        modalPhone.style.display = "none";
    }
    if (event.target === modalAddress) {

        modalAddress.style.display = "none";
    }
    if (event.target === modalPostOffice) {

        modalPostOffice.style.display = "none";
    }
};


function showModal(nameModal){
    document.getElementById(nameModal).style.display = "block";
}


function rs(id,idMes,mess,er){

    let Input=document.getElementById(id);

    let ErrorDiv=  document.getElementById(idMes);

    if(er===0){

        Input.classList.add('is-invalid');

        ErrorDiv.classList.add('invalid-feedback');
    }else if(er>0){

        Input.classList.add('is-valid');

        ErrorDiv.classList.add('valid-feedback');
    }
    ErrorDiv.innerText=mess;
}


function res(res){

    let m=JSON.parse(res);

    if(m.error){

        rs(m.id,m.idMes,m.error,0);
    }
    if(m.res){

        rs(m.id,m.idMes,m.res,1);
       // location.reload();
    }
}


function editPhone(phone_id,user_id){

    let infoV="info_phone"+phone_id;

    let phoneV="phone"+phone_id;

    let info=document.getElementById(infoV).value;

    let phone=document.getElementById(phoneV).value;
                //"id="+phone_id+"&userId="+user_id+"&phone="+phone.trim()+"&info="+info
    const formData ={"id":phone_id,"userId":user_id,"phone":phone.trim(),"info":info};

    let csrf =frm_csrf.value;

    myFetchFunction("/rest/update-phone",JSON.stringify(formData),"Post",csrf);
}


function editAddress(address_id,user_id){

    let infoV="info_address"+address_id;

    let cityV="city"+address_id;

    let addressV="address"+address_id;

    let postCodeV="postCode"+address_id;

    let info=document.getElementById(infoV).value;

    let city=document.getElementById(cityV).value;

    let address=document.getElementById(addressV).value;

    let postCode=document.getElementById(postCodeV).value;
                //"id="+address_id+"&userId="+user_id+"&city="+city+"&address="+address+"&postCode="+postCode+"&info="+info
    const formData ={"id":address_id,"userId":user_id,"city":city,"address":address,"postCode":postCode,"info":info};

    let csrf =frm_csrf.value;

    myFetchFunction("/rest/update-address",JSON.stringify(formData),"Post",csrf);
}


function editPostOffice(postOffice_id,user_id){

    let infoV="info_post_office"+postOffice_id;

    let postOfficeV="postOffice"+postOffice_id;

    let info=document.getElementById(infoV).value;

    let postOffice=document.getElementById(postOfficeV).value;
            //"id="+postOffice_id+"&userId="+user_id+"&postOffice="+postOffice+"&info="+info;
    const formData ={"id":postOffice_id,"userId":user_id,"postOffice":postOffice,"info":info};

    let csrf =frm_csrf.value;

    myFetchFunction("/rest/update-post-office",JSON.stringify(formData),"Post",csrf)
}


function myFetchFunction(url,data,method,csrf){
    try {
        const response = fetch(url,
            {
                method:method ,
                body: data,
                headers: {
                    'X-CSRF-TOKEN': csrf,
                    'Content-Type': 'application/json'
                    //'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
        response.then(data=>{
            return data.text()
        })
            .then(data=>{

                res(data) ;
            });
    } catch (error) {
        console.error('Ошибка:', error);
    }
}


function clearEditUser(){
    location.reload();
}