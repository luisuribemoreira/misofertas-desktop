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
