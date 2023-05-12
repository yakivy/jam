package jam

import javax.inject.Inject
import org.scalactic.source.Position
import org.scalatest.Assertion
import org.scalatest.Assertions
import org.scalatest.exceptions.TestFailedException
import org.scalatest.freespec.AnyFreeSpec

object CustomSpec {
    trait Marked
    class WithEmptyArgs
    class WithEmptyArgList()
    class WithSingleArg(val a: WithEmptyArgs)
    class WithTwoArgs(val a: WithEmptyArgs, val b: WithSingleArg)

    class WithTwoArgsCompanion private (val a: WithEmptyArgs, val b: WithSingleArg)
    object WithTwoArgsCompanion {
        def apply(a: WithEmptyArgs, b: WithSingleArg): WithTwoArgsCompanion = new WithTwoArgsCompanion(a, b)
    }

    class WithTwoArgLists(val a: WithEmptyArgList)(val b: WithSingleArg, val c: WithTwoArgs)

    class WithTwoArgListsInOptionCompanion private (val a: WithEmptyArgs)(val b: WithSingleArg, val c: WithTwoArgsCompanion)
    object WithTwoArgListsInOptionCompanion {
        def apply(a: WithEmptyArgs)(b: WithSingleArg, c: WithTwoArgsCompanion): Option[WithTwoArgListsInOptionCompanion] =
            Option(new WithTwoArgListsInOptionCompanion(a)(b, c))
    }

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
        def this(a: WithSingleArg, b: Option[WithEmptyArgs]) = this(b.getOrElse(a.a))
    }
    class WithPrivateConstructor(val a: WithEmptyArgs) {
        private def this(a: WithSingleArg) = this(a.a)
    }
    object WithCustomConstructors {
        def apply(a: WithEmptyArgs): WithCustomConstructors = new WithCustomConstructors(a)
        def apply(a: WithSingleArg): WithCustomConstructors = new WithCustomConstructors(a)
        def apply(a: WithSingleArg, b: WithEmptyArgs): WithCustomConstructors = new WithCustomConstructors(a, b)
        def custom(a: WithEmptyArgs, b: WithEmptyArgs): WithCustomConstructors = apply(a)
        def option(a: WithEmptyArgs): Option[WithCustomConstructors] = Option(apply(a))
    }
    class WithGenericAndPlainArgs[A](val a: A, val b: WithSingleArg)

    class WithGenericAndPlainArgsCompanion[A] private (val a: A, val b: WithSingleArg)
    object WithGenericAndPlainArgsCompanion {
        def apply[A](a: A, b: WithSingleArg): WithGenericAndPlainArgsCompanion[A] =
            new WithGenericAndPlainArgsCompanion(a, b)
    }

    class WithStringArg(val a: String)
    class WithAnnotatedConstructor(val a: Int) {
        @Inject()
        def this(a: String) = this(a.length)
        def this(a: Double) = this(a.toInt)
    }
    class WithBrewablePattern
    class WithSingleBrewableArg(val a: WithBrewablePattern)
}

trait CustomSpec extends Assertions {
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
