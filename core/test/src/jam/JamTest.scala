package jam

import org.scalatest.freespec.AnyFreeSpec

class JamTest extends AnyFreeSpec {
    "Jam should be brewed" in {
        class A extends (() => String) {
            override def apply(): String = "A"
        }
        class B(val a: A) extends (() => String) {
            override def apply(): String = s"B(${a()})"
        }
        class C(val a: A, val b: B) extends (() => String) {
            override def apply(): String = s"C(${a()},${b()})"
        }

        new {
            val c = brew[C]
            assert(c.apply() == "C(A,B(A))")
            assert(!c.a.eq(c.b.a))
        }

        new {
            val a = new A
            val c = brew[C]
            assert(c.apply() == "C(A,B(A))")
            assert(c.a.eq(c.b.a))
        }

        new {
            def a = new A {
                override def apply(): String = "A1"
            }
            val c = brew[C]
            assert(c.apply() == "C(A1,B(A1))")
            assert(!c.a.eq(c.b.a))
        }

        new {
            def a()()()()()() = new A {
                override def apply(): String = "A1"
            }
            val c = brew[C]
            assert(c.apply() == "C(A1,B(A1))")
            assert(!c.a.eq(c.b.a))
        }
    }
}
