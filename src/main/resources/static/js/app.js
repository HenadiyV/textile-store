
const place = document.getElementById('place');//место куда размещаем елементы

const img_view = document.getElementById('img_view');// отображает картинку динамически

const inp_add = document.getElementById('inp_add');

const productId = document.getElementById('productId');// ID product

const im_csrf = document.getElementById('im_csrf');// input hidden _csrf

const myModalAddImg = document.getElementById('myModalAddImg');// модалбное окно

const spanMyModalAddImg = document.getElementById("closeMyModalAddImg");// елемент для закрытия модального окна

let el_preview = '';
let upload = '';


window.onclick = function (event) {

    if (event.target === myModalAddImg) {

        myModalAddImg.style.display = "none";

        closeModal();
    }
};


if (spanMyModalAddImg) {

    spanMyModalAddImg.onclick = function () {

        myModalAddImg.style.display = "none";

        closeModal();
    };
}


function bytesToSize(bytes) {

    let sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];

    if (!bytes) return '0 Byte';

    const i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));

    return Math.round(bytes / Math.pow(1024, i)) + ' ' + sizes[i];
}

// создание элементов
const element = (tag, classes = [], content, src, id) => {

    const node = document.createElement(tag);

    if (classes.length) {

        node.classList.add(...classes);
    }
    if (content) {

        node.textContent = content;
    }
    if (src) {

        node.src = src;
    }
    if (id) {

        node.id = id;
    }
    return node;
};

// создание формы
const createForm = (action, method, multipart) => {

    const frm = document.createElement('form');

    if (action) {

        frm.action = action;
    }
    if (method) {

        frm.method = method;
    }
    if (multipart) {

        frm.enctype = multipart;
    }
    return frm;
};

function noop() {}


const preview = element('div', ['preview'], '');


// обрабатчик входящих файлов
function changeHandler(event) {
    // если нет файлов выходим
    if (!event.target.files.length ) {
        return;
    }
    // переводим в масив event.target.files
    files = Array.from(event.target.files);

    preview.innerHTML = '';

    upload.style.display = 'inline';

    files.forEach(file => {

        // если файл не картинка выходим
        if (!file.type.match('image')) {
            return;
        }
        // создаём reader
        const reader = new FileReader();

        reader.onload = ev => {
            //input.insertAdjacentHTML('afterend',`<img src="${ev.target.result}" />`);
            const src = ev.target.result;
            // карточка картинок
            preview.insertAdjacentHTML('afterbegin',
                `<div class="preview-image">
                <div class = "preview-remove" data-name = "${file.name}"> &times; </div>
                                        <img src="${src}" alt="${file.name}"/>
                                        <div class = "preview-info">
                                        <span>${file.name}</span>
                                        ${bytesToSize(file.size)}
                                    </div>
                                </div>`
            );
        };

        reader.readAsDataURL(file);
    });
}


// удаление картинки из превю
const removeHandler = event => {

    if (!event.target.dataset.name) { // если dataset.name отсутствует выходим
        return;
    }

    const {name} = event.target.dataset;

    // если имя несовпадает оставляем в масиве совпавшее удаляется
    files = files.filter(file => file.name !== name);

    // усли масив пустой прячем кнопку загрузки
    if (!files.length) {
        upload.style.display = 'none';
    }
    //удаление из блока превю
    const block = preview.querySelector(`[data-name="${name}"`).closest('.preview-image');// в блоке ближайший сосед с классом '.preview-image'

    block.classList.add('removing');
    setTimeout(() => block.remove(), 300); //анимация удаления
};


//удаление картинки
preview.addEventListener('click', removeHandler);


function addImage(act,id) {

    myModalAddImg.style.display = 'block';

    let el = '';

    let inp = '';

    let inpId = '';

    inp_add.innerHTML='';

        inp = element('input', [], '', '', 'files');

        inp.type = "file";

        inp.name='files';

        if(act && act.indexOf('update-img')>0){
            //inp.multiple = true;
        }else{
        inp.multiple = true;
        }
        inp.style.display = 'none';

    inp.addEventListener('change', function () {
        changeHandler(event);
    });

    if(id){
        inpId = element('input', [], '', '', '');
        inpId.type='hidden';
        inpId.name='imageId';
        inpId.value=id;
    }
// input _csfr
    let inpC = element('input', [], '', '', '');
    inpC.type = 'hidden';
    inpC.name='_csrf';
    inpC.value = im_csrf.value;

    let inpP = element('input', [], '', '', '');
    inpP.type='hidden';
    inpP.name='id';
    inpP.value=productId.value;

    // пенреопределяем клик на input
    const triggerInput = () => inp.click();
    // кнопка открыть
    const open = element('a', ['my-btn'], 'Відкрити', '', '');
    // вызов переопределения клика
    open.addEventListener('click', triggerInput);

    if (act.indexOf("add-img")>-1) {
        el = createForm(act, 'post','multipart/form-data');
        el.appendChild(open);

        upload = element('button', ['my-btn', 'primary'], 'Загрузити'); //document.createElement('button');
        upload.type='submit';
        upload.style.display = 'none';

        el.appendChild(upload);
        el.appendChild(inpC);
        el.appendChild(inp);
        el.appendChild(inpP);

        if(inpId!==''){
            el.appendChild(inpId);
        }
        place.appendChild(el);
        place.appendChild(preview);
        //document.body.appendChild(el);
    } else {
        el = element('div');
        el.appendChild(open);
        // el.appendChild(open);

        upload = element('a', ['my-btn', 'primary'], 'Загрузити');//document.createElement('button');
        upload.style.display = 'none';

        upload.addEventListener('click', function () {

            el_preview.innerHTML = '';

            el_preview = preview.cloneNode(true);

            preview.innerHTML = '';

            let img = el_preview.querySelectorAll('.preview-remove');
            img.forEach(el => el.remove());

            closeModal();

            img_view.appendChild(el_preview);
            //document.body.appendChild(el_preview);
        });

        el.appendChild(upload);
        // el.appendChild(inpC);
        place.appendChild(el);
        place.appendChild(preview);
        inp_add.appendChild(inp);
    }
}


function closeModal() {
    if (document.getElementById('file')) {
        document.getElementById('file').value = "";
    }
    place.innerHTML = '';
    preview.innerHTML = '';
    myModalAddImg.style.display = "none";
}