package example.adapter;

import example.io.MyReader;
import example.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdapterPipeTest {

    @InjectMocks
    private AdapterPipe adapterPipe;

    @Mock
    private MyReader myReader;
    private final String TEST_PATH = "test/path/file.txt";
    private List<String> mockLines;


    @BeforeEach
    void setUp() {
        mockLines = Arrays.asList(
                "2021-01-01T16:00:22|Company1|1111",
                "2022-02-02T08:42:59|Company2|2222",
                "2023-03-03T10:48:34|Company3|3333"
        );
    }


    @Test
    void readCustomers_ShouldReturnNotNull() throws IOException {


        when(myReader.readLines(TEST_PATH)).thenReturn(mockLines);

        List<Customer> result = adapterPipe.readCustomers(TEST_PATH);

        assertNotNull(result);
        assertEquals(3, result.size());


    }

    @Test
    void readCustomers_ShouldReturnCorrectCustomers() throws IOException {

        when(myReader.readLines(TEST_PATH)).thenReturn(mockLines);

        List<Customer> result = adapterPipe.readCustomers(TEST_PATH);

        Customer first = result.getFirst();
        assertEquals(LocalDateTime.parse("2021-01-01T16:00:22"), first.getOrderDate());
        assertEquals("Company1", first.getCompanyName());
        assertEquals(1111.0, first.getOrderCount());

        Customer second = result.get(1);
        assertEquals(LocalDateTime.parse("2022-02-02T08:42:59"), second.getOrderDate());
        assertEquals("Company2", second.getCompanyName());
        assertEquals(2222.0, second.getOrderCount());

        Customer third = result.get(2);
        assertEquals(LocalDateTime.parse("2023-03-03T10:48:34"), third.getOrderDate());
        assertEquals("Company3", third.getCompanyName());
        assertEquals(3333.0, third.getOrderCount());

        verify(myReader, times(1)).readLines(TEST_PATH);

    }

    @Test
    void readCustomers_ShouldThrowAccessDeniedException_WhenPathIsEmpty() throws IOException {
        when(myReader.readLines("")).thenThrow(new AccessDeniedException("Must be path"));

        assertThrows(AccessDeniedException.class, () -> adapterPipe.readCustomers(""));
        verify(myReader, times(1)).readLines("");
    }

    @Test
    void readCustomers_ShouldThrowNullPointerException_WhenPathIsNull() throws IOException {
        when(myReader.readLines(null)).thenThrow(new NullPointerException("Path cannot be null"));

        assertThrows(NullPointerException.class, () -> adapterPipe.readCustomers(null));
        verify(myReader, times(1)).readLines(null);
    }

}