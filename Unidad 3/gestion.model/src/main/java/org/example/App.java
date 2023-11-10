package org.example;


import java.sql.SQLException;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException {

        miBD bd = new miBD("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/Empresa", "postgres", "iesbelen");

        List<String> departamentos = List.of(
                "id SERIAL PRIMARY KEY,"
                        + "nombre VARCHAR(100) NOT NULL,"
                        + "presupuesto DOUBLE PRECISION NOT NULL,"
                        + "gastos DOUBLE PRECISION NOT NULL"
        );
        bd.crearTabla("departamento", departamentos);
        List<String> empleados = List.of(
                "id SERIAL PRIMARY KEY,"
                        + "nif VARCHAR(9) NOT NULL UNIQUE,"
                        + "nombre VARCHAR(100) NOT NULL,"
                        + "apellido1 VARCHAR(100) NOT NULL,"
                        + "apellido2 VARCHAR(100),"
                        + "id_departamento INT REFERENCES departamento(id)"
        );
        bd.crearTabla("empleados", empleados);
        // Inserción de elementos en la tabla departamento.
        bd.insertar("departamento", "Desarrollo", 120000, 6000);
        bd.insertar("departamento", "Sistemas", 15000, 21000);
        bd.insertar("departamento", "Recursos Humanos", 28000, 25000);
        bd.insertar("departamento", "Contabilidad", 110000, 3000);
        bd.insertar("departamento", "I+D", 375000, 380000);
        bd.insertar("departamento", "Proyectos", 0, 0);
        bd.insertar("departamento", "Publicidad", 0, 1000);

        // Inserción de elementos en la tabla empleados.
        bd.insertar("empleados", "32481596F", "Aarón", "Rivero", "Gómez", 1);
        bd.insertar("empleados", "Y5575632D", "Adela", "Salas", "Díaz", 2);
        bd.insertar("empleados", "R6970642B", "Adolfo", "Rubio", "Flores", 3);
        bd.insertar("empleados", "77705545E", "Adrián", "Suárez", null, 4);
        bd.insertar("empleados", "17087203C", "Marcos", "Loyola", "Méndez", 5);
        bd.insertar("empleados", "38382980M", "María", "Santana", "Moreno", 1);
        bd.insertar("empleados", "80576669X", "Pilar", "Ruiz", null, 2);
        bd.insertar("empleados", "71651431Z", "Pepe", "Ruiz", "Santana", 3);
        bd.insertar("empleados", "56399183D", "Juan", "Gómez", "López", 2);
        bd.insertar("empleados", "46384486H", "Diego", "Flores", "Salas", 5);
        bd.insertar("empleados", "67389283A", "Marta", "Herrera", "Gil", 1);
        bd.insertar("empleados", "41234836R", "Irene", "Salas", "Flores", null);
        bd.insertar("empleados", "82635162B", "Juan Antonio", "Sáez", "Guerrero", null);

        // Consultas.

        bd.consultas("SELECT apellido1 from empleados");
        bd.consultas("SELECT DISTINCT apellido1 FROM empleados");
        bd.consultas("SELECT nombre, gastos FROM departamento ORDER BY gastos LIMIT 2");
        bd.consultas("SELECT nombre, presupuesto FROM departamento WHERE presupuesto >= 150000");
        bd.consultas("SELECT empleados.*, departamento.nombre AS nombre_departamento\n" + "FROM empleados\n" + "JOIN departamento ON empleados.id_departamento = departamento.id");
        bd.consultas("SELECT empleados.*, departamento.* FROM empleados JOIN departamento ON empleados.id_departamento = departamento.id ORDER BY departamento.nombre, empleados.apellido1, empleados.nombre");
        bd.consultas("SELECT id, nombre FROM departamento WHERE id IN (SELECT DISTINCT id FROM empleados)");
        bd.consultas("SELECT departamento.nombre FROM empleados JOIN departamento ON empleados.id_departamento = departamento.id WHERE empleados.nif = '38382980M'");
        bd.consultas("SELECT SUM(presupuesto) AS suma_presupuesto FROM departamento");


    }


}
