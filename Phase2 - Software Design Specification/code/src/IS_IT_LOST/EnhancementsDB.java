package IS_IT_LOST;

import java.util.Vector;

public class EnhancementsDB{
    private Vector <String> Enhancements;
    Vector<String> getAllEnhancements(){
        return Enhancements;
    }
}
interface  IEnhancement{
    void AddEnhancement ();
    void RemoveEnhancement();
    void UpdateEnhancement();
    void showOptions();
}
class EnahncementService implements  IEnhancement{
    EnhancementsDB enhancementsDB;
    public void AddEnhancement(){}
    public void RemoveEnhancement(){}
    public void UpdateEnhancement(){}

    @Override
    public void showOptions() {

    }


}