<#import "common.ftlh" as c>

<@c.page>
<div class="container">

    <a href="${url}">Повернутись</a>
    <div class="centr">
    <h4>Редагування замовлення</h4>
    </div>
    <div class="row">
        <div class="col-6">
            <label for="userL">Пошук користувачів</label><br/>
            <input type="text" id="search_user" placeholder="Search user" />
            <select id="userL">
            <option>Список користувачів</option>
              <#list users as user>
                 <option value="${user.id}"><#if user.name?has_content>${user.name}</#if> | ${user.username}</option>
              </#list>
        </select>
        </div>

        <div class="col-6 mt-4">
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-sm-4 mt-2">
             <label for="phon">Телефон</label><br/>
            <select id="phon" class="ordUpdForm">

                            <#if userN??>
                                <#list userN.phones as phone>
                                <option value="${phone.id}">${phone.phone}</option>
                                </#list>
                            <#else>
                            <option value="${order.phone_id}"> ${order.phone}</option>
                            </#if>
            </select>
        </div>

        <div class="col-sm-4 mt-2">
            <span >Нік: <#if userN??>  <b>${userN.name}</b><#else><b>${order.name}</b></#if></span> <br/>
           <span >Імя: <#if userN??> <b>${userN.username}</b><#else><b>${order.username}</b></#if></span>
        </div>

        <div class="col-sm-4 mt-2">
            <label for="addr">Адресса</label><br/>
            <select id="addr">
                <#if userN??>
                   <#list userN.addresses as address>
                      <option value="${address.id}">${address.city} |
                                 ${address.address} |
                                 ${address.postCode}</option>
                   </#list>
                <#else>
                    <option value="${order.address_id}">
                    ${order.city} |
                    ${order.address} |
                    ${order.postCode}</option>
                 </#if>
            </select>
        </div>
</div>

    <div class="row mt-3">

        <div class="col-sm-4">
            <div class="mt-2">
                <label for="dat_dispatch">Дата відправки</label><br/>
                <input type="text" id="dat_dispatch"  class="input-with"/>
                <input type="hidden"  id="dat_dispatch1" class="input-with" value="${order.dat_dispatch?date?string('yyyy/MM/dd')}" />
            </div>

            <div class="mt-3">
                <label for="postOffice">Почтове відділення</label><br/>
                <select id="postOffice" class="ordUpdForm">
                        <#if userN??>
                            <#list userN.postOfficeUsers as postOffice>
                            <option value="${postOffice.id}">${postOffice.postOffice}</option>
                            </#list>
                        <#else>
                        <option value="${order.postOffice_id}"> ${order.postOffice}</option>
                        </#if>
                </select>
            </div>
    </div>

        <div class="col-sm-4">

            <div class="mt-2">
                <label for="delivery">ТТН</label><br/>
                <input type="text" id="delivery" value="${order.delivery}" class="input-with" />
                <input type="hidden" id="ord_id" value="${order.id}" class="input-with" />
            </div>

            <div class="mt-3">
                <label for="status">Статус відправлення</label><br/>
                <select id="status" class="ordUpdForm">
                    <#list status1 as stat>
                        <#if stat.status = order.status>
                        <option value="${stat.status}" selected>${stat.status}</option>
                        <#else>
                        <option value="${stat.status}" >${stat.status}</option>
                        </#if>
                    </#list>
                </select><br/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="mt-1">
            <label for="infoOr">Додаткова інформація</label><br/>
            <textarea id="infoOr" class="input-with" rows="4" cols="45"><#if order.info_order?has_content>${order.info_order}</#if></textarea>
            </div>
        </div>
    </div>

    <div class="mt-3 centr" >
        <span>Сумма: ${order.summ}</span><br/><br/>
        <form   method="post" >
            <input type="hidden"   name="id"  value="${order.id}">
            <input type="hidden" name="user_id"  value="<#if userN??>${userN.id}<#else>${order.user_id}</#if>"readonly />
            <input type="hidden"   name="phone_id"   value="<#if user??><#else>${order.phone_id}</#if>"readonly />
            <input type="hidden"   name="address_id"   value="<#if user??><#else>${order.address_id}</#if>"readonly />
            <input type="hidden"   name="post_office_id"   value="<#if user??><#else>${order.postOffice_id}</#if>"readonly />
            <input type="hidden"   name="summ"   value="#{order.summ}" readonly />
            <input type="hidden"  name="dat_dispatch"   value="" />
            <input type="hidden"  name="status"   value="" />
            <input type="hidden"  name="delivery"   value="" />
            <input type="hidden"   name="info_order"   value="" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" id="btn_form" class="btn btn-primary">Зберегти</button>
        </form>
    </div>

<script>
    document.querySelector("#search_user").addEventListener("input",searchUser);

    document.querySelector("body").addEventListener("click",listUser);

    let userL= document.getElementById("userL");

    function searchUser (){

        let us=this.value;

        if(us.length>0){

            let request = new XMLHttpRequest();

            request.open("Get", "/rest/us-search/"+us, false);

            request.send();

            let status=request.status;

            if (status ===200){

                userL.innerHTML='';

                let data = JSON.parse(request.response);

                let opt='<option>Виберіть користувача</option>';

                for(let i=0;i<data.length;i++){

                    pt+='<option value='+data[i].id+'>'+data[i].name+' | '+data[i].username+'</option>'
                }
                userL.innerHTML=opt;
            }
        }else{

            listUser();
        }
    }


    function listUser(){

        let request = new XMLHttpRequest();

        request.open("Get", "/rest/us-list/");

        request.send();

        let status=request.status;

        if (status ===200){

            userL.innerHTML='';

            let data = JSON.parse(request.response);

            let opt="<option>Список користувачів</option>";

            for(let i=0;i<data.length;i++){

                opt+="<option  value="+data[i].id+">"+data[i].name+" | "+data[i].username+"</option>"
            }
            userL.innerHTML=opt;
        }
    }


    if(userL){

        userL.addEventListener("change", function (){

            let  selectedText =+this.options[this.selectedIndex].value;

            let ord=+document.querySelector("#ord_id").value;

            document.location.href = "/order/update-order-user/"+ord+"/"+selectedText;
        }
        );
    }


    function initOrder(){

        let phon=  document.querySelector("#phon");

        let addr=  document.querySelector("#addr");

        let postOffice=  document.querySelector("#postOffice");

        let status=  document.querySelector("#status");

        let  selectedphon = phon.options[phon.selectedIndex].value;

        let  selectedaddr = addr.options[addr.selectedIndex].value;

        let  selectedpostOffice = postOffice.options[postOffice.selectedIndex].value;

        let  selectedStatus = status.options[status.selectedIndex].value;

        document.querySelector("input[name='phone_id']").value=selectedphon;

        document.querySelector("input[name='address_id']").value=selectedaddr;

        document.querySelector("input[name='post_office_id']").value=selectedpostOffice;

        document.querySelector("input[name='dat_dispatch']").value=document.querySelector("#dat_dispatch").value;

        document.querySelector("input[name='status']").value=selectedStatus;

        document.querySelector("input[name='delivery']").value=document.querySelector("#delivery").value;

        document.querySelector("input[name='info_order']").value=document.querySelector("#infoOr").value;
    }

    btn_form.addEventListener('mousemove',initOrder);
</script>

</div>
</@c.page>