const modal = document.getElementById('myModal');
const spanMyModal  = document.getElementById("closeMyModal");
const siz_inpt =document.querySelector("input[name='siz']");
const balanceProduct_inpt =document.querySelector("input[name='balance_product']");
const summ_inpt = document.querySelector("input[name='summ']");
const discount_price_inpt =document.querySelector("input[name='discount_price']");
const balance_span =document.querySelector(".balance");
const upSumm =document.querySelectorAll(".upSumm");
const addCartToOrder = document.getElementById('addCartToOrder');
const spanAddCartToOrder  = document.getElementById("closeAddCartToOrder");
const titleSumm =document.querySelector("#order_summ");
const search_product =document.querySelector("#search_product");
const search_user =document.querySelector("#search_user");
const select_userL =document.getElementById('userL');
const select_userE =document.getElementById('userE');
const select_productL =document.getElementById('productL');
const btn_form =document.getElementById('btn_form');
const pr_order =document.getElementById('pr_order');
//const product_detail =document.getElementById('product_detail');
const us_order =document.getElementById('us_order');
const save_cart =document.getElementById('save_cart');
const cartValue =document.getElementById('cartValue');
const dat_dispatch =document.getElementById('dat_dispatch');
const dat_dispatch1 =document.getElementById('dat_dispatch1');
const user_id =document.getElementById("user_id");
const order_user_table =document.getElementById("order_user_table");

let salle=0;
let balance=0;
let size_sale=0;
let size_product=0;
let ind=0;
let orderSumm="Сумма заказу: ";
let orderId=0;
let productMap= new Map();
let productToCartMap=new Map();
let cartMap=new Array();

window.addEventListener("click", function(event) {

    if(modal){

        if (event.target === modal) {

            modal.style.display = "none";
        }
    }

    if(addCartToOrder){

        if (event.target === addCartToOrder) {

            addCartToOrder.style.display = "none";
        }
    }
    // if(dat_dispatch) {console.log("ok");}

});
window.addEventListener("load",function(){
    if(dat_dispatch) {
        if(dat_dispatch.value.length==0){ //dat_dispatch.value = '';
            let options = {
                year: 'numeric',
                month: 'numeric',
                day: 'numeric',
                timezone: 'UTC'
            };

            let dat = new Date();

            dat_dispatch.value += dat.toLocaleString('ru', options);
        }console.log("ok");
    }
});

if(select_userL){

    select_userL.addEventListener("change",function(){

        let  selectedText = this.options[this.selectedIndex].value;

        let url="/rest/user/"+selectedText;

        myFetchFunction(url,null,"Get",'','us_order');
    });
}
if(select_userE){

    select_userE.addEventListener("change",function(){

        let  selectedText = this.options[this.selectedIndex].value;

        let url="/rest/user/"+selectedText;

        myFetchFunction(url,null,"Get",'','us_order');
    });
}

if(select_productL){

    select_productL.addEventListener("change",function(){

        let  selectedText = this.options[this.selectedIndex].value;

        let url="/rest/product/"+selectedText;

        myFetchFunction(url,null,"Get",'','pr_order');
    });
}


if(search_product!=null){

    search_product.addEventListener("input",function(){

        let url="/rest/search-product/"+this.value;

        let place=select_productL!=null?"productL":"productList";

        myFetchFunction(url,null,"Get",'',place);
    });
}


if(search_user!=null){

    search_user.addEventListener("input",function(){
        pag=0;
        //console.log("pag "+pag);
let place=select_userL!=null?select_userL.id:select_userE.id;
 let name =this.value!==''?this.value:'_';
// if(this.value.length>0)nam =this.value;
        //console.log("nam "+this.value);
        let url="/rest/search-user/"+name;
       // console.log(nam);
        myFetchFunction(url,null,"Get",'',place);
    });
}


function viewDataReturnFetch(data,place1){
//console.log(place1);
    switch(place1){

        case "userL": dataReturnFetchUserList(data,"userL"); break;

        case "userE": dataReturnFetchUserList(data,"userE"); break;

        case "productL": dataReturnFetchProductList(data,"productL");break;

        case "productList": createProductList(data,"productList"); break;

        case "us_order": dataReturnFetchUser(data,"us_order"); break;

        case "pr_order":  dataReturnFetchProduct(data,"pr_order");break;
    }
}



// NEW ORDER user list
function dataReturnFetchUserList(data1,place){
//console.log(place);
    let newPalce= document.getElementById(place);

    newPalce.innerHTML='';

    let data=JSON.parse(data1);

    let option=["<option>Список користувачів</option>"];

    for(let i=0;i<data.length;i++){

        let op="<option value="+data[i].id+">"+data[i].username+" | "+data[i].name+"</option>";

        option.push(op);
    }
    newPalce.innerHTML=option.join(' ');
}

// NEW ORDER  user
function dataReturnFetchUser(data1,place){

    let newPalce= document.getElementById(place);
    let us_name_order= document.querySelector("#us_name_order");
    let user_id= document.querySelector("#user_id");
    let phone_id= document.querySelector("#phone_id");
    let address_id= document.querySelector("#address_id");
    let postOffice_id= document.querySelector("#postOffice_id");
    let order_phone_select=document.getElementById('order_phone');
    let order_address_select=document.getElementById('order_address');
    let order_postOffice_select=document.getElementById('order_postOffice');

    let data=JSON.parse(data1);

    user_id.value=data.id;

    us_name_order.innerHTML=data.username;
    newPalce.innerHTML=data.name;

    let order_phone_el=[];

    for(let i=0;i<data.phoneList.length;i++){

        phone_id.value=data.phoneList[0].id;

        let el="<option value="+data.phoneList[i].id +">"+data.phoneList[i].phone +"</option>";

        order_phone_el.push(el);
    }
    order_phone_select.innerHTML=order_phone_el.join("");

    order_phone_select.addEventListener("change",function(){

        document.querySelector('#phone_id').value=this.options[this.selectedIndex].value;
    });

    let order_address_el=[];

    for(let i=0;i<data.addressList.length;i++){

        address_id.value=data.addressList[0].id;

        let el="<option value="+data.addressList[i].id +">"+data.addressList[i].city+" | "+
            data.addressList[i].address+" | "+data.addressList[i].postCode+"</option>";

        order_address_el.push(el);

    }
    order_address_select.innerHTML=order_address_el.join("");

    order_address_select.addEventListener("change",function(){

        document.querySelector('#address_id').value=this.options[this.selectedIndex].value;
    });

    let order_postOffice_el=[];

    for(let i=0;i<data.postOfficeList.length;i++){

        postOffice_id.value=data.postOfficeList[0].id;

        let el="<option value="+data.postOfficeList[i].id +">"+data.postOfficeList[i].postOffice +"</option>";

        order_postOffice_el.push(el);
    }
    order_postOffice_select.innerHTML=order_postOffice_el.join("");

    order_postOffice_select.addEventListener("change",function(){

        document.querySelector('#postOffice_id').value=this.options[this.selectedIndex].value;
    });
    order_user_table.removeAttribute('hidden');
    controlElementSaveCart();
}

//product
function dataReturnFetchProduct(data1,place){

    ++ind;

    let newPalce1= document.getElementById(place);

    let data=JSON.parse(data1);

    productToCartMap.set(ind,data);

    createProductTableToOrder(productToCartMap,place);
}

// product list
function dataReturnFetchProductList(data1,place){

    let newPalce= document.getElementById(place);

    newPalce.innerHTML='';

    let data=JSON.parse(data1);

    let option=["<option>Список товарів</option>"];

    for(let i=0;i<data.length;i++){

        let op="<option value="+data[i].id+">"+data[i].name+" | "+data[i].color+"</option>";

        option.push(op);
    }
    newPalce.innerHTML=option.join(' ');
}

function myFetchFunction(url,data,method,csrf,place){
    console.log("url= "+url);
    try {
        const response = fetch(url,
            {
                method:method ,
                body: data,
                headers: {
                    'X-CSRF-TOKEN': csrf,
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
        response.then(data=>{
            return data.text()
        })
            .then(data=>{
                viewDataReturnFetch(data,place);
            });
    } catch (error) {
        console.error('Ошибка:', error);
    }
}

// таблица товара для заказа
function createProductTableToOrder(data,place){
//console.log(data);
    let divCart = ["<table class='table-modal'><thead><tr><th class='td-hidden'>id</th><th>назва</th>" +
                    "<th>колір</th><th>остаток</th><th>ціна</th><th>продаж</th><th>знижка</th>"+
                    "<th>інфо</th><th>img</th><th>X</th></tr></thead><tbody>"];

    let d = '';

    for (let v of data.entries()) {

        if (v !== undefined) {

            let ob = v[1];
            console.log(ob);

let balan=ob.product_balance.toFixed(2);
console.log(balan);
            d += `<tr>
                <td class='td-hidden'>${ob.id}</td>
                <td><div class='tag'>${ob.name}</div> </td>
                <td><div class='tag'>${ob.color}</div> </td>
                <td>${balan} </td>
                <td>${ob.sellingPrice}</td>
                <td><input type='number' name='metr' min='0' data-metr='${v[0]}' max="${balan}"  value="${ob.metr}" /></td>
                <td><input type='number' name='bonus' data-bonus="${v[0]}" value="${ob.bonus}" min="0" /></td>
                <td><input type='text' name='info' data-info="${v[0]}" value="${ob.info}" /></td>
                <td><img src="/img/${v[1].img}"  /></td>
                <td><input class='btn btn-primary ' onclick='delProductToOrder(${v[0]},${place})' value='X' readonly /></td>
                </tr>`;
        }
    }
    divCart.push(d);

    divCart.push("</tbody></table>");

    if(document.getElementById(place)!=null){

        document.getElementById(place).innerHTML = divCart.join('');
    }else{
        console.log("error",place);
    }

    document.querySelectorAll("input[name='metr']").forEach(el => el.addEventListener("change", function(){metrListener(this,place);}));

    document.querySelectorAll("input[name='bonus']").forEach(el => el.addEventListener("change",  function(){bonusListener(this,place)}));

    document.querySelectorAll("input[name='info']").forEach(el => el.addEventListener("change",  function(){infoListener(this,place)}));
}

// для createProductTableToOrder input metr
function metrListener(el,place) {

    let mtr=el;

    let res = mtr.value;

    let id = mtr.dataset.metr;

    let redact = productToCartMap.get(+id);

    if((redact.product_balance-res)>=0){

        if (redact.metr < +res) {

            redact.product_balance -= +res - redact.metr;
            redact.metr = +res;

        } else if (redact.metr > +res) {

            redact.product_balance += +redact.metr - res;
            redact.metr = +res;

        }
        redact.summ=redact.metr*redact.sellingPrice;

        productToCartMap.set(+id, redact);

        updateOrderBalance(productToCartMap, redact);

        summOrder(productToCartMap);

        createProductTableToOrder(productToCartMap,place);

        addProductToCartValue(productToCartMap);
    }
}

// для createProductTableToOrder input bonus
function bonusListener(el,place) {

    let res = el.value;

    let id = el.dataset.bonus;

    let redact = productToCartMap.get(+id);

    redact.bonus = res;

    redact.summ=redact.metr*redact.sellingPrice-redact.bonus;

    productToCartMap.set(+id, redact);

    summOrder(productToCartMap);

    updateOrderBalance(productToCartMap, redact);

    createProductTableToOrder(productToCartMap,place);

    addProductToCartValue(productToCartMap);
}

// для createProductTableToOrder input info
function infoListener(el,place) {

    let res = el.value;

    let id = el.dataset.info;

    let redact = productToCartMap.get(+id);
    redact.info = res;

    productToCartMap.set(+id, redact);

    createProductTableToOrder(productToCartMap,place);

    addProductToCartValue(productToCartMap);
}

// сумма заказа
function summOrder(productToCartMap) {

    let summ = 0;

    for (let v of productToCartMap.entries()) {

        if (v[1].metr > 0) {

            summ += v[1].metr * v[1].sellingPrice - v[1].bonus;
        }
    }
    titleSumm.innerHTML =orderSumm+"<b>"+summ+"</b>" ;
}

// остаток
function updateOrderBalance(productToCartMap, ob) {

    let summ = 0;

    for (let v of productToCartMap.entries()) {

        if (ob.id === v[1].id) {

            v[1].product_balance = ob.product_balance;
        }
    }
}

// удаление для createProductTableToOrder
function delProductToOrder(id,place) {

    let ob = productToCartMap.get(id);

    let atr=place.getAttribute("id");

    ob.product_balance += ob.metr;

    ob.metr = 0;

    ob.summ=0;

    checkMetr(productToCartMap, ob);

    updateOrderBalance(productToCartMap, ob);

    productToCartMap.delete(id);

    summOrder(productToCartMap);

    if (productToCartMap.size > 0) {

        createProductTableToOrder(productToCartMap,atr);

        addProductToCartValue(productToCartMap);
    } else {
        if (ind > 0) {

            ind = 0;
        }
        document.getElementById(atr).innerHTML = '';

        save_cart.setAttribute("disabled", "disabled");
    }
}

// создание списка товаров
function addProductToCartValue(data){

    cartMap=[];

    if(cartValue){cartValue.value='';}

    for (let v of data.entries()) {

        if(v[1].metr>0){
           console.log((v[1].product_balance).toFixed(2));
            let ob={
                cartId:null,
                orderId:orderId,
                productId:v[1].id,
                productName:v[1].name,
                sellingPrice:v[1].sellingPrice,
                sizeProduct:+(v[1].sizeProduct).toFixed(2),
                balance:+(v[1].product_balance).toFixed(2),
                siz:v[1].metr,
                summ:v[1].summ,
                discountPrice:v[1].bonus,
                infoCart:v[1].info
            };

            cartMap.push(ob);
        }
    }

    if(cartMap.length>0){

        if(cartValue){
            cartValue.value=JSON.stringify({"cart":cartMap});
        }
        controlElementSaveCart();
    }
}


if(siz_inpt){

    siz_inpt.addEventListener("input",function(){

        if(siz_inpt.value>0){

            btn_form.disabled=false;

            updateSumm();
        }else{

            btn_form.disabled=true;
        }
    });
}

// устанавливает остаток для  modalShow input[name='balance_product']"
function updateBalance(){

    if(balanceProduct_inpt){

    balanceProduct_inpt.value=balance;//.toFixed(2)
    }
}


if(upSumm){ upSumm.forEach(el=>el.addEventListener("input",updateSumm));}

//сумма заказа и остаток
function updateSumm(){

    let siz=+siz_inpt.dataset.siz;

    let balanc_temp=+balanceProduct_inpt.dataset.balance;

    let metr=+(siz_inpt.value - siz);

    balance=0;

    if(metr<0){

        balance=balanc_temp+metr*-1;
    }else if(metr===0){

        balance=balanc_temp;
    }else if(metr>0){

        balance=balanc_temp-metr;
    }
    summ_inpt.value=siz_inpt.value*salle-discount_price_inpt.value;

    balance_span.innerHTML="Остаток: "+balance.toFixed(2);

    updateBalance();
}

// редактирование товара в заказе
function modalShow(id) {

    modal.style.display = "block";

    let request = new XMLHttpRequest();

    request.open('GET', '/rest/cart-view/' +id);

    request.send();

    request.onload = function () {

        if (request.status === 200) {

            let data = JSON.parse(request.response);
            let id_inpt=document.querySelector("input[name='cart_id']");
            let order_id_inpt=document.querySelector("input[name='order_id']");
            let product_id_inpt=document.querySelector("input[name='product_id']");
            let product_name_inpt=document.querySelector("input[name='product_name']");
            let salePrice_inpt=document.querySelector("input[name='salePrice']");
            let sale_price_span=document.querySelector(".sale_price");

            sale_price_span.innerHTML="Ціна продажу: "+data.sellingPrice;
            salePrice_inpt.value=data.sellingPrice;

            let info_cart_inpt=document.querySelector("textarea[name='info_cart']");
            balance=data.balance.toFixed(2);
            balance_span.innerHTML="Остаток: "+balance;

            id_inpt.value=data.cartId;
            order_id_inpt.value=data.orderId;
            product_id_inpt.value=data.productId;
            product_name_inpt.value=data.productName ;

            salle=data.sellingPrice;
            siz_inpt.value=data.siz ;
            siz_inpt.setAttribute("data-siz",data.siz);
            summ_inpt.value=data.summ ;
            balanceProduct_inpt.value=data.balance.toFixed(2);
            balanceProduct_inpt.setAttribute("data-balance",data.balance);
            size_sale=data.sallingProduct;
            size_product=data.sizeProduct;
            discount_price_inpt.value=data.discountPrice ;
            info_cart_inpt.value=data.infoCart ;

            updateBalance();
        }
    }
}


if(spanMyModal) {

    spanMyModal.onclick = function () {

        modal.style.display = "none";
    };
}


if(spanAddCartToOrder){

    spanAddCartToOrder.onclick = function() {

        addCartToOrder.style.display = "none";
    };
}
let positionList=0;
//== ADD CART TO ORDER ==
function  addCartToOrderShow(order_id, order_summ) {

    orderId=order_id;

    if(addCartToOrder){

        addCartToOrder.style.display = "block";

        orderSumm+= order_summ + " + ";

        if(titleSumm){

            titleSumm.innerHTML = orderSumm + 0;
        }
        let request = new XMLHttpRequest();
let url="/rest/product-list/"+positionList;
        request.open('GET', url);

        request.send();

        request.onload = function () {

            if (request.status === 200) {

                let div = document.querySelector("#productList").innerHTML = '';

                let data = JSON.parse(request.response);

                createProductList(data,"productList");
            }
        }
    }else{

        console.log("addCartToOrder not found");
    }
}

//таблица для добавления к заказу
function createProductList(data,place){
console.log(data);
    let tab = ["<table id='productTable' class='table-modal'><thead>" +
    "<tr><th class='td-hidden'>id</th>" +
                "<th class='table-text'>назва</th><th>остаток</th>" +
                "<th>ціна</th>" +
    "<th>img</th></tr></thead> <tbody>"];

    for (let i = 0; i < data.length; i++) {

        let productId1 = data[i].id;

        let product = data[i];
positionList=data[i].positionList;
        productMap.set(productId1, createObjectProduct(product));

        let tr = "<tr>" +
            "<td class='td-hidden'>"+data[i].id+"</td>" +
            "<td ><div class='tag'>" + data[i].name + "</div></td>" +
            "<td>" + (data[i].product_balance).toFixed(2) + "</td>" +
            "<td>" + data[i].sellingPrice + "</td>" +
            "<td><img src=/img/"+ data[i].img + "/></td>" +
            "</tr>";
        if (tr.length > 4) {
            tab.push(tr);
        }
    }
    tab.push("</tbody></table>");
let up="<a>";
    div = document.getElementById(place).innerHTML = tab.join('');

    document.querySelectorAll("#productTable tr").forEach(e => e.addEventListener("click", addProduct));
}

//создание обьекта для таблицы товара
function createObjectProduct(ob) {

    let ordId1=orderId===0?null:orderId;

    return {
        id: ob["id"],
        orderId:ordId1,
        name: ob["name"],
        sizeProduct: ob["sizeProduct"],
        product_balance: ob["product_balance"],
        sellingPrice: ob["sellingPrice"],
        summ : 0,
        color: ob["color"],
        metr: 0,
        bonus: 0,
        info: '',
        img:ob.img
    };
}

//добавить товар
function addProduct() {

    ++ind;

    let product = this.innerHTML;

    if (product.indexOf("th") < 0) {

        document.querySelector("#productAddCartList").innerHTML = '';

        let productT = product.split("</td>")[0];

        let prd=productT.substring(productT.indexOf('>')+1, productT.length);

        let productToCart = productMap.get(+prd);

        let ob = checkBalance(productToCartMap,createObjectProduct(productToCart) );

        if(ob!=null) {

            productToCartMap.set(ind, ob);
        }

        createProductTableToOrder(productToCartMap,"productAddCartList");
    }
}

//проверить остаток с учетом добавленого
function checkBalance(productToCartMap, ob) {

    for (let v of productToCartMap.entries()) {

        if (ob.id === v[1].id) {

            if(v[1].product_balance>0){

                if (ob.product_balance > v[1].product_balance) {

                    ob.product_balance = (v[1].product_balance).toFixed(2);
                } else {

                    v[1].product_balance = ob.product_balance.toFixed(2);
                }
            }else return null;
        }
    }
    return ob;
}

//востановить остаток при удалении из таблицы товаров
function checkMetr(productToCartMap, ob) {

    for (let v of productToCartMap.entries()) {

        if (ob.id === v[1].id && ob.metr !== 0) {

            v[1].product_balance = ob.product_balance.toFixed(2);
        }
    }
}

// добавление товара к заказу
function saveCart(){

    let data=JSON.stringify(cartMap);

    let csrf =document.querySelector('#cart_csrf').value;

    let url="/order/add-cart";

    let method="POST";

    try {
        const response = fetch(url,
            {
                method:method ,
                body: data,
                headers: {
                    'X-CSRF-TOKEN': csrf,
                    'Content-Type': 'application/json'
                    // 'Content-Type': 'application/x-www-form-urlencoded'
                }
            });

        cartMap.length=0;

        if(cartValue){

            cartValue.value='';
        }
        if( document.querySelector("#productAddCartList")){

            document.querySelector("#productAddCartList").innerHTML='';
        }
        addCartToOrder.style.display = "none";

        location.reload(); // перезагружаем страницу

    } catch (error) {
        console.error('Ошибка:', error);
    }
}

// переключение активности кнопки по условию
function controlElementSaveCart(){

    if(user_id){

        if(dat_dispatch.value.length>0&&cartMap.length>0&&user_id.value.length>0&&titleSumm.textContent.length>15){

            save_cart.removeAttribute("disabled");
        }else{

            save_cart.setAttribute("disabled", "disabled");
        }
    }
}


let rw=0;
let pag=0;
let dt='';
$('.select_listUser').scroll(function(e){
    let $t=$(this);
    ++rw;
    if ($t.height()+$t.scrollTop()-$t.prop('scrollHeight')>0) return;

    console.log(rw);
    if(rw>14){
        add_rand_opt($t);
        rw=0;
    }

});

function add_rand_opt($t) {
    let url="/rest/userList/?page="+pag;
    newFetch(url,null,"GET",null);
    if(dt!==''){
        console.log(dt);
        console.log(dt.length);
        if(dt.length>0){
            for(let i=0;i<dt.length;i++){
                let tex=dt[i].username+" | "+dt[i].name;
                $('<option>',{text: tex}).val(dt[i].id).appendTo($t);
            }
            pag++;
        }else{
            pag=0;
        }
        console.log("pag"+pag);
        dt='';



    }
    //console.log(data.length);
    //$('<option>',{text: Math.random()}).val(Math.random()).appendTo($t);
    // if(data.length === 0){
    //    pag=0;
    // }else {}
    // pag++;
    // console.log("pag"+pag);

}

function newFetch(url,body,method,csrf){

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
        method: method,
        body: body,
        headers: {
            'X-CSRF-TOKEN': csrf,
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
        .then(status)
        .then(json)
        .then( data=>{dt=data})
        .catch(function (error) {
            console.log('error', error)
        })
}

//===========================order.js ============
// if(dat_dispatch) {
//
//     dat_dispatch.addEventListener('click', function () {
//         dat_dispatch.value='';
//         let options = {
//             year: 'numeric',
//             month: 'numeric',
//             day: 'numeric',
//             timezone: 'UTC'
//         };
//
//         let dat = new Date();
//
//         dat_dispatch.value += dat.toLocaleString('ru', options);
//
//         //controlElementSaveCart();
//     });
// }
// if(place1.indexOf("id")>0){
//     let place =place1.indexOf("id")>0?place1.getAttribute("id"):place1;
// }