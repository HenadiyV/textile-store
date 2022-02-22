<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>

<div class="centr"><h3>Реєстрация</h3></div>
<#--${message?ifExists}-->
<@l.login "/registration" true/>

</@c.page>