<#import "common.ftlh" as c>

<@c.page>
<link rel="stylesheet" href="/static/js/node_modules/air-datepicker/air-datepicker.css" />
 <script src="/static/js/node_modules/air-datepicker/air-datepicker.js"></script>
<div class="container">

                <div class="row mt-5">

                    <div class="col-3">

                        <div class="form-group ">

                            <label for="userL">Пошук користувачів</label>
                            <input type="text" id="search_user" placeholder="Search user" />

                            <select id="userL">
                                <option>Список користувачів</option>
                              <#list users as user>
                                 <option value="${user.id}"><#if user.name?has_content>${user.name}</#if> | ${user.username}</option>
                              </#list>
                            </select>
                        </div>
                    </div>

                    <div class="col-9 mt-2">
                        <table>
                            <thead>
                            <tr>
                                <th>Nick | Імя</th><th>Телефон</th><th>Адресс</th><th>Почта</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><span id="us_order"></span></td>
                                <td><select id="order_phone"></select></td>
                                <td> <select id="order_address"></select></td>
                                <td><select id="order_postOffice"></select></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

    <form   action="/order/add-order" method="post">

    <div class="row">

        <div class="col-3 mt-2">

            <div class="form-group ">

                <label for="productL">Пошук товару</label>
                <input type="text" id="search_product" placeholder="Search product" />

                <select id="productL"><option>Список товарів</option>
                          <#list products as product>
                             <option value="${product.id}"><#if product.name?has_content>${product.name}</#if> | ${product.color}</option>
                          </#list>
                </select>
            </div>
        </div>
        <div class="col-3  mt-2">

                <div class="form-group">

                    <label>Дата відправлення</label><br/>
                    <input type="text" id="dat_dispatch" name="dat_dispatch" autocomplete="off"/><br/>

                    <div class="mt-2">
                        <label>TTH</label><br/>
                        <input type="text" name="delivery"/>
                    </div>
            </div>
        </div>
        <div class="col-3  mt-2">

                <div class="form-group">

                    <label>Статус замовлення</label><br/>
                    <select name="status">
                       <#list statusList as status>
                           <#if status.status?contains("не оплачений")>
                       <option value="${status.status}" selected>${status.status}</option>
                           <#else>
                           <option value="${status.status}">${status.status}</option>
                           </#if>
                       </#list>
                    </select>

                    <div class="mt-3">
                        <textarea name="info" id="" cols="30" rows="2" value=" ">Додаткова інформація</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <input type="hidden" id="user_id"  name="userId">
                    <input type="hidden" id="phone_id"  name="phoneId">
                    <input type="hidden" id="address_id"  name="addressId">
                    <input type="hidden" id="postOffice_id"  name="postOfficeId">

                    <textarea id="cartValue" name="carts" hidden="hidden"></textarea>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <button class="btn btn-primary mt-4" id="save_cart" type="submit" disabled="disabled">Зберегти</button>
                </div>
        </div>
    </div>
</form>
            <div class="row">

                <div class="col-3"> </div>

                <div class="col-9 mt-2">

                    <span id="order_summ"></span>

                    <div class="form-group" id="pr_order"></div>
                </div>
            </div>
</div>

<script src="/static/js/order.js"></script>
<script src="/static/js/node_modules/air-datepicker/my_script_to_air.js"></script>

</@c.page>