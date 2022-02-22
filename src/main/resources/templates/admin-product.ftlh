<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>

<@c.page><@p.pager url page ></@p.pager>
<div class="container">

    <h4 class="centr">Список тканин</h4>


    <a class="btn btn-primary mb-2"  href="/add-product" > Додати товар  </a>
    <a class="btn btn-primary mb-2" data-bs-toggle="collapse" href="#searchBlock" role="button" aria-expanded="false" aria-controls="searchBlock">Пошук</a>
    <div class="collapse" id="searchBlock">
        <div class="card card-body">
            <div></div>
            <div>
                <form action="/product/search">
                    <label>Пошук товару
                        <input type="text" name="product_name"/>
                    </label>
                    <div class="centr">
                    <button class="btn btn-primary " type="submit">Шукати</button>
                </div>

            </form>

            </div>
        </div>
    </div>
    <div class="row row-cols-2 row-cols-md-2 ">
    <#list page.content as product>

        <div class="col">
            <div class="card">

                <div class="row g-0">

                <div class="col-md-4">
                <#if product.imageProducts?? >
                    <#list product.imageProducts as img>
                        <#if img.showcase><img src="/img/${img.imgProduct}" class="card-img-top mt-2" style="width: 150px;" alt="..."/></#if>
                    </#list>
                    </#if>
                </div>
             <div class="col-md-8">
                 <div class="card-body">


                    <h5 class="card-title">${product.name}</h5>

                   <div> <span>Колір: ${product.color}</span></div>
                    <div><span>Ціна закупки: ${product.purchasePrice}</span></div>
                    <div><span>Ціна закупки(долар): ${product.purchasePriceDollar}</span></div>
                    <div><span>Ціна продажу: ${product.sellingPrice}</span></div>
                    <div><span>Кількість: ${product.sizeProduct}</span></div>
                    <div> <span>Продано: ${product.selling_size}</span></div>
                    <div><span>Дата: ${product.dat?date?string('dd-MM-yyyy')}</span></div>
                    <div><span>ID: <b>${product.id}</b></span></div>

                </div>
             </div>
               <div class="col-md-10">
                  <div class="centr"><h6>Опис:</h6></div>
                   <div style="height: 100px;width:auto;overflow: auto;">
                       <p class="card-text">${product.description}</p>

                   </div>
               </div>
            </div>

                <div class="cart-body">
                <#if product.tags??>
                    <#list product.tags as tag>
                        ${tag.name}
                    </#list>
    </#if><br/>
                <a  class="btn btn-success" href="/edit-product/${product.id}">Редагувати</a>
                    <a href="/order/user-product/${product.id}" class="btn btn-primary">&#8192;Покупці&#8192;&#8192;</a>
                </div>
                </div>
            </div>
    </#list>
           </div>
</div>

</@c.page>