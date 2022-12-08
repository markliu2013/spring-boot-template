#!/bin/bash
clear
export $(grep -v '^#' .env | xargs)
./gradlew bootRun