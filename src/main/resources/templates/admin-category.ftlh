<#import "parts/common.ftlh" as c>
<@c.page>

<h4 class="centr">Category</h4>
<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
        <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Додати категорію
        </a>
        <div class="collapse <#if category??>show</#if>" id="collapseExample">
            <div class="form-group mt-3 mb-3" >

                <form  method="post" enctype="multipart/form-data">
                    <div class="form-group mt-2">
                        <input type="text" class="form-control ${(nameError??)?string('is-invalid','')} "
                               value="<#if category??>${category.name}</#if>"  name="categoryName" id="categoryName" placeholder="Повідомлення" />
        <#if nameError??>
        <div class="invalid-feedback">
            ${nameError}
        </div>
        </#if>
                    </div>

                    <div class="form-group mt-2">

                        <input type="hidden" name="_csrf" value="${_csrf.token}" />

                        <label for="id">ID категорії</label>
                        <input type="text" name="id" id="id" value="<#if category??>${category.id}<#else>-1</#if>" />
                        <button class="btn btn-primary ml-2" type="submit">Зберегти</button>
                    </div>
                </form>
            </div>
        </div>
        <table>
            <thead>
            <tr>
                <th>Назва категорії</th>
                <th></th>
            </tr>
            </thead><tbody>
<#list categoryList as category>
<tr>
    <td>${category.name}</td>
    <td>
        <a class="btn btn-success" href="/category/${category.id}">Редагувати</a>
    </td>

</tr>
</#list>
        </tbody>
        </table>
    </div>
    <div class="col-2"></div>
</div>

</@c.page>