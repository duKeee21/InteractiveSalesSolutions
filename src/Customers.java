import java.util.Date;

public class Customers {
    private Date orderTime;
    private String nameOfCompany;
    private int companyMoney;

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public int getCompanyMoney() {
        return companyMoney;
    }

    public void setCompanyMoney(int companyMoney) {
        this.companyMoney = companyMoney;
    }
}
