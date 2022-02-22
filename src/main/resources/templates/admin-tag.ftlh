<#import "parts/common.ftlh" as c>
<@c.page>

<h4 class="centr">Теги</h4>
<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
        <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Додати тег
        </a>
        <div class="collapse <#if tag??>show</#if>" id="collapseExample">
            <div class="form-group mt-3 mb-3" >

                <form  method="post" >
                    <div class="form-group mt-2">
                        <input type="text" class="form-control ${(nameError??)?string('is-invalid','')} "
                               value="<#if tag??>${tag.name}</#if>"  name="name" id="tagName" placeholder="Повідомлення" />
        <#if nameError??>
        <div class="invalid-feedback">
            ${nameError}
        </div>
        </#if>
                    </div>

                    <div class="form-group mt-2">

                        <input type="hidden" name="_csrf" value="${_csrf.token}" />

                        <#--<label for="id">ID тегу</label>-->
                        <input type="hidden" name="id" id="id" value="<#if tag??>${tag.id}<#else>-1</#if>" />
                        <button class="btn btn-primary ml-2" type="submit">Зберегти</button>
                    </div>
                </form>
            </div>
        </div>
        <table>
            <thead>
            <tr>
                <th>Тег</th>
                <th></th>
            </tr>
            </thead><tbody>
<#list tagList as tag>
<tr>
    <td>${tag.name}</td>
    <td>
        <a class="btn btn-success" href="/tag/${tag.id}">Редагувати</a>
    </td>

</tr>
</#list>
        </tbody>
        </table>
    </div>
    <div class="col-2"></div>
</div>

</@c.page>