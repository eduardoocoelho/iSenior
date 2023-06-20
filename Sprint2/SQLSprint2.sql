CREATE TABLE Usuario (
IdUsuario INT NOT NULL,
Email VARCHAR(100) NOT NULL,
NomeUsuario VARCHAR(20) NOT NULL,
Senha VARCHAR(50) NOT NULL,
PrimeiroNome VARCHAR(30) NOT NULL,
Sobrenome VARCHAR(50),
PRIMARY KEY (IdUsuario),
);

CREATE TABLE Comum (
IdUsuario INT NOT NULL, 
PRIMARY KEY (IdUsuario),
FOREIGN KEY (IdUsuario)
REFERENCES Usuario(IdUsuario)
);

CREATE TABLE Administrador (
IdUsuario INT NOT NULL, 
PRIMARY KEY (IdUsuario),
FOREIGN KEY (IdUsuario)
REFERENCES Usuario(IdUsuario)
);

CREATE TABLE Categoria (
IdCategoria INT NOT NULL, 
Categoria VARCHAR(200) NOT NULL, 
PRIMARY KEY (IdCategoria)
);

CREATE TABLE Artigo (
IdArtigo INT NOT NULL,
Tag VARCHAR(200) NOT NULL, 
Nome VARCHAR(50) NOT NULL,
Categoria INT NOT NULL,
PRIMARY KEY (IdArtigo),
FOREIGN KEY (Categoria)
REFERENCES Categoria(IdCategoria)
);

CREATE TABLE Favoritar (
IdUsuario INT NOT NULL,
IdArtigo INT NOT NULL,
PRIMARY KEY (IdUsuario, IdArtigo),
FOREIGN KEY (IdUsuario)
REFERENCES Usuario(IdUsuario),
FOREIGN KEY (IdArtigo)
REFERENCES Artigo(IdArtigo)
);

CREATE TABLE Buscar ( 
IdUsuario INT NOT NULL,
IdArtigo INT NOT NULL,
PRIMARY KEY (IdUsuario, IdArtigo), 
FOREIGN KEY (IdUsuario)
REFERENCES Usuario(IdUsuario),
FOREIGN KEY (IdArtigo)
REFERENCES Artigo(IdArtigo)
);

CREATE TABLE Faq (
IdAdm INT NOT NULL,   
Pergunta VARCHAR(500) NOT NULL, 
Resposta VARCHAR(500) NOT NULL, 
PRIMARY KEY (IdAdm, Pergunta),
FOREIGN KEY (IdAdm)
REFERENCES Administrador(IdUsuario)
);




