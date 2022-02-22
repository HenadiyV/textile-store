const area = document.getElementById("area");
const adr = document.getElementById("adr");
const regionT = document.getElementById("region");
const districtT = document.getElementById("district");
const cityT = document.getElementById("city");
/*
region
district
city
*/
window.onload = () => {
    if (area) {
        area.addEventListener("input", function () {
            let data = {
                "apiKey": "a2ba8dc38439a72ba6e975230f9b9086",
                "modelName": "Address",
                "calledMethod": "searchSettlements",
                "methodProperties": {
                    "CityName": this.value,
                    "Limit": 5
                }
            };
            let dataA = `{\r\n\"apiKey\": \"\",\r\n \"modelName\": \"Address\",\r\n \"calledMethod\": \"searchSettlements\",\r\n \"methodProperties\": {\r\n \"CityName\": \"${this.value}\",\r\n \"Limit\": 15\r\n }\r\n}`;
            let url = "https://api.novaposhta.ua/v2.0/json/";
            myFetchFunctionAddress(url, dataA, "POST")
        });
    }
};

function myFetchFunctionAddress(url, data, method) {
    console.log("url= " + url);
    try {
        const response = fetch(url, {
            method: method,
            body: data,
            headers: {
                "content-type": "application/json",
                // 'X-CSRF-TOKEN': csrf,
                // 'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
        response.then(data => {

            return data.text()

        })
            .then(data => {
                viewDataReturnFetchAddress(data);
            });
    } catch (error) {
        console.error('Ошибка:', error);
    }
}

function viewDataReturnFetchAddress(data) {
    //console.log(data.Addresses);
    adr.innerHTML = '';
    let dataOb = JSON.parse(data);
    console.log(dataOb);
    let one = dataOb.data;

    console.log(one[0]);
    let two = one[0];
    //киев

    //console.log(two.Addresses);
    let op = "<option>Список адрес</option>";
    for (address of two.Addresses) {
        op += "<option>" + address.Present + "</option>";
        //console.log(address.Area);
       // console.log(address.Present.split(','));
        //     // for (adr of address.Present) {
        //     //     console.log(adr);
        //     // }
    }
    adr.innerHTML = op;
}
if (adr) {
    adr.addEventListener('change', function () {
        let selectedText = this.options[this.selectedIndex].value.split(',');
        if(regionT){regionT.value=selectedText[1];}
        if(districtT){districtT.value=selectedText[2];}
        if(cityT){cityT.value=selectedText[0];}
        //district console.log(selectedText[0]);
        // console.log(selectedText[1]);
        // console.log(selectedText[2]);
        // areaT.innerText = selectedText[2];
        // regionT.innerText = selectedText[1];
        // cityT.innerText = selectedText[0];
    });
}