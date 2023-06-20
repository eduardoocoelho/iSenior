let botaoStar = document.getElementById("star-button")
let estrela = document.getElementById("star");

var dbFavoritosExiste = JSON.parse(localStorage.getItem('db_favoritos'));
// var listaNoticaDb = JSON.parse(localStorage.getItem('db_noticia'))

var users = JSON.parse(localStorage.getItem('db_usuarios'));



// if (!dbFavoritosExiste) {
//     var dbFavoritos = {
//         resultados: [{
//             login: users.usuarios[0].login ,
//             favoritos: []
//         },
//         {
//             login: users.usuarios[1].login,
//             favoritos: []
//         }]
//     }
//  localStorage.setItem('db_favoritos', JSON.stringify(dbFavoritos))

// } else 

dbFavoritos = dbFavoritosExiste;

var userNow = JSON.parse(sessionStorage.getItem('usuarioCorrente'));

for (i = 0; i < users.usuarios.length; i++) {
    for(u=0;u<dbFavoritos.resultados.length;u++){
        if (dbFavoritos.resultados[u].login ==  userNow.login) 
        {
            var login = userNow.login;
            var posicao = u;
        }
      
    }
    
}

// function addFavoritos(login){
//     let favoritoObj = { login: login, favoritos:[]};

//     dbFavoritos.resultados.push(favoritoObj);
//     localStorage.setItem('db_favoritos', JSON.stringify(dbFavoritos))
// }

function trocaCorEstrela() {


    if (estrela.firstChild.nextSibling.getAttribute('fill') == "none") {
        console.log("estrela colorido")

        estrela.firstChild.nextSibling.setAttribute("fill", "#FFD540");

        const params = new URLSearchParams(location.search);
        let idNoticia = params.get('id');

        let podeAdicionar=true;
        for(u=0;u<dbFavoritos.resultados[posicao].favoritos.length;u++){
            if(idNoticia==dbFavoritos.resultados[posicao].favoritos[u]) podeAdicionar=false;
            else podeAdicionar=true;
        }
        if(podeAdicionar){
 
        dbFavoritos.resultados[posicao].favoritos.push(idNoticia)
        localStorage.setItem('db_favoritos', JSON.stringify(dbFavoritos))

        }


        console.log(dbFavoritos);


    } else {
        console.log("estrela none")

        estrela.firstChild.nextSibling.setAttribute("fill", "none");
    }
}

botaoStar.addEventListener("click", () => {
    if(usuarioCorrente) trocaCorEstrela()
})
