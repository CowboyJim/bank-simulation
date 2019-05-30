#!/usr/bin/env bash

BANKJAR=./bank-1.0.jar

java -Dspring.profiles.active=AlphaBank -jar $BANKJAR >> alphaBank.log &
alphaPid=$!
echo Alpha Bank PID: $alphaPid

java -Dspring.profiles.active=BetaBank -jar $BANKJAR >> betaBank.log &
betaPid=$!
echo Beta Bank PID: $betaPid

