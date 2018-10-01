INSERT INTO USUARIO VALUES (2, 'LUIS', 'LUIS', 'CONSUMIDOR');
CREATE OR REPLACE PROCEDURE pl (
   cadena VARCHAR2
)
AS
BEGIN
  dbms_output.put_line(cadena);
END;
/

CREATE OR REPLACE PROCEDURE LoginEmpleados(
    v_username VARCHAR2, v_pass VARCHAR2, resultado OUT VARCHAR2
)
AS
BEGIN
    SELECT perfil
    INTO resultado
    FROM USUARIO
    WHERE username = v_username and password = v_pass;
    
    EXCEPTION
    WHEN OTHERS THEN
      resultado := 'ERROR';
END;
/

VARIABLE resultado VARCHAR2(50);

EXEC LoginEmpleados('LUIS', 'LUS', :resultado);

EXEC pl('' || :resultado);
