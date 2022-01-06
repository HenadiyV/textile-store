<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div class="mt-5">
<div class="md-1">Реєстрация</div>
${message?ifExists}
<@l.login "/registration" true/>
</div>
</@c.page>