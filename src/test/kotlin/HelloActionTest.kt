import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HelloActionTest {

    private lateinit var helloAction: HelloAction

    @BeforeEach
    fun configureSystemUnderTest() {
        helloAction = HelloAction()
    }

    @Test
    fun test1() {
        assertThat("Hello World!").isEqualTo("Hello World!")
        assertThat(helloAction.isLineAdable("       s")).isEqualTo(true)
    }
}
