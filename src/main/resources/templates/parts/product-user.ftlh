<#import "common.ftlh" as c>
<@c.page>
<#if userProductList??>
<div class="container">
<table class="centr">
    <COLGROUP>
        <COL width="5">
        <COL width="350">
        <COL width="5">
        <COL width="10">
        <COL width="5">
        <COL width="150">
        <COL width="150">
        <COL width="150">

    </COLGROUP>
    <thead>
    <tr >
        <th>id</th>
        <th>Nick <br/> Імя  <br/> Телефон <br/> Адресса </th>
        <th class="caption-table-vertical">№ карти</th>
        <th>Категорія </th>
        <th class="caption-table">Назва Колір</th>
        <th>Куплено м: </th>

        <th>Опис</th>
        <th>   Дата    </th>
        <th>Інфо</th>
    </tr>
    </thead>
    <tbody>
    <#list userProductList as us>
    <tr>
        <td>${us.id}</td>
        <td>${us.username} <br/>  ${us.name} <br/> ${us.phone} <br/>${us.address}</td>
        <td>${us.cartId}</td>
        <td>${us.category}</td>
        <td>${us.nameProduct} <br/> ${us.color}</td>
        <td>${us.selling_size}</td>
        <td>${us.description}</td>

        <td>${us.dat}</td>
        <td>${us.info}</td>
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