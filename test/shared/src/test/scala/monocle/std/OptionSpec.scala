package monocle.std

import monocle.MonocleSuite
import monocle.law.discipline.{IsoTests, PrismTests}
import monocle.law.discipline.function.{EachTests, EmptyTests, PossibleTests}

class OptionSpec extends MonocleSuite {
  checkAll("some", PrismTests(some[Int]))
  checkAll("none", PrismTests(none[Long]))
  checkAll("optionToDisjunction", IsoTests(optionToDisjunction[Int]))
  checkAll("pOptionToDisjunction", IsoTests(pOptionToDisjunction[Int, Int]))

  checkAll("each Option", EachTests[Option[Int], Int])
  checkAll("possible Option", PossibleTests[Option[Int], Int])
  checkAll("empty Option", EmptyTests[Option[Int]])
}
