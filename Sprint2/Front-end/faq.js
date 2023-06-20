let data =  [
    {
        title: 'Qual é o intuito do iSenior?',
        text: 'O intuito do nosso site é fornecer um conteúdo de qualidade para os nossos usuários, os guiando para que possam navegar pela internet com mais facilidade e segurança.'
    }, {
        title: 'Como criar minha conta no iSenior?',
        text: 'Para criar uma conta em nosso site, você deve clicar no botão "login" na página principal. Após isso, você será direcionado para outra página onde você deve clicar no botão "Novo Usuário". Após clicar no segundo botão, basta preencher seus dados e salvar.'
    }, {
        title: 'Como acessar o canal do iSenior no Youtube?',
        text: 'Para acessar nosso canal no youtube, basta você pesquisar "iSenior" na plataforma. Você também pode acessar pelo seguinte link: \n https://www.youtube.com/channel/UC8gEa4WP7oMFxulS-mov-ng .'          
    }, {
        title: 'Quem são os responsáveis pelo iSenior?',
        text: 'Os responsáveis pelo site são os estudantes da PUC Minas do curso de Ciência da Computação: Ana Beatriz Pessoa Braz, Arthur Ricardo, Beatriz Fulgêncio da Cunha Menezes, Brenno Augusto Herculano, Davi Lima, Eduardo Oliveira Coelho, Gabriel Ribeiro Todt.'         
    },{
        title: 'Como favoritar uma notícia no site do iSenior?',
        text: 'Para favoritar uma notícia no site, basta você clicar no botão em formato de estrela ao lado do título da notícia. '
    }
]


const DATA_KEY = 'FAQ'

function loadPage() {

    console.log("Começando o processo de armazento!")

    initEvents();

    let storedData = localStorage.getItem(DATA_KEY);
    if(!storedData) {
        initDatabase()
        return;
    }

    let parsedData = JSON.parse(storedData);

    showQuestionsAndAnswers(parsedData);
}

function initEvents() {
    let insertForm = document.getElementById('cadastrofaq');
    insertForm.addEventListener('submit', e => {
        e.preventDefault();
        let title = document.getElementById('titulo').value;
        let text = document.getElementById('texto').value;
        insertQuestion(title, text);
        window.location.reload();
    });
}

function initDatabase() {
    let jsonData = JSON.stringify(data);
    localStorage.setItem(DATA_KEY, jsonData);
    showQuestionsAndAnswers(data);
}

function insertQuestion(title, text) {
    console.log("Inserido")
    let storedData = JSON.parse(localStorage.getItem(DATA_KEY));
    const question = {
        title,
        text
    }

    let dataToBeStored = [
        ...storedData,
        question
    ]

    localStorage.setItem(DATA_KEY, JSON.stringify(dataToBeStored));
}

function showQuestionsAndAnswers(faqList) {
    let questionList = document.getElementById('lista-perguntas');
    let faqHtml = '';
    for(let question of faqList) {
        faqHtml += `<div class="pergunta-item">
        <div class="pergunta-box">
            <p class="pergunta-titulo"> <b>${question.title}</b></p> <br> 
            <p class="pergunta-paragrafo"><b>RESPOSTA</b>: ${question.text}</p> <br>
        </div>
        </div>`
    }
    questionList.innerHTML = faqHtml;
}
