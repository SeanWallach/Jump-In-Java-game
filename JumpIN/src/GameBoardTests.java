import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GameBoardOneTest.class, GameBoardTwoTest.class, GameBoardZeroTest.class, TestFox.class, TestBunny.class })
public class GameBoardTests {

}