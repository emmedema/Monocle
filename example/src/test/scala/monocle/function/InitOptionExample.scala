package monocle.function

import monocle.MonocleSuite

class InitOptionExample extends MonocleSuite {
  test("tail creates a Traversal from a List, IList, Vector or Stream to its tail") {
    (List(1, 2, 3) applyOptional initOption getOption) shouldEqual Some(List(1, 2))
    (List(1) applyOptional initOption getOption) shouldEqual Some(Nil)
    ((Nil: List[Int]) applyOptional initOption getOption) shouldEqual None

    (List(1, 2, 3) applyOptional initOption set List(4, 5, 6)) shouldEqual List(4, 5, 6, 3)
    (Vector(1, 2, 3) applyOptional initOption set Vector(4, 5, 6)) shouldEqual Vector(4, 5, 6, 3)
  }

  test("tail creates a Traversal from a String to its tail") {
    ("hello" applyOptional initOption modify (_.toUpperCase)) shouldEqual "HELLo"
  }
}
