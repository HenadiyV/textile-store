<#import "common.ftlh" as c>

<@c.page>
<div class="container">

 <form  method="post" enctype="multipart/form-data">

    <div class="row mt-5">

       <div class="col-4 form-group">

            <input type="hidden" name="id_product" id="id_product" class="input-with" value="
                <#if product??><#if product.id?has_content>${product.id}</#if></#if>" />

            <label for="name">Назва: </label> <br/>
             <input type="text" name="name" id="name" class="form-control ${(nameError??)?string('is-invalid','')} "
                           value="<#if product??><#if product.name?has_content> ${product.name}</#if></#if>" />

                        <#if nameError??>
                                <div class="invalid-feedback">
                                    ${nameError}
                                </div>
                        </#if>
             <div class="mt-2">
                    <label for="color">Колір: </label><br/>
                    <input type="text" name="color" id="color" class="input-with"
                           value="<#if product??><#if product.color?has_content>${product.color}</#if></#if>" />
             </div>
             <div class="mt-2">
                    <label for="category">Категорія</label> <br/>
                        <select id="category" name="category">
                        <#list categoryList as category>
                            <#if product??>
                            <#if product.category_id?has_content>
                                <#if category.id = product.category.id>
                                    <option value="${category.id}" selected>${category.name}</option>
                                <#else>
                                <option value="${category.id}">${category.name}</option>
                                </#if>
                            <#else>
                                <#if category.id = product.category.id>
                                    <option value="${category.id}" selected>${category.name}</option>
                                <#else>
                                <option value="${category.id}">${category.name}</option>
                                </#if>
                            </#if>
                        <#else>
                            <option value="${category.id}">${category.name}</option>
                            </#if>
                        </#list>
                        </select>
             </div>
             <div class="mt-3">
                    <span>Дата</span><br/>
                    <input type="text" id="dat_dispatch" name="dat_dispatch" class="input-with" value="" />
                    <input type="hidden" id="dat_dispatch1" class="input-with"
                           value ="<#if product??><#if product.dat?has_content>${product.dat?date?string('yyyy/MM/dd')}<#else>""</#if></#if>"  />
             </div>
             <div class="mt-2">
                 <#if product??>
                      <#if product.active>
                         <label>Активний</label> <input type="checkbox" name="active" checked value="1"/>
                      <#else>
                         <label>Не активний</label><input type="checkbox" name="active" value="0"/>
                      </#if>
                  <#else>
                      <label>Активний</label> <input type="checkbox" name="active" checked value="1"/>
                </#if>
                </div>
       </div>
       <div class="col-4 form-group">
                <label for="purchasePrice">Ціна закупки: </label><br/>
                <input type="number" name="purchasePrice" min="0" id="purchasePrice" class="form-control
                            ${(purchasePriceError??)?string('is-invalid','')}"
                                value="<#if product??>${product.purchasePrice}<#else>0</#if>" />

                    <#if purchasePriceError??>
                              <div class="invalid-feedback">
                                   ${purchasePriceError}
                              </div>
                    </#if>
                <div class="mt-2">
                    <label for="sellingPrice">Ціна продажу: </label><br/>
                    <input type="number" name="sellingPrice" min="0" id="sellingPrice"  class="form-control
                                ${(sellingPriceError??)?string('is-invalid','')}"
                                    value="<#if product??>${product.sellingPrice}<#else>0</#if>" />

                    <#if sellingPriceError??>
                              <div class="invalid-feedback">
                                  ${sellingPriceError}
                              </div>
                    </#if>
                </div>
                <div class="mt-2">
                    <label for="sizeProduct">Кількість: </label> <br/>
                    <input type="number" name="sizeProduct" min="0" id="sizeProduct" class="form-control
                                ${(sizeProductError??)?string('is-invalid','')}"
                                    value="<#if product??>${product.sizeProduct}<#else>0</#if>" />

                     <#if sizeProductError??>
                            <div class="invalid-feedback">
                                ${sizeProductError}
                            </div>
                     </#if>
                </div>
                <div class="mt-2">
                    <label for="selling_size">Продано: </label> <br/>
                    <input type="number" name="selling_size" min="0" id="selling_size" class="input-with"
                                    value="<#if product??>${product.selling_size}<#else>0</#if>"  />
                </div>
                <div class="mt-2">
                    <div id="inp_add"></div>

                    <input type="hidden" name="_csrf" class="input-with" value="${_csrf.token}"/>

                    <button class="btn btn-success" type="submit" id="btn-upd-product">Зберегти</button>
                </div>
            </div>
            <div class="col-4 form-group">

                <label for="description">Опис: </label> <br/>
                <textarea name="description" id="description" cols="30" rows="6" class="input-with">
                   <#if product??><#if product.description?has_content>${product.description}</#if></#if> </textarea> <br/>

                <div class="mt-2">
                    <label for="info">Додаткова інформація: </label>
                    <textarea name="info" id="info" cols="30" rows="2" class="input-with" >
                        <#if product??><#if product.info?has_content>${product.info}</#if></#if></textarea> <br/>
                    </div>
            </div>
    </div>
 </form>

    <div class="mt-2">

        <a class="btn btn-primary"
           onclick="addImage(<#if product??><#if product.id?has_content>'\/product\/add-img\/${product.id}'</#if></#if>)">
            Додати зображення</a>

            <div id="img_view"></div>
        <#if product??>
          <#if product.imageProducts?? && product.imageProducts?size gt 0>

           <table class="table-image-product ">
                    <COLGROUP>
                        <COL width="3*">
                        <COL width="0.3*">
                        <COL width="0.3*">
                        <COL width="0.3*">
                    <thead>
                    <tr>
                        <th>Зображення</th>
                        <th>Вітрина</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
            <tbody>
            <#list product.imageProducts as image>
                <tr>
                  <td>
                    <div class="img-zoom-container">
                       <div class="row">
                          <div class="col-6">
                             <img id="${image.id}" class="demo" src="/img/${image.imgProduct}"width="300" height="240"
                                            alt="image" />
                                </div>
                          <div class="col-6">
                              <div id="R${image.id}" class="img-zoom-result"></div>
                          </div>
                       </div>
                    </div>
                  </td>
                  <td>
                       <#if image.showcase>
                            <span>Вітрина</span><br/> <input  type="checkbox" name="showcase" checked value="${image.id}"/><br/>
                          <#else>
                            <span>Додаткове</span><br/><input type="checkbox" name="showcase" value="${image.id}" /><br/>
                       </#if>
                  </td>
                  <td>
                      <a class="btn btn-primary"  onclick="addImage('\/product\/update-img',${image.id})">Редагувати</a>
                  </td>
                  <td>
                      <a class="btn btn-primary" href="/product/delete-img/${image.id}">Видалити</a>
                  </td>
                </tr>
            </#list>
            </tbody>
           </table>
          </#if>
        </#if>
    </div>
</div>

<div id="myModalAddImg" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="closeMyModalAddImg">&times;</span>
            <h2>Додати зображення</h2>
        </div>
        <div class="modal-body">

            <input type="hidden" class="input-with"  id="productId"
                                        value="<#if product??><#if product.id?has_content> ${product.id}</#if></#if>" />

            <input type="hidden" class="input-with" id="im_csrf" value="${_csrf.token}"/>

            <div class="title-cart">

                <div id="place"> </div>

            </div>
        </div>
        <div class="modal-footer">
            <#--<h3>Футер модального окна</h3>-->
        </div>
    </div>
</div>

<script src="/static/js/product.js"></script>
<script src="/static/js/image-view.js"></script>

</@c.page>