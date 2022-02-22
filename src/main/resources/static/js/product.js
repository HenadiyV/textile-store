const myModalOrdering = document.getElementById("myModalOrdering");
const closeMyModalOrdering = document.getElementById("closeMyModalOrdering");
const myModalBasket = document.getElementById("myModalBasket");
const closeMyModalBasket = document.getElementById("closeMyModalBasket");
const myBasketUser = document.getElementById("myBasketUser");
const closeMyBasketUser = document.getElementById("closeMyBasketUser");
const imgView = document.getElementById('view-img');
const img_product = document.getElementById('img_product');
const token_cookie = document.getElementById('token_cookie');
const token_ordering = document.getElementById('token_ordering');
const place_summ = document.getElementById("place_summ");
const place_price = document.getElementById("place_price");
const table_place = document.getElementById("table_place");
const inpPrice = document.getElementById("price");
const inpSize = document.getElementById('size');
const productIdBasket = document.getElementById('productId');
const username = document.querySelector("input[name='username']");
const phone = document.querySelector("input[name='phone']");
const info = document.querySelector("textarea[name='info']");
const add_basket = document.getElementsByName('add-basket');
const show_basket = document.querySelector("a[name='show-basket']");
const write_token = document.getElementById("write_token");
const add_to_basket = document.getElementById("add_to_basket");

const b_user = document.getElementById("b_user");
const b_phone = document.getElementById("b_phone");
const b_address = document.getElementById("b_address");
const b_postOffice = document.getElementById("b_postOffice");
const b_info = document.getElementById("b_info");
const b_token = document.getElementById("b_token_ordering");

let imageName = '';


if (inpSize) {

    inpSize.addEventListener("input", function () {

        if (+this.value > +this.max) {

            this.value = +this.max;
        }
        let summ = (+inpPrice.value * +this.value);

        place_summ.textContent = "Сумма : " + (+inpPrice.value * +this.value).toFixed(2);

        if(img){

            img.value=imageName;
        }
    });
}


if(add_to_basket){

    add_to_basket.addEventListener("click",function(){
        if(token_cookie){

            let token=getCookie('textile-basket');

            if(!token){

                token=writeCookies();
            }
            token_cookie.value=token;
        }
        myModalOrdering.style.display = "none";
    });
}


window.addEventListener("click",function (event) {

    if (myModalOrdering) {

        if (event.target === myModalOrdering) {

            clearOrderingForm();

            myModalOrdering.style.display = "none";
        }
    }
    if (myModalBasket) {

        if (event.target === myModalBasket) {

            basketProductSizeSave(basketProductMap);

            myModalBasket.style.display = "none";
        }
    }
    if (myBasketUser) {

        if (event.target === myBasketUser) {

            myBasketUser.style.display = "none";
        }
    }
});


function clearOrderingForm() {

    inpSize.value = "";

    place_summ.innerHTML = '';
}


window.onload = function () {


    add_basket.forEach(el => el.classList.remove('disabled'));

    if (checkCookie('textile-basket')) {

        if(show_basket){

        show_basket.style.display = 'block';}
    }else{
        if(write_token){

        write_token.style.display = 'block';
        }
    }

};


if (closeMyModalOrdering) {

    closeMyModalOrdering.onclick = function () {

        myModalOrdering.style.display = "none";
    };
}


if (closeMyModalBasket) {

    closeMyModalBasket.onclick = function () {

        basketProductSizeSave(basketProductMap);

        myModalBasket.style.display = "none";
    };
}


if (closeMyBasketUser) {

    closeMyBasketUser.onclick = function () {

        myBasketUser.style.display = "none";
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

                viewDataReturnFetchOrdering(data, place);
            });
    } catch (error) {

        console.error('Ошибка:', error);
    }
}


function viewDataReturnFetchOrdering(data1, place1) {

    let place = place1.indexOf("id") > 0 ? place1.getAttribute("id") : place1;

    switch (place) {

        case "user_ordering": dataReturnFetchUserOrdering(data1, "us_order"); break;

        case "ordering_product": dataReturnFetchProductOrdering(data1); break;

        case "basket": dataReturnFetchBasket(data1); break;

        case "search-token": dataReturnFetchBasketToToken(data1); break;

        case "user": dataReturnFetchUserById(data1); break;

    }
}


function dataReturnFetchProductOrdering(data1) {

    let data = JSON.parse(data1);

    if (productIdBasket) {

        productIdBasket.value = data.id;
    }
    if (imgView) {

        imgView.src = "/img/" + data.img;

        imgView.alt = data.img;

        imgView.style.width = "180px";
    }

    if(img_product){

        img_product.value=data.img;
    }
    imageName = data.img;

    place_price.textContent = "Ціна : " + data.sellingPrice;

    inpPrice.value = data.sellingPrice;

    inpSize.max = data.product_balance;

    myModalOrdering.style.display = "block" ;
}


function searchProduct(productId) {

    let url = "/rest/product/" + productId;

    myFetchFunction(url, null, "Get", '', 'ordering_product');
}


let tokenTrue='';


function writeCookies() {

    let tokenCreateCount=0;

    let resCookie = getCookie('textile-basket');

    let token = '';

    if (!resCookie) {

        do{

         let  url= "/rest/search-token/"+token;

          myFetchFunction(url,null,"get",'','search-token');

          tokenCreateCount++;

          if(tokenCreateCount>5){break}

          token = createToken(20);

        }while(tokenTrue);

        setCookie('textile-basket', token,1);

    } else {
       token = resCookie;
    }
    //location.reload();

    return token;
}


function showBasket() {

    let resCookie = getCookie('textile-basket');

    let url = "/rest/basket-view/" + resCookie;

    myFetchFunction(url, null, "Get", '', 'basket');
}


function dataReturnFetchUserById(data1){

    let data = JSON.parse(data1);

    if(b_user){
        b_user.value=data.id;
    }
    if(b_phone){

        let phoneOpt="";

        for(phon of data.phoneList){

            phoneOpt+="<option value="+phon.id+">"+phon.phone+"</option>";
        }
        b_phone.innerHTML= phoneOpt;
    }
    if(b_address){

        let addressOpt="";

        for(address of data.addressList){

            let adr=address.city+" | "+address.address+" | "+address.postCode;
            addressOpt+="<option value="+address.id+">"+adr+"</option>";
        }
        b_address.innerHTML=addressOpt;
    }
    if(b_postOffice){

        let postOfficeOpt="";

        for(postOffice of data.postOfficeList){

            postOfficeOpt+="<option value="+postOffice.id+">"+postOffice.postOffice+"</option>"
    }
        b_postOffice.innerHTML=postOfficeOpt;
    }

    if(b_token){

        b_token.value=getCookie('textile-basket');
    }
    myBasketUser.style.display = "block";
}


function dataReturnFetchBasket(data1) {

    if(data1!=null && data1!==''){

        let data = JSON.parse(data1);

        table_place.innerHTML = '';

        let div = [];

        for (let i = 0; i < data.length; i++) {

            let summ = data[i].price * data[i].size;

            let dv = `
            <div class="col-sm-6" style="border:1px solid silver">
             <div class="table-content-center">
                 <div class="row">
                     <div class="col-sm-6 col-md-6">
                            <div class="preview">
                               <div class="preview-image">
                                <img src=/img/${data[i].img} />
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-6">
                            <span>Ціна : ${data[i].price}</span><br/>
                            <label>Метраж : </label><br/>
                            
                 <div class="number">
                    <button class="number-minus" type="button" onclick="this.nextElementSibling.stepDown(); 
                    this.nextElementSibling.onchange;
                    basketProductSizeEdit(${data[i].id},this.nextElementSibling.dataset.product,this.nextElementSibling.value)">-</button>
                    
                    <input type="number" min="0" step="0.1" data-product="${data[i].productId}" value="${data[i].size}" readonly />
                    
                    <button class="number-plus" type="button" onclick="this.previousElementSibling.stepUp(); 
                    this.previousElementSibling.onchange;
                    basketProductSizeEdit(${data[i].id},this.previousElementSibling.dataset.product,this.previousElementSibling.value)">+</button>
                </div><br/>
                           
                            <span>Сумма : ${summ}</span><br/>
                            <a class="btn btn-danger" onclick="deleteBasketProduct(${data[i].id})">Видалити</a>
                        </div>
                    </div>
                </div>
            </div>`;

            div.push(dv);
        }
        table_place.innerHTML = div.join('');

        if(token_ordering){

            token_ordering.value=getCookie('textile-basket');
        }

        test_user();
    }else{
        location.reload();
    }
}


function deleteBasketProduct(basketProductId) {

    let resCookie = getCookie('textile-basket');

    let url = "/rest/delete-basket-product/" + resCookie + "/" + basketProductId;

    myFetchFunction(url, null, "Get", '', 'basket');
}


function createToken(lenghtStr) {
    let char;
    let arr = [];
    let len = lenghtStr || 5;
    do {
        char = ~~(Math.random() * 128);
        if ((
            (char > 47 && char < 58) || // 0-9
            (char > 64 && char < 91) || // A-Z
            (char > 96 && char < 123) // a-z
            // || (char > 32 && char < 48) // !"#$%&,()*+'-./
            // || (char > 59 && char < 65) // <=>?@
            // || (char > 90 && char < 97) // [\]^_'
            // || (char > 123 && char < 127) // {|}~
        )
        //security conscious removals: " ' \ '
        //&& (char != 34 && char != 39 && char != 92 && char != 96)
        ) {
            arr.push(String.fromCharCode(char))
        }
    } while (arr.length < len);

    return arr.join('')
}


function dataReturnFetchBasketToToken(data1) {

    tokenTrue = data1;
}

//=================CORECT SIZE PRODUCT==============
let basketProductMap = new Map();

let basketProduct = new Array();


function basketProductSizeEdit(id,productId,size){

    basketProductMap.set(id,{productId,size});
}


function basketProductSizeSave(basketProductMap){

    let inp_csrf='';

        document.querySelectorAll("input[name='_csrf'").forEach(el=>inp_csrf=el.value);

    for (let v of basketProductMap.entries()) {

        let ob={"id":v[0],"productId":v[1].productId,"size":v[1].size};

        basketProduct.push(ob);
    }
    url="/rest/edit-selling-size";
    let data=JSON.stringify(basketProduct);
    let method="POST";

    try {
        const response = fetch(url,
            {
                method: method,
                body: data,
                headers: {
                    'X-CSRF-TOKEN': inp_csrf,
                    'Content-Type': 'application/json'
                    // 'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
        basketProduct.length = 0;
    }catch(error){
        console.error('Ошибка:', error);
    }
}
//=================END CORECT SIZE PRODUCT==============
//=============Cookie============
    function setCookie(cname, cvalue, exdays) {

        let d = new Date();

        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));

        let expires = "expires="+d.toUTCString();

        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }


    function getCookie(cname) {

        let name = cname + "=";

        let ca = document.cookie.split(';');

        for(let i = 0; i < ca.length; i++) {

            let c = ca[i];

            while (c.charAt(0) === ' ') {

                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {

                return c.substring(name.length, c.length);
            }
        }
        return "";
    }


    function checkCookie(cookie_name) {

        let cookie = getCookie(cookie_name);

        if (cookie !== "") {

          return true;
        }
        return false;
    }


    function testRestSearchToken(token) {

        let url = "/rest/search-token/" + token;

        myFetchFunction(url, null, "get", '', 'search-token');
    }
//=============End Cookie============

//====================productEdit ============
    let inpShowcase=document.querySelectorAll("input[name='showcase']");


    if(inpShowcase){

        inpShowcase.forEach(el=>el.addEventListener('click',showcase));
    }


    function showcase(){
        let sp='';
        for(let i=0;i<inpShowcase.length;i++){

            if(inpShowcase[i]!==this){

                inpShowcase[i].checked=false;

                sp=inpShowcase[i].previousElementSibling.previousElementSibling;

                sp.innerText="Додаткове";
            }else{
                this.checked=true;

                sp=this.previousElementSibling.previousElementSibling;

                sp.innerHTML="Вітрина";

                checkedShowcase(this.value)
            }
        }
    }


    function checkedShowcase(id){

        let productId=document.querySelector("#id_product").value;

        let request = new XMLHttpRequest();

        request.open("Get", "/rest/showcase-image/"+productId+"/"+id);

        request.send();
        // let status=request.status;
        // if (status ===200){
        //     console.log(status);
        // }else{
        //     console.log("error",status);
        // }
    }
//====================End productEdit ============

function test_user(){
    if(document.getElementById("__user_id")){

        let userId = document.getElementById("__user_id").value;

        let url = "/rest/user/" + userId;

        myFetchFunction(url, null, "Get", '', 'user');

    }else{
        console.log("not found __user_id");

        myModalBasket.style.display = "block";
    }
}

let doll;
let purchasePrice =document.getElementById("purchasePrice");
const inpDollar=document.getElementById("dollar");
const inpHrivna=document.getElementById("hrivna");

// if(inpDollar){
//     inpDollar.addEventListener("change", function(){
//
//         let dl=this.value;
//
//         if(dl>0){
//             //inpHrivna.setAttribute('readonly',"readonly");
//             let dd=(dl*doll[0].sale).toFixed(2);
//            inpHrivna.value=dd;
//         }else{
//            // inpHrivna.removeAttribute('readonly');
//             inpHrivna.value=0;
//         }
//         //console.log(dl);
//        // purchasePrice.value=(dl*doll[0].sale).toFixed(2);
// //
//        // console.log(doll[0].sale);
//     });
//     //
// }

 // if(inpHrivna){
 //     inpHrivna.addEventListener("change", function(){
 //         let hr=this.value;
 //        // console.log(hr);
 //         if(hr>0){
 //
 //            // inpDollar.setAttribute('readonly',"readonly");
 //             let hh=(hr/doll[0].sale).toFixed(2);
 //             inpDollar.value=hh;//.replace(',','.')
 //         }else{
 //            // inpDollar.removeAttribute('readonly');
 //             inpDollar.value=0;
 //         }
 //        // purchasePrice.value=hr;
 //     });
 // }

function getCurency(){
    let url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    let status = function (response) {
        if (response.status !== 200) {
            return Promise.reject(new Error(response.statusText))
        }
        return Promise.resolve(response)
    };
    let json = function (response) {
        return response.json()
    };

    fetch(url, {
        method: "Get",

        headers: {

            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
        .then(status)
        .then(json)
        .then( data=>{doll=data})
        .catch(function (error) {
            console.log('error', error)
        })
}
getCurency();
//console.log(doll);
//=========== product.js==============
// if(purchasePrice){
//     purchasePrice.addEventListener("input",()=>{
//         //console.log(doll);
//         //console.log(doll[0].sale);&&doll[0].sale!=='undefined'
//         if(chekcedDollar){
//
//             let tempValue=+purchasePrice.value;
//             let tempSalle=doll[0].sale;
//             console.log(tempValue);
//             console.log(tempSalle);
//             purchasePrice.value=(tempValue*tempSalle).toFixed(2);
//
//         }else{
//             console.log(purchasePrice.value);
//         }
//     })
//
// }