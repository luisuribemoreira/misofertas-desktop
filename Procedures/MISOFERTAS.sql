

CREATE OR REPLACE PROCEDURE Login(
    v_username VARCHAR2, v_pass VARCHAR2, v_perfil OUT VARCHAR2
)
AS
BEGIN
    SELECT perfil
    INTO v_perfil
    FROM USUARIO
    WHERE username = v_username and password = v_pass;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_perfil := 'ERROR';
END;
/


/*Primera version del procedimiento almacenado de insertar producto*/

create or replace PROCEDURE AGREGAR_PRODUCTO(
    nombre        VARCHAR2,
    desc_prod     VARCHAR2,
    fec_ingreso   DATE,
    estado        CHAR,
    stk_seguro    NUMBER,
    stk_sucur     NUMBER,
    rubro         VARCHAR2,
    desc_rubro    VARCHAR2,
    valor         NUMBER,
    id_sucursal   NUMBER,
    v_respuesta OUT NUMBER
)
AS
BEGIN
    insert into PRODUCTO VALUES (PRODUCTO_SEQ.NEXTVAL,nombre,desc_prod,fec_ingreso,estado,stk_seguro,stk_sucur,rubro,desc_rubro,valor,id_sucursal);
     v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/
/*Probando el pl de insertar producto*/
EXEC AGREGAR_PRODUCTO('Celular','Producto tecnologico', TO_DATE('10/10/2018','DD/MM/YYYY'),1,50,'Tecnologia','Productos tecnologicos',50000, :v_respuesta);
/
/*Primera version del procedimiento almacenado de buscar producto*/
create or replace PROCEDURE BUSCAR_PRODUCTO(
    v_id NUMBER, 
    v_nombre        OUT VARCHAR2,
    v_desc_prod     OUT VARCHAR2,
    v_fec_ingreso   OUT DATE,
    v_estado        OUT CHAR,
    v_stk_seguro    OUT NUMBER,
    v_stk_sucur     OUT NUMBER,
    v_rubro         OUT VARCHAR2,
    v_desc_rubro    OUT VARCHAR2,
    v_valor         OUT NUMBER,
    v_id_sucursal   OUT NUMBER
)
AS
BEGIN
    SELECT NOMBRE, DESC_PROD, FEC_INGRESO,ESTADO,STK_SEGURO,STK_SUCUR,RUBRO,DESC_RUBRO,VALOR,SUCURSAL_ID_SUCUR
    INTO v_nombre, v_desc_prod, v_fec_ingreso, v_estado,v_stk_seguro,v_stk_sucur,v_rubro,v_desc_rubro,v_valor,v_id_sucursal
    FROM PRODUCTO
    WHERE ID_PROD = v_id;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_valor := 'ERROR';
      v_desc_prod := 'ERROR';
      v_fec_ingreso := TO_DATE('00/00/0000','DD/MM/YYYY');
      v_estado := 0;
      v_stk_seguro := 0;
      v_stk_sucur := 0;
      v_rubro := 'ERROR';
      v_desc_rubro := 'ERROR';
      v_valor := 0;
      v_id_sucursal := 0;
END;
/
/*Primera version del procedimiento almacenado de eliminar producto*/
CREATE OR REPLACE PROCEDURE ELIMINAR_PRODUCTO(
    v_id NUMBER, v_respuesta OUT NUMBER
)
AS
BEGIN
    DELETE FROM PRODUCTO
    WHERE ID_PROD = v_ID;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/
/*Primera version del procedimiento almacenado de modificar producto*/
create or replace PROCEDURE MODIFICAR_PRODUCTO(
    v_id          NUMBER,
    v_nombre        VARCHAR2,
    v_desc_prod     VARCHAR2,
    v_fec_ingreso   DATE,
    v_estado        CHAR,
    v_stk_seguro    NUMBER,
    v_stk_sucur     NUMBER,
    v_rubro         VARCHAR2,
    v_desc_rubro    VARCHAR2,
    v_valor         NUMBER,
    v_id_sucur      NUMBER,
    v_respuesta OUT NUMBER
)
AS
BEGIN
    UPDATE PRODUCTO
    SET NOMBRE = v_nombre,DESC_PROD = v_desc_prod,FEC_INGRESO = v_fec_ingreso,ESTADO = v_estado,STK_SEGURO = v_stk_seguro,STK_SUCUR = v_stk_sucur, RUBRO = v_rubro, DESC_RUBRO = v_desc_rubro, VALOR = v_valor,SUCURSAL_ID_SUCUR = v_id_sucur    
    WHERE ID_PROD = v_id;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;

/
-- Procedimientos Almacenados la GESTIÓN DE EMPRESAS

CREATE OR REPLACE PROCEDURE BUSCAR_EMPRESA(
    v_rut VARCHAR2, v_nombre OUT VARCHAR2, v_direccion OUT VARCHAR2, v_razon_social OUT VARCHAR2
)
AS
BEGIN
    SELECT nombre, direccion, razon_social
    INTO v_nombre, v_direccion, v_razon_social
    FROM EMPRESA
    WHERE rut = v_rut;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_nombre := 'ERROR';
      v_direccion := 'ERROR';
      v_razon_social := 'ERROR';
END;
/

CREATE OR REPLACE PROCEDURE BUSCAR_EMPRESA_REF(
    v_id VARCHAR2, v_rut OUT VARCHAR2, v_nombre OUT VARCHAR2, v_direccion OUT VARCHAR2, v_razon_social OUT VARCHAR2
)
AS
BEGIN
    SELECT rut, nombre, direccion, razon_social
    INTO v_rut, v_nombre, v_direccion, v_razon_social
    FROM EMPRESA
    WHERE SUBSTR(rut,0,8) = v_id;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_nombre := 'ERROR';
      v_direccion := 'ERROR';
      v_razon_social := 'ERROR';
END;
/

CREATE OR REPLACE PROCEDURE AGREGAR_EMPRESA(
    v_rut VARCHAR2, v_nombre VARCHAR2, v_direccion VARCHAR2, v_razon_social VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    INSERT INTO EMPRESA VALUES (v_rut, v_nombre, v_direccion, v_razon_social);
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE MODIFICAR_EMPRESA(
    v_rut VARCHAR2, v_nombre VARCHAR2, v_direccion VARCHAR2, v_razon_social VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    UPDATE EMPRESA
    SET NOMBRE = v_nombre, DIRECCION = v_direccion, RAZON_SOCIAL = v_razon_social
    WHERE RUT = v_rut;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE ELIMINAR_EMPRESA(
    v_rut VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    DELETE FROM EMPRESA
    WHERE RUT = v_rut;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/


-- Procedimientos Almacenados la GESTIÓN DE SUCURSALES

CREATE OR REPLACE PROCEDURE BUSCAR_SUCURSAL(
    v_id NUMBER, v_nombre OUT VARCHAR2, v_direccion OUT VARCHAR2, v_fono OUT VARCHAR2, v_comuna OUT VARCHAR2, v_empresa_rut OUT VARCHAR2
)
AS
BEGIN
    SELECT nombre, direccion, fono, comuna, empresa_rut
    INTO v_nombre, v_direccion, v_fono, v_comuna, v_empresa_rut
    FROM SUCURSAL
    WHERE id_sucur = v_id;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_nombre := 'ERROR';
      v_direccion := 'ERROR';
      v_fono := 'ERROR';
      v_comuna := 'ERROR';
      v_empresa_rut := 'ERROR';
END;
/

CREATE OR REPLACE PROCEDURE AGREGAR_SUCURSAL(
    v_nombre VARCHAR2, v_direccion VARCHAR2, v_fono VARCHAR2, v_comuna VARCHAR2, v_empresa_rut VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    INSERT INTO SUCURSAL VALUES (SUCURSAL_SEQ.NEXTVAL, v_nombre, v_direccion, v_fono, v_comuna, v_empresa_rut);
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE MODIFICAR_SUCURSAL(
    v_id NUMBER, v_nombre VARCHAR2, v_direccion VARCHAR2, v_fono VARCHAR2, v_comuna VARCHAR2, v_empresa_rut VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    UPDATE SUCURSAL
    SET NOMBRE = v_nombre, DIRECCION = v_direccion, FONO = v_fono, COMUNA = v_comuna, EMPRESA_RUT = v_empresa_rut
    WHERE ID_SUCUR = v_id;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE ELIMINAR_SUCURSAL(
    v_id NUMBER, v_respuesta OUT NUMBER
)
AS
BEGIN
    DELETE FROM SUCURSAL
    WHERE ID_SUCUR = v_id;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/


-- Procedimientos Almacenados la GESTIÓN DE USUARIOS

CREATE OR REPLACE PROCEDURE BUSCAR_USUARIO(
    v_user VARCHAR2, v_pass OUT VARCHAR2, v_perfil OUT VARCHAR2
)
AS
BEGIN
    SELECT PASSWORD, PERFIL
    INTO v_pass, v_perfil
    FROM USUARIO
    WHERE USERNAME = v_user AND NOT PERFIL = 'CONSUMIDOR';
    
    EXCEPTION
    WHEN OTHERS THEN
      v_pass := 'ERROR';
      v_perfil := 'ERROR';
END;
/

CREATE OR REPLACE PROCEDURE BUSCAR_PERSONA_USER(
    v_user VARCHAR2, v_run OUT VARCHAR2, v_nombre OUT VARCHAR2, v_app OUT VARCHAR2, v_apm OUT VARCHAR2, v_sexo OUT VARCHAR2, v_email OUT VARCHAR2, v_fecha OUT DATE
)
AS
    lrun VARCHAR2(15);
BEGIN
    SELECT PERSONA_RUN
    INTO lrun
    FROM EMPLEADO
    WHERE USUARIO_USERNAME = v_user;
    
    SELECT RUN, NOMBRE, PATERNO, MATERNO, SEXO, EMAIL, FEC_NAC
    INTO v_run, v_nombre, v_app, v_apm, v_sexo, v_email, v_fecha
    FROM PERSONA
    WHERE run = lrun;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_run := 'ERROR';
      v_nombre := 'ERROR';
      v_app := 'ERROR';
      v_apm := 'ERROR';
      v_sexo := 'ERROR';
      v_email := 'ERROR';
      v_fecha := TO_DATE('01/01/0001','DD-MM-YYYY');
END;
/

CREATE OR REPLACE PROCEDURE BUSCAR_PERSONA(
    v_run VARCHAR2, v_nombre OUT VARCHAR2, v_app OUT VARCHAR2, v_apm OUT VARCHAR2, v_sexo OUT VARCHAR2, v_email OUT VARCHAR2, v_fecha OUT DATE
)
AS
BEGIN
    
    SELECT NOMBRE, PATERNO, MATERNO, SEXO, EMAIL, FEC_NAC
    INTO v_nombre, v_app, v_apm, v_sexo, v_email, v_fecha
    FROM PERSONA
    WHERE run = v_run;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_nombre := 'ERROR';
      v_app := 'ERROR';
      v_apm := 'ERROR';
      v_sexo := 'ERROR';
      v_email := 'ERROR';
      v_fecha := TO_DATE('01/01/0001','DD-MM-YYYY');
END;
/

CREATE OR REPLACE PROCEDURE BUSCAR_EMPLEADO(
    v_run VARCHAR2, v_user OUT VARCHAR2, v_cargo OUT VARCHAR2, v_id OUT NUMBER
)
AS
BEGIN
    
    SELECT USUARIO_USERNAME, CARGO, IDREFERENCIA
    INTO v_user, v_cargo, v_id
    FROM EMPLEADO
    WHERE PERSONA_RUN = v_run;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_user := 'ERROR';
      v_cargo := 'ERROR';
END;
/

CREATE OR REPLACE PROCEDURE AGREGAR_USUARIO(
    v_user VARCHAR2, v_pass VARCHAR2, v_perfil VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    INSERT INTO USUARIO VALUES (v_user, v_pass, v_perfil);
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE AGREGAR_PERSONA(
    v_run VARCHAR2, v_nombre VARCHAR2, v_app VARCHAR2, v_apm VARCHAR2, v_sexo VARCHAR2, v_email VARCHAR2, v_fecha DATE, v_respuesta OUT NUMBER
)
AS
BEGIN
    INSERT INTO PERSONA VALUES (v_run, v_nombre, v_app, v_apm, v_sexo, v_email,v_fecha);
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE AGREGAR_EMPLEADO(
    v_user VARCHAR2, v_run VARCHAR2, v_cargo VARCHAR2, v_ref NUMBER, v_respuesta OUT NUMBER
)
AS
BEGIN
    INSERT INTO EMPLEADO VALUES (v_ref, v_cargo, v_run , v_user);
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE MODIFICAR_USUARIO(
    v_user VARCHAR2, v_pass VARCHAR2, v_perfil VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    UPDATE USUARIO
    SET PASSWORD = v_pass, PERFIL = v_perfil
    WHERE USERNAME = v_user;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE MODIFICAR_PERSONA(
    v_run VARCHAR2, v_nombre VARCHAR2, v_app VARCHAR2, v_apm VARCHAR2, v_email VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    UPDATE PERSONA
    SET NOMBRE = v_nombre, PATERNO = v_app, MATERNO = v_apm, EMAIL = v_email
    WHERE RUN = v_run;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE MODIFICAR_EMPLEADO(
    v_user VARCHAR2, v_run VARCHAR2, v_cargo VARCHAR2, v_idreferencia NUMBER, v_respuesta OUT NUMBER
)
AS
BEGIN
    UPDATE EMPLEADO
    SET CARGO = v_cargo, IDREFERENCIA = v_idreferencia
    WHERE PERSONA_RUN = v_run and USUARIO_USERNAME = v_user;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE ELIMINAR_USUARIO(
    v_user VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    DELETE FROM USUARIO
    WHERE USERNAME = v_user;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

/*Primera version del procedimiento almacenado de BUSCAR_OFERTA*/
CREATE OR REPLACE PROCEDURE BUSCAR_OFERTA(
    v_id NUMBER,
    v_descripcion         OUT VARCHAR2,
    v_fec_inicio          OUT DATE,
    v_fec_termino         OUT DATE,
    v_img_oferta          OUT BLOB,
    v_valoracion_total    OUT NUMBER,
    v_porc_desc           OUT NUMBER,
    v_sucursal_id_sucur   OUT NUMBER,
    v_producto_id_prod    OUT NUMBER
)
AS
BEGIN
    SELECT DESCRIPCION, FEC_INICIO, FEC_TERMINO, IMG_OFERTA, VALORACION_TOTAL,PORC_DESC,SUCURSAL_ID_SUCUR,PRODUCTO_ID_PROD
    INTO v_descripcion, v_fec_inicio, v_fec_termino, v_img_oferta, v_valoracion_total,v_porc_desc,v_sucursal_id_sucur,v_producto_id_prod
    FROM OFERTA
    WHERE ID_OFERTA = v_id;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_descripcion := 'ERROR';
      v_fec_inicio := TO_DATE('00/00/0000','DD/MM/YYYY');
      v_fec_termino := TO_DATE('00/00/0000','DD/MM/YYYY');
      v_img_oferta := null;
      v_valoracion_total := 0;
      v_porc_desc := 0;
      v_sucursal_id_sucur := 0;
      v_producto_id_prod := 0;
END;

/

/*Primera version del procedimineto almacenado de AGREGAR_OFERTA*/

create or replace PROCEDURE AGREGAR_OFERTA(
    v_descripcion         VARCHAR2,
    v_fec_inicio          DATE,
    v_fec_termino         DATE,
    v_img_oferta          BLOB,
    v_valoracion_total    NUMBER,
    v_porc_desc           NUMBER,
    v_sucursal_id_sucur   NUMBER,
    v_producto_id_prod    NUMBER,
    v_respuesta           OUT NUMBER,
    v_id_oferta           OUT NUMBER
)
AS
BEGIN
    INSERT INTO OFERTA VALUES (OFERTA_SEQ.NEXTVAL, v_descripcion, v_fec_inicio, v_fec_termino, v_img_oferta, v_valoracion_total,v_porc_desc,v_sucursal_id_sucur,v_producto_id_prod)
    returning ID_OFERTA INTO v_id_oferta;
    
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/
/*Primera version del procedimiento almacenado de ELIMINAR_OFERTA*/
CREATE OR REPLACE PROCEDURE ELIMINAR_OFERTA(
    v_id NUMBER, v_respuesta OUT NUMBER
)
AS
BEGIN
    DELETE FROM OFERTA
    WHERE ID_OFERTA = v_id;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;

/
/*Primera version del procedimiento almacenado de modificar oferta*/


CREATE OR REPLACE PROCEDURE MODIFICAR_OFERTA(
    v_id                  NUMBER,
    v_descripcion         VARCHAR2,
    v_fec_inicio          DATE,
    v_fec_termino         DATE,
    v_img_oferta          BLOB,
    v_valoracion_total    NUMBER,
    v_porc_desc           NUMBER,
    v_sucursal_id_sucur   NUMBER,
    v_producto_id_prod    NUMBER,
    v_respuesta OUT NUMBER
)
AS
BEGIN
    UPDATE OFERTA
    SET DESCRIPCION = v_descripcion,FEC_INICIO = v_fec_inicio,FEC_TERMINO = v_fec_termino,IMG_OFERTA = v_img_oferta,VALORACION_TOTAL = v_valoracion_total,
    PORC_DESC = v_porc_desc, SUCURSAL_ID_SUCUR = v_sucursal_id_sucur, PRODUCTO_ID_PROD = v_producto_id_prod
    WHERE ID_OFERTA = v_id;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/



-- Procedimientos Almacenados la GESTIÓN DE CERTIFICADOS (DESCUENTOS)

CREATE OR REPLACE PROCEDURE BUSCAR_CERTIFICADO(
    v_id NUMBER, v_pts_min OUT NUMBER, v_pts_max OUT NUMBER, v_descuento OUT NUMBER, v_tope OUT NUMBER, v_rubro OUT VARCHAR2
)
AS
BEGIN
    SELECT pts_min, pts_max, descuento, tope, rubro
    INTO v_pts_min, v_pts_max, v_descuento, v_tope, v_rubro
    FROM CERTIFICADO
    WHERE id_cert = v_id;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_pts_min := -1;
      v_pts_max := -1;
      v_descuento := -1;
      v_tope := -1;
      v_rubro := 'ERROR';
END;
/

CREATE OR REPLACE PROCEDURE AGREGAR_CERTIFICADO(
    v_pts_min NUMBER, v_pts_max NUMBER, v_descuento NUMBER, v_tope NUMBER, v_rubro VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    INSERT INTO CERTIFICADO VALUES (CERTIFICADO_SEQ.NEXTVAL, v_pts_min, v_pts_max, v_descuento, v_tope, v_rubro);
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE MODIFICAR_CERTIFICADO(
    v_id NUMBER, v_pts_min NUMBER, v_pts_max NUMBER, v_descuento NUMBER, v_tope NUMBER, v_rubro VARCHAR2, v_respuesta OUT NUMBER
)
AS
BEGIN
    UPDATE CERTIFICADO
    SET PTS_MIN = v_pts_min, PTS_MAX = v_pts_max, DESCUENTO = v_descuento, TOPE = v_tope, RUBRO = v_rubro
    WHERE ID_CERT = v_id;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;
/

CREATE OR REPLACE PROCEDURE ELIMINAR_CERTIFICADO(
    v_id NUMBER, v_respuesta OUT NUMBER
)
AS
BEGIN
    DELETE FROM CERTIFICADO
    WHERE ID_CERT = v_id;
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;

/
/*Procedimiento almacenado para dejar registro en la base de datos del envio de un correo electronico*/
create or replace PROCEDURE AGREGAR_MENSAJERIA(
    v_asunto           VARCHAR2,
    v_mensaje          VARCHAR2,
    v_cupon            BLOB,
    v_img_oferta       BLOB,
    v_sucursal_id      NUMBER,
    v_oferta_id        NUMBER,
    v_consumidor_name  VARCHAR2,
    v_consumidor_run   VARCHAR2,
    v_respuesta        OUT NUMBER
)
AS
BEGIN
    INSERT INTO MENSAJERIA VALUES (MSJ_SEQ.NEXTVAL, v_asunto, v_mensaje, v_cupon, v_img_oferta, v_sucursal_id,v_oferta_id,v_consumidor_name,v_consumidor_run);
    
    v_respuesta := 1;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_respuesta := 0;
END;



/*Procedimiento almacenaod para buscar un usuario por username*/
create or replace PROCEDURE BUSCAR_EMPLEADO_LOGEADO(
    v_user VARCHAR2,
    v_run OUT VARCHAR2,
    v_cargo OUT VARCHAR2,
    v_id OUT NUMBER
)
AS
BEGIN
    
    SELECT PERSONA_RUN, CARGO, IDREFERENCIA
    INTO v_run, v_cargo, v_id
    FROM EMPLEADO
    WHERE USUARIO_USERNAME = v_user;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_run := 'ERROR';
      v_cargo := 'ERROR';
END;