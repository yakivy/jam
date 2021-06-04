package jam

import org.scalatest.freespec.AnyFreeSpec

class JamSpec extends AnyFreeSpec {
    "Jam should brew" - {
        class A extends (() => String) {
            override def apply(): String = "A"
        }
        @jam.tree.annotated.brewable class B(val a: A) extends (() => String) {
            override def apply(): String = s"B(${a()})"
        }
        class C(val a: A, val b: B) extends (() => String) {
            override def apply(): String = s"C(${a()},${b()})"
        }
        class D(val a: A)(implicit val b: B) extends (() => String) {
            override def apply(): String = s"D(${a()},${b()})"
        }
        object E {
            class F(val a: A) extends (() => String) {
                override def apply(): String = s"F(${a()})"
            }
        }
        class G[A <: (() => String)](val a: A, val b: B) extends (() => String) {
            override def apply(): String = s"G(${a()},${b()})"
        }

        "objects for simple module" in {
            new {
                val a = brew[A]
                val b = brew[B]
                val c = brew[C]
                val f = brew[E.F]
                val g = brew[G[B]]
                assert(c.apply() == "C(A,B(A))")
                assert(f.apply() == "F(A)")
                assert(g.apply() == "G(B(A),B(A))")
                assert(c.a.eq(a) && c.b.eq(b) && b.a.eq(a))
                assert(f.a.eq(a))
                assert(g.a.eq(b) && g.b.eq(b))
            }
        }

        "objects for module with execution blocks" in {
            new {
                val a1 = new A {
                    override def apply(): String = "A1"
                }
                val b = {
                    val a2 = new A {
                        override def apply(): String = "A2"
                    }
                    val b: B = tree.brew[B]
                    b
                }
                assert(b.apply() == "B(A1)")
                assert(b.a.eq(a1))
            }
        }

        "objects for composite module" in {
            trait Module {
                def a: A
                val b = brew[B]
            }
            new Module {
                def a = new A {
                    override def apply(): String = "A1"
                }
                val c = brew[C]
                assert(c.apply() == "C(A1,B(A1))")
                assert(!c.a.eq(b.a) && c.b.eq(b))
            }
        }

        "object trees for simple module" in {
            new {
                val c = tree.brew[C]
                assert(c.apply() == "C(A,B(A))")
                assert(!c.a.eq(c.b.a))
            }
        }

        "object trees for module with singleton" in {
            new {
                val a = new A
                val c = tree.brew[C]
                assert(c.apply() == "C(A,B(A))")
                assert(c.a.eq(c.b.a))
            }
        }

        "object trees for module with prototype" in {
            new {
                def a = new A {
                    override def apply(): String = "A1"
                }
                val c = tree.brew[C]
                assert(c.apply() == "C(A1,B(A1))")
                assert(!c.a.eq(c.b.a))
            }
        }

        "object trees for module with prototype that has empty param lists" in {
            new {
                def a()()()()()() = new A {
                    override def apply(): String = "A1"
                }
                val c = tree.brew[C]
                assert(c.apply() == "C(A1,B(A1))")
                assert(!c.a.eq(c.b.a))
            }
        }

        "object trees for module with implicits" in {
            implicit val b = new B(new A) {
                override def apply(): String = s"B1(${a()})"
            }
            new {
                val d = tree.brew[D]
                assert(d.apply() == "D(A,B1(A))")
                assert(d.b.eq(b))
            }
        }

        "annotated object trees for simple module" in {
            new {
                val a = new A
                val c = tree.annotated.brew[C]
                assert(c.apply() == "C(A,B(A))")
                assert(c.a.eq(c.b.a))
            }
        }
    }
}
