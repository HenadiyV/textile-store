<#import "common.ftlh" as c>
<@c.page>
<#if userProductList??>
<div class="container">
<h4>${userProduct.name}</h4>
<table class="centr">
    <COLGROUP>
        <COL width="5">
        <COL width="400">
        <COL width="100">
        <COL width="5">
        <COL width="10">
        <COL width="5">

        <COL width="150">
        <COL width="150">

    </COLGROUP>
    <thead>
    <tr >
        <th>id</th>
        <th>Nick  Імя   Телефон  Адресса </th>
        <th > метрів: </th>
        <th class="caption-table-vertical">№ карти</th>
        <th>Категорія </th>
        <th class="caption-table">Колір</th>
        <th>    Інфо   </th>
        <th>   Дата    </th>
    </tr>
    </thead>
    <tbody>
    <#list userProductList as us>
    <tr>
        <td>${us.id}</td>
        <td>${us.username} <br/>  ${us.name} <br/> ${us.phone} <br/>${us.address}</td>
        <td>${us.selling_size}</td>
        <td>${us.cartId}</td>
        <td>${us.category}</td>
        <td> ${us.color}</td>
        <td>${us.info}</td>
        <td>${us.dat}</td>

    </tr>
    </#list>
    </tbody>
</table>
    <span>Закуплено: ${userProduct.sizeProduct} м.</span><br/>
    <span>Закупочна ціна: ${userProduct.purchasePrice} гр.</span><br/>
    <span>Ціна продажу: ${userProduct.sellingPrice} гр.</span><br/>
    <span>Продано: ${userProduct.selling_size} м.</span>
<#else>
<span>Нічого не знайдено</span>
</#if>
</div>

</@c.page>