#include <stdio.h>

int main() {
  printf("%d\n", -5 % 2);
  return 0;
  // To run
  // gcc modulo.c -o modulo && ./modulo
  // PRINTS -1
  // C performs modulo operations using truncated division
}
