DROP TABLE IF EXISTS `lista_de_compras`;

CREATE TABLE `lista_de_compras` (
    `id_lista_de_compras` INT NOT NULL AUTO_INCREMENT,
    `nome` varchar(40) NOT NULL,
    `concluida` tinyint(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id_lista_de_compras`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `produtos`;

CREATE TABLE `produtos` (
    `id_produto` INT NOT NULL AUTO_INCREMENT,
    `nome` varchar(40) NOT NULL,
    `quantidade` INT,
    `id_lista_de_compras` INT NOT NULL,
    PRIMARY KEY (`id_produto`),
    CONSTRAINT `fk_lista_de_compras` FOREIGN KEY (`id_lista_de_compras`) REFERENCES `lista_de_compras` (`id_lista_de_compras`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;