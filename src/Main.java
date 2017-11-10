import dao.EmployeeDao;

/**
 * Created by lenovo on 2017/5/19.
 */
public class Main {
    public static void main(final String[] args) throws Exception {
        EmployeeDao employeeDao=new EmployeeDao();
//        employeeDao.addEmployee("lin","rui",10000);
//        employeeDao.addEmployee("chen","bin",5000);
//        employeeDao.listEmployeeEntity();
//        employeeDao.listEmployee();
         employeeDao.queryEmployee();

    }
}
