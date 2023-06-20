function formaArtigo() {
    const params = new URLSearchParams(location.search)
    let id = params.get('id');

    var dadosNoticia = JSON.parse(localStorage.getItem('db_noticia'));
    let idxNoticia = dadosNoticia.data.findIndex((elem) => elem.id == id);

    console.log(id);

    if (idxNoticia > -1) {
        let artigo = dadosNoticia.data[idxNoticia]


        let novoArtigo = '';
        novoArtigo += `<div class="article">
        <button class="button-star" id="star-button">
        <svg class="star" id="star" width="70" height="66" viewBox="0 0 70 66" fill="none"
        xmlns="http://www.w3.org/2000/svg">
        <path
            d="M35 1.61804L42.607 25.0299L42.7192 25.3754H43.0825H67.6992L47.7839 39.8447L47.49 40.0582L47.6022 40.4037L55.2092 63.8156L35.2939 49.3463L35 49.1327L34.7061 49.3463L14.7908 63.8156L22.3978 40.4037L22.51 40.0582L22.2161 39.8447L2.30081 25.3754H26.9175H27.2808L27.393 25.0299L35 1.61804Z"
            fill="none" stroke="#001A3F" />
        </svg>
    </button>
            <h2 id="title-artigo" class="title tit-artigo">${artigo.titulo}</h2>
            
        </div>

        <h6 class="resumo-artigo" id="resumo">${artigo.descricao}</h6>

        <div class="texto">
            <div class="img-text">
            <figure> 
            <img class="img-texto" id="img-artigo" src="./imgs/${artigo.imagem}" alt="">
            </figure>
            <figcaption class="caption">${artigo.legendaimagem}</figcaption>
          </div>
            <p>${artigo.texto}</p>
           
            
            <div class="ratio ratio-16x9">
                ${artigo.video}
            </div>
            <figcaption class="caption">${artigo.legendavideo}</figcaption>
           
        </div>
        <p>Feito por: ${artigo.autores}</p>
        <div class="tag-container">`
        for (u = 0; u < artigo.palavraschave.length; u++) {
            novoArtigo += `<p class="tag-2" id="tags"> ${artigo.palavraschave[u]}</p>`
        }
        novoArtigo += `
        </div>`

        document.getElementById('artigoCompleto').innerHTML = novoArtigo;
    } else {
        alert('artigo não encontrado')
    }
}

function formaSidebar() {
    const params2 = new URLSearchParams(location.search)
    let cat = params2.get('categoria');

    var dadosNoticia2 = JSON.parse(localStorage.getItem('db_noticia'));


    if (cat == 'Dia-a-Dia') {
        let textoSide = '';

        for (i = 0; i < dadosNoticia2.data.length; i++) {

            let news = dadosNoticia2.data[i];

            if (news.categoria == cat) {

                textoSide += ` <a href="artigo.html?id=${news.id}&categoria=${news.categoria}" class="artigos-link">
                <li class="artigo-side1">${news.titulo}</li>
            </a>`
            }
        }
        document.getElementById('sidebarArtig').innerHTML = textoSide;
    }
    if (cat == 'Configuracoes') {
        let textoSide = '';

        for (i = 0; i < dadosNoticia2.data.length; i++) {

            let news = dadosNoticia2.data[i];

            if (news.categoria == cat) {

                textoSide += ` <a href="artigo.html?id=${news.id}&categoria=${news.categoria}" class="artigos-link">
                <li class="artigo-side1">${news.titulo}</li>
            </a>`
            }
        }
        document.getElementById('sidebarArtig').innerHTML = textoSide;
    }
    if (cat == 'Cuidados') {
        let textoSide = '';

        for (i = 0; i < dadosNoticia2.data.length; i++) {

            let news = dadosNoticia2.data[i];

            if (news.categoria == cat) {

                textoSide += ` <a href="artigo.html?id=${news.id}&categoria=${news.categoria}" class="artigos-link">
                <li class="artigo-side1">${news.titulo}</li>
            </a>`
            }
        }
        document.getElementById('sidebarArtig').innerHTML = textoSide;
    }
}
formaArtigo();
formaSidebar();
