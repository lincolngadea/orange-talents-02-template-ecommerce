package io.orange.mercadolivre.registerProduct;

import io.orange.mercadolivre.registerCategory.Category;
import io.orange.mercadolivre.registerDetails.NewDetailsRequest;
import io.orange.mercadolivre.registerUser.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class ProductTest {

    @MethodSource("generate")
    @ParameterizedTest
    @DisplayName("Must create product with various types of details")
    void test(int expected, List<NewDetailsRequest> newDetails) throws Exception {
        Category category = new Category("category");
        NewProductRequest request = new NewProductRequest("name", BigDecimal.TEN, 1, "description", category.getId(), newDetails);

        Assertions.assertEquals(expected, request.searchEqualDetails().size());
    }

    static Stream<Arguments> generate() {
        return Stream.of(
                Arguments.of(0, List.of()),
                Arguments.of(0, List.of(new NewDetailsRequest("key", "value"))),
                Arguments.of(0, List.of(new NewDetailsRequest("key", "value"), new NewDetailsRequest("key1", "value1"))),
                Arguments.of(1, List.of(
                        new NewDetailsRequest("key", "value"),
                        new NewDetailsRequest("key", "value")))
        );
    }

    @ParameterizedTest
    @DisplayName("A product must have at least 3 details!")
    @MethodSource("testGenerate1")
    void test1(Collection<NewDetailsRequest> details) throws Exception {
        Category category = new Category("category");
        UserAccount usernameAuth = new UserAccount("email@email.com.br", "123456");

        new Product("name", BigDecimal.TEN, 1, "description", category, usernameAuth, details);
    }

    static Stream<Arguments> testGenerate1() {
        return Stream.of(
                Arguments.of(
                        List.of(new NewDetailsRequest("key1", "value1"),
                                new NewDetailsRequest("key2", "value2"),
                                new NewDetailsRequest("key3", "value3"))),
                Arguments.of(List.of(
                        new NewDetailsRequest("key1", "value1"),
                        new NewDetailsRequest("key2", "value2"),
                        new NewDetailsRequest("key3", "value3"),
                        new NewDetailsRequest("key4", "value4"))));
    }

    @ParameterizedTest
    @DisplayName("A product cannot be less than 3 characters!")
    @MethodSource("testGenerate2")
    void test2(Collection<NewDetailsRequest> details) throws Exception {
        Category category = new Category("category");
        UserAccount usernameAuth = new UserAccount("email@email.com.br", "123456");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Product("name", BigDecimal.TEN, 1, "description", category, usernameAuth, details);
        });
    }

    static Stream<Arguments> testGenerate2() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new NewDetailsRequest("key1", "value1"),
                                new NewDetailsRequest("key2", "value2"))),
                Arguments.of(
                        List.of(new NewDetailsRequest("key1", "value1"))));
    }
}