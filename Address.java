
public class Address {
    private String country;
    private String governate;
    private String street;
    private int building;
    Address(){}
    Address(String country, String governate, String street, int building) {
        this.setCountry(country);
        this.setGovernate(governate);
        this.setStreet(street);
        this.setBuilding(building);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGovernate() {
        return governate;
    }

    public void setGovernate(String governate) {
        this.governate = governate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }
    static boolean checkPalindrome(int arr[],int start,int end){
        if (arr[start]!=arr[end]){
            return false;
        }
        if (start>end) return true;
        return checkPalindrome(arr,start+1,end-1);

    }

    public static void main(String[] args) {
        int [] arr = new int[]{1,2,1,1,2,1};
        System.out.println(checkPalindrome(arr,0,5));
    }
}
