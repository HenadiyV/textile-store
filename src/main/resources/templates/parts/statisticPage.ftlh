<#include "security.ftlh">
 <link rel="stylesheet" href="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
    <script src="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>

<link rel="stylesheet" href="/static/js/chart/Chart.min.css">
<script src="/static/js/chart/Chart.min.js"></script>

<div class="container">
    <div class="row">
        <div class="col-4"></div>
        <div class="col-4">
            <span>Кількість продуктів: ${col_product}</span><br/>
            <span>Кількість користувачів: ${col_user}</span><br/>
            <span>Закупленно на суму: ${sellingSumm}</span><br/>
            <span>Продано на суму: ${purchaseSumm}</span><br/>
        </div>
        <div class="col-4"></div>
    </div>

</div>
<canvas id="myChart"></canvas>


<div id="myDiv" ></div>

<div style="width:200px;height: 200px">
<div class="ct-chart ct-perfect-fourth"></div>
</div>
<script src="/static/js/myCharts.js"></script>
