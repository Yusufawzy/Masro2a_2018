package IS_IT_LOST;
import java.util.Vector;
public class ReportDB{
    private  Vector <String> Reports;
    Vector <String> getAllReports(){return Reports;}
}
interface IReport{
    void AddReport (String msg,User u,Vector<User> v,Vector<String> r) ;
    void RmoveReport();
    void showOptions();
}
class ReportService implements IReport{
    ReportDB reportDB;
    public void AddReport(String msg, User u, Vector <User> v, Vector <String> r){
        r.add(msg);
        for (int i = 0 ; i < v.size();i++){
            if (v.elementAt(i)==u){
                v.elementAt(i).setReportCount( v.elementAt(i).getReportCount() + 1);
                break;
            }
        }
    }
    public void RmoveReport(){

    }

    @Override
    public void showOptions() {

    }
}
