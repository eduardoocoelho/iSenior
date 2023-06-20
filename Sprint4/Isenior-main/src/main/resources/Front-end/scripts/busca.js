
var dbNEws = JSON.parse(localStorage.getItem("db_noticia"));
if (!dbNEws) {
    dbNEws = db_noticia_inicial;
};

function exibePesquisa(query){
    divPesquisa = document.getElementById('busca');
    let pesquisaTexto='';
    let palavrasChaves='';
    for(i=0;i<dbNEws.data.length;i++){
        noticia=dbNEws.data[i];
        for(u=0;u<noticia.palavraschave.length;u++){
            word=noticia.palavraschave[u];

            let re = new RegExp(word,'i' );

            
            if(re.test(query)){
                pesquisaTexto+=`<div class="search-card container-artigo-busca">
                <div class="img-busca">
                    <img class="img-busca" src="./imgs/${noticia.imagem}" alt="">
                </div>
                <h3 class="title-busca" id="title-artigo"><a href="artigo.html?id=${noticia.id}&categoria=${noticia.categoria}"> ${noticia.titulo} </a></h3>
                <p class="resumo-busca" id="resumo">${noticia.descricao}</p>
                <div class="tags-busca">`
                for(e=0;e<noticia.palavraschave.length;e++){
                    pesquisaTexto+=`<p class="tag-3" id="tags">${noticia.palavraschave[e]}</p> `
                }
               pesquisaTexto+= `</div>
        </div>`
            }
             
            
        }  
        
    }

    

    if(pesquisaTexto!='')
    divPesquisa.innerHTML = pesquisaTexto;
    else divPesquisa.innerHTML =`<h2 id="title-artigo" class="title tit-artigo">Não foi possível achar sua pesquisa :(</h2>`

}
function executaPesquisa (evento){
    console.log("entrou");
    let query = document.getElementById("buscar").value;

    if(query.length>0) exibePesquisa(query);


    evento.preventDefault();
}

document.getElementById('search').addEventListener('submit', executaPesquisa);