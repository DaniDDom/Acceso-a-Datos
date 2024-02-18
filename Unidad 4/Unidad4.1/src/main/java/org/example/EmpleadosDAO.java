package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class EmpleadosDAO {
    private final SessionFactory sessionFactory;
    static Scanner teclado = new Scanner(System.in);

    public EmpleadosDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void obtenerTodosEmpleados() {
        try (Session session = sessionFactory.openSession()) {
            Query<EmpleadosEntity> query = session.createQuery("FROM org.example.EmpleadosEntity");
            List<EmpleadosEntity> listaEmpleados = query.list();
            for (Object obj :
                    listaEmpleados) {
                EmpleadosEntity empleado = (EmpleadosEntity) obj;
                System.out.printf("Número : %d Nombre : %s Puesto :  ", empleado.getEmpno(), empleado.getNombre(), empleado.getPuesto());
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarEmpleado() {
        System.out.println("Inserte el número de empleado a actualizar: ");
        int numEmpleado = teclado.nextInt();
        try (Session session = sessionFactory.openSession()) {
            Query<EmpleadosEntity> miQuery = session.createQuery("from org.example.EmpleadosEntity" + "where empno='" + String.valueOf(numEmpleado) + "'");
            List<EmpleadosEntity> listaEmpleados = miQuery.list();
            Transaction transaction = session.beginTransaction();
            if (listaEmpleados.size() > 0) {
                EmpleadosEntity empleado = (EmpleadosEntity) listaEmpleados.get(0);
                System.out.println("Introduzca el nuevo nombre");
                String nombre = teclado.nextLine();
                empleado.setNombre(nombre);
                System.out.println("Introduzca el nuevo puesto");
                String puesto = teclado.nextLine();
                empleado.setPuesto(puesto);
                session.update(puesto);
                transaction.commit();
            } else {
                System.out.println("Departamento no encontrado.");
            }
        }
    }

    public void insertarEmpleado() {
        Scanner teclado = new Scanner(System.in);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            EmpleadosEntity empleado = new EmpleadosEntity();
            System.out.println("Nombre del empleado");
            empleado.setNombre(teclado.nextLine());
            System.out.println("Puesto del empleado");
            empleado.setPuesto(teclado.nextLine());
            System.out.println("Departamento del empleado");
            empleado.setDepno(teclado.nextInt());
            session.save(empleado);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarEmpleado(int numEmpleado) {
        try (Session session = sessionFactory.openSession()) {
            Query<EmpleadosEntity> miQuery = session.createQuery("from org.example.EmpleadosEntity" + "where empno='" + String.valueOf(numEmpleado) + "'");
            List<EmpleadosEntity> listaEmpleados = miQuery.list();
            if (listaEmpleados.size() > 0) {
                Transaction transaction = session.beginTransaction();
                session.delete(listaEmpleados.get(0));
                transaction.commit();
                System.out.println("Se ha eliminado el empleado");
            } else {
                System.out.println("No se ha encontrado el empleado");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public  void gestionarEmpleados() {
        String menuEmpleados = "------------- MENÚ EMPLEADOS -------------\n" +
                "1. Mostrar empleados\n" +
                "2. Actualizar empleado\n" +
                "3. Añadir empleado\n" +
                "4. Borrar empleado\n" +
                "5. SALIR\n" +
                "-> ";
        int opcion;
        EmpleadosDAO crud = new EmpleadosDAO(sessionFactory);
        do {
            System.out.println(menuEmpleados);
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1 -> crud.obtenerTodosEmpleados();
                case 2 -> crud.actualizarEmpleado();
                case 3 -> crud.insertarEmpleado();
                case 4 ->  {
                    System.out.println("Introduzca el empleado a borrar.");
                    int numeroEmpleado = teclado.nextInt();
                    crud.borrarEmpleado(numeroEmpleado);
                }
                case 5 -> System.out.println("Volviendo al menú principal");
            }
        } while (opcion != 5);
    }
}
