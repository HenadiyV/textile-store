<#import "parts/common.ftlh" as c>
<@c.page>
<div class="container">
    <h4 class="centr">Користувачі</h4>

    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        <#if !userUp??>Додати користувача <#else> <span class="mr-2">Редагувати</span>
           <span class="ml-2"> <a href="/admin/user" class="btn btn-danger">&#8192;Очистити&#8192;</a></span>
        </#if>
    </a>
        <div class="collapse <#if userUp??>show<#elseif user??> show</#if>" id="collapseExample">

    <#if userUp??>


            <div class="edit-user">

                <h4 class="centr mt-2">Користувач</h4>

                <form action="/admin/user-update/" method="post">
                    <div class="row">
                        <div class="col-3">
                            <label for="username">Логін</label>


                            <input type="text" class="form-control ${(usernameError??)?string('is-invalid','')} "
                                   value="<#if userUp??>${userUp.username}</#if>"  name="username" id="username" placeholder="username" />
                              <#if usernameError??>
                                   <div class="invalid-feedback">
                                       ${usernameError}
                                   </div>
                              </#if>
                            <label for="name">Імя</label>
                            <input type="text" class="form-control ${(nameError??)?string('is-invalid','')} "
                                   value="<#if userUp??>${userUp.name}</#if>"  name="name" id="name" placeholder="name" />
                               <#if nameError??>
                                    <div class="invalid-feedback">
                                        ${nameError}
                                    </div>
                               </#if>
                        </div>

                        <div class="col-3">
                            <label for="email">email</label>
                            <input type="text" class="form-control ${(emailError??)?string('is-invalid','')} "
                                   value="<#if userUp??>${userUp.email}</#if>"  name="email" id="email" placeholder="email" />
                                <#if emailError??>
                                       <div class="invalid-feedback">
                                           ${emailError}
                                       </div>
                                </#if>
                            <label for="rating">Рейтинг</label>
                            <input type="number" class="form-control" name="rating" id="rating" max="100" min="0" step="1"
                                   value="<#if userUp??>${userUp.rating}</#if>"   />
                        </div>

                        <div class="col-3">
                            <label for="info_user">Інформація</label>
                            <textarea name="info" id="info_user" cols="25" rows="3"><#if userUp.info?has_content>${userUp.info} </#if></textarea>

                        </div>

                        <div class="col-3 ">

                            <label ><#if userUp.active>активний<#else>не активний</#if>
                                <input type="checkbox" name="active"
                                           <#if userUp.active>checked="checked" value="1" <#else>checked="" value="0"</#if>/>
                            </label>
                            <div class="mt-3">
                                <input type="hidden" name="_csrf" id="frm_csrf" value="${_csrf.token}" />
                                <input type="hidden" name="id" id="id" value="<#if userUp??>${userUp.id}<#else>-1</#if>" />
                                <button class="btn btn-primary " type="submit">Зберегти</button>
                            </div>
                        </div>

                    </div>

                </form>

            </div>

            <div class="row container-edit-phone-post-office">

                <div class="col-6 mt-2 edit-phone">
                <h4 class="centr ">Телефон</h4>
                        <table class="table-modal">
                          <thead>
                            <tr>
                              <th>Телефон</th><th>Інфо</th><th><a class="btn btn-primary" onclick="showModal('myModalPhone')">Додати</a> </th>
                            </tr>
                          </thead>
                          <tbody>
                            <#list userUp.phones as phone>
                              <tr>
                                <td>
                                  <input type="text" class=" ${(phonError??)?string('is-invalid','')} "
                                         value="<#if phone??>${phone.phone}</#if>" id="phone#{phone.id}" name="phone"  placeholder="phone" />
                                   <div id="phoneErr#{phone.id}" >
                                   </div>
                                </td>
                                  <td>
                                  <textarea cols="15" rows="2" name="info" id="info_phone#{phone.id}" ><#if phone?has_content>${phone.info} </#if></textarea>
                                </td>
                                <td>

                                    <a class="btn btn-success" onclick="editPhone(#{phone.id},#{userUp.id})" >Зберегти </a>
                                </td>
                              </tr>
                            </#list>
                          </tbody>
                        </table>
                   </div>

                <div class="col-6 mt-2 edit-post-office">
                         <h4 class="centr ">Почта</h4>
                                 <table class="table-modal">
                                    <thead>
                                      <tr>
                                        <th>Почтове відділення</th><th>Інфо</th><th><a class="btn btn-primary"
                                                                          onclick="showModal('modalPostOffice')">Додати</a> </th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                        <#list userUp.postOfficeUsers as postOffice>
                                      <tr>


                                        <td>
                                            <input type="text"
                                                   value="<#if postOffice.postOffice?trim?length gt 1>${postOffice.postOffice} </#if>"
                                                   name="postOffice" id="postOffice#{postOffice.id}"  placeholder="postOffice" />

                                            <div id="postOfficeErr#{postOffice.id}">
                                            </div>
                                        </td>
                                              <td>
                                                  <textarea cols="15" rows="2" name="info" id="info_post_office${postOffice.id}" ><#if postOffice??>${postOffice.info} </#if></textarea>
                                              </td>
                                        <td>
                                           <button class="btn btn-success"  onclick="editPostOffice(#{postOffice.id},#{userUp.id})" >Зберегти</button>
                                        </td>

                                    </tr>
                                        </#list>
                                    </tbody>
                                </table>

                        </div>

            </div>

            <div class="edit-address">

                <h4 class="centr mt-2 ">Адресса</h4>

                <table class="table-modal">
                    <COLGROUP>
                <COL width="20%">
                <COL width="35%">
                <COL width="20%">
                <COL width="10%">
                <COL width="15%">

                    <thead>
                        <tr>
                        <th>Місто</th><th>Адресса</th><th>Індекс</th><th>Інфо</th>
                            <th> <button class="btn btn-primary" onclick="showModal('myModalAddress')">Додати</button></th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list userUp.addresses as address>
                             <tr>
                                     <td>
                                         <input type="text" value="<#if address.city?trim?length gt 1>${address.city}</#if>"
                                                name="city" id="city${address.id}" placeholder="city" />
                                     </td>
                                     <td>
                                         <input type="text" value="<#if address.address?trim?length gt 1>${address.address}</#if>"
                                               name="address" id="address${address.id}" placeholder="address" />
                                         <div id="addressErr#{address.id}" >
                                         </div>
                                     </td>
                                 <td>
                                     <input type="text" value="<#if address.postCode?trim?length gt 1>${address.postCode}</#if>"
                                            name="postCode" id="postCode${address.id}" placeholder="postCode" />

                                 </td><td>
                                         <textarea name="info" id="info_address${address.id}" rows="2" cols="28"><#if address.postCode?has_content>${address.postCode}</#if></textarea>
                                     </td>
                                     <td>
                                         <button class="btn btn-success" onclick="editAddress(#{address.id},#{userUp.id})" >Зберегти</button>
                                          </td>
                             </tr>
                        </#list>
                    </tbody>
                </table>

            </div>

    <#else>
            <form action="/admin/add-user" method="post">
        <#if userIsPresent??><span>${userIsPresent}</span></#if>
                    <div class="row">
                        <div class="col-3">
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
                                       <#--placeholder="Імя"-->
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
                        </div>

                    <div class="col-3">
                            <div class="form-group mt-2">
                                <label for="phone">Телефон</label>
                                <input type="text" class="form-control ${(phoneError??)?string('is-invalid','')} "

                                       value="<#if phone??>${phone}</#if>"  name="phone" id="phone"   />
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
                                <div class="form-group mt-5">
                                <label>Активний <input type="checkbox" name="active" checked="checked"/> </label>
                            </div>
                        </div>
                    <div class="col-3">
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

                    <div class="col-3">

                                <div class="form-group mt-2">
                                <label for="infoU">Інфо</label>
                                <textarea name="info" id="infoU" cols="27" rows="5" value="<#if info??>${info}<#else> </#if>"></textarea>
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />


                                <button class="btn btn-primary ml-2" type="submit">Зберегти</button>
                            </div>
                        </div>
                    </div>
                </form>
    </#if>

    </div>

    <#if users??>

<div class="mt-3">
        <table class="table-modal">
            <COLGROUP>
                <COL width="30%">
                <COL width="15%">
                <COL width="30%">
                <COL width="15%">
                <COL width="10%">
            <thead>
            <tr>
                <th>Логін/Імя</th>
                <th>Phone</th>

                <th>Address</th>

                <th>Post office</th>

                <th></th>
            </tr>
            </thead><tbody>
            <#list users as user>
         <tr>
                <td>  ${user.username} <#if user.name?has_content>/ ${user.name} </#if> </td>
              <#if user.phones??><td>

                        <select>
                                <#list user.phones as phone>
                                  <option>${phone.phone}</option>
                                </#list>
                            </select>


        </td><#else><td></td>
                </#if>
                <#if user.addresses??>
               <td>
                   <select>
                        <#list user.addresses as address>
                            <option>${address.city} | ${address.address} | ${address.postCode}</option>
                        </#list>
                    </select>

                </td>
                <#else><td></td>
                </#if>
        <#if user.postOfficeUsers??>
           <td>

               <select> <#list user.postOfficeUsers as postOffice>

                        <option>${postOffice.postOffice}</option>
                        </#list>
               </select>

           </td>
        <#else><td></td>
        </#if>
        <td><a class="btn btn-primary" href="/admin/user-update/${user.id}">Редагувати</a></td>
            </#list>

        </tr>
        </tbody>
        </table>
</div>
    </#if>

        <#--ADD PHONE-->
        <div id="myModalPhone" class="modal">
               <div class="modal-content">
                   <div class="modal-header">
                       <span class="close" id="closeMyModalPhone">&times;</span>
                       <h2>Шапка модального окна</h2>
                   </div>
                   <div class="modal-body">
                       <div class="title-cart">

                       <form  action="/admin/add-phone" method="post" >
                           <div class="row">
                               <div class="col-6 mt-2">
                                   <input type="text"   name="user_id" value="<#if userUp??>${userUp.id}</#if>" />
                                   <textarea name="info" rows="2" cols="28"></textarea>
                                   <input type="text" name="phone" />
                                   <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                   <button type="submit" id="btn_form" class="btn btn-primary">Зберегти</button>
                               </div>
                           </div>
                       </form>
                   </div>
                   </div>
                   <div class="modal-footer">
                       <#--<h3>Футер модального окна</h3>-->
                   </div>

               </div>
           </div>

        <#--ADD ADDRESS-->
        <div id="myModalAddress" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close" id="closeMyModalAddress">&times;</span>
                    <h2>Шапка модального окна</h2>
                </div>
                <div class="modal-body">
                    <div class="title-cart">

                        <form  action="/admin/add-address" method="post" >
                            <div class="row">
                                <div class="col-6 mt-2">

                                    <input type="text"   name="user_id" value="<#if userUp??>${userUp.id}</#if>" />
                                    <input type="text" name="city" />
                                    <input type="text"   name="address"   />
                                    <input type="text"   name="postCode"  />
                                    <textarea name="info" id="" cols="25" rows="2"></textarea>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" id="btn_form" class="btn btn-primary">Зберегти</button>
                                </div>

                            </div>
                     </form>
                    <div class="modal-footer">
                        <#--<h3>Футер модального окна</h3>-->
                    </div>
                </div>
            </div>
        </div>
        </div>

        <#--ADD POSTOFFICE-->
        <div id="modalPostOffice" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close" id="closeMyModalPostOffice">&times;</span>
                    <h2>Шапка модального окна</h2>
                </div>
                <div class="modal-body">
                    <div class="title-cart">

                        <form action="/admin/add-post-office" method="post" >
                            <div class="row">
                                <div class="col-6 mt-2">

                                    <input type="text"   name="postOffice" />
                                    <textarea name="info" id="" cols="25" rows="2"></textarea>
                                    <input type="text"   name="user_id" value="<#if userUp??>${userUp.id}</#if>" />
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" id="btn_form" class="btn btn-primary">Зберегти</button>
                                </div>

                            </div>
                        </form>
                    <div class="modal-footer">
                        <h3>Футер модального окна</h3>
                    </div>
                </div>
            </div>
        </div>
        </div>
</div>
<script src="/static/js/admin_user.js"></script>
</@c.page>