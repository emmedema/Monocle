package monocle.std

import monocle.{PPrism, Prism}

import scala.util.{Failure, Success, Try}

object utilTry extends TryOptics

trait TryOptics {
  final def pTrySuccess[A, B]: PPrism[Try[A], Try[B], A, B] =
    PPrism[Try[A], Try[B], A, B] {
      case Success(a) => Right(a)
      case Failure(e) => Left(Failure(e))
    }(Success.apply)

  final def trySuccess[A]: Prism[Try[A], A] =
    pTrySuccess[A, A]

  final def tryFailure[A]: Prism[Try[A], Throwable] =
    Prism[Try[A], Throwable] {
      case Success(a) => None
      case Failure(e) => Some(e)
    }(Failure.apply)
}
