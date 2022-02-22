<#import "parts/common.ftlh" as c>
<@c.page>

<div class="container">
    <h4 class="centr">Замовлення</h4>

    <a class="btn btn-primary mb-2"  href="/order/add-order" > Додати замовлення </a>
    <a class="btn btn-primary mb-2" data-bs-toggle="collapse" href="#searchBlock" role="button" aria-expanded="false" aria-controls="searchBlock">Пошук</a>
    <div class="collapse" id="searchBlock">
        <div class="card card-body">
            <div></div>
        <div>
            <input type="text" name="search_order" placeholder="search order"/><br/>
                <div class="row">
                   <div class="col-sm-4 col-md-4 col-xl-3 mt-1">
                       <label> Пошук по ніку
                           <input type="radio" name="search_type" value="2" checked="checked"/>
                       </label><br/>
                       <label> Пошук по імені
                            <input type="radio" name="search_type" value="1" />
                       </label>
                   </div>
                   <div class="col-sm-4 col-md-4 col-xl-3 mt-1">
                       <label>По даті відправки
                            <input type="radio" name="search_type" value="4"/>
                       </label><br/>
                       <label>По даті створеня
                            <input type="radio" name="search_type" value="3"/>
                       </label>
                   </div>
                   <div class="col-sm-4 col-md-4 col-xl-3 mt-1">
                        <label>По номеру телефона
                            <input type="radio" name="search_type" value="5"/>
                        </label><br/>
                        <label>Пошук по TTH
                            <input type="radio" name="search_type" value="6"/>
                        </label>
                   </div>
                   <div class="col-sm-4 col-md-4 col-xl-3 mt-1">
                        <label>По статусу
                          <input type="radio" name="search_type" id="r_sel_stat" value="7"/><br/>
                             <select  id="sel_stat"><option>Виберіть статус</option>
                                <#list statuses as status>
                                   <option >${status.status}</option>
                                </#list>
                             </select>
                        </label>
                   </div>
                </div>
                    <div class="centr mt-2">
                   <a class="btn btn-primary" id="searchUrl" href="">Шукати</a>
                    </div>
            </div>
        </div>
    </div>


<#include "parts/orderList.ftlh">


<#--<@p.orders orderList ></@p.orders>-->
<#--<div><span>product_name</span></div>-->
<#--<div class="mt-1"><span>info_cart</span><br/></div>-->
        <div id="myModal" class="modal">

            <div class="modal-content">

                <div class="modal-header">
                    <span class="close" id="closeMyModal">&times;</span>
                    <h2>Редагувати товар в замовлені</h2>
                </div>

                <div class="modal-body">

                    <div class="title-cart">
                        <span class="sale_price"></span><br/>
                        <span class="balance"></span>
                    </div>

                    <form id="cartUp" action="/order/update-cart" method="post" >

                        <div class="row">

                            <div class="col-6 mt-2">

                                    <label>Назва<br/>
                                        <input type="text"   name="product_name"   readonly/>
                                    </label><br/>

                                    <label>Додаткова інформація<br/>
                                        <textarea name="info_cart" rows="4" cols="30"></textarea>
                                    </label>
                                <input type="hidden"   name="cart_id"  />
                                <input type="hidden"   name="order_id" />
                                <input type="hidden"   name="product_id"   />
                                <input type="hidden"   name="salePrice"  />
                                <input type="hidden"   name="balance_product" min="0"  />
                            </div>
                            <div class="col-6 mt-2">
                                        <label>Метраж:<br/>
                                            <input type="number" class="upSumm" min="0" name="siz"  />
                                        </label><br/>

                                        <label>Знижка<br/>
                                            <input type="number" class="upSumm"  name="discount_price"   step="1"/>
                                        </label><br/>
                                        <label>Сумма<br/>
                                        <input type="number"   name="summ"   readonly/>
                                    </label><br/>
                                </div>
                            </div>
                            <div class="col-12 mt-2">
                                <div class="row">
                                   <div class="col-8">
                                   </div>
                                   <div class="col-4">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                        <button type="submit" id="btn_form" class="btn btn-primary" >Зберегти</button>
                                    </div>
                                </div>
                             </div>
                    </form>

                        </div>
                </div>

                <div class="modal-footer">
                    <#--<h3>Футер модального окна</h3>-->
                </div>

            </div>


    <#-- == ADD CART TO ORDER == -->
        <div id="addCartToOrder" class="modal">

            <div class="modal-content">

                <div class="modal-header">
                    <span class="close" id="closeAddCartToOrder" >&times;</span>
                    <span id="order_summ"></span>
                    <h3 >Додати до замовлення</h3><br/>
                </div>

                <div class="modal-body">

                        <div class="row">

                            <div class="col-6">
                                <div><label for="search_product"> Пошук товару </label>
                                    <input type="text" id="search_product"> </div>
                            </div>
                            <div class="col-6">
                                <input type="hidden" name="_csrf" id="cart_csrf" value="${_csrf.token}"/>
                                <button   class="btn btn-primary" id="save_cart" onclick="saveCart()" disabled="disabled">Зберегти</button>
                            </div>

                        </div>
                        <div class="row">

                            <div class="col-4 mt-1">
                               <div id="productList"></div>
                                <input type="hidden" name="order_id" />
                            </div>
                            <div class="col-8 mt-1">
                               <div id="productAddCartList"></div>
                            </div>

                        </div>
                </div>
            </div>
                <div class="modal-footer">
                    <#--<h3 style="color: black">Футер модального окна</h3>-->
                </div>
            </div>

</div>

<script src="/static/js/order.js"></script>
<script>

const search_order= document.querySelector("input[name='search_order']");
const searchUrl= document.getElementById("searchUrl");
const radioButtonList= document.querySelectorAll("input[name='search_type']").forEach(radioListener);
  let typ=1;
let res="";

function radioListener(el){
    el.addEventListener("change",()=>{

        typ = el.value;
        console.log(typ);
        console.log(search_order.value);
        let tex=search_order.value;
        if(search_order.value.length!==0){
        res= "/order/search/"+typ+"/"+(search_order.value).trim();
            searchUrl.href=res.trim();
            console.log(res);
        }
    });

}
if(search_order){
    search_order.addEventListener("change",function(){
        let tex=this.value.trim();
         res= "/order/search/"+typ+"/"+tex;
        searchUrl.href=res.trim();
        console.log(res);
    });

}
    const sel_stat=document.getElementById("sel_stat");
    if(sel_stat){

        sel_stat.addEventListener("change",function(){
            let rad= document.querySelector("input[name='search_type']:checked");

            let tex=this.options[this.selectedIndex].text;
            if(rad.value==="7"&&tex!=="Виберіть статус"){
                search_order.value=tex.trim();
                res= "/order/search/"+7+"/"+tex;
                console.log(res);
                searchUrl.href=res.trim();
            }
        });

    }
</script>

</@c.page>