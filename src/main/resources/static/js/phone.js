const o_phone = document.querySelector("#o_phone");
const profile_phone = document.querySelector("#profile_phone");
const profile_upPhone_phone = document.querySelector("#upPhone_phone");
const phoneList=document.querySelectorAll("input[name='phone']");
const add_phone=document.getElementById("add_phone");

function setCursorPosition(pos, e) {
    e.focus();
    if (e.setSelectionRange) e.setSelectionRange(pos, pos);
    else if (e.createTextRange) {
        let range = e.createTextRange();
        range.collapse(true);
        range.moveEnd("character", pos);
        range.moveStart("character", pos);
        range.select()
    }
}
function mask(e) {
    //console.log('mask',e);
    let matrix = this.placeholder,// .defaultValue
        i = 0,
        def = matrix.replace(/\D/g, ""),
        val = this.value.replace(/\D/g, "");
    def.length >= val.length && (val = def);
    matrix = matrix.replace(/[_\d]/g, function(a) {
        return val.charAt(i++) || "_"
    });
    this.value = matrix;
    i = matrix.lastIndexOf(val.substr(-1));
    i < matrix.length && matrix != this.placeholder ? i++ : i = matrix.indexOf("_");
    setCursorPosition(i, this)
}
window.addEventListener("DOMContentLoaded", function() {
    if(o_phone){
        phoneMask(o_phone);
    }
    if(add_phone){
       // autofocus=
        //add_phone.setAttribute('autofocus',"autofocus");
        phoneMask(add_phone);

    }

    if(profile_phone){
        phoneMask(profile_phone);
    }

    if(profile_upPhone_phone){
        phoneMask(profile_upPhone_phone);
    }
    if(phoneList.length>0){
        for(phon of phoneList){
            phoneMask(phon);
        }
    }

});

function phoneMask(element){

    element.addEventListener("input", mask, false);
    element.focus();
    setCursorPosition(4, element);
}