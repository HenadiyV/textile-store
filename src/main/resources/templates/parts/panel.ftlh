<#include "security.ftlh">

<#if isAdmin>
    <div class="list-group">

        <a href="/admin" class="list-group-item list-group-item-action "> Admin</a>

        <a href="/admin/users" class="list-group-item list-group-item-action">Користувачі</a>

        <a href="/category" class="list-group-item list-group-item-action">Категоріі</a>

        <a href="/product" class="list-group-item list-group-item-action">Товар</a>

        <a href="/order/view-order" class="list-group-item list-group-item-action">Замовлення</a>

    </div>
<#elseif user??>
    <div class="list-group">

        <a href="#" class="list-group-item list-group-item-action">Персональні дані</a>

        <a href="#" class="list-group-item list-group-item-action">Замовлення</a>

        <a href="#" class="list-group-item list-group-item-action">Ваш збір</a>

        <a href="#" class="list-group-item list-group-item-action">Ваші бонуси</a>

    </div>
<#else>
    <div class="list-group">

        <a href="#" class="list-group-item list-group-item-action">Тканини в наявності</a>

        <a href="/zbir" class="list-group-item ">Збір</a>

        <a href="/lito" class="list-group-item list-group-item-action">Літні тканини</a>

        <a href="/zima" class="list-group-item list-group-item-action">Зимові тканини</a>

        <a href="#" class="list-group-item list-group-item-action ">Написати адміну</a>

    </div>
</#if>