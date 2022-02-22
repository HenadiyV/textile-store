<#import "common.ftlh" as c>
    <@c.page>
    <style>
        .p-right{

            margin:0 1.5%;
            width:50%
     }
        .p-input{

            width:40%
     }
        .p-textarea{

            width:40%
     }
        .p-left{
            float:left;
            margin:0 1.5%;
            width:50%

        }
</style>
    <div class="centr">
        <h4>Редагування замовлення</h4>
    </div>
<div class="container">
<div class="row">
    <div class="col-sm-4 col-md-4 col-lx-4">
        <a href="${url}">Повернутись</a>
    </div>
    <div class="col-sm-4 col-md-4 col-lx-4">
        <a class="btn btn-primary" data-bs-toggle="collapse" href="#changeUserBlock" role="button" aria-expanded="false" aria-controls="changeUserBlock">Змінити користувача</a>
    </div>
    <div class="col-sm-4 col-md-4 col-lx-4">
        <a class="btn btn-primary" data-bs-toggle="collapse" href="#addProductBlock" role="button" aria-expanded="false" aria-controls="changeUserBlock">Додати товар</a>
    </div>
</div>

     <div class="collapse" id="changeUserBlock">
        <div class="card card-body">
<div><label for="search_user">Пошук користувача</label></div>
            <div>

                <input type="text" id="search_user">
                <label for="userSelect">
            <select id="userSelect" style="width:200px">
            <option>Список користувачів</option>
            </select></label>
            </div>
        </div>
    </div>

    <div class="collapse" id="addProductBlock">
        <div class="card card-body">

            <div>   <label for="search_product">Пошук товару</label>
                <input type="text" id="search_product">

                <label for="productSelect"></label>
                <select id="productSelect">
                    <option>Товарні позиції</option>
                </select>
            </div>
        </div>
    </div>

    <form   method="post" >
    <div class="row ">
        <div class="col-sm-4 mt-2">
            <label for="phon">Телефон</label><br/>
            <select id="phon" class="ordUpdForm">
                            <option value="${order.phone_id}"> ${order.phone}</option>
            </select>
        </div>

        <div class="col-sm-4 mt-2">
            <span >Нік: <b id="nickV">${order.name}</b></span> <br/>
            <span >Імя: <b id="nameV">${order.username}</b></span>
        </div>

        <div class="col-sm-4 mt-2">
            <label for="addr">Адресса</label><br/>
            <select id="addr">
                    <option value="${order.address_id}">
                        ${order.city} |
                        ${order.address} |
                        ${order.postCode}</option>
            </select>
        </div>
    </div>
    <div class="row mt-3">
        <div class="col-sm-4">
            <div class="mt-2">
                <label for="dat_dispatch">Дата відправки</label><br/>
                <input type="text" name="dat_dispatch" id="dat_dispatch"  class="input-with"/>
                <input type="hidden"  id="dat_dispatch1" class="input-with" value="${order.dat_dispatch?date?string('yyyy/MM/dd')}" />
            </div>

            <div class="mt-3">
                <label for="postOffice">Почтове відділення</label><br/>
                <select id="postOffice" class="ordUpdForm">

                        <option value="${order.postOffice_id}"> ${order.postOffice}</option>

                </select>
            </div>
        </div>

        <div class="col-sm-4">

            <div class="mt-2">
                <label for="delivery">ТТН</label><br/>
                <input type="text" name="delivery" id="delivery" value="${order.delivery}" class="input-with" />
                <input type="hidden" id="ord_id" value="${order.id}" class="input-with" />
            </div>

            <div class="mt-3">
                <label for="status">Статус відправлення</label><br/>
                <select id="status" name="status" class="ordUpdForm">
                    <#list status1 as stat>
                        <#if stat.status = order.status>
                        <option value="${stat.status}" selected>${stat.status}</option>
                        <#else>
                        <option value="${stat.status}" >${stat.status}</option>
                        </#if>
                    </#list>
                </select><br/>
            </div>
            <div class="mt-3 centr" >

                    <input type="hidden"   name="id"  value="${order.id}">
                    <input type="hidden" name="user_id" id="user_id" value="${order.user_id}"readonly />
                    <input type="hidden"   name="phone_id"  id="phone_id" value="${order.phone_id}"readonly />
                    <input type="hidden"   name="address_id" id="address_id"  value="${order.address_id}"readonly />
                    <input type="hidden"   name="post_office_id" id="postOffice_id"  value="${order.postOffice_id}"readonly />
                    <input type="hidden"   name="summ"   value="#{order.summ}" readonly />
                    <input type="text" name="_csrf" value="${_csrf.token}" />
                    <button type="submit" id="btn_form" class="btn btn-primary">Зберегти</button>

            </div>
        </div>
        <div class="col-sm-4">
            <div class="mt-1">
                <label for="infoOr">Додаткова інформація</label><br/>
                <textarea id="infoOr" name="info_order" class="input-with" rows="4" cols="45"><#if order.info_order?has_content>${order.info_order}</#if></textarea>
            </div>
        </div>
    </div>

    </form>
    <div class="centr">
    <span id="order-summ" data-summ="#{order.summ}">Сумма: ${order.summ}</span><br/>
        <div id="new_product"></div>
    </div>


<#if order.carts??>
    <div class="centr">

        <#list order.carts as cart>
<div class="row "style="border:1px solid">
     <div class="col-sm-4 col-md-4 col-lx-4">
         <div ><i class="p-left">IdCart:   </i>
             <b class="p-right">${cart.cartId}</b>
         </div>
        <div>  <i class="p-left">IdProduct:</i>
            <b class="p-right">${cart.productId}</b>
        </div>
          <div><i class="p-left">Назва:</i>
              <b class="p-right">${cart.productName}</b>
          </div>
          <div><i class="p-left">Ціна продажу:</i>
              <b class="p-right" id="price${cart.cartId}" data-price="${cart.sellingPrice?replace(',','.')}">${cart.sellingPrice}</b>
          </div>
         <div><i class="p-left">Кількість:</i>
             <b class="p-right">${cart.sizeProduct}</b>
         </div>
         <div><i class="p-left">Залишок:</i>
             <b class="p-right" data-balance="${cart.balance?replace(',','.')}" id="balance${cart.cartId}">${cart.balance}</b>
         </div>
     </div>
     <div class="col-sm-5 col-md-5 col-lx-5">
            <div><i class="p-left">Метраж:</i>
                <input type="number" class="p-input" name="product-size" id="siz-${cart.cartId}"
                      data-size="${cart.siz?replace(',','.')}" value="${cart.siz?replace(',','.')}" placeholder="${cart.siz?string}" step="0.1" min="0" max="${cart.balance}"/>
            </div>
         <div><i class="p-left">Сумма:</i>  <b class="p-right summ" data-summ="${cart.summ?replace(',','.')}" id="summ-${cart.cartId}">${cart.summ}</b></div>
         <div><i class="p-left">Знижка: </i>
             <input type="number" class="p-input" name="product-discountPrice"
                    id="discountPrice-${cart.cartId}" min="0" data-discountPrice="${cart.discountPrice}" value="${cart.discountPrice}" />
         </div>
         <div><i class="p-left">Додаткова інформація:</i>
             <textarea class="p-textarea" name="infoCart" data-info="${cart.infoCart?length}"
                       id="infoCart-${cart.cartId}">${cart.infoCart}</textarea></div>
     </div>
     <div class="col-sm-3 col-md-3 col-lx-3">
                <img src="/img/${cart.img}" style="width:100px"/>

              </div>
    <div>
        <button class="btn btn-success" id="btn-#{cart.cartId}"  onclick="updateCart(#{cart.cartId})" disabled="disabled">Зберегти</button>

        <a class="btn btn-danger" href="/order/delete-cart/${cart.cartId}" > &#8203; Видалити &#8203; </a>
    </div>

    </div>
        </#list>
</div>
</#if>


</div>
<div id="changeCart" class="modal">

    <div class="modal-content">

    <div class="modal-header">
    <span class="close" id="closeChangeCart" >&times;</span>
    <span id="order_summ"></span>
    <h3 >Змінити користувача</h3><br/>
    </div>

    <div class="modal-body">

    <div class="row">

    <div class="col-6">

    <div class="col-6">
    <input type="hidden" name="_csrf" id="cart_csrf" value="${_csrf.token}"/>
    <button   class="btn btn-primary" id="save_cart" onclick="saveCart()" disabled="disabled">Зберегти</button>
    </div>

    </div>

    </div>
    </div>
    <div class="modal-footer">
    <#--<h3 style="color: black">Футер модального окна</h3>-->
    </div>
    </div>
    </div><script src="/static/js/edit-order.js"></script>

    </@c.page>
