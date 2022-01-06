<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">

        <a class="navbar-brand" href="/">Текстиль</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <ul class="navbar-nav me-auto mb-2 mb-lg-0 mr-auto">

            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link " aria-current="page" href="/admin/user">Список корістувачів</a>
                </li>
                <li class="nav-item">
                        <a href="/category" class="nav-link " aria-current="page">Категоріі</a>
                </li>
                 <li class="nav-item">
                     <a href="/product" class="nav-link " aria-current="page">Товар</a>
                </li>
                 <li class="nav-item">
                     <a href="/order/view-order" class="nav-link " aria-current="page">Замовлення</a>
                </li>

                 <li class="nav-item">

                <a href="/admin" class="nav-link " aria-current="page">
                    Admin
                </a>
                 </li>
            </#if>

            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Профіль </a>
                </li>
             </#if>

            </ul>

            <div class="havbar-text me-3">
                <#if cookie??>
                     <a class="btn btn-primary me-1" onclick="showBasket()" href="javascript:void(0)"name="show-basket">Кошик</a>
           <#--<#else><button onclick="writeCookies()" id="write_token">writeToken</button>-->
                </#if>
               <#if user??>
                       ${name}
                   <#else>
                       <a class="btn btn-primary me-1" href="/login" >Вхід</a>
                                <a class="btn btn-primary me-1" href="/registration" >Регістрація</a>
               </#if>
            </div>
       </div>
        <#if user??><@l.logout /></#if>
    </nav>