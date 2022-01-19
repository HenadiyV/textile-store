<#include "security.ftlh">
<#macro orders orderList>
<#if orderList?has_content>
    <#list orderList as order>
    <div class="card">
        <div class="card-body">
            <h4 class="card-title centr">${order.username}</h4>
    <div class="row">
        <div class="col-sm-4 col-md-4 col-xl-4">
            <span>Сумма: <b>${order.summ}</b> </span><br/>
            <span>Імя: <b>${order.name}</b> </span><br/>
            <span>Нік: <b>${order.username}</b></span><br/>
            <span>Телефон: <b>${order.phone}</b></span>
        </div>
        <div class="col-sm-4 col-md-4 col-xl-4">
            <span>Місто: <b>${order.city}</b></span><br/>
            <span>Адреса:<b>${order.address} </b></span> <br/>
            <span>Індекс:<b>${order.postCode} </b> </span><br/>
            <span>Пошта:<b> ${order.postOffice}</b></span>
        </div>
        <div class="col-sm-4 col-md-4 col-xl-4">

            <span>Статус: <b>${order.status}</b></span> <br/>
            <span>  ТТН: <b>${order.delivery}</b></span><br/>
            <span>Відправка: <b>${order.dat_dispatch?date?string('dd-MM-yyyy')} </b></span><br/>
            <span>Створенно: ${order.dat_create?date?string('dd-MM-yyyy')}</span>

        </div>
            <#if isAdmin>
        <div class="col-sm-12 col-md-8 col-xl-6">
            <button class="btn btn-primary" onclick="addCartToOrderShow(#{order.id},#{order.summ})">Дододати </button>
            <a class="btn btn-success" href="/order/update-order/${order.id}">Редагувати</a>
            <a class="btn btn-danger" href="/order/delete-order/${order.id}">Видалити</a>
        </div>
            </#if>
    </div>

<table class="t-table">
    <thead>
    <tr>
        <th>назва</th>
        <th>метр</th>
        <th>сумма</th>
        <th>бонус</th>
        <th>інфо</th>
        <th>зображеня</th>
        <#if isAdmin>
        <th>Редагувати Видалити</th>
        </#if>
    </tr>
    </thead>
    <tbody>
        <#list order.carts as cart>
          <tr>

           <td>${cart.productName}</td>
           <td>${cart.siz}</td>
           <td>${cart.summ}</td>
           <td>${cart.discountPrice}</td>
           <td><#if cart.infoCart?has_content>${cart.infoCart}</#if></td>
              <td>
              <img src="/img/${cart.img}" alt="${cart.img}" />
          </td>

           <#if isAdmin>
             <td style="width: 5%">
               <a class="btn btn-success" onclick="modalShow(${cart.cartId})">Редагувати</a>

               <a  href="/order/delete-cart/${cart.cartId}" class="btn btn-danger"> &#8203; Видалити &#8203; </a>
             </td>
           </#if>
          </tr>
        </#list>
    </tbody>
</table>
        </div>
    </div>
</#list>

<#else><h3 class="centr">Замовлення выдсутні</h3>
</#if>
</#macro>