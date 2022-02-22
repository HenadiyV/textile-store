<#import "common.ftlh" as c>

<@c.page>
<div class="container">

 <form  method="post" enctype="multipart/form-data">
<div>
<h6>Теги</h6>
    <#list tagList as tag>
        <#if tag.active>
             <label style="border: solid silver;padding: 1px" class="centr">${tag.name}<br/>
           <input type="checkbox" class="checkbox" name="tag" value="${tag.id}" checked="checked"/>
             </label>
        <#else>
           <label style="border: solid silver;padding: 1px" class="centr">${tag.name}<br/>
           <input type="checkbox" class="checkbox" name="tag" value="${tag.id}"/>
           </label>
        </#if>
    </#list>

    <input type="hidden" name="tagStr" value=""/>
</div>
    <div class="row mt-5">

       <div class="col-4 form-group">

            <input type="hidden" name="id_product" id="id_product" class="input-with"
                   value="<#if product??><#if product.id?has_content>${product.id}</#if></#if>" />

            <label for="name">Назва: </label> <br/>
             <input type="text" name="name" id="name" class="form-control ${(nameError??)?string('is-invalid','')}"
                           value="<#if product??><#if product.name?has_content>${product.name}</#if></#if>" />

                        <#if nameError??>
                                <div class="invalid-feedback">
                                    ${nameError}
                                </div>
                        </#if>
             <div class="mt-2">
                    <label for="color">Колір: </label><br/>
                    <input type="text" name="color" id="color" class="form-control"
                           value="<#if product??><#if product.color?has_content>${product.color}</#if></#if>" />
             </div>
             <div class="mt-2">
                    <label for="category">Категорія</label> <br/>
                        <select id="category" name="category" class="form-control">
                        <#list categoryList as category>
                            <#if product??>
                                <#if product.category.id?has_content>
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
             <div class="mt-2">
                    <span>Дата</span><br/>
                    <input type="text" id="dat_dispatch" name="dat_dispatch" class="form-control"value="" />
                    <input type="hidden" id="dat_dispatch1" class="input-with"
                           value ="<#if product??><#if product.dat?has_content>${product.dat?date?string('yyyy/MM/dd')}</#if></#if>"  />
             </div>
             <div class="mt-2">
                 <#if product??>
                      <#if product.active>
                         <label>Активний <input type="checkbox" name="active" checked value="1"/></label><br/>
                      <#else>
                         <label>Не активний<input type="checkbox" name="active" value="0"/></label><br/>
                      </#if>
                  <#else>
                      <label>Активний <input type="checkbox" name="active" checked value="1"/></label><br/>
                 </#if>
                 <#if imgUrl??>

                 <a class="btn btn-primary mt-1"
                          onclick="addImage('${imgUrl}')">
                 Додати зображення</a>
                 </#if>

                </div>
       </div>
       <div class="col-4 form-group">
               <label style="width: 48%">Ціна : Дол.<input type="number" name="purchasePriceDollar" min="0"   id="dollar"
                                                           value="<#if product??>${product.purchasePriceDollar?replace(',','.')}</#if>" class="form-control" />
               </label>
               <label style="width: 48%">Ціна : Грн. <input type="number" name="purchasePrice"  min="0"  id="hrivna"
                                                            value="<#if product??>${product.purchasePrice?replace(',','.')}</#if>" class="form-control" />
               </label> <br/>
           <div class="mt-2">
                <label for="sellingPrice">Ціна продажу: </label><br/>
                    <input type="number" name="sellingPrice" min="0" step="0.1" id="sellingPrice"  class="form-control
                                ${(sellingPriceError??)?string('is-invalid','')}"
                             value="<#if product??><#if product.sellingPrice?has_content>${product.sellingPrice?replace(',','.')}<#else>0</#if><#else>0</#if>" />
                        <#if sellingPriceError??>
                          <div class="invalid-feedback">
                             ${sellingPriceError}
                          </div>
                        </#if>
            </div>
            <div class="mt-2">
                <label for="sizeProduct">Кількість: </label> <br/>
                    <input type="number" name="sizeProduct" min="0" step="0.1" id="sizeProduct" class="form-control
                                ${(sizeProductError??)?string('is-invalid','')}"
                                    value="<#if product??><#if product.sellingPrice?has_content>${product.sizeProduct?replace(',','.')}<#else>0</#if><#else>0</#if>" />
                     <#if sizeProductError??>
                         <div class="invalid-feedback">
                            ${sizeProductError}
                         </div>
                     </#if>
            </div>
            <div class="mt-2">
                <label for="selling_size">Продано: </label> <br/>
                    <input type="number" name="selling_size" min="0" step="0.1" id="selling_size" class="form-control"
                                    value="<#if product??><#if product.selling_size?has_content>${product.selling_size?replace(',','.')}<#else>0</#if><#else>0</#if>"  />
            </div>
            <div class="mt-4">
                <div id="inp_add"></div>
                </div>
            </div>
            <div class="col-4 form-group">
                <label for="description">Опис: </label> <br/>
                <textarea name="description" id="description" cols="30" rows="6"
                          class="input-with"><#if product??><#if product.description?has_content>${product.description}<#else> </#if></#if></textarea> <br/>
                <div class="mt-2">
                    <label for="info">Додаткова інформація: </label>
                    <textarea name="info" id="info" cols="30" rows="2" class="input-with" ><#if product??><#if product.info?has_content>${product.info}<#else> </#if></#if></textarea> <br/>
                </div>
                <div class="mt-4">
                <input type="hidden" name="_csrf" class="input-with" value="${_csrf.token}"/>

                <button class="btn btn-success" type="submit" id="btn-upd-product">Зберегти</button>
                </div>
            </div>
    </div>
 </form>

<#if product??>

    <#if product.id?has_content>
    <div class="mt-2">

        <a class="btn btn-primary"
           onclick="addImage('/product/add-img/${product.id}')">
            Додати зображення</a>
    </#if>
</#if>
            <div id="img_view"></div>
        <#if product??>
          <#if product.imageProducts?? && product.imageProducts?size gt 0>
<#--class="table-image-product "-->
           <table class="table-image-product " style="width: 100%">
                    <COLGROUP>
                        <COL width="3*">
                        <COL width="0.3*">
                        <COL width="0.3*">
                        <COL width="0.3*">
                        <COL width="0.3*">
                    <thead>
                    <tr>
                        <th>Зображення</th>
                        <th>Збільшене</th>
                        <th>Вітрина</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
            <tbody>
            <#list product.imageProducts as image>
                <tr>
                  <td>
                    <div class="img-zoom-container"><img id="${image.id}" class="demo" src="/img/${image.imgProduct}"width="150"
                                                         alt="image" />

                    </div>
                  </td>
                    <td class="center">

                        <div id="R${image.id}" class="img-zoom-result" ></div>
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
<script>
    window.addEventListener("load", function(event) {
        document.querySelector("input[name='tagStr']").value=getCheckedCheckBoxes();
    });

  let inp_tag=  document.querySelectorAll("input[name='tag']");
  inp_tag.forEach(el=>el.addEventListener("click",()=>{
        document.querySelector("input[name='tagStr']").value=getCheckedCheckBoxes();
    }));

    function getCheckedCheckBoxes() {
        let checkboxes = document.getElementsByClassName('checkbox');
        let checkboxesChecked = []; // можно в массиве их хранить, если нужно использовать
        for (let index = 0; index < checkboxes.length; index++) {
            if (checkboxes[index].checked) {
                checkboxesChecked.push(checkboxes[index].value); // положим в массив выбранный
               // alert(checkboxes[index].value); // делайте что нужно - это для наглядности
            }
        }
        return checkboxesChecked; // для использования в нужном месте
    }

</script>
</@c.page>
