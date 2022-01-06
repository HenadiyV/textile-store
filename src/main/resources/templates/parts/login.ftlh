<#macro login path isRegisterForm>
<div class="container outer" >
    <div class="inner">

    <form action="${path}" method="post">
        <div class="form-group row mb-3">

            <label class="col-sm-2 col-form-label">Ваш логін:</label>
            <div class="col-sm-6">

                <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid','')}"
                       value="<#if user??>${user.username}</#if>" placeholder="Логін" />

                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
            </div>
        </div>
        <div class="row mb-3">

            <label class="col-sm-2 col-form-label">Ваш пароль:</label>

            <div class="col-sm-6">

                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}"
                       placeholder="Пароль" />

                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
            </div>
        </div>
            <#if isRegisterForm>
         <div class="row mb-3 ">

              <label class="col-sm-2 col-form-label"> Повторіть пароль:</label>
             <div class="col-sm-6">

                 <input type="password" name="password2"
                        class="form-control ${(password2Error??)?string('is-invalid','')}"
                         placeholder="Введіть повторно пароль" />

                     <#if password2Error??>
                           <div class="invalid-feedback">
                               ${password2Error}
                           </div>
                     </#if>
              </div>
          </div>
          <div class="row mb-3 ">

                <label class="col-sm-2 col-form-label"> Ваша єлєктрона адресса:</label>
              <div class="col-sm-6">

                    <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid','')}"
                           value="<#if user??>${user.email}</#if>" placeholder="some@some.com" />

                    <#if emailError??>
                         <div class="invalid-feedback">
                            ${emailError}
                         </div>
                    </#if>
              </div>
          </div>
          <div class="row mb-3 ">

                <div class="col-sm-6">

                    <div class="g-recaptcha" data-sitekey="6Lde2ogcAAAAAEZlZ-ZU0IVIrltnWAytIeffSzbU"></div>

                <#if captchaError??>

                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>

                </div>
          </div>
         </#if>

        <div class="row mb-3 ">

            <div class="col-sm-6">

                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <#if !isRegisterForm> <a href="/registration" class="mr-3">Зареєструватись</a></#if>

                <button type="submit" class="btn btn-primary ml-10"><#if isRegisterForm>Зберегти<#else>Вхід</#if> </button>
            </div>
        </div>

    </form>
    </div>
</div>
</#macro>


<#macro logout>

<form action="/logout" method="post">

    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    <div class="col-auto">

        <button type="submit" class="btn btn-primary">Вихід</button>

    </div>

</form>

</#macro>