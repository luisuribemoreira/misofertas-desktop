--SECCION BORRADO
DROP TABLE certificado CASCADE CONSTRAINTS;
DROP TABLE consumidor CASCADE CONSTRAINTS;
DROP TABLE detalle_oferta CASCADE CONSTRAINTS;
DROP TABLE detalle_producto CASCADE CONSTRAINTS;
DROP TABLE empleado CASCADE CONSTRAINTS;
DROP TABLE empresa CASCADE CONSTRAINTS;
DROP TABLE lote CASCADE CONSTRAINTS;
DROP TABLE mensajeria CASCADE CONSTRAINTS;
DROP TABLE oferta CASCADE CONSTRAINTS;
DROP TABLE persona CASCADE CONSTRAINTS;
DROP TABLE producto CASCADE CONSTRAINTS;
DROP TABLE reg_error CASCADE CONSTRAINTS;
DROP TABLE reg_producto CASCADE CONSTRAINTS;
DROP TABLE sucursal CASCADE CONSTRAINTS;
DROP TABLE usuario CASCADE CONSTRAINTS;
DROP TABLE certificado_emitido CASCADE CONSTRAINTS;

DROP SEQUENCE certificado_seq;
DROP SEQUENCE consumidor_seq;
DROP SEQUENCE oferta_seq;
DROP SEQUENCE detalleoferta_seq;
DROP SEQUENCE empleado_seq;
DROP SEQUENCE lote_seq;
DROP SEQUENCE msj_seq;
DROP SEQUENCE producto_seq;
DROP SEQUENCE error_seq;
DROP SEQUENCE caso_seq;
DROP SEQUENCE sucursal_seq;
DROP SEQUENCE emitido_seq;

--SECCION CREACION SEQUENCIAS
CREATE SEQUENCE certificado_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE consumidor_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE oferta_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE detalleoferta_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE empleado_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE lote_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE msj_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE producto_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE error_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE caso_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE sucursal_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE emitido_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;

--SECCION CREACION TABLAS
CREATE TABLE certificado (
    id_cert     NUMBER(10) NOT NULL,
    pts_min     NUMBER(7) NOT NULL,
    pts_max     NUMBER(7) NOT NULL,
    descuento   NUMBER(3,1) NOT NULL,
    tope        NUMBER(9) NOT NULL,
    rubro       VARCHAR2(150) NOT NULL
);

ALTER TABLE certificado ADD CONSTRAINT certificado_pk PRIMARY KEY ( id_cert );

CREATE TABLE certificado_emitido (
    id_cemitido           NUMBER(9) NOT NULL,
    descuento             NUMBER(3,1) NOT NULL,
    pts_usados            NUMBER(8) NOT NULL,
    consumidor_username   VARCHAR2(25) NOT NULL,
    consumidor_run        VARCHAR2(11) NOT NULL,
    certificado_id_cert   NUMBER(10) NOT NULL
);

ALTER TABLE certificado_emitido ADD CONSTRAINT certificado_emitido_pk PRIMARY KEY ( id_cemitido );

CREATE TABLE consumidor (
    puntos             NUMBER(6) NOT NULL,
    persona_run        VARCHAR2(11) NOT NULL,
    usuario_username   VARCHAR2(25) NOT NULL
);

CREATE UNIQUE INDEX consumidor__idx ON
    consumidor ( usuario_username ASC );

CREATE UNIQUE INDEX consumidor__idxv1 ON
    consumidor ( persona_run ASC );

ALTER TABLE consumidor ADD CONSTRAINT consumidor_pk PRIMARY KEY ( usuario_username,
persona_run );

CREATE TABLE detalle_oferta (
    id_det                NUMBER(8) NOT NULL,
    img_boleta            BLOB NOT NULL,
    fec_valoracion        DATE NOT NULL,
    valoracion            NUMBER(3,1) NOT NULL,
    oferta_id_oferta      NUMBER(10) NOT NULL,
    consumidor_username   VARCHAR2(25) NOT NULL,
    consumidor_run        VARCHAR2(11) NOT NULL
);

ALTER TABLE detalle_oferta
    ADD CONSTRAINT detalle_oferta_pk PRIMARY KEY ( oferta_id_oferta,
    consumidor_username,
    consumidor_run );

CREATE TABLE empleado (
    idreferencia       NUMBER(8),
    cargo              VARCHAR2(25) NOT NULL,
    persona_run        VARCHAR2(11) NOT NULL,
    usuario_username   VARCHAR2(25) NOT NULL
);

CREATE UNIQUE INDEX empleado__idx ON
    empleado ( usuario_username ASC );

CREATE UNIQUE INDEX empleado__idxv1 ON
    empleado ( persona_run ASC );

ALTER TABLE empleado ADD CONSTRAINT empleado_pk PRIMARY KEY ( usuario_username,
persona_run );

CREATE TABLE empresa (
    rut            VARCHAR2(18) NOT NULL,
    nombre         VARCHAR2(25) NOT NULL,
    direccion      VARCHAR2(100) NOT NULL,
    razon_social   VARCHAR2(25) NOT NULL
);

ALTER TABLE empresa ADD CONSTRAINT empresa_pk PRIMARY KEY ( rut );

CREATE TABLE lote (
    id_lote            NUMBER(10) NOT NULL,
    cantidad           NUMBER(5) NOT NULL,
    fecha_limite       DATE NOT NULL,
    producto_id_prod   NUMBER(12) NOT NULL
);

ALTER TABLE lote ADD CONSTRAINT lote_pk PRIMARY KEY ( id_lote );

CREATE TABLE mensajeria (
    id_msj                NUMBER(12) NOT NULL,
    asunto                VARCHAR2(25) NOT NULL,
    mensaje               VARCHAR2(100) NOT NULL,
    cupon                 BLOB,
    img_oferta            BLOB,
    sucursal_id_sucur     NUMBER(8) NOT NULL,
    oferta_id_oferta      NUMBER(10) NOT NULL,
    consumidor_username   VARCHAR2(25) NOT NULL,
    consumidor_run        VARCHAR2(11) NOT NULL
);

ALTER TABLE mensajeria ADD CONSTRAINT mensajeria_pk PRIMARY KEY ( id_msj );

CREATE TABLE oferta (
    id_oferta           NUMBER(10) NOT NULL,
    descripcion         VARCHAR2(200) NOT NULL,
    fec_inicio          DATE NOT NULL,
    fec_termino         DATE NOT NULL,
    img_oferta          BLOB NOT NULL,
    valoracion_total    NUMBER(3,1) NOT NULL,
    porc_desc           NUMBER(3,1) NOT NULL,
    sucursal_id_sucur   NUMBER(8) NOT NULL,
    producto_id_prod    NUMBER(12) NOT NULL
);

ALTER TABLE oferta ADD CONSTRAINT oferta_pk PRIMARY KEY ( id_oferta );

CREATE TABLE persona (
    run       VARCHAR2(11) NOT NULL,
    nombre    VARCHAR2(25) NOT NULL,
    paterno   VARCHAR2(25) NOT NULL,
    materno   VARCHAR2(25),
    sexo      VARCHAR2(20),
    email     VARCHAR2(35) NOT NULL,
    fec_nac   DATE NOT NULL
);

ALTER TABLE persona ADD CONSTRAINT persona_pk PRIMARY KEY ( run );

CREATE TABLE producto (
    id_prod             NUMBER(12) NOT NULL,
    nombre              VARCHAR2(25) NOT NULL,
    desc_prod           VARCHAR2(200) NOT NULL,
    fec_ingreso         DATE NOT NULL,
    estado              CHAR(1) NOT NULL,
    stk_seguro          NUMBER(7) NOT NULL,
    stk_sucur           NUMBER(8) NOT NULL,
    rubro               VARCHAR2(25) NOT NULL,
    desc_rubro          VARCHAR2(200) NOT NULL,
    valor               NUMBER(9) NOT NULL,
    sucursal_id_sucur   NUMBER(8) NOT NULL
);

ALTER TABLE producto ADD CONSTRAINT producto_pk PRIMARY KEY ( id_prod );

CREATE TABLE reg_error (
    id_error            NUMBER(10) NOT NULL,
    descripcion         VARCHAR2(200) NOT NULL,
    mensajeria_id_msj   NUMBER(12) NOT NULL
);

ALTER TABLE reg_error ADD CONSTRAINT reg_error_pk PRIMARY KEY ( id_error );

CREATE TABLE reg_producto (
    id_caso            NUMBER(8) NOT NULL,
    descripcion        VARCHAR2(200) NOT NULL,
    fec_salida         DATE NOT NULL,
    producto_id_prod   NUMBER(12) NOT NULL
);

ALTER TABLE reg_producto ADD CONSTRAINT reg_producto_pk PRIMARY KEY ( id_caso );

CREATE TABLE sucursal (
    id_sucur      NUMBER(8) NOT NULL,
    nombre        VARCHAR2(25) NOT NULL,
    direccion     VARCHAR2(100) NOT NULL,
    fono          VARCHAR2(13) NOT NULL,
    comuna        VARCHAR2(25) NOT NULL,
    empresa_rut   VARCHAR2(18) NOT NULL
);

ALTER TABLE sucursal ADD CONSTRAINT sucursal_pk PRIMARY KEY ( id_sucur );

CREATE TABLE usuario (
    username   VARCHAR2(25) NOT NULL,
    password   VARCHAR2(65) NOT NULL,
    perfil     VARCHAR2(25) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( username );

--SECCION FOREIGN KEY
ALTER TABLE certificado_emitido
    ADD CONSTRAINT emitido_certificado_fk FOREIGN KEY ( certificado_id_cert )
        REFERENCES certificado ( id_cert ) ON DELETE CASCADE;

ALTER TABLE certificado_emitido
    ADD CONSTRAINT certificado_emitido_fk FOREIGN KEY ( consumidor_username,
    consumidor_run )
        REFERENCES consumidor ( usuario_username,
        persona_run ) ON DELETE CASCADE;

ALTER TABLE consumidor
    ADD CONSTRAINT consumidor_persona_fk FOREIGN KEY ( persona_run )
        REFERENCES persona ( run ) ON DELETE CASCADE;

ALTER TABLE consumidor
    ADD CONSTRAINT consumidor_usuario_fk FOREIGN KEY ( usuario_username )
        REFERENCES usuario ( username ) ON DELETE CASCADE;

ALTER TABLE detalle_oferta
    ADD CONSTRAINT detalle_oferta_consumidor_fk FOREIGN KEY ( consumidor_username,
    consumidor_run )
        REFERENCES consumidor ( usuario_username,
        persona_run ) ON DELETE CASCADE;

ALTER TABLE detalle_oferta
    ADD CONSTRAINT detalle_oferta_oferta_fk FOREIGN KEY ( oferta_id_oferta )
        REFERENCES oferta ( id_oferta ) ON DELETE CASCADE;

ALTER TABLE empleado
    ADD CONSTRAINT empleado_persona_fk FOREIGN KEY ( persona_run )
        REFERENCES persona ( run ) ON DELETE CASCADE;

ALTER TABLE empleado
    ADD CONSTRAINT empleado_usuario_fk FOREIGN KEY ( usuario_username )
        REFERENCES usuario ( username ) ON DELETE CASCADE;

ALTER TABLE lote
    ADD CONSTRAINT lote_producto_fk FOREIGN KEY ( producto_id_prod )
        REFERENCES producto ( id_prod ) ON DELETE CASCADE;

ALTER TABLE mensajeria
    ADD CONSTRAINT mensajeria_consumidor_fk FOREIGN KEY ( consumidor_username,
    consumidor_run )
        REFERENCES consumidor ( usuario_username,
        persona_run ) ON DELETE CASCADE;

ALTER TABLE mensajeria
    ADD CONSTRAINT mensajeria_oferta_fk FOREIGN KEY ( oferta_id_oferta )
        REFERENCES oferta ( id_oferta ) ON DELETE CASCADE;

ALTER TABLE mensajeria
    ADD CONSTRAINT mensajeria_sucursal_fk FOREIGN KEY ( sucursal_id_sucur )
        REFERENCES sucursal ( id_sucur ) ON DELETE CASCADE;

ALTER TABLE oferta
    ADD CONSTRAINT oferta_producto_fk FOREIGN KEY ( producto_id_prod )
        REFERENCES producto ( id_prod ) ON DELETE CASCADE;

ALTER TABLE oferta
    ADD CONSTRAINT oferta_sucursal_fk FOREIGN KEY ( sucursal_id_sucur )
        REFERENCES sucursal ( id_sucur ) ON DELETE CASCADE;

ALTER TABLE producto
    ADD CONSTRAINT producto_sucursal_fk FOREIGN KEY ( sucursal_id_sucur )
        REFERENCES sucursal ( id_sucur ) ON DELETE CASCADE;

ALTER TABLE reg_error
    ADD CONSTRAINT reg_error_mensajeria_fk FOREIGN KEY ( mensajeria_id_msj )
        REFERENCES mensajeria ( id_msj ) ON DELETE CASCADE;

ALTER TABLE reg_producto
    ADD CONSTRAINT reg_producto_producto_fk FOREIGN KEY ( producto_id_prod )
        REFERENCES producto ( id_prod ) ON DELETE CASCADE;

ALTER TABLE sucursal
    ADD CONSTRAINT sucursal_empresa_fk FOREIGN KEY ( empresa_rut )
        REFERENCES empresa ( rut ) ON DELETE CASCADE;

--Seccion Trigger
CREATE OR REPLACE TRIGGER certificado_tg
    BEFORE INSERT ON certificado FOR EACH ROW
DECLARE
BEGIN
    IF :NEW.id_cert IS NULL THEN
        :new.id_cert := certificado_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER oferta_tg
    BEFORE INSERT ON oferta FOR EACH ROW
DECLARE 
BEGIN
    IF :NEW.id_oferta IS NULL THEN
        :new.id_oferta := oferta_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER detalleoferta_tg
    BEFORE INSERT ON detalle_oferta FOR EACH ROW
DECLARE
BEGIN
    IF :NEW.id_det IS NULL THEN
        :new.id_det := detalleoferta_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER lote_tg
    BEFORE INSERT ON lote FOR EACH ROW
DECLARE
BEGIN
IF :NEW.id_lote IS NULL THEN
        :new.id_lote := lote_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER msj_tg
    BEFORE INSERT ON mensajeria FOR EACH ROW
DECLARE
BEGIN
    IF :NEW.id_msj IS NULL THEN
        :new.id_msj := msj_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER producto_tg
    BEFORE INSERT ON producto FOR EACH ROW
DECLARE
BEGIN
    IF :NEW.id_prod IS NULL THEN
        :new.id_prod := producto_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER error_tg
    BEFORE INSERT ON reg_error FOR EACH ROW
DECLARE
BEGIN
    IF :NEW.id_error IS NULL THEN
        :new.id_error := error_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER caso_tg
    BEFORE INSERT ON reg_producto FOR EACH ROW
DECLARE
BEGIN
    IF :NEW.id_caso IS NULL THEN
        :new.id_caso := caso_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER sucursal_tg
    BEFORE INSERT ON sucursal FOR EACH ROW
DECLARE
BEGIN
    IF :NEW.id_sucur IS NULL THEN
        :new.id_sucur := sucursal_seq.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER certemitido_tg
    BEFORE INSERT ON certificado_emitido FOR EACH ROW
DECLARE
BEGIN
    IF :NEW.id_cemitido IS NULL THEN
        :new.id_cemitido := emitido_seq.NEXTVAL;
    END IF;
END;
/
--Seccion Insert
INSERT INTO persona VALUES('19308344-7','Gabriel','Espoz','Aliaga','Masculino','g.espoz@alumnos.duoc.cl',TO_DATE('30/04/1996','dd/mm/yyyy'));
INSERT INTO usuario VALUES('g.espoz','123','Consumidor');
INSERT INTO consumidor VALUES(0,'19308344-7','g.espoz');