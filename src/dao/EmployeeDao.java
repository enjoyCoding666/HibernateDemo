package dao;

import bean.Employee;
import org.hibernate.*;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Iterator;
import java.util.List;

/**
 * Created by lenovo on 2017/5/19.
 */
public class EmployeeDao {
    private static final SessionFactory sessionFactory;
    private static Transaction transaction;
    private  static  Session session;

    static {
        try {
            sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class).buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();                          //打开Session
    }

    public static void listEmployee() {
        session=getSession();
        try{
            transaction = session.beginTransaction();
            List employees = session.createQuery("FROM EmployeeBean").list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Employee employee = (Employee) iterator.next();
                System.out.print("First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
            }
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();                     //关闭Session
        }
    }
    //使用sql进行查询
    public  static void queryEmployee(){
        session=getSession();
        try{
             transaction=session.beginTransaction();
             String sql="select first_name,salary from Employee where first_name='lin'";
             NativeQuery query=session.createNativeQuery(sql);
             List<Object[]> workers=query.getResultList();
             for(Object[] worker:workers) {
                 String firstName=(String) worker[0];
                 int salary=(int)worker[1];
                 System.out.println("first_name:"+firstName+"  salary:"+salary);
             }
        }catch (Exception e) {
            if(transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    public  static void listEmployeeEntity(){
        session=getSession();
        try{
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
            System.out.println("executing over");
        }catch (HibernateException e) {
            if(transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


      public static Integer addEmployee(EmployeeDao employee){
            session = getSession();
            Integer employeeId=null;
            try {
               transaction=session.beginTransaction();      //开启事务
                employeeId=(Integer) session.save(employee);    // 保存对象，返回一个序列id
                transaction.commit();
            }catch (HibernateException e){
                if(transaction!=null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }finally {
                session.close();
            }
           return employeeId;

      }


    public static void updateEmployee(EmployeeDao employee){
        try{
            session = getSession();
            transaction=session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static void deleteEmployee(EmployeeDao employee){
        try{
            session = getSession();
            transaction=session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }



    public static void updateEmployeeById(Integer employeeId,int salary){
        try{
            session = getSession();
            transaction=session.beginTransaction();
            Employee employee=(Employee)session.get(Employee.class,employeeId);
            employee.setSalary(salary);
            session.update(employee);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

     public static void deleteEmployeeById(Integer employeeId){
          try{
              session = getSession();
              transaction=session.beginTransaction();
              Employee employee=session.get(Employee.class,employeeId);
              session.delete(employee);
              transaction.commit();
          }catch (HibernateException e) {
              if(transaction!=null) {
                  transaction.rollback();
              }
              e.printStackTrace();
          }finally {
              session.close();
          }
     }

}