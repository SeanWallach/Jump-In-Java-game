import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GameBoardOneTest.class, GameBoardTwoTest.class, GameBoardZeroTest.class, TestBunny.class, TestFox.class })
public class GameBoardTests {

}
