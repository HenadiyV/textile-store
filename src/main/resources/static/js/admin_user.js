const frm_csrf = document.getElementById('frm_csrf');
const modalPhone = document.getElementById('myModalPhone');
const spanCloseMyModalPhone = document.getElementById("closeMyModalPhone");
let direction = 0;
const search_user=document.getElementById("search_user");
if(search_user){
    search_user.addEventListener("input",function(){
      //  console.log(this.value);
      let url = "/rest/search-user/"+this.value;
        myFetchFunction(url,null,"GET",'',"searchUser")
    });
}
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


const container_table = document.getElementById('container_table');

let t = false;

let tt = false;


function isElementVisible (el, holder) {

    holder = holder || document.body;

    const { top, bottom, height } = el.getBoundingClientRect();

    const holderRect = holder.getBoundingClientRect();

    return top <= holderRect.top
        ? holderRect.top - top <= height
        : bottom - holderRect.bottom <= height
}


window.onload=function(){

    if(document.getElementById("test_table")){

        userListRest(direction);
    }
};

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

    myFetchFunction("/rest/update-phone",JSON.stringify(formData),"Post",csrf,"res");
}


function editAddress(address_id,user_id){

    let infoV="info_address"+address_id;

    let regionV="region"+address_id;
    let districtV="district"+address_id;
    let cityV="city"+address_id;

    let addressV="address"+address_id;

    let postCodeV="postCode"+address_id;

    let info=document.getElementById(infoV).value;

    let region=document.getElementById(regionV).value;
    let district=document.getElementById(districtV).value;
    let city=document.getElementById(cityV).value;

    let address=document.getElementById(addressV).value;

    let postCode=document.getElementById(postCodeV).value;
                //"id="+address_id+"&userId="+user_id+"&city="+city+"&address="+address+"&postCode="+postCode+"&info="+info
    const formData ={"id":address_id,"userId":user_id,"region":region,"district":district,"city":city,"address":address,"postCode":postCode,"info":info};

    let csrf =frm_csrf.value;

    myFetchFunction("/rest/update-address",JSON.stringify(formData),"Post",csrf,"res");
}


function editPostOffice(postOffice_id,user_id){

    let infoV="info_post_office"+postOffice_id;

    let postOfficeV="postOffice"+postOffice_id;

    let info=document.getElementById(infoV).value;

    let postOffice=document.getElementById(postOfficeV).value;
            //"id="+postOffice_id+"&userId="+user_id+"&postOffice="+postOffice+"&info="+info;
    const formData ={"id":postOffice_id,"userId":user_id,"postOffice":postOffice,"info":info};

    let csrf =frm_csrf.value;

    myFetchFunction("/rest/update-post-office",JSON.stringify(formData),"Post",csrf,"res")
}


function myFetchFunction(url,data,method,csrf,fun){
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

                viewDataReturnFetchAdminUser(data,fun)
            });
    } catch (error) {
        console.error('Ошибка:', error);
    }
}
function viewDataReturnFetchAdminUser(data,fun){

    //let place =place1.indexOf("id")>0?place1.getAttribute("id"):place1;

    switch(fun){

        case "res": res(data) ; break;

        case "createTableUser": createTableUser(data);break;

        case "searchUser": createTableUser(data);break;

    }
}

function clearEditUser(){
    location.reload();
}

function userListRest(direction){
   // console.log(direction);
    let url="/rest/user-list/"+direction;
    myFetchFunction(url,null,"GET",'',"createTableUser");
}

function createTableUser(data1){
    let data= JSON.parse(data1);
//console.log(data);
    let table =["<table class='table-modal'>" +
    "<COLGROUP>" +
    "<COL width=\"3%\">\n" +
    "<COL width=\"27%\">\n" +
    "<COL width=\"15%\">\n" +
    "<COL width=\"30%\">\n" +
    "<COL width=\"15%\">\n" +
    "<COL width=\"10%\">\n" +
    "<thead>\n" +
    "<tr>\n" +
    "<th>ID</th>\n" +
    "<th>Логін/Імя</th>\n" +
    "<th>Phone</th>\n" +
    "<th>Address</th>\n" +
    "<th>Post office</th>\n" +
    "<th></th>\n" +
    " </tr>\n" +
    "</thead><tbody>"];
    let tdPo='';
    let tdP='';
    let tdAdr='';
    direction=data[0].pageNum;
//console.log(data[0].addressList.length);
    //console.log(direction);
    for(let i=0;i<data.length;i++){
            let tr ="";

        let tdU=`<td>${data[i].id}</td> 
                 <td> ${data[i].username} | ${data[i].name} </td>`;

        if(data[i].phoneList.length>0){

         tdP="<td><select>";

        for(ph of data[i].phoneList){

        tdP+="<option value="+ph.id+">"+ph.phone+"</option>"
        }

        tdP+="</select></td>";
        }

        let dataAdr='';

        if(data[i].addressList.length>0){

         tdAdr="<td><select>";

                for(adrs of data[i].addressList){

                if(adrs.region.length>2){

                    dataAdr=adrs.region;
                }
                if(adrs.district.length>2){

                    dataAdr+=" | "+adrs.district;
                }
                if(adrs.city.length>2){

                    if(dataAdr!==''){
                        dataAdr+=" | "+adrs.city;
                    }else{
                        dataAdr+=adrs.city;
                    }
                }
                if(adrs.address.length>2){

                    if(dataAdr!==''){
                        dataAdr+=" | "+adrs.address;
                    }else{
                        dataAdr+=adrs.address;
                    }
                }
              tdAdr+="<option value="+adrs.id+">"+dataAdr+"</option>"
            }
        tdAdr+="</select></td>";
        }

        if(data[i].postOfficeList.length>0){

         tdPo="<td><select>";

             for(po of data[i].postOfficeList){

                 tdPo+="<option value="+po.id+">"+po.postOffice+"</option>" ;

             }
        tdPo+="</select></td>";
        }

         let url="/admin/user-update/"+data[i].id;

         let tdE="<td><a class='btn btn-primary' href="+url+">Редагувати</a></td>";

         tr ="<tr>";

             if(tdU.length>0){

                 tr +=tdU;
             }else{

                 tr +="<td></td>"
             }


             if(tdP.length>0){

                 tr +=tdP;
             }else{
                 tr +="<td></td>"
             }

             if(dataAdr.length>0){

                 tr +=tdAdr;
                }else{

                 tr +="<td></td>"
             }

             if(tdPo.length>52){

                     tr +=tdPo;
                    }else{

                     tr +="<td></td>"
                    }

             if(tdE.length>0){

                 tr +=tdE;

                }
            tr +="</tr>";

            table.push(tr);
        }
        table.push("</tbody></table>");

       // let start="<a  onclick='userListRest(direction<20?0:(direction-20))'><div class='arrow-8-top'></div></a>";

  // let stop="<a  onclick='userListRest(direction+20)'><div class='arrow-8-bottom'></div></a>";
    //start++stop
    document.getElementById("test_table").innerHTML=table.join(" ");

}
//let stop="<div  style='height: 100px;'></div><div  style='height: 10px;background: #9c9c9c;'></div><div id='stop' style='height: 10px;'></div>";

//let start="<div id='start' style='height: 1px;'></div><div style='height: 10px;background: #9c9c9c;'></div><div style='height: 100px;'></div>";
// let goToStartList=++direction;
// let goToEndtList=direction<0?--direction:0;
// let urlScrolgoToStartList="/admin/user-update/"+goToStartList;
// let urlScrolgoToEndtList="/admin/user-update/"+goToEndtList;
// console.log(goToStartList);
// console.log(goToEndtList);
//const test_table = document.getElementById('test_table');

// container_table.addEventListener("scroll", function () {
//
//     const start =document.getElementById("start");
//
//     const stop =document.getElementById("stop");
//
//     if(isElementVisible (start, container_table)){
//         if(t){
//             if(direction>=20){
//             direction-=20;}
//             else{
//                 direction=0;
//             }
//             console.log("start "+direction);
//             userListRest(direction);
//         t=!t;
//         }
//     }else{
//         t=true;
//     }
//     if(isElementVisible (stop, container_table)){
//         if(tt){
//             direction+=20;
//             console.log("stop "+direction);
//
//             userListRest(direction);
//
//             tt=!tt;
//         }
//     }else{
//         tt=true;
//     }
// });
