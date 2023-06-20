
localStorage.setItem("db_noticia", JSON.stringify(db_noticia_inicial));
// Caso os dados já estejam no Local Storage, caso contrário, carrega os dados iniciais
var dbNEws = JSON.parse(localStorage.getItem("db_noticia"));
if (!dbNEws) {
    dbNEws = db_noticia_inicial
};

// Exibe mensagem em um elemento de ID msg
function displayMessage(msg) {
    $('#msg').html('<div class="alert alert-warning">' + msg + '</div>');
}

function insertNoticia(noticia) {
    // Calcula novo Id a partir do último código existente no array (PODE GERAR ERRO SE A BASE ESTIVER VAZIA)
    let novoId = 1;
    if (dbNews.data.length != 0)
        novoId = dbNews.data[dbNews.data.length - 1].id + 1;
    let novaNoticia = {
        "id": novoId,
        "titulo": noticia.titulo,
        "autores": noticia.autores,
        "publicoalvo": noticia.publicoalvo,
        "categoria": noticia.categoria,
        "descricao": noticia.descricao,
        "palavraschave": noticia.palavraschave,
        "texto": noticia.texto,
        "video": noticia.video,
        "legendavideo": noticia.legendavideo,
        "imagem": noticia.imagem,
        "legendaimagem": noticia.legendaimagem
    };

    // Insere o novo objeto no array
    dbNews.data.push(novaNoticia);
    displayMessage("Notícia inserida com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_noticia', JSON.stringify(db));
}

function updateNoticia(id, noticia) {
    // Localiza o indice do objeto a ser alterado no array a partir do seu ID
    let index = dbNews.data.map(obj => obj.id).indexOf(id);

    // Altera os dados do objeto no array
    dbNews.data[index].titulo = noticia.titulo
    dbNews.data[index].autores = noticia.autores,
        dbNews.data[index].publicoalvo = noticia.publicoalvo,
        dbNews.data[index].categoria = noticia.categoria,
        dbNews.data[index].descricao = noticia.descricao,
        dbNews.data[index].palavraschave = noticia.palavraschave,
        dbNews.data[index].texto = noticia.texto,
        dbNews.data[index].video = noticia.video,
        dbNews.data[index].legendavideo = noticia.legendavideo
    dbNews.data[index].imagem = noticia.imagem,
        dbNews.data[index].legendaimagem = noticia.legendaimagem

    displayMessage("Notícia alterada com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_noticia', JSON.stringify(db));
}

function deleteNoticia(id) {
    // Filtra o array removendo o elemento com o id passado
    dbNews.data = dbNews.data.filter(function (element) { return element.id != id });

    displayMessage("Notícia removida com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_noticia', JSON.stringify(db));
}