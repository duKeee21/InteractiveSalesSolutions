package example.service;

import example.api.CustomerSource;
import example.io.MyReader;
import example.io.MyWriterToFileTxt;
import example.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderCalculationServiceTest {

    @Mock
    private CustomerSource customersHash;

    @Mock
    private CustomerSource customersPipe;

    @Mock
    private MyReader myReader;

    @Mock
    private OrderExtractionService orderExtractionService;

    @Mock
    private MyWriterToFileTxt myWriterToFileTxt;

    @InjectMocks
    private OrderCalculationService orderCalculationService;

    private final DiscountConfig discountConfig = new DiscountConfig(50.0, 0.5, 0.05);
    private final String testFilePath = "test/file/path.txt";

    @Test
    void calculateOrders_WhenFirstLineContainsHash_ShouldUseHashAdapter() throws IOException {
        List<Customer> mockCustomers = createMockCustomers();

        when(myReader.readFirstLine(testFilePath)).thenReturn("2021-02-09T16:00:22#Industrial#8800");
        when(customersHash.readCustomers(testFilePath)).thenReturn(mockCustomers);
        when(orderExtractionService.extractOrder(any())).thenReturn(List.of(100.0, 200.0));

        orderCalculationService.calculateOrders(testFilePath, discountConfig);

        verify(customersHash).readCustomers(testFilePath);
        verifyNoInteractions(customersPipe);
        verify(myWriterToFileTxt).write(any());
    }

    @Test
    void calculateOrders_WhenFirstLineDoesNotContainHash_ShouldUsePipeAdapter() throws IOException {
        List<Customer> mockCustomers = createMockCustomers();

        when(myReader.readFirstLine(testFilePath)).thenReturn("2021-02-09T16:00:22|Industrial|8800");
        when(customersPipe.readCustomers(testFilePath)).thenReturn(mockCustomers);
        when(orderExtractionService.extractOrder(any())).thenReturn(List.of(100.0, 200.0));

        orderCalculationService.calculateOrders(testFilePath, discountConfig);

        verify(customersPipe).readCustomers(testFilePath);
        verifyNoInteractions(customersHash);
        verify(myWriterToFileTxt).write(any());
    }

    @Test
    void calculateOrders_ShouldSortCustomersByOrderDateDescending() throws IOException {
        List<Customer> unsortedCustomers = createUnsortedCustomers();

        when(myReader.readFirstLine(testFilePath)).thenReturn("2021-02-09T16:00:22#Industrial#8800");
        when(customersHash.readCustomers(testFilePath)).thenReturn(unsortedCustomers);
        when(orderExtractionService.extractOrder(any())).thenReturn(List.of(200.0, 100.0, 150.0));

        orderCalculationService.calculateOrders(testFilePath, discountConfig);

        verify(orderExtractionService).extractOrder(argThat(customers ->
                customers.size() == 3 &&
                        "Customer3".equals(customers.getFirst().getCompanyName()) &&
                        "Customer2".equals(customers.get(1).getCompanyName()) &&
                        "Customer1".equals(customers.get(2).getCompanyName())
        ));
    }

    @Test
    void calculateOrders_WhenReaderThrowsIOException_ShouldThrowsException() throws IOException {
        when(myReader.readFirstLine(testFilePath)).thenThrow(new IOException("error"));

        assertThrows(IOException.class, () ->
                orderCalculationService.calculateOrders(testFilePath, discountConfig));

        verifyNoInteractions(myWriterToFileTxt);
    }

    private List<Customer> createMockCustomers() {
        return List.of(
                new Customer(LocalDateTime.of(2023, 3, 1, 0, 0), "Customer1", 100.0),
                new Customer(LocalDateTime.of(2023, 2, 1, 0, 0), "Customer2", 150.0)
        );
    }

    private List<Customer> createUnsortedCustomers() {
        return List.of(
                new Customer(LocalDateTime.of(2023, 1, 1, 0, 0), "Customer1", 150.0),
                new Customer(LocalDateTime.of(2023, 2, 1, 0, 0), "Customer2", 100.0),
                new Customer(LocalDateTime.of(2023, 3, 1, 0, 0), "Customer3", 200.0)
        );
    }
}