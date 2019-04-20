#!/bin/bash

sbt "run -Dconfig.resource=development.conf 9001" -jvm-debug 7777