public class Sach {
    private int maSach;
    private String tenSach;
    private String tacGia;
    private int namXB;
    private String nhaXB;
    private int soTrang;
    private double giaTien;
    
    public Sach(int maSach,String tenSach,String tacGia,int namXB,String nhaXB,int soTrang,double giaTien){
        super();
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.nhaXB = nhaXB;
        this.soTrang = soTrang;
        this.giaTien = giaTien;	
    }
    
    public Sach(int maSach){
        this(maSach,"","",0,"",0,0);
    }
    
    public Sach() {
        this(0);
    }
    
    public int hashCode() {
        final int prime=31;
        int result=1;
        result = prime*result+maSach;
        return result;
    }
    
    public boolean equals(Object obj) {
        if(this==obj) return true;
	if(obj==null) return false;
	if(getClass()!= obj.getClass()) return false;
	Sach other=(Sach) obj;
        if(maSach!=other.maSach) return false;
	return true;
    }
    
    public String toString() {
        return maSach+";"+tenSach+";"+tacGia+";"+namXB+";"+nhaXB+";"+soTrang+";"+giaTien;
    }
    
    public int getMaSach() {
        return maSach;
    }
    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }
    
    public String getTuaSach() {
        return tenSach;
    }
    public void setTuaSach(String tuaSach) {
        this.tenSach = tuaSach;
    }
    
    public String getTacGia() {
        return tacGia;
    }
    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
    
    public int getNamXB() {
        return namXB;
    }
    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }
    
    public String getNhaXB() {
        return nhaXB;
    }
    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }
    
    public int getSoTrang() {
        return soTrang;
    }
    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }
    
    public double getDonGia() {
        return giaTien;
    }
    public void setDonGia(double donGia) {
        this.giaTien = donGia;
    }
}