package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class DepartamentoDAO {
    private final SessionFactory sessionFactory;
    static Scanner teclado = new Scanner(System.in);

    public DepartamentoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearDepartamento(DepartamentosEntity departamento) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(departamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public DepartamentosEntity obtenerDepartamento(int depno) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(DepartamentosEntity.class, depno);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarDepartamento(DepartamentosEntity departamento) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(departamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void eliminarDepartamento(int depno) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            DepartamentosEntity departamento = session.get(DepartamentosEntity.class, depno);
            if (departamento != null) {
                session.delete(departamento);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void obtenerTodosDepartamentos() {
        try (Session session = sessionFactory.openSession()) {
            Query<DepartamentosEntity> query = session.createQuery("FROM org.example.DepartamentosEntity");
            List<DepartamentosEntity> listaDepartamentos = query.list();
            for (Object obj :
                    listaDepartamentos) {
                DepartamentosEntity departamento = (DepartamentosEntity) obj;
                System.out.printf("Número : %d Nombre : %s Ubicación: %s", departamento.getDepno(), departamento.getNombre(), departamento.getUbicacion());
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void actualizarDepartamento() {
        try (Session session = sessionFactory.openSession()) {
            System.out.println("Inserte el número de departamento a actualizar: ");
            int numDepartamento = teclado.nextInt();
            Query<DepartamentosEntity> miQuery = session.createQuery("from org.example.EmpleadosEntity" + "where empno='"+ String.valueOf(numDepartamento) +"'");
            List<DepartamentosEntity> listaDepartamentos = miQuery.list();
            Transaction transaction = session.beginTransaction();
           if(listaDepartamentos.size() > 0 ) {
               DepartamentosEntity departamento = (DepartamentosEntity) listaDepartamentos.get(0);
               System.out.println("Introduzca la nueva ubicación");
               String ubicacion = teclado.nextLine();
               departamento.setUbicacion(ubicacion);
               System.out.println("Introduzca el nuevo nombre");
               String nombre = teclado.nextLine();
               departamento.setNombre(nombre);
               session.update(departamento);
               transaction.commit();
           } else {
               System.out.println("Departamento no encontrado.");
           }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertarDepartamento() {
        Scanner teclado = new Scanner(System.in);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            DepartamentosEntity departamento = new DepartamentosEntity();
            System.out.println("Nombre del departamento");
            departamento.setNombre(teclado.nextLine());
            System.out.println("Ubicación del departamento");
            departamento.setUbicacion(teclado.nextLine());
            session.save(departamento);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void borrarDepartamento(int numDepartamento) {
        try (Session session = sessionFactory.openSession()) {
            Query<DepartamentosEntity> miQuery = session.createQuery("from org.example.DepartamentosEntity" + "where empno='" + String.valueOf(numDepartamento) + "'");
            List<DepartamentosEntity> listaDepartamentos = miQuery.list();
            if (listaDepartamentos.size() > 0) {
                Transaction transaction = session.beginTransaction();
                session.delete(listaDepartamentos.get(0));
                transaction.commit();
                System.out.println("Se ha eliminado el departamento");
            } else {
                System.out.println("No se ha encontrado el departamento.");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public  void gestionarDepartamentos() {
        String menuDepartamentos = "------------- MENÚ DEPARTAMENTOS -------------\n" +
                "1. Mostrar departamentos\n" +
                "2. Actualizar departamento\n" +
                "3. Añadir departamento\n" +
                "4. Borrar departamento\n" +
                "5. SALIR\n" +
                "-> ";
        int opcion;
        DepartamentoDAO crud = new DepartamentoDAO(sessionFactory);
        do {
            System.out.println(menuDepartamentos);
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1 -> crud.obtenerTodosDepartamentos();
                case 2 -> crud.actualizarDepartamento();
                case 3 -> insertarDepartamento();
                case 4 -> {
                    System.out.println("Introduzca el número de departamento a borrar");
                    int numero = teclado.nextInt();
                    borrarDepartamento(numero);
                }

                case 5 -> System.out.println("Volviendo al menú principal");
            }
        } while (opcion != 5);
    }
}
