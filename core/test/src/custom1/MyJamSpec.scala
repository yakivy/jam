package custom1

import jam.CustomSpec
import jam.CustomSpec._
import custom2.myjam

class MyJamSpec extends CustomSpec {
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
                    val b = myjam.brew[WithSingleArg]
                    val c = myjam.brew[WithTwoArgs]
                    val d = myjam.brew[ParentObject.InObject]
                    val e = myjam.brew[WithGenericAndPlainArgs[WithSingleArg]]
                    assert(c.a.eq(a) && c.b.eq(b) && b.a.eq(a))
                    assert(d.a.eq(a))
                    assert(e.a.eq(b) && e.b.eq(b))
                }
            }
            "in simple module recursively" in {
                new {
                    val c = jam.brewRec[WithSingleBrewableArg]
                    assert(c.a != null)
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
