import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.Mockito.spy;
import java.util.Random;
import java.util.stream.Stream;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.*;
 import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TestDemoTest {

    TestDemo testDemo;

    @BeforeEach
    void setUp() {
        testDemo = new TestDemo();

    }

    @ParameterizedTest
    @MethodSource("TestDemoTest#argumentsForAddPositive")
    void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b,
                                                        int expected, boolean expectException) {
        if(expectException) {
            Assertions.assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);

        } else {
            assertThatThrownBy( () -> testDemo.addPositive(a,b) )
                    .isInstanceOf(IllegalArgumentException.class);
        }

    }
    static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
                arguments(2, 4, 6, true),
                arguments(4,4,8, true),
                arguments(5,5,10, true),
                arguments( -4,4,10, false)
        );
    }

    @Test
    void assertThatnumberSquaredIscorrect() {
        TestDemo mockDemo = spy(testDemo);
        doReturn(5).when(mockDemo).randomNumberSquared();
        int fiveSquared = mockDemo.randomNumberSquared();
        Assertions.assertThat(fiveSquared).isEqualTo(5);
    }
}