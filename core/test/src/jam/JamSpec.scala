package jam

import org.scalatest.freespec.AnyFreeSpec

class JamSpec extends AnyFreeSpec {
    "Jam" - {
        class WithEmptyArgs
        class WithSingleArg(val a: WithEmptyArgs)
        class WithTwoArgs(val a: WithEmptyArgs, val b: WithSingleArg)
        class WithImplicitArg(val a: WithEmptyArgs)(implicit val b: WithSingleArg)
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

            "objects for module with execution blocks" in {
                new {
                    val a1 = new WithEmptyArgs
                    val b = {
                        val a2 = new WithEmptyArgs
                        val b: WithSingleArg = tree.brew[WithSingleArg]
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

            "objects with custom constructors" in {
                new {
                    val a = new WithEmptyArgs
                    val b = new WithSingleArg(a)

                    val c = brew(new WithCustomConstructors(_: WithEmptyArgs))
                    val d = brew(new WithCustomConstructors(_: WithSingleArg))
                    val e = brew(new WithCustomConstructors(_: WithSingleArg, _: WithEmptyArgs))
                    val f = brew(WithCustomConstructors.apply(_: WithEmptyArgs))
                    val g = brew(WithCustomConstructors.apply(_: WithSingleArg))
                    val h = brew(WithCustomConstructors.apply(_: WithSingleArg, _: WithEmptyArgs))
                    val i = brew(WithCustomConstructors.custom _)

                    assert(c.a.eq(a))
                    assert(d.a.eq(a))
                    assert(e.a.eq(a))
                    assert(f.a.eq(a))
                    assert(g.a.eq(a))
                    assert(h.a.eq(a))
                    assert(i.a.eq(a))
                }
            }

            "object trees with custom constructors" in {
                new {
                    val a = new WithEmptyArgs

                    val c = tree.brew(new WithCustomConstructors(_: WithEmptyArgs))
                    val d = tree.brew(new WithCustomConstructors(_: WithSingleArg))
                    val e = tree.brew(new WithCustomConstructors(_: WithSingleArg, _: WithEmptyArgs))
                    val f = tree.brew(WithCustomConstructors.apply(_: WithEmptyArgs))
                    val g = tree.brew(WithCustomConstructors.apply(_: WithSingleArg))
                    val h = tree.brew(WithCustomConstructors.apply(_: WithSingleArg, _: WithEmptyArgs))
                    val i = tree.brew(WithCustomConstructors.custom _)

                    assert(c.a.eq(a))
                    assert(d.a.eq(a))
                    assert(e.a.eq(a))
                    assert(f.a.eq(a))
                    assert(g.a.eq(a))
                    assert(h.a.eq(a))
                    assert(i.a.eq(a))
                }
            }

            "object trees for simple module" in {
                new {
                    val c = tree.brew[WithTwoArgs]
                    assert(!c.a.eq(c.b.a))
                }
            }

            "object trees for module with singleton" in {
                new {
                    val a = new WithEmptyArgs
                    val c = tree.brew[WithTwoArgs]
                    assert(c.a.eq(c.b.a))
                }
            }

            "object trees for module with prototype" in {
                new {
                    def a = new WithEmptyArgs
                    val c = tree.brew[WithTwoArgs]
                    assert(!c.a.eq(c.b.a))
                }
            }

            "object trees for module with prototype that has empty param lists" in {
                new {
                    def a()()()()()() = new WithEmptyArgs
                    val c = tree.brew[WithTwoArgs]
                    assert(!c.a.eq(c.b.a))
                }
            }

            "object trees for module with implicits" in {
                implicit val b = new WithSingleArg(new WithEmptyArgs)
                new {
                    val d = tree.brew[WithImplicitArg]
                    assert(d.b.eq(b))
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
                    //brew(WithCustomConstructors.apply(_: WithSingleArg))
                }
            }
            "objects with ambiguous constructor and unresolved arguments" in {
                new {
                    //brew[WithCustomConstructors]
                }
            }
            "object trees with ambiguous constructor and unresolved arguments" in {
                new {
                    //tree.brew[WithCustomConstructors]
                }
            }
        }
    }
}
