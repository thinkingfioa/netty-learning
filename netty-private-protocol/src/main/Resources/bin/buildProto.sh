#!/bin/bash

protoc -I=../proto/ --java_out=../../java/ Body.proto
protoc -I=../proto/ --java_out=../../java/ Header.proto
protoc -I=../proto/ --java_out=../../java/ HeartbeatReqBody.proto
protoc -I=../proto/ --java_out=../../java/ HeartbeatRespBody.proto
protoc -I=../proto/ --java_out=../../java/ LoginReqBody.proto
protoc -I=../proto/ --java_out=../../java/ LoginRespBody.proto
protoc -I=../proto/ --java_out=../../java/ LogoutBody.proto
protoc -I=../proto/ --java_out=../../java/ ProtocolDataBody.proto
protoc -I=../proto/ --java_out=../../java/ ProtocolSubBody.proto
protoc -I=../proto/ --java_out=../../java/ Tail.proto
protoc -I=../proto/ --java_out=../../java/ ProtocolMessage.proto