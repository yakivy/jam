package jam.cats

import jam.CustomSpec
import jam.CustomSpec._
import org.scalatest.freespec.AnyFreeSpec

class CatsSpec extends AnyFreeSpec with CustomSpec {
    "Jam Cats" - {
        "should brew objects" - {
            "in simple module" in {
                new {
                    val a = jam.brew[WithEmptyArgs]
                    val aa = jam.brew[WithEmptyArgList]
                    val b = jam.cats.brewF[Option][WithSingleArg]
                    val c = jam.cats.brewF[Option][WithTwoArgs]
                    val d = jam.cats.brewF[Option][ParentObject.InObject]
                    val e = jam.cats.brewF[Option][WithGenericAndPlainArgs[WithSingleArg]]
                    val f = jam.cats.brewF[Option][WithTwoArgLists]
                    assert(c.get.a.eq(a) && c.get.b.eq(b.get) && b.get.a.eq(a))
                    assert(d.get.a.eq(a))
                    assert(e.get.a.eq(b.get) && e.get.b.eq(b.get))
                    assert(f.get.a.eq(aa) && f.get.b.eq(b.get) && f.get.c.eq(c.get))
                }
            }

            "in simple module with companion" in {
                new {
                    val a = jam.brew[WithEmptyArgs]
                    val b = jam.cats.brewF[Option][WithSingleArg]
                    val c = jam.cats.brewF[Option][WithTwoArgsCompanion]
                    val d = jam.cats.brewF[Option][WithTwoArgListsInOptionCompanion]
                    assert(c.get.a.eq(a) && c.get.b.eq(b.get) && b.get.a.eq(a))
                    assert(d.get.a.eq(a) && d.get.b.eq(b.get) && d.get.c.eq(c.get))
                }
            }

            "in simple module from argument" in {
                new {
                    object module {
                        val a = jam.cats.brewF[Option][WithEmptyArgs]
                    }
                    val b = jam.cats.brewFromF[Option][WithSingleArg](module)
                    assert(b.get.a.eq(module.a.get))
                }
            }

            "with argument in F[_] context" in {
                new {
                    val a = Option(new WithEmptyArgs)
                    val b = a.map(new WithSingleArg(_))

                    val f = jam.cats.brewWithF[Option](new WithCustomConstructors(_: WithSingleArg, _: Option[WithEmptyArgs]))

                    assert(f.get.a.eq(a.get))
                }
            }

            "with custom constructors" in {
                new {
                    val a = new WithEmptyArgs
                    val b = Option(new WithSingleArg(a))

                    val f = jam.cats.brewWithF[Option](WithCustomConstructors.apply(_: WithEmptyArgs))
                    val g = jam.cats.brewWithFlatF[Option]((a: WithSingleArg) => Option(WithCustomConstructors.apply(a)))
                    //causes compile error on Scala 2.x
                    //val h = b.flatMap(b => jam.cats.brewWithF[Option]((a: WithEmptyArgs) => WithCustomConstructors.apply(b, a)))
                    val h = b.flatMap(b =>
                        jam.cats.brewWithF[Option]((a: WithEmptyArgs) => WithCustomConstructors.apply(_: WithSingleArg, a)).map(_.apply(b))
                    )
                    val i = jam.cats.brewWithFlatF[Option](WithCustomConstructors.option _)

                    assert(f.get.a.eq(a))
                    assert(g.get.a.eq(a))
                    assert(h.get.a.eq(a))
                    assert(i.get.a.eq(a))
                }
            }

            "with custom constructors from argument" in {
                new {
                    object module {
                        val a = Option(new WithEmptyArgs)
                    }
                    val c = jam.cats.brewFromWithF[Option](module, new WithCustomConstructors(_: WithEmptyArgs))
                    val d = jam.cats.brewFromWithFlatF[Option](module, (a: WithEmptyArgs) => Option(new WithCustomConstructors(a)))
                    assert(c.get.a.eq(module.a.get))
                    assert(d.get.a.eq(module.a.get))
                }
            }

            "in simple module recursively" in {
                new {
                    val c = jam.cats.brewRecF[Option][WithTwoArgLists]
                    assert(!c.get.b.a.eq(c.get.c.a) && !c.get.b.eq(c.get.c.b))
                }
            }

            "in module with implicits recursively" in {
                implicit val a = new WithSingleArg(new WithEmptyArgs)
                new {
                    val c = jam.cats.brewRecF[Option][WithImplicitArgList]
                    assert(c.get.b.eq(a))
                }
            }

            "in simple module with companion recursively" in {
                new {
                    val c = jam.cats.brewRecF[Option][WithTwoArgListsInOptionCompanion]
                    assert(!c.get.a.eq(c.get.b.a) && !c.get.a.eq(c.get.c.a) && !c.get.b.eq(c.get.c.b))
                }
            }

            "in module with singleton from argument recursively" in {
                new {
                    object module {
                        val a = Option(new WithEmptyArgs)
                    }
                    val c = jam.cats.brewFromRecF[Option][WithTwoArgs](module)
                    assert(c.get.a.eq(module.a.get) && c.get.b.a.eq(module.a.get))
                }
            }

            "with custom constructors recursively" in {
                new {
                    val a = new WithEmptyArgs

                    val g = jam.cats.brewWithFlatRecF[Option]((a: WithSingleArg) => Option(WithCustomConstructors.apply(a)))
                    val h = jam.cats.brewWithRecF[Option](WithCustomConstructors.apply(_: WithSingleArg, _: WithEmptyArgs))
                    val i = jam.cats.brewWithFlatRecF[Option](WithCustomConstructors.option _)

                    assert(g.get.a.eq(a))
                    assert(h.get.a.eq(a))
                    assert(i.get.a.eq(a))
                }
            }

            "with custom constructors from argument recursively" in {
                new {
                    object module {
                        val a = Option(new WithEmptyArgs)
                    }

                    val g = jam.cats.brewFromWithFlatRecF[Option](module, (a: WithSingleArg) => Option(WithCustomConstructors.apply(a)))
                    val h = jam.cats.brewFromWithRecF[Option](module, WithCustomConstructors.apply(_: WithSingleArg, _: WithEmptyArgs))
                    val i = jam.cats.brewFromWithFlatRecF[Option](module, WithCustomConstructors.option _)

                    assert(g.get.a.eq(module.a.get))
                    assert(h.get.a.eq(module.a.get))
                    assert(i.get.a.eq(module.a.get))
                }
            }
        }
        "should short-circuit" in {
            new {
                val a = jam.brew[WithEmptyArgs]
                val b: Option[WithSingleArg] = None
                val c = jam.cats.brewF[Option][WithTwoArgs]
                assert(c == None)
            }
        }
    }
}
