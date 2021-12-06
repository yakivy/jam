package jam

import javax.inject.Inject
import org.scalatest.freespec.AnyFreeSpec

class JamSpec extends AnyFreeSpec {
    "Jam" - {
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

        "should brew" - {

            "objects for simple module" in {
                new {
                    val a = brew[WithEmptyArgs]
                    val b = brew[WithSingleArg]
                    val c = brew[WithTwoArgs]
                    val d = brew[ParentObject.InObject]
                    val e = brew[WithGenericAndPlainArgs[WithSingleArg]]
                    assert(c.a.eq(a) && c.b.eq(b) && b.a.eq(a))
                    assert(d.a.eq(a))
                    assert(e.a.eq(b) && e.b.eq(b))
                }
            }

            "objects for simple module from argument" in {
                new {
                    object module {
                        val a = brew[WithEmptyArgs]
                    }
                    val b = brewFrom[WithSingleArg](module)
                    assert(b.a.eq(module.a))
                }
            }

            "objects for generic module from argument" in {
                new {
                    trait HasA[A] {
                        def a: A
                    }
                    object module extends HasA[WithEmptyArgs] {
                        val a: WithEmptyArgs = brew[WithEmptyArgs]
                    }
                    val b = brewFrom[WithSingleArg](module)
                    assert(b.a.eq(module.a))
                }
            }

            "objects for module with execution blocks" in {
                new {
                    val a1 = new WithEmptyArgs
                    val b = {
                        val a2 = new WithEmptyArgs
                        val b: WithSingleArg = brewRec[WithSingleArg]
                        b
                    }
                    assert(b.a.eq(a1))
                }
            }

            "objects for composite module" in {
                trait Module {
                    def a: WithEmptyArgs
                    val b = brew[WithSingleArg]
                }
                new Module {
                    def a = new WithEmptyArgs
                    val c = brew[WithTwoArgs]
                    assert(!c.a.eq(b.a) && c.b.eq(b))
                }
            }

            "objects for module with string" in {
                new {
                    val a = "string"
                    val b = brew[WithStringArg]
                    assert(b.a.eq(a))
                }
            }

            "objects for classes with an annotated primary constructor" in {
                new {
                    val a = "string"
                    val b = brew[WithAnnotatedConstructor]
                    assert(b.a == a.length)
                }
            }

            "objects with custom constructors" in {
                new {
                    val a = new WithEmptyArgs
                    val b = new WithSingleArg(a)

                    val c = brewWith(new WithCustomConstructors(_: WithEmptyArgs))
                    val d = brewWith(new WithCustomConstructors(_: WithSingleArg))
                    val e = brewWith(new WithCustomConstructors(_: WithSingleArg, _: WithEmptyArgs))
                    val f = brewWith(WithCustomConstructors.apply(_: WithEmptyArgs))
                    val g = brewWith(WithCustomConstructors.apply(_: WithSingleArg))
                    val h = brewWith(WithCustomConstructors.apply(_: WithSingleArg, _: WithEmptyArgs))
                    val i = brewWith(WithCustomConstructors.custom _)

                    assert(c.a.eq(a))
                    assert(d.a.eq(a))
                    assert(e.a.eq(a))
                    assert(f.a.eq(a))
                    assert(g.a.eq(a))
                    assert(h.a.eq(a))
                    assert(i.a.eq(a))
                }
            }

            "objects with custom constructors from argument" in {
                new {
                    object module {
                        val a = new WithEmptyArgs
                    }
                    val c = brewWithFrom(new WithCustomConstructors(_: WithEmptyArgs))(module)
                    assert(c.a.eq(module.a))
                }
            }

            "objects with argument with child" in {
                new {
                    def `null`: Null = null
                    def absurd: Nothing = ???
                    val a = brew[WithChildChild]
                    val b = brew[WithArgWithChild]
                    assert(b.a.eq(a))
                }
            }

            "objects for module with implicits" in {
                implicit val a = new WithSingleArg(new WithEmptyArgs)
                new {
                    val b = new WithEmptyArgs
                    val c = brew[WithImplicitArgList]
                    assert(c.b.eq(a) && c.a.eq(b))
                }
            }

            "objects for module with implicits values" in {
                new {
                    val a = new WithEmptyArgs
                    val b = new WithSingleArg(a)
                    val c = brew[WithImplicitArg]
                    assert(c.a.eq(a) && c.b.eq(b))
                }
            }

            "object for simple module recursivelly" in {
                new {
                    val c = brewRec[WithTwoArgs]
                    assert(!c.a.eq(c.b.a))
                }
            }

            "object trees for module with singleton" in {
                new {
                    val a = new WithEmptyArgs
                    val c = brewRec[WithTwoArgs]
                    assert(c.a.eq(c.b.a))
                }
            }

            "object for module with singleton from argument recursivelly" in {
                new {
                    object module {
                        val a = new WithEmptyArgs
                    }
                    val c = brewFromRec[WithTwoArgs](module)
                    assert(c.a.eq(module.a) && c.b.a.eq(module.a))
                }
            }

            "object trees for module with prototype" in {
                new {
                    def a = new WithEmptyArgs with Marked
                    val c = brewRec[WithTwoArgs]
                    assert(!c.a.eq(c.b.a) && c.a.isInstanceOf[Marked] && c.b.a.isInstanceOf[Marked])
                }
            }

            "object with custom constructors recursivelly" in {
                new {
                    val a = new WithEmptyArgs

                    val c = brewWithRec(new WithCustomConstructors(_: WithEmptyArgs))
                    val d = brewWithRec(new WithCustomConstructors(_: WithSingleArg))
                    val e = brewWithRec(new WithCustomConstructors(_: WithSingleArg, _: WithEmptyArgs))
                    val f = brewWithRec(WithCustomConstructors.apply(_: WithEmptyArgs))
                    val g = brewWithRec(WithCustomConstructors.apply(_: WithSingleArg))
                    val h = brewWithRec(WithCustomConstructors.apply(_: WithSingleArg, _: WithEmptyArgs))
                    val i = brewWithRec(WithCustomConstructors.custom _)

                    assert(c.a.eq(a))
                    assert(d.a.eq(a))
                    assert(e.a.eq(a))
                    assert(f.a.eq(a))
                    assert(g.a.eq(a))
                    assert(h.a.eq(a))
                    assert(i.a.eq(a))
                }
            }

            "object with custom constructors from argument recursivelly" in {
                new {
                    object module {
                        val a = new WithEmptyArgs
                    }
                    val c = brewWithFromRec(new WithCustomConstructors(_: WithEmptyArgs))(module)
                    assert(c.a.eq(module.a))
                }
            }

            "object trees for module with prototype that has empty param lists" in {
                new {
                    def a()()()()()() = new WithEmptyArgs
                    val c = brewRec[WithTwoArgs]
                    assert(!c.a.eq(c.b.a))
                }
            }
        }

        "shouldn't brew" - {
            "objects with unresolved arguments" in {
                new {
                    //brew[WithSingleArg]
                }
            }
            "objects with custom constructors and unresolved arguments" in {
                new {
                    //brewWith(WithCustomConstructors.apply(_: WithSingleArg))
                }
            }
            "objects with ambiguous constructor and unresolved arguments" in {
                new {
                    //brew[WithCustomConstructors]
                }
            }
            "object trees with ambiguous constructor and unresolved arguments" in {
                new {
                    //brewRec[WithCustomConstructors]
                }
            }
        }
    }
}
