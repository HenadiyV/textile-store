
<div class="centr">
            <form action="/admin/add-user" method="post">
        <#if userIsPresent??><span>${userIsPresent}</span></#if>
                <div class="row">
                    <div class="col-sm-6 col-md-6 col-lx-4">
                        <div class="form-group mt-2">
                            <label for="username">Логін</label>
                            <input type="text" class="form-control ${(usernameError??)?string('is-invalid','')} "
                                   value="<#if user??>${user.username}<#else> </#if>"  name="username" id="username"  />
                              <#if usernameError??>
                                   <div class="invalid-feedback">
                                       ${usernameError}
                                   </div>
                              </#if>
                        </div>

                        <div class="form-group mt-2">
                            <label for="name">Імя</label>
                            <input type="text" class="form-control ${(nameError??)?string('is-invalid','')} "
                                   value="<#if user??>${user.name}<#else> </#if>"  name="name" id="name" />
                               <#if nameError??>
                                    <div class="invalid-feedback">
                                        ${nameError}
                                    </div>
                               </#if>
                        </div>

                        <div class="form-group mt-2">
                            <label for="email">email</label>
                            <input type="text" class="form-control ${(emailError??)?string('is-invalid','')} "
                                   value="<#if user??>${user.email}<#else> </#if>"  name="email" id="email"  />
                                <#if emailError??>
                                       <div class="invalid-feedback">
                                           ${emailError}
                                       </div>
                                </#if>
                        </div>

                        <div class="form-group mt-2">
                            <label for="add_phone">Телефон</label>
                            <input type="text" class="form-control ${(phoneError??)?string('is-invalid','')} "
                                   value="<#if phone??>${phone}</#if>"  name="phone" id="add_phone"
                                   maxlength="50"
                                   placeholder="+38(___)___-__-__"/>
                                        <#if phoneError??>
                                            <div class="invalid-feedback">
                                                ${phoneError}
                                            </div>
                                        </#if>
                        </div>

                        <div class="form-group mt-2">
                            <label for="postOffice">Почта</label>
                            <input type="text" class="form-control ${(postOfficeError??)?string('is-invalid','')} "
                                   value="<#if postOffice??>${postOffice}</#if>"  name="postOffice" id="postOffice"   />
                                  <#if postOfficeError??>
                                    <div class="invalid-feedback">
                                        ${postOfficeError}
                                    </div>
                                  </#if>
                        </div>

                        <div class="form-group mt-4">
                            <label>Активний <input type="checkbox" name="active" checked="checked"/> </label>
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-6 col-lx-4">
                        <div class="mt-2">
                            <label for="area">
                                <input type="text" name="search_address" id="area" placeholder="Введіть назву населеного пункту"/>
                            </label><br/>
                            <label for="adr" class="mt-2">
                                <select name="adr" id="adr" class="input-with"><option>Список адрес</option></select>
                            </label>
                        </div>

                        <div class="form-group mt-2">
                            <label for="region">Область</label>
                            <input type="text" class="form-control ${(regionError??)?string('is-invalid','')} "
                                   value="<#if region??>${region}</#if>"  name="region" id="region"  />
                                <#if regionError??>
                                    <div class="invalid-feedback">
                                        ${regionError}
                                    </div>
                                </#if>
                        </div>

                        <div class="form-group mt-2">
                            <label for="district">Район</label>
                            <input type="text" class="form-control ${(districtError??)?string('is-invalid','')} "
                                   value="<#if district??>${district}</#if>"  name="district" id="district"  />
                                <#if districtError??>
                                    <div class="invalid-feedback">
                                        ${districtError}
                                    </div>
                                </#if>
                        </div>

                        <div class="form-group mt-2">
                            <label for="city">Місто</label>
                            <input type="text" class="form-control ${(cityError??)?string('is-invalid','')} "
                                   value="<#if city??>${city}</#if>"  name="city" id="city"  />
                                <#if cityError??>
                                    <div class="invalid-feedback">
                                        ${cityError}
                                    </div>
                                </#if>
                        </div>

                        <div class="form-group mt-2">
                            <label for="address">Адрес</label>
                            <input type="text" class="form-control ${(addressError??)?string('is-invalid','')} "
                                   value="<#if address??>${address}</#if>"  name="address" id="address"  />
                                <#if addressError??>
                                <div class="invalid-feedback">
                                    ${addressError}
                                </div>
                                </#if>
                        </div>

                        <div class="form-group mt-2">
                            <label for="postCode">Індекс</label>
                            <input type="text" class="form-control ${(postCodeError??)?string('is-invalid','')} "
                                   value="<#if postCode??>${postCode}</#if>"  name="postCode" id="postCode"  />
                                <#if postCodeError??>
                                <div class="invalid-feedback">
                                    ${postCodeError}
                                </div>
                                </#if>
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-6 col-lx-4">
                        <div class="form-group mt-2">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        </div>
                    </div>
                </div>
                <label for="infoU">Інфо</label><br/>
                <textarea name="info" id="infoU" cols="27" rows="3" class="input-with"><#if info??>${info}<#else> </#if></textarea><br/>

                <button class="btn btn-primary ml-2" type="submit">Зберегти</button>

            </form>
</div>
<script src="/static/js/search_address.js"></script>