<#import "common.ftlh" as c>

<@c.page>
<link rel="stylesheet" href="/static/js/node_modules/air-datepicker/air-datepicker.css" />
 <script src="/static/js/node_modules/air-datepicker/air-datepicker.js"></script>
<div class="container">

                <div class="row mt-5">

                    <div class="col-sm-3 col-md-3 col-xl-3">
                            <label for="search_user">Пошук користувачів<br/>
                            <input type="text" id="search_user"  placeholder="Введіть імя або нік" />
                                </label>
                    </div>
                    <div class="col-sm-3 col-md-3 col-xl-3">

                        <label for="userL">Користувачі<br/>
                        <select id="userL" >
                            <option>Список користувачів</option>
                              <#list users as user>
                                 <option value="${user.id}"><#if user.name?has_content>${user.name}</#if> | ${user.username}</option>
                              </#list>
                        </select>
                        </label>
                    </div>
                    <div class="col-sm-3 col-md-3 col-xl-3">
                        <label for="search_product">Пошук товару<br/>
                        <input type="text" id="search_product"  placeholder="Введіть назву" />
                        </label>
                    </div>
                    <div class="col-sm-3 col-md-3 col-xl-3">
                        <label for="productL">Список товарів<br/>
                        <select id="productL">
                            <option>Товарні позиціі</option>
                          <#list products as product>
                             <option value="${product.id}"><#if product.name?has_content>${product.name}</#if> | ${product.color}</option>
                          </#list>
                        </select>
                        </label>
                    </div>
                    <div class="col-sm-12 col-md-12 col-xl-12 mt-2">
                        <table class="t-table">
                            <thead>
                            <tr>
                                <th>Nick</th><th>Імя</th><th>Телефон</th><th>Адресс</th><th>Почта</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><span id="us_name_order"></span></td>
                                <td><span id="us_order"></span></td>
                                <td><label for="order_phone">Телефон:</label><select id="order_phone"></select></td>
                                <td><label for="order_address">Адреса:</label><select id="order_address"></select></td>
                                <td><label for="order_postOffice">Пошта:</label><select id="order_postOffice"></select></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

    <form   action="/order/add-order" method="post">

    <div class="row">



        <div class="col-sm-4 col-md-4 col-xl-4  mt-2">
            <label for="dat_dispatch">Дата відправлення</label><br/>
            <input type="text" id="dat_dispatch" name="dat_dispatch" autocomplete="off"/><br/>

            <label for="deliv">TTH</label><br/>
            <input type="text" name="delivery" id="deliv"/><br/>
        </div>
        <div class="col-sm-4 col-md-4 col-xl-4  mt-2">
            <label>Статус замовлення<br/>

                <select name="status" >
                           <#list statusList as status>
                               <#if status.status?contains("не оплачений")>
                           <option value="${status.status}" selected>${status.status}</option>
                               <#else>
                               <option value="${status.status}">${status.status}</option>
                               </#if>
                           </#list>
                </select>
            </label><br/>
            <div class="mt-3">
            <span id="order_summ" ></span>
            </div>
    </div>
        <div class="col-sm-4 col-md-4 col-xl-4  mt-2">
                    <div >
                        <label>Додаткова інформація<br/>
                            <textarea name="info"  cols="30" rows="3" >Додаткова інформація</textarea>
                        </label>
                    </div>
                    <input type="hidden" id="user_id"  name="userId">
                    <input type="hidden" id="phone_id"  name="phoneId">
                    <input type="hidden" id="address_id"  name="addressId">
                    <input type="hidden" id="postOffice_id"  name="postOfficeId">

                    <textarea id="cartValue" name="carts" hidden="hidden"></textarea>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </div>
    </div>
        <div class="centr">
        <button class="btn btn-primary" id="save_cart" type="submit" disabled="disabled">Зберегти</button>
        </div>
</form>

    <div class="mt-2 centr">
        <div  id="pr_order"></div>
    </div>
</div>

<script src="/static/js/order.js"></script>
</@c.page>