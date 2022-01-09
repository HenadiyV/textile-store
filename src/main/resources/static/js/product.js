const myModalOrdering = document.getElementById("myModalOrdering");
const closeMyModalOrdering = document.getElementById("closeMyModalOrdering");
const myModalBasket = document.getElementById("myModalBasket");
const closeMyModalBasket = document.getElementById("closeMyModalBasket");
//const order_csrf = document.getElementById("order_csrf");
// const order_csrf = document.getElementById("order_csrf");
// const basket_csrf = document.getElementById("basket_csrf");
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
let imageName = '';


if (inpSize) {

    inpSize.addEventListener("input", function () {

        if (+this.value > +this.max) {

            this.value = +this.max;
        }
        let summ = (+inpPrice.value * +this.value);

        place_summ.textContent = "Сумма : " + (+inpPrice.value * +this.value);

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

            myModalBasket.style.display = "none";
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

        myModalBasket.style.display = "none";
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

        case "search-token":dataReturnFetchBasketToToken(data1); break;
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

    myModalOrdering.style.display = "block";
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


function dataReturnFetchBasket(data1) {
    console.log(data1.length);
if(data1!=null&&data1!=''){
    let data = JSON.parse(data1);

    table_place.innerHTML = '';

    let div = [];

    for (let i = 0; i < data.length; i++) {

        let summ = data[i].price * data[i].size;
console.log("dataReturnFetchBasket = "+data[i].id);
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
                            <span>Метраж : ${data[i].size}</span><br/>
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
    myModalBasket.style.display = "block";
}else{
    location.reload();
}
    //
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

//=========== product.js==============

// // создание элементов
// const element = (tag, classes = [], content, src, id) => {
//
//     const node = document.createElement(tag);
//
//     if (classes.length) {
//
//         node.classList.add(...classes);
//     }
//     if (content) {
//
//         node.textContent = content;
//     }
//     if (src) {
//
//         node.src = src;
//     }
//     if (id) {
//
//         node.id = id;
//     }
//     return node;
// };
//
//
// // создание формы
// const createForm = (action, method, multipart) => {
//
//     const frm = document.createElement('form');
//
//     if (action) {
//
//         frm.action = action;
//     }
//     if (method) {
//
//         frm.method = method;
//     }
//     if (multipart) {
//
//         frm.enctype = multipart;
//     }
//     return frm;
// };
//
//
// function noop() {}


// console.log('Готов!'+Cookies.get('foo'));
//    if(order_csrf){
//        console.log(order_csrf.value);
//    }else{
//        console.log("not order_csrf.value");
//        location.reload();
//    }
// if(basket_csrf){
//        console.log(basket_csrf.value);
//    }else{
//        console.log("not basket_csrf.value");
//        location.reload();
//    }

// let cookies=document.cookie;
// console.log(cookies);

// let token = '';
// if (data1 === null) {
//     token = createToken(20);
// } else {
//     token = getCookie('foo');
// }

// if(show_basket){
//     show_basket.style.display = 'none';
// }


// if(write_token){
//     write_token.style.display = 'none';
// }

// let index_ordering_product = 0;
// let productToBasketMap = new Map();

// function saveBasket(){
//     let url = "/rest/basket";
//     let data = {
//         username: username.value,
//         phone: phone.value,
//         info: info.value,
//         token: writeCookies(),
//     };
//     username.value = '';
//     phone.value = '';
//     info.value = '';
//
//     myFetchFunction(url, JSON.stringify(data), "Post", order_csrf.value, '');
//
//     myModalOrdering.style.display = "none";
// }



// function saveProductToBasket(productId) {
//     let url = "/rest/basket";
//     let data = {
//         productId: +productIdBasket.value,
//         username: username.value,
//         phone: phone.value,
//         info: info.value,
//         token: writeCookies(),
//         size: inpSize.value,
//         price: inpPrice.value,
//         img: imageName
//     };
//     imageName = '';
//     productIdBasket.value = '';
//     username.value = '';
//     phone.value = '';
//     info.value = '';
//     inpSize.value = '';
//     inpPrice.value = '';
//     myFetchFunction(url, JSON.stringify(data), "Post", order_csrf.value, '');
//     myModalOrdering.style.display = "none";
//     //Cookies.remove('foo');
// }


// function clearCookies() {
//
//     if (Cookies.get('foo')) {
//
//         Cookies.remove('foo');
//     }
// }

/*
dat: null
img: "13_1.jpg"
info: ""
phone: null
price: 60
productId: 2
size: 1
token: "115b256a-15f4-4378-82d7-a9a059f606ff"
username: null
* */

// if(addBasketProductModal){
//
//     if (event.target.className==="my_modal-table-cell") {
//         console.log(event);
//         //addBasketProductModal.style.display = "none";
//         addBasketProductModal.classList.remove("my_modal-overlay_visible");
//     }
// }

// console.log(data);
//     let table=["<table><tr><th>Img</th><th>Price</th><th>Size</th><th>Summ</th></tr>"];
//
//     for(let i=0;i<data.length;i++){
//         let summ=data[i].price*data[i].size;
//         let tr=`<tr><td class="preview">
// <div class="preview-image">
// <img src=/img/${data[i].img} /></div></td>
//                 <td>${data[i].price}</td><td>${data[i].size}</td>
//                 <td>${summ}</td><td><a class="btn btn-danger" onclick="deleteBasketProduct(${data[i].id})">Видалити</a></td>
//                 </tr>`;
//         table.push(tr);
//     }
//     table.push("</table>");


//function ordering(productId){
//console.log(productId);
// let inpProductId = element('input', [], '', '', 'productId');
// inpProductId.type='hidden';
// inpProductId.name='id';
// inpProductId.value=productId;
// const open = element('a', ['my-btn'], 'Відкрити', '', '');
// el = createForm(act, 'post','multipart/form-data');


// searchProduct(productId)
//}
//const frm=createForm("product-to-order","post");
//product
// ++index_ordering_product;
//     productToBasketMap.set(index_ordering_product,{})
//console.log(productIdBasket.value);
//frm.innerHTML='';
//  ++index_ordering_product;

// console.log(place);
//console.log(data1);
// let newPalce1= document.getElementById(place);//"product_detail"
// console.log(newPalce1);
/*
active: true
bonus: 0
category: null
category_id: 1
color: "Color_2"
dat: "2021-04-11T21:00:00.000+00:00"
description: "Name_Color_2"
id: 2
imageProducts: null
info: ""
metr: 0
name: "Name_2"
product_balance: 30
purchasePrice: 0
sellingPrice: 60
selling_size: 50
sizeProduct: 50*/


//     const inpCSRF = element('input', [], '', '', '');//'img-view'
//     inpCSRF.type = 'text';
//     inpCSRF.name='_csrf';
//     inpCSRF.value = order_csrf.value;//document.querySelector("input[name='tkn']").value;
//
//     const inpProductId = element('input', [], '', '', '');
//     inpProductId.type='text';
//     inpProductId.name='id';
//     inpProductId.value=data.id;
//     const spanSumm=element('span');
//     const labelProductSize = element('label', ['mt-2'], 'Кількість: ', '', '');
//
//
//     const inpProductSize = element('input', [], '', '', '');
//     inpProductSize.type='number';
//     inpProductSize.name='selling_size';
//     inpProductSize.min=0;
//     inpProductSize.max=data.product_balance;
//     inpProductSize.addEventListener('change',function(){
//         //console.log(this.value);
//        /// console.log(this.max);
//         if(+this.value>+this.max){
//             this.value=this.max;
//         }
//
//         spanSumm.textContent=inpProductSize.value*data.sellingPrice;
//         //console.log(this.value);
//     });
//
//
//
//     const spanProductName=element('span');//, [], '', '', ''
//     spanProductName.textContent=data.name;
//     //spanProductName.insertAdjacentHTML('beforebegin','<br/>');
//     const buttonProduct=element('button',["btn","btn-primary","mt-2"]);
//     buttonProduct.type="submit";
//     buttonProduct.textContent="Save";
//
//     const myBr=element('br');
//     const myBr1=element('br');
//     const myBr2=element('br');
//     const myBr3=element('br');
//     const preview = element('div', ['preview','mt-2'], '');
//
//     // <div class = "preview-remove" data-name = "${data.img}"> &times; </div>
//     //     <<div class = "preview-info">
//     //     <span>${file.name}</span>
//     //     ${bytesToSize(file.size)}
//     // </div>
// preview.insertAdjacentHTML('afterbegin',
//         `<div class="preview-image">
//                                         <img src="/img/${data.img}" alt="${data.img}"/>
//                                 </div>`
//     );
//
// //const imgDiv=element('div',["preview-image"]);
// //const img=element('img');
// //img.src=data.img;
// //imgDiv.insertAdjacentHTML('afterbegin',img);
//
// //console.log(data);product-to-order/rest/product-order/
//
// frm.appendChild(spanProductName);
// //frm.appendChild(myBr1);
// frm.appendChild(spanSumm);
// frm.appendChild(inpCSRF);
// frm.appendChild(inpProductId);
// frm.appendChild(myBr2);
// frm.appendChild(labelProductSize);
// frm.appendChild(inpProductSize);
// frm.appendChild(myBr3);
// frm.appendChild(preview);
//
// frm.appendChild(myBr);
// frm.appendChild(buttonProduct);
//
// place.appendChild(frm);
//   productToBasketMap.set(index_ordering_product,data);
//   console.log(productToBasketMap);
// createProductTableToOrder(productToCartMap,place);

