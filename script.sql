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

CREATE TABLE certificado (
    id_cert              NUMBER(10) NOT NULL,
    pts_min              NUMBER(7) NOT NULL,
    pts_max              NUMBER(7) NOT NULL,
    descuento            NUMBER(3,1) NOT NULL,
    tope                 NUMBER(9) NOT NULL,
    rubro                VARCHAR2(150) NOT NULL,
    consumidor_id_cons   NUMBER(10) NOT NULL
);

ALTER TABLE certificado ADD CONSTRAINT certificado_pk PRIMARY KEY ( id_cert );

CREATE TABLE consumidor (
    id_cons           NUMBER(10) NOT NULL,
    puntos            NUMBER(6) NOT NULL,
    usuario_id_user   NUMBER(10) NOT NULL,
    persona_run       VARCHAR2(11) NOT NULL
);

ALTER TABLE consumidor ADD CONSTRAINT consumidor_pk PRIMARY KEY ( id_cons );

CREATE TABLE detalle_oferta (
    id_det               NUMBER(8) NOT NULL,
    img_boleta           BLOB NOT NULL,
    fec_valoracion       DATE NOT NULL,
    valoracion           NUMBER(3,1) NOT NULL,
    oferta_id_oferta     NUMBER(10) NOT NULL,
    consumidor_id_cons   NUMBER(10) NOT NULL
);

ALTER TABLE detalle_oferta
    ADD CONSTRAINT detalle_oferta_pk PRIMARY KEY ( oferta_id_oferta,
    consumidor_id_cons,
    id_det );

CREATE TABLE detalle_producto (
    producto_id_prod    NUMBER(12) NOT NULL,
    sucursal_id_sucur   NUMBER(8) NOT NULL,
    stk_sucur           NUMBER(7) NOT NULL
);

ALTER TABLE detalle_producto ADD CONSTRAINT detalle_producto_pk PRIMARY KEY ( producto_id_prod,
sucursal_id_sucur );

CREATE TABLE empleado (
    id_emp            NUMBER(10) NOT NULL,
    cargo             VARCHAR2(25) NOT NULL,
    persona_run       VARCHAR2(11) NOT NULL,
    usuario_id_user   NUMBER(10) NOT NULL
);

ALTER TABLE empleado ADD CONSTRAINT empleado_pk PRIMARY KEY ( id_emp );

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
    id_msj               NUMBER(12) NOT NULL,
    asunto               VARCHAR2(25) NOT NULL,
    mensaje              VARCHAR2(100) NOT NULL,
    cupon                BLOB,
    img_oferta           BLOB,
    consumidor_id_cons   NUMBER(10) NOT NULL,
    sucursal_id_sucur    NUMBER(8) NOT NULL,
    oferta_id_oferta     NUMBER(10) NOT NULL
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
    email     VARCHAR2(35),
    fec_nac   DATE NOT NULL
);

ALTER TABLE persona ADD CONSTRAINT persona_pk PRIMARY KEY ( run );

CREATE TABLE producto (
    id_prod       NUMBER(12) NOT NULL,
    nombre        VARCHAR2(25) NOT NULL,
    desc_prod     VARCHAR2(200) NOT NULL,
    fec_ingreso   DATE NOT NULL,
    estado        CHAR(1) NOT NULL,
    stk_seguro    NUMBER(7) NOT NULL,
    rubro         VARCHAR2(25) NOT NULL,
    desc_rubro    VARCHAR2(200) NOT NULL,
    valor         NUMBER(9) NOT NULL
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
    id_user    NUMBER(10) NOT NULL,
    username   VARCHAR2(25) NOT NULL,
    password   VARCHAR2(65) NOT NULL,
    perfil     VARCHAR2(25) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_user );

ALTER TABLE certificado
    ADD CONSTRAINT certificado_consumidor_fk FOREIGN KEY ( consumidor_id_cons )
        REFERENCES consumidor ( id_cons );

ALTER TABLE consumidor
    ADD CONSTRAINT consumidor_persona_fk FOREIGN KEY ( persona_run )
        REFERENCES persona ( run );

ALTER TABLE consumidor
    ADD CONSTRAINT consumidor_usuario_fk FOREIGN KEY ( usuario_id_user )
        REFERENCES usuario ( id_user );

ALTER TABLE detalle_oferta
    ADD CONSTRAINT detalle_oferta_consumidor_fk FOREIGN KEY ( consumidor_id_cons )
        REFERENCES consumidor ( id_cons );

ALTER TABLE detalle_oferta
    ADD CONSTRAINT detalle_oferta_oferta_fk FOREIGN KEY ( oferta_id_oferta )
        REFERENCES oferta ( id_oferta );

ALTER TABLE detalle_producto
    ADD CONSTRAINT detalle_producto_producto_fk FOREIGN KEY ( producto_id_prod )
        REFERENCES producto ( id_prod );

ALTER TABLE detalle_producto
    ADD CONSTRAINT detalle_producto_sucursal_fk FOREIGN KEY ( sucursal_id_sucur )
        REFERENCES sucursal ( id_sucur );

ALTER TABLE empleado
    ADD CONSTRAINT empleado_persona_fk FOREIGN KEY ( persona_run )
        REFERENCES persona ( run );

ALTER TABLE empleado
    ADD CONSTRAINT empleado_usuario_fk FOREIGN KEY ( usuario_id_user )
        REFERENCES usuario ( id_user );

ALTER TABLE lote
    ADD CONSTRAINT lote_producto_fk FOREIGN KEY ( producto_id_prod )
        REFERENCES producto ( id_prod );

ALTER TABLE mensajeria
    ADD CONSTRAINT mensajeria_consumidor_fk FOREIGN KEY ( consumidor_id_cons )
        REFERENCES consumidor ( id_cons );

ALTER TABLE mensajeria
    ADD CONSTRAINT mensajeria_oferta_fk FOREIGN KEY ( oferta_id_oferta )
        REFERENCES oferta ( id_oferta );

ALTER TABLE mensajeria
    ADD CONSTRAINT mensajeria_sucursal_fk FOREIGN KEY ( sucursal_id_sucur )
        REFERENCES sucursal ( id_sucur );

ALTER TABLE oferta
    ADD CONSTRAINT oferta_producto_fk FOREIGN KEY ( producto_id_prod )
        REFERENCES producto ( id_prod );

ALTER TABLE oferta
    ADD CONSTRAINT oferta_sucursal_fk FOREIGN KEY ( sucursal_id_sucur )
        REFERENCES sucursal ( id_sucur );

ALTER TABLE reg_error
    ADD CONSTRAINT reg_error_mensajeria_fk FOREIGN KEY ( mensajeria_id_msj )
        REFERENCES mensajeria ( id_msj );

ALTER TABLE reg_producto
    ADD CONSTRAINT reg_producto_producto_fk FOREIGN KEY ( producto_id_prod )
        REFERENCES producto ( id_prod );

ALTER TABLE sucursal
    ADD CONSTRAINT sucursal_empresa_fk FOREIGN KEY ( empresa_rut )
        REFERENCES empresa ( rut );