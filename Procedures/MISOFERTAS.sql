INSERT INTO USUARIO VALUES (2, 'LUIS', 'LUIS', 'CONSUMIDOR');
CREATE OR REPLACE PROCEDURE pl (
   cadena VARCHAR2
)
AS
BEGIN
  dbms_output.put_line(cadena);
END;
/

CREATE OR REPLACE PROCEDURE Login(
    v_username VARCHAR2, v_pass VARCHAR2, v_perfil OUT VARCHAR2, v_id OUT NUMBER
)
AS
BEGIN
    SELECT perfil, id_user
    INTO v_perfil, v_id
    FROM USUARIO
    WHERE username = v_username and password = v_pass;
    
    EXCEPTION
    WHEN OTHERS THEN
      v_perfil := 'ERROR';
      v_id := 0;
END;
/

VARIABLE perfil VARCHAR2(50);
VARIABLE v_id NUMBER;

EXEC Login('LUIS', 'LUIS', :perfil, :v_id);

EXEC pl('' || :perfil || :v_id);

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


