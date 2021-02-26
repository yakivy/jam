package jam

import org.scalatest.freespec.AnyFreeSpec

class JamSpec extends AnyFreeSpec {
    "Jam should be brewed" in {
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

        new {
            val a: A = brew[A]
            val b: B = brew[B]
            val c: C = brew[C]
            assert(c.apply() == "C(A,B(A))")
            assert(c.a.eq(a) && c.b.eq(b) && b.a.eq(a))
        }

        new {
            val a = new A
            val c: C = tree.annotated.brew[C]
            assert(c.apply() == "C(A,B(A))")
            assert(c.a.eq(c.b.a))
        }

        new {
            val c: C = tree.brew[C]
            assert(c.apply() == "C(A,B(A))")
            assert(!c.a.eq(c.b.a))
        }

        new {
            val a = new A
            val c: C = tree.brew[C]
            assert(c.apply() == "C(A,B(A))")
            assert(c.a.eq(c.b.a))
        }

        new {
            def a = new A {
                override def apply(): String = "A1"
            }
            val c: C = tree.brew[C]
            assert(c.apply() == "C(A1,B(A1))")
            assert(!c.a.eq(c.b.a))
        }

        new {
            def a()()()()()() = new A {
                override def apply(): String = "A1"
            }
            val c: C = tree.brew[C]
            assert(c.apply() == "C(A1,B(A1))")
            assert(!c.a.eq(c.b.a))
        }

        {
            implicit val b = new B(new A) {
                override def apply(): String = s"B1(${a()})"
            }
            new {
                val d: D = tree.brew[D]
                assert(d.apply() == "D(A,B1(A))")
            }
        }
    }
}
