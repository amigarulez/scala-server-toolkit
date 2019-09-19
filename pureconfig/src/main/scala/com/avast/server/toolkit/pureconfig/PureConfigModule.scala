package com.avast.server.toolkit.pureconfig

import cats.data.NonEmptyList
import cats.effect.Sync
import cats.syntax.either._
import pureconfig.error.{ConfigReaderFailure, ConfigReaderFailures, ConvertFailure}
import pureconfig.{ConfigReader, ConfigSource}

import scala.language.higherKinds
import scala.reflect.ClassTag

/** Provides loading of configuration into case class via PureConfig. */
object PureConfigModule {

  /** Loads the case class `A` using Lightbend Config's standard behavior. */
  def make[F[_]: Sync, A: ConfigReader]: F[Either[NonEmptyList[String], A]] = make(ConfigSource.default)

  /** Loads the case class `A` using provided [[pureconfig.ConfigSource]]. */
  def make[F[_]: Sync, A: ConfigReader](source: ConfigSource): F[Either[NonEmptyList[String], A]] = {
    Sync[F].delay(source.load[A].leftMap(convertFailures))
  }

  /** Loads the case class `A` using Lightbend Config's standard behavior or throws an exception. */
  def makeOrThrow[F[_]: Sync, A: ConfigReader: ClassTag]: F[A] = makeOrThrow(ConfigSource.default)

  /** Loads the case class `A` using provided [[pureconfig.ConfigSource]] or throws an exception. */
  def makeOrThrow[F[_]: Sync, A: ConfigReader: ClassTag](source: ConfigSource): F[A] = Sync[F].delay(source.loadOrThrow[A])

  private def convertFailures(failures: ConfigReaderFailures): NonEmptyList[String] = {
    NonEmptyList(failures.head, failures.tail).map(formatFailure)
  }

  private def formatFailure(configReaderFailure: ConfigReaderFailure): String = {
    configReaderFailure match {
      case convertFailure: ConvertFailure =>
        s"Invalid configuration ${convertFailure.path}: ${convertFailure.description}"
      case configFailure =>
        s"Invalid configuration : ${configFailure.description}"
    }
  }

}
