CREATE DATABASE fgaworkshop;
CREATE USER fgaworkshopusr WITH PASSWORD 'fgaworkshopusr';
GRANT CONNECT ON DATABASE fgaworkshop TO fgaworkshopusr;
ALTER USER fgaworkshopusr WITH SUPERUSER;

drop table TB_UF;
drop table TB_USUARIO;

create sequence fgaworkshop.hibernate_sequence;

/*==============================================================*/
/* Table: TB_UF                                                 */
/*==============================================================*/
create table TB_UF (
   ID_UF                INT8                 not null,
   NOME                 VARCHAR(30)          not null,
   SIGLA                VARCHAR(2)           not null,
   constraint PK_TB_UF primary key (ID_UF)
);

/*==============================================================*/
/* Table: TB_USUARIO                                            */
/*==============================================================*/
create table TB_USUARIO (
   ID_USUARIO           INT8                 not null,
   NOME                 VARCHAR(100)         not null,
   CPF                  VARCHAR(11)          not null,
   RG                   VARCHAR(30)          null,
   ORGAO_EXPEDIDOR      VARCHAR(10)          null,
   UF_DOCUMENTO         VARCHAR(2)           null,
   CURSO                VARCHAR(30)          not null,
   OUTRO_CURSO          VARCHAR(30)          null,
   CEP                  VARCHAR(10)          null,
   LOGRADOURO           VARCHAR(100)         null,
   COMPLEMENTO          VARCHAR(20)          null,
   NUMERO               VARCHAR(10)          null,
   BAIRRO               VARCHAR(50)          null,
   CIDADE               VARCHAR(50)          null,
   UF                   VARCHAR(2)           null,
   PAIS                 VARCHAR(30)          null,
   TELEFONE             VARCHAR(15)          null,
   EMAIL                VARCHAR(100)         not null,
   LOGIN                VARCHAR(100)         null,
   LANGUAGE             VARCHAR(10)          null,
   constraint PK_TB_USUARIO primary key (ID_USUARIO)
);

