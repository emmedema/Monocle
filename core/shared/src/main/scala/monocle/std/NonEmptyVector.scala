package monocle.std

import monocle.{Iso, PIso, PPrism, Prism}
import cats.data.{NonEmptyVector, OneAnd}

object nev extends NonEmptyVectorOptics

trait NonEmptyVectorOptics {
  final def pNevToOneAnd[A, B]: PIso[NonEmptyVector[A], NonEmptyVector[B], OneAnd[Vector, A], OneAnd[Vector, B]] =
    PIso((nev: NonEmptyVector[A]) => OneAnd[Vector, A](nev.head, nev.tail))((oneAnd: OneAnd[Vector, B]) =>
      NonEmptyVector(oneAnd.head, oneAnd.tail)
    )

  final def nevToOneAnd[A]: Iso[NonEmptyVector[A], OneAnd[Vector, A]] =
    pNevToOneAnd[A, A]

  final def pOptNevToVector[A, B]: PIso[Option[NonEmptyVector[A]], Option[NonEmptyVector[B]], Vector[A], Vector[B]] =
    PIso[Option[NonEmptyVector[A]], Option[NonEmptyVector[B]], Vector[A], Vector[B]](
      _.fold(Vector.empty[A])(_.toVector)
    )(
      NonEmptyVector.fromVector
    )

  final def optNevToVector[A]: Iso[Option[NonEmptyVector[A]], Vector[A]] =
    pOptNevToVector[A, A]

  final def pVectorToNev[A, B]: PPrism[Vector[A], Vector[B], NonEmptyVector[A], NonEmptyVector[B]] =
    PPrism((v: Vector[A]) => NonEmptyVector.fromVector[A](v).toRight(Vector.empty[B]))((nev: NonEmptyVector[B]) =>
      nev.toVector
    )

  final def vectorToNev[A]: Prism[Vector[A], NonEmptyVector[A]] =
    pVectorToNev[A, A]
}
