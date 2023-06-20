let favoritos = [];

window.onload = () => {


    let usuarioCorrente = JSON.parse(sessionStorage.getItem('usuarioCorrente'));
    let estrelas = document.getElementsByClassName("star");
    let noticia = JSON.parse(localStorage.getItem("db_noticia"));
    let novoFavorito = {};

    const params = new URLSearchParams(location.search)
    let id = params.get('id');
    let idxNoticia = noticia.data.findIndex((elem) => elem.id == id);

    let favoritos_db = JSON.parse(localStorage.getItem("db_favoritos"));

    if(!favoritos_db){
        localStorage.setItem('db_favoritos', JSON.stringify(favoritos));
    }
    else{
        if(usuarioCorrente) {
            favoritos_db = JSON.parse(localStorage.getItem("db_favoritos"));
            for(let i = 0; i < estrelas.length; i++) {
                estrelas[i].onclick = () => {
                    if(estrelas[i].firstChild.nextSibling.getAttribute("fill") == "#") {
                        estrelas[i].firstChild.nextSibling.setAttribute("fill", "#FFD540");
                        novoFavorito = {"user": usuarioCorrente['id'], "noticia": noticia.data[idxNoticia].id};
                        favoritos_db.push(novoFavorito);
                        console.log("teste estrela");
    
                        localStorage.setItem('db_favoritos', JSON.stringify(favoritos_db));
                        sessionStorage.setItem('db_favoritos', JSON.stringify(favoritos_db));
    
                        console.log(favoritos);
                        console.log(favoritos_db);

                    }
                    else if(estrelas[i].firstChild.nextSibling.getAttribute("fill") == "#FFD540") {
                        estrelas[i].firstChild.nextSibling.setAttribute("fill", "#");
                        // favoritos_db.splice(favoritos_db.indexOf({"user": usuarioCorrente['id'], "noticia": noticia.data.id}), 1);
                        localStorage.setItem('db_favoritos', JSON.stringify(favoritos_db));
                        sessionStorage.setItem('db_favoritos', JSON.stringify(favoritos_db));
    
                        console.log(favoritos);
                    }
                }
            }
       }
    }

  

}