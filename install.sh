#!/usr/bin/env bash
curl -L -o /usr/local/bin/coursier https://git.io/vgvpD
coursier bootstrap com.geirsson:scalafmt-cli_2.12:1.5.1 \
  -r bintray:scalameta/maven \
  -o /usr/local/bin/scalafmt -f --standalone --main org.scalafmt.cli.Cli
scalafmt --version
