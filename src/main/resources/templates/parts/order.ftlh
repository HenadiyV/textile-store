<#import "common.ftlh" as c>

<@c.page>
<div class="container">
                <div class="row mt-5">
                    <div class="col-sm-6 col-md-6 ">
                            <label for="search_user">Пошук користувачів</label><br/>
                            <input type="text" id="search_user" name="name" placeholder="Введіть імя або нік" />

                        <label for="userL"></label>
                        <select id="userL" class="input-with select_listUser" name="user_list[]" multiple="" size="6">

                              <#list users as user>
                                 <option value="${user.id}"><#if user.name?has_content>${user.name}</#if> | ${user.username}</option>
                              </#list>
                        </select>

                    </div>
                    <div class="col-sm-6 col-md-6 ">
                        <label for="search_product">Пошук товару</label><br/>
                        <input type="text" id="search_product"  placeholder="Введіть назву" />


                        <label for="productL"></label>
                        <select id="productL" class="input-with" name="product_list[]" multiple="" size="6">
                            <option>Товарні позиціі</option>
                          <#list products as product>
                             <option value="${product.id}"><#if product.name?has_content>${product.name}</#if> | ${product.color}</option>
                          </#list>
                        </select>

                    </div>
                    <div class="col-sm-12 col-md-12 col-xl-12 mt-2">

                        <table class="t-table" id="order_user_table" hidden="hidden">
                            <thead>
                            <tr>
                                <th>Nick</th><th>Імя</th><th>Телефон</th><th>Адресс</th><th>Почта</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><span id="us_name_order"></span></td>
                                <td><span id="us_order"></span></td>
                                <td><select id="order_phone"></select></td>
                                <td><select id="order_address"></select></td>
                                <td><select id="order_postOffice"></select></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

    <form   action="/order/add-order" method="post">

    <div class="row">
        <div class="col-sm-4 col-md-4 col-xl-4  mt-2">
            <label for="dat_dispatch">Дата відправлення</label><br/>
            <input type="text" id="dat_dispatch" name="dat_dispatch" /><br/>
            <input type="hidden" id="dat_dispatch1"  /><br/>

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