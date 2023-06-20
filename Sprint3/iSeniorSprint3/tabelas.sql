CREATE TABLE IF NOT EXISTS public.usuario
(
    idusuario integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 999999 CACHE 1 ),
    email text COLLATE pg_catalog."default",
    nomeusuario text COLLATE pg_catalog."default",
    senha text COLLATE pg_catalog."default",
    primeironome text COLLATE pg_catalog."default",
    sobrenome text COLLATE pg_catalog."default",
    CONSTRAINT pk_usuario PRIMARY KEY (idusuario),
    CONSTRAINT un_usuario_email UNIQUE (email),
    CONSTRAINT un_usuario_nome UNIQUE (nomeusuario)
);



CREATE TABLE IF NOT EXISTS public.categoria
(
    idcategoria integer NOT NULL,
    categoria text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_categoria PRIMARY KEY (idcategoria)
);


CREATE TABLE IF NOT EXISTS public.artigo
(
    idartigo integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 999999 CACHE 1 ),
    categoria integer,
    palavrachave text COLLATE pg_catalog."default",
    tag text COLLATE pg_catalog."default" NOT NULL,
    titulo text COLLATE pg_catalog."default" NOT NULL,
    texto text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_artigo PRIMARY KEY (idartigo),
    CONSTRAINT fk_artigo FOREIGN KEY (categoria)
        REFERENCES public.categoria (idcategoria) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS public.faq
(
    idfaq integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 999999 CACHE 1 ),
    pergunta text COLLATE pg_catalog."default",
    resposta text COLLATE pg_catalog."default",
    CONSTRAINT pk_faq PRIMARY KEY (idfaq)
);

insert into categoria values (1, 'Dia-a-dia');
insert into categoria values (3, 'Configuracoes');
insert into categoria values (2, 'Cuidados');
insert into artigo values (default, 1, 'celular, iphone, calendário, relógio, lembretes, aplicativos', 'Como utilizar os aplicativos de Calendário, Relógio e Lembretes que já vem no iPhone', 'Como utilizar as ferramentas de organização do iPhone', 'CALENDÁRIO: Quando o aplicativo é aberto, o que aparece na tela é uma visão mensal. Para ver o calendário de forma anual, basta clicar no ano no canto superior esquerdo. A data em vermelho é a selecionada e as datas com a bolinha cinza embaixo são as que já possuem tarefas/compromissos/feriados marcados. Alguns feriados nacionais e datas comemorativas já estão cadastrados no aplicativo...');
insert into artigo values (default, 3, 'recursos, acessibilidade, android, dispositivos, celular, telefone', 'Aprenda a utilizar os principais recursos de acessibilidade diponíveis em dispositivos Android', 'Como utilizar os recursos de acessibilidade do Android', 'Para acessar a tela de acessibilidade do android, você deve descer a tela de notificações e clicar na engrenagem no canto superior direito. Após fazer isso, você precisa clicar na barra de pesquisa e pesquisar por acessibilidade e selecionar a opção abaixo de um ícone verde com uma pessoa dentro. ZOOM: selecione a opção melhorias de visibilidade. Ao fazer isso, desça a tela até encontrar a opção zoom da tela, onde você consegue aumentar e diminuir o zoom da tela de maneira prática. TAMANHO E ESTILO DA FONTE: na tela de melhorias de visibilidade, desça a tela até encontrar a opção tamanho e estilo da fonte, onde você pode alterar o tamanho das letras, a fonte e escolher se as letras estarão em negrito. AMPLIAÇÃO: na tela de melhorias de visibilidade, desça a tela até encontrar a opção ampliação, onde você pode escolher se deseja ampliar a tela cheia ou usar a janela lupa...');
insert into artigo values (default, 2, 'sus, conecte, isenior, medicamentos, alergia, conectesus, saúde', 'Como utilizar o aplicativo Conecte SUS.', 'Como utilizar o aplicativo Conecte SUS', 'VACINAS E COMPROVANTE DE VACINAÇÃO: Clicando no ícone de vacina, serão exibidas todas as doses tomadas da vacina da COVID-19, com suas devidas datas de aplicação, fabricantes, lotes, estabelecimentos de saúde e vacinadores. Clicando no nome da vacina, você será direcionado para uma nova tela, na qual você poderá gerar seu Certificado de Vacinação COVID-19. Para isso, basta clicar no botão azul na parte inferior da tela. Será disponibilizado o comprovante, com todos os seus dados cadastrais e informações sobre as doses já recebidas. A partir dessa tela, o certificado pode ter seu idioma alterado para inglês ou espanhol, clicando na bandeira do Brasil na parte superior da tela e selecionando a língua de escolha. Ademais, pode ser gerado um PDF, clicando no botão à direita da bandeira, um QR, virando a tela na horizontal e até mesmo adicionar o documento à Carteira da Apple, clicando no botão preto na parte inferior do certificado....');
insert into usuario values (default, 'email@gmail.com', 'eduardocoelho', 'senha123', 'Eduardo', 'Coelho');
insert into usuario values (default, 'hotmail@hotmail.com', 'beatrizfulgêncio', 'senha456', 'Beatriz', 'Fulgêncio');
insert into usuario values (default, 'outlook@outlook.com', 'anabeatriz', 'senha789', 'Ana Beatriz', 'Pessoa');
insert into faq values (default, 'Qual é o intuito do iSenior?', 'O intuito do nosso site é fornecer um conteúdo de qualidade para os nossos usuários, os guiando para que possam navegar pela internet com mais facilidade e segurança.');
insert into faq values (default, 'Como criar minha conta no iSenior?', 'Para criar uma conta em nosso site, você deve clicar no botão "login" na página principal. Após isso, você será direcionado para outra página onde você deve clicar no botão "Novo Usuário". Após clicar no segundo botão, basta preencher seus dados e salvar.');
insert into faq values (default, 'Como acessar o canal do iSenior no Youtube?', 'Para acessar nosso canal no youtube, basta você pesquisar "iSenior" na plataforma. Você também pode acessar pelo seguinte link: https://www.youtube.com/channel/UC8gEa4WP7oMFxulS-mov-ng .');
