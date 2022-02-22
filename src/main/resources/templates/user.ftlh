<#import "parts/common.ftlh" as c>

<@c.page>
   <div class="container">
       <#if orderList?size gt 0>
   <#list orderList as order>
       <div class="card">
           <div class="card-body">
               <h4 class="card-title centr">${order.username}</h4>
               <div class="row">
                   <div class="col-sm-4 col-md-4 col-xl-4">

                       <span>Імя: <b>${order.name}</b> </span><br/>
                       <span>Нік: <b>${order.username}</b></span><br/>
                       <span>Телефон: <b>${order.phone}</b></span>
                   </div>
                   <div class="col-sm-4 col-md-4 col-xl-4">
                       <span>Область: <b>${order.region}</b></span><br/>
                       <span>Район: <b>${order.district}</b></span><br/>
                       <span>Місто: <b>${order.city}</b></span><br/>
                       <span>Адреса:<b>${order.address} </b></span> <br/>
                       <span>Індекс:<b>${order.postCode} </b> </span><br/>

                   </div>
                   <div class="col-sm-4 col-md-4 col-xl-4">
                       <span>Пошта:<b> ${order.postOffice}</b></span><br/>
                       <span>Статус: <b>${order.status}</b></span> <br/>
                       <span>  ТТН: <b>${order.delivery}</b></span><br/>
                       <span>Відправка: <b>${order.dat_dispatch?date?string('dd-MM-yyyy')} </b></span><br/>
                       <span>Створенно: ${order.dat_create?date?string('dd-MM-yyyy')}</span>

                   </div>

               </div>
<div class="centr">
               <table style="width: 100%">
                   <COLGROUP>
                       <COL width="30%">
                       <COL width="10%">
                       <COL width="10%">
                       <COL width="10%">
                       <COL width="20%">
                       <COL width="20%">
                   <thead>
                   <tr>
                       <th>назва</th>
                       <th>метр</th>
                       <th>сумма</th>
                       <th>бонус</th>
                       <th>інфо</th>
                       <th>зображеня</th>

                   </tr>
                   </thead>
                   <tbody>
        <#list order.carts as cart>
        <tr>

            <td>${cart.productName}</td>
            <td>${cart.siz}</td>
            <td>${cart.summ}</td>

            <td>${cart.discountPrice}</td>
            <td><#if cart.info?has_content>${cart.info}</#if></td>
            <td>

            <img src="/img/${cart.img}" alt="${cart.img}" style="width: 150px"/>
            </td>


        </tr>
        </#list>
                   </tbody>
               </table>
</div>
           </div>
       </div>
   </#list>


       <#else>
       <div class="centr"><h3>Замовленя відсутні</h3></div>
       </#if>
   </div>
</@c.page>
