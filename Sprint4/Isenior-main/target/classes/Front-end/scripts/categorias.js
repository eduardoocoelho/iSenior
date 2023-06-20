function qualCategoria() {
    const params = new URLSearchParams(location.search)
    let cat = params.get('categoria');

    var dadosNoticia2 = JSON.parse(localStorage.getItem('db_noticia'));


    if (cat == 'Dia-a-Dia') {
        let textoCategoria = '';
        let textoSide='';
        textoCategoria += `<div class="categoria-info">
        <h1 id="title-categoria" class="title">Dia a dia</h1>
        <p>Artigos que ensinam, de maneira simples e didática, a utilizar as mais diversas ferramentas digitais para facilitar o seu cotidiano.</p>
    </div>
    <div class="container-artigo">`



        for (i = 0; i < dadosNoticia2.data.length; i++) {

            let news = dadosNoticia2.data[i];

            if (news.categoria == cat) {

                textoCategoria += `<div class="artigo">
                <div class="card-artigo" id="card-artigo">
                    <img src="./imgs/${news.imagem}" id="img-artigo" class="img-artigo" alt="">
                    <h3 id="title-artigo" class="title-artigo"><a href="artigo.html?id=${news.id}&categoria=${news.categoria}">${news.titulo}.</a></h3>
                    <div class="resumo" id="resumo">
                        <p>${news.descricao}</p>
                    </div>
                    <div class="tag-containers">`
                    for (u = 0; u < news.palavraschave.length; u++) {
                        textoCategoria+=`<div class="tags tags-cat">
                        <p class="tag" id="tags"> ${news.palavraschave[u]}</p>
                    </div>`
                    }
                    
                textoCategoria+=`
                </div>
                    </div>
                </div>`

                textoSide+=` <a href="artigo.html?id=${news.id}&categoria=${news.categoria}" class="artigos-link"><li class="artigo-side1">${news.titulo}</li></a>`

             }
    }
    document.getElementById('categoriaInfo').innerHTML = textoCategoria;
    document.getElementById('sidebarArtigos').innerHTML = textoSide;
}

if(cat =="Cuidados"){
    let textoCategoria = '';
    let textoSide='';
    textoCategoria += `<div class="categoria-info">
    <h1 id="title-categoria" class="title">Cuidados</h1>
    <p>Aprenda a navegar pela internet com segurança, reduzindo o risco de cair em golpes e em fake news.</p>
    </div>
<div class="container-artigo">`



    for (i = 0; i < dadosNoticia2.data.length; i++) {

        let news = dadosNoticia2.data[i];

        if (news.categoria == cat) {

            textoCategoria += `<div class="artigo">
            <div class="card-artigo" id="card-artigo">
                <img src="./imgs/${news.imagem}" id="img-artigo" class="img-artigo" alt="">
                <h3 id="title-artigo" class="title-artigo"><a href="artigo.html?id=${news.id}&categoria=${news.categoria}">${news.titulo}.</a></h3>
                <div class="resumo" id="resumo">
                    <p>${news.descricao}</p>
                </div>
                <div class="tag-containers">`
                for (u = 0; u < news.palavraschave.length; u++) {
                    textoCategoria+=`<div class="tags tags-cat">
                    <p class="tag" id="tags"> ${news.palavraschave[u]}</p>
                </div>`
                }
                
            textoCategoria+=`
            </div>
            </div>
        </div>`

        textoSide+=` <a href="artigo.html?id=${news.id}&categoria=${news.categoria}" class="artigos-link"><li class="artigo-side1">${news.titulo}</li></a>`

    }
}
document.getElementById('categoriaInfo').innerHTML = textoCategoria;
document.getElementById('sidebarArtigos').innerHTML = textoSide;

}


if(cat =="Configuracoes"){
    let textoCategoria = '';
    let textoSide='';

    textoCategoria += `<div class="categoria-info">
    <h1 id="title-categoria" class="title">Configurações</h1>
    <p>Conheça e aprenda a ativar os principais recursos de acessibilidade dos dispositivos e dos sites.</p>
    </div>
<div class="container-artigo">`



    for (i = 0; i < dadosNoticia2.data.length; i++) {

        let news = dadosNoticia2.data[i];

        if (news.categoria == cat) {

            textoCategoria += `<div class="artigo">
            <div class="card-artigo" id="card-artigo">
                <img src="./imgs/${news.imagem}" id="img-artigo" class="img-artigo" alt="">
                <h3 id="title-artigo" class="title-artigo"><a href="artigo.html?id=${news.id}&categoria=${news.categoria}">${news.titulo}.</a></h3>
                <div class="resumo" id="resumo">
                    <p>${news.descricao}</p>
                </div>
                <div class="tag-containers">`
                for (u = 0; u < news.palavraschave.length; u++) {
                    textoCategoria+=`<div class="tags tags-cat">
                    <p class="tag" id="tags"> ${news.palavraschave[u]}</p>
                </div>`
                }
                
            textoCategoria+=`
            </div>
            </div>
        </div>`
        textoSide+=` <a href="artigo.html?id=${news.id}&categoria=${news.categoria}" class="artigos-link"><li class="artigo-side1">${news.titulo}</li></a>`


    }
}
document.getElementById('categoriaInfo').innerHTML = textoCategoria;
document.getElementById('sidebarArtigos').innerHTML = textoSide;

}
}

qualCategoria();