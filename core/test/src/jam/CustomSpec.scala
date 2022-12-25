package jam

import javax.inject.Inject
import org.scalactic.source.Position
import org.scalatest.Assertion
import org.scalatest.exceptions.TestFailedException
import org.scalatest.freespec.AnyFreeSpec

object CustomSpec {
    trait Marked
    class WithEmptyArgs
    class WithSingleArg(val a: WithEmptyArgs)
    class WithTwoArgs(val a: WithEmptyArgs, val b: WithSingleArg)
    class WithChild
    class WithChildChild extends WithChild
    class WithArgWithChild(val a: WithChild)
    class WithImplicitArgList(val a: WithEmptyArgs)(implicit val b: WithSingleArg)
    class WithImplicitArg(val a: WithEmptyArgs, implicit val b: WithSingleArg)
    object ParentObject {
        class InObject(val a: WithEmptyArgs)
    }
    class WithCustomConstructors(val a: WithEmptyArgs) {
        def this(a: WithSingleArg) = this(a.a)
        def this(a: WithSingleArg, b: WithEmptyArgs) = this(b)
    }
    object WithCustomConstructors {
        def apply(a: WithEmptyArgs): WithCustomConstructors = new WithCustomConstructors(a)
        def apply(a: WithSingleArg): WithCustomConstructors = new WithCustomConstructors(a)
        def apply(a: WithSingleArg, b: WithEmptyArgs): WithCustomConstructors = new WithCustomConstructors(
            a, b)
        def custom(a: WithEmptyArgs, b: WithEmptyArgs): WithCustomConstructors = apply(a)
    }
    class WithGenericAndPlainArgs[A](val a: A, val b: WithSingleArg)
    class WithStringArg(val a: String)
    class WithAnnotatedConstructor(val a: Int) {
        @Inject()
        def this(a: String) = this(a.length)
        def this(a: Double) = this(a.toInt)
    }
    class WithBrewablePattern
    class WithSingleBrewableArg(val a: WithBrewablePattern)
}


class CustomSpec extends AnyFreeSpec {
    def assertCompilationErrorMessage(
        compilesAssert: => Assertion,
        message: String,
        alternativeMessages: String*,
    )(implicit
        pos: Position,
    ): Assertion = {
        try {
            compilesAssert
            fail("Compilation was successful")
        } catch {
            case e: TestFailedException =>
                val messages = (message :: alternativeMessages.toList).map(m => s""""$m"""")
                val candidateMessage = messages.find(e.getMessage.contains(_)).getOrElse(messages.head)
                assert(e.getMessage().contains(candidateMessage))
        }
    }
}
