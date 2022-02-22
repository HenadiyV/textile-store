const changeCart = document.getElementById("changeCart");
const search_user = document.getElementById("search_user");
const userSelect= document.getElementById("userSelect");
const productSelect= document.getElementById("productSelect");
const new_product= document.getElementById("new_product");
const ord_id= document.getElementById("ord_id");
const inp_product_size=document.querySelectorAll("input[name='product-size']");
const inp_product_discountPrice=document.querySelectorAll("input[name='product-discountPrice']");
const text_product_infoCart=document.querySelectorAll("textarea[name='infoCart']");
const order_summ=document.getElementById("order-summ");

let discon=new Map();
let summOrderMap=new Map();


    function  modalShowCart(){

        modalCart.style.display="block";
    }

    userSelect.addEventListener("change",changeUserFetch);


    function changeUserFetch(){

        let  selectedText = this.options[this.selectedIndex].value;

        let url="/rest/user/"+selectedText;

        myFetchFunctionEditOrder(url,null,"Get",'','change_user');
    }


    function changeUser(data1){

        let data=JSON.parse(data1);
        console.log(data);
        /*phon
addr
postOffice
{"id":3,"name":"Беседа Елена","username":"Beseda Elena","email":"3emai@l.test","active":true,"pageNum":0,
"phoneList":
[{"id":3,"userId":null,"info":null,"phone":"+38(050)217-58-28","active":false}],
"addressList":
[{"id":3,"userId":null,"region":" Луганская обл.","district":" ","city":" г.Северодонецк","address":"","postCode":"93405","info":null,"active":true}],
"postOfficeList":
[{"id":3,"userId":null,"postOffice":"93405","active":false,"info":null}]}*/
        /*phon
nickV
nameV
addr*/
        const nickV = document.getElementById("nickV");
        const nameV = document.getElementById("nameV");
        const phon_select = document.getElementById("phon");
        const addr_select = document.getElementById("addr");
        const postOffice_select = document.getElementById("postOffice");

        nickV.innerText=data.username;
        nameV.innerText=data.name;

        let phoneOp="";
        let phoneId="";
        for(ph of data.phoneList){
            if(ph.active){
                phoneId=ph.id;
            }
            phoneOp+="<option value="+ph.id+">"+ph.phone+"</option>";
        }
        phon_select.innerHTML=phoneOp;

        let addressOp="";
        let addrId="";
        for(addr of data.addressList){
            if(addr.active){
                addrId=addr.id;
            }
            let adr ="";
            if(addr.city){
                adr =addr.city;
            }
            if(addr.address){
                if(adr!==""){
                    adr +=" | "+addr.address;
                }else{
                    adr +=addr.address;
                }
            }
            if(addr.info){
                if(adr.indexOf('|')>-1){
                    adr +=" | "+addr.info;
                }else{
                    adr +=addr.info;
                }
            }
            addressOp+="<option value="+addr.id+">"+adr+"</option>";
        }
        addr_select.innerHTML=addressOp;

        let postOfficeOp="";
        let postOfficeId="";
        for(po of data.postOfficeList){
            if(po.active){
                postOfficeId=po.id;
            }
            postOfficeOp+="<option value="+po.id+">"+po.postOffice+"</option>";
        }
        postOffice_select.innerHTML=postOfficeOp;

        console.log(phoneId);
        console.log(addrId);
        console.log(postOfficeId);
        let us_name_order= document.querySelector("#us_name_order");
        let user_id= document.querySelector("#user_id");
        let phone_id= document.querySelector("#phone_id");
        let address_id= document.querySelector("#address_id");
        let postOffice_id= document.querySelector("#postOffice_id");
        // let order_phone_select=document.getElementById('order_phone');
        // let order_address_select=document.getElementById('order_address');
        // let order_postOffice_select=document.getElementById('order_postOffice');
        user_id.value=data.id;
        phon_select.addEventListener("change",function(){
            phone_id.value = this.options[this.selectedIndex].value;
        });
        addr_select.addEventListener("change",function(){

            address_id.value = this.options[this.selectedIndex].value;
        });
        postOffice_select.addEventListener("change",function(){
            postOffice_id.value = this.options[this.selectedIndex].value;
        });

        phone_id.value=phoneId;
        address_id.value=addrId;
        postOffice_id.value=postOfficeId;
    }

    text_product_infoCart.forEach(checkedSumm);//enabledButtonCart

    inp_product_size.forEach(checkedSumm);//enabledButtonCart

    inp_product_discountPrice.forEach(checkedSumm);//enabledButtonCart


     function checkedSumm(item){

         let elId=item.id.split("-");

        const infoCart =document.getElementById("infoCart-"+elId[1]);
        const btn =document.getElementById("btn-"+elId[1]);

         item.addEventListener("input",()=>{

            if(elId[0]==="siz"){

                sizeControl(item.id);

                orderSummControl();
            }
            if(elId[0]==="discountPrice"){

                discountPriceControl(item.id);

                orderSummControl();
            }
            if(elId[0]==="infoCart"){

                if(+infoCart.value.length!==+infoCart.dataset.info){

                     btn.removeAttribute('disabled');
                 }else{
                    btn.setAttribute("disabled", "disabled");
                }
            }
         });
     }


     function orderSummControl(){

         const summShowElements= document.querySelectorAll(".summ");

         let summEl=0;

         summShowElements.forEach(el=>{

             summEl+=Number(el.innerText);
         });
         order_summ.innerHTML="Сумма: <b>"+summEl+"</b>";
     }


    function sizeControl(item){

        let elId=item.split("-");

        const siz = document.getElementById("siz-"+elId[1]);
        const balance =document.getElementById("balance"+elId[1]);
        const btn =document.getElementById("btn-"+elId[1]);
        const summ = document.getElementById("summ-"+elId[1]);
        const priceV =document.getElementById("price"+elId[1]);

        let price=+priceV.dataset.price;

        let balanceText=balance.dataset.balance;

        let sizText=siz.dataset.size;

        let sizOld=Number(sizText);

        let res=0;

        let t=0;

             t=Number(siz.value)-Number(sizOld);

            res= Number(balanceText)-Number(t);

            balance.innerText=res.toFixed(1);

            if(Number(siz.value)!==Number(sizOld)){

                let sm=t*price;

                let summText= summ.dataset.summ;

                let summV = 0;

                 summV = Number(summText);

                let summShow=(summV+sm).toFixed(2);

                summ.innerText=summShow;

                btn.removeAttribute('disabled');
            }else{
                summ.innerText=summ.dataset.summ;

                order_summ.innerText="Сумма: "+order_summ.dataset.summ;

            btn.setAttribute("disabled", "disabled");
        }
    }


     function discountPriceControl(item){

         let elId=item.split("-");

            console.log(elId[1]);

         const summ = document.getElementById("summ-"+elId[1]);
         const discountPrice = document.getElementById("discountPrice-"+elId[1]);
         const btn =document.getElementById("btn-"+elId[1]);

         let summTempText=summ.innerText.match(/\d+/s);

         if(discountPrice.value>0){

             let sumSum=Number( summTempText[0]);

             let r=discon.get(item);

             if(r>0){

                 sumSum+= +discon.get(item);

                 discon.set(item,discountPrice.value);
             }else{

                 discon.set(item,discountPrice.value);
             }
             sumSum-=discountPrice.value;

             summ.innerText=sumSum;
         }
         else{
          if(discon.size>0){

              let res = discon.get(item);

              let summTemp = Number(summTempText[0])+Number(res);

              summ.innerText=summTemp;

              discon.delete(item);
          }
         }
         if(discon.has(item)){

             btn.removeAttribute('disabled');
         }else{

             btn.setAttribute("disabled", "disabled");
         }
     }


    if(search_user){
        search_user.addEventListener("input",searchUser);
    }


    function searchUser (){

        let us=this.value;
        let data, url;

        if(us.length>0){

            url= "/rest/search-user/"+us;
        }else{

            url= "/rest/user-list/"+0;
        }
        data = restData("GET",url);

        initUserSelect(data,userSelect)
    }


    function initUserSelect(data,elem){

        if(data!=null){

            let opt="<option>Список користувачів</option>";

            for(let i=0;i<data.length;i++){

                opt+="<option  value="+data[i].id+">"+data[i].name+" | "+data[i].username+"</option>"
            }
            elem.innerHTML=opt;
        }
    }


    if(search_product){
        search_product.addEventListener("input",searchProduct);
    }


    function searchProduct(){

        let vl=this.value;
        let data, url;

        if(vl.length>0){

            url ="/rest/search-product-list/"+vl;
        }else{

            url ="/rest/product-list/"+0;
        }
        data =restData("GET",url);

        initProductSelect(data,productSelect)
    }


    function initProductSelect(data,elem){

        elem.innerHTML="";
        let opt="<option>Список товарів</option>";

        for(let i=0;i<data.length;i++){

            opt+="<option  value="+data[i].id+">"+data[i].name+" | "+data[i].color+"</option>"
        }
        elem.innerHTML=opt;
    }


    if(productSelect){
        productSelect.addEventListener("change",addProductToOrder)
    }
    //document.querySelector("body").addEventListener("click",listUser);

    function addProductToOrder(){

        let  selectedText = this.options[this.selectedIndex].value;
        let data,url;

        url = "/rest/product/"+selectedText;

        data = restData("Get",url);

        initProduct(data);
    }

    function initProduct(data){

        let ordId=ord_id.value;
        let token=document.querySelector("input[name='_csrf']").value;

        let info=data.infoCart!==undefined?data.infoCart:' ';

        new_product.innerHTML='';

        new_product.innerHTML= `<form action="/order/add-cart-to-order" method="post" >
                 <div class="row" style="border:1px solid">
                    <div class="col-sm-4 col-md-4 col-lx-4">
                        <div ><i class="p-left">IdCart:   </i><b class="p-right">0</b></div>
                        <div>  <i class="p-left">IdProduct:</i>    <b class="p-right">${data.id}</b>
                        <input type="text" name="productId" value="${data.id}"/>
                        <input type="text" name="order" value="${ordId}"/>
                        </div>
                        <div><i class="p-left">Назва:</i>        <b class="p-right">${data.name}</b>
                        <input type="text" name="productName" value="${data.name}"/>
                        </div>
                        <div><i class="p-left">Ціна продажу:</i> <b class="p-right">${data.sellingPrice}</b>
                        <input type="text" name="sellingPrice"  id="addCartSellingPrice" value="${data.sellingPrice}"/>
                        </div>
                        <div><i class="p-left">Кількість:</i>     <b class="p-right">${data.sizeProduct}</b>
                        <input type="text" name="sizeProduct"  value="${data.sizeProduct}"/>
                        </div>
                        <div><i class="p-left">Залишок:</i>       <b class="p-right" id="addCartBalanceView">${data.product_balance}</b>
                         <input type="text" name="balance" id="addCartBalance" 
                                data-balance="${data.product_balance}" value="${data.product_balance}"/>
                        </div>
                    </div>
                    <div class="col-sm-5 col-md-5 col-lx-5">
                        <div><i class="p-left">Метраж:</i> 
                        <input type="number" class="p-input" id="addCartSiz" name="siz" value="0" 
                                        min="0" step="0.1" max="${data.product_balance}"/></div>
                        <div><i class="p-left">Сумма:</i>  <b class="p-right" id="addCartSummView">0</b>
                        <input type="text" class="p-input" id="addCartSumm" name="summ" value="" />
                        </div>
                        <div><i class="p-left">Знижка: </i>
                        <input type="number" class="p-input" id="addCartDiscountPrice" name="discountPrice" value="0" />
                        </div>
                        <div><i class="p-left">Додаткова інформація:</i> 
                        <textarea class="p-textarea"  name="infoCart">${info}</textarea>
                        </div>
                    </div>
                    <div class="col-sm-3 col-md-3 col-lx-3">
                        <img src="/img/${data.img}" style="width:100px"/>
                        <input type="text" class="p-input" name="img" value="${data.img}" />
                        </div>
                        <div>
                        <input type="text" name="_csrf" value="${token}" />
                       <button class="btn btn-success" type="submit">Зберегти</button>  
                    </div>
                </div>
            </form>`;

        const addCartSiz=document.getElementById("addCartSiz");
        const addCartDiscountPrice=document.getElementById("addCartDiscountPrice");

        if(addCartSiz&&addCartDiscountPrice){
            addCartSiz.addEventListener("input",getSumm);
            addCartDiscountPrice.addEventListener("input",getSumm);
        }
    }

    function getSumm(){

        const addCartSiz=document.getElementById("addCartSiz");
        const addCartDiscountPrice=document.getElementById("addCartDiscountPrice");
        const addCartSellingPrice=document.getElementById("addCartSellingPrice");
        const addCartSummView=document.getElementById("addCartSummView");
        const addCartBalance=document.getElementById("addCartBalance");
        const addCartBalanceView=document.getElementById("addCartBalanceView");


        let balanceOld=addCartBalance.dataset.balance;

        let summ=+(addCartSiz.value*addCartSellingPrice.value-addCartDiscountPrice.value);

        let balance=+(balanceOld-addCartSiz.value);

        let orderSummOld=+order_summ.dataset.summ;

        let summOrd=orderSummOld+summ;

        addCartSumm.value=summ;

        addCartBalance.value=balance;

        addCartSummView.innerText=summ;

        addCartBalanceView.innerText= balance;

        order_summ.innerText="Сумма: "+summOrd;

    }


    function updateCart(cartId){

        const siz= document.getElementById("siz-"+cartId);
        const discountPrice= document.getElementById("discountPrice-"+cartId).value;


        let infoCart= document.getElementById("infoCart-"+cartId).value;
        let sV=0;

        if(siz.value!==''){

            sV=siz.value;
        }else{

            sV=siz.dataset.size;
        }
        let ob={"cartId":cartId,"siz":sV,"discountPrice":discountPriceV,"infoCart":infoCart};

        let data=JSON.stringify(ob);

        let url ="/rest/cart-update/";

        let token=document.querySelector("input[name='_csrf']").value;

        myFetchFunctionEditOrder(url,data,"Post",token,"update-cart");
    }


    function restData(method,url){

        let request = new XMLHttpRequest();

        request.open(method, url,false);

        request.send();

        let status=request.status;

        if (status ===200){

            return JSON.parse(request.response);
        }
    }


    function myFetchFunctionEditOrder(url,data,method,csrf,point){

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
                    viewData(data,point);
                });
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }


     function viewData(data,point){
       // console.log(data);
         /*{
         "cartId":451,
         "orderId":null,
         "productId":null,
         "productName":null,
         "sellingPrice":null,
         "sizeProduct":null,
         "balance":null,
         "siz":3.0,
         "summ":null,
         "discountPrice":5.0,
         "infoCart":"
          ddd222","img":null}*/
         switch(point){

             case "update-cart": break;

             case "change_user": changeUser(data); break;
         }
     }
// if(userL){
// // onclick="modalShowCart(${data.productId})"<a class="btn btn-danger" href="/order/delete-cart/${data.productId}" > &#8203; Видалити &#8203; </a>
//     userL.addEventListener("change", function (){
//
//                 let  selectedText =+this.options[this.selectedIndex].value;
//
//                 let ord=+document.querySelector("#ord_id").value;
//
//                 document.location.href = "/order/update-order-user/"+ord+"/"+selectedText;
//             }
//     );
//}


function initOrder(){

    let phon=  document.querySelector("#phon");

    let addr=  document.querySelector("#addr");

    let postOffice=  document.querySelector("#postOffice");

    let status=  document.querySelector("#status");

    let  selectedphon = phon.options[phon.selectedIndex].value;

    let  selectedaddr = addr.options[addr.selectedIndex].value;

    let  selectedpostOffice = postOffice.options[postOffice.selectedIndex].value;

    let  selectedStatus = status.options[status.selectedIndex].value;

    document.querySelector("input[name='phone_id']").value=selectedphon;

    document.querySelector("input[name='address_id']").value=selectedaddr;

    document.querySelector("input[name='post_office_id']").value=selectedpostOffice;

    document.querySelector("input[name='dat_dispatch']").value=document.querySelector("#dat_dispatch").value;

    document.querySelector("input[name='status']").value=selectedStatus;

    document.querySelector("input[name='delivery']").value=document.querySelector("#delivery").value;

    document.querySelector("input[name='info_order']").value=document.querySelector("#infoOr").value;
}
//======================edit-order.js==============
//     let sizTemp=Number(sizOld);
//     let res=Number(siz.value)===Number(sizTemp)?true:false;
//
//      console.log(res);
//         if(!res){
//             summNew=siz.value*price-discountPrice.value;
//
//             summOrdOld=(summOrdOld-summOld)+summNew;
//
//             summ.innerText=summNew.toFixed(2);
//
//             order_summ.innerHTML="Сумма: <b>"+(summOrdOld.toFixed(2))+"</b>";
//
// console.log("yes");
//             balance.innerText=balanceOld-siz.value+sizOld;
//
//             btn.removeAttribute('disabled');
//         }else {//if(siz.value===sizOld)
//             order_summ.innerText="Сумма: "+order_summ.dataset.summ;
//
//             summ.innerText=summ.dataset.summ;
//
//             balance.innerText=balance.dataset.balance;
//             console.log("no");
//             btn.setAttribute("disabled", "disabled");
//         }


// if(sizOld>siz.value){
//  res= Number(balanceText)+(sizOld-siz.value);//
// balance.innerText=res;
// }else{
//     let t=siz.value-sizOld;
//     console.log(t);
//     res= Number(balanceText)-t;//
//     balance.innerText=res;
// }
// if(res>0){
//     btn.removeAttribute('disabled');
// }else{
//     btn.setAttribute("disabled", "disabled");
// }
//console.log(res);
//console.log(typeof sizText);
//if()
// let sizTemp=Number(sizOld) ;
// let res=Number(siz.value)===Number(sizTemp)?true:false;
//
// console.log(res);
// if(!res){
//     summNew=siz.value*price-discountPrice.value;
//
//     summOrdOld=(summOrdOld-summOld)+summNew;
//
//     summ.innerText=summNew.toFixed(2);
//
//     order_summ.innerHTML="Сумма: <b>"+(summOrdOld.toFixed(2))+"</b>";
//
//     console.log("yes");
//     balance.innerText=balanceOld-siz.value+sizOld;
//
//
// }else {//if(siz.value===sizOld)
//     order_summ.innerText="Сумма: "+order_summ.dataset.summ;
//
//     summ.innerText=summ.dataset.summ;

// balance.innerText=balance.dataset.balance;
// console.log("no");
//
//
//}


// let summOrdTempText=order_summ.innerText.match(/\d+/s);
// let summOrdTemp = Number(summOrdTempText[0]);
// //let tempDiscountPrice
//     if(discountPrice.value>0){
//         //summNew=summNew;
//         summ.innerText=siz.value*price-discountPrice.value;
//         summOrdTemp-=discountPrice.value;
//         order_summ.innerHTML="Сумма: <b>"+(summOrdTemp.toFixed(2))+"</b>";
//
//     }else{
//         summNew=siz.value*price;
//         summOrdOld=(summOrdOld-summOld)+summNew;
//
//         summ.innerText=summNew.toFixed(2);
//
//         order_summ.innerHTML="Сумма: <b>"+(summOrdOld.toFixed(2))+"</b>";
//
//
//
//         // order_summ.innerText="Сумма: "+order_summ.dataset.summ;
//         // balance.innerText=balance.dataset.balance;
//
//       //  btn.setAttribute("disabled", "disabled");
//     }
//     btn.removeAttribute('disabled');


// btn_form.addEventListener('mousemove',initOrder);

// function enabledButtonCart(item){
//
//     item.addEventListener("input",()=>{
//         // const discountPrice= document.getElementById("discountPrice-"+cartId).value;
//         // const summ= document.getElementById("summ"+cartId).value;
//         // const balance= document.getElementById("balance"+cartId).value;
//         //let discountPriceV = discountPrice.value;
//
//         // let balanceV=balance.innerText;
//         // let bV=+balanceV-(+sV);
//         // balance.innerText=(bV);console.log(typeof bV);
//
//         let elId=item.id.split("-");
//
//         let summ=document.getElementById("summ"+elId[1]);
//         console.log(summ);
//
//         let balance=document.getElementById("balance"+elId[1]);
//         console.log(balance);
//
//         let price=document.getElementById("price"+elId[1]);
//         console.log(price);
//         let sum=summ.dataset.summ;
//         let summOld=summ.dataset.summ;
//         let btn="btn-"+elId[1];
//
//         let nameEl=elId[0]+'-';
//
//         if(nameEl==="siz-"){
//
//             let ds_size=+item.dataset.size;
//
//             let vl_size=+item.value;
//
//             if(ds_size!==vl_size){
//
//                 document.getElementById(btn).removeAttribute('disabled');
//                 let bal=balance.dataset.balance;
//                 let balNew=bal-vl_size;
//                 // console.log(bal);
//                 // console.log(balNew);
//                 balance.innerText=balNew;
//                 let pr=price.dataset.price;

//                 if(vl_size>0){
//                     sum=+(vl_size*pr);
//                 }else{
//                     sum=summOld;
//                 }
//                 summ.innerText=sum;
//             }
//         }else
//         if(nameEl==="discountPrice-"){
//
//             let ds_discountPrice=+item.dataset.discountPrice;
//
//             let vl_discountPrice=+item.value;
//
//             if(ds_discountPrice!==vl_discountPrice){
//                 sum-=vl_discountPrice;
//                 summ.innerText=sum;
//                 document.getElementById(btn).removeAttribute('disabled');
//             }
//         }else
//         if(nameEl==="infoCart-"){
//
//             let ds_infoCart=+item.dataset.info;
//
//             let vl_infoCart=+item.value.length;
//
//             if(ds_infoCart!==vl_infoCart){
//                 document.getElementById(btn).removeAttribute('disabled');
//             }
//         }
//         else{
//             document.getElementById(btn).setAttribute("disabled", "disabled");
//         }
//     })
// }