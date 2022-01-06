<#include "security.ftlh">
<div class="container">
    <div class="row">
        <div class="col-4"></div>
        <div class="col-4">
            <span>Кількість продуктів: ${col_product}</span><br/>
            <span>Кількість користувачів: ${col_user}</span><br/>
            <span>Кількість метри: ${sumPurchace.size}</span><br/>
            <span>Закуплено на: ${sumPurchace.purchace}</span><br/>
            <span>По продажній: ${sumPurchace.selling}</span><br/>
            <span>Продано: ${sumPurchace.selling_size}</span>
            <span> метрів на сумму: ${sumPurchace.sum_selling}</span><span> (закупочна : ${sumPurchace.sum_purchace})</span><br/>
            <span>Прибуток: ${sumPurchace.profit}</span><br/>
            <span>Остаток по закупочній ціні: ${sumPurchace.sum_remainder_purchace}</span><br/>
            <span>Остаток метри: ${sumPurchace.remainder}</span><br/>
        </div>
        <div class="col-4"></div>
    </div>

</div>