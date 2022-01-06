<#import "parts/common.ftlh" as c>


<@c.page>
<#--Login page-->
    <#--${message?ifExists}-->
<div class="container">
<h5>${username}</h5>

  <form  method="post">

      <div class="row mb-3">
          <label class="col-sm-2 col-form-label"> Ваш пароль:</label>
          <div class="col-sm-6">
              <input type="password" name="password" class="form-control" placeholder="Пароль" />
          </div>

            <div class="row mb-3">
                <label class="col-sm-2 col-form-label"> Ваша електрона адресса:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="some@some.com" valiue="${email!''}" />
                </div>
            </div>
      </div>
      <div class="col-auto">
          <input type="hidden" name="_csrf" value="${_csrf.token}" />
          <button type="submit" class="btn btn-primary">Зберегти</button>
      </div>


  </form>
</div>
</@c.page>