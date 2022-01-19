<#import "parts/common.ftlh" as c>
<@c.page>

<div class="container">
    <h4 class="centr">Замовлення</h4>

     <a class="btn btn-primary"  href="/order/add-order" > Додати замовлення </a>
<#import "parts/orderList.ftlh" as p>
<@p.orders orderList ></@p.orders>

        <div id="myModal" class="modal">

            <div class="modal-content">

                <div class="modal-header">
                    <span class="close" id="closeMyModal">&times;</span>
                    <h2>Редагувати товар в замовлені</h2>
                </div>

                <div class="modal-body">

                    <div class="title-cart">
                        <span class="sale_price"></span><br/>
                        <span class="balance"></span>
                    </div>

                    <form id="cartUp" action="/order/update-cart" method="post" >

                        <div class="row">

                            <div class="col-6 mt-2">
                                <#--<div><span>product_name</span></div>-->
                                    <label>Назва<br/>
                                        <input type="text"   name="product_name"   readonly/>
                                    </label><br/>
                                <#--<div class="mt-1"><span>info_cart</span><br/></div>-->
                                    <label>Додаткова інформація<br/>
                                        <textarea name="info_cart" rows="4" cols="30"></textarea>
                                    </label>
                                <input type="hidden"   name="cart_id"  />
                                <input type="hidden"   name="order_id" />
                                <input type="hidden"   name="product_id"   />
                                <input type="hidden"   name="salePrice"  />
                                <input type="hidden"   name="balance_product" min="0"  />
                            </div>
                            <div class="col-6 mt-2">
                                    <#--<div> <span>siz</span></div>-->
                                        <label>Метраж:<br/>
                                            <input type="number" class="upSumm" min="0" name="siz"  />
                                        </label><br/>
                                    <#--<div class="mt-1"><span>discount_price</span>-->
                                        <label>discount_price<br/>
                                            <input type="number" class="upSumm"  name="discount_price"   step="1"/>
                                        </label><br/>
                                    <#--<div class="mt-3"> <span>summ</span><br/></div>-->
                                        <label>summ<br/>
                                        <input type="number"   name="summ"   readonly/>
                                    </label><br/>
                                </div>

                            </div>
                            <div class="col-12 mt-2">

                                <div class="row">

                                   <div class="col-8">
                                   </div>
                                   <div class="col-4">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                        <button type="submit" id="btn_form" class="btn btn-primary" >Зберегти</button>
                                    </div>

                                </div>

                             </div>
                    </form>

                        </div>
                </div>

                <div class="modal-footer">
                    <#--<h3>Футер модального окна</h3>-->
                </div>

            </div>


    <#-- == ADD CART TO ORDER == -->
        <div id="addCartToOrder" class="modal">

            <div class="modal-content">

                <div class="modal-header">
                    <span class="close" id="closeAddCartToOrder" >&times;</span>
                    <span id="order_summ"></span>
                    <h3 >Додати до замовлення</h3><br/>
                </div>

                <div class="modal-body">

                        <div class="row">

                            <div class="col-6">
                                <div><label for="search_product"> Пошук товару </label>
                                    <input type="text" id="search_product"> </div>
                            </div>
                            <div class="col-6">
                                <input type="hidden" name="_csrf" id="cart_csrf" value="${_csrf.token}"/>
                                <button   class="btn btn-primary" id="save_cart" onclick="saveCart()" disabled="disabled">Зберегти</button>
                            </div>

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