#include <cmath>
#include <iomanip>
#include <iostream>

int main() {
  double a = 0;
  int h = 0;
  int m = 0;

  while (scanf("%d:%d", &h, &m), (h != 0 || m != 0)) {
    a = std::abs(30 * h - 5.5 * m);
    printf("%.3lf\n", std::min(a, 360 - a));
  }
}