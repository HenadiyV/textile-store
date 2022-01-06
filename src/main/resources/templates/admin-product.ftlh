<#import "parts/common.ftlh" as c>
<@c.page>
<div class="container">

<h4 class="centr">Список тканин</h4>

    <a class="btn btn-primary"  href="/add-product" > Додати товар  </a>

<table class='table-modal'>
    <thead>
    <tr>
        <th>Імя</th>
        <th>Колір</th>
        <th>Ціна закупки</th>
        <th>ціна продажу</th>
        <th>Кількість</th>
        <th>Продано</th>
        <th>Дата</th>
        <th>Редагувати</th>
        <th>Зображення</th>
    </tr>
    </thead><tbody>
    <#list productList as product>

<tr>
    <td><span>${product.name}</span></td>
    <td><span>${product.color}</span></td>
    <td><span>${product.purchasePrice}</span></td>
    <td><span>${product.sellingPrice}</span></td>
    <td><span>${product.sizeProduct}</span></td>
    <td><span>${product.selling_size}</span></td>
    <td><span>${product.dat?date?string('dd-MM-yyyy')}</span></td>
    <td>
<div class="mt-2">
        <a  class="btn btn-success" href="/edit-product/${product.id}">Редагувати</a>
</div>
        <div class="mt-2">
        <a href="/order/user-product/${product.id}" class="btn btn-primary">&#8192;Покупці&#8192;&#8192;</a>
        </div>
    </td>
    <#if product.imageProducts?? >
        <td>
        <#list product.imageProducts as img>
            <#if img.showcase><img src="/img/${img.imgProduct}" style="width: 100px;height: 100px"/></#if>
        </#list>
        </td>

    <#else>

    </#if>
</tr>
    </#list>
</tbody>
</table>

</div>

</@c.page>