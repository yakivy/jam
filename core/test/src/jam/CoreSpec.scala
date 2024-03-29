package jam

import jam.CustomSpec._
import org.scalatest.freespec.AnyFreeSpec

class CoreSpec extends AnyFreeSpec with CustomSpec {
    "Jam" - {
        "should brew objects" - {
            "in simple module" in {
                new {
                    val a = jam.brew[WithEmptyArgs]
                    val aa = jam.brew[WithEmptyArgList]
                    val b = jam.brew[WithSingleArg]
                    val c = jam.brew[WithTwoArgs]
                    val d = jam.brew[ParentObject.InObject]
                    val e = jam.brew[WithGenericAndPlainArgs[WithSingleArg]]
                    val f = jam.brew[WithTwoArgLists]
                    assert(c.a.eq(a) && c.b.eq(b) && b.a.eq(a))
                    assert(d.a.eq(a))
                    assert(e.a.eq(b) && e.b.eq(b))
                    assert(f.a.eq(aa) && f.b.eq(b) && f.c.eq(c))
                }
            }

            "in simple module with companion" in {
                new {
                    val a = jam.brew[WithEmptyArgs]
                    val b = jam.brew[WithSingleArg]
                    val c = jam.brew[WithTwoArgsCompanion]
                    assert(c.a.eq(a) && c.b.eq(b) && b.a.eq(a))
                }
            }

            "in simple module with type alias" in {
                new {
                    type A = WithEmptyArgs
                    val a = jam.brew[A]
                    val b = jam.brew[WithSingleArg]

                    assert(b.a.eq(a))
                }
            }

            "in case module" in {
                case class Module(a: WithEmptyArgs) {
                    val b = jam.brew[WithSingleArg]

                    assert(b.a.eq(a))
                }
                Module(new WithEmptyArgs)
            }

            "in simple module from argument" in {
                new {
                    object module {
                        val a = jam.brew[WithEmptyArgs]
                    }
                    val b = jam.brewFrom[WithSingleArg](module)
                    assert(b.a.eq(module.a))
                }
            }

            "in case module from argument" in {
                new {
                    val a = new WithEmptyArgs
                    val b = new WithEmptyArgs
                    case class Module(c: WithEmptyArgs = a)
                    val module = Module(b)
                    val d = jam.brewFromRec[WithSingleArg](module)
                    assert(d.a.eq(b))
                }
            }

            "in generic module from argument" in {
                new {
                    trait HasA[A] {
                        def a: A
                    }
                    object module extends HasA[WithEmptyArgs] {
                        val a: WithEmptyArgs = jam.brew[WithEmptyArgs]
                    }
                    val b = jam.brewFrom[WithSingleArg](module)
                    assert(b.a.eq(module.a))
                }
            }

            "in module with execution blocks" in {
                new {
                    val a1 = new WithEmptyArgs
                    val b = {
                        val a2 = new WithEmptyArgs
                        val b: WithSingleArg = jam.brewRec[WithSingleArg]
                        b
                    }
                    assert(b.a.eq(a1))
                }
            }

            "in composite module" in {
                trait Module {
                    def a: WithEmptyArgs
                    val b = jam.brew[WithSingleArg]
                }
                new Module {
                    def a = new WithEmptyArgs
                    val c = jam.brew[WithTwoArgs]
                    assert(!c.a.eq(b.a) && c.b.eq(b))
                }
            }

            "in module with string" in {
                new {
                    val a = "string"
                    val b = jam.brew[WithStringArg]
                    assert(b.a.eq(a))
                }
            }

            "with an annotated primary constructor" in {
                new {
                    val a = "string"
                    val b = jam.brew[WithAnnotatedConstructor]
                    assert(b.a == a.length)
                }
            }

            "with custom constructors" in {
                new {
                    val a = new WithEmptyArgs
                    val b = new WithSingleArg(a)

                    val cf = new WithCustomConstructors(_: WithEmptyArgs)
                    val c = jam.brewWith(cf.apply _)
                    val d = jam.brewWith(new WithCustomConstructors(_: WithSingleArg))
                    val e = jam.brewWith(new WithCustomConstructors(_: WithSingleArg, _: WithEmptyArgs))
                    val f = jam.brewWith(WithCustomConstructors.apply(_: WithEmptyArgs))
                    val g = jam.brewWith(WithCustomConstructors.apply(_: WithSingleArg))
                    val h = {
                        val aa = a
                        jam.brewWith(WithCustomConstructors.apply(_: WithSingleArg, aa))
                    }
                    val i = jam.brewWith(WithCustomConstructors.custom _)

                    assert(c.a.eq(a))
                    assert(d.a.eq(a))
                    assert(e.a.eq(a))
                    assert(f.a.eq(a))
                    assert(g.a.eq(a))
                    assert(h.a.eq(a))
                    assert(i.a.eq(a))
                }
            }

            "with private constructor" in {
                new {
                    val a = new WithEmptyArgs

                    val c = jam.brew[WithPrivateConstructor]

                    assert(c.a.eq(a))
                }
            }

            "with custom constructors from argument" in {
                new {
                    object module {
                        val a = new WithEmptyArgs
                    }
                    val c = jam.brewFromWith(module, new WithCustomConstructors(_: WithEmptyArgs))
                    assert(c.a.eq(module.a))
                }
            }

            "with argument with child" in {
                new {
                    def `null`: Null = null
                    def absurd: Nothing = ???
                    val a = jam.brew[WithChildChild]
                    val b = jam.brew[WithArgWithChild]
                    assert(b.a.eq(a))
                }
            }

            "in module with implicits" in {
                implicit val a = new WithSingleArg(new WithEmptyArgs)
                new {
                    val b = new WithEmptyArgs
                    val c = jam.brew[WithImplicitArgList]
                    assert(c.b.eq(a) && c.a.eq(b))
                }
            }

            "in module with implicits values" in {
                new {
                    val a = new WithEmptyArgs
                    val b = new WithSingleArg(a)
                    val c = jam.brew[WithImplicitArg]
                    assert(c.a.eq(a) && c.b.eq(b))
                }
            }

            "in simple module recursively" in {
                new {
                    val c = jam.brewRec[WithTwoArgLists]
                    assert(!c.b.a.eq(c.c.a) && !c.b.eq(c.c.b))
                }
            }

            "in simple module with companion recursively" in {
                new {
                    val c = jam.brewRec[WithTwoArgsCompanion]
                    assert(!c.a.eq(c.b.a))
                }
            }

            "in module with singleton recursively" in {
                new {
                    val a = new WithEmptyArgs
                    val c = jam.brewRec[WithTwoArgs]
                    assert(c.a.eq(c.b.a))
                }
            }

            "in module with singleton from argument recursively" in {
                new {
                    object module {
                        val a = new WithEmptyArgs
                    }
                    val c = jam.brewFromRec[WithTwoArgs](module)
                    assert(c.a.eq(module.a) && c.b.a.eq(module.a))
                }
            }

            "in module with prototype recursively" in {
                new {
                    def a = new WithEmptyArgs with Marked
                    val c = jam.brewRec[WithTwoArgs]
                    assert(!c.a.eq(c.b.a) && c.a.isInstanceOf[Marked] && c.b.a.isInstanceOf[Marked])
                }
            }

            "with custom constructors recursively" in {
                new {
                    val a = new WithEmptyArgs

                    val c = jam.brewWithRec(new WithCustomConstructors(_: WithEmptyArgs))
                    val d = jam.brewWithRec(new WithCustomConstructors(_: WithSingleArg))
                    val e = jam.brewWithRec(new WithCustomConstructors(_: WithSingleArg, _: WithEmptyArgs))
                    val f = jam.brewWithRec(WithCustomConstructors.apply(_: WithEmptyArgs))
                    val g = jam.brewWithRec(WithCustomConstructors.apply(_: WithSingleArg))
                    val h = jam.brewWithRec(WithCustomConstructors.apply(_: WithSingleArg, _: WithEmptyArgs))
                    val i = jam.brewWithRec(WithCustomConstructors.custom _)

                    assert(c.a.eq(a))
                    assert(d.a.eq(a))
                    assert(e.a.eq(a))
                    assert(f.a.eq(a))
                    assert(g.a.eq(a))
                    assert(h.a.eq(a))
                    assert(i.a.eq(a))
                }
            }

            "with custom constructors from argument recursively" in {
                new {
                    object module {
                        val a = new WithEmptyArgs
                    }
                    val c = jam.brewFromWithRec(module, new WithCustomConstructors(_: WithEmptyArgs))
                    assert(c.a.eq(module.a))
                }
            }

            "in module with prototype that has empty param lists recursively" in {
                new {
                    def a()()()()()() = new WithEmptyArgs
                    val c = jam.brewRec[WithTwoArgs]
                    assert(!c.a.eq(c.b.a))
                }
            }
        }

        "shouldn't brew objects" - {
            "with unresolved arguments" in {
                new {
                    assertCompilationErrorMessage(
                        assertCompiles("""jam.brew[WithSingleArg]"""),
                        "Unable to find instance for (jam.CustomSpec.WithSingleArg).a(jam.CustomSpec.WithEmptyArgs)",
                    )
                }
            }
            "with custom constructors and unresolved arguments" in {
                new {
                    assertCompilationErrorMessage(
                        assertCompiles("""jam.brewWith(WithCustomConstructors.apply(_: WithSingleArg))"""),
                        "Unable to find instance for (jam.CustomSpec.WithCustomConstructors).x$1(jam.CustomSpec.WithSingleArg)",
                        "Unable to find instance for (jam.CustomSpec.WithCustomConstructors)._$18(jam.CustomSpec.WithSingleArg)",
                    )
                }
            }
            "for traits" in {
                new {
                    assertCompilationErrorMessage(
                        assertCompiles("""jam.brew[Marked]"""),
                        "Unable to find public constructor or apply method for (jam.CustomSpec.Marked)",
                    )
                }
            }
            "with ambiguous constructor and unresolved arguments" in {
                new {
                    assertCompilationErrorMessage(
                        assertCompiles("""jam.brew[WithCustomConstructors]"""),
                        "More than one primary apply method was found for (jam.CustomSpec.WithCustomConstructors)",
                    )
                }
            }
            "with ambiguous constructor and unresolved arguments recursively" in {
                new {
                    assertCompilationErrorMessage(
                        assertCompiles("""jam.brewRec[WithCustomConstructors]"""),
                        "More than one primary apply method was found for (jam.CustomSpec.WithCustomConstructors)",
                    )
                }
            }
            "with ambiguous injection candidate" in {
                new {
                    val a = brew[WithEmptyArgs]
                    val b = brew[WithEmptyArgs]
                    assertCompilationErrorMessage(
                        assertCompiles("""jam.brewRec[WithSingleArg]"""),
                        "More than one injection candidate was found for " +
                            "(jam.CustomSpec.WithSingleArg).a(jam.CustomSpec.WithEmptyArgs): " +
                            "jam.CoreSpec.$anon.a(jam.CustomSpec.WithEmptyArgs), " +
                            "jam.CoreSpec.$anon.b(jam.CustomSpec.WithEmptyArgs)",
                        "More than one injection candidate was found for " +
                            "(jam.CustomSpec.WithSingleArg).a(jam.CustomSpec.WithEmptyArgs): " +
                            "jam.CoreSpec._$$anon.a(jam.CustomSpec.WithEmptyArgs), " +
                            "jam.CoreSpec._$$anon.b(jam.CustomSpec.WithEmptyArgs)",
                    )
                }
            }
            "with unresolved implicit arguments" in {
                new {
                    val a = brew[WithEmptyArgs]
                    assertCompilationErrorMessage(
                        assertCompiles("""jam.brewRec[WithImplicitArgList]"""),
                        "Unable to resolve implicit instance for " +
                            "(jam.CustomSpec.WithImplicitArgList).b(jam.CustomSpec.WithSingleArg)",
                    )
                }
            }
            "unsupported function type" in {
                new {
                    assertCompilationErrorMessage(
                        assertCompiles(
                            """jam.brewWith {
                              |    val f = new WithSingleArg(_: WithEmptyArgs)
                              |    f
                              |}""".stripMargin
                        ),
                        ".brewWith supports only anonymous functions as an argument, " +
                            "so try to extract your logic into a variable and trigger eta expansion like .brewWith(f.apply _)",
                        ".brewWith supports only anonymous functions as an argument, " +
                            "so try to extract your logic into a variable and trigger eta expansion like .brewWith(f.apply)",
                    )
                }
            }
        }
    }
}
