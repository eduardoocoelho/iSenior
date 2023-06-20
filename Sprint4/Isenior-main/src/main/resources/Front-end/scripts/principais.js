const NEWS_KEY = "db_noticia";

function randomNoticia(){
var noticiasTexto = '';

// dbNoticia recebe as ARRYS de JSON
var dbNoticia = JSON.parse(localStorage.getItem("db_noticia"));

//variaveis para determinar a quantidade 

controle = [];

//conseguir noticia aleatória
function getRandom() {
     return Math.floor(Math.random() * dbNoticia.data.length + 0)
}

// For para pegar todos os JSON da ARRYS

    for (i = getRandom(), a = 0; a < 3; a++, i = getRandom()) {

        //controle para não repetir noticia
        for (z = 0; z <= controle.length; z++) {
            while (i == controle[z]) {
                i = getRandom();
            }
        }
        //preencher noticias
        noticiasTexto += `<div class="principais">
            <img class="img-principais" src=./imgs/${dbNoticia.data[i].imagem} >
            <h6 class="text-principais">${dbNoticia.data[i].titulo} </h6>
            <p  class="link-principais" ><a href="artigo.html?id=${dbNoticia.data[i].id}&categoria=${dbNoticia.data[i].categoria}">Ler mais>> </a></p>
            <div class="tags tags-principal">`
        for (u = 0; u < dbNoticia.data[i].palavraschave.length; u++) {
            noticiasTexto += `<p class="tag" id="tags"> ${dbNoticia.data[i].palavraschave[u]}</p>`
        }
        noticiasTexto += `</div> 
            </div>`
    
        //controle 
        controle[a] = i
    
    }
    
    // Escrevendo no HTML as Notícias recuperadas
    document.getElementById('principais').innerHTML = noticiasTexto;
}

const onLoad = () => {
    initNews();

    randomNoticia();

    resetar.onclick = () => {
        randomNoticia();
    }
} 

function initNews() {
    let storedNews = localStorage.getItem('db_noticia');
    if(!storedNews) {
        localStorage.setItem('db_noticia', JSON.stringify(db_noticia_inicial));
    }
}

onLoad();
