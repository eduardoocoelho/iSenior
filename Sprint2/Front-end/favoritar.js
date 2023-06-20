userNow = JSON.parse(sessionStorage.getItem('usuarioCorrente'));
dbFavoritos = JSON.parse(localStorage.getItem('db_favoritos'));
 users = JSON.parse(localStorage.getItem('db_usuarios'));

var posicao;
for (i = 0; i < users.usuarios.length; i++) {
    for(u=0;u<dbFavoritos.resultados.length;u++){
        if (dbFavoritos.resultados[u].login ==  userNow.login) {
            var login = userNow.login;
            posicao = u;
        }
       
    }
}

function ExibeFavoritos (){
    var dadosNoticia2 = JSON.parse(localStorage.getItem('db_noticia'));
    let textoFavorits='';
    let res = dbFavoritos.resultados[posicao].favoritos;

    for(i=0; i<res.length;i++){
        for(u=0;u<dadosNoticia2.data.length;u++){

            if(res[i]==dadosNoticia2.data[u].id){
                noticia = dadosNoticia2.data[u];
                textoFavorits+=`<div class="search-card container-artigo-busca">
                <div class="img-busca">
                    <img class="img-busca" src="${noticia.imagem}" alt="">
                </div>
                <h3 class="title-busca" id="title-artigo"><a href="/artigo.html?id=${noticia.id}&categoria=${noticia.categoria}"> ${noticia.titulo} </a></h3><svg class="star_favorito" width="53" height="50" viewBox="0 0 70 66" fill="none"
                xmlns="http://www.w3.org/2000/svg">
                <path
                    d="M35 1.61804L42.607 25.0299L42.7192 25.3754H43.0825H67.6992L47.7839 39.8447L47.49 40.0582L47.6022 40.4037L55.2092 63.8156L35.2939 49.3463L35 49.1327L34.7061 49.3463L14.7908 63.8156L22.3978 40.4037L22.51 40.0582L22.2161 39.8447L2.30081 25.3754H26.9175H27.2808L27.393 25.0299L35 1.61804Z"
                    fill="#FFD540" stroke="#001A3F" />
            </svg>
                <p class="resumo-busca" id="resumo">${noticia.descricao}</p>
                <div class="tags-busca">`
                for(e=0;e<noticia.palavraschave.length;e++){
                    textoFavorits+=`<p class="tag-3" id="tags">${noticia.palavraschave[e]}</p> `
                }
               textoFavorits+= `</div>
        </div>`
            }
        }
        
    }

    document.getElementById('favoritos').innerHTML = textoFavorits;
}

ExibeFavoritos();


