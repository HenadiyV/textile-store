<#import "parts/common.ftlh" as c>
<#import "parts/orderList.ftlh" as p>

<@c.page>
   <div class="container">
    <@p.orders orderList ></@p.orders>
   </div>
    <#--<#include "parts/orderList.ftlh"/>-->

</@c.page>