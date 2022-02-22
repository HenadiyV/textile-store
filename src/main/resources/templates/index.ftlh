<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#--<#import "parts/pager.ftlh" as p>-->
<@c.page>
    <#include "parts/productList.ftlh"/>
<script>
    let code = navigator.appCodeName;
    let name = navigator.appName;
    let vers = navigator.appVersion;
    let platform = navigator.platform;
    let cook = navigator.cookieEnabled;
    let je = navigator.javaEnabled();
    let ua = navigator.userAgent;
    document.write('Ваш браузер: ' + name +
            '<br />Версия браузера: ' + vers +
            '<br />Кодовое название браузера: ' + code +
            '<br />Платформа: ' + platform +
            '<br />Поддержка cookie: ' + cook +
            '<br />Поддержка JavaScript: ' + je +
            '<br />userAgent: ' + ua);
</script>
<h2>Только для Internet Explorer</h2>
<script type = "text/javascript">
    document.write(navigator.appMinorVersion +
            '<br />' + navigator.onLine +
            '<br />' + navigator.systemLanguage);
</script>
<h2>Только для Firefox</h2>
<script type = "text/javascript">
    document.write(navigator.language +
            '<br />' + navigator.oscpu +
            '<br />' + navigator.product +
            '<br />' + navigator.productSub +
            '<br />' + navigator.vendor +
            '<br />' + navigator.vendorSub);
</script>

</@c.page>
