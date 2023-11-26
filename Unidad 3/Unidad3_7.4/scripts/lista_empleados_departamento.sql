CREATE OR REPLACE FUNCTION lista_emp_dpto(p_dept VARCHAR)
RETURNS SETOF empleados
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE 
    mi_empleado empleados;
BEGIN
    FOR mi_empleado IN
        SELECT empleados.* FROM public.empleados 
        JOIN public.departamentos d ON empleados.depno = departamentos.depno 
        WHERE lower(departamentos.nombre) LIKE lower(p_dept)
    LOOP
        RETURN NEXT mi_empleado;
    END LOOP;

    RETURN;
END;
$BODY$;