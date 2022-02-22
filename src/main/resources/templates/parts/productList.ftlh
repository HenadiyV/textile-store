<#include "security.ftlh">
<#import "pager.ftlh" as p>
<@p.pager url page ></@p.pager>
<style>
    select,
    input[type="text"] {
        padding: 2px;
        border: 1px solid #ccc;
        font-size: 15px;
        -webkit-appearance: none;
    }
    select {padding-right: 10px;}
    .select {display: inline-block; position: relative;}
    .select:after {
        display: block;
        position: absolute;
        right: 5px;
        content: '▼';
        font-size: 0.6rem;
        top: 50%;
        pointer-events: none;
        transform: translateY(-50%);
    }
    .number {
        display: inline-block;
        position: relative;
        width: 100px;
    }
    .number input[type="number"] {
        display: block;
        height: 32px;
        line-height: 32px;
        width: 100%;
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        text-align: center;
        -moz-appearance: textfield;
        -webkit-appearance: textfield;
        appearance: textfield;
    }
    .number input[type="number"]::-webkit-outer-spin-button,
    .number input[type="number"]::-webkit-inner-spin-button {
        display: none;
    }
    .number-minus {
        position: absolute;
        top: 1px;
        left: 1px;
        bottom: 1px;
        width: 20px;
        padding: 0;
        display: block;
        text-align: center;
        border: none;
        border-right: 1px solid #ddd;
        font-size: 16px;
        font-weight: 600;
    }
    .number-plus {
        position: absolute;
        top: 1px;
        right: 1px;
        bottom: 1px;
        width: 20px;
        padding: 0;
        display: block;
        text-align: center;
        border: none;
        border-left: 1px solid #ddd;
        font-size: 16px;
        font-weight: 600;
    }
</style>
<div class="container">
    <div class="row row-cols-1 row-cols-md-3 g-4 mt-1" id="product-list">
    <#list page.content as product>
        <div class="col">
            <div class="card">
                <div> <#if product.imageProducts??>
                     <#list product.imageProducts as image>
                         <#if image.showcase>
                             <div class="row">
                                 <div class="col-6">
                                     <img id="${image.id}" class="demo" src="/img/${image.imgProduct}"
                                                         width="200" height="200" style="overflow: hidden;"  alt="image" />
                                 </div>
                                 <div class="col-6">
                                     <div id="R${image.id}" class="img-zoom-result "></div>
                                 </div>
                             </div>
                         </#if>
                     </#list>
                </#if>
                </div>
                <div class="card-body">
                    <a class="btn btn-primary disabled" name="add-basket" onclick="searchProduct(#{product.id})" >Замовити</a><br/>
                    <i>#${product.id}</i>
                    <span>Назва: ${product.name}</span>
                    <span>Колір: ${product.color}</span><br/>
                    <span>Ціна: ${product.sellingPrice}</span><br/>
                  <#if isAdmin>
                  <div style="border:1px solid #9c9c9c;background: #ffffcc">
                      <span>Ціна закупки: ${product.purchasePrice}</span><br/>
                      <span>Кількість: ${product.sizeProduct}</span><br/>
                      <span>Продано: ${product.selling_size}</span><br/>
                      <span>Дата: ${product.dat?date?string('dd-MM-yyyy')}</span><br/>
                  </div>
                  </#if>
                </div>
                <div class="card-footer text-muted">
                    <#if product.description?length gt 0>
                        <b>${product.description}</b>
                    <#else>  Опис товару відсутній.
                    </#if>
                </div>
            </div>
        </div>
    <#else>
    Повідомлення відсутні
    </#list>
      </div>
    <#if user??>
        <div class="container">
            <div id="myBasketUser" class="modal">
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="close" id="closeMyBasketUser">&times;</span>
                        <h2>Оформити замовленя</h2>
                    </div>
                    <div class="modal-body">
                        <div class="title-cart">
                            <#if basketId??><a href="/basket/delete/${basketId}" class="btn btn-danger">Очистити</a></#if>
                            <div id="place-basket"> </div>
                        </div>
                        <div class="body-cart">
                            <div class="table-content-center">

                                <form action="/basket/user-ordering" method="post">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <label for="b_user">Ваше імя</label> <br/>
                                            <input type="text" name="userId" id="b_user" value="${user.id}" class="input-with"/><br/>
                                        </div>

                                        <div class="col-sm-4">
                                            <label for="b_phone">Ваш телефон</label> <br/>
                                            <select id="b_phone" name="phone"></select><br/>
                                        </div>

                                        <div class="col-sm-4">
                                            <label for="b_postOffice">Ваша пошта</label> <br/>

                                            <select id="b_postOffice" name="postOffice"></select><br/>
                                        </div>
                                    </div>

                                    <div class="centr">
                                        </div>

                                    <div class="row">
                                    <div class="col-sm-1 col-md-2 col-xl-3"></div>
                                        <div class="col-md-8 col-sm-10 col-xl-6 centr">
                                            <label for="b_address" class="centr">Ваша адресса</label> <br/>

                                            <select id="b_address" name="address" class="input-with centr"></select><br/>
                                         </div>
                                        <div class="col-sm-1 col-md-2 col-xl-3"></div>
                                    </div>

                                    <label for="b_info">Додаткова інформація до замовленя</label><br/>
                                    <textarea name="info" id="b_info" cols="30" rows="3" style="width: 50%"></textarea><br/>
                                    <input type="hidden" name="token" id="b_token_ordering"/>
                                    <input type="hidden" class="input-with" name="_csrf"  value="${_csrf.token}"/>
                                    <button class="btn btn-primary mt-1" type="submit">Замовити</button>
                                </form>

                            </div>

                            <div id="table_place" class="row mt-2"></div>
                        </div>
                    </div>
                    <div class="modal-footer">
                    <#--<h3>Футер модального окна</h3>-->
                    </div>
                </div>
            </div>
        </div>
    <#else>
        <div class="container">
            <div id="myModalBasket" class="modal">
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="close" id="closeMyModalBasket">&times;</span>
                        <h2>Оформити замовленя 2</h2>
                    </div>
                    <div class="modal-body">
                        <div class="title-cart">
                        <#if basketId??><a href="/basket/delete/${basketId}" class="btn btn-danger">Очистити</a></#if>
                            <div id="place-basket"> </div>
                        </div>
                        <div class="body-cart">
                            <div class="table-content-center">

                                <form action="/basket/ordering" method="post">
                                    <label for="username">Ваше імя</label> <br/>
                                    <input type="text" name="username" id="o_username" value=""/><br/>

                                    <label for="phone">Ваш телефон:</label> <br/>
                                    <input type="tel" name="phone" id="o_phone" autofocus="autofocus" maxlength="50"
                                           value="+38(___)___-__-__" required="required"
                                           pattern="\+38\[\(][0-9]{3}[\)]\d{3}[-]\d{2}[-]\d{2}"
                                           placeholder="+38(___)___-__-__"/><br/>

                                    <label for="info">Додаткова інформація до замовленя</label><br/>
                                    <textarea name="info" id="info" cols="30" rows="3" style="width: 50%"></textarea><br/>
                                    <input type="hidden" name="token" id="token_ordering"/>

                                    <input type="hidden" class="input-with" name="_csrf"  value="${_csrf.token}"/>
                                    <button class="btn btn-primary mt-1" type="submit" id="o_btn_ordering" disabled="disabled">Замовити</button>
                                </form>

                            </div>

                            <div id="table_place" class="row mt-2"></div>
                        </div>
                    </div>
                    <div class="modal-footer">
                    <#--<h3>Футер модального окна</h3>-->
                    </div>
                </div>
            </div>
        </div>
</#if>

    <div class="container">
        <div id="myModalOrdering" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close" id="closeMyModalOrdering">&times;</span>
                    <h2>Додати до кошика</h2>
                </div>
                <div class="modal-body">
                    <div class="title-cart">
                        <div id="place-ordering"> </div>
                    </div>
                    <div class="body-cart">
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-6 ">
                                    <div class="preview">
                                        <div class="prview-image">
                                            <img src="" id="view-img" alt="" />
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                                <div class="col-sm-6 table-content-center">

                              <form action="/basket/add-product" method="post">

                                <input type="hidden" class="input-with" name="_csrf" value="${_csrf.token}"/>
                                    <span id="place_price"></span><br/>
                                    <span id="place_summ"></span><br/>
                                    <label for="size">Введіть кількість</label> <br/>
                                    <input type="number" name="size" min=0 step="0.1" id="size" style="width: 100%"/><br/>
                                    <input type="hidden" name="productId" id="productId"/>
                                    <input type="hidden" name="price" id="price" readonly/>
                                    <input type="hidden" name="img" id="img_product" />
                                    <input type="hidden" name="token" id="token_cookie" />

                                    <label for="info">Додаткова інформація</label>
                                    <textarea name="info" id="info" cols="30" rows="3" style="width: 100%"></textarea><br/>
                                        <button class="btn btn-primary mt-1" type="submit" id="add_to_basket">Додати до кошика</button>

                              </form>

                               </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                <#--<h3>Футер модального окна</h3>-->
                </div>
            </div>

        </div>

    </div>
</div>
<script>
    const o_username = document.getElementById("o_username");
  // const o_phone = document.getElementById("o_phone");
   const btn_ordering = document.getElementById("o_btn_ordering");
   if(o_username ){
       o_username.addEventListener("change",activeBtnOrdering);
      // o_phone.addEventListener("change",activeBtnOrdering);&& o_phone
   }
   function activeBtnOrdering(){
       if(o_username.value.length>2){
           btn_ordering.disabled=false;
       }else{
           btn_ordering.disabled=true;
       }
   }

</script>

<script src="/static/js/image-view.js"></script>
<script src="/static/js/product.js"></script>
<script src="/static/js/phone.js"></script>


