INSERT INTO USUARIO VALUES ('LUIS', 'LUIS', 'CONSUMIDOR');
CREATE OR REPLACE PROCEDURE pl (
   cadena VARCHAR2
)
AS
BEGIN
  dbms_output.put_line(cadena);
END;
/

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

VARIABLE perfil VARCHAR2(50);

EXEC Login('LUIS', 'LUIS', :perfil);

EXEC pl('' || :perfil);

/*Primera version del procedimiento almacenado de insertar producto*/

CREATE OR REPLACE PROCEDURE AGREGAR_PRODUCTO(
    nombre        VARCHAR2,
    desc_prod     VARCHAR2,
    fec_ingreso   DATE,
    estado        CHAR,
    stk_seguro    NUMBER,
    rubro         VARCHAR2,
    desc_rubro    VARCHAR2,
    valor         NUMBER,
    v_respuesta OUT NUMBER
)
AS
BEGIN
    insert into PRODUCTO VALUES (PRODUCTO_SEQ.NEXTVAL,nombre,desc_prod,fec_ingreso,estado,stk_seguro,rubro,desc_rubro,valor);
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
CREATE OR REPLACE PROCEDURE BUSCAR_PRODUCTO(
    v_id NUMBER, 
    v_nombre        OUT VARCHAR2,
    v_desc_prod     OUT VARCHAR2,
    v_fec_ingreso   OUT DATE,
    v_estado        OUT CHAR,
    v_stk_seguro    OUT NUMBER,
    v_rubro         OUT VARCHAR2,
    v_desc_rubro    OUT VARCHAR2,
    v_valor         OUT NUMBER
)
AS
BEGIN
    SELECT NOMBRE, DESC_PROD, FEC_INGRESO,ESTADO,STK_SEGURO,RUBRO,DESC_RUBRO,VALOR
    INTO v_nombre, v_desc_prod, v_fec_ingreso, v_estado,v_stk_seguro,v_rubro,v_desc_rubro,v_valor
    FROM PRODUCTO
    WHERE ID_PROD = v_id;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_valor := 'ERROR';
      v_desc_prod := 'ERROR';
      v_fec_ingreso := TO_DATE('00/00/0000','DD/MM/YYYY');
      v_estado := 0;
      v_stk_seguro := 0;
      v_rubro := 'ERROR';
      v_desc_rubro := 'ERROR';
      v_valor := 0;
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
CREATE OR REPLACE PROCEDURE MODIFICAR_PRODUCTO(
    v_id          NUMBER,
    v_nombre        VARCHAR2,
    v_desc_prod     VARCHAR2,
    v_fec_ingreso   DATE,
    v_estado        CHAR,
    v_stk_seguro    NUMBER,
    v_rubro         VARCHAR2,
    v_desc_rubro    VARCHAR2,
    v_valor         NUMBER,
    v_respuesta OUT NUMBER
)
AS
BEGIN
    UPDATE PRODUCTO
    SET NOMBRE = v_nombre,DESC_PROD = v_desc_prod,FEC_INGRESO = v_fec_ingreso,ESTADO = v_estado,STK_SEGURO = v_stk_seguro, RUBRO = v_rubro, DESC_RUBRO = v_desc_rubro, VALOR = v_valor
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

