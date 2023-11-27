import org.example.entidades.DepartamentosEntidades;
import org.example.entidades.EmpleadosEntidades;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class app {

    static Session abrirSession() throws Exception {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session == null) {
            throw new Exception("Error abriendo la sesión");
        } else {
            System.out.println("Conectado.");
        }

        return session;
    }

    private static void cargarEmpleados() {
        try {
            Session session = abrirSession();
            Query<EmpleadosEntidades> miQuery = session.createQuery("from org.example.entidades.EmpleadosEntidades");
            List<EmpleadosEntidades> listaEmpleados = miQuery.list();
            System.out.println("EMPLEADOS");
            for (Object obj : listaEmpleados) {
                EmpleadosEntidades empleado = (EmpleadosEntidades) obj;
                System.out.printf("Número :  %d\tNombre: %s\n", empleado.getEmpno(), empleado.getNombre());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void cargarDepartamentos() {
        try {
            Session session = abrirSession();
            Query<DepartamentosEntidades> miQuery = session.createQuery("from org.example.entidades.DepartamentosEntidades");
            List<DepartamentosEntidades> listaDepartamentos = miQuery.list();
            System.out.println("DEPARTAMENTOS");
            for (Object obj : listaDepartamentos) {
                DepartamentosEntidades departamento = (DepartamentosEntidades) obj;
                System.out.printf("Número :  %d\tNombre: %s\t Ubicación: %s \n", departamento.getDepno(), departamento.getNombre(),departamento.getUbicacion());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        cargarEmpleados();
        try {
            Thread.sleep (2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cargarDepartamentos();


    }

}
