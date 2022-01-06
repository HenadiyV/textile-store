<#import "parts/common.ftlh" as c>
<@c.page>

<div class="container" >
    <h4 class="centr">Замовлення</h4>

     <a class="btn btn-primary"  href="/order/add-order" > Додати замовлення </a>

        <table>
            <COLGROUP>
                <COL width="10">
                <COL width="30">
                <COL width="80">
                <COL width="150">
                <COL width="170">
                <COL width="10">
                <COL width="10">
                <COL width="4*">
            </COLGROUP>
            <thead>
                <tr>
                    <th>Сумма</th>
                    <th>Статус/ТТН</th>
                    <th>Відправка/Створення</th>
                    <th>Нік / Імя</th>
                    <th>Телефон/Адресса</th>
                    <th>Почта</th><th></th>
                    <th>Товар</th>
                </tr>
            </thead>
            <tbody>
                <#list orderList as order>
                    <tr>
                        <td>${order.summ}</td>
                        <#if order.delivery?length gt 3>
                        <td>${order.status}<br>
                            ${order.delivery}</td>
                        <#else><td>${order.status}</td>
                        </#if>
                        <td>
                            ${order.dat_dispatch?date?string('dd-MM-yyyy')}<br>
                            ${order.dat_create?date?string('dd-MM-yyyy')}
                        </td>

                        <td>
                            ${order.name}<br>
                            ${order.username}
                        </td>
                        <td>
                            <b>т: </b>${order.phone}<br>
                            <b>м: </b>${order.city}<br>
                            <b>адр: </b>${order.address}<br>
                            <b>інд: </b> ${order.postCode}
                        </td>
                        <td>${order.postOffice}</td>
                        <td>
                       <div>
                       <button class="btn btn-primary" onclick="addCartToOrderShow(#{order.id},#{order.summ})">Дод..</button>
                       </div><br/>

                        <div><a class="btn btn-success" href="/order/update-order/${order.id}">Ред..</a></div><br/>

                        <div><a class="btn btn-danger" href="/order/delete-order/${order.id}">Вид..</a></div>
                    </td>
                        <td>
                            <table>
                                <thead>
                                    <tr>
                                        <th>назва</th>
                                        <th>метр</th>
                                        <th>сумма</th>
                                        <th>бонус</th>
                                        <th>інфо</th>
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
                                               <button class="btn btn-success" onclick="modalShow(${cart.cartId})">Ред..</button>
                                           </td>
                                            <td>
                                               <a  href="/order/delete-cart/${cart.cartId}"
                                                  <#if order.carts?size lt 2>class="btn btn-danger disabled-el"
                                                  <#else>class="btn btn-danger"
                                                  </#if>>Вид..</a>
                                           </td>
                                        </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </#list>
            </tbody>
        </table>

        <div id="myModal" class="modal">

            <div class="modal-content">

                <div class="modal-header">
                    <span class="close" id="closeMyModal">&times;</span>
                    <h2>Шапка модального окна myModal</h2>
                </div>

                <div class="modal-body">

                    <div class="title-cart">
                        <span class="sale_price"></span><br/>
                        <span class="balance"></span>
                    </div>

                    <form id="cartUp" action="/order/update-cart" method="post" >

                        <div class="row">

                            <div class="col-6 mt-2">
                                <div><span>product_name</span><br/>
                                    <input type="text"   name="product_name"   readonly/>
                                </div>
                                <div class="mt-1"><span>info_cart</span><br/>
                                    <textarea name="info_cart" rows="4" cols="30"></textarea>
                                </div>
                                <input type="hidden"   name="cart_id"  />
                                <input type="hidden"   name="order_id" />
                                <input type="hidden"   name="product_id"   />
                                <input type="hidden"   name="salePrice"  />
                                <input type="hidden"   name="balance_product" min="0"  />
                            </div>

                            <div class="col-6 mt-2">
                                    <div> <span>siz</span><br/>
                                        <input type="number" class="upSumm" min="0" name="siz"  />
                                    </div>
                                    <div class="mt-1"><span>discount_price</span><br/>
                                        <input type="number" class="upSumm"  name="discount_price"   step="1"/>
                                    </div>
                                    <div class="mt-3">
                                        <span>summ</span><br/><input type="number"   name="summ"   readonly/>
                                    </div>
                            </div>

                            <div class="col-12 mt-2">
                                <div class="row">

                                   <div class="col-8"> </div>

                                   <div class="col-4">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                        <button type="submit" id="btn_form" class="btn btn-primary" >Зберегти</button>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </form>

                </div>

                <div class="modal-footer">
                    <#--<h3>Футер модального окна</h3>-->
                </div>

            </div>

        </div>
    <#-- == ADD CART TO ORDER == -->
        <div id="addCartToOrder" class="modal">

            <div class="modal-content">

                <div class="modal-header">
                    <span class="close" id="closeAddCartToOrder" >&times;</span><span id="order_summ"></span>
                    <h3 >Додати до замовлення</h3><br/>
                </div>

                <div class="modal-body">

                        <div class="row">

                            <div class="col-6">
                                <div><input type="text" id="search_product"> </div>
                            </div>

                            <div class="col-6">
                                <input type="hidden" name="_csrf" id="cart_csrf" value="${_csrf.token}"/>
                                <button   class="btn btn-primary" id="save_cart" onclick="saveCart()" disabled="disabled">Зберегти</button></div>
                        </div>

                        <div class="row">

                            <div class="col-4 mt-1">

                               <div id="productList"></div>
                                <input type="hidden" name="order_id" />
                            </div>

                            <div class="col-8 mt-1">
                               <div id="productAddCartList"></div>
                            </div>

                        </div>

                </div>
            </div>
                <div class="modal-footer">
                    <#--<h3 style="color: black">Футер модального окна</h3>-->
                </div>
            </div>

</div>

<script src="/static/js/order.js"></script>

</@c.page>