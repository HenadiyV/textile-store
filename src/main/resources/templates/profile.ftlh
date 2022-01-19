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
<h5>${user.username}</h5>

    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Редагувати профіль
    </a><br/>
    <div class="collapse" id="collapseExample">
        <div class="centr">
        <div class="card card-body">
            <form action="/user/profile-user" method="post">
                <div class="row mb-3">

                    <div class="col-sm-6 col-md-6 col-lx-6">
                        <label> Нік:</label>
                        <input type="text" name="username" class="form-control" value="${user.username}" placeholder="Нік" />
                    </div>

                    <div class="col-sm-6 col-md-6 col-lx-6">
                        <label > Імя:</label>
                        <input type="text" name="name" class="form-control" value="${user.name}" placeholder="Імя" />
                    </div>

                    <div class="col-sm-6 col-md-6 col-lx-6">
                        <label > Ваш пароль:</label>
                        <input type="password" name="password" class="form-control" placeholder="Пароль" />
                    </div>

                    <div class="col-sm-6 col-md-6 col-lx-6">
                        <label > Повторіть пароль:<span class="resultConfirm not-corect"></span></label>
                        <input type="password" name="confirmPassword"  class="form-control" placeholder="Повторити пароль" />

                    </div>

                    <div class="col-sm-6 col-md-6 col-lx-6">
                        <label > Ваша електрона адресса:</label>
                        <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${user.email!''}" />
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />

                    </div>

                    <div class="col-sm-6 col-md-6 col-lx-6">
                        <label for="info_user">Додаткова інформація:</label><br/>
                        <textarea name="info" id="info_user" cols="30" rows="3" class="input-with" >${user.info!'Додаткова інформація'}</textarea>
                    </div>
                </div>
                <#--disabled="disabled"-->
                <button type="submit" class="btn btn-primary mt-1" id="upProfile_btn" >Зберегти</button>
            </form>
        </div>
       </div>
    </div>

   <div class="row">

       <div class="col-sm-6 col-md-6 col-xl-6">

               <a class="btn btn-primary  mt-3" data-bs-toggle="collapse" href="#collapseExamplePhone" role="button" aria-expanded="false" aria-controls="collapseExample">
               Додати Phone
           </a>


           <#if phones??>
            <table class="t-table mt-2">
                <thead>
                <tr>
                    <th>phone</th><th>info</th><th>id</th>
                </tr>
                </thead>
                <tbody>
                <#list phones as phone>
                <tr>
                    <td>${phone.phone}</td>
                    <td>${phone.info}</td>
                    <td>
                        <a class="btn btn-success" onclick="updatePhone(${phone.id})">Редагувати</a>
                    </td>
                    <td>
                        <a class="btn btn-danger"  onclick="deletePhone(#{phone.id})">Видалити </a>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
       </#if>

       </div>
       <div class="col-sm-6 col-md-6 col-xl-6">

           <a class="btn btn-primary  mt-3" data-bs-toggle="collapse" href="#collapseExamplePostOffice" role="button" aria-expanded="false" aria-controls="collapseExample">
           Додати postOffice
           </a>

           <#if postOffices??>
               <table class="t-table mt-2">
                   <thead>
                   <tr>
                       <th>postOffice</th><th>info</th><th>id</th>
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
           </#if>
       </div>

   </div>


    <div class="collapse" id="collapseExamplePhone">
        <div class="centr ">
        <div class="card card-body">
            <form action="/user/profile-phone" method="post">
                <div class="row mb-5">

                    <div class="col-sm-6 col-md-6 col-lx-6 ">
                        <label >Телефон :</label>
                        <input type="text" name="city" class="form-control" placeholder="Телефон" />
                        <label class="mt-2">
                            Основний номер:
                            <input type="checkbox" name="active" checked="checked"/>
                        </label><br/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />


                    </div>

                    <div class="col-sm-6 col-md-6 col-lx-6 ">
                        <label for="info_phone">Додаткова інформація:</label> <br/>
                        <textarea name="info" id="info_phone" cols="30" rows="3" class="input-with">Додаткова інформація</textarea>
                    </div>

                </div>
                <button type="submit" class="btn btn-primary mt-2">Зберегти</button>
            </form>
        </div>
        </div>
    </div>




    <div class="collapse" id="collapseExamplePostOffice">
        <div class="card card-body">
            <form action="/user/profile-postOffice" method="post">
                <div class="row mb-3">

                    <div class="col-sm-6 col-md-6 col-lx-6 centr">
                        <label>Поштове віділеня :</label>
                        <input type="text" name="city" class="form-control" placeholder="Пошта" />
                        <label class="mt-2">
                            Основне віділеня :
                            <input type="checkbox" name="active" checked="checked" />
                        </label><br/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />

                    </div>

                    <div class="col-sm-6 col-md-6 col-lx-6 centr">
                        <label for="info_postOffice">Додаткова інформація:</label><br/>
                        <textarea name="info" id="info_postOffice" cols="30" rows="3" class="input-with">Додаткова інформація</textarea>

                    </div>
                </div>

                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mt-2">Зберегти</button>
                </div>
            </form>
        </div>
    </div>


        <a class="btn btn-primary mt-3" data-bs-toggle="collapse" href="#collapseExampleAddress" role="button" aria-expanded="false" aria-controls="collapseExample">
            Додати Address
        </a>

        <div class="collapse" id="collapseExampleAddress">
            <div class="card card-body">
                <form action="/user/profile-address" method="post">
                    <div class="row mb-3">

                        <div class="col-sm-6 col-md-6 col-lx-6 centr">
                            <label>Місто :</label>
                            <input type="text" name="city" class="form-control" placeholder="Місто" />
                        </div>

                        <div class="col-sm-6 col-md-6 col-lx-6 centr">
                            <label>Адреса :</label>
                            <input type="text" name="address" class="form-control" placeholder="Адреса" />
                        </div>

                        <div class="col-sm-6 col-md-6 col-lx-6 centr">
                            <label> Індекс:</label>
                            <input type="text" name="postCode" class="form-control" placeholder="Індекс міста" />
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />

                        </div>

                        <div class="col-sm-6 col-md-6 col-lx-6 centr">
                            <label class="mt-2">
                                Основна адреса:
                                <input type="checkbox" name="active" checked/>
                            </label><br/>
                            <label for="info_address" class="mt-2">Додаткова інформація:</label><br/>
                            <textarea name="info" id="info_address" cols="30" rows="3" class="input-with">Додаткова інформація</textarea>
                        </div>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mt-2">Зберегти</button>
                    </div>
                </form>
            </div>
        </div>

    <#if addresess??>
        <table class="t-table mt-2" >
            <thead>
            <tr>
                <th>City</th><th>Address</th><th>PostCode</th><th></th><th></th>
            </tr>
            </thead>
            <tbody>
                <#list addresess as address>
                <tr>
                    <td>${address.city}</td>
                    <td>${address.address}</td>
                    <td>${address.postCode}</td>
                    <td>
                        <a class="btn btn-success"  onclick="updateAddress(#{address.id})">Редагувати</a>
                    </td>
                    <td>
                        <a class="btn btn-danger"  onclick="deleteAddress(#{address.id})">Видалити</a>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>

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
                         <label>
                             Місто<br/>
                             <input type="text" name="city" id="upAddress_city" />
                         </label ><br/>
                         <label class="mt-2">Адреса<br/>
                             <input type="text" name="address" id="upAddress_address"/>
                         </label><br/>
                         <label class="mt-3">Активний
                             <input type="checkbox" name="active" id="upAddress_active"/>
                         </label><br/>
                     </div>
                     <div class="col-sm-6 col-md-6 col-xl-6 ">
                         <label>Індекс<br/>
                         <input type="text" name="postCode" id="upAddress_postCode"/>
                     </label><br/>
                         <label for="upAddress_info" class="mt-2">Додаткова інформація</label><br/>
                         <textarea name="info" id="upAddress_info" cols="50" rows="3" class="input-with">Додаткова інформація</textarea>
                         <input type="hidden" name="_csrf"  value="${_csrf.token}"/>
                         <input type="hidden" name="id" id="upAddress_id"/>
                         <#--onclick="saveAddress()"disabled="disabled"-->
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
                         <label>
                             Номер:<br/>
                             <input type="text" name="phone" id="upPhone_phone" />
                         </label><br/>
                         <label class="mt-3">Активний
                             <input type="checkbox" name="active" id="upPhone_active">
                         </label>
                     </div>
                     <div class="col-sm-6 col-md-6 col-xl-6">
                         <label for="upPhone_info">Додаткова інформація</label><br/>
                         <textarea name="info" id="upPhone_info" cols="30" rows="3" class="input-with">Додаткова інформація</textarea>
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


</@c.page>