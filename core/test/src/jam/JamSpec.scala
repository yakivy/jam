package jam

import org.scalatest.freespec.AnyFreeSpec

class JamSpec extends AnyFreeSpec {
    "Jam should brew" - {
        class WithEmptyArgs
        @jam.tree.annotated.brewable class WithSingleArg(val a: WithEmptyArgs)
        class WithTwoArgs(val a: WithEmptyArgs, val b: WithSingleArg)
        class WithImplicitArg(val a: WithEmptyArgs)(implicit val b: WithSingleArg)
        object ParentObject {
            class InObject(val a: WithEmptyArgs)
        }
        class WithGenericAndPlainArgs[A](val a: A, val b: WithSingleArg)
        class WithStringArg(val a: String)

        "objects for simple module" in {
            new {
                val a = brew[WithEmptyArgs]
                val b = brew[WithSingleArg]
                val c = brew[WithTwoArgs]
                val f = brew[ParentObject.InObject]
                val g = brew[WithGenericAndPlainArgs[WithSingleArg]]
                assert(c.a.eq(a) && c.b.eq(b) && b.a.eq(a))
                assert(f.a.eq(a))
                assert(g.a.eq(b) && g.b.eq(b))
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

        "object trees for simple module" in {
            new {
                val c = tree.brew[WithTwoArgs]
                assert(!c.a.eq(c.b.a))
            }
        }

        "object trees for module with string" in {
            new {
                val a = "string"
                val b = tree.brew[WithStringArg]
                assert(b.a.eq(a))
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

        "annotated object trees for simple module" in {
            new {
                val a = new WithEmptyArgs
                val c = tree.annotated.brew[WithTwoArgs]
                assert(c.a.eq(c.b.a))
            }
        }
    }
}
