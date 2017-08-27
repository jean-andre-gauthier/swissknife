#!/bin/bash
folder=${1}
problem=${2}
from=${3}
to=${4}
cd ${folder}
touch ${problem}.java
for i in `seq ${from} ${to}`;
do
  touch ${problem}${i}.in
  touch ${problem}${i}.out
done
