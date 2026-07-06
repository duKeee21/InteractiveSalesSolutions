import java.io.IOException;

public interface SourceOfOrders {

    void Read () throws IOException;

    void Data ();

    void CompanyName ();

    void CompanyMoney();

}