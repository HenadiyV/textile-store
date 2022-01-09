<#include "security.ftlh">

<div class="container">
    <div class="row">
        <div class="col-10">
        </div>
        <div class="col-2">
        </div>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-4 mt-1" id="product-list">

    <#list productList as product>
        <div class="col">
            <div class="card">
                <div> <#if product.imageProducts??>
                     <#list product.imageProducts as image>
                         <#if image.showcase>
                             <div class="row">
                                 <div class="col-6">
                                     <img id="${image.id}" class="demo" src="/img/${image.imgProduct}"
                                                         width="200" height="200" style="overflow: hidden;"  alt="image" />
                                 </div>
                                 <div class="col-6">
                                     <div id="R${image.id}" class="img-zoom-result "></div>
                                 </div>
                             </div>
                         </#if>
                     </#list>
                </#if>
                </div>
                <div class="card-body">
                    <a class="btn btn-primary disabled" name="add-basket" onclick="searchProduct(#{product.id})" >Замовити</a><br/>

                    <i>#${product.id}</i>
                    <span>Назва: ${product.name}</span>
                    <span>Колір: ${product.color}</span><br/>
                    <span>Ціна: ${product.sellingPrice}</span><br/>

                  <#if isAdmin>
                      <span>Ціна закупки: ${product.purchasePrice}</span><br/>
                      <span>Кількість: ${product.sizeProduct}</span><br/>
                      <span>Продано: ${product.selling_size}</span><br/>
                      <span>Дата: ${product.dat}</span><br/>
                  </#if>

                </div>
                <div class="card-footer text-muted">

                    <#if product.description?length gt 0>
                        <b>${product.description}</b>
                    <#else>  Опис товару відсутній.
                    </#if>
                </div>
            </div>
        </div>
    <#else>
    Повідомлення відсутні
    </#list>
      </div>
    <div class="container">
        <div id="myModalBasket" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close" id="closeMyModalBasket">&times;</span>
                    <h2>Оформити замовленя</h2>
                </div>
                <div class="modal-body">
                    <div class="title-cart">
                        <#if basketId??><a href="/basket/delete/${basketId}" class="btn btn-danger">Очистити</a></#if>
                        <div id="place-basket"> </div>
                    </div>
                    <div class="body-cart">
                        <div class="table-content-center">
                            <form action="/basket/ordering" method="post">
                            <label for="username">Ваше імя</label> <br/>
                            <input type="text" name="username" id="username" value=""/><br/>

                            <label for="phone">Ваш телефон</label> <br/>
                            <input type="text" name="phone" id="phone" value=""/><br/>

                            <label for="info">Додаткова інформація до замовленя</label><br/>
                            <textarea name="info" id="info" cols="30" rows="3" style="width: 50%"></textarea><br/>
                            <input type="hidden" name="token" id="token_ordering"/>
                                <input type="hidden" class="input-with" name="_csrf"  value="${_csrf.token}"/>
                             <button class="btn btn-primary mt-1" type="submit">Замовити</button>
                            <#--id="basket_csrf"<a class="btn btn-primary mt-1" onclick="saveBasket()">Замовити</a>-->
                            </form>
                        </div>

                        <div id="table_place" class="row mt-2"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <#--<h3>Футер модального окна</h3>-->
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div id="myModalOrdering" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close" id="closeMyModalOrdering">&times;</span>
                    <h2>Додати до кошика</h2>
                </div>
                <div class="modal-body">
                    <div class="title-cart">
                        <div id="place-ordering"> </div>
                    </div>
                    <div class="body-cart">
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-6 ">
                                    <div class="preview">
                                        <div class="prview-image">
                                            <img src="" id="view-img" alt="" />
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                                <div class="col-sm-6 table-content-center">
                                    <form action="/basket/add-product" method="post">

                                <input type="hidden" class="input-with" name="_csrf" value="${_csrf.token}"/>
                                    <span id="place_price"></span><br/>
                                    <span id="place_summ"></span><br/>
                                    <label for="size">Введіть кількість</label> <br/>
                                    <input type="number" name="size" min=0 id="size" style="width: 100%"/><br/>
                                    <input type="hidden" name="productId" id="productId"/>
                                    <input type="hidden" name="price" id="price" readonly/>
                                    <input type="hidden" name="img" id="img_product" />
                                    <input type="hidden" name="token" id="token_cookie" />

                                    <label for="info">Додаткова інформація</label>
                                    <textarea name="info" id="info" cols="30" rows="3" style="width: 100%"></textarea><br/>
                                        <button class="btn btn-primary mt-1" type="submit" id="add_to_basket">Додати до кошика</button>
                                    </form>
                               </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                <#--<h3>Футер модального окна</h3>-->
                </div>
            </div>

        </div>

    </div>
</div>


<script src="/static/js/image-view.js"></script>
<script src="/static/js/product.js"></script>


