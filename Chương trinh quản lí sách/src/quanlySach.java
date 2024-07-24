import java.util.ArrayList;

public class quanlySach  {
    private ArrayList<Sach> list;
    public quanlySach() {
        list= new ArrayList<Sach>();
    }
    
    public boolean themSach(Sach s1) {
        if(list.contains(s1)) return false;
	list.add(s1);
	return true;
    }

    public boolean xoaSach(int maSach) {
        Sach s1= new Sach(maSach);
        if(list.contains(s1)) {
            list.remove(s1);
            return true;
	}
	return false;
    }

    public ArrayList<Sach> getList(){
        return list;
    }
}	
