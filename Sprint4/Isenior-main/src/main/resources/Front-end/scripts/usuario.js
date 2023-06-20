function GetUser(){
    usuarioCorrenteJSON = sessionStorage.getItem('usuarioCorrente');
    if (usuarioCorrenteJSON) {
        usuarioCorrente = JSON.parse (usuarioCorrenteJSON);
    } 

    document.getElementById('userInfo').innerHTML=` <div class="container-artigo-user search-card">
    <div style="width: 390px;">
       <div class="user">
           <i class="fa-solid fa-user"></i>
           <div class="user-text">
           <h1 class="title-user">${usuarioCorrente.nome}</h1>
           <h5 class="resumo-user" id="resumo">${usuarioCorrente.login}</h5>
           <h5 class="resumo-user" id="resumo">${usuarioCorrente.email}</h5>
       </div>
           </div>
       
     </div>
   </div>`
}

GetUser();