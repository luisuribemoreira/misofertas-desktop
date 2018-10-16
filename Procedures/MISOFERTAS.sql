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

CREATE OR REPLACE PROCEDURE insertProduc(
    id_prod       NUMBER,
    nombre        VARCHAR2,
    desc_prod     VARCHAR2,
    fec_ingreso   DATE,
    estado        CHAR,
    stk_seguro    NUMBER,
    rubro         VARCHAR2,
    desc_rubro    VARCHAR2,
    valor         NUMBER
)
AS
BEGIN
    insert into PRODUCTO 
    VALUES (id_prod,nombre,desc_prod,fec_ingreso,estado,stk_seguro,rubro,desc_rubro,valor);
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

