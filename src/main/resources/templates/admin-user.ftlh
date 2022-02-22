<#import "parts/common.ftlh" as c>
<@c.page>
<div class="container">
    <h4 class="centr">Користувачі</h4>
    <input type="text" id="search_user" placeholder="Пошук користувача" />
    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        <#if !userUp??>
            Додати користувача
        <#else>
            <span class="mr-2">Редагувати</span>
           <span class="ml-2"> <a href="/admin/user" class="btn btn-danger">&#8192;Вийти з редагування&#8192;</a></span>
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
                    <COLGROUP>
                        <COL width="40%">
                        <COL width="40%">
                        <COL width="20%">
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
                                 value="<#if phone??>${phone.phone}</#if>" id="phone#{phone.id}" name="phone"
                                      <#--pattern="\+38\[\(][0-9]{3}[\)]\d{3}[-]\d{2}[-]\d{2}"-->
                                      placeholder="+38(___)___-__-__" />

                               <div id="phoneErr#{phone.id}" > </div>
                           </td>
                           <td>
                               <label for="info_phone#{phone.id}">Додаткова інформація</label><br/>
                               <textarea cols="15" rows="2" name="info" id="info_phone#{phone.id}"
                                         class="input-with" ><#if phone?has_content>${phone.info} </#if></textarea>
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
                    <COLGROUP>
                        <COL width="40%">
                        <COL width="40%">
                        <COL width="20%">
                    <thead>
                    <tr>
                        <th>Почтове відділення</th>
                        <th>Інфо</th>
                        <th> <a class="btn btn-primary"onclick="showModal('modalPostOffice')">Додати</a> </th>
                    </tr>
                    </thead>
                    <tbody>
                      <#list userUp.postOfficeUsers as postOffice>
                         <tr>
                          <td>
                            <input type="text" value="<#if postOffice.postOffice??>${postOffice.postOffice}</#if>"
                                name="postOffice" id="postOffice#{postOffice.id}"  placeholder="postOffice" />

                            <div id="postOfficeErr#{postOffice.id}"> </div>
                          </td>
                          <td>
                            <label for="info_post_office${postOffice.id}">Додаткова інформація</label><br/>
                            <textarea cols="15" rows="2" name="info" id="info_post_office${postOffice.id}" class="input-with" ><#if postOffice??>${postOffice.info} </#if></textarea>
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
                    <COL width="10%">
                    <COL width="10%">
                    <COL width="10%">
                    <COL width="30%">
                    <COL width="10%">
                    <COL width="20%">
                    <COL width="10%">

                <thead>
                <tr>
                    <th>Область</th><th>Район</th><th>Місто</th><th>Адреса</th><th>Індекс</th><th>Інфо</th>
                    <th> <button class="btn btn-primary" onclick="showModal('myModalAddress')">Додати</button></th>
                </tr>
                </thead>
                <tbody>
                        <#list userUp.addresses as address>
                        <tr>
                            <td>
                                <input type="text" value="<#if address.city?trim?length gt 1>${address.region}</#if>"
                                       name="region" id="region${address.id}" placeholder="Область" />
                            </td>
                            <td>
                                <input type="text" value="<#if address.city?trim?length gt 1>${address.district}</#if>"
                                       name="district" id="district${address.id}" placeholder="Район" />
                            </td>
                            <td>
                                <input type="text" value="<#if address.city?trim?length gt 1>${address.city}</#if>"
                                       name="city" id="city${address.id}" placeholder="Місто" />
                            </td>
                            <td>
                                <input type="text" value="<#if address.address?trim?length gt 1>${address.address}</#if>"
                                       name="address" id="address${address.id}" placeholder="Адреса" />
                                <div id="addressErr#{address.id}" >
                                </div>
                            </td>
                            <td>
                                <input type="text" value="<#if address.postCode?trim?length gt 1>${address.postCode}</#if>"
                                       name="postCode" id="postCode${address.id}" placeholder="Індекс" />

                            </td><td>
                            <label for="info_address${address.id}">Додаткова інформація</label><br/>
                            <textarea name="info" id="info_address${address.id}" rows="2" cols="28">
                                <#if address.postCode?has_content>${address.postCode}</#if></textarea>

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
    <#include "parts/add-user.ftlh"/>

    </#if>

    </div>

    <#if users??>
    <div class="centr">
        <div class="arrow-61">
            <a  onclick='userListRest(direction+20)'>

                <svg width="18px" height="17px" viewBox="-1 0 18 17" version="1.1" xmlns="http://www.w3.org/2000/svg"
                     xmlns:xlink="http://www.w3.org/1999/xlink">
                    <g>
                        <polygon class="arrow-61-pl"
                                 points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596">
                        </polygon>
                        <polygon class="arrow-61-pl-fixed"
                                 points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596">
                        </polygon>
                        <path
                                d="M-4.58892184e-16,0.56157424 L-4.58892184e-16,16.1929159 L9.708,8.33860465 L-1.64313008e-15,0.56157424 L-4.58892184e-16,0.56157424 Z M1.33333333,3.30246869 L7.62533333,8.34246869 L1.33333333,13.4327013 L1.33333333,3.30246869 L1.33333333,3.30246869 Z">
                        </path>
                    </g>
                </svg>

            </a>
     <a  onclick='userListRest(direction<20?0:(direction-20))'>

             <svg width="18px" height="17px" viewBox="0 0 18 17" version="1.1" xmlns="http://www.w3.org/2000/svg"
                  xmlns:xlink="http://www.w3.org/1999/xlink">
                 <g transform="translate(8.500000, 8.500000) scale(-1, 1) translate(-8.500000, -8.500000)">
                     <polygon class="arrow-61-pl"
                              points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596">
                     </polygon>
                     <polygon class="arrow-61-pl-fixed"
                              points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596">
                     </polygon>
                     <path
                             d="M-1.48029737e-15,0.56157424 L-1.48029737e-15,16.1929159 L9.708,8.33860465 L-2.66453526e-15,0.56157424 L-1.48029737e-15,0.56157424 Z M1.33333333,3.30246869 L7.62533333,8.34246869 L1.33333333,13.4327013 L1.33333333,3.30246869 L1.33333333,3.30246869 Z">
                     </path>
                 </g>
             </svg>

     </a>

        </div>
    </div>
        <#include "parts/user-list.ftlh"/>

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
                                <label>ID_User<br/>
                                    <input type="text"   name="user_id" value="<#if userUp??>${userUp.id}</#if>" />
                                </label>
                                <label>Додаткова інформація:<br/>
                                    <textarea name="info" rows="2" cols="28"></textarea>
                                </label>
                                <label for="phone">Ваш телефон:</label> <br/>
                                <input type="tel" name="phone" id="o_phone" autofocus="autofocus" maxlength="50"
                                       value="+38(___)___-__-__" required="required"
                                       pattern="\+38\[\(][0-9]{3}[\)]\d{3}[-]\d{2}[-]\d{2}"
                                       placeholder="+38(___)___-__-__"/><br/>
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

                                <label>ID_User:<br/>
                                    <input type="text"   name="user_id" value="<#if userUp??>${userUp.id}</#if>" />
                                </label>
                                <label>Область:<br/>
                                    <input type="text" name="region" />
                                </label>
                                <label>Район:<br/>
                                    <input type="text" name="district" />
                                </label>
                                <label>Місто:<br/>
                                    <input type="text" name="city" />
                                </label>
                                <label>Алреса:<br/>
                                    <input type="text"   name="address"   />
                                </label>
                                <label>Індекс:<br/>
                                    <input type="text"   name="postCode"  />
                                </label>
                                <label for="adr_info">Додаткова інформація:</label><br/>
                                <textarea name="info" id="adr_info" cols="25" rows="2"></textarea>
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

                                <label>Пошта:
                                    <input type="text"   name="postOffice" />
                                </label>
                                <label for="po_info">Додаткова інформація:</label>
                                <textarea name="info" id="po_info" cols="25" rows="2"></textarea>
                                <label>ID_User
                                    <input type="text"   name="user_id" value="<#if userUp??>${userUp.id}</#if>" />
                                </label>
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
<script src="/static/js/phone.js"></script>
</@c.page>