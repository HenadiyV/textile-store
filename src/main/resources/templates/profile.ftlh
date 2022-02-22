<#import "parts/common.ftlh" as c>


<@c.page>
<#--Login page-->
    <#-- centr${message?ifExists}mr-3style="display:block;height: 200px"-->
<style>
    .not-corect{
        background: #dabcba;
    }

</style>
<div class="container" >
    <div class="centr"><h4>${user.username}</h4></div>

    <div class="centr mt-3">

        <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseUser" role="button"
           aria-expanded="false" aria-controls="collapse">
            Редагувати профіль
        </a>
        <a class="btn btn-primary " data-bs-toggle="collapse" href="#collapsePhone" role="button"
           aria-expanded="false" aria-controls="collapsePhone">
            Додати телефон
        </a>

        <a class="btn btn-primary " data-bs-toggle="collapse" href="#collapsePostOffice" role="button"
           aria-expanded="false" aria-controls="collapsePostOffice">
            Додати поштове відділення
        </a>

        <a class="btn btn-primary " data-bs-toggle="collapse" href="#collapseAddress" role="button"
           aria-expanded="false" aria-controls="collapseAddress">
            Додати адресу
        </a>

    </div>


    <div class="collapse <#if usernameError??>show</#if><#if emailError??>show</#if>" id="collapseUser">
        <div class="centr">
        <div class="card card-body">
            <form action="/user/profile-user" method="post">
                <div class="row mb-3">

                    <div class="col-sm-6 col-md-6 col-lx-6">
                        <label> Нік:</label>
                        <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid','')}"
                               value="${user.username!''}" placeholder="Нік" />

                        <#if usernameError??>
                            <div class="invalid-feedback">
                                ${usernameError}
                            </div>
                        </#if>

                        <label> Імя:</label>
                        <input type="text" name="name" class="form-control" value="${user.name!''}" placeholder="Імя" />
                        <label > Ваша електрона адресса:</label>
                        <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid','')}"
                               placeholder="some@some.com" value="${user.email!''}" />
                        <#if emailError??>
                            <div class="invalid-feedback">
                                ${emailError}
                            </div>
                        </#if>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    </div>

                    <div class="col-sm-6 col-md-6 col-lx-6">
                        <label > Ваш пароль:</label>
                        <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}"
                               placeholder="Пароль" />
                         <#if passwordError??>
                            <div class="invalid-feedback">
                                ${passwordError}
                            </div>
                         </#if>
                        <label > Повторіть пароль:<span class="resultConfirm not-corect"></span></label>
                        <input type="password" name="confirmPassword"  class="form-control  ${(password2Error??)?string('is-invalid','')}"
                               placeholder="Повторити пароль" />
                         <#if password2Error??>
                            <div class="invalid-feedback">
                                ${password2Error}
                            </div>
                         </#if>
                        <label for="info_user">Додаткова інформація:</label><br/>
                        <textarea name="info" id="info_user"  rows="2" class="form-control" >${user.info!''}</textarea>
                    </div>

                </div>
                <button type="submit" class="btn btn-primary mt-1" id="upProfile_btn" >Зберегти</button>
            </form>
        </div>
       </div>
    </div>


    <div class="collapse" id="collapsePhone">
        <div class="centr ">
            <div class="card card-body">
                <form action="/user/profile-phone" method="post">
                    <div class="row">

                        <div class="col-sm-6 col-md-6 col-lx-6 ">

                            <label for="profile_phone">Телефон :</label>
                            <input type="tel" name="phone" id="profile_phone" autofocus="autofocus" maxlength="50"
                                   value="+38(___)___-__-__" required="required"
                                   pattern="\+38\[\(][0-9]{3}[\)]\d{3}[-]\d{2}[-]\d{2}"
                                   placeholder="+38(___)___-__-__"/><br/>
                            <label class="mt-3">
                                Основний номер:
                                <input type="checkbox" name="active" checked="checked"/>
                            </label><br/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        </div>

                        <div class="col-sm-6 col-md-6 col-lx-6 ">
                            <label for="info_phone">Додаткова інформація:</label> <br/>
                            <textarea name="info" id="info_phone" cols="30" rows="3" class="input-with"></textarea>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Зберегти</button>
                </form>
            </div>
        </div>
    </div>


    <div class="collapse" id="collapsePostOffice">
        <div class="centr">
            <div class="card card-body">
                <form action="/user/profile-postOffice" method="post">
                    <div class="row mb-3">

                        <div class="col-sm-6 col-md-6 col-lx-6">
                            <label>Поштове віділеня :</label>
                            <input type="text" name="postOffice" class="form-control" placeholder="Пошта" />
                            <label class="mt-2">
                                Основне віділеня :
                                <input type="checkbox" name="active" checked="checked" />
                            </label><br/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />

                        </div>

                        <div class="col-sm-6 col-md-6 col-lx-6">
                            <label for="info_postOffice">Додаткова інформація:</label><br/>
                            <textarea name="info" id="info_postOffice" cols="30" rows="3" class="input-with"></textarea>

                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Зберегти</button>
                </form>
            </div>
        </div>
    </div>


    <div class="collapse" id="collapseAddress">
        <div class="centr">
            <div class="card card-body">
                <form action="/user/profile-address" method="post">
                    <div class="row">

                        <div class="col-sm-6 col-md-6 col-lx-6">
                            <label>Область :</label>
                            <input type="text" name="region" class="form-control" placeholder="Область" />
                             <label>Район :</label>
                            <input type="text" name="district" class="form-control" placeholder="Район" />
                            <label>Місто :</label>
                            <input type="text" name="city" class="form-control" placeholder="Місто" />
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <label class="mt-3">
                                Основна адреса:
                                <input type="checkbox" name="active" checked/>
                            </label>
                        </div>


                        <div class="col-sm-6 col-md-6 col-lx-6">

                            <label>Адреса :</label>
                            <input type="text" name="address" class="form-control" placeholder="Адреса" />
                            <label> Індекс:</label>
                            <input type="text" name="postCode" class="form-control" placeholder="Індекс міста" />
                            <label for="info_address" >Додаткова інформація:</label><br/>
                            <textarea name="info" id="info_address" cols="30" rows="3" class="input-with"></textarea>
                        </div>
                    </div>
                    <#---->
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">Зберегти</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


        <#if phones?size gt 0>
            <div class="centr mt-3" style="background: #d2f4e1"><h4>Телефон</h4>

            <table class="t-table mt-2">
                <COLGROUP>
                    <COL width="40%">
                    <COL width="40%">
                    <COL width="10%">
                    <COL width="10%">
                <thead>
                <tr>
                    <th>Телефон</th><th>Інфо</th>
                </tr>
                </thead>
                <tbody>
                    <#list phones as phone>
                    <tr>
                        <td>${phone.phone}</td>
                        <td>${phone.info!''}</td>
                        <td>
                            <a class="btn btn-success" onclick="updatePhone(#{phone.id})">Редагувати</a>
                        </td>
                        <td>
                            <a class="btn btn-danger"  onclick="deletePhone(#{phone.id})">Видалити </a>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            </div>
        </#if>


       <#if postOffices?size gt 0>
           <#--style="background: beige"-->
           <div class="centr mt-3" ><h4>Пошта</h4>
               <table class="t-table mt-2">
                   <COLGROUP>
                       <COL width="40%">
                       <COL width="40%">
                       <COL width="10%">
                       <COL width="10%">
                   <thead>
                   <tr>
                       <th>Пошта</th><th>Інфо</th>
                   </tr>
                   </thead>
                   <tbody>
            <#list postOffices as postOffice>
            <tr>
                <td>${postOffice.postOffice}</td>
                <td>${postOffice.info}</td>
                <td>

                    <a  class="btn btn-success" onclick="updatePostOffice(#{postOffice.id})">Редагувати</a>
                </td>
                <td>
                    <a class="btn btn-danger"  onclick="deletePostOffice(#{postOffice.id})">Видалити</a>
                </td>
            </tr>
            </#list>
                   </tbody>
               </table>
            </div>
        </#if>


    <#if addresess?size gt 0>
     <div class="centr mt-3" style="background: #d2f4e1"><h4>Адреса</h4>
        <table class="t-table mt-2">
            <COLGROUP>
                <COL width="15%">
                <COL width="15%">
                <COL width="15%">
                <COL width="25%">
                <COL width="10%">
                <COL width="10%">
                <COL width="5%">
                <COL width="5%">

            <thead>
            <tr>
                <th>Область</th><th>Район</th><th>Місто</th><th>Адреса</th><th>Індекс</th><th>Інфо</th>
            </tr>
            </thead>
            <tbody>
                <#list addresess as address>
                <tr>
                    <td>${address.region}</td>
                    <td>${address.district}</td>
                    <td>${address.city}</td>
                    <td>${address.address}</td>
                    <td>${address.postCode}</td>
                    <td>${address.info}</td>
                    <td><a class="btn btn-success"  onclick="updateAddress(#{address.id})">Редагувати</a></td>
                    <td><a class="btn btn-danger"  onclick="deleteAddress(#{address.id})">Видалити</a></td>

                </tr>
                </#list>
            </tbody>
        </table>
    </div>
    </#if>
</div>


<div class="container">
    <div id="updateAddressFromUser" class="modal">
        <div class="centr">
            <div class="modal-content">

                <div class="modal-header">
                     <span class="close" id="closeUpdateAddressFromUser" >&times;</span>
                     <h3 >Редагувати адресу </h3><br/>
                </div>

                <div class="modal-body centr">
                    <form action="/user/update-address" method="post">
                 <div class="row">
                     <div class="col-sm-6 col-md-6 col-xl-6 ">
                         <label>Область :<br/>
                         <input type="text" name="region" id="upAddress_region" class="form-control" placeholder="Область" />
                         </label><br/>
                         <label>Район :<br/>
                         <input type="text" name="district" id="upAddress_district" class="form-control" placeholder="Район" />
                         </label><br/>
                         <label>
                             Місто :<br/>
                             <input type="text" name="city" id="upAddress_city" class="form-control" />
                         </label ><br/>

                         <label class="mt-3">Активний
                             <input type="checkbox" name="active" id="upAddress_active" />
                         </label><br/>
                     </div>
                     <div class="col-sm-6 col-md-6 col-xl-6 ">
                         <label class="mt-2">Адреса :<br/>
                             <input type="text" name="address" id="upAddress_address" class="form-control"/>
                         </label><br/>
                         <label>Індекс :<br/>
                         <input type="text" name="postCode" id="upAddress_postCode" class="form-control"/>
                     </label><br/>
                         <label for="upAddress_info" class="mt-2">Додаткова інформація :</label><br/>
                         <textarea name="info" id="upAddress_info" cols="50" rows="3" class=" form-control"></textarea>
                         <input type="hidden" name="_csrf"  value="${_csrf.token}"/>
                         <input type="hidden" name="id" id="upAddress_id"/>
                         <#--onclick="saveAddress()"disabled="disabled"input-with-->
                     </div>
                 </div>
                    <button  type="submit" class="btn btn-primary">Зберегти</button>
                </form>
                </div>
            </div>

            <div class="modal-footer">
            <#--<h3 style="color: black">Футер модального окна</h3>-->
            </div>
        </div>
    </div>


    <div id="updatePhoneFromUser" class="modal">
        <div class="centr">
            <div class="modal-content">

             <div class="modal-header">
                 <span class="close" id="closeUpdatePfoneFromUser" >&times;</span>
                 <h3 >Редагувати до телефон </h3><br/>
             </div>
             <div class="modal-body centr">
                 <form action="/user/update-phone" method="post" >
                 <div class="row">
                     <div class="col-sm-6 col-md-6 col-xl-6">
                         <#--<label>Номер:<br/></label><br/>-->
                             <label for="upPhone_phone">Номер :</label><br/>
                             <input type="tel" name="phone" id="upPhone_phone"  maxlength="50"
                                    value=""
                                    <#--pattern="/\+38\[\(][0-9]{3}[\)]\d{3}[-]\d{2}[-]\d{2}/"-->
                                    <#--\+38\[\(][0-9]{3}[\)]\d{3}[-]\d{2}[-]\d{2}-->
                                    placeholder="+38(___)___-__-__"/><br/>
                             <#--<input type="text" name="phone" id="upPhone_phone" />-->

                         <label class="mt-3">Активний
                             <input type="checkbox" name="active" id="upPhone_active">
                         </label>
                     </div>
                     <div class="col-sm-6 col-md-6 col-xl-6">
                         <label for="upPhone_info">Додаткова інформація</label><br/>
                         <textarea name="info" id="upPhone_info" cols="30" rows="3" class="input-with"></textarea>
                         <input type="hidden" name="id" id="upPhone_id" />
                         <input type="hidden" name="_csrf"  value="${_csrf.token}"/>
                         <#--onclick="savePhone()"-->
                     </div>
                 </div>
                 <button  type="submit" class="btn btn-primary">Зберегти</button>
                 </form>    
                </div>
            </div>

            <div class="modal-footer">
         <#--<h3 style="color: black">Футер модального окна</h3>-->
            </div>
        </div>
    </div>


    <div id="updatePostOfficeFromUser" class="modal">
        <div class="centr">
            <div class="modal-content">

                 <div class="modal-header">
                     <span class="close" id="closeUpdatePostOfficeFromUser" >&times;</span>
                     <h3 >Редагувати пошту </h3><br/>
                 </div>

                 <div class="modal-body ">
                     <form action="/user/update-postOffice" method="post">
                     <div class="row">
                         <div class="col-sm-6 col-md-6 col-xl-6">
                             <label >
                                 Пошта:<br/>
                                 <input type="text" name="postOffice" id="upPostOffice_postOffice"/>
                             </label><br/>
                             <label class="mt-2">Активний
                                 <input type="checkbox" name="active" id="upPostOffice_active">
                             </label>
                         </div>
                         <div class="col-sm-6 col-md-6 col-xl-6">
                             <label for="upPostOffice_info">Додаткова інформація</label><br/>
                             <textarea name="info" id="upPostOffice_info" cols="30" rows="3" class="input-with">Додаткова інформація</textarea>
                             <input type="hidden" name="_csrf"  value="${_csrf.token}"/>
                             <input type="hidden" name="id" id="upPostOffice_id" />
                             <#--onclick="savePhone()"id="cart_csrf"-->
                         </div>
                     </div>
                     <button type="submit"  class="btn btn-primary">Зберегти</button>
                     </form>
                    </div>
                </div>

                <div class="modal-footer">
                <#--<h3 style="color: black">Футер модального окна</h3>-->
                </div>
        </div>
    </div>

</div>

 <script src="/static/js/profile.js"> </script>
<script src="/static/js/phone.js"></script>
<script>
    const a_collapseUser = document.querySelector("a[href='#collapseUser']");
    const a_collapsePhone = document.querySelector("a[href='#collapsePhone']");
    const a_collapsePostOffice = document.querySelector("a[href='#collapsePostOffice']");
    const a_collapseAddress = document.querySelector("a[href='#collapseAddress']");
    const collapseUser = document.querySelector("#collapseUser");
    const collapsePhone = document.querySelector("#collapsePhone");
    const collapsePostOffice = document.querySelector("#collapsePostOffice");
    const collapseAddress = document.querySelector("#collapseAddress");

    a_collapseUser.addEventListener('click',function(){
        a_collapsePhone.setAttribute("aria-expanded","false");
        collapsePhone.classList.remove('show');
        a_collapsePostOffice.setAttribute("aria-expanded","false");
        collapsePostOffice.classList.remove('show');
        a_collapseAddress.setAttribute("aria-expanded","false");
        collapseAddress.classList.remove('show');
    });
    a_collapsePhone.addEventListener('click',function(){
        a_collapseUser.setAttribute("aria-expanded","false");
        collapseUser.classList.remove('show');
        a_collapsePostOffice.setAttribute("aria-expanded","false");
        collapsePostOffice.classList.remove('show');
        a_collapseAddress.setAttribute("aria-expanded","false");
        collapseAddress.classList.remove('show');
    });
     a_collapsePostOffice.addEventListener("click",function(){
         a_collapseUser.setAttribute("aria-expanded","false");
         collapseUser.classList.remove('show');
       a_collapsePhone.setAttribute("aria-expanded","false");
        collapsePhone.classList.remove('show');
        a_collapseAddress.setAttribute("aria-expanded","false");
         collapseAddress.classList.remove('show');
    });
     a_collapseAddress.addEventListener("click",function(){
         a_collapseUser.setAttribute("aria-expanded","false");
         collapseUser.classList.remove('show');
         a_collapsePostOffice.setAttribute("aria-expanded","false");
         collapsePostOffice.classList.remove('show');
         a_collapsePhone.setAttribute("aria-expanded","false");
         collapsePhone.classList.remove('show');
    });

</script>

</@c.page>