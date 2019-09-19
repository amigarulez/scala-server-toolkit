# Scala Server Toolkit

[![Build Status](https://travis-ci.org/avast/scala-server-toolkit.svg?branch=master)](https://travis-ci.org/avast/scala-server-toolkit)
![GitHub tag (latest SemVer)](https://img.shields.io/github/v/tag/avast/scala-server-toolkit?color=white&label=version&sort=semver)

This project is a culmination of years of Scala development at Avast and tries to represent the best practices of Scala server development 
we have gained together with tools that allow us to be effective. It is a set of small, flexible and cohesive building blocks that fit 
together well and allow you to build reliable server applications.

## [Documentation](./docs/index.md)

Or you can [deep dive into example code](example/src/main/scala/com/avast/server/toolkit/example/Main.scala) if you like that more.

## Design

There are certain design decisions and constraints that are put in place to guide the development of the toolkit and recommended for 
the development of your server applications.

* Keep the number of dependencies as low as possible.
* Modular design: small, cohesive, orthogonal and composable components.
* Functional programming.
* Type safe configuration and resource lifecycle.
* No need for dependency injection.
* [Scalazzi Safe Scala Subset](https://slides.yowconference.com/yowwest2014/Morris-ParametricityTypesDocumentationCodeReadability.pdf)

## Issues

Please report issues to [GitHub](https://github.com/avast/scala-server-toolkit/issues).

## License

Scala Server Toolkit is licensed under [MIT License](LICENSE).
