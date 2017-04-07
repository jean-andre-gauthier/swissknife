#!/bin/bash
folder=${1};
problem=${2};
from=${3};
to=${4}
cd ${folder};
javac ${problem}.java
for i in `seq ${from} ${to}`;
do
  echo "--- ${i}.in / ${i}.out ---";
  java ${problem} < ${problem}${i}.in | diff - ${problem}${i}.out;
done