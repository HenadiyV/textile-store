let dataPorduct=[];
let dataCart=[];
let dataLabels;
const  myDiv = document.getElementById('myDiv');

getData();


function primerChart(){
    let ctx = document.getElementById('myChart').getContext('2d');
    let chart = new Chart(ctx, {
        type: 'line',

        data: {
            labels: dataLabels, //['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль'],
            datasets: [
                { // График зелёного цвета
                    label: 'Закупка',
                    backgroundColor: 'transparent',
                    borderColor: 'green',
                    data: dataPorduct,//[0, 10, 5, 2, 20, 30, 45],
                },
                { // График синего цвета
                    label: 'Продаж',
                    backgroundColor: 'transparent',
                    borderColor: 'blue',
                    data: dataCart,//[5, 3, 10, 30, 40, 20, 5]
                }],
            // { // График красного цвета
            //     label: 'График 3',
            //     backgroundColor: 'transparent',
            //     borderColor: 'red',
            //     data: [20, 15, 10, 30, 25, 30, 35]
            // }],
        },
    });
}
setTimeout(primerChart,5000);


function getData(){
    newFetch("/rest/statistic-product",null,"Get","","product");
    newFetch("/rest/statistic-cart",null,"Get","","cart");
}

function showData(data,place){
    let dataProd='';
    let dataCar='';
    let lab= new Set();
    switch(place){
        case "product":{
            dataProd=data;
            //console.log(dataProd);
            for (let i = 0; i < dataProd.length; i ++) {

                let dt=dataProd[i].dat.substring(0,10);

                dt=dt.split("-").reverse().join("-");
                //lab.add(dt);
                dataPorduct.push(dataProd[i].bought);
                if(dataProd[i].purchase>0){


                }
            }
            break;}
        case "cart":{
            dataCar=data;
          // console.log(dataCar);
            for (let i = 0; i < dataCar.length; i ++) {

                let dt=dataCar[i].dat.substring(0,10);

                dt=dt.split("-").reverse().join("-");
                lab.add(dt);
                dataCart.push(dataCar[i].bought);
                if(dataCar[i].size_selling>0){


                }
            }
            break;}
    }
    //console.log(lab);
    dataLabels=Array.from(lab);
    //console.log(dataLabels);
    //console.log(dataCart);
    //console.log(dataPorduct);
}



function newFetch(url,body,method,csrf,place){

    let status = function (response) {
        if (response.status !== 200) {
            return Promise.reject(new Error(response.statusText))
        }
        return Promise.resolve(response)
    };
    let json = function (response) {
        return response.json()
    };

    fetch(url, {
        method: method,
        body: body,
        headers: {
            'X-CSRF-TOKEN': csrf,
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
        .then(status)
        .then(json)
        .then( data=>{showData(data,place);})
        .catch(function (error) {
            console.log('error', error)
        })
}


function graficScatter(){
    /*
    dat: "2020-11-28T22:00:00.000+00:00"
    purchese: 100
    selling: 120
    size_product: 20.2
    size_selling: 19.97
    summ_purchese: 2020
    summ_selling: 2396.3999999999996*/
    console.log(dataPorduct);
    console.log(dataCart);
    console.log("grafic");
    if(dataPorduct!==''&&dataPorduct!==''){
        let y0 = [];
        let y1 = [];
        let y2 = [];
        let x1 = [];
        let x2 = [];
        let x3 = [];

        for (let i = 0; i < dataPorduct.length; i ++) {

            let dt=dataPorduct[i].dat.substring(0,10);

            dt=dt.split("-").reverse().join("-");

            if(dataPorduct[i].bought>0){

                y1[i] =dt;
                x1[i] =dataPorduct[i].bought;
            }
        }

        for (let i = 0; i < dataCart.length; i ++) {

            let dt=dataCart[i].dat.substring(0,10);

            dt=dt.split("-").reverse().join("-");

            if(dataCart[i].sold>0){

                y2[i] =dt; //Math.random();
                x2[i] =dataCart[i].sold;
            }
        }

        let  trace1 = {
            x: x1,
            y: y1,
            mode: 'markers',
            name: 'куплено',
            text: ['United States', 'Canada'],
            marker: {
                color: 'rgb(164, 194, 244)',
                size: 12,
                line: {
                    color: 'white',
                    width: 0.5
                }
            },
            type: 'scatter'
        };

        let  trace2 = {
            x: x2,
            y: y2,
            mode: 'markers',
            name: 'Продано',
            text: ['Germany', 'Britain', 'France', 'Spain', 'Italy', 'Czech Rep.', 'Greece', 'Poland'],
            marker: {
                color: 'rgb(255, 217, 102)',
                size: 12
            },
            type: 'scatter'
        };

        // let trace3 = {
        //     x: [42952, 37037, 33106, 17478, 9813, 5253, 4692, 3899],
        //     y: [23, 42, 54, 89, 14, 99, 93, 70],
        //     mode: 'markers',
        //     name: 'Asia/Pacific',
        //     text: ['Australia', 'Japan', 'South Korea', 'Malaysia', 'China', 'Indonesia', 'Philippines', 'India'],
        //     marker: {
        //         color: 'rgb(234, 153, 153)',
        //         size: 12
        //     },
        //     type: 'scatter'
        // };
        //
        // let trace4 = {
        //     x: [19097, 18601, 15595, 13546, 12026, 7434, 5419],
        //     y: [43, 47, 56, 80, 86, 93, 80],
        //     mode: 'markers',
        //     name: 'Latin America',
        //     text: ['Chile', 'Argentina', 'Mexico', 'Venezuela', 'Venezuela', 'El Salvador', 'Bolivia'],
        //     marker: {
        //         color: 'rgb(142, 124, 195)',
        //         size: 12
        //     },
        //     type: 'scatter'
        // };

        let dataP = [trace1, trace2];//, trace3, trace4

        let layout = {
            title: 'Закупка та продаж',
            xaxis: {
                title: '',
                showgrid: false,
                zeroline: false
            },
            yaxis: {
                title: '',
                showline: true
            }
        };

        Plotly.newPlot('myDiv', dataP, layout);
    }
}

function graficLine(){
    /*
    dat: "2020-11-28T22:00:00.000+00:00"
    purchese: 100
    selling: 120
    size_product: 20.2
    size_selling: 19.97
    summ_purchese: 2020
    summ_selling: 2396.3999999999996*/
    console.log(data);
    console.log("grafic");
    if(data!==''){
        let y0 = [];
        let y1 = [];
        let y2 = [];
        let x1 = [];
        let x2 = [];
        let x3 = [];
        for (let i = 0; i < data.length; i ++) {
            let dt=data[i].dat.substring(0,10);
            dt=dt.split("-").reverse().join("-");
            console.log("dt "+dt);
            y0[i] =dt;
            if(data[i].summ_selling>0){
                y1[i] =dt; //Math.random();
                x1[i] =data[i].summ_selling;
            }
            if(data[i].summ_purchese>0){
                y2[i] =dt;
                x2[i] =data[i].summ_purchese;
            }
            //Math.random();
            //Math.random();
        }

        let trace1 = {
            x: x1,
            y: y1,
            mode: 'lines',
            name: 'Solid',
            line: {
                dash: 'solid',
                width: 4
            }
        };

        let trace2 = {
            x: x2,
            y: y2,
            mode: 'lines',
            name: 'dashdotSelling',
            line: {
                dash: 'solid',//dash: 'dashdot',
                width: 4
            }
        };

        // let trace3 = {
        //     x: [0],
        //     y: y0,
        //     mode: 'lines',
        //     name: 'Solid',
        //     line: {
        //         dash: 'solid',
        //         width: 4
        //     }
        // };
        //
        // let trace4 = {
        //     x: [1, 2, 3, 4, 5],
        //     y: [16, 18, 17, 18, 16],
        //     mode: 'lines',
        //     name: 'dot',
        //     line: {
        //         dash: 'dot',
        //         width: 4
        //     }
        // };

        let dataP = [trace1, trace2];//, trace3, trace4

        let layout = {
            title: 'Line Dash',
            xaxis: {
                range: [0.75, 5.25],
                autorange: false
            },
            yaxis: {
                range: [0, 18.5],
                autorange: false
            },
            legend: {
                y: 0.5,
                traceorder: 'reversed',
                font: {
                    size: 16
                }
            }
        };

        Plotly.newPlot('myDiv', dataP, layout);
    }
}

function graficHistogram(){
    /*
    dat: "2020-11-28T22:00:00.000+00:00"
    purchese: 100
    selling: 120
    size_product: 20.2
    size_selling: 19.97
    summ_purchese: 2020
    summ_selling: 2396.3999999999996*/
    console.log(data);
    console.log("grafic");
    if(data!==''){
        let x1 = [];
        let x2 = [];
        let x3 = [];
        for (let i = 0; i < data.length; i ++) {
            let dt=data[i].dat.substring(0,10);
            dt=dt.split("-").reverse().join("-");
            console.log("dt "+dt);
            x1[i] =dt; //Math.random();
            x2[i] =data[i].summ_selling; //Math.random();
            x3[i] =data[i].summ_purchese; //Math.random();
        }

        let trace1 = {
            x: x1,
            type: "histogram",
            opacity: 0.5,
            marker: {
                color: 'green',
            },
        };
        let trace2 = {
            x: x2,
            type: "histogram",
            opacity: 0.6,
            marker: {
                color: 'red',
                name: 'summ_purchese'
            },
        };
        let trace3 = {
            x: x3,
            type: "histogram",
            opacity: 0.7,
            marker: {
                color: 'blue',
            },
        };


        let dataP = [trace1, trace2,trace3];
        let layout = {barmode: "stack"};
        Plotly.newPlot(myDiv, dataP, layout);
    }
}


// TESTER = document.getElementById('tester');
// Plotly.newPlot( TESTER, [{
//     x: [1, 2, 3, 4, 5],
//     y: [1, 2, 4, 8, 16] }], {
//     margin: { t: 0 } } );
function primerChart1(){
    let ctx = document.getElementById('myChart').getContext('2d');
    let chart = new Chart(ctx, {
        // Тип графика
        type: 'line',

        // Создание графиков
        data: {
            // Точки графиков
            labels: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль'],
            // График
            datasets: [{
                label: 'Мой первый график на Chart.js', // Название
                backgroundColor: 'rgb(255, 99, 132)', // Цвет закраски
                borderColor: 'rgb(255, 99, 132)', // Цвет линии
                data: [0, 10, 5, 2, 20, 30, 45] // Данные каждой точки графика
            }]
        },

        // Настройки графиков
        options: {}
    });
}


function primerCanvas(){
    // Получаем canvas элемент
    let canvas = document.getElementById('canvas');

    // Указываем элемент для 2D рисования
    let ctx = canvas.getContext('2d');

    ctx.fillStyle = "black"; // Задаём чёрный цвет для линий
    ctx.lineWidth = 2.0; // Ширина линии
    ctx.beginPath(); // Запускает путь
    ctx.moveTo(30, 10); // Указываем начальный путь
    ctx.lineTo(30, 460); // Перемешаем указатель
    ctx.lineTo(500, 460); // Ещё раз перемешаем указатель
    ctx.stroke(); // Делаем контур
    // Цвет для рисования
    ctx.fillStyle = "black";
    // Цикл для отображения значений по Y
    for(let i = 0; i < 6; i++) {
        ctx.fillText((5 - i) * 20 + "", 4, i * 80 + 60);
        ctx.beginPath();
        ctx.moveTo(25, i * 80 + 60);
        ctx.lineTo(30, i * 80 + 60);
        ctx.stroke();
    }

    // Массив с меткам месяцев
    let labels = ["JAN", "FEB", "MAR", "APR", "MAY"];

    // Выводим меток
    for(let i=0; i<5; i++) {
        ctx.fillText(labels[i], 50+ i*100, 475);
    }
    // Объявляем массив данных графика
    let data = [ 10, 53, 39, 54, 21 ];

    // Назначаем зелёный цвет для графика
    ctx.fillStyle = "green";
    // Цикл для от рисовки графиков
    for(let i=0; i<data.length; i++) {
        let dp = data[i];
        ctx.fillRect(40 + i*100, 460-dp*5 , 50, dp*5);
    }
}

