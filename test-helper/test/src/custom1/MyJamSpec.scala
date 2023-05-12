package custom1

import custom2.myjam
import jam.CustomSpec
import jam.CustomSpec._
import org.scalatest.freespec.AnyFreeSpec

class MyJamSpec extends AnyFreeSpec with CustomSpec {
    "Jam used in a different package" - {
        "should brew objects" - {
            "in simple module" in {
                new {
                    val a = jam.brew[WithEmptyArgs]
                    val b = jam.brew[WithSingleArg]
                    assert(b.a.eq(a))
                }
            }
        }
    }
    "My Jam" - {
        "should brew objects" - {
            "in simple module" in {
                new {
                    val a = myjam.brew[WithEmptyArgs]
                    val aa = myjam.brew[WithEmptyArgList]
                    val b = myjam.brew[WithSingleArg]
                    val c = myjam.brew[WithTwoArgs]
                    val d = myjam.brew[ParentObject.InObject]
                    val e = myjam.brew[WithGenericAndPlainArgs[WithSingleArg]]
                    val f = myjam.brew[WithTwoArgLists]
                    assert(c.a.eq(a) && c.b.eq(b) && b.a.eq(a))
                    assert(d.a.eq(a))
                    assert(e.a.eq(b) && e.b.eq(b))
                    assert(f.a.eq(aa) && f.b.eq(b) && f.c.eq(c))
                }
            }
            "in simple module recursively" in {
                new {
                    val c = myjam.brewRec[WithSingleBrewableArg]
                    assert(c.a != null)
                }
            }
            "in simple module in context" in {
                new {
                    val a = Option(myjam.brew[WithBrewablePattern])
                    val c = myjam.brewRecF[Option][WithSingleBrewableArg]
                    assert(c.get.a.eq(a.get))
                }
            }
        }
        "shouldn't brew objects" - {
            "if implicit config is not accessible at compile time" in {
                new {
                    implicit val brokenConfig: jam.JamConfig = new jam.JamConfig("")
                    assertCompilationErrorMessage(
                        assertCompiles("""jam.brewRec[WithEmptyArgs]"""),
                        "Unable to eval JamConfig at compile time. " +
                            "Provided value should be `macro def` and in " +
                            "`: c.Tree = c.universe.reify(new JamConfig(...)).tree` format",
                        "Unable to eval JamConfig at compile time. " +
                            "Provided value should be `inline def` and in `= JamConfig(...)` format",
                    )
                }
            }
            "recursively if argument doesn't match jam.JamConfig.brewRecPattern" in {
                new {
                    assertCompilationErrorMessage(
                        assertCompiles("""myjam.brewRec[WithSingleArg]"""),
                        "Recursive brewing for instance (jam.CustomSpec.WithSingleArg).a(jam.CustomSpec.WithEmptyArgs) is prohibited from config. " +
                            "jam.CustomSpec.WithEmptyArgs doesn't match (?i).*brewable.* regex.",
                    )
                }
            }
        }
    }
}
