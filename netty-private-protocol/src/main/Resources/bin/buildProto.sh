#!/bin/bash

protoc -I=../proto/ --java_out=../../java/ *.proto